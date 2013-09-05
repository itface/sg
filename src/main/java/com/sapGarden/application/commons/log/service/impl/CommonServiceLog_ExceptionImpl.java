package com.sapGarden.application.commons.log.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.sapGarden.application.commons.log.model.CommonLogModel;
import com.sapGarden.application.commons.log.service.CommonServiceWithLog;
import com.sapGarden.application.commons.log.service.LogDaoService;
@Aspect
//@Order(value=Ordered.LOWEST_PRECEDENCE)
@Order(1)
@Component("commonServiceLog_Exception")
public class CommonServiceLog_ExceptionImpl{

	@Autowired
	private LogDaoService logDaoService;
	
	
	@AfterThrowing(value="execution(* com.sapGarden.application.commons.log.service.CommonServiceWithLog.addWithLog(..)) && args(opttype,sapclient,user,modelClass,logModelClass,object)",argNames= "exception,opttype,sapclient,user,modelClass,logModelClass,object",throwing = "exception")
	public void save_afterThrowingAdvice(Throwable exception,String opttype,long sapclient,String user,Class modelClass,Class logModelClass,Object object) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String msg = exception.toString();
		String opt = CommonServiceWithLog.OPT_ADD;
		String optflag = CommonServiceWithLog.OPTFLAG_FAIL;
		if(object!=null){
			Object logObj = logModelClass.getConstructor().newInstance();
			Method initCommonLogModelMethod = logModelClass.getMethod("initCommonLogModel", new Class[]{Object.class,Class.class,Class.class,long.class,String.class,String.class,String.class,String.class,String.class,Date.class});
			initCommonLogModelMethod.invoke(logObj, new Object[]{object,modelClass,logModelClass,sapclient,msg,opt,optflag,opttype,user,new Date()});
			logDaoService.add(logObj);
		}
	}
	@AfterThrowing(value="execution(* com.sapGarden.application.commons.log.service.CommonServiceWithLog.updateWithLog(..)) && args(opttype,sapclient,user,modelClass,logModelClass,object)",argNames= "exception,opttype,sapclient,user,modelClass,logModelClass,object",throwing = "exception")
	public void update_afterThrowingAdvice(Throwable exception,String opttype,long sapclient,String user,Class modelClass,Class logModelClass,Object object) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String msg = exception.toString();
		String opt = CommonServiceWithLog.OPT_UPDATE;
		String optflag = CommonServiceWithLog.OPTFLAG_FAIL;
		if(object!=null){
			Object logObj = logModelClass.getConstructor().newInstance();
			Method initCommonLogModelMethod = logModelClass.getMethod("initCommonLogModel", new Class[]{Object.class,Class.class,Class.class,long.class,String.class,String.class,String.class,String.class,String.class,Date.class});
			initCommonLogModelMethod.invoke(logObj, new Object[]{object,modelClass,logModelClass,sapclient,msg,opt,optflag,opttype,user,new Date()});
			logDaoService.add(logObj);
		}
	}
	@AfterThrowing(value="execution(* com.sapGarden.application.commons.log.service.CommonServiceWithLog.deleteWithLog(..)) && args(opttype,sapclient,user,modelClass,logModelClass,object)",argNames= "exception,opttype,sapclient,user,modelClass,logModelClass,object",throwing = "exception")
	public void delete_afterThrowingAdvice(Throwable exception,String opttype,long sapclient,String user,Class modelClass,Class logModelClass,Object object) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String msg = exception.toString();
		String opt = CommonServiceWithLog.OPT_DELETE;
		String optflag = CommonServiceWithLog.OPTFLAG_FAIL;
		Object logObj = logModelClass.getConstructor().newInstance();
		Method initCommonLogModelMethod = logModelClass.getMethod("initCommonLogModel", new Class[]{Object.class,Class.class,Class.class,long.class,String.class,String.class,String.class,String.class,String.class,Date.class});
		initCommonLogModelMethod.invoke(logObj, new Object[]{object,modelClass,logModelClass,sapclient,msg,opt,optflag,opttype,user,new Date()});
		logDaoService.add(logObj);
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
