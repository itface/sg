package com.sapGarden.global.jqgrid.model;

import java.io.Serializable;

public class Jqgrid_formoptions implements Serializable{

	private static final long serialVersionUID = 8961653795826721685L;

	private String elmprefix;
	private String elmsuffix;
	public Jqgrid_formoptions(String elmprefix,String elmsuffix){
		this.elmprefix=elmprefix;
		this.elmsuffix=elmsuffix;
	}
	public String getElmprefix() {
		return elmprefix;
	}
	public void setElmprefix(String elmprefix) {
		this.elmprefix = elmprefix;
	}
	public String getElmsuffix() {
		return elmsuffix;
	}
	public void setElmsuffix(String elmsuffix) {
		this.elmsuffix = elmsuffix;
	}
	
}
