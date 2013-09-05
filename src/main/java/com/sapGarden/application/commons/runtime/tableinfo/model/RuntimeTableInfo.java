package com.sapGarden.application.commons.runtime.tableinfo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="bo_synconfig_runtime_dbinfo")
public class RuntimeTableInfo implements Serializable{

	private static final long serialVersionUID = 8961653795826720612L;

	@Id
	@TableGenerator(name = "bo_synconfig_runtime_dbinfo_gen", //该表主键生成策略的名称,被@GeneratedValue.generator引用。
	                table = "sys_tb_generator",       //表生成策略所持久化的表名。
	                pkColumnName = "gen_name",    //在持久化的表中，该主键生成策略所对应键值的名称。
	                valueColumnName = "gen_value", //在持久化的表中， 该主键当前所生成的值，它的值将会随着每次创建而加。
	                pkColumnValue = "bo_synconfig_runtime_dbinfo_pk",//在持久化的表中，该生成策略所对应的主键
	                initialValue = 100,             //默认主键值为50
	                allocationSize = 1)           //每次主键值增加的大小
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "bo_synconfig_runtime_dbinfo_gen")
	private long id;
	private String reflectTable;
	private String reflectTableMemo;
	private String businesstype;
	private long sapclient;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getReflectTable() {
		return reflectTable;
	}
	public void setReflectTable(String reflectTable) {
		this.reflectTable = reflectTable;
	}
	public String getReflectTableMemo() {
		return reflectTableMemo;
	}
	public void setReflectTableMemo(String reflectTableMemo) {
		this.reflectTableMemo = reflectTableMemo;
	}
	public String getBusinesstype() {
		return businesstype;
	}
	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}
	public long getSapclient() {
		return sapclient;
	}
	public void setSapclient(long sapclient) {
		this.sapclient = sapclient;
	}
	
	
}
