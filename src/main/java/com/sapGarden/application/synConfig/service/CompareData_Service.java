package com.sapGarden.application.synConfig.service;

import net.sf.json.JSONObject;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public interface CompareData_Service {

	public JSONObject compare(String type,SapDataCollection sapDataCollection)throws Exception;
}
