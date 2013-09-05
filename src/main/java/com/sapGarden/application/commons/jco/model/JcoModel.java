package com.sapGarden.application.commons.jco.model;

import java.io.Serializable;
import java.util.List;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public class JcoModel implements Serializable{

	private static final long serialVersionUID = 8961653795826720665L;

	private SapDataCollection sapDataCollection;
	private String rfcName;
	private List<TableImpModel> tableImpModelList;
	private List<TableExpModel> tableExpModelList;
	private List<ExportModel> exportModelList;
	private List<ImportModel> importModelList;
	public JcoModel(SapDataCollection sapDataCollection,String rfcName,List<TableImpModel> tableImpModelList,List<TableExpModel> tableExpModelList,List<ImportModel> importModelList,List<ExportModel> exportModelList){
		this.rfcName=rfcName;
		this.tableImpModelList=tableImpModelList;
		this.tableExpModelList=tableExpModelList;
		this.importModelList=importModelList;
		this.exportModelList=exportModelList;
		this.sapDataCollection=sapDataCollection;
	}
	public String getRfcName() {
		return rfcName;
	}
	public void setRfcName(String rfcName) {
		this.rfcName = rfcName;
	}
	public List<TableImpModel> getTableImpModelList() {
		return tableImpModelList;
	}
	public void setTableImpModelList(List<TableImpModel> tableImpModelList) {
		this.tableImpModelList = tableImpModelList;
	}
	public List<TableExpModel> getTableExpModelList() {
		return tableExpModelList;
	}
	public void setTableExpModelList(List<TableExpModel> tableExpModelList) {
		this.tableExpModelList = tableExpModelList;
	}
	public List<ExportModel> getExportModelList() {
		return exportModelList;
	}
	public void setExportModelList(List<ExportModel> exportModelList) {
		this.exportModelList = exportModelList;
	}
	public List<ImportModel> getImportModelList() {
		return importModelList;
	}
	public void setImportModelList(List<ImportModel> importModelList) {
		this.importModelList = importModelList;
	}
	public SapDataCollection getSapDataCollection() {
		return sapDataCollection;
	}
	public void setSapDataCollection(SapDataCollection sapDataCollection) {
		this.sapDataCollection = sapDataCollection;
	}
	
	
	
}
