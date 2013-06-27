package com.sapGarden.application.jco.commons.service;

import java.util.List;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public interface BasicService {

	public List find(SapDataCollection sapDataCollection,String extendCondition,String filters);
	public void saveList(List list);
	public void saveOne(Object obj);
	public void removeAll(SapDataCollection sapDataCollection);
	public void removeById(long id);
}
