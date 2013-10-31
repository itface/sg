package com.sapGarden.application.commons.progress.service;

import java.lang.reflect.InvocationTargetException;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.progress.model.NewProgress;

public interface CommonInitDataService {

	public boolean validateTable();
	public void repairTable();
	public void init(SapDataCollection sapDataCollection,String user,long threadid,NewProgress progress)throws Exception;
}
