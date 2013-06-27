package com.sapGarden.application.jco.commons.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.jco.commons.model.CommonCompareDataModel;

public interface CompareData_CommonService {

	public List<CommonCompareDataModel> getCompareData(SapDataCollection sapDataCollection,String type)throws Exception;
	public  JSONObject getJqgridCompareJsonData(SapDataCollection sapDataCollection,String type)throws Exception;
}
