package com.sapGarden.application.jco.commons.service;

import java.util.List;
import java.util.Map;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.jco.commons.exception.JcoException;
import com.sapGarden.application.jco.commons.model.CommonSynModel;
import com.sapGarden.application.jco.commons.model.JcoReturnModel;

public interface CommonRfcService {

	public List<JcoReturnModel> callRfc(SapDataCollection sapDataCollection,Map<String,Object> map,boolean ifFilter) throws JcoException;
	public List<CommonSynModel> getData(SapDataCollection sapDataCollection,Map<String,Object> map,boolean ifFilter)throws JcoException;
}
