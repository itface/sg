package com.sapGarden.application.commons.runtime.scheduling.jobs;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.log.service.CommonService;
import com.sapGarden.application.fi.company.service.SynService;
import com.sapGarden.system.db.DbContextHolder;

public class JobOfCOMPANY extends QuartzJobBean{

	private final static transient Logger log = LoggerFactory.getLogger(JobOfCOMPANY.class);

	


	@Override
	protected void executeInternal(JobExecutionContext context)throws JobExecutionException {
		// TODO Auto-generated method stub
		//<!-- 因为job不是有spring容器代理，所以不能注入spring bean，可以通过这种方式用代码获取bean -->
		System.out.println("**************************执行公司代码同步定时任务开始*************************************");
		ApplicationContext springContext =  WebApplicationContextUtils.getWebApplicationContext(ContextLoaderListener.getCurrentWebApplicationContext().getServletContext());
		SynService synService = (SynService) springContext.getBean("company_SynService");
		Object o = context.getJobDetail().getJobDataMap().get("owner");
		String owner = "";
		if(o!=null){
			owner = (String)o;
		}
		o = context.getJobDetail().getJobDataMap().get("sapclient");
		SapDataCollection sapDataCollection = null;
		if(o!=null){
			sapDataCollection = (SapDataCollection)o;
		}
		//因为job是单独起的线程，所以需要设置datasource才能连上正确的库
		DbContextHolder.setDbType("dataSource"+sapDataCollection.getId());
		try {
			synService.syn(sapDataCollection,CommonService.OPTTYPE_SCHEDULING,owner,CommonService.IFLOG_YES);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String now = sd.format(Calendar.getInstance().getTime());
			 log.error(now, e);
		}
		System.out.println("**************************执行公司代码同步定时任务结束*************************************");
	}
	

	
}
