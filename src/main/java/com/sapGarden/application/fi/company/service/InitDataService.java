package com.sapGarden.application.fi.company.service;

import java.lang.reflect.InvocationTargetException;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.progress.model.NewProgress;

public interface InitDataService {

	public boolean validateTable();
	public void repairTable();
	public void init(SapDataCollection sapDataCollection,String user,long threadid,NewProgress progress)throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
