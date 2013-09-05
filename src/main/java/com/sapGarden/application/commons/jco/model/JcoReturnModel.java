package com.sapGarden.application.commons.jco.model;

import java.io.Serializable;
import java.util.Map;

public class JcoReturnModel implements Serializable{

	private static final long serialVersionUID = 8961653795826720675L;

	private Map<String,Object> tableMap;
	private Map<String,Object> exportMap;
	public Map<String, Object> getTableMap() {
		return tableMap;
	}
	public void setTableMap(Map<String, Object> tableMap) {
		this.tableMap = tableMap;
	}
	public Map<String, Object> getExportMap() {
		return exportMap;
	}
	public void setExportMap(Map<String, Object> exportMap) {
		this.exportMap = exportMap;
	}
	
}
