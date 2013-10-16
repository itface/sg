package com.sapGarden.application.commons.runtime.columninfo.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dao.ExtendDao;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.runtime.columninfo.model.JqgirdNewRuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.service.Runtime_ColumnInfo_Service;
import com.sapGarden.global.jqgrid.model.Jqgrid_DataJson;
import com.sapGarden.system.db.DbContextHolder;

@Service
public  class Runtime_ColumnInfo_ServiceImpl implements Runtime_ColumnInfo_Service{

	@Autowired
	private ExtendDao<RuntimeColumnInfo> extendDao;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addOne(JqgirdNewRuntimeColumnInfo jqgirdNewRuntimeColumnInfo) {
		// TODO Auto-generated method stub
		RuntimeColumnInfo dbReflectOfColumn = new RuntimeColumnInfo(jqgirdNewRuntimeColumnInfo);
		extendDao.save(dbReflectOfColumn);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteOne(long id){
		// TODO Auto-generated method stub
		extendDao.deleteById(RuntimeColumnInfo.class, id);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public JSONObject findJsonByBusinesstype(int pageNumber,int rowsPerPage,String sidx,String sord,SapDataCollection sapDataCollection,String businesstype){
		// TODO Auto-generated method stub
		//Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "id"));
		String orderby = "";
		if(sidx!=null&&!"".equals(sidx)){
			orderby=" order by "+sidx+" "+sord;
		}
		List<RuntimeColumnInfo> list = extendDao.findByPage("from RuntimeColumnInfo t where t.businesstype=? and t.sapclient=? "+orderby, new Object[]{businesstype,sapDataCollection.getId()}, pageNumber, rowsPerPage);
		long totalRows = extendDao.findTotalCount("select count(t.id) from RuntimeColumnInfo t where t.businesstype=? and t.sapclient=?", new Object[]{businesstype,sapDataCollection.getId()});
		Jqgrid_DataJson jsonData = new Jqgrid_DataJson(pageNumber,rowsPerPage,totalRows,list);
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		return jsonObject;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateOne(JqgirdNewRuntimeColumnInfo jqgirdNewRuntimeColumnInfo){
		// TODO Auto-generated method stub
		RuntimeColumnInfo dbReflectOfColumn = new RuntimeColumnInfo(jqgirdNewRuntimeColumnInfo);
		extendDao.save(dbReflectOfColumn);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<RuntimeColumnInfo> findByBusinesstype(int pageNumber,int rowsPerPage,SapDataCollection sapDataCollection,String businesstype){
		// TODO Auto-generated method stub
		List<RuntimeColumnInfo> list = extendDao.findByPage("from RuntimeColumnInfo t where t.businesstype=? and t.sapclient=?", new Object[]{businesstype,sapDataCollection.getId()}, pageNumber, rowsPerPage);
		return list;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateStatusById(long id, String status) {
		// TODO Auto-generated method stub
		RuntimeColumnInfo runtimeColumnInfo = extendDao.find(RuntimeColumnInfo.class, id);
		runtimeColumnInfo.setStatus(status);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<RuntimeColumnInfo> findAllActiveData(SapDataCollection sapDataCollection, String businesstype) {
		// TODO Auto-generated method stub
		List<RuntimeColumnInfo> list = extendDao.find("from RuntimeColumnInfo t where t.businesstype=? and t.sapclient=? and t.status='ACTIVATE'", new Object[]{businesstype,sapDataCollection.getId()});//runtimeColumnInfoDao.findAllActiveData(businesstype,client.getId());
		return list;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addList(List<RuntimeColumnInfo> list) {
		// TODO Auto-generated method stub
		extendDao.saveList(list);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<RuntimeColumnInfo> findAll(SapDataCollection sapDataCollection,String businesstype) {
		// TODO Auto-generated method stub
		List<RuntimeColumnInfo> list = extendDao.find("from RuntimeColumnInfo t where t.businesstype=? and t.sapclient=?", new Object[]{businesstype,sapDataCollection.getId()});//runtimeColumnInfoDao.findAllActiveData(businesstype,client.getId());
		return list;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteByTableAndSapclient(String tablename, long sapclient) {
		// TODO Auto-generated method stub
		extendDao.executeUpdate("delete from  RuntimeColumnInfo t where t.businesstype=? and t.sapclient=?", new Object[]{tablename,sapclient});
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public long countByTableAndSapclient(String tablename, long sapclient) {
		// TODO Auto-generated method stub
		long totalRows = extendDao.findTotalCount("select count(t.id) from RuntimeColumnInfo t where t.businesstype=? and t.sapclient=?", new Object[]{tablename,sapclient});
		return totalRows;
	}

}
