package com.sapGarden.application.fi.customer.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.fi.customer.model.Kna1Compared;
import com.sapGarden.application.fi.customer.model.Knb1Compared;
import com.sapGarden.application.fi.customer.model.KnvvCompared;

public interface CompareDataService {

	public void comparedDataToLocal(long sapclient,String user,String opttype,List<Kna1Compared> kna1List,List<Knb1Compared> knb1List,List<KnvvCompared> knvvList) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;
	public void sapSynToLocal(long sapclient,String user,String kna1Compareds,String knb1Compareds,String knvvCompareds)throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;
	public String getDataTableHtml(SapDataCollection sapDataCollection,String tablename)throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException;
	public String getComparedResult(SapDataCollection sapDataCollection);
	public void compare(SapDataCollection sapDataCollection,String user,String kunnr,String bukrs,String vkorg,String vtweg,String spart)throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException ;
	public void doCompare(SapDataCollection sapDataCollection,String user,String kunnr,String bukrs,String vkorg,String vtweg,String spart) throws SecurityException, IllegalArgumentException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;
	public void initComaredData(long sapclient);
}
