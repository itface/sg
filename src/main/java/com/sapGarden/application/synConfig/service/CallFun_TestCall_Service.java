package com.sapGarden.application.synConfig.service;

import net.sf.json.JSONObject;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.jco.commons.exception.JcoException;

public interface CallFun_TestCall_Service {

	public JSONObject testCall(String type,SapDataCollection sapDataCollection)throws JcoException;
}
