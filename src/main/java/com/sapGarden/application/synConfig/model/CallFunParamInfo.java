package com.sapGarden.application.synConfig.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="BO_SYNCONFIG_CALLFUN_PARAMINFO")
public class CallFunParamInfo implements Serializable{

	private static final long serialVersionUID = 8961653795826720615L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_callfun_paramconfig")
	@SequenceGenerator(name="seq_callfun_paramconfig",sequenceName="seq_callfun_paramconfig",allocationSize=1)
	private long id;
	private String paramname;
	private String paramvalue;
	private String businesstype;
	public CallFunParamInfo(){
		
	}
	public CallFunParamInfo(JqgridNewCallFunParamInfo jqgridNewCallFunParamInfo){
		if(jqgridNewCallFunParamInfo!=null){			
			String newId = jqgridNewCallFunParamInfo.getId();
			//如果newId不为空并且不为_empty，则说明是更新，要设置id，如果不设置id，则为更新
			if(newId!=null&&!"".equals(newId)&&!"_empty".equals(newId)){
				this.id=Long.parseLong(newId);
			}
			this.paramname=jqgridNewCallFunParamInfo.getParamname();
			this.paramvalue=jqgridNewCallFunParamInfo.getParamvalue();
			this.businesstype=jqgridNewCallFunParamInfo.getBusinesstype();
		}
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getParamname() {
		return paramname;
	}
	public void setParamname(String paramname) {
		this.paramname = paramname;
	}
	public String getParamvalue() {
		return paramvalue;
	}
	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}
	public String getBusinesstype() {
		return businesstype;
	}
	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}
	
}
