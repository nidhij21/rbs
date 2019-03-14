

package com.rbs.sample.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the RECORDINGS database table.
 * @author Deepak.Goel
 *
 */
@Entity
@Table(name="RECORDINGS")
public class Recordings implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RECORDINGS_ID_GENERATOR")
	@SequenceGenerator(name="RECORDINGS_ID_GENERATOR", sequenceName="RECORDINGS_SEQ", allocationSize=1)
	@Column(unique=true, nullable=false, precision=11)
	private Long id;
	
	@Column(length=300, name ="SOURCEURL")
	private String sourceURL;
	
	
	@Column(length=100, name ="FILENAME")
	private String fileName;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getSourceURL() {
		return sourceURL;
	}


	public void setSourceURL(String sourceURL) {
		this.sourceURL = sourceURL;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
	
}