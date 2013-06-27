package com.sapGarden.application.commons.service.constructJqgridService;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;


public interface Common_ConstructCompareGrid_Service {

	public String getTableHeaderHtml(SapDataCollection sapDataCollection,String tableName);
	public long getTableWidth(SapDataCollection sapDataCollection,String tableName);

}
