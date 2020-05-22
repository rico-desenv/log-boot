package com.prevent.desafio.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.context.annotation.Configuration;

import com.prevent.desafio.entity.Log;

@Configuration
public interface LogService {

	void save(@Valid Log log);

	List<Log> getLogs(String ip);

	Optional<Log> editLog(@Valid long id);

	void deleteLog(@Valid long id);

	void updateLog(@Valid long id, @Valid Log log);
	
}
