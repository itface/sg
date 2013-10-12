package com.sapGarden.application.fi.company.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.fi.company.model.CompanyLog;



public interface CompanyLogService{

	public long findTotalNum(SapDataCollection sapDataCollection,String companyCode,String optflag,String bdate,String edate);
	public List<CompanyLog> find(SapDataCollection sapDataCollection,String companyCode,String optflag,String bdate,String edate);
	public List<CompanyLog> findByPage(SapDataCollection sapDataCollection,String companyCode,String optflag,String bdate,String edate,int rows,int page,String sidx,String sord);
	public JSONObject findJqgridData(SapDataCollection sapDataCollection,String companyCode,String optflag,String bdate,String edate,int rows,int page,String sidx,String sord);
}
