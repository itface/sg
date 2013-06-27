package com.sapGarden.application.commons.log.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.constants.LogConstants;
import com.sapGarden.application.commons.dao.CommonDao;
import com.sapGarden.application.commons.log.CommonServiceLog;
@Aspect
@Service("commonServiceLog_NotException")
public class CommonServiceLog_NotExceptionImpl implements CommonServiceLog{

	private CommonDao commonDao;
	@Autowired
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	@Before(value="execution(* com.sapGarden.application.commons.service.commonService.impl.CommonServiceImpl.saveList(..)) && args(opttype,ifLog,sapclient,user,modelClass,logModelClass,list)",argNames= "opttype,ifLog,sapclient,user,modelClass,logModelClass,list")
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveList_beforeAdvice(String opttype,boolean ifLog,long sapclient,String user,Class modelClass,Class logModelClass,List list) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(ifLog){
			String msg = "";
			String opt = LogConstants.OPT_ADD;
			String optflag = LogConstants.OPTFLAG_SUCCESS;
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
			String opt = LogConstants.OPT_ADD;
			String optflag = LogConstants.OPTFLAG_SUCCESS;
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
			String opt = LogConstants.OPT_UPDATE;
			String optflag = LogConstants.OPTFLAG_SUCCESS;
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
			String opt = LogConstants.OPT_DELETE;
			String optflag = LogConstants.OPTFLAG_SUCCESS;
			Object object = commonDao.findById(modelClass, id);
			if(object!=null){
				Object obj = logModelClass.getConstructor(modelClass,long.class,String.class,String.class,String.class,String.class,String.class,Date.class).newInstance(object,sapclient,msg,opt,optflag,opttype,user,new Date());
				commonDao.save(obj);
			}
		}
	}
	/*
	@AfterReturning(value="execution(* com.sapGarden.application.commons.service.commonService.impl.CommonServiceImpl.updateList(..)) && args(opttype,ifLog,sapclient,user,dao,modelClass,logDaoClass,logModelClass,list,logList)",argNames= "opttype,ifLog,sapclient,user,dao,modelClass,logDaoClass,logModelClass,list,logList")
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateList_afterThrowingAdvice(String opttype,boolean ifLog,long sapclient,String user,PagingAndSortingRepository dao,Class modelClass,Class logDaoClass,Class logModelClass,List list,List logList) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(ifLog){
			PagingAndSortingRepository logDao = (PagingAndSortingRepository)beanUtils.getBeanByType(logDaoClass);
			String optmsg = "";
			String opt = LogConstants.OPT_UPDATE;
			String optflag = LogConstants.OPTFLAG_SUCCESS;
			if(logList!=null&&logList.size()>0){
				List tempLogList = new LinkedList();
				for(int i=0;i<logList.size();i++){
					Object obj = logList.get(i);
					Method method = logModelClass.getDeclaredMethod("setId", new Class[]{long.class});
					method.invoke(obj, new Object[]{0});
					method = logModelClass.getDeclaredMethod("setSapclient", new Class[]{long.class});
					method.invoke(obj, new Object[]{sapclient});
					method = logModelClass.getDeclaredMethod("setOpt", new Class[]{String.class});
					method.invoke(obj, new Object[]{opt});
					method = logModelClass.getDeclaredMethod("setOptflag", new Class[]{String.class});
					method.invoke(obj, new Object[]{optflag});
					method = logModelClass.getDeclaredMethod("setOptmsg", new Class[]{String.class});
					method.invoke(obj, new Object[]{optmsg});
					method = logModelClass.getDeclaredMethod("setOpttime", new Class[]{Date.class});
					method.invoke(obj, new Object[]{new Date()});
					method = logModelClass.getDeclaredMethod("setOptuser", new Class[]{String.class});
					method.invoke(obj, new Object[]{user});
					method = logModelClass.getDeclaredMethod("setOpttype", new Class[]{String.class});
					method.invoke(obj, new Object[]{opttype});
					tempLogList.add(obj);
				}
				logDao.save(tempLogList);
			}else{
				Object obj = logModelClass.getConstructor().newInstance();
				Method method = logModelClass.getDeclaredMethod("setSapclient", new Class[]{long.class});
				method.invoke(obj, new Object[]{sapclient});
				method = logModelClass.getDeclaredMethod("setOpt", new Class[]{String.class});
				method.invoke(obj, new Object[]{opt});
				method = logModelClass.getDeclaredMethod("setOptflag", new Class[]{String.class});
				method.invoke(obj, new Object[]{optflag});
				method = logModelClass.getDeclaredMethod("setOptmsg", new Class[]{String.class});
				method.invoke(obj, new Object[]{optmsg});
				method = logModelClass.getDeclaredMethod("setOpttime", new Class[]{Date.class});
				method.invoke(obj, new Object[]{new Date()});
				method = logModelClass.getDeclaredMethod("setOptuser", new Class[]{String.class});
				method.invoke(obj, new Object[]{user});
				method = logModelClass.getDeclaredMethod("setOpttype", new Class[]{String.class});
				method.invoke(obj, new Object[]{opttype});
				logDao.save(obj);
			}
		}
	}
	@AfterReturning(value="execution(* com.sapGarden.application.commons.service.commonService.impl.CommonServiceImpl.removeList(..)) && args(opttype,ifLog,sapclient,user,dao,modelClass,logDaoClass,logModelClass,list)",argNames= "opttype,ifLog,sapclient,user,dao,modelClass,logDaoClass,logModelClass,list")
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeList_afterThrowingAdvice(String opttype,boolean ifLog,long sapclient,String user,PagingAndSortingRepository dao,Class modelClass,Class logDaoClass,Class logModelClass,List list) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(ifLog){
			PagingAndSortingRepository logDao = (PagingAndSortingRepository)beanUtils.getBeanByType(logDaoClass);
			String msg = "";
			String opt = LogConstants.OPT_DELETE;
			String optflag = LogConstants.OPTFLAG_SUCCESS;
			if(list!=null&&list.size()>0){
				List logList = new LinkedList();
				for(int i=0;i<list.size();i++){
					Object obj = logModelClass.getConstructor(modelClass,long.class,String.class,String.class,String.class,String.class,String.class,Date.class).newInstance(list.get(i),sapclient,msg,opt,optflag,opttype,user,new Date());
					logList.add(obj);
				}
				logDao.save(logList);
			}else{
				Object obj = logModelClass.getConstructor(modelClass,long.class,String.class,String.class,String.class,String.class,String.class,Date.class).newInstance(null,sapclient,msg,opt,optflag,opttype,user,new Date());
				logDao.save(obj);
			}
		}
	}
	*/
}
