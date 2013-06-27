package com.sapGarden.system.scheduling.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TestJobs extends QuartzJobBean{


	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException{
		// TODO Auto-generated method stub
		System.out.println("****************************************Job");
		//throw new JobExecutionException();
		//throw new RuntimeException("congratulations!!!"); 
	}

}
