package com.sapGarden.application.fi.customer.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.constants.SjlxTypeName;
import com.sapGarden.application.commons.log.service.CommonService;
import com.sapGarden.application.commons.service.constructJqgridService.CommonConstructJqgridService;
import com.sapGarden.application.fi.customer.service.ManualSynService;
import com.sapGarden.system.org.model.User;

@Controller("customer_manualSynController")
@RequestMapping("/application/fi/customer/manualSyn")
public class ManualSynController {
	private final String basePagePath = "/application/synConfig/fi/customer";
	@Autowired
	@Qualifier("commonData")
	private CommonConstructJqgridService commonConstructJqgridService;
	@Autowired
	private ManualSynService manualSynService;
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;


	//*************************************初始化调用函数页面*****************************************************************
	@RequestMapping
	public ModelAndView index(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("kna1GridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_CUSTOMER_KNA1,false));
		map.put("knb1GridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_CUSTOMER_KNB1,false));
		map.put("knvvGridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_CUSTOMER_KNVV,false));
		return new ModelAndView(basePagePath+"/manualSyn",map);
	}
	@RequestMapping("/getSapData")
	public @ResponseBody Object getSapData(String kunnr) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return manualSynService.getSapData(user.getCurrentSapDataCollection(), kunnr);
	}
	@RequestMapping("/syn")
	public @ResponseBody Object syn(String kna1s,String knb1s,String knvvs) throws SecurityException, IllegalArgumentException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		manualSynService.manualSyn(user.getCurrentSapDataCollection(),user.getUsername(), kna1s,knb1s,knvvs);
		return null;
	}
	
	
}
