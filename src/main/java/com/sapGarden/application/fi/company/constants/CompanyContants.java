package com.sapGarden.application.fi.company.constants;

import java.util.HashMap;
import java.util.Map;

import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.sapGarden.application.fi.company.dao.CompanyDao;
import com.sapGarden.application.fi.company.dao.CompanyLogDao;
import com.sapGarden.application.fi.company.model.Company;
import com.sapGarden.application.fi.company.model.CompanyLog;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getDetail.Export_companyCode_detail;
import com.sapGarden.application.fi.company.service.CompanyLogService;
import com.sapGarden.application.fi.company.service.CompanyService;
import com.sapGarden.application.jco.commons.constants.Constant;
import com.sapGarden.application.jco.commons.service.BasicService;
import com.sapGarden.application.jco.commons.service.CallFun_TestCall_CommonService;
import com.sapGarden.application.jco.commons.service.CommonRfcService;
import com.sapGarden.application.jco.commons.service.CompareData_CommonService;
import com.sapGarden.application.jco.commons.service.CompareData_SapSynToLocal_CommonService;
import com.sapGarden.application.jco.commons.service.RuntimeMonitor_ProcessError_CommonService;
import com.sapGarden.global.jqgrid.service.ConstructJqgridConfigModelService;
import com.sapGarden.global.utils.commonUtils.BeanUtils;
@Component("bo_fin_company_constants")
public class CompanyContants implements Constant{

	private BeanUtils beanUtils;
	@Autowired
	public void setBeanUtils(BeanUtils beanUtils) {
		this.beanUtils = beanUtils;
	} 
	@Override
	public ConstructJqgridConfigModelService getConstructDataJqgridConfigModelService() {
		// TODO Auto-generated method stub
		return (ConstructJqgridConfigModelService)beanUtils.getBeanByName("bo_fin_company_constructData_JqgridConfigModel");
	}
	@Override
	public ConstructJqgridConfigModelService getConstructResultJqgridConfigModelService() {
		// TODO Auto-generated method stub
		return (ConstructJqgridConfigModelService)beanUtils.getBeanByName("bo_fin_company_constructResult_JqgridConfigModel");
	}
	@Override
	public BasicService getLogService() {
		// TODO Auto-generated method stub
		return (BasicService)beanUtils.getBeanByType(CompanyLogService.class);
	}
	@Override
	public BasicService getService() {
		// TODO Auto-generated method stub
		return (BasicService)beanUtils.getBeanByType(CompanyService.class);
	}
	@Override
	public ConstructJqgridConfigModelService getConstructLogJqgridConfigModelService() {
		// TODO Auto-generated method stub
		return (ConstructJqgridConfigModelService)beanUtils.getBeanByName("bo_fin_company_constructLog_JqgridConfigModel");
	}
	@Override
	public Job getJob() {
		// TODO Auto-generated method stub
		return (Job)beanUtils.getBeanByName("bo_fin_company_job");
	}
	@Override
	public CallFun_TestCall_CommonService getTestCallService() {
		// TODO Auto-generated method stub
		return (CallFun_TestCall_CommonService)beanUtils.getBeanByName("bo_fin_company_testCall_service");
	}
	@Override
	public ConstructJqgridConfigModelService getConstructCompareDataJqgridConfigModelService() {
		// TODO Auto-generated method stub
		return (ConstructJqgridConfigModelService)beanUtils.getBeanByName("bo_fin_company_constructCompareData_JqgridConfigModel");
	}
	public CompareData_CommonService getCommonCompareService() {
		return (CompareData_CommonService)beanUtils.getBeanByName("bo_fin_company_compareData_service");
	}
	public CompareData_SapSynToLocal_CommonService getCommon_ComparedData_sapSynToLocal_Service() {
		return (CompareData_SapSynToLocal_CommonService)beanUtils.getBeanByName("bo_fin_company_comparedData_sapSynToLocal_service");
	}
	@Override
	public PagingAndSortingRepository getDao() {
		// TODO Auto-generated method stub
		return (PagingAndSortingRepository)beanUtils.getBeanByType(CompanyDao.class);
	}
	@Override
	public PagingAndSortingRepository getLogDao() {
		// TODO Auto-generated method stub
		return (PagingAndSortingRepository)beanUtils.getBeanByType(CompanyLogDao.class);
	}
	@Override
	public Class getLogModelClass() {
		// TODO Auto-generated method stub
		return CompanyLog.class;
	}
	@Override
	public Class getModelClass() {
		// TODO Auto-generated method stub
		return Company.class;
	}
	@Override
	public Map<String, Class> getBapiExportMap() {
		// TODO Auto-generated method stub
		 Map<String, Class> map = new  HashMap<String, Class>();
		 map.put("COMPANYCODE_DETAIL", Export_companyCode_detail.class);
		return map;
	}
	@Override
	public Map<String, Class> getBapiTableExpMap() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CommonRfcService getCommonRfcService() {
		// TODO Auto-generated method stub
		return (CommonRfcService)beanUtils.getBeanByName("bo_fin_company_CommonRfcService");
	}
	@Override
	public ConstructJqgridConfigModelService getConstructMonitorJqgridConfigModelService() {
		// TODO Auto-generated method stub
		return (ConstructJqgridConfigModelService)beanUtils.getBeanByName("bo_fin_company_constructMonitor_JqgridConfigModel");
	}
	public RuntimeMonitor_ProcessError_CommonService getCommon_processError_service() {
		return (RuntimeMonitor_ProcessError_CommonService)beanUtils.getBeanByName("bo_fin_company_processError_service");
	}
	
	
	
	
	
	
}
