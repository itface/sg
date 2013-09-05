package com.sapGarden.application.fi.customer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.basetable.service.BaseTableService;
import com.sapGarden.application.commons.constants.SjlxTypeName;
import com.sapGarden.application.commons.runtime.baseinfo.model.RuntimeBasicInfo;
import com.sapGarden.application.commons.runtime.baseinfo.service.Runtime_BasicInfo_Service;
import com.sapGarden.application.commons.runtime.tableinfo.model.RuntimeTableInfo;
import com.sapGarden.application.commons.runtime.tableinfo.service.Runtime_TableInfo_Service;
import com.sapGarden.application.fi.customer.service.RuntimeConfig_BasicInfo_Service;
import com.sapGarden.system.org.model.User;

@Controller("customer_RuntimeController")
@RequestMapping("/application/fi/customer")
public class RuntimeController {

	private final String basePagePath = "/application/synConfig/fi/customer";
	private final String jobName = "com.sapGarden.application.fi.customer.jobs.CustomerJob";
	
	
	
	//private SchedulingService schedulingService;
	private Runtime_TableInfo_Service runtime_TableInfo_Service;
	private Runtime_BasicInfo_Service runtime_BasicInfo_Service;
	@Autowired
	private RuntimeConfig_BasicInfo_Service runtimeConfig_BasicInfo_Service;
	@Autowired
	private BaseTableService baseTableService;


	@Autowired
	public void setRuntime_TableInfo_Service(Runtime_TableInfo_Service runtime_TableInfo_Service) {
		this.runtime_TableInfo_Service = runtime_TableInfo_Service;
	}
	@Autowired
	public void setRuntime_BasicInfo_Service(Runtime_BasicInfo_Service runtime_BasicInfo_Service) {
		this.runtime_BasicInfo_Service = runtime_BasicInfo_Service;
	}
	/*
	@RequestMapping
	public ModelAndView index(HttpServletRequest request){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sapClient", user.getCurrentSapClientModel().getId());
		map.put("basePath", request.getRequestURI());
		return new ModelAndView(basePagePath+"/main",map);
	}*/
	//*************************************初始化运行设置页面*****************************************************************
	@RequestMapping(value=("/runtimeConfig"),method = RequestMethod.GET)
	public ModelAndView runtimeConfigIndex(HttpServletRequest request){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//Scheduling scheduling = schedulingService.findOneByJobGroup(SjlxTypeName.TYPE_CUSTOMER,user.getCurrentSapDataCollection().getId());
		RuntimeTableInfo dbReflect = runtime_TableInfo_Service.findOneByBusinesstype(user.getCurrentSapDataCollection().getId(),SjlxTypeName.TYPE_CUSTOMER);
		RuntimeBasicInfo basicInfo = runtime_BasicInfo_Service.findOneByBusinesstype(SjlxTypeName.TYPE_CUSTOMER,user.getCurrentSapDataCollection().getId());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", SjlxTypeName.TYPE_CUSTOMER);
		//map.put("scheduling", scheduling==null?(new Scheduling()):scheduling);
		map.put("jobClassName", jobName);
		map.put("owner", user.getUsername());
		map.put("dbReflect", dbReflect==null?(new RuntimeTableInfo()):dbReflect);
		map.put("basicInfo", basicInfo==null?(new RuntimeBasicInfo()):basicInfo);
		map.put("kna1TableName", "KNA1");
		map.put("knb1TableName", "KNB1");
		map.put("knVVTableName", "KNVV");
		map.put("basePath", request.getRequestURI());
		map.put("sapclient", user.getCurrentSapDataCollection().getId());
		return new ModelAndView(basePagePath+"/runtimeConfig",map);
	}
	//*************************************运行设置-〉基本信息***************************************************************
	@RequestMapping(value=("/runtimeConfig/basicInfo"),method=RequestMethod.POST)
	public @ResponseBody void _newbasicInfo(RuntimeBasicInfo runtimeBasicInfo){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		List<BaseTableModel> kna1List = baseTableService.getBaseTableByTableName("KNA1");
//		List<BaseTableModel> knb1List = baseTableService.getBaseTableByTableName("KNB1");
//		List<BaseTableModel> knvvList = baseTableService.getBaseTableByTableName("KNVV");
		//runtimeConfig_BasicInfo_Service.addBasicInfo(runtimeBasicInfo, user.getCurrentSapClientModel().getId(), null, null, null);
		//runtimeConfig_BasicInfo_Service.addBasicInfo(runtimeBasicInfo, user.getCurrentSapClientModel().getId());
		//testService.test(runtimeBasicInfo, user.getCurrentSapClientModel().getId());
		runtime_BasicInfo_Service.addOne(runtimeBasicInfo,new String[]{"KNA1","KNB1","KNVV"},user.getCurrentSapDataCollection().getId());
	}
	@RequestMapping(value=("/runtimeConfig/basicInfo/{id}"),method=RequestMethod.PUT)
	public @ResponseBody void updatebasicInfo(RuntimeBasicInfo runtimeBasicInfo){
		runtime_BasicInfo_Service.updateOne(runtimeBasicInfo);
	}
	
}
