/***************************************************************************
 *  Copyright (C) Proximus 2018
 *
 *  The reproduction, transmission or use of this document  or its contents
 *  is not  permitted without  prior express written consent of Proximus.
 *  Offenders will be liable for damages. All rights, including  but not 
 *  limited to rights created by patent grant or registration of a utility
 *  model or design, are reserved.
 *
 *  Proximus reserves the right to modify technical specifications and features.
 *
 *  Technical specifications and features are binding only in so far as they
 *  are specifically and expressly agreed upon in a written contract.
 *
 **************************************************************************/

package com.rbs.sample.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.boot.autoconfigure.jms.JmsProperties.AcknowledgeMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * This is used to configure the JMS listener
 * 
 * @author Narendra.Kumar
 *
 * Jun 7, 2018
 *
 */

@Configuration
public class RBSMessageListenerConfiguration {

	//private static final Logger LOGGER = LogManager.getLogger(BDGSMessageListenerConfiguration.class);

	@Autowired
	private JmsProperties jmsProperties;

	@Autowired
	private CachingConnectionFactory connectionFactory;

	/**
	 * This method is used to configure the JMS container factory for JMS listener
	 * 
	 * @param configurer
	 * @return
	 */
	@Bean(name = "jmsListenerContainerFactory")
	public JmsListenerContainerFactory<?> jmsContainerFactory(DefaultJmsListenerContainerFactoryConfigurer configurer) {
		//LOGGER.debug("jmsListenerContainerFactory  +");

		Integer minThread = jmsProperties.getListener().getConcurrency();
		Integer maxThread = jmsProperties.getListener().getMaxConcurrency();
		AcknowledgeMode acknowledgeMode = jmsProperties.getListener().getAcknowledgeMode();

		if (minThread == null) {
			minThread = 1;
		}
		if (maxThread == null) {
			maxThread = minThread;
		}

		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setSessionTransacted(true);
		factory.setConcurrency(minThread.toString() + "-" + maxThread.toString());
		if (acknowledgeMode != null) {
			factory.setSessionAcknowledgeMode(acknowledgeMode.getMode());
		}
		//LOGGER.debug("jmsListenerContainerFactory  -");
		return factory;
	}

	/**
	 * This method is used to Serialize message content to json using TextMessage
	 * 
	 * @return
	 */
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		//LOGGER.debug("jacksonJmsMessageConverter  ===>");
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
}