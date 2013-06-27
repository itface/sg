package com.sapGarden.system.org.model;

import com.sapGarden.application.commons.dao.ExtendDao;

public class ResourceRowsJson {

	private String ID;
	private String id;
	private String url;
	private String resourceName;
	private String parentId;
	private String resourceIndex;
	private String issystem;
	private String isshow;
	private String alias;
	private String parent;
	private String level;
	private String expanded;
	private String isLeaf;
	
	public ResourceRowsJson(){
		
	}
	public ResourceRowsJson(Resource resource,ExtendDao extendDao){
		this.ID=resource.getId()+"";
		this.id=resource.getId()+"";
		this.url=resource.getUrl();
		this.resourceName=resource.getResourceName();
		this.parentId=resource.getParentId()+"";
		this.resourceIndex=resource.getResourceIndex()+"";
		this.issystem=resource.getIssystem()+"";
		this.isshow=resource.getIsshow()+"";
		this.alias=resource.getAlias();
		if(resource.getParentId()==0){
			parent="\"null\"";
		}else{
			parent=resource.getParentId()+"";
		}
		this.expanded="true";
		this.isLeaf=this.ifLeaf(resource.getId(),extendDao)+"";
		this.level=this.getLevel(resource.getParentId(), 0,extendDao)+"";
		
	}
	public int getLevel(long parent,int count,ExtendDao extendDao){
		Resource resource = (Resource)extendDao.find(Resource.class, parent);
		if(resource!=null){
			count++;
			count=this.getLevel(resource.getParentId(),count,extendDao);
		}
		return count;
	}
	public boolean ifLeaf(long id,ExtendDao extendDao){
		long nums = extendDao.findTotalCount("select count(r.id) from Resource r where r.parentId=?", new Object[]{id});
		if(nums>0){
			return false;
		}else{
			return true;
		}
		/*
		Resource resource = resourceDao.findOneByParent(id);
		if(resource==null){
			return true;
		}else{
			return false;
		}*/
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getExpanded() {
		return expanded;
	}
	public void setExpanded(String expanded) {
		this.expanded = expanded;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getResourceIndex() {
		return resourceIndex;
	}
	public void setResourceIndex(String resourceIndex) {
		this.resourceIndex = resourceIndex;
	}
	public String getIssystem() {
		return issystem;
	}
	public void setIssystem(String issystem) {
		this.issystem = issystem;
	}
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	

	
	
}
