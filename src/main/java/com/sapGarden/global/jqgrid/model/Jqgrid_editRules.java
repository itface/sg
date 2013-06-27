package com.sapGarden.global.jqgrid.model;

import java.io.Serializable;

public class Jqgrid_editRules implements Serializable{

	private static final long serialVersionUID = 8961653795826721675L;

	private boolean required;
	private boolean custom;
	private String custom_func;
	public Jqgrid_editRules(boolean required,boolean custom,String custom_func){
		this.required=required;
		this.custom=custom;
		this.custom_func=custom_func;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public boolean isCustom() {
		return custom;
	}
	public void setCustom(boolean custom) {
		this.custom = custom;
	}
	public String getCustom_func() {
		return custom_func;
	}
	public void setCustom_func(String custom_func) {
		this.custom_func = custom_func;
	}
	
}
