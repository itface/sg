package com.sapGarden.application.synConfig.service.impl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.dataCollection.service.SapDataCollectionService;
import com.sapGarden.application.jco.commons.constants.Constant;
import com.sapGarden.application.jco.commons.service.CompareData_CommonService;
import com.sapGarden.application.synConfig.service.CompareData_Service;
import com.sapGarden.global.utils.commonUtils.BeanUtils;
@Service
public class CompareData_ServiceImpl implements CompareData_Service{

	private BeanUtils beanUtils;
	@Autowired
	public void setBeanUtils(BeanUtils beanUtils) {
		this.beanUtils = beanUtils;
	}
	@Override
	public JSONObject compare(String type,SapDataCollection sapDataCollection) throws Exception {
		// TODO Auto-generated method stub
		Object constantObject = beanUtils.getBeanByName(type+"_constants");
		if(constantObject!=null){
			Constant constant = (Constant)constantObject;
			CompareData_CommonService service  = constant.getCommonCompareService();
			if(service!=null){
				return service.getJqgridCompareJsonData(sapDataCollection,type);
			}
		}
		return null;
	}

}
