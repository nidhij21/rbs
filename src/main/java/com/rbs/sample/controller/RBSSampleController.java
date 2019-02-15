package com.rbs.sample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RBSSampleController {
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String generateBrokerDocument() {
		return "Test";
	}

}
