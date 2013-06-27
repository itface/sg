package com.sapGarden.application.commons.runtime.baseinfo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="BO_SYNCONFIG_RUNTIME_BASICINFO")
public class RuntimeBasicInfo implements Serializable{

	private static final long serialVersionUID = 8961653795826720614L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_RUNTIMECONFIG_BASICINFO")
	@SequenceGenerator(name="SEQ_RUNTIMECONFIG_BASICINFO",sequenceName="SEQ_RUNTIMECONFIG_BASICINFO",allocationSize=1)
	private long id;
	private String status;
	private long sapclient;
	private String businesstype;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBusinesstype() {
		return businesstype;
	}
	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}
	public long getSapclient() {
		return sapclient;
	}
	public void setSapclient(long sapclient) {
		this.sapclient = sapclient;
	}
	
	
	
}
