package com.sapGarden.application.fi.company.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.jco.model.CommonCompareDataModel;
import com.sapGarden.application.fi.company.service.CompanyService;
import com.sapGarden.application.fi.company.service.CompareDataService;
import com.sapGarden.application.fi.company.service.SynService;
@Service("company_SynService")
public class SynServiceImpl implements SynService{

	@Autowired
	@Qualifier("company_CompareDataService")
	private CompareDataService compareDataService;


	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean syn(SapDataCollection sapDataCollection,String opttype,String user,boolean ifLog) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{
		// TODO Auto-generated method stub
		List<CommonCompareDataModel> commonCompareDataModelList=compareDataService.getCompareData(sapDataCollection);
		List<CommonCompareDataModel> list = new LinkedList<CommonCompareDataModel>();
		if(commonCompareDataModelList!=null&&commonCompareDataModelList.size()>0){
			for(CommonCompareDataModel commonCompareDataModel : commonCompareDataModelList){
				boolean flag = commonCompareDataModel.isFlag();
				if(flag){
					list.add(commonCompareDataModel);
				}
			}
		}
		compareDataService.saveCompareDataList(ifLog,sapDataCollection.getId(),opttype,user, list);
		return true;
	}
}
