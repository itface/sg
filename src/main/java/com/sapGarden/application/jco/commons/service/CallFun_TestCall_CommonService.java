package com.sapGarden.application.jco.commons.service;

import net.sf.json.JSONObject;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.jco.commons.exception.JcoException;

public interface CallFun_TestCall_CommonService {

	public JSONObject testCall(SapDataCollection sapDataCollection,String type)throws JcoException;
}
