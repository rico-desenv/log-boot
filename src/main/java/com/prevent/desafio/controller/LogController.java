package com.prevent.desafio.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.prevent.desafio.entity.Log;
import com.prevent.desafio.service.LogService;

@Controller
@RequestMapping("/Log/")
public class LogController {

	@Autowired
	private LogService service;

	private List<Log> logs;

	@PostMapping("add")
	@ResponseBody
	public ResponseEntity<Log> add(@RequestParam String ip, @RequestParam String request, @RequestParam String data,
			@RequestParam int status, @RequestParam String useragent) {
		System.out.println("LogController.add - inicio");

		Log log = new Log();
		log.setIp(ip);
		log.setRequest(request);

		String pattern = "yyyy-MM-dd";
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat(pattern);

		Date dataParsed = null;
		try {
			dataParsed = dateFormatGmt.parse(data);
		} catch (ParseException e) {
			System.out.println("LogController.add - Erro data parse");
		}
		log.setData(dataParsed);
		log.setStatus(status);
		log.setUserAgent(useragent);

		service.save(log);

		System.out.println("LogController.add - fim");
		return new ResponseEntity<>(log, HttpStatus.OK);
	}

	@RequestMapping(value = "listar", method = RequestMethod.GET)
	public ResponseEntity<List<Log>> listar(@RequestParam String ip) {
		System.out.println("LogController.listar - inicio");
		logs = new ArrayList<Log>();

		logs = service.getLogs(ip);

		if (logs == null)
			return new ResponseEntity<>(logs, HttpStatus.INTERNAL_SERVER_ERROR);

		System.out.println("LogController.listar - fim");
		return new ResponseEntity<>(logs, HttpStatus.OK);
	}

	@GetMapping("editar/{id}")
	public ResponseEntity<Log> editar(@Valid long id) {
		System.out.println("LogController.editar - inicio");
		Optional<Log> log = service.editLog(id);

		if (log == null)
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		System.out.println("LogController.editar - final");
		return new ResponseEntity<>(log.get(), HttpStatus.OK);
	}

	@PostMapping("atualizar/{id}")
	public ResponseEntity<Log> updateLog(@Valid long id, @Valid Log log) {
		System.out.println("LogController.atualizar - inicio");

		service.updateLog(id, log);

		System.out.println("LogController.atualizar - fim");
		return new ResponseEntity<>(log, HttpStatus.OK);
	}

	@PostMapping("apagar")
	@ResponseBody
	public ResponseEntity<Log> apagar(@RequestParam String id) {
		System.out.println("LogController.apagar- inicio");

		Optional<Log> log;

		long _id = Long.parseLong(id);

		log = service.editLog(_id);
		if (log == null)
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		service.deleteLog(_id);

		System.out.println("LogController.apagar - final");

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping(value = "/arquivo", method = RequestMethod.POST)
	public ResponseEntity<Log> UploadFile(MultipartHttpServletRequest request) throws IOException {
		Iterator<String> itr = request.getFileNames();
		MultipartFile file = request.getFile(itr.next());
		String fileName = file.getOriginalFilename();
		Properties prop = new Properties();
		prop.load(LogController.class.getClassLoader().getResourceAsStream("config.properties"));
		String diretorio=prop.getProperty("diretorio.upload");		
		File dir = new File(diretorio);		
					
		if (dir.isDirectory()) {
			File serverFile = new File(dir, fileName);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(file.getBytes());
			stream.close();
		}
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
