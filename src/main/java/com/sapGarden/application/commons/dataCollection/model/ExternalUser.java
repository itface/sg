package com.sapGarden.application.commons.dataCollection.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
@Entity
@Table(name="sys_externalUser")
public class ExternalUser implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -8839173417687023310L;
	@Id
	@TableGenerator(name = "externalUser_gen", //该表主键生成策略的名称,被@GeneratedValue.generator引用。
	                table = "sys_tb_generator",       //表生成策略所持久化的表名。
	                pkColumnName = "gen_name",    //在持久化的表中，该主键生成策略所对应键值的名称。
	                valueColumnName = "gen_value", //在持久化的表中， 该主键当前所生成的值，它的值将会随着每次创建而加。
	                pkColumnValue = "sys_externalUser_pk",//在持久化的表中，该生成策略所对应的主键
	                initialValue = 100,             //默认主键值为50
	                allocationSize = 1)           //每次主键值增加的大小
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "externalUser_gen")
	private long id;
	private String externalsystemname;
	private String externalsystemurl;
	private String username;
	private String sapdatacollection;
	private String descriptioninfo;
	public ExternalUser(){
		
	}
	public ExternalUser(NewExternalUser newExternalUser){
		if(newExternalUser!=null){
			String newId = newExternalUser.getId();
			if(newId!=null&&!"".equals(newId)&&!"_empty".equals(newId)){
				this.id=Long.parseLong(newId);
			}
			this.externalsystemname=newExternalUser.getExternalsystemname();
			this.externalsystemurl=newExternalUser.getExternalsystemurl();
			this.username=newExternalUser.getUsername();
			this.sapdatacollection=newExternalUser.getSapdatacollection();
			this.descriptioninfo=newExternalUser.getDescriptioninfo();
		}
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getExternalsystemurl() {
		return externalsystemurl;
	}
	public void setExternalsystemurl(String externalsystemurl) {
		this.externalsystemurl = externalsystemurl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSapdatacollection() {
		return sapdatacollection;
	}
	public void setSapdatacollection(String sapdatacollection) {
		this.sapdatacollection = sapdatacollection;
	}
	public String getDescriptioninfo() {
		return descriptioninfo;
	}
	public void setDescriptioninfo(String descriptioninfo) {
		this.descriptioninfo = descriptioninfo;
	}
	public String getExternalsystemname() {
		return externalsystemname;
	}
	public void setExternalsystemname(String externalsystemname) {
		this.externalsystemname = externalsystemname;
	}
	

}
