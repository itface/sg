package com.sapGarden.application.commons.service.commonService.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dao.CommonDao;
import com.sapGarden.application.commons.service.commonService.TempCommonService;
import com.sapGarden.application.fi.customer.model.Kna1;
import com.sapGarden.global.jqgrid.model.Jqgrid_DataJson;
import com.sapGarden.global.json.JsonUtils;
@Service
@Order(2)
public class CommonServiceImpl implements TempCommonService{


	private CommonDao commonDao;
	@Autowired
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public JSONObject findJsonBySapclientAndFilters(Class modelClass,int pageNumber, int rowsPerPage, long sapclient,String filters) {
		// TODO Auto-generated method stub
		List<Kna1> list = commonDao.findBySapclientAndCondition(modelClass.getName(),pageNumber, rowsPerPage, sapclient, filters);
		long count = commonDao.count(modelClass,sapclient);
		Jqgrid_DataJson jsonData = new Jqgrid_DataJson(pageNumber,rowsPerPage,count,list);
		JSONObject json = JsonUtils.objectToJSONObject(jsonData,null);
		return json;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveList(String opttype,boolean ifLog,long sapclient,String user,Class modelClass,Class logModelClass,List list){
		// TODO Auto-generated method stub
		commonDao.save(list);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeList(String opttype,boolean ifLog,long sapclient,String user,Class modelClass,Class logModelClass,List list) {
		// TODO Auto-generated method stub
		commonDao.delete(list);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateList(String opttype, boolean ifLog, long sapclient,String user,Class modelClass,Class logModelClass, List list,List logList) {
		// TODO Auto-generated method stub
		commonDao.save(list);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteBySapclient(Class modelClass, long sapclient) {
		// TODO Auto-generated method stub
		commonDao.deleteBySapclient(modelClass, sapclient);
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public long countBySapclient(Class modelClass, long sapclient) {
		// TODO Auto-generated method stub
		return commonDao.count(modelClass, sapclient);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateGardenFlag(Class modelClass, int value, long sapclient) {
		// TODO Auto-generated method stub
		commonDao.updateGardenFlag(sapclient, modelClass, value);
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List findByIds(Class modelClass, String ids) {
		// TODO Auto-generated method stub
		return commonDao.findByIds(modelClass, ids);
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List queryBySql(Class modelClass,String sql) {
		// TODO Auto-generated method stub
		return commonDao.queryBySql(modelClass,sql);
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List findBySapclient(Class modelClass, long sapclient) {
		// TODO Auto-generated method stub
		return commonDao.findBySapclient(modelClass, sapclient);
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public String findSimpleGridDataBySapclient(Class modelClass, long sapclient) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		List list = commonDao.findBySapclient(modelClass, sapclient);
		Field[] fields = modelClass.getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		sb.append("{list:[");
		for(int i=0;i<list.size();i++){
			sb.append("{");
			Object obj = list.get(i);
			for(int j=0;j<fields.length;j++){
				String name = fields[j].getName();
				if(!name.equals("serialVersionUID")){
					String getMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
					Method getMethod = modelClass.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(obj, new Object[]{});
					if(value==null){
						value="";
					}
					sb.append(name).append(":'").append(value).append("',");
				}
			}
			sb.deleteCharAt(sb.lastIndexOf(","));
			sb.append("},");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("]");
		return sb.toString();
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateBySql(String sql) {
		// TODO Auto-generated method stub
		commonDao.updateBySql(sql);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(String opttype, boolean ifLog, long sapclient,
			String user, Class modelClass, Class logModelClass, Object object) {
		// TODO Auto-generated method stub
		commonDao.save(object);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeById(String opttype, boolean ifLog, long sapclient,String user, Class modelClass, Class logModelClass, long id) {
		// TODO Auto-generated method stub
		commonDao.deleteById(modelClass, id);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(String opttype, boolean ifLog, long sapclient,String user, Class modelClass, Class logModelClass, Object object) {
		// TODO Auto-generated method stub
		commonDao.update(object);
	}

}
