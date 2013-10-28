package com.sapGarden.application.commons.log.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.baseDao.BaseDao;
import com.sapGarden.application.commons.log.service.CommonService;
import com.sapGarden.application.commons.log.service.LogService;
@Service
public class LogServiceImpl implements LogService{

	@Autowired
	private BaseDao dao;
	
	@Override
	@Transactional
	public void logSave(String opttype, long sapclient, String user,
			Class modelClass, Class logModelClass, Object object)throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		String msg = "";
		String opt = CommonService.OPT_ADD;
		String optflag = CommonService.OPTFLAG_SUCCESS;
		if(object!=null){
			Object logObj = logModelClass.getConstructor().newInstance();
			Method initCommonLogModelMethod = logModelClass.getMethod("initCommonLogModel", new Class[]{Object.class,Object.class,Class.class,Class.class,long.class,String.class,String.class,String.class,String.class,String.class,Date.class});
			initCommonLogModelMethod.invoke(logObj, new Object[]{object,null,modelClass,logModelClass,sapclient,msg,opt,optflag,opttype,user,new Date()});
			//logDaoService.add(logObj);
			dao.persist(logObj);
		}
	}

	@Override
	@Transactional
	public void logUpdate(String opttype,long sapclient,String user,Class modelClass,Class logModelClass,Object object,Object oldObject) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		String msg = "";
		String opt = CommonService.OPT_UPDATE;
		String optflag = CommonService.OPTFLAG_SUCCESS;
		if(object!=null){
			Object logObj = logModelClass.getConstructor().newInstance();
			Method initCommonLogModelMethod = logModelClass.getMethod("initCommonLogModel", new Class[]{Object.class,Object.class,Class.class,Class.class,long.class,String.class,String.class,String.class,String.class,String.class,Date.class});
			initCommonLogModelMethod.invoke(logObj, new Object[]{object,oldObject,modelClass,logModelClass,sapclient,msg,opt,optflag,opttype,user,new Date()});
			//logDaoService.add(logObj);
			dao.persist(logObj);
		}
	}

	@Override
	@Transactional
	public void logDelete(String opttype,long sapclient,String user,Class modelClass,Class logModelClass,Object object) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		String msg = "";
		String opt = CommonService.OPT_DELETE;
		String optflag = CommonService.OPTFLAG_SUCCESS;
		if(object!=null){
			Object logObj = logModelClass.getConstructor().newInstance();
			Method initCommonLogModelMethod = logModelClass.getMethod("initCommonLogModel", new Class[]{Object.class,Object.class,Class.class,Class.class,long.class,String.class,String.class,String.class,String.class,String.class,Date.class});
			initCommonLogModelMethod.invoke(logObj, new Object[]{null,object,modelClass,logModelClass,sapclient,msg,opt,optflag,opttype,user,new Date()});
			//logDaoService.add(logObj);
			dao.persist(logObj);
		}
	}

}
