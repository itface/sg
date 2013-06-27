package com.sapGarden.application.fi.customer.service;

import java.lang.reflect.InvocationTargetException;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public interface ManualSynService {

	public void manualSyn(SapDataCollection sapDataCollection,String user,String kunnr)throws SecurityException, IllegalArgumentException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;
}
