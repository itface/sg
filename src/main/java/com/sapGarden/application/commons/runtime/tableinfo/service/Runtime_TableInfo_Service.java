package com.sapGarden.application.commons.runtime.tableinfo.service;

import com.sapGarden.application.commons.runtime.tableinfo.model.RuntimeTableInfo;

public interface Runtime_TableInfo_Service {

	public RuntimeTableInfo findOneByBusinesstype(long sapclient,String businesstype);
	public void addOne(RuntimeTableInfo dbReflect);
	public void updateOne(RuntimeTableInfo dbReflect);
	public void deleteByBusinesstypeAndSapclient(String tablename, long sapclient);
}
