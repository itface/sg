package com.sapGarden.application.commons.dao;

import java.util.List;

public interface CommonLogDao {

	public long countSuccess(Class modelClass,long sapclient);
	public long countError(Class modelClass,long sapclient);
	public void updateProcessstatusByIds(Class modelClass,long sapclient,String ids);
	public List findSuccessLogBySapclientAndCondition(Class modelClass,int pageNumber,int rowsPerPage,long sapclient,String filter);
	public List findErrorLogBySapclientAndCondition(Class modelClass,int pageNumber,int rowsPerPage,long sapclient,String filter);
}
