package com.sapGarden.application.commons.runtime.tableinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dao.ExtendDao;
import com.sapGarden.application.commons.runtime.tableinfo.model.RuntimeTableInfo;
import com.sapGarden.application.commons.runtime.tableinfo.service.Runtime_TableInfo_Service;

@Service
public class Runtime_TableInfo_ServiceImpl implements Runtime_TableInfo_Service{

	@Autowired
	private ExtendDao<RuntimeTableInfo> extendDao;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addOne(RuntimeTableInfo dbReflect) {
		// TODO Auto-generated method stub
		extendDao.save(dbReflect);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public RuntimeTableInfo findOneByBusinesstype(long sapclient,String businesstype) {
		// TODO Auto-generated method stub
		RuntimeTableInfo runtimeTableInfo=null;
		List<RuntimeTableInfo> list=extendDao.find("from RuntimeTableInfo t where t.businesstype=? and t.sapclient=?", new Object[]{businesstype,sapclient});
		if(list!=null&&list.size()>0){
			runtimeTableInfo=list.get(0);
		}
		return runtimeTableInfo;
		//return runtimeTableInfoDao.findOneByBusinesstypeAndSapclient(businesstype,sapclient);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateOne(RuntimeTableInfo runtimeTableInfo){
		// TODO Auto-generated method stub
		//runtimeTableInfoDao.save(runtimeTableInfo);
		extendDao.save(runtimeTableInfo);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteByBusinesstypeAndSapclient(String tablename, long sapclient) {
		// TODO Auto-generated method stub
		//runtimeTableInfoDao.deleteByBusinesstypeAndSapclient(tablename, sapclient);
		extendDao.executeUpdate("delete from  RuntimeTableInfo t where t.businesstype=? and t.sapclient=?", new Object[]{tablename,sapclient});
	}

	

}
