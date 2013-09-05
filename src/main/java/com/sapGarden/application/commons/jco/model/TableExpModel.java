package com.sapGarden.application.commons.jco.model;

import java.io.Serializable;

public class TableExpModel implements Serializable{

	private static final long serialVersionUID = 8961653795826720645L;
	private String tableName;
	private Class tableStructureClass;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Class getTableStructureClass() {
		return tableStructureClass;
	}
	public void setTableStructureClass(Class tableStructureClass) {
		this.tableStructureClass = tableStructureClass;
	}

}
