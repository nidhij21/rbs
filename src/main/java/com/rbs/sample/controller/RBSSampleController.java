package com.rbs.sample.controller;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RBSSampleController {
	@Autowired
	JmsTemplate jmsTemplate;
	
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

}
