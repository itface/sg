package com.sapGarden.application.commons.jco.model;

import java.io.Serializable;

public class ImportModel implements Serializable{

	private static final long serialVersionUID = 8961653795826720655L;

	private String propertyName;
	private Object propertyData;
	
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public Object getPropertyData() {
		return propertyData;
	}
	public void setPropertyData(Object propertyData) {
		this.propertyData = propertyData;
	}
	
	
}
