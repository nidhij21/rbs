package com.rbs.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rbs.sample.dao.RBSDAO;
import com.rbs.sample.model.Recordings;

@RestController
public class RBSSampleController {
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	RBSDAO rbsdao;
	
	
	@RequestMapping(value = "/getRecordings", method = RequestMethod.GET)
	public String testRest() {
		List<Recordings> recordings = rbsdao.getRecordings();
		System.out.println(recordings);
		return "Success" + recordings;
	}

}
