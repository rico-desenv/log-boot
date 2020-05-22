package com.prevent.desafio.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prevent.desafio.entity.Log;
import com.prevent.desafio.repository.LogRepository;

@Service
@Transactional
public class LogServiceImpl implements LogService {

	@Autowired
	private LogRepository repository;

	@Override
	public void save(@Valid Log log) {
		repository.save(log);
	}
	
	@Override
	public List<Log> getLogs(String ip) {
		if (ip.isEmpty())		
			return repository.findAll();
		
		List<Log> logs;
		logs = repository.findByIp(ip);

		return logs;
	}
	
	@Override
	public Optional<Log> editLog(@Valid long id) {
		Optional<Log> log = repository.findById(id);
		return log;
	}	

	@Override
	public void deleteLog(@Valid long id) {
		Optional<Log> log;
		log=editLog(id);
		if ( log != null)		
			repository.deleteById(id);

		return;
	}
	
	@Override
	public void updateLog(@Valid long id, @Valid Log log) {
		Optional<Log> log_=editLog(id);
		if ( log_ == null)		
			return;

		log.setId(id);
		repository.save(log);
		return;
	}

}

