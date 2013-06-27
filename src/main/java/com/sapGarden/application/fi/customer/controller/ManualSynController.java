package com.sapGarden.application.fi.customer.controller;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.fi.customer.service.ManualSynService;
import com.sapGarden.system.org.model.User;

@Controller("customer_manualSynController")
@RequestMapping("/application/fi/customer/manualSyn")
public class ManualSynController {
	private final String basePagePath = "/application/synConfig/fi/customer";
	private ManualSynService manualSynService;
	@Autowired
	public void setManualSynService(ManualSynService manualSynService) {
		this.manualSynService = manualSynService;
	}

	//*************************************初始化调用函数页面*****************************************************************
	@RequestMapping
	public ModelAndView index(){
		return new ModelAndView(basePagePath+"/manualSyn");
	}
	@RequestMapping("/syn")
	public @ResponseBody Object syn(String kunnr) throws SecurityException, IllegalArgumentException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		manualSynService.manualSyn(user.getCurrentSapDataCollection(),user.getUsername(), kunnr);
		return null;
	}
	
	
}
