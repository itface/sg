package com.sapGarden.application.commons.log.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.log.model.CommonLogModel;

/**
 * 带日志的server
 * @author Administrator
 *
 */
public interface CommonService<T> {
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
	
	public long findTotalNum(SapDataCollection sapDataCollection,String className,JSONObject json);
	public List<T> findByPage(SapDataCollection sapDataCollection,String className,JSONObject json,int rows,int page,String sidx,String sord);
	public T findSingleByCondition(SapDataCollection sapDataCollection,String className,JSONObject json);
	public List<T> findAllByCondition(SapDataCollection sapDataCollection,String className,JSONObject json);
	public JSONObject findDataOfJqgridByPage(SapDataCollection sapDataCollection,String className,JSONObject json,int rows,int page,String sidx,String sord);
	public void addList(List<T> t);
	public void add(T t);
	public void update(T t);
	public void removeAll(SapDataCollection sapDataCollection,String className);
	public void removeById(long id,Class<T> clazz);
	public void addListWithLog(String opttype, long sapclient, String user,Class<T> modelClass, Class<? extends CommonLogModel> logModelClass,List<T> t);
	public void addWithLog(String opttype,long sapclient,String user,Class<T> modelClass,Class<? extends CommonLogModel> logModelClass,T t);
	public void updateWithLog(String opttype,long sapclient,String user,Class<T> modelClass,Class<? extends CommonLogModel> logModelClass,T t,T oldt);
	public void deleteWithLog(String opttype,long sapclient,String user,Class<T> modelClass,Class<? extends CommonLogModel> logModelClass,T t);

	public void executeUpdateSql(String sql,Object[] param);
	public List executeQuerySql(String sql,Object[] param);
}
