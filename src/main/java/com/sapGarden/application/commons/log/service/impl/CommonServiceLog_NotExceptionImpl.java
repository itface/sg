package com.sapGarden.application.commons.log.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.log.service.CommonService;
import com.sapGarden.application.commons.log.service.LogDaoService;

@Aspect
//@Order(1)
@Component
public class CommonServiceLog_NotExceptionImpl{

	@Autowired
	private LogDaoService logDaoService;
	//@Autowired
	//private BaseDao dao;
	@Transactional
	@AfterReturning(value="execution(* com.sapGarden.application.commons.log.service.CommonService.addListWithLog(..)) && args(opttype,sapclient,user,modelClass,logModelClass,objects)",argNames= "opttype,sapclient,user,modelClass,logModelClass,objects")
	public void saveListAfter_Advice(String opttype,long sapclient,String user,Class modelClass,Class logModelClass,List<Object> objects) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String msg = "";
		String opt = CommonService.OPT_ADD;
		String optflag = CommonService.OPTFLAG_SUCCESS;
		if(objects!=null&&objects.size()>0){
			List<Object> list = new ArrayList<Object>();
			for(Object object:objects){
				Object logObj = logModelClass.getConstructor().newInstance();
				Method initCommonLogModelMethod = logModelClass.getMethod("initCommonLogModel", new Class[]{Object.class,Object.class,Class.class,Class.class,long.class,String.class,String.class,String.class,String.class,String.class,Date.class});
				initCommonLogModelMethod.invoke(logObj, new Object[]{object,null,modelClass,logModelClass,sapclient,msg,opt,optflag,opttype,user,new Date()});
				list.add(logObj);
			}
			logDaoService.addList(list);
		}
	}
	@Transactional
	@AfterReturning(value="execution(* com.sapGarden.application.commons.log.service.CommonService.addWithLog(..)) && args(opttype,sapclient,user,modelClass,logModelClass,object)",argNames= "opttype,sapclient,user,modelClass,logModelClass,object")
	public void saveAfter_Advice(String opttype,long sapclient,String user,Class modelClass,Class logModelClass,Object object) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String msg = "";
		String opt = CommonService.OPT_ADD;
		String optflag = CommonService.OPTFLAG_SUCCESS;
		if(object!=null){
			Object logObj = logModelClass.getConstructor().newInstance();
			Method initCommonLogModelMethod = logModelClass.getMethod("initCommonLogModel", new Class[]{Object.class,Object.class,Class.class,Class.class,long.class,String.class,String.class,String.class,String.class,String.class,Date.class});
			initCommonLogModelMethod.invoke(logObj, new Object[]{object,null,modelClass,logModelClass,sapclient,msg,opt,optflag,opttype,user,new Date()});
			logDaoService.add(logObj);
		}
	}
	@Transactional
	@AfterReturning(value="execution(* com.sapGarden.application.commons.log.service.CommonService.updateWithLog(..)) && args(opttype,sapclient,user,modelClass,logModelClass,object,oldObject)",argNames= "opttype,sapclient,user,modelClass,logModelClass,object,oldObject")
	public void updateAfter_Advice(String opttype,long sapclient,String user,Class modelClass,Class logModelClass,Object object,Object oldObject) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String msg = "";
		String opt = CommonService.OPT_UPDATE;
		String optflag = CommonService.OPTFLAG_SUCCESS;
		if(object!=null){
			Object logObj = logModelClass.getConstructor().newInstance();
			Method initCommonLogModelMethod = logModelClass.getMethod("initCommonLogModel", new Class[]{Object.class,Object.class,Class.class,Class.class,long.class,String.class,String.class,String.class,String.class,String.class,Date.class});
			initCommonLogModelMethod.invoke(logObj, new Object[]{object,oldObject,modelClass,logModelClass,sapclient,msg,opt,optflag,opttype,user,new Date()});
			logDaoService.add(logObj);
		}
	}
	@Transactional
	@AfterReturning(value="execution(* com.sapGarden.application.commons.log.service.CommonService.deleteWithLog(..)) && args(opttype,sapclient,user,modelClass,logModelClass,object)",argNames= "opttype,sapclient,user,modelClass,logModelClass,object")
	public void deleteBefore_Advice(String opttype,long sapclient,String user,Class modelClass,Class logModelClass,Object object) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String msg = "";
		String opt = CommonService.OPT_DELETE;
		String optflag = CommonService.OPTFLAG_SUCCESS;
		if(object!=null){
			Object logObj = logModelClass.getConstructor().newInstance();
			Method initCommonLogModelMethod = logModelClass.getMethod("initCommonLogModel", new Class[]{Object.class,Object.class,Class.class,Class.class,long.class,String.class,String.class,String.class,String.class,String.class,Date.class});
			initCommonLogModelMethod.invoke(logObj, new Object[]{null,object,modelClass,logModelClass,sapclient,msg,opt,optflag,opttype,user,new Date()});
			logDaoService.add(logObj);
		}
	}

	/*
	@Before(value="execution(* com.sapGarden.application.commons.service.commonService.impl.CommonServiceImpl.saveList(..)) && args(opttype,ifLog,sapclient,user,modelClass,logModelClass,list)",argNames= "opttype,ifLog,sapclient,user,modelClass,logModelClass,list")
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveList_beforeAdvice(String opttype,boolean ifLog,long sapclient,String user,Class modelClass,Class logModelClass,List list) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(ifLog){
			String msg = "";
			String opt = CommonServiceWithLog.OPT_ADD;
			String optflag = CommonServiceWithLog.OPTFLAG_SUCCESS;
			if(list!=null&&list.size()>0){
				List logList = new LinkedList();
				for(int i=0;i<list.size();i++){
					Object obj = logModelClass.getConstructor(modelClass,long.class,String.class,String.class,String.class,String.class,String.class,Date.class).newInstance(list.get(i),sapclient,msg,opt,optflag,opttype,user,new Date());
					logList.add(obj);
				}
				commonDao.save(logList);
			}
		}
	}
	@Before(value="execution(* com.sapGarden.application.commons.service.commonService.impl.CommonServiceImpl.save(..)) && args(opttype,ifLog,sapclient,user,modelClass,logModelClass,object)",argNames= "opttype,ifLog,sapclient,user,modelClass,logModelClass,object")
	@Transactional(propagation=Propagation.REQUIRED)
	public void save_beforeAdvice(String opttype,boolean ifLog,long sapclient,String user,Class modelClass,Class logModelClass,Object object) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(ifLog){
			String msg = "";
			String opt = CommonServiceWithLog.OPT_ADD;
			String optflag = CommonServiceWithLog.OPTFLAG_SUCCESS;
			if(object!=null){
				Object obj = logModelClass.getConstructor(modelClass,long.class,String.class,String.class,String.class,String.class,String.class,Date.class).newInstance(object,sapclient,msg,opt,optflag,opttype,user,new Date());
				commonDao.save(obj);
			}
		}
	}
	@Before(value="execution(* com.sapGarden.application.commons.service.commonService.impl.CommonServiceImpl.update(..)) && args(opttype,ifLog,sapclient,user,modelClass,logModelClass,object)",argNames= "opttype,ifLog,sapclient,user,modelClass,logModelClass,object")
	@Transactional(propagation=Propagation.REQUIRED)
	public void update_beforeAdvice(String opttype,boolean ifLog,long sapclient,String user,Class modelClass,Class logModelClass,Object object) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(ifLog){
			String msg = "";
			String opt = CommonServiceWithLog.OPT_UPDATE;
			String optflag = CommonServiceWithLog.OPTFLAG_SUCCESS;
			if(object!=null){
				Object obj = logModelClass.getConstructor(modelClass,long.class,String.class,String.class,String.class,String.class,String.class,Date.class).newInstance(object,sapclient,msg,opt,optflag,opttype,user,new Date());
				commonDao.save(obj);
			}
		}
	}
	@Before(value="execution(* com.sapGarden.application.commons.service.commonService.impl.CommonServiceImpl.removeById(..)) && args(opttype,ifLog,sapclient,user,modelClass,logModelClass,id)",argNames= "opttype,ifLog,sapclient,user,modelClass,logModelClass,id")
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeById_beforeAdvice(String opttype,boolean ifLog,long sapclient,String user,Class modelClass,Class logModelClass,long id) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(ifLog){
			String msg = "";
			String opt = CommonServiceWithLog.OPT_DELETE;
			String optflag = CommonServiceWithLog.OPTFLAG_SUCCESS;
			Object object = commonDao.findById(modelClass, id);
			if(object!=null){
				Object obj = logModelClass.getConstructor(modelClass,long.class,String.class,String.class,String.class,String.class,String.class,Date.class).newInstance(object,sapclient,msg,opt,optflag,opttype,user,new Date());
				commonDao.save(obj);
			}
		}
	}
	*/
}
