package com.sapGarden.application.commons.runtime.columninfo.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.runtime.columninfo.model.JqgirdNewRuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;

public interface Runtime_ColumnInfo_Service {

	public List<RuntimeColumnInfo> findAll(SapDataCollection sapDataCollection,String businesstype);
	public List<RuntimeColumnInfo> findAllActiveData(SapDataCollection sapDataCollection,String businesstype);
	public List<RuntimeColumnInfo> findByBusinesstype(int pageNumber,int rowsPerPage,SapDataCollection sapDataCollection,String businesstype);
	public JSONObject findJsonByBusinesstype(int pageNumber,int rowsPerPage,String sidx,String sord,SapDataCollection sapDataCollection,String businesstype);
	public void addList(List<RuntimeColumnInfo> list);
	public void addOne(JqgirdNewRuntimeColumnInfo jqgirdNewDbReflectOfColumn);
	public void updateOne(JqgirdNewRuntimeColumnInfo jqgirdNewDbReflectOfColumn);
	public void deleteOne(long id);
	public void deleteByTableAndSapclient(String tablename,long sapclient);
	public void updateStatusById(long id,String status);
	public long countByTableAndSapclient(String tablename,long sapclient);
}
