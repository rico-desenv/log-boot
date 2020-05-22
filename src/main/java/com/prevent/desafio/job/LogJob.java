package com.prevent.desafio.job;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.prevent.desafio.entity.Log;
import com.prevent.desafio.service.LogService;

@Component
class LogJob {

	@Autowired
	private LogService service;
	
    private final AtomicBoolean enabled = new AtomicBoolean(true);

    @Scheduled(fixedRate = 10000)
    void execute() throws IOException, ParseException {
        if (enabled.get()) {
        	
    		Properties prop = new Properties();
    		prop.load(LogService.class.getClassLoader().getResourceAsStream("config.properties"));
    		String diretorio=prop.getProperty("diretorio.upload");
    		String arquivo=prop.getProperty("arquivo.job");
        	
        	String file=diretorio + arquivo;
			List<String> strings = null;
			if (!Files.exists(Paths.get(file)))
				return;
			
			System.out.println("Log Job Initialize");
			strings = Files.readAllLines(Paths.get(file));
			System.out.println("Log Job file:" + file);

			int cont=0;
			for (String line : strings) {
				cont++;
				String[] split = line.split("\\|");

				String data = split[0];
				String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
				SimpleDateFormat dateFormatGmt = new SimpleDateFormat(pattern);

				Date dataParsed = null;
				dataParsed = dateFormatGmt.parse(data);
				
				String ip = split[1];
				String request = split[2];
				Integer status = Integer.valueOf(split[3]);
				String userAgent = split[4];

				Log log = new Log(dataParsed, ip, request, status, userAgent);
				service.save(log);
				
				System.out.println("Log Job Register : " + cont);
				System.out.println("Log Job IP : " + ip);
			}
			
			Files.deleteIfExists(Paths.get(file));
			
			System.out.println("Log Job Total : " + cont);
			System.out.println("Log Job Finish");
        }
    }

    void toggle() {
        enabled.set(!enabled.get());
    }

}