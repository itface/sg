package com.sapGarden.application.jco.commons.service;

import java.util.Map;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public interface CommonSynService {

	public boolean syn(SapDataCollection sapDataCollection,String type,String optType,String user,Map<String,Object> map)throws Exception;
}
