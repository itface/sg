package com.sapGarden.system.startUp;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.sapGarden.system.startUp.service.StartUpInitialService;
/**
 * spring容器构建完时调用，即启动平台时调用
 * @author Administrator
 *
 */
@Service
public class StartUp implements ApplicationListener<ContextRefreshedEvent>{

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
	}

}
