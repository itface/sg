package com.sapGarden.system.startUp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.dataCollection.service.SapDataCollectionService;
import com.sapGarden.system.startUp.service.StartUpInitialService;
import com.sapGarden.system.startUp.service.impl.InitialSchedulingServiceImpl;
import com.sapGarden.system.startUp.service.impl.SapclientCollections;
/**
 * spring容器构建完时调用，即启动平台时调用
 * @author Administrator
 *
 */
@Service
public class StartUp implements ApplicationListener<ContextRefreshedEvent>{


	@Autowired
	private InitialSchedulingServiceImpl initialSchedulingServiceImpl;
	@Autowired
	private SapDataCollectionService sapDataCollectionService;
	private List<StartUpInitialService> startUpInitialServiceList;
	public void setStartUpInitialServiceList(List<StartUpInitialService> startUpInitialServiceList){
		this.startUpInitialServiceList=startUpInitialServiceList;
	}
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		// TODO Auto-generated method stub
		if(startUpInitialServiceList!=null){
			for(StartUpInitialService startUpInitialService:startUpInitialServiceList){
				new Thread(startUpInitialService).start();
			}
		}
		List<SapDataCollection> list = sapDataCollectionService.findAll();
		if(list!=null&&list.size()>0){
			SapclientCollections.add(list);
			if(SapclientCollections.length()>0){
				int length = SapclientCollections.length();
				for(int i=1;i<=length;i++){
					new Thread(initialSchedulingServiceImpl).start();
					//等待一分钟后再初始化其它sapclient的定时任务,防止产生冲突
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}
