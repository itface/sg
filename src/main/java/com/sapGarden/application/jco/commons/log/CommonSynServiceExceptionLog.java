package com.sapGarden.application.jco.commons.log;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.constants.LogConstants;
import com.sapGarden.application.jco.commons.constants.Constant;
import com.sapGarden.application.jco.commons.model.CommonLogModel;
import com.sapGarden.application.jco.commons.service.BasicService;
import com.sapGarden.global.exception.JpaException;
import com.sapGarden.global.utils.commonUtils.BeanUtils;
@Aspect
@Component("CommonSynServiceExceptionLog")
@Order(1)
public class CommonSynServiceExceptionLog {

	private BasicService logService;
	private Class logModelClass;
	private BeanUtils beanUtils;
	@Autowired
	public void setBeanUtils(BeanUtils beanUtils) {
		this.beanUtils = beanUtils;
	}
	public void init(String type){
		Object constantObject = beanUtils.getBeanByName(type+"_constants");
		if(constantObject!=null){
			Constant constant = (Constant)constantObject;
			logService=constant.getLogService();
			logModelClass=constant.getLogModelClass();
		}else{
			logService=null;
			logModelClass=null;
		}
	}
	@AfterThrowing(value="execution(* com.sapGarden.application.jco.commons.service.impl.CommonSynServiceImpl.syn(..)) && args(type,optType,user,map)",argNames= "exception,type,optType,user,map",throwing = "exception")
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void log(Exception exception,String type,String optType,String user,Map<String, Object> map) throws JpaException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		this.init(type);
		CommonLogModel obj = (CommonLogModel)logModelClass.getConstructor().newInstance();
		obj.setOptmsg("程序异常:"+(exception.getMessage()==null?"":exception.getMessage())+(exception.getStackTrace())[0].toString());
		obj.setOptflag(LogConstants.OPTFLAG_FAIL);
		obj.setOptuser(user);
		obj.setOpttype(optType);
		logService.saveOne(obj);
	}
	/*
	@AfterThrowing(value="execution(* com.sapGarden.system.jco.commons.service.impl.CommonSynServiceImpl.getSapData(..)) && args(type,optType,user,map)",argNames= "exception,type,optType,user,map",throwing = "exception")
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void logGetSapDataException(Exception exception,String type,String optType,String user,Map<String, Object> map) throws JpaException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		this.init(type);
		CommonLogModel obj = (CommonLogModel)logModelClass.getConstructor().newInstance();
		obj.setOptmsg(exception.getMessage()+(exception.getStackTrace())[0].toString());
		obj.setOptflag(CommonLogModel.OPTFLAG_FAIL);
		obj.setOptuser(user);
		obj.setOpttype(optType);
		obj.setOpttime(new Date());
		logService.saveOne(obj);
	}
	@AfterThrowing(value="execution(* com.sapGarden.system.jco.commons.service.impl.CommonSynServiceImpl.initDataList(..)) && args(compareDataModelList,successList,logList,type,optType,user)",argNames= "exception,compareDataModelList,successList,logList,type,optType,user",throwing = "exception")
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void logInitDataListException(Exception exception,List<CommonCompareDataModel> compareDataModelList,List<CommonCompareDataModel> successList,List<CommonLogModel> logList,String type,String optType,String user) throws JpaException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		this.init(type);
		CommonLogModel obj = (CommonLogModel)logModelClass.getConstructor().newInstance();
		obj.setOptmsg(exception.getMessage()+(exception.getStackTrace())[0].toString());
		obj.setOptflag(CommonLogModel.OPTFLAG_FAIL);
		obj.setOptuser(user);
		obj.setOpttype(optType);
		obj.setOpttime(new Date());
		logService.saveOne(obj);
	}
	@AfterThrowing(value="execution(* com.sapGarden.system.jco.commons.service.impl.CommonSynServiceImpl.insertData(..)) && args(successList,logList,optType,type,user)",argNames= "exception,successList,logList,type,optType,user",throwing = "exception")
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void logInsertDataException(Exception exception,List<CommonCompareDataModel> successList,List<CommonLogModel> logList,String optType,String type,String user) throws JpaException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		this.init(type);
		CommonLogModel obj = (CommonLogModel)logModelClass.getConstructor().newInstance();
		obj.setOptmsg(exception.getMessage()+(exception.getStackTrace())[0].toString());
		obj.setOptflag(CommonLogModel.OPTFLAG_FAIL);
		obj.setOptuser(user);
		obj.setOpttype(optType);
		obj.setOpttime(new Date());
		logService.saveOne(obj);
	}
	*/
}
