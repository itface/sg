package com.sapGarden.application.commons.testConnection.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import com.sap.mw.jco.JCO;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.dataCollection.service.SapDataCollectionService;
import com.sapGarden.application.commons.jco.JcoConnectionPool;
import com.sapGarden.application.commons.testConnection.service.TestConnectionService;
import com.sapGarden.system.spring.MyApplicationContextUtil;
@Service
public class TestConnectionServiceImpl implements TestConnectionService{

	@Autowired
	@Qualifier("myApplicationContextUtil")
	private MyApplicationContextUtil myApplicationContextUtil;
	@Autowired
	private SapDataCollectionService sapDataCollectionService;
	@Override
	public String testConnection(long id) {
		// TODO Auto-generated method stub
		String msg = null;
		if(!this.testConnectDb(id)){
			msg="数据库连接失败";
		}else if(!this.testConnectSap(id)){
			msg="SAP连接失败";
		}
		return msg;
	}

	private boolean testConnectDb(long id){
		ApplicationContext context = myApplicationContextUtil.getApplicationContext();
		Object obj = context.getBean("dataSource"+id);
		if(obj!=null){
			DriverManagerDataSource obj2 = (DriverManagerDataSource)obj;
			try {
				Connection conn = obj2.getConnection();
				if(conn==null){
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return false;
			}
		}
		return true;
	}
	private boolean testConnectSap(long id){
		try {
			SapDataCollection sapDataCollection = sapDataCollectionService.findById(id);
			String poolName1 = JcoConnectionPool.getPoolName(sapDataCollection);
			if(JCO.getClient(poolName1)==null){
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
}
