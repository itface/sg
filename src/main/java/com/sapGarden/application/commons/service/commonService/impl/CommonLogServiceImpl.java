package com.sapGarden.application.commons.service.commonService.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dao.CommonLogDao;
import com.sapGarden.application.commons.service.commonService.TempCommonLogService;
import com.sapGarden.global.jqgrid.model.Jqgrid_DataJson;
import com.sapGarden.global.json.JsonUtils;
@Service
public class CommonLogServiceImpl implements TempCommonLogService{

	private CommonLogDao commonLogDao;
	
	@Autowired
	public void setCommonLogDao(CommonLogDao commonLogDao) {
		this.commonLogDao = commonLogDao;
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public JSONObject findErrorLogBySapclientAndFilters(Class modelClass,int pageNumber, int rowsPerPage, long sapclient,String filters) {
		// TODO Auto-generated method stub
		List list = commonLogDao.findErrorLogBySapclientAndCondition(modelClass,pageNumber, rowsPerPage, sapclient,filters);
		long count = commonLogDao.countError(modelClass,sapclient);
		Jqgrid_DataJson jsonData = new Jqgrid_DataJson(pageNumber,rowsPerPage,count,list);
		JSONObject json = JsonUtils.objectToJSONObject(jsonData,null);
		return json;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public JSONObject findSuccessLogBySapclientAndFilters(Class modelClass,int pageNumber, int rowsPerPage, long sapclient,String filters) {
		// TODO Auto-generated method stub
		List list = commonLogDao.findSuccessLogBySapclientAndCondition(modelClass,pageNumber, rowsPerPage, sapclient,filters);
		long count = commonLogDao.countSuccess(modelClass,sapclient);
		Jqgrid_DataJson jsonData = new Jqgrid_DataJson(pageNumber,rowsPerPage,count,list);
		JSONObject json = JsonUtils.objectToJSONObject(jsonData,null);
		return json;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateProcessstatusByIds(Class modelClass,long sapclient, String ids) {
		// TODO Auto-generated method stub
		commonLogDao.updateProcessstatusByIds(modelClass,sapclient,ids);
	}

}
