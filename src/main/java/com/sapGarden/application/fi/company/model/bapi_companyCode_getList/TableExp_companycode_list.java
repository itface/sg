package com.sapGarden.application.fi.company.model.bapi_companyCode_getList;

import java.io.Serializable;

public class TableExp_companycode_list implements Serializable{

	private static final long serialVersionUID = 8961653795826720695L;
	private String comp_code;
	private String comp_name;
	public String getComp_code() {
		return comp_code;
	}
	public void setComp_code(String comp_code) {
		this.comp_code = comp_code;
	}
	public String getComp_name() {
		return comp_name;
	}
	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}
}
