package com.sapGarden.application.fi.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.constants.LogConstants;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.fi.company.dao.CompanyLogDao;
import com.sapGarden.application.jco.commons.service.CommonSynService;
import com.sapGarden.application.jco.commons.service.RuntimeMonitor_ProcessError_CommonService;
@Service("bo_fin_company_processError_service")
public class Company_processError_serviceImpl implements RuntimeMonitor_ProcessError_CommonService{

	private final String type = "bo_fin_company";
	private CommonSynService commonSynService;
	private CompanyLogDao companyLogDao;
	@Autowired
	public void setCommonSynService(CommonSynService commonSynService) {
		this.commonSynService = commonSynService;
	}
	@Autowired
	public void setCompanyLogDao(CompanyLogDao companyLogDao) {
		this.companyLogDao = companyLogDao;
	}
	@Override
	public boolean process(SapDataCollection sapDataCollection,String ids,String user)throws Exception{
		// TODO Auto-generated method stub
		if(ids!=null&&!"".equals(ids)){
			commonSynService.syn(sapDataCollection,type,LogConstants.OPTTYPE_PROCESSERROR,user,null);
			companyLogDao.updateProcessstatusByIds(ids,sapDataCollection.getId());
		}
		return true;
	}

}
