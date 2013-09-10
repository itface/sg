package com.sapGarden.application.fi.company.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.constants.SjlxTypeName;
import com.sapGarden.application.commons.log.service.CommonServiceWithLog;
import com.sapGarden.application.commons.service.constructJqgridService.CommonConstructJqgridService;
import com.sapGarden.application.fi.company.service.ManualSynService;
import com.sapGarden.application.fi.company.service.SynService;
import com.sapGarden.system.org.model.User;

@Controller("company_manualSynController")
@RequestMapping("/application/fi/company/manualSyn")
public class ManualSynController {
	@Autowired
	@Qualifier("commonData")
	private CommonConstructJqgridService commonConstructJqgridService;
	@Autowired
	@Qualifier("company_ManualSynService")
	private ManualSynService manualSynService;

	//*************************************初始化调用函数页面*****************************************************************
	@RequestMapping
	public ModelAndView index(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("resultGridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_COMPANY));
		return new ModelAndView("/application/synConfig/fi/company/manualSyn",map);
	}
	@RequestMapping("/testCall")
	public @ResponseBody Object testCall() throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return manualSynService.testCall(user.getCurrentSapDataCollection());
	}
	/*
	@RequestMapping("/syn")
	public @ResponseBody Object syn(String kunnr) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		synService.syn(user.getCurrentSapDataCollection(), CommonServiceWithLog.OPTTYPE_MANUALSYN, user.getUsername(), true);
		return companyService.findDataOfJqgridByPage(user.getCurrentSapDataCollection(),null,10000,1);
	}*/
	@RequestMapping("/syn")
	public @ResponseBody void syn(String list) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		manualSynService.saveManuSynDataOfJqgridToLocal(user.getCurrentSapDataCollection(), list, user.getUsername(), CommonServiceWithLog.OPTTYPE_MANUALSYN, CommonServiceWithLog.IFLOG_YES);
	}
	
}
