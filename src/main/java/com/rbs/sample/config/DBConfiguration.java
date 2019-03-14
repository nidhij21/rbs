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

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiTemplate;

/**
 * This class is used to configure data-source and JDBC template beans
 * nidhi.jain
 *
 * Jun 11, 2018
 *
 */
@Configuration
public class DBConfiguration {
	@Value("${spring.datasource.jndi-name}")
	private String databaseJndi;
	private static final Logger LOGGER = LogManager.getLogger(DBConfiguration.class);

	@Bean
	public DataSource dataSource() throws RuntimeException, NamingException {
		LOGGER.debug("Creating datasource +");
		return (DataSource) new JndiTemplate().lookup(databaseJndi);
	}

	@Bean
	JdbcTemplate jdbcTemplate(DataSource dataSource) throws NamingException {
		LOGGER.debug("Creating jdbctemplate +");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		LOGGER.debug("Creating jdbctemplate -");
		return jdbcTemplate;
	}

}
