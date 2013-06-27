package com.sapGarden.application.synConfig.service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public interface CompareData_SapSynToLocal_Service {

	public void sapSynToLocal(SapDataCollection sapDataCollection,String type,String list,String user)throws Exception ;
}
