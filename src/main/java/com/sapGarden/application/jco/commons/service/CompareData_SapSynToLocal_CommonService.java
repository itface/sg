package com.sapGarden.application.jco.commons.service;

import java.util.List;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.jco.commons.model.CommonCompareDataModel;

public interface CompareData_SapSynToLocal_CommonService {
	public void sapSynToLocal(SapDataCollection sapDataCollection,List<CommonCompareDataModel> list,String type,String opttype,String user,boolean logException)throws Exception;
	public void jqgridDataSapSynToLocal(SapDataCollection sapDataCollection,String list,String user)throws Exception ;
}
