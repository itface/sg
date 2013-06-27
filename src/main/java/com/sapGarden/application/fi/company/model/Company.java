package com.sapGarden.application.fi.company.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.context.SecurityContextHolder;

import com.sapGarden.application.fi.company.model.bapi_companyCode_getDetail.Export_companyCode_detail;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getList.TableExp_companycode_list;
import com.sapGarden.system.org.model.User;

@Entity
@Table(name="bo_fin_company")
public class Company implements Serializable{

	private static final long serialVersionUID = 8961653795826720695L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_fin_company")
	@SequenceGenerator(name="seq_fin_company",sequenceName="seq_fin_company",allocationSize=1)
	private long id;
	private String comp_code;
	private String comp_name;
	private String city;
	private String country;
	private String currency;
	private String langu;
	private String chrt_accts;
	private String fy_variant;
	private String vat_reg_no;
	private String company;
	private String addr_no;
	private long sapclient;
	public Company(){
		if(SecurityContextHolder.getContext()!=null&&SecurityContextHolder.getContext().getAuthentication()!=null&&SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null){
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			this.sapclient=user.getCurrentSapDataCollection().getId();
		}
	}
	public Company(TableExp_companycode_list tableExp){
		if(tableExp!=null){
			this.comp_code = tableExp.getComp_code();
			this.comp_name = tableExp.getComp_name();
		}
		if(SecurityContextHolder.getContext()!=null&&SecurityContextHolder.getContext().getAuthentication()!=null&&SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null){
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			this.sapclient=user.getCurrentSapDataCollection().getId();
		}
	}
	public Company(String comp_code, String comp_name,String city,String country,String currency,String langu,String chrt_accts,String fy_variant,String vat_reg_no,String company,String addr_no,long sapclient){
		this.comp_code = comp_code;
		this.comp_name = comp_name;
		this.city = city;
		this.country=country;
		this.currency=currency;
		this.langu=langu;
		this.chrt_accts=chrt_accts;
		this.fy_variant=fy_variant;
		this.vat_reg_no=vat_reg_no;
		this.company=company;
		this.addr_no=addr_no;
		this.sapclient=sapclient;
		/*
		if(SecurityContextHolder.getContext()!=null&&SecurityContextHolder.getContext().getAuthentication()!=null&&SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null){
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			this.sapclient=user.getCurrentSapClientModel().getId();
		}*/
	}
	public Company(Export_companyCode_detail detail){
		if(detail!=null){
			this.comp_code = detail.getComp_code();
			this.comp_name = detail.getComp_name();
			this.city = detail.getCity();
			this.country=detail.getCountry();
			this.currency=detail.getCurrency();
			this.langu=detail.getLangu();
			this.chrt_accts=detail.getChrt_accts();
			this.fy_variant=detail.getFy_variant();
			this.vat_reg_no=detail.getVat_reg_no();
			this.company=detail.getCompany();
			this.addr_no=detail.getAddr_no();
		}
		if(SecurityContextHolder.getContext()!=null&&SecurityContextHolder.getContext().getAuthentication()!=null&&SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null){
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			this.sapclient=user.getCurrentSapDataCollection().getId();
		}
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public String getVat_reg_no() {
		return vat_reg_no;
	}
	public void setVat_reg_no(String vat_reg_no) {
		this.vat_reg_no = vat_reg_no;
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
	public long getSapclient() {
		return sapclient;
	}
	public void setSapclient(long sapclient) {
		this.sapclient = sapclient;
	}
	
}
