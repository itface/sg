package com.sapGarden.system.org.model;

public class Menu {

	private String ID;
	private String id;
	private String url;
	private String resourceName;
	private String resourceIndex;
	private String parent;
	private String oper;
	private int issystem;
	private int isshow;
	private String alias;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	public String getResourceIndex() {
		return resourceIndex;
	}
	public void setResourceIndex(String resourceIndex) {
		this.resourceIndex = resourceIndex;
	}
	public int getIssystem() {
		return issystem;
	}
	public void setIssystem(int issystem) {
		this.issystem = issystem;
	}
	public int getIsshow() {
		return isshow;
	}
	public void setIsshow(int isshow) {
		this.isshow = isshow;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	
}
