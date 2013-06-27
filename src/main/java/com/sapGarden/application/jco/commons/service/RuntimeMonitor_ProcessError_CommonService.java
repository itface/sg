package com.sapGarden.application.jco.commons.service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public interface RuntimeMonitor_ProcessError_CommonService {

	public boolean process(SapDataCollection sapDataCollection,String ids,String user)throws Exception;
}
