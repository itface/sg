package com.sapGarden.application.commons.dataCollection.service;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sapGarden.application.commons.dataCollection.model.NewSapDataCollection;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public interface SapDataCollectionService {

	public JSONArray findCheckedTreeJson();
	public JSONObject findAllJson();
	public List<SapDataCollection> findAll();
	public SapDataCollection findById(long id);
	public String addOneByNewSapClient(NewSapDataCollection newSapDataCollection);
	public String updateOne(NewSapDataCollection newSapDataCollection);
	public void deleteOne(Long id);
	public long countByAlias(String alias);
	//public String findSapDataSelectSelect();
}
