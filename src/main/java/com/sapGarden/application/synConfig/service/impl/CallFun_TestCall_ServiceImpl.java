package com.sapGarden.application.synConfig.service.impl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.jco.commons.constants.Constant;
import com.sapGarden.application.jco.commons.exception.JcoException;
import com.sapGarden.application.jco.commons.service.CallFun_TestCall_CommonService;
import com.sapGarden.application.synConfig.service.CallFun_TestCall_Service;
import com.sapGarden.global.utils.commonUtils.BeanUtils;
@Service
public class CallFun_TestCall_ServiceImpl implements CallFun_TestCall_Service{

	private BeanUtils beanUtils;
	@Autowired
	public void setBeanUtils(BeanUtils beanUtils) {
		this.beanUtils = beanUtils;
	}
	@Override
	public JSONObject testCall(String type,SapDataCollection sapDataCollection) throws JcoException {
		// TODO Auto-generated method stub
		Object constantObject = beanUtils.getBeanByName(type+"_constants");
		if(constantObject!=null){
			Constant constant = (Constant)constantObject;
			CallFun_TestCall_CommonService service  = constant.getTestCallService();
			if(service!=null){
				return service.testCall(sapDataCollection,type);
			}
		}
		return null;
	}

}
