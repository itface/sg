package com.sapGarden.application.synConfig.service;

import net.sf.json.JSONObject;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public interface RuntimeLog_Service {
	public JSONObject findJsonByBusinesstype(SapDataCollection sapDataCollection,String type,String filters) throws Exception;
}
