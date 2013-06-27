package com.sapGarden.application.fi.customer.service;

import java.lang.reflect.InvocationTargetException;

import net.sf.json.JSONObject;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public interface CallFun_GetSapCustomer_TestCall_Service {

	public JSONObject testCall(SapDataCollection sapDataCollection)throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException;
}
