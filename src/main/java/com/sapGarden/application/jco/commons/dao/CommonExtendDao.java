package com.sapGarden.application.jco.commons.dao;

import java.util.List;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public interface CommonExtendDao {

	public List findByCondition(SapDataCollection sapDataCollection,String filters,String extendCondition);
}
