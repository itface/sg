package com.sapGarden.application.fi.company.service;

import java.lang.reflect.InvocationTargetException;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

import net.sf.json.JSONObject;

public interface ManualSynService {

	public JSONObject testCall(SapDataCollection sapDataCollection)throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
