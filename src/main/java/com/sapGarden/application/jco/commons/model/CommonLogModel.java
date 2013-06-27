package com.sapGarden.application.jco.commons.model;


import java.util.Date;

import org.springframework.security.core.context.SecurityContextHolder;

import com.sapGarden.system.org.model.User;

public  class CommonLogModel {

	/*
	public static final String OPT_ADD = "新增";
	public static final String OPT_UPDATE = "修改";
	public static final String OPT_DELETE = "删除";
	public static final String OPT_CALLSAPERROR = "调用SAP异常";
	public static final String OPT_NOTSYNDATA = "没有更新数据";
	public static final String OPT_EXCEPTION = "程序异常";
	
	public static final String OPTFLAG_SUCCESS = "S";
	public static final String OPTFLAG_FAIL = "E";
	
	public static final String OPTTYPE_PROCESSERROR = "异常处理";
	public static final String OPTTYPE_SCHEDULING = "定时任务";
	public static final String OPTTYPE_COMPAREDATA = "同步校对结果";
	*/

	private Date opttime;
	private String optuser;
	private String opt;
	private long optid;
	private String optflag;
	private String opttype;
	private String optmsg;
	private long sapclient;
	public CommonLogModel(){
		if(SecurityContextHolder.getContext()!=null&&SecurityContextHolder.getContext().getAuthentication()!=null&&SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null){
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			this.sapclient=user.getCurrentSapDataCollection().getId();
		}
	}
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
	public long getSapclient() {
		return sapclient;
	}
	public void setSapclient(long sapclient) {
		this.sapclient = sapclient;
	}
	
}
