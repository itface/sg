package com.sapGarden.application.synConfig.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="bo_synconfig_runtimelog")
public class RuntimeLog implements Serializable{

	private static final long serialVersionUID = 8961653795826720705L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_synconfig_runtimelog")
	@SequenceGenerator(name="seq_synconfig_runtimelog",sequenceName="seq_synconfig_runtimelog",allocationSize=1)
	private long id;
	private String operatoruid;
	private Date operatetime;
	private String operateObject;
	private String operateResult;
	private String businesstype;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOperatoruid() {
		return operatoruid;
	}
	public void setOperatoruid(String operatoruid) {
		this.operatoruid = operatoruid;
	}
	public Date getOperatetime() {
		return operatetime;
	}
	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}
	public String getOperateObject() {
		return operateObject;
	}
	public void setOperateObject(String operateObject) {
		this.operateObject = operateObject;
	}
	public String getOperateResult() {
		return operateResult;
	}
	public void setOperateResult(String operateResult) {
		this.operateResult = operateResult;
	}
	public String getBusinesstype() {
		return businesstype;
	}
	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}
	
}
