package com.sapGarden.system.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.dataCollection.service.SapDataCollectionService;
import com.sapGarden.system.spring.BeanFactoryAwareBean;


@Service
public class DynamicDb implements ApplicationContextAware,ApplicationListener {

	private ApplicationContext app;
	@Override
	public void setApplicationContext(ApplicationContext app)throws BeansException {
		this.app = app;
	}
	public void onApplicationEvent(ApplicationEvent event) {  
		//如果是容器刷新事件  
		if(event instanceof ContextRefreshedEvent ){//如果是容器关闭事件  
			regDynamicBean();
			//System.out.println(event.getClass().getSimpleName()+" 事件已发生！");
		}
	}  
	private void regDynamicBean(){
		SapDataCollectionService sapDataCollectionService = (SapDataCollectionService)app.getBean("sapDataCollectionService");
		List<SapDataCollection> list = sapDataCollectionService.findAll();
		if(list!=null&&list.size()>0){
			DefaultListableBeanFactory beanFactory = BeanFactoryAwareBean.getDefaultListableBeanFactory();
			Map map = new HashMap();
			for(SapDataCollection sapDataCollection : list){
				String beanId = "dataSource"+sapDataCollection.getId();
				String driverClassName = "oracle.jdbc.driver.OracleDriver";
				String url = "jdbc:oracle:thin:@"+sapDataCollection.getGardendbip()+":1521:"+sapDataCollection.getGardendbinstance();
				String username = sapDataCollection.getGardendbusername();
				String password = sapDataCollection.getGardendbpassword();
				BeanDefinitionBuilder userBeanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(org.springframework.jdbc.datasource.DriverManagerDataSource.class);  
				userBeanDefinitionBuilder.addPropertyValue("driverClassName",driverClassName );  
				userBeanDefinitionBuilder.addPropertyValue("url", url);  
				userBeanDefinitionBuilder.addPropertyValue("username",username );  
				userBeanDefinitionBuilder.addPropertyValue("password",password );
//				if(beanFactory.containsBean(beanId)){
//					beanFactory.removeBeanDefinition(beanId);
//					DriverManagerDataSource obj = (DriverManagerDataSource)beanFactory.getBean(beanId);
//					beanFactory.destroyBean(beanId, obj);
//				}
				//beanFactory.destroyBean(beanId, obj);
				beanFactory.registerBeanDefinition(beanId,userBeanDefinitionBuilder.getBeanDefinition());
				/*
				if(beanFactory.containsBean(beanId)){
					DriverManagerDataSource obj = (DriverManagerDataSource)beanFactory.getBean(beanId);
					obj.setUrl(url);
					obj.setUsername(username);
					obj.setPassword(password);
				}else{
					beanFactory.registerBeanDefinition(beanId,userBeanDefinitionBuilder.getBeanDefinition());
				}*/
				map.put(beanId, beanFactory.getBean(beanId));
			}
			//取得bean，并修改属性
			AbstractRoutingDataSource dataSource = (AbstractRoutingDataSource)beanFactory.getBean("dataSource");
			//map.put("defaultDataSource", beanFactory.getBean("defaultDataSource"));
			dataSource.setTargetDataSources(map);
			//修改完bean属性，需要执行afterPropertiesSet生效
			dataSource.afterPropertiesSet();
		}
	}
}

