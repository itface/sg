package com.sapGarden.application.commons.service.commonService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.sf.json.JSONObject;

public interface TempCommonService {

	//public List findBySapclientAndFilters(int pageNumber,int rowsPerPage,SapClientModel sapclient,String filters);
	public JSONObject findJsonBySapclientAndFilters(Class modelClass,int pageNumber,int rowsPerPage,long sapclient,String filters);
	public long countBySapclient(Class modelClass,long sapclient);
	public void save(String opttype,boolean ifLog,long sapclient,String user,Class modelClass,Class logModelClass,Object object);
	public void saveList(String opttype,boolean ifLog,long sapclient,String user,Class modelClass,Class logModelClass,List list);
	public void update(String opttype,boolean ifLog,long sapclient,String user,Class modelClass,Class logModelClass,Object object);
	public void updateList(String opttype,boolean ifLog,long sapclient,String user,Class modelClass,Class logModelClass,List list,List logList);
	public void removeById(String opttype,boolean ifLog,long sapclient,String user,Class modelClass,Class logModelClass,long id);
	public void removeList(String opttype,boolean ifLog,long sapclient,String user,Class modelClass,Class logModelClass,List list);
	public void deleteBySapclient(Class modelClass,long sapclient);
	public void updateGardenFlag(Class modelClass,int value,long sapclient);
	public List findByIds(Class modelClass, String ids);
	public List queryBySql(Class modelClass,String sql);
	public void updateBySql(String sql);
	public List findBySapclient(Class modelClass,long sapclient);
	public String findSimpleGridDataBySapclient(Class modelClass,long sapclient)throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException ;
	//public void saveOne(Object obj);
	//public void removeAll(SapClientModel sapclient);
	//public void removeById(long id);
}
