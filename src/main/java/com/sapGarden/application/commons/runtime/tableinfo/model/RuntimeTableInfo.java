package com.sapGarden.application.commons.runtime.tableinfo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="bo_synconfig_runtime_dbinfo")
public class RuntimeTableInfo implements Serializable{

	private static final long serialVersionUID = 8961653795826720612L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_RUNTIMECONFIG_TABLEREFLECT")
	@SequenceGenerator(name="SEQ_RUNTIMECONFIG_TABLEREFLECT",sequenceName="SEQ_RUNTIMECONFIG_TABLEREFLECT",allocationSize=1)
	private long id;
	private String reflectTable;
	private String reflectTableMemo;
	private String businesstype;
	private long sapclient;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getReflectTable() {
		return reflectTable;
	}
	public void setReflectTable(String reflectTable) {
		this.reflectTable = reflectTable;
	}
	public String getReflectTableMemo() {
		return reflectTableMemo;
	}
	public void setReflectTableMemo(String reflectTableMemo) {
		this.reflectTableMemo = reflectTableMemo;
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
