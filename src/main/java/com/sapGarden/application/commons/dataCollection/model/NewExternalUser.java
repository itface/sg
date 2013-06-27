package com.sapGarden.application.commons.dataCollection.model;

import java.io.Serializable;

public class NewExternalUser implements Serializable{


	private String id;
	private String externalsystemname;
	private String externalsystemurl;
	private String username;
	private String sapdatacollection;
	private String descriptioninfo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
