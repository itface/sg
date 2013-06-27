package com.sapGarden.application.fi.customer.service;

import java.util.List;

import com.sapGarden.application.commons.basetable.model.BaseTableModel;
import com.sapGarden.application.commons.runtime.baseinfo.model.RuntimeBasicInfo;

public interface RuntimeConfig_BasicInfo_Service {

//	public void addBasicInfo(RuntimeBasicInfo runtimeBasicInfo,long sapclient);
//	public void addBasicInfo1(RuntimeBasicInfo runtimeBasicInfo,long sapclient,List<BaseTableModel> kna1List,List<BaseTableModel> knb1List,List<BaseTableModel> knvvList);
	public void addBasicInfo(RuntimeBasicInfo runtimeBasicInfo,long sapclient,List<BaseTableModel> kna1List,List<BaseTableModel> knb1List,List<BaseTableModel> knvvList);
}
