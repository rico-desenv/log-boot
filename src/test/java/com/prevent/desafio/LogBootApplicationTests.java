package com.prevent.desafio;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.prevent.desafio.controller.LogController;
import com.prevent.desafio.entity.Log;

@SpringBootTest
class LogBootApplicationTests {
	
	@Autowired
	LogController controller;

	@Test
	public void cadastrarLog() {	
		Date agora = new Date();
		controller.add("9999", "99",agora.toString(), 9, "9999");
	}
	
	@Test
	public void updateLog() {
		Log log = new Log(null, "555", "555", 5, "55555");
		Long _id = (long) 116534;
		controller.updateLog(_id, log);
	}
	
	@Test
	public void deleteLog() {
		String id = "116534";
		controller.apagar(id);
	}
	
	
}
