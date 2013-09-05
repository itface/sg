package com.sapGarden.application.fi.company.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.sf.json.JSONObject;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.jco.model.CommonCompareDataModel;
/**
 * 对比sap数据和本地数据
 * @author Administrator
 *
 */
public interface CompareDataService {

	public List<CommonCompareDataModel> getCompareData(SapDataCollection sapDataCollection)throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
	public  JSONObject getJqgridCompareJsonData(SapDataCollection sapDataCollection)throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
	public void saveCompareDataList(boolean ifLog,long sapclient,String opttype,String user, List<CommonCompareDataModel> list);
	public void saveComparedDataOfJqgridToLocal(SapDataCollection sapDataCollection,String list,String user,String opttype,boolean ifLog);
}
