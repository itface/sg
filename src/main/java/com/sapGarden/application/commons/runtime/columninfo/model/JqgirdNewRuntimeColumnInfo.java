package com.sapGarden.application.commons.runtime.columninfo.model;

import java.io.Serializable;

public class JqgirdNewRuntimeColumnInfo implements Serializable{

	private static final long serialVersionUID = 8961653795826721615L;

	private String id;
	private int ifkey;
	private String sourceColumn;
	private String sourceColumnName;
	private String targetColumn;
	private String targetColumnName;
	private String status;
	private String businesstype;
	private long sapclient;
	private int searchable;
	private String searchtype;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSourceColumn() {
		return sourceColumn;
	}
	public void setSourceColumn(String sourceColumn) {
		this.sourceColumn = sourceColumn;
	}
	public String getTargetColumn() {
		return targetColumn;
	}
	public void setTargetColumn(String targetColumn) {
		this.targetColumn = targetColumn;
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
	public String getSourceColumnName() {
		return sourceColumnName;
	}
	public void setSourceColumnName(String sourceColumnName) {
		this.sourceColumnName = sourceColumnName;
	}
	public String getTargetColumnName() {
		return targetColumnName;
	}
	public void setTargetColumnName(String targetColumnName) {
		this.targetColumnName = targetColumnName;
	}
	public int getIfkey() {
		return ifkey;
	}
	public void setIfkey(int ifkey) {
		this.ifkey = ifkey;
	}
	public long getSapclient() {
		return sapclient;
	}
	public void setSapclient(long sapclient) {
		this.sapclient = sapclient;
	}
	public int getSearchable() {
		return searchable;
	}
	public void setSearchable(int searchable) {
		this.searchable = searchable;
	}
	public String getSearchtype() {
		return searchtype;
	}
	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}
	
}
