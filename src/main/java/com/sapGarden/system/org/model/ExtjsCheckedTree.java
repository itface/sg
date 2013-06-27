package com.sapGarden.system.org.model;

import java.util.List;

public class ExtjsCheckedTree{

	public long id;
	public String text;
	public String url;
	public boolean leaf;
	public List<ExtjsCheckedTree> children;
	private boolean checked=false;
	private String attributes;

	public ExtjsCheckedTree(){
		
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

	public List<ExtjsCheckedTree> getChildren() {
		return children;
	}

	public void setChildren(List<ExtjsCheckedTree> children) {
		this.children = children;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	
	
}
