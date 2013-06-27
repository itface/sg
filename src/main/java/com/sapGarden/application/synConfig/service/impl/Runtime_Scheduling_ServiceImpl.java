package com.sapGarden.application.synConfig.service.impl;

import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapGarden.application.jco.commons.constants.Constant;
import com.sapGarden.application.synConfig.service.Runtime_SchedulingService;
import com.sapGarden.global.utils.commonUtils.BeanUtils;
@Service
public class Runtime_Scheduling_ServiceImpl implements Runtime_SchedulingService{

	private BeanUtils beanUtils;
	@Autowired
	public void setBeanUtils(BeanUtils beanUtils) {
		this.beanUtils = beanUtils;
	}
	@Override
	public String getJobClassName(String type) {
		// TODO Auto-generated method stub
		Object constantObject = beanUtils.getBeanByName(type+"_constants");
		if(constantObject!=null){
			Constant constant = (Constant)constantObject;
			Job job  = constant.getJob();
			return job.getClass().getName();
		}
		return null;
	}

}
