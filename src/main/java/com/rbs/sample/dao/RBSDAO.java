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
package com.rbs.sample.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rbs.sample.model.Recordings;



/**
 * This Class is for Implementing MDP related DAO Methods
 * 
 * @author deepesh.gupta
 *
 */
@Repository
public class RBSDAO  {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	

	/**
	 * This gets Available MDPs for Channel
	 * 
	 * @param channelID
	 * @return
	 */
	public List<Recordings> getRecordings() {

		List<Recordings> recordingsList = new ArrayList<Recordings>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList("Select * from RECORDINGS");

		for (Map<String, Object> row : rows) {
			Recordings recordings = new Recordings();
			recordings.setFileName((String)row.get("FILENAME"));
			recordings.setSourceURL((String)row.get("SOURCEURL"));
			recordings.setId(((BigDecimal)row.get("ID")).longValue());
			recordingsList.add(recordings);
		}
		return recordingsList;
	}

	}
