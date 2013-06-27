package com.sapGarden.global.jqgrid.model;

import java.io.Serializable;

public class Jqgrid_columnModel implements Serializable{

	private static final long serialVersionUID = 8961653795826721645L;

	private String name;
	private String index;
	private int width;
	private boolean hidden;
	private boolean sortable;
	private boolean editable;
	private boolean search;
	private String stype;
	private Jqgrid_searchoptions searchoptions;
	private String edittype;
	private Jqgrid_editRules  editRules;
	private Jqgrid_formoptions formoptions;
	public Jqgrid_columnModel(String name,String index,int width,boolean sortable,boolean hidden,boolean editable,String edittype,boolean search,String stype,Jqgrid_searchoptions searchoptions,Jqgrid_editRules  editRules,Jqgrid_formoptions formoptions){
		this.name=name;
		this.index=index;
		this.width=width;
		this.hidden=hidden;
		this.editable=editable;
		this.edittype=edittype;
		this.editRules=editRules;
		this.formoptions=formoptions;
		this.stype=stype;
		this.search=search;
		this.searchoptions=searchoptions;
		this.sortable=false;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public String getEdittype() {
		return edittype;
	}
	public void setEdittype(String edittype) {
		this.edittype = edittype;
	}
	public Jqgrid_editRules getEditRules() {
		return editRules;
	}
	public void setEditRules(Jqgrid_editRules editRules) {
		this.editRules = editRules;
	}
	public Jqgrid_formoptions getFormoptions() {
		return formoptions;
	}
	public void setFormoptions(Jqgrid_formoptions formoptions) {
		this.formoptions = formoptions;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
	public boolean isSearch() {
		return search;
	}
	public void setSearch(boolean search) {
		this.search = search;
	}
	public Jqgrid_searchoptions getSearchoptions() {
		return searchoptions;
	}
	public void setSearchoptions(Jqgrid_searchoptions searchoptions) {
		this.searchoptions = searchoptions;
	}
	public boolean isSortable() {
		return sortable;
	}
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
	
}
