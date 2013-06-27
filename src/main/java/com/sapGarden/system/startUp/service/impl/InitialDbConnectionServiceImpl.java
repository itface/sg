package com.sapGarden.system.startUp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.dataCollection.service.SapDataCollectionService;
import com.sapGarden.system.db.RegisterConnection;
import com.sapGarden.system.startUp.service.StartUpInitialService;

/**
 * 动态注册数据库连接
 * @author Administrator
 *
 */
@Service("initialDbConnectionService")
public class InitialDbConnectionServiceImpl implements StartUpInitialService{

	private final static transient Logger log = LoggerFactory.getLogger("errorLog");
	@Autowired
	private SapDataCollectionService sapDataCollectionService;
	@Override
	//@Transactional(propagation = Propagation.REQUIRED)事务是有效的
	public void run() {
		// TODO Auto-generated method stub
		List<SapDataCollection> list = sapDataCollectionService.findAll();
		RegisterConnection.registerConnection(list);
	}

}
