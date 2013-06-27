package com.sapGarden.application.commons.runtime.baseinfo.service;

import com.sapGarden.application.commons.runtime.baseinfo.model.RuntimeBasicInfo;

public interface Runtime_BasicInfo_Service {

	public RuntimeBasicInfo findOneByBusinesstype(String businesstype,long sapclient);
	public void addOne(RuntimeBasicInfo runtimeBasicInfo,String[] tableNames,long sapclient);
	public void updateOne(RuntimeBasicInfo runtimeBasicInfo);
	public void updateByBusinesstypes(String btypes,long sapclient,String status);
}
