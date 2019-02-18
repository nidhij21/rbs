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

package com.rbs.sample.receiver;

import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * This class is used to listen and process the TM internal queue
 * 
 * Narendra.Kumar
 *
 * Jun 1, 2018
 *
 */
@Component
//@Lazy
public class InternalTMTriggerQueueReceiver {

	
	@JmsListener(destination = "${jms.jndi.tm.trigger.queue}", containerFactory = "jmsListenerContainerFactory")
	public void receiveInternalTMTriggerQueueMessage(Message msg) {
		System.out.println(msg);
	} // end method onMessage

	}
