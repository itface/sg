package com.sapGarden.system.startUp.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.runtime.scheduling.exception.SchedulingException;
import com.sapGarden.application.commons.runtime.scheduling.service.CommonSchedulingService;
import com.sapGarden.system.db.DbContextHolder;

@Service("initialSchedulingService")
public class InitialSchedulingServiceImpl implements Runnable{

	private final static transient Logger log = LoggerFactory.getLogger(InitialSchedulingServiceImpl.class);
	private CommonSchedulingService commonSchedulingService;
	@Autowired
	public InitialSchedulingServiceImpl(CommonSchedulingService commonSchedulingService){
		this.commonSchedulingService=commonSchedulingService;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//因为平台启动时要初努化所有sapclient，所以执行初始化之前要设置连的是哪个sapclient,每次取一个
		SapDataCollection sapclient = SapclientCollections.getNext();
		if(sapclient!=null){
			DbContextHolder.setDbType("dataSource"+sapclient.getId());
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String now = sd.format(Calendar.getInstance().getTime());
		try {
			commonSchedulingService.initAllJob(sapclient);
		} catch (SchedulingException e) {
			// TODO Auto-generated catch block
			log.error(now,e);
		}
	}

}
