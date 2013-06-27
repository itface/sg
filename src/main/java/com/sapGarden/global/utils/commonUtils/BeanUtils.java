package com.sapGarden.global.utils.commonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.sapGarden.system.spring.MyApplicationContextUtil;
@Component("beanUtils")
public class BeanUtils {

	private MyApplicationContextUtil myApplicationContextUtil;
	@Autowired
	public void setMyApplicationContextUtil(MyApplicationContextUtil myApplicationContextUtil) {
		this.myApplicationContextUtil = myApplicationContextUtil;
	}
	public  Object getBeanByName(String beanName){
		if(beanName!=null&&!"".equals(beanName.trim())){
			//ApplicationContext context = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
			ApplicationContext context = myApplicationContextUtil.getApplicationContext();
			Object bean = context.getBean(beanName);
			//context.getBeanNamesForType(arg0)
			return bean;
		}
		return null;
	}
	public  Object getBeanByType(Class type){
		ApplicationContext context = myApplicationContextUtil.getApplicationContext();
		//System.out.println(context.getBeanDefinitionNames());
		Object bean = context.getBean(type);
		if(bean!=null){
			bean = type.cast(bean);
		}
		return bean;
	}
}
