package com.sapGarden.system.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.system.spring.BeanFactoryAwareBean;
/**
 * 动态注册数据库连接到spring容器中
 * @author Administrator
 *
 */
public class RegisterConnection{

	public synchronized static void registerConnection(List<SapDataCollection> list) {
		// TODO Auto-generated method stub
		//注册bean
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
				if(beanFactory.containsBean(beanId)){
					DriverManagerDataSource obj = (DriverManagerDataSource)beanFactory.getBean(beanId);
					obj.setUrl(url);
					obj.setUsername(username);
					obj.setPassword(password);
				}
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
