package com.sapGarden.application.jco.commons.log;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
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
@Component("commonSynServiceLog_notException")
public class CommonDaoServiceLog_notException {

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
	/**
	 * 不用order,记日志和dao动作在同一事务
	 * @param type
	 * @param optType
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	@Before(value="execution(* com.sapGarden.application.jco.commons.service.impl.CommonDaoServiceImpl.removeAll(..)) && args(logException,type,optType,user)",argNames= "logException,type,optType,user")
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeAll_before(boolean logException,String type,String optType,String user) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		init(type);
		List list = (List)dao.findAll();
		if(list!=null&&list.size()>0){
			List logList = new LinkedList();
			for(int i=0;i<list.size();i++){
				CommonLogModel obj = (CommonLogModel)logModelClass.getConstructor(modelClass,modelClass).newInstance(null,list.get(i));
				obj.setOpt(LogConstants.OPT_DELETE);
				obj.setOptflag(LogConstants.OPTFLAG_SUCCESS);
				obj.setOpttype(optType);
				obj.setOptuser(user);
				obj.setOpttime(new Date());
				logList.add(obj);
			}
			logDao.save(logList);
		}
	}
	
	@Before(value="execution(* com.sapGarden.application.jco.commons.service.impl.CommonDaoServiceImpl.removeOneById(..)) && args(logException,id,type,optType,user)",argNames= "logException,id,type,optType,user")
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeOneById_before(boolean logException,long id,String type,String optType,String user) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		init(type);
		Object object = dao.findOne(id);
		if(object!=null){
			CommonLogModel obj = (CommonLogModel)logModelClass.getConstructor(modelClass,modelClass).newInstance(null,object);
			obj.setOpt(LogConstants.OPT_DELETE);
			obj.setOptflag(LogConstants.OPTFLAG_SUCCESS);
			obj.setOpttype(optType);
			obj.setOptuser(user);
			obj.setOpttime(new Date());
			logDao.save(obj);
		}
	}
	@AfterReturning(value="execution(* com.sapGarden.application.jco.commons.service.impl.CommonDaoServiceImpl.addList(..)) && args(logException,objectList,type,optType,user)",argNames= "logException,objectList,type,optType,user")
	@Transactional(propagation=Propagation.REQUIRED)
	public void addList_afterReturning(boolean logException,List objectList, String type,String optType,String user) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		init(type);
		if(objectList!=null){
			List logList = new LinkedList();
			for(int i=0;i<objectList.size();i++){
				CommonLogModel obj = (CommonLogModel)logModelClass.getConstructor(modelClass,modelClass).newInstance(objectList.get(i),null);
				obj.setOpt(LogConstants.OPT_ADD);
				obj.setOptflag(LogConstants.OPTFLAG_SUCCESS);
				obj.setOpttype(optType);
				obj.setOptuser(user);
				obj.setOpttime(new Date());
				logList.add(obj);
			}
			logDao.save(logList);
		}
	}
	@AfterReturning(value="execution(* com.sapGarden.application.jco.commons.service.impl.CommonDaoServiceImpl.addOne(..)) && args(logException,obj,type,optType,user)",argNames= "logException,obj,type,optType,user")
	@Transactional(propagation=Propagation.REQUIRED)
	public void addOne_afterReturning(boolean logException,Object obj, String type,String optType,String user) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		init(type);
		if(obj!=null){
			CommonLogModel object = (CommonLogModel)logModelClass.getConstructor(modelClass,modelClass).newInstance(obj,null);
			object.setOpt(LogConstants.OPT_ADD);
			object.setOptflag(LogConstants.OPTFLAG_SUCCESS);
			object.setOpttype(optType);
			object.setOptuser(user);
			object.setOpttime(new Date());
			logDao.save(object);
		}
	}
	@AfterReturning(value="execution(* com.sapGarden.application.jco.commons.service.impl.CommonDaoServiceImpl.updateOne(..)) && args(logException,obj,compareDataModel,type,optType,user)",argNames= "logException,obj,compareDataModel,type,optType,user")
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateOne_afterReturning(boolean logException,Object obj,CommonCompareDataModel compareDataModel,String type,String optType,String user) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		init(type);
		CommonLogModel object = null;
		if(compareDataModel!=null){
			object = (CommonLogModel)logModelClass.getConstructor(compareDataModel.getClass()).newInstance(compareDataModel);
		}else if(obj!=null){
			object = (CommonLogModel)logModelClass.getConstructor(modelClass,modelClass).newInstance(obj,null);
		}else{
			object = (CommonLogModel)logModelClass.getConstructor().newInstance();
		}
		object.setOpt(LogConstants.OPT_UPDATE);
		object.setOptflag(LogConstants.OPTFLAG_SUCCESS);
		object.setOpttype(optType);
		object.setOptuser(user);
		object.setOpttime(new Date());
		logDao.save(object);
	}
	@AfterReturning(value="execution(* com.sapGarden.application.jco.commons.service.impl.CommonDaoServiceImpl.updateList(..)) && args(logException,objectList,compareDataModelList,type,optType,user)",argNames= "logException,objectList,compareDataModelList,type,optType,user")
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateList_afterReturning(boolean logException,List objectList,List<CommonCompareDataModel> compareDataModelList, String type,String optType,String user) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		init(type);
		if(objectList!=null){
			List logList = new LinkedList();
			if(compareDataModelList!=null&&compareDataModelList.size()>0){
				for(int i=0;i<compareDataModelList.size();i++){
					CommonLogModel obj = (CommonLogModel)logModelClass.getConstructor(compareDataModelList.get(i).getClass()).newInstance(compareDataModelList.get(i));
					obj.setOpt(LogConstants.OPT_UPDATE);
					obj.setOptflag(LogConstants.OPTFLAG_SUCCESS);
					obj.setOpttype(optType);
					obj.setOptuser(user);
					obj.setOpttime(new Date());
					logList.add(obj);
				}
			}else if(objectList!=null&&objectList.size()>0){
				for(int i=0;i<objectList.size();i++){
					CommonLogModel obj = (CommonLogModel)logModelClass.getConstructor(modelClass,modelClass).newInstance(objectList.get(i),null);
					obj.setOpt(LogConstants.OPT_UPDATE);
					obj.setOptflag(LogConstants.OPTFLAG_SUCCESS);
					obj.setOpttype(optType);
					obj.setOptuser(user);
					obj.setOpttime(new Date());
					logList.add(obj);
				}
			}
			if(logList.size()>0){
				logDao.save(logList);
			}
		}
	}
}
