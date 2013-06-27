package com.sapGarden.application.synConfig.service.impl;

import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.jco.commons.constants.Constant;
import com.sapGarden.application.synConfig.service.Abstract_ConstructJqgrid_Service;
import com.sapGarden.global.jqgrid.service.ConstructJqgridConfigModelService;
@Service("compareData_ConstructGrid_ServiceImpl")
public class CompareData_ConstructGrid_ServiceImpl extends Abstract_ConstructJqgrid_Service{

	
	@Override
	public String construct(String type,SapDataCollection sapDataCollection) {
		// TODO Auto-generated method stub
		Object constantObject = super.beanUtils.getBeanByName(type+"_constants");
		if(constantObject!=null){
			Constant constant = (Constant)constantObject;
			ConstructJqgridConfigModelService service  = constant.getConstructCompareDataJqgridConfigModelService();
			if(service!=null){
				return service.construct(sapDataCollection,type);
			}
		}
		return null;
	}

}
