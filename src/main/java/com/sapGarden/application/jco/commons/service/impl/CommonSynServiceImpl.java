package com.sapGarden.application.jco.commons.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.constants.LogConstants;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.jco.commons.constants.Constant;
import com.sapGarden.application.jco.commons.model.CommonCompareDataModel;
import com.sapGarden.application.jco.commons.model.CommonLogModel;
import com.sapGarden.application.jco.commons.service.BasicService;
import com.sapGarden.application.jco.commons.service.CommonDaoService;
import com.sapGarden.application.jco.commons.service.CommonRfcService;
import com.sapGarden.application.jco.commons.service.CommonSynService;
import com.sapGarden.application.jco.commons.service.CompareData_CommonService;
import com.sapGarden.application.jco.commons.service.CompareData_SapSynToLocal_CommonService;
import com.sapGarden.global.utils.commonUtils.BeanUtils;
@Service("commonSynService")
@Order(2)
public class CommonSynServiceImpl implements CommonSynService{

	private Class modelClass;
	private Class logModelClass;
	private CommonRfcService commonRfcService;
	private CompareData_CommonService compareData_CommonService;
	private CompareData_SapSynToLocal_CommonService compareData_SapSynToLocal_CommonService;
	private BasicService service;
	private BasicService logService;
	private BeanUtils beanUtils;
	@Autowired
	public void setBeanUtils(BeanUtils beanUtils) {
		this.beanUtils = beanUtils;
	}
	private CommonDaoService commonDaoService;
	@Autowired
	@Qualifier("commonDaoService")
	public void setCommonDaoService(CommonDaoService commonDaoService) {
		this.commonDaoService = commonDaoService;
	}
	public void init(String type){
		Object constantObject = beanUtils.getBeanByName(type+"_constants");
		if(constantObject!=null){
			Constant constant = (Constant)constantObject;
			commonRfcService = constant.getCommonRfcService();
			service=constant.getService();
			logService=constant.getLogService();
			modelClass=constant.getModelClass();
			logModelClass=constant.getLogModelClass();
			compareData_SapSynToLocal_CommonService=constant.getCommon_ComparedData_sapSynToLocal_Service();
			compareData_CommonService=constant.getCommonCompareService();
		}else{
			commonRfcService = null;
			service=null;
			logService=null;
			modelClass=null;
			logModelClass=null;
			compareData_SapSynToLocal_CommonService=null;
			compareData_CommonService=null;
		}
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean syn(SapDataCollection sapDataCollection,String type,String optType,String user,Map<String, Object> map)throws Exception{
		// TODO Auto-generated method stub
		this.init(type);
		List<CommonCompareDataModel> successList=new LinkedList();
		List<CommonLogModel> logList=new LinkedList();
		List<CommonCompareDataModel> compareDataModelList = this.getSapData(sapDataCollection,type,optType,user,map);
		this.initDataList(compareDataModelList, successList, logList,type,optType,user);
		this.insertData(sapDataCollection,successList, logList, optType,type,user);
		return true;
	}

	public List<CommonCompareDataModel> getSapData(SapDataCollection sapDataCollection,String type,String optType,String user,Map<String, Object> map)throws Exception{
		if(compareData_CommonService!=null){
			List<CommonCompareDataModel> commonCompareDataModelList=compareData_CommonService.getCompareData(sapDataCollection,type);
			return commonCompareDataModelList;
		}
		return null;
	}
	public void initDataList(List<CommonCompareDataModel> compareDataModelList,List<CommonCompareDataModel> successList,List<CommonLogModel> logList,String type,String optType,String user) throws Exception{
		if(compareDataModelList!=null&&compareDataModelList.size()>0){
			for(CommonCompareDataModel commonCompareDataModel : compareDataModelList){
				boolean flag = commonCompareDataModel.isFlag();
				if(!flag){
					CommonLogModel log = (CommonLogModel)logModelClass.getConstructor(CommonCompareDataModel.class).newInstance(commonCompareDataModel);
					log.setOptmsg(commonCompareDataModel.getMsg());
					log.setOpt(LogConstants.OPT_CALLSAPERROR);
					log.setOptflag(LogConstants.OPTFLAG_FAIL);
					log.setOpttype(optType);
					log.setOptuser(user);
					log.setOpttime(new Date());
					logList.add(log);
				}else{
					successList.add(commonCompareDataModel);
				}
			}
		}else{
			CommonLogModel log = (CommonLogModel)logModelClass.getConstructor().newInstance();
			log.setOpt(LogConstants.OPT_NOTSYNDATA);
			log.setOptflag(LogConstants.OPTFLAG_SUCCESS);
			log.setOpttype(optType);
			log.setOptuser(user);
			log.setOpttime(new Date());
			logList.add(log);
		}
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void insertData(SapDataCollection sapDataCollection,List<CommonCompareDataModel> successList,List<CommonLogModel> logList,String optType,String type,String user)throws Exception {
		if(logList!=null&&logList.size()>0){
			logService.saveList(logList);
		}
		if(successList!=null&&successList.size()>0){
			compareData_SapSynToLocal_CommonService.sapSynToLocal(sapDataCollection,successList,type,optType,user, false);
		}
	}
}
