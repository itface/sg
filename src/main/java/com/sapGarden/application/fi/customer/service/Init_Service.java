package com.sapGarden.application.fi.customer.service;

import java.lang.reflect.InvocationTargetException;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public interface Init_Service {

	public void removeAll(SapDataCollection sapDataCollection);
	public void init(SapDataCollection sapDataCollection,String user) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException;
	//public HashMap<String,Object> call(SapClientModel sapclient)throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException ;
}
