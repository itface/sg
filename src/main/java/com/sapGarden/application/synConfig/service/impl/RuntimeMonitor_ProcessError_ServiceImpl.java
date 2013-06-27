package com.sapGarden.application.synConfig.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.jco.commons.constants.Constant;
import com.sapGarden.application.jco.commons.service.RuntimeMonitor_ProcessError_CommonService;
import com.sapGarden.application.synConfig.service.RuntimeMonitor_ProcessError_Service;
import com.sapGarden.global.utils.commonUtils.BeanUtils;
@Service
public class RuntimeMonitor_ProcessError_ServiceImpl implements RuntimeMonitor_ProcessError_Service{

	private BeanUtils beanUtils;
	@Autowired
	public void setBeanUtils(BeanUtils beanUtils) {
		this.beanUtils = beanUtils;
	}
	@Override
	public boolean process(SapDataCollection sapDataCollection,String type, String ids, String user)throws Exception {
		// TODO Auto-generated method stub
		Object constantObject = beanUtils.getBeanByName(type+"_constants");
		if(constantObject!=null){
			Constant constant = (Constant)constantObject;
			RuntimeMonitor_ProcessError_CommonService service  = constant.getCommon_processError_service();
			if(service!=null){
				return service.process(sapDataCollection,ids, user);
			}
		}
		return false;
	}

}
