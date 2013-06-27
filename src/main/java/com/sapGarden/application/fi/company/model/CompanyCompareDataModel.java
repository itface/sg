package com.sapGarden.application.fi.company.model;

import java.io.Serializable;

import com.sapGarden.application.fi.company.model.bapi_companyCode_getDetail.Export_companyCode_detail;
import com.sapGarden.application.jco.commons.model.CommonCompareDataModel;

public class CompanyCompareDataModel extends CommonCompareDataModel implements Serializable {
	private long dataid;
	private String comp_code_s,
	comp_code_g,
	comp_name_s,
	comp_name_g,
	city_s,
	city_g,
	country_s,
	country_g,
	currency_s,
	currency_g,
	langu_s,
	langu_g,
	chrt_accts_s,
	chrt_accts_g,
	fy_variant_s,
	fy_variant_g,
	vat_reg_no_s,
	vat_reg_no_g,
	company_s,
	company_g,
	addr_no_s,
	addr_no_g;

	public CompanyCompareDataModel(){
		
	}
	public CompanyCompareDataModel(String comp_code_s,
			String comp_code_g,
			String comp_name_s,
			String comp_name_g,
			String city_s,
			String city_g,
			String country_s,
			String country_g,
			String currency_s,
			String currency_g,
			String langu_s,
			String langu_g,
			String chrt_accts_s,
			String chrt_accts_g,
			String fy_variant_s,
			String fy_variant_g,
			String vat_reg_no_s,
			String vat_reg_no_g,
			String company_s,
			String company_g,
			String addr_no_s,
			String addr_no_g){
		this.comp_code_g = comp_code_g;
		this.comp_name_g = comp_name_g;
		this.city_g = city_g;
		this.country_g = country_g;
		this.currency_g = currency_g;
		this.langu_g = langu_g;
		this.chrt_accts_g = chrt_accts_g;
		this.fy_variant_g = fy_variant_g;
		this.vat_reg_no_g = vat_reg_no_g;
		this.company_g = company_g;
		this.addr_no_g = addr_no_g;
		this.comp_code_s = comp_code_s;
		this.comp_name_s = comp_name_s;
		this.city_s = city_s;
		this.country_s = country_s;
		this.currency_s = currency_s;
		this.langu_s = langu_s;
		this.chrt_accts_s = chrt_accts_s;
		this.fy_variant_s = fy_variant_s;
		this.vat_reg_no_s = vat_reg_no_s;
		this.company_s = company_s;
		this.addr_no_s = addr_no_s;
	}
	public CompanyCompareDataModel(Company company,Export_companyCode_detail sapCompany){
		if(company!=null){
			this.dataid=company.getId();
			this.comp_code_g = company.getComp_code();
			this.comp_name_g = company.getComp_name();
			this.city_g = company.getCity();
			this.country_g = company.getCountry();
			this.currency_g = company.getCurrency();
			this.langu_g = company.getLangu();
			this.chrt_accts_g = company.getChrt_accts();
			this.fy_variant_g = company.getFy_variant();
			this.vat_reg_no_g = company.getVat_reg_no();
			this.company_g = company.getCompany();
			this.addr_no_g = company.getAddr_no();
		}
		if(sapCompany!=null){
			this.comp_code_s = sapCompany.getComp_code();
			this.comp_name_s = sapCompany.getComp_name();
			this.city_s = sapCompany.getCity();
			this.country_s = sapCompany.getCountry();
			this.currency_s = sapCompany.getCurrency();
			this.langu_s = sapCompany.getLangu();
			this.chrt_accts_s = sapCompany.getChrt_accts();
			this.fy_variant_s = sapCompany.getFy_variant();
			this.vat_reg_no_s = sapCompany.getVat_reg_no();
			this.company_s = sapCompany.getCompany();
			this.addr_no_s = sapCompany.getAddr_no();
		}
	}
	public String getComp_code_s() {
		return comp_code_s;
	}

	public void setComp_code_s(String comp_code_s) {
		this.comp_code_s = comp_code_s;
	}

	public String getComp_code_g() {
		return comp_code_g;
	}

	public void setComp_code_g(String comp_code_g) {
		this.comp_code_g = comp_code_g;
	}

	public String getComp_name_s() {
		return comp_name_s;
	}

	public void setComp_name_s(String comp_name_s) {
		this.comp_name_s = comp_name_s;
	}

	public String getComp_name_g() {
		return comp_name_g;
	}

	public void setComp_name_g(String comp_name_g) {
		this.comp_name_g = comp_name_g;
	}

	public String getCity_s() {
		return city_s;
	}

	public void setCity_s(String city_s) {
		this.city_s = city_s;
	}

	public String getCity_g() {
		return city_g;
	}

	public void setCity_g(String city_g) {
		this.city_g = city_g;
	}

	public String getCountry_s() {
		return country_s;
	}

	public void setCountry_s(String country_s) {
		this.country_s = country_s;
	}

	public String getCountry_g() {
		return country_g;
	}

	public void setCountry_g(String country_g) {
		this.country_g = country_g;
	}

	public String getCurrency_s() {
		return currency_s;
	}

	public void setCurrency_s(String currency_s) {
		this.currency_s = currency_s;
	}

	public String getCurrency_g() {
		return currency_g;
	}

	public void setCurrency_g(String currency_g) {
		this.currency_g = currency_g;
	}

	public String getLangu_s() {
		return langu_s;
	}

	public void setLangu_s(String langu_s) {
		this.langu_s = langu_s;
	}

	public String getLangu_g() {
		return langu_g;
	}

	public void setLangu_g(String langu_g) {
		this.langu_g = langu_g;
	}

	public String getChrt_accts_s() {
		return chrt_accts_s;
	}

	public void setChrt_accts_s(String chrt_accts_s) {
		this.chrt_accts_s = chrt_accts_s;
	}

	public String getChrt_accts_g() {
		return chrt_accts_g;
	}

	public void setChrt_accts_g(String chrt_accts_g) {
		this.chrt_accts_g = chrt_accts_g;
	}

	public String getFy_variant_s() {
		return fy_variant_s;
	}

	public void setFy_variant_s(String fy_variant_s) {
		this.fy_variant_s = fy_variant_s;
	}

	public String getFy_variant_g() {
		return fy_variant_g;
	}

	public void setFy_variant_g(String fy_variant_g) {
		this.fy_variant_g = fy_variant_g;
	}

	public String getVat_reg_no_s() {
		return vat_reg_no_s;
	}

	public void setVat_reg_no_s(String vat_reg_no_s) {
		this.vat_reg_no_s = vat_reg_no_s;
	}

	public String getVat_reg_no_g() {
		return vat_reg_no_g;
	}

	public void setVat_reg_no_g(String vat_reg_no_g) {
		this.vat_reg_no_g = vat_reg_no_g;
	}

	public String getCompany_s() {
		return company_s;
	}

	public void setCompany_s(String company_s) {
		this.company_s = company_s;
	}

	public String getCompany_g() {
		return company_g;
	}

	public void setCompany_g(String company_g) {
		this.company_g = company_g;
	}

	public String getAddr_no_s() {
		return addr_no_s;
	}

	public void setAddr_no_s(String addr_no_s) {
		this.addr_no_s = addr_no_s;
	}

	public String getAddr_no_g() {
		return addr_no_g;
	}

	public void setAddr_no_g(String addr_no_g) {
		this.addr_no_g = addr_no_g;
	}
	public long getDataid() {
		return dataid;
	}
	public void setDataid(long dataid) {
		this.dataid = dataid;
	}
	
	
}
