package com.sapGarden.application.commons.basetable.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="sys_basetable")
public class BaseTableModel implements Serializable{
	
	private static final long serialVersionUID = 115L;
	@Id
	private long id;
	private String tablename;
	private String tablememo;
	private String columnname;
	private String columnmemo;
	private String columnrememo;
	private String columntype;
	private int columnlength;
	private String columnscale;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getTablememo() {
		return tablememo;
	}
	public void setTablememo(String tablememo) {
		this.tablememo = tablememo;
	}
	public String getColumnname() {
		return columnname;
	}
	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}
	public String getColumnmemo() {
		return columnmemo;
	}
	public void setColumnmemo(String columnmemo) {
		this.columnmemo = columnmemo;
	}
	public String getColumnrememo() {
		return columnrememo;
	}
	public void setColumnrememo(String columnrememo) {
		this.columnrememo = columnrememo;
	}
	public String getColumntype() {
		return columntype;
	}
	public void setColumntype(String columntype) {
		this.columntype = columntype;
	}
	public int getColumnlength() {
		return columnlength;
	}
	public void setColumnlength(int columnlength) {
		this.columnlength = columnlength;
	}
	public String getColumnscale() {
		return columnscale;
	}
	public void setColumnscale(String columnscale) {
		this.columnscale = columnscale;
	}
	
}
