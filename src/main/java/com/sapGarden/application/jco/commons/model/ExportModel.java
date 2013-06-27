package com.sapGarden.application.jco.commons.model;

import java.io.Serializable;

public class ExportModel implements Serializable{

	private static final long serialVersionUID = 8961653795826720635L;

	private String propertyName;
	private Class propertyClass;
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public Class getPropertyClass() {
		return propertyClass;
	}
	public void setPropertyClass(Class propertyClass) {
		this.propertyClass = propertyClass;
	}
}
