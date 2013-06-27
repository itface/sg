package com.sapGarden.application.commons.dao;

import java.util.List;

public interface CommonDao {

	public long count(Class modelClass,long sapclient);
	public List queryBySql(Class modelClass,String sql);
	public List findByIds(Class modelClass,String ids);
	public List findBySapclient(Class modelClass,long sapclient);
	public List findBySapclientAndCondition(String modelName,int pageNumber,int rowsPerPage,long sapclient,String filter);
	public void deleteBySapclient(Class modelClass,long sapclient);
	public void updateGardenFlag(long sapclient,Class modelClass,int value);
	public void delete(Object object);
	public void save(Object object);
	public void deleteById(Class modleClass,long id);
	public void update(Object object);
	public void updateBySql(String sql);
	public Object findById(Class modleClass,long id);
}
