package com.sapGarden.application.commons.log.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.sapGarden.application.commons.log.service.CommonService;
import com.sapGarden.application.commons.log.service.ExceptionLogDaoService;
@Aspect
//@Order(value=Ordered.LOWEST_PRECEDENCE)
//@Order(1)
@Component("commonServiceLog_Exception")
public class CommonServiceLog_ExceptionImpl{

	@Autowired
	private ExceptionLogDaoService exceptionLogDaoService;
	
	
	@AfterThrowing(value="execution(* com.sapGarden.application.commons.log.service.CommonService.addWithLog(..)) && args(opttype,sapclient,user,modelClass,logModelClass,object)",argNames= "exception,opttype,sapclient,user,modelClass,logModelClass,object",throwing = "exception")
	public void save_afterThrowingAdvice(Throwable exception,String opttype,long sapclient,String user,Class modelClass,Class logModelClass,Object object) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String msg = exception.toString();
		String opt = CommonService.OPT_ADD;
		String optflag = CommonService.OPTFLAG_FAIL;
		if(object!=null){
			Object logObj = logModelClass.getConstructor().newInstance();
			Method initCommonLogModelMethod = logModelClass.getMethod("initCommonLogModel", new Class[]{Object.class,Object.class,Class.class,Class.class,long.class,String.class,String.class,String.class,String.class,String.class,Date.class});
			initCommonLogModelMethod.invoke(logObj, new Object[]{object,null,modelClass,logModelClass,sapclient,msg,opt,optflag,opttype,user,new Date()});
			exceptionLogDaoService.add(logObj);
		}
	}
	@AfterThrowing(value="execution(* com.sapGarden.application.commons.log.service.CommonService.updateWithLog(..)) && args(opttype,sapclient,user,modelClass,logModelClass,object,oldObject)",argNames= "exception,opttype,sapclient,user,modelClass,logModelClass,object,oldObject",throwing = "exception")
	public void update_afterThrowingAdvice(Throwable exception,String opttype,long sapclient,String user,Class modelClass,Class logModelClass,Object object,Object oldObject) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String msg = exception.toString();
		String opt = CommonService.OPT_UPDATE;
		String optflag = CommonService.OPTFLAG_FAIL;
		if(object!=null){
			Object logObj = logModelClass.getConstructor().newInstance();
			Method initCommonLogModelMethod = logModelClass.getMethod("initCommonLogModel", new Class[]{Object.class,Object.class,Class.class,Class.class,long.class,String.class,String.class,String.class,String.class,String.class,Date.class});
			initCommonLogModelMethod.invoke(logObj, new Object[]{object,oldObject,modelClass,logModelClass,sapclient,msg,opt,optflag,opttype,user,new Date()});
			exceptionLogDaoService.add(logObj);
		}
	}
	@AfterThrowing(value="execution(* com.sapGarden.application.commons.log.service.CommonService.deleteWithLog(..)) && args(opttype,sapclient,user,modelClass,logModelClass,object)",argNames= "exception,opttype,sapclient,user,modelClass,logModelClass,object",throwing = "exception")
	public void delete_afterThrowingAdvice(Throwable exception,String opttype,long sapclient,String user,Class modelClass,Class logModelClass,Object object) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String msg = exception.toString();
		String opt = CommonService.OPT_DELETE;
		String optflag = CommonService.OPTFLAG_FAIL;
		Object logObj = logModelClass.getConstructor().newInstance();
		Method initCommonLogModelMethod = logModelClass.getMethod("initCommonLogModel", new Class[]{Object.class,Object.class,Class.class,Class.class,long.class,String.class,String.class,String.class,String.class,String.class,Date.class});
		initCommonLogModelMethod.invoke(logObj,new Object[]{null,object,modelClass,logModelClass,sapclient,msg,opt,optflag,opttype,user,new Date()});
		exceptionLogDaoService.add(logObj);
	}

	/*
	@AfterThrowing(value="execution(* com.sapGarden.application.commons.service.commonService.impl.CommonServiceImpl.update(..)) && args(opttype,ifLog,sapclient,user,modelClass,logModelClass,object)",argNames= "exception,opttype,ifLog,sapclient,user,modelClass,logModelClass,object",throwing = "exception")
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void update_afterThrowingAdvice(Throwable exception,String opttype,boolean ifLog,long sapclient,String user,Class modelClass,Class logModelClass,Object object) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(ifLog){
			String msg = exception.getMessage()+(exception.getStackTrace())[0].toString();
			String opt = LogConstants.OPT_UPDATE;
			String optflag = LogConstants.OPTFLAG_FAIL;
			if(object!=null){
				Object obj = logModelClass.getConstructor(modelClass,long.class,String.class,String.class,String.class,String.class,String.class,Date.class).newInstance(object,sapclient,msg,opt,optflag,opttype,user,new Date());
				commonDao.save(obj);
			}
		}
	}
	@AfterThrowing(value="execution(* com.sapGarden.application.commons.service.commonService.impl.CommonServiceImpl.removeById(..)) && args(opttype,ifLog,sapclient,user,modelClass,logModelClass,id)",argNames= "exception,opttype,ifLog,sapclient,user,modelClass,logModelClass,id",throwing = "exception")
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void removeById_afterThrowingAdvice(Throwable exception,String opttype,boolean ifLog,long sapclient,String user,Class modelClass,Class logModelClass,long id) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(ifLog){
			String msg = exception.getMessage()+(exception.getStackTrace())[0].toString();
			String opt = LogConstants.OPT_DELETE;
			String optflag = LogConstants.OPTFLAG_FAIL;
			Object object = commonDao.findById(modelClass, id);
			if(object!=null){
				Object obj = logModelClass.getConstructor(modelClass,long.class,String.class,String.class,String.class,String.class,String.class,Date.class).newInstance(object,sapclient,msg,opt,optflag,opttype,user,new Date());
				commonDao.save(obj);
			}
		}
	}*/
}
