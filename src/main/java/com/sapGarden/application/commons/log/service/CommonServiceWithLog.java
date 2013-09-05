package com.sapGarden.application.commons.log.service;

import com.sapGarden.application.commons.log.model.CommonLogModel;

/**
 * 带日志的server
 * @author Administrator
 *
 */
public interface CommonServiceWithLog<T> {
	public static final String OPT_ADD = "新增";
	public static final String OPT_UPDATE = "修改";
	public static final String OPT_DELETE = "删除";
	public static final String OPT_CALLSAPERROR = "调用SAP异常";
	public static final String OPT_NOTSYNDATA = "没有更新数据";
	public static final String OPT_EXCEPTION = "程序异常";
	
	public static final String OPTFLAG_SUCCESS = "S";
	public static final String OPTFLAG_FAIL = "E";
	
	public static final String OPTTYPE_INIT = "初始化";
	public static final String OPTTYPE_MANUALSYN = "手工同步";
	public static final String OPTTYPE_PROCESSERROR = "异常处理";
	public static final String OPTTYPE_SCHEDULING = "定时任务";
	public static final String OPTTYPE_COMPAREDATA = "同步校对结果";
	
	public static final boolean IFLOG_YES = true;
	public static final boolean IFLOG_NO = false;
	
	
	public void addWithLog(String opttype,long sapclient,String user,Class<T> modelClass,Class<? extends CommonLogModel> logModelClass,T t);
	public void updateWithLog(String opttype,long sapclient,String user,Class<T> modelClass,Class<? extends CommonLogModel> logModelClass,T t);
	public void deleteWithLog(String opttype,long sapclient,String user,Class<T> modelClass,Class<? extends CommonLogModel> logModelClass,T t);
}
