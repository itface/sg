package com.sapGarden.global.jqgrid.model;

public class Jqgrid_searchRulesModel {

	public static final String OPTION_EQ = "eq";
	public static final String OPTION_NE = "ne";
	public static final String OPTION_LT = "lt";
	public static final String OPTION_LE = "le";
	public static final String OPTION_GT = "gt";
	public static final String OPTION_GE = "ge";
	public static final String OPTION_NU = "and";
	public static final String OPTION_NN = "nn";	
	public static final String OPTION_NI = "ni";
	public static final String OPTION_IN = "in";
	private String field;
	private String op;
	private String data;
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
