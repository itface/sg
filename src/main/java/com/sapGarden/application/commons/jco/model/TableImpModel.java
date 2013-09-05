package com.sapGarden.application.commons.jco.model;

import java.io.Serializable;
import java.util.List;

public class TableImpModel implements Serializable{

	private static final long serialVersionUID = 8961653795826720625L;

	private String tableName;
	private List<Object> tableData;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<Object> getTableData() {
		return tableData;
	}
	public void setTableData(List<Object> tableData) {
		this.tableData = tableData;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
