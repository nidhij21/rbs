package com.rbs.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RBSSampleController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value = "/testRest", method = RequestMethod.GET)
	public String testRest() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://bgc.india.rsystems.com:8085/rbs/test1", String.class);
		System.out.println(response.getBody());
		return response.getBody();
	}

}
