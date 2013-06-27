package com.sapGarden.application.synConfig.model;

import java.io.Serializable;

public class JqgridNewCallFunParamInfo implements Serializable{

	private static final long serialVersionUID = 8961653795826721625L;
	private String id;
	private String paramname;
	private String paramvalue;
	private String businesstype;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParamname() {
		return paramname;
	}
	public void setParamname(String paramname) {
		this.paramname = paramname;
	}
	public String getParamvalue() {
		return paramvalue;
	}
	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}
	public String getBusinesstype() {
		return businesstype;
	}
	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}
	
}
