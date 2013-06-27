package com.sapGarden.application.synConfig.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.jco.commons.constants.Constant;
import com.sapGarden.application.jco.commons.service.CompareData_SapSynToLocal_CommonService;
import com.sapGarden.application.synConfig.service.CompareData_SapSynToLocal_Service;
import com.sapGarden.global.utils.commonUtils.BeanUtils;
@Service
public class CompareData_SapSynToLocal_ServiceImpl implements CompareData_SapSynToLocal_Service{

	private BeanUtils beanUtils;
	@Autowired
	public void setBeanUtils(BeanUtils beanUtils) {
		this.beanUtils = beanUtils;
	}
	@Override
	public void sapSynToLocal(SapDataCollection sapDataCollection,String type,String list,String user) throws Exception {
		// TODO Auto-generated method stub
		Object constantObject = beanUtils.getBeanByName(type+"_constants");
		if(constantObject!=null){
			Constant constant = (Constant)constantObject;
			CompareData_SapSynToLocal_CommonService service  = constant.getCommon_ComparedData_sapSynToLocal_Service();
			if(service!=null){
				service.jqgridDataSapSynToLocal(sapDataCollection,list, user);
			}
		}
	}

}
