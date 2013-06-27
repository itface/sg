package com.sapGarden.application.commons.dataCollection.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="sys_externalUser")
public class ExternalUser implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_sys_externalUser")
	@SequenceGenerator(name="seq_sys_externalUser",sequenceName="seq_sys_externalUser",allocationSize=1)
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
