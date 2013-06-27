package com.sapGarden.application.jco.commons.constants;

import java.util.Map;

import org.quartz.Job;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sapGarden.application.jco.commons.service.BasicService;
import com.sapGarden.application.jco.commons.service.CallFun_TestCall_CommonService;
import com.sapGarden.application.jco.commons.service.CommonRfcService;
import com.sapGarden.application.jco.commons.service.CompareData_CommonService;
import com.sapGarden.application.jco.commons.service.CompareData_SapSynToLocal_CommonService;
import com.sapGarden.application.jco.commons.service.RuntimeMonitor_ProcessError_CommonService;
import com.sapGarden.global.jqgrid.service.ConstructJqgridConfigModelService;

public interface Constant {
	public RuntimeMonitor_ProcessError_CommonService getCommon_processError_service();
	public Map<String,Class> getBapiExportMap();
	public Map<String,Class> getBapiTableExpMap();
	public CommonRfcService getCommonRfcService();
	public CompareData_SapSynToLocal_CommonService getCommon_ComparedData_sapSynToLocal_Service();
	public CompareData_CommonService getCommonCompareService();
	public CallFun_TestCall_CommonService getTestCallService();
	public Job getJob();
	public ConstructJqgridConfigModelService getConstructLogJqgridConfigModelService();
	public ConstructJqgridConfigModelService getConstructDataJqgridConfigModelService();
	public ConstructJqgridConfigModelService getConstructResultJqgridConfigModelService();
	public ConstructJqgridConfigModelService getConstructCompareDataJqgridConfigModelService();
	public ConstructJqgridConfigModelService getConstructMonitorJqgridConfigModelService();
	public BasicService getLogService();
	public BasicService getService();
	public Class getModelClass();
	public Class getLogModelClass();
	public PagingAndSortingRepository getDao();
	public PagingAndSortingRepository getLogDao();
}
