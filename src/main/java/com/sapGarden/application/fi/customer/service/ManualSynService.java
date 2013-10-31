package com.sapGarden.application.fi.customer.service;

import java.lang.reflect.InvocationTargetException;

import net.sf.json.JSONObject;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public interface ManualSynService {

	public JSONObject getSapData(SapDataCollection sapDataCollection,String kunnr)throws SecurityException, IllegalArgumentException,NoSuchMethodException, IllegalAccessException,InvocationTargetException, InstantiationException, NoSuchFieldException ;
	public void manualSyn(SapDataCollection sapDataCollection,String user,String kna1s,String knb1s,String knvvs);
}
