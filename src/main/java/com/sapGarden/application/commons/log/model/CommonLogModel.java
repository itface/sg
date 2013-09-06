package com.sapGarden.application.commons.log.model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public  class CommonLogModel {
	
	public  void initCommonLogModel(Object obj,Object oldObj,Class modelClass,Class logClass,long sapclient,String optmsg,String opt,String optflag,String opttype,String optuser,Date opttime) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Field[] fields = modelClass.getDeclaredFields();
		Method method = logClass.getMethod("setSapclient", new Class[]{long.class});
		method.invoke(this, new Object[]{sapclient});
		method = logClass.getMethod("setOpt", new Class[]{String.class});
		method.invoke(this, new Object[]{opt});
		method = logClass.getMethod("setOptmsg", new Class[]{String.class});
		method.invoke(this, new Object[]{optmsg});
		method = logClass.getMethod("setOpttime", new Class[]{Date.class});
		method.invoke(this, new Object[]{opttime});
		method = logClass.getMethod("setOptuser", new Class[]{String.class});
		method.invoke(this, new Object[]{optuser});
		method = logClass.getMethod("setOpttype", new Class[]{String.class});
		method.invoke(this, new Object[]{opttype});
		method = logClass.getMethod("setOptflag", new Class[]{String.class});
		method.invoke(this, new Object[]{optflag});
		if(obj!=null){
			for(int j=0;j<fields.length;j++){
				String name = fields[j].getName();
				if(!name.equals("serialVersionUID")&&!name.equals("id")&&!name.equals("sapclient")&&!name.equals("garden_flag")){
					Class fieldType = fields[j].getType();
					String getMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
					Method getMethod = modelClass.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(obj, new Object[]{});
					String methodName = "set"+name.substring(0, 1).toUpperCase()+name.substring(1);
					method = logClass.getMethod(methodName, new Class[]{fieldType});
					method.invoke(this, new Object[]{value});
				}
			}
		}
		if(oldObj!=null){
			method = modelClass.getMethod("getId", new Class[]{});
			Object id = method.invoke(oldObj, new Object[]{});
			method = logClass.getMethod("setOptid", new Class[]{long.class});
			method.invoke(this, new Object[]{id});
			for(int j=0;j<fields.length;j++){
				String name = fields[j].getName();
				if(!name.equals("serialVersionUID")&&!name.equals("id")&&!name.equals("sapclient")&&!name.equals("garden_flag")){
					Class fieldType = fields[j].getType();
					String getMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
					Method getMethod = modelClass.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(oldObj, new Object[]{});
					String methodName = "set"+name.substring(0, 1).toUpperCase()+name.substring(1)+"_old";
					method = logClass.getMethod(methodName, new Class[]{fieldType});
					method.invoke(this, new Object[]{value});
				}
			}
		}
	}
}
