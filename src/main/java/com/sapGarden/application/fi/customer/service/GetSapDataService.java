package com.sapGarden.application.fi.customer.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;
import com.sapGarden.application.fi.customer.model.SapDataModel;

public interface GetSapDataService {

	public String getActiveColumnNames(List<RuntimeColumnInfo> list);
	public String getColumnNames(List<RuntimeColumnInfo> list);
	public SapDataModel getSapData(SapDataCollection sapDataCollection,int perNum,List<RuntimeColumnInfo> kna1RuntimeColumnInfoList,List<RuntimeColumnInfo> knb1RuntimeColumnInfoList,List<RuntimeColumnInfo> knvvRuntimeColumnInfoList,String kna1s,String knb1s,String knvvs,Object O_CONTROL1,Object O_CONTROL2,Object O_CONTROL3,Object O_CONTROL4,Object O_CONTROL5,Object O_CONTROL6,String kunnr,String bukrs,String vkorg,String vtweg,String spart)throws SecurityException, NoSuchFieldException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;
}
