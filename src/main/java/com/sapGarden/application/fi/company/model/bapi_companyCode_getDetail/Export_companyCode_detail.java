package com.sapGarden.application.fi.company.model.bapi_companyCode_getDetail;

import java.io.Serializable;

import com.sapGarden.application.jco.commons.model.CommonExportModel;

public class Export_companyCode_detail implements Serializable,CommonExportModel{

	private static final long serialVersionUID = 8961653795826721695L;

	private String comp_code;
	private String comp_name;
	private String city;
	private String country;
	private String currency;
	private String langu;
	private String chrt_accts;
	private String fy_variant;
	private String company;
	private String addr_no;
	private String vat_reg_no;
	public String getComp_code() {
		return comp_code;
	}
	public void setComp_code(String comp_code) {
		this.comp_code = comp_code;
	}
	public String getComp_name() {
		return comp_name;
	}
	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getLangu() {
		return langu;
	}
	public void setLangu(String langu) {
		this.langu = langu;
	}
	public String getChrt_accts() {
		return chrt_accts;
	}
	public void setChrt_accts(String chrt_accts) {
		this.chrt_accts = chrt_accts;
	}
	public String getFy_variant() {
		return fy_variant;
	}
	public void setFy_variant(String fy_variant) {
		this.fy_variant = fy_variant;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAddr_no() {
		return addr_no;
	}
	public void setAddr_no(String addr_no) {
		this.addr_no = addr_no;
	}
	public String getVat_reg_no() {
		return vat_reg_no;
	}
	public void setVat_reg_no(String vat_reg_no) {
		this.vat_reg_no = vat_reg_no;
	}
	
}
