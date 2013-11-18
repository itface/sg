package com.sapGarden.application.fi.company.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.sapGarden.application.commons.log.model.CommonLogModel;
@Entity
@Table(name="company_log")
public class CompanyLog extends CommonLogModel implements Serializable{

	private static final long serialVersionUID = 8961653795826721700L;
	

	@Id
//	@TableGenerator(name = "companylog_gen", //该表主键生成策略的名称,被@GeneratedValue.generator引用。
//	                table = "sys_tb_generator",       //表生成策略所持久化的表名。
//	                pkColumnName = "gen_name",    //在持久化的表中，该主键生成策略所对应键值的名称。
//	                valueColumnName = "gen_value", //在持久化的表中， 该主键当前所生成的值，它的值将会随着每次创建而加。
//	                pkColumnValue = "companylog_pk",//在持久化的表中，该生成策略所对应的主键
//	                initialValue = 100,             //默认主键值为50
//	                allocationSize = 1)           //每次主键值增加的大小
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "companylog_gen")
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	private String comp_code_old;
	private String comp_name_old;
	private String city_old;
	private String country_old;
	private String currency_old;
	private String langu_old;
	private String chrt_accts_old;
	private String fy_variant_old;
	private String vat_reg_no_old;
	private String company_old;
	private String addr_no_old;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date opttime;
	private String optuser;
	private String opt;
	private long optid;
	private String optflag;
	private String opttype;
	@Length(max=1000)
	private String optmsg;
	private String processstatus;
	private long sapclient;
	
	public CompanyLog(){
		
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
		if(!(obj instanceof CompanyLog)){
			return false;
		}
		CompanyLog obj2 = (CompanyLog)obj;
		if(this.id>0){
			return this.id==obj2.getId();
		}else{
			return false;
		}
	}
	public CompanyLog(CompanyCompareDataModel compareDataModel,long sapclient){
		//CompanyCompareDataModel compareDataModel = (CompanyCompareDataModel)commonCompareDataModel;
		if(compareDataModel!=null){
			this.comp_code=compareDataModel.getComp_code_s();
			this.comp_code = compareDataModel.getComp_code_s();
			this.comp_name = compareDataModel.getComp_name_s();
			this.city = compareDataModel.getCity_s();
			this.country=compareDataModel.getCountry_s();
			this.currency=compareDataModel.getCurrency_s();
			this.langu=compareDataModel.getLangu_s();
			this.chrt_accts=compareDataModel.getChrt_accts_s();
			this.fy_variant=compareDataModel.getFy_variant_s();
			this.vat_reg_no=compareDataModel.getVat_reg_no_s();
			this.company=compareDataModel.getCompany_s();
			this.addr_no=compareDataModel.getAddr_no_s();		
			this.comp_code_old=compareDataModel.getComp_code_g();
			this.comp_code_old = compareDataModel.getComp_code_g();
			this.comp_name_old = compareDataModel.getComp_name_g();
			this.city_old = compareDataModel.getCity_g();
			this.country_old=compareDataModel.getCountry_g();
			this.currency_old=compareDataModel.getCurrency_g();
			this.langu_old=compareDataModel.getLangu_g();
			this.chrt_accts_old=compareDataModel.getChrt_accts_g();
			this.fy_variant_old=compareDataModel.getFy_variant_g();
			this.vat_reg_no_old=compareDataModel.getVat_reg_no_g();
			this.company_old=compareDataModel.getCompany_g();
			this.addr_no_old=compareDataModel.getAddr_no_g();
			this.optid=compareDataModel.getDataid();
		}
		this.sapclient=sapclient;
	}
	public CompanyLog(Company company,Company oldCompany,long sapclient){
		this.opttime = new Date();
		if(company!=null){
			this.comp_code=company.getComp_code();
			this.comp_code = company.getComp_code();
			this.comp_name = company.getComp_name();
			this.city = company.getCity();
			this.country=company.getCountry();
			this.currency=company.getCurrency();
			this.langu=company.getLangu();
			this.chrt_accts=company.getChrt_accts();
			this.fy_variant=company.getFy_variant();
			this.vat_reg_no=company.getVat_reg_no();
			this.company=company.getCompany();
			this.addr_no=company.getAddr_no();
			this.optid=company.getId();
		}
		if(oldCompany!=null){
			this.comp_code_old = oldCompany.getComp_code();
			this.comp_code_old = oldCompany.getComp_code();
			this.comp_name_old = oldCompany.getComp_name();
			this.city_old = oldCompany.getCity();
			this.country_old=oldCompany.getCountry();
			this.currency_old=oldCompany.getCurrency();
			this.langu_old=oldCompany.getLangu();
			this.chrt_accts_old=oldCompany.getChrt_accts();
			this.fy_variant_old=oldCompany.getFy_variant();
			this.vat_reg_no_old=oldCompany.getVat_reg_no();
			this.company_old=oldCompany.getCompany();
			this.addr_no_old=oldCompany.getAddr_no();
			this.optid=oldCompany.getId();
		}
		this.sapclient=sapclient;
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
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOpttime() {
		return opttime;
	}
	public void setOpttime(Date opttime) {
		this.opttime = opttime;
	}
	public String getOptuser() {
		return optuser;
	}
	public void setOptuser(String optuser) {
		this.optuser = optuser;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public long getOptid() {
		return optid;
	}
	public void setOptid(long optid) {
		this.optid = optid;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getOptflag() {
		return optflag;
	}
	public void setOptflag(String optflag) {
		this.optflag = optflag;
	}
	public String getOpttype() {
		return opttype;
	}
	public void setOpttype(String opttype) {
		this.opttype = opttype;
	}
	public String getOptmsg() {
		return optmsg;
	}
	public void setOptmsg(String optmsg) {
		this.optmsg = optmsg;
	}
	public String getProcessstatus() {
		return processstatus;
	}
	public void setProcessstatus(String processstatus) {
		this.processstatus = processstatus;
	}
	public String getComp_code_old() {
		return comp_code_old;
	}
	public void setComp_code_old(String comp_code_old) {
		this.comp_code_old = comp_code_old;
	}
	public String getComp_name_old() {
		return comp_name_old;
	}
	public void setComp_name_old(String comp_name_old) {
		this.comp_name_old = comp_name_old;
	}
	public String getCity_old() {
		return city_old;
	}
	public void setCity_old(String city_old) {
		this.city_old = city_old;
	}
	public String getCountry_old() {
		return country_old;
	}
	public void setCountry_old(String country_old) {
		this.country_old = country_old;
	}
	public String getCurrency_old() {
		return currency_old;
	}
	public void setCurrency_old(String currency_old) {
		this.currency_old = currency_old;
	}
	public String getLangu_old() {
		return langu_old;
	}
	public void setLangu_old(String langu_old) {
		this.langu_old = langu_old;
	}
	public String getChrt_accts_old() {
		return chrt_accts_old;
	}
	public void setChrt_accts_old(String chrt_accts_old) {
		this.chrt_accts_old = chrt_accts_old;
	}
	public String getFy_variant_old() {
		return fy_variant_old;
	}
	public void setFy_variant_old(String fy_variant_old) {
		this.fy_variant_old = fy_variant_old;
	}
	public String getVat_reg_no_old() {
		return vat_reg_no_old;
	}
	public void setVat_reg_no_old(String vat_reg_no_old) {
		this.vat_reg_no_old = vat_reg_no_old;
	}
	public String getCompany_old() {
		return company_old;
	}
	public void setCompany_old(String company_old) {
		this.company_old = company_old;
	}
	public String getAddr_no_old() {
		return addr_no_old;
	}
	public void setAddr_no_old(String addr_no_old) {
		this.addr_no_old = addr_no_old;
	}
	public long getSapclient() {
		return sapclient;
	}
	public void setSapclient(long sapclient) {
		this.sapclient = sapclient;
	}
	
}
