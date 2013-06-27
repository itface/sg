package com.sapGarden.application.synConfig.service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public interface RuntimeMonitor_ProcessError_Service {

	public boolean process(SapDataCollection sapDataCollection,String type,String ids,String user)throws Exception;
}
