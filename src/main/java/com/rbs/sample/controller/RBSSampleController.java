package com.rbs.sample.controller;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
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
	RBSDAO rbsdao;@Autowired
	JmsTemplate jmsTemplate;
	
	@Value("${jms.naming.provider.port}")
	private String jmsNamingProviderPort;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String generateBrokerDocument() {
		try {
			jmsTemplate.send("BrokerGenerationQueue", new MessageCreator() {
			      @Override
			      public Message createMessage(Session session) throws JMSException {
			          return session.createTextMessage("Test");
			      }
			  });
		} catch (JmsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Test";
	}
	
	
	@RequestMapping(value = "/getRecordings", method = RequestMethod.GET)
	public String testRest() {
		List<Recordings> recordings = rbsdao.getRecordings();
		System.out.println(recordings);
		return "Success" + recordings;
	}
	
	@RequestMapping(value = "/getPort", method = RequestMethod.GET)
	public String getPort() {
		
		return "Success" + jmsNamingProviderPort;
	}
	
	@RequestMapping(value = "/getRbsPort", method = RequestMethod.GET)
	public String getRbsPort() {
		ResponseEntity<String> response = restTemplate.getForEntity("rbs/rbs/test1", String.class);
		System.out.println(response.getBody());
		
		return "Success: " + response.getBody();
	}

}
 
 
