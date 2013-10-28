package com.sapGarden.application.fi.customer.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.sf.json.JSONObject;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.fi.customer.model.Kna1Compared;
import com.sapGarden.application.fi.customer.model.Knb1Compared;
import com.sapGarden.application.fi.customer.model.KnvvCompared;

public interface CompareDataService {

	public void comparedDataToLocal(SapDataCollection sapDataCollection,String user,String opttype,List<Kna1Compared> kna1List,List<Knb1Compared> knb1List,List<KnvvCompared> knvvList) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;
	/**
	 * 对比功能中的同步到本地调用的方法
	 * @param sapclient
	 * @param user
	 * @param kna1Compareds
	 * @param knb1Compareds
	 * @param knvvCompareds
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void compareSapAndGardenDataToLocal(SapDataCollection sapDataCollection,String user,String kna1Compareds,String knb1Compareds,String knvvCompareds)throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;
	//public String getDataTableHtml(SapDataCollection sapDataCollection,String tablename)throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException;
	//public String getComparedResult(SapDataCollection sapDataCollection);
	/**
	 * 对比功能中对比调用的方法
	 * @param sapDataCollection
	 * @param user
	 * @param kunnr
	 * @param bukrs
	 * @param vkorg
	 * @param vtweg
	 * @param spart
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws NoSuchFieldException
	 */
	public JSONObject compareSapAndGarden(SapDataCollection sapDataCollection,String user,String kunnr,String bukrs,String vkorg,String vtweg,String spart)throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException ;
	//public void doCompare(SapDataCollection sapDataCollection,String user,String kunnr,String bukrs,String vkorg,String vtweg,String spart) throws SecurityException, IllegalArgumentException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;
	//public void initComaredData(SapDataCollection sapDataCollection);
}
