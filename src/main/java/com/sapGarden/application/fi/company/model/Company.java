package com.sapGarden.application.fi.company.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.springframework.security.core.context.SecurityContextHolder;

import com.sapGarden.application.fi.company.model.bapi_companyCode_getDetail.Export_companyCode_detail;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getList.TableExp_companycode_list;
import com.sapGarden.system.org.model.User;

@Entity
@Table(name="company")
public class Company implements Serializable{

	private static final long serialVersionUID = 8961653795826720695L;

	@Id
//	@TableGenerator(name = "company_gen", //该表主键生成策略的名称,被@GeneratedValue.generator引用。
//	                table = "sys_tb_generator",       //表生成策略所持久化的表名。
//	                pkColumnName = "gen_name",    //在持久化的表中，该主键生成策略所对应键值的名称。
//	                valueColumnName = "gen_value", //在持久化的表中， 该主键当前所生成的值，它的值将会随着每次创建而加。
//	                pkColumnValue = "company_pk",//在持久化的表中，该生成策略所对应的主键
//	                initialValue = 100,             //默认主键值为50
//	                allocationSize = 1)           //每次主键值增加的大小
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "company_gen")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(length = 50)
	private String comp_code;
	@Column(length = 100)
	private String comp_name;
	@Column(length = 50)
	private String city;
	@Column(length = 100)
	private String country;
	@Column(length = 50)
	private String currency;
	@Column(length = 50)
	private String langu;
	@Column(length = 100)
	private String chrt_accts;
	@Column(length = 100)
	private String fy_variant;
	@Column(length = 100)
	private String vat_reg_no;
	@Column(length = 100)
	private String company;
	@Column(length = 100)
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
	public Company(Export_companyCode_detail detail,long sapclient){
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
		this.sapclient=sapclient;
	}
	/*
	[2.1]boolean型，计算(f ? 0 : 1); 
	[2.2]byte,char,short型，计算(int); 
	[2.3]long型，计算(int) (f ^ (f>>>32)); 
	[2.4]float型，计算Float.floatToIntBits(afloat); 
	[2.5]double型，计算Double.doubleToLongBits(adouble)得到一个long，再执行[2.3]; 
	*/
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;
		result = 37*result+(int) (id ^ (id>>>32));
		//result = 37*result+(name==null?0:name.hashCode());
		//result = 37*result+displayOrder;
		//result = 37*result+(this.url==null?0:url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(!(obj instanceof Company)){
			return false;
		}
		Company obj2 = (Company)obj;
		if(this.id>0){
			return this.id==obj2.getId();
		}else{
			return false;
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
