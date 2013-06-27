package com.sapGarden.application.commons.service.constructJqgridService;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;


public interface Common_ConstructJqgrid_Service{

	public String construct(SapDataCollection sapDataCollection,String tableName,String gridId,String gridCaption,String gridUrl,String pager);
}
