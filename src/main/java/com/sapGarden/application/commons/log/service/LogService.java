package com.sapGarden.application.commons.log.service;

import java.lang.reflect.InvocationTargetException;


public interface LogService {

	public void logSave(String opttype,long sapclient,String user,Class modelClass,Class logModelClass,Object object)throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	public void logUpdate(String opttype,long sapclient,String user,Class modelClass,Class logModelClass,Object object,Object oldObject)throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	public void logDelete(String opttype,long sapclient,String user,Class modelClass,Class logModelClass,Object object)throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	
}
