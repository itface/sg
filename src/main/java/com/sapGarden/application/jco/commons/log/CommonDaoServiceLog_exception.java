package com.sapGarden.application.jco.commons.log;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.constants.LogConstants;
import com.sapGarden.application.jco.commons.constants.Constant;
import com.sapGarden.application.jco.commons.model.CommonCompareDataModel;
import com.sapGarden.application.jco.commons.model.CommonLogModel;
import com.sapGarden.global.utils.commonUtils.BeanUtils;
@Aspect
@Component("commonSynServiceLog_notRollback")
@Order(1)
public class CommonDaoServiceLog_exception {

	private PagingAndSortingRepository dao = null;
	private PagingAndSortingRepository logDao = null;
	private Class modelClass = null;
	private Class logModelClass = null;
	private BeanUtils beanUtils;
	@Autowired
	public void setBeanUtils(BeanUtils beanUtils) {
		this.beanUtils = beanUtils;
	}
	public void init(String type){
		Object constantObject = beanUtils.getBeanByName(type+"_constants");
		if(constantObject!=null){
			Constant constant = (Constant)constantObject;
			dao = constant.getDao();
			logDao = constant.getLogDao();
			modelClass = constant.getModelClass();
			logModelClass = constant.getLogModelClass();
		}else{
			dao = null;
			logDao = null;
			modelClass = null;
			logModelClass = null;
		}
	}	
	@AfterThrowing(value="execution(* com.sapGarden.application.jco.commons.service.impl.CommonDaoServiceImpl.addList(..)) && args(logException,objectList,type,optType,user)",argNames= "exception,logException,objectList,type,optType,user",throwing = "exception")
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void addList_afterThrowingAdvice(Throwable exception,boolean logException,List objectList, String type,String optType,String user) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if(logException){
			init(type);
			if(objectList!=null){
				List logList = new LinkedList();
				for(int i=0;i<objectList.size();i++){
					CommonLogModel obj = (CommonLogModel)logModelClass.getConstructor(modelClass,modelClass).newInstance(objectList.get(i),null);
					obj.setOptmsg(exception.getMessage()+(exception.getStackTrace())[0].toString());
					obj.setOpt(LogConstants.OPT_ADD);
					obj.setOptflag(LogConstants.OPTFLAG_FAIL);
					obj.setOpttype(optType);
					obj.setOptuser(user);
					obj.setOpttime(new Date());
					logList.add(obj);
				}
				logDao.save(logList);
			}else{
				CommonLogModel obj = (CommonLogModel)logModelClass.getConstructor().newInstance();
				obj.setOptmsg(exception.getMessage()+(exception.getStackTrace())[0].toString());
				obj.setOpt(LogConstants.OPT_ADD);
				obj.setOptflag(LogConstants.OPTFLAG_FAIL);
				obj.setOpttype(optType);
				obj.setOptuser(user);
				obj.setOpttime(new Date());
				logDao.save(obj);
			}
		}
	}
	
	@AfterThrowing(value="execution(* com.sapGarden.application.jco.commons.service.impl.CommonDaoServiceImpl.addOne(..)) && args(logException,obj,type,optType,user)",argNames= "exception,logException,obj,type,optType,user",throwing = "exception")
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void addOne_afterThrowingAdvice(Throwable exception,boolean logException,Object obj, String type,String optType,String user) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if(logException){
			init(type);
			CommonLogModel object = null;
			if(obj!=null){
				object = (CommonLogModel)logModelClass.getConstructor(modelClass,modelClass).newInstance(obj,null);
			}else{
				object = (CommonLogModel)logModelClass.getConstructor().newInstance();
			}
			object.setOptmsg(exception.getMessage()+(exception.getStackTrace())[0].toString());
			object.setOpt(LogConstants.OPT_ADD);
			object.setOptflag(LogConstants.OPTFLAG_FAIL);
			object.setOpttype(optType);
			object.setOptuser(user);
			object.setOpttime(new Date());
			logDao.save(object);
		}
	}
	
	@AfterThrowing(value="execution(* com.sapGarden.application.jco.commons.service.impl.CommonDaoServiceImpl.removeAll(..)) && args(logException,type,optType,user)",argNames= "exception,logException,type,optType,user",throwing = "exception")
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void removeAll_afterThrowingAdvice(Throwable exception,boolean logException,String type,String optType,String user) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if(logException){
			init(type);
			List list = (List)dao.findAll();
			if(list!=null&&list.size()>0){
				List logList = new LinkedList();
				for(int i=0;i<list.size();i++){
					CommonLogModel obj = (CommonLogModel)logModelClass.getConstructor(modelClass,modelClass).newInstance(null,list.get(i));
					obj.setOptmsg(exception.getMessage()+(exception.getStackTrace())[0].toString());
					obj.setOpt(LogConstants.OPT_DELETE);
					obj.setOptflag(LogConstants.OPTFLAG_FAIL);
					obj.setOpttype(optType);
					obj.setOptuser(user);
					obj.setOpttime(new Date());
					logList.add(obj);
				}
				logDao.save(logList);
			}else{
				CommonLogModel obj = (CommonLogModel)logModelClass.getConstructor().newInstance();
				obj.setOptmsg(exception.getMessage()+(exception.getStackTrace())[0].toString());
				obj.setOpt(LogConstants.OPT_DELETE);
				obj.setOptflag(LogConstants.OPTFLAG_FAIL);
				obj.setOpttype(optType);
				obj.setOptuser(user);
				obj.setOpttime(new Date());
				logDao.save(obj);
			}
		}
	}
	@AfterThrowing(value="execution(* com.sapGarden.application.jco.commons.service.impl.CommonDaoServiceImpl.removeOneById(..)) && args(logException,id,type,optType,user)",argNames= "exception,logException,id,type,optType,user",throwing = "exception")
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void removeOneById_afterThrowingAdvice(Throwable exception,boolean logException,long id,String type,String optType,String user) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if(logException){
			init(type);
			Object object = dao.findOne(id);
			CommonLogModel obj = null;
			if(object!=null){
				object = (CommonLogModel)logModelClass.getConstructor(modelClass,modelClass).newInstance(null,object);
			}else{
				object = (CommonLogModel)logModelClass.getConstructor().newInstance();
			}
			obj.setOptmsg(exception.getMessage()+(exception.getStackTrace())[0].toString());
			obj.setOpt(LogConstants.OPT_DELETE);
			obj.setOptflag(LogConstants.OPTFLAG_FAIL);
			obj.setOpttype(optType);
			obj.setOptuser(user);
			obj.setOpttime(new Date());
			logDao.save(obj);
		}
	}
	@AfterThrowing(value="execution(* com.sapGarden.application.jco.commons.service.impl.CommonDaoServiceImpl.updateOne(..)) && args(logException,obj,compareDataModel,type,optType,user)",argNames= "exception,logException,obj,compareDataModel,type,optType,user",throwing = "exception")
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateOne_afterThrowingAdvice(Throwable exception,boolean logException,Object obj,CommonCompareDataModel compareDataModel, String type,String optType,String user) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if(logException){
			init(type);
			CommonLogModel object = null;
			if(compareDataModel!=null){
				object = (CommonLogModel)logModelClass.getConstructor(compareDataModel.getClass()).newInstance(compareDataModel);
			}else if(obj!=null){
				object = (CommonLogModel)logModelClass.getConstructor(obj.getClass(),obj.getClass()).newInstance(obj,null);
			}else{
				object = (CommonLogModel)logModelClass.getConstructor().newInstance();
			}
			object.setOptmsg(exception.getMessage()+(exception.getStackTrace())[0].toString());
			object.setOpt(LogConstants.OPT_UPDATE);
			object.setOptflag(LogConstants.OPTFLAG_FAIL);
			object.setOptuser(user);
			object.setOpttype(optType);
			object.setOpttime(new Date());
			logDao.save(object);
		}
	}
	@AfterThrowing(value="execution(* com.sapGarden.application.jco.commons.service.impl.CommonDaoServiceImpl.updateList(..)) && args(logException,objectList,compareDataModelList,type,optType,user)",argNames= "exception,logException,objectList,compareDataModelList,type,optType,user",throwing = "exception")
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateList_afterThrowingAdvice(Throwable exception,boolean logException,List objectList,List<CommonCompareDataModel> compareDataModelList, String type,String optType,String user) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		init(type);
		if(objectList!=null){
			List logList = new LinkedList();
			if(compareDataModelList!=null&&compareDataModelList.size()>0){
				for(int i=0;i<compareDataModelList.size();i++){
					CommonLogModel object = (CommonLogModel)logModelClass.getConstructor(compareDataModelList.get(i).getClass()).newInstance(compareDataModelList.get(i));
					object.setOptmsg(exception.getMessage()+(exception.getStackTrace())[0].toString());
					object.setOpt(LogConstants.OPT_UPDATE);
					object.setOptflag(LogConstants.OPTFLAG_FAIL);
					object.setOptuser(user);
					object.setOpttype(optType);
					object.setOpttime(new Date());
					logList.add(object);
				}
				logDao.save(logList);
			}else if(objectList!=null&&objectList.size()>0){
				for(int i=0;i<objectList.size();i++){
					CommonLogModel object = (CommonLogModel)logModelClass.getConstructor(modelClass,modelClass).newInstance(objectList.get(i),null);
					object.setOptmsg(exception.getMessage()+(exception.getStackTrace())[0].toString());
					object.setOpt(LogConstants.OPT_UPDATE);
					object.setOptflag(LogConstants.OPTFLAG_FAIL);
					object.setOptuser(user);
					object.setOpttype(optType);
					object.setOpttime(new Date());
					logList.add(object);
				}
				logDao.save(logList);
			}else{
				CommonLogModel object = (CommonLogModel)logModelClass.getConstructor().newInstance();
				object.setOptmsg(exception.getMessage()+(exception.getStackTrace())[0].toString());
				object.setOpt(LogConstants.OPT_UPDATE);
				object.setOptflag(LogConstants.OPTFLAG_FAIL);
				object.setOptuser(user);
				object.setOpttype(optType);
				object.setOpttime(new Date());
				logDao.save(object);
			}
		}
	}
}
