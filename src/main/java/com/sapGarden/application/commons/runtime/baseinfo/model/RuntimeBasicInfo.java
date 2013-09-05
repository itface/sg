package com.sapGarden.application.commons.runtime.baseinfo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
@Entity
@Table(name="BO_SYNCONFIG_RUNTIME_BASICINFO")
public class RuntimeBasicInfo implements Serializable{

	private static final long serialVersionUID = 8961653795826720614L;

	@Id
	@TableGenerator(name = "bo_synconfig_runtime_basicinfo_gen", //该表主键生成策略的名称,被@GeneratedValue.generator引用。
	                table = "sys_tb_generator",       //表生成策略所持久化的表名。
	                pkColumnName = "gen_name",    //在持久化的表中，该主键生成策略所对应键值的名称。
	                valueColumnName = "gen_value", //在持久化的表中， 该主键当前所生成的值，它的值将会随着每次创建而加。
	                pkColumnValue = "bo_synconfig_runtime_basicinfo_pk",//在持久化的表中，该生成策略所对应的主键
	                initialValue = 200,             //默认主键值为50
	                allocationSize = 1)           //每次主键值增加的大小
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "bo_synconfig_runtime_basicinfo_gen")
	private long id;
	private String status;
	private long sapclient;
	private String businesstype;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
