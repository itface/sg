package com.sapGarden.application.commons.log.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

/**
 * 带日志的server
 * @author Administrator
 *
 */
public interface CommonLogService<T> {
	public void save(T t);
	public long findTotalNum(SapDataCollection sapDataCollection,String className,JSONObject json,String optflag,String bdate,String edate);
	public List<T> find(SapDataCollection sapDataCollection,String className,JSONObject json,String optflag,String bdate,String edate);
	public List<T> findByPage(SapDataCollection sapDataCollection,String className,JSONObject json,String optflag,String bdate,String edate,int rows,int page,String sidx,String sord);
	public JSONObject findJqgridData(SapDataCollection sapDataCollection,String className,JSONObject json,String optflag,String bdate,String edate,int rows,int page,String sidx,String sord);
}
