package com.sapGarden.application.commons.service.commonService;

import net.sf.json.JSONObject;

public interface TempCommonLogService{

	public void updateProcessstatusByIds(Class modelClass,long sapclient,String ids);
	public JSONObject findSuccessLogBySapclientAndFilters(Class modelClass,int pageNumber,int rowsPerPage,long sapclient,String filters);
	public JSONObject findErrorLogBySapclientAndFilters(Class modelClass,int pageNumber,int rowsPerPage,long sapclient,String filters);
	/*
	public void saveList(List list);
	public void saveOne(Object obj);
	public void removeAll(SapClientModel sapclient);
	public void removeById(long id);
	*/
}
