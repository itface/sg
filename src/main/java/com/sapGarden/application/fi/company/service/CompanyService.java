package com.sapGarden.application.fi.company.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.log.service.CommonServiceWithLog;
import com.sapGarden.application.fi.company.model.Company;



public interface CompanyService extends CommonServiceWithLog<Company>{

	public long findTotalNumByPage(SapDataCollection sapDataCollection,String companyCode);
	public List<Company> findByPage(SapDataCollection sapDataCollection,String companyCode,int rows,int page);
	public List<Company> find(SapDataCollection sapDataCollection);
	public List<Company> findByCompanyCode(SapDataCollection sapDataCollection,String companyCode);
	public JSONObject findDataOfJqgridByPage(SapDataCollection sapDataCollection,String companyCode,int rows,int page);
	public void addList(List<Company> list);
	public void add(Company company);
	public void update(Company company);
	public void removeAll(SapDataCollection sapDataCollection);
	public void removeById(long id);
}
