package com.sapGarden.global.jqgrid.model;

import java.io.Serializable;
import java.util.List;

import net.sf.json.JSONObject;

public class Jqgrid_ConfigModel implements Serializable{

	private static final long serialVersionUID = 8961653795826721655L;
	private int width;
	private int height;
	private String id;
	private String caption;
	private String baseUrl;
	//private boolean sortname;
	//private boolean sortorder;
	private String pager;
	private boolean multiselect;
	private boolean editable;
	private boolean searchable=true;
	private String[] columnNames;
	private List<Jqgrid_columnModel> columnModel;
	public Jqgrid_ConfigModel(String pager,String id,int height,int width,String caption,String baseUrl,boolean multiselect,boolean searchable,boolean editable,String[] columnNames,List<Jqgrid_columnModel> columnModel){
		this.id=id;
		this.width=width;
		this.height=height;
		this.caption=caption;
		this.baseUrl=baseUrl;
		this.columnNames=columnNames;
		this.columnModel=columnModel;
		this.editable=editable;
		this.searchable=searchable;
		this.multiselect=multiselect;
		this.pager=pager;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public String[] getColumnNames() {
		return columnNames;
	}
	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}
	public List<Jqgrid_columnModel> getColumnModel() {
		return columnModel;
	}
	public void setColumnModel(List<Jqgrid_columnModel> columnModel) {
		this.columnModel = columnModel;
	}
	public String toJsonString(){
		JSONObject jsonObject = JSONObject.fromObject(this);
		//String jsonString = jsonObject.toString().replaceAll("null", "\"\"");
		return jsonObject.toString();
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public boolean isSearchable() {
		return searchable;
	}
	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public boolean isMultiselect() {
		return multiselect;
	}
	public void setMultiselect(boolean multiselect) {
		this.multiselect = multiselect;
	}
	public void setPager(String pager) {
		this.pager = pager;
	}
	public String getPager() {
		return pager;
	}
	
}
