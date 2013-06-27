package com.sapGarden.global.jqgrid.model;

public class Jqgrid_searchoptions {

	private  boolean searchhidden;
	private String[] sopt;

	public Jqgrid_searchoptions(){
		
	}
	public Jqgrid_searchoptions(String[] sopt){
		this.sopt = sopt;
		this.searchhidden=false;
	}
	public String[] getSopt() {
		return sopt;
	}

	public void setSopt(String[] sopt) {
		this.sopt = sopt;
	}
	public boolean isSearchhidden() {
		return searchhidden;
	}
	public void setSearchhidden(boolean searchhidden) {
		this.searchhidden = searchhidden;
	}
	
}
