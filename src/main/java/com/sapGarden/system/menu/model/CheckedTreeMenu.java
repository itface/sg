package com.sapGarden.system.menu.model;

import java.util.List;
/**
 * easyui tree 节点格式
 * @author Administrator
 *
 */
public class CheckedTreeMenu{

	public long id;
	public String text;
	public String url;
	public boolean leaf;
	public String attributes;
	public List<CheckedTreeMenu> children;
	private boolean checked=false;

	public CheckedTreeMenu(){
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	
	public List<CheckedTreeMenu> getChildren() {
		return children;
	}
	public void setChildren(List<CheckedTreeMenu> children) {
		this.children = children;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getAttributes() {
		return this.url;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	
}
