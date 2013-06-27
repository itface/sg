package com.sapGarden.global.model;

import java.io.Serializable;


public class TableStructure implements Serializable{

	private String table_name;
	private String column_name;
	private int data_length;
	private String data_precision;
	private String data_scale;
	private String data_type;
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public int getData_length() {
		return data_length;
	}
	public void setData_length(int data_length) {
		this.data_length = data_length;
	}
	public String getData_precision() {
		return data_precision;
	}
	public void setData_precision(String data_precision) {
		this.data_precision = data_precision;
	}
	public String getData_scale() {
		return data_scale;
	}
	public void setData_scale(String data_scale) {
		this.data_scale = data_scale;
	}
	
}
