package com.sapGarden.system.org.model;

import java.util.List;

public class ResourceJson {

	private String total;
	private String page;
	private String records;
	private List<ResourceRowsJson> rows;
	public ResourceJson(List<ResourceRowsJson> list){
		this.total=list.size()+"";
		this.page="1";
		this.records=list.size()+"";
		this.rows=list;
	}
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRecords() {
		return records;
	}

	public void setRecords(String records) {
		this.records = records;
	}

	public List<ResourceRowsJson> getRows() {
		return rows;
	}
	public void setRows(List<ResourceRowsJson> rows) {
		this.rows = rows;
	}
	
}
