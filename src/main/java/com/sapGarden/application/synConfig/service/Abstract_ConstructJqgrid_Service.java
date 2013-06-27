package com.sapGarden.application.synConfig.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sapGarden.global.utils.commonUtils.BeanUtils;

public abstract class Abstract_ConstructJqgrid_Service implements Common_ConstructJqgrid_Service{

	protected BeanUtils beanUtils;
	@Autowired
	public void setBeanUtils(BeanUtils beanUtils) {
		this.beanUtils = beanUtils;
	}
}
