package com.sapGarden.application.synConfig.controller;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.runtime.baseinfo.model.RuntimeBasicInfo;
import com.sapGarden.application.commons.runtime.baseinfo.service.Runtime_BasicInfo_Service;
import com.sapGarden.application.commons.runtime.columninfo.model.JqgirdNewRuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.service.Runtime_ColumnInfo_Service;
import com.sapGarden.application.commons.runtime.tableinfo.model.RuntimeTableInfo;
import com.sapGarden.application.commons.runtime.tableinfo.service.Runtime_TableInfo_Service;
import com.sapGarden.application.synConfig.service.Runtime_SchedulingService;
import com.sapGarden.global.exception.JpaException;
import com.sapGarden.system.org.model.User;
import com.sapGarden.system.scheduling.model.Scheduling;
import com.sapGarden.system.scheduling.service.SchedulingService;

@Controller
@RequestMapping("/application/synConfig/{type}")
public class RuntimeController {

	private SchedulingService schedulingService;
	private Runtime_TableInfo_Service runtime_TableInfo_Service;
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	private Runtime_BasicInfo_Service runtime_BasicInfo_Service;
	private Runtime_SchedulingService runtime_SchedulingService;
	@Autowired
	public void setSchedulingService(SchedulingService schedulingService) {
		this.schedulingService = schedulingService;
	}
	@Autowired
	public void setRuntime_TableInfo_Service(Runtime_TableInfo_Service runtime_TableInfo_Service) {
		this.runtime_TableInfo_Service = runtime_TableInfo_Service;
	}
	@Autowired
	public void setRuntime_ColumnInfo_Service(Runtime_ColumnInfo_Service runtime_ColumnInfo_Service) {
		this.runtime_ColumnInfo_Service = runtime_ColumnInfo_Service;
	}
	@Autowired
	public void setRuntime_BasicInfo_Service(Runtime_BasicInfo_Service runtime_BasicInfo_Service) {
		this.runtime_BasicInfo_Service = runtime_BasicInfo_Service;
	}
	@Autowired
	public void setRuntime_SchedulingService(Runtime_SchedulingService runtime_SchedulingService) {
		this.runtime_SchedulingService = runtime_SchedulingService;
	}
	/*
	@RequestMapping
	public ModelAndView index(@PathVariable String type){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", type);
		map.put("sapClient", user.getCurrentSapClientModel().getId());
		return new ModelAndView("/application/synConfig/main",map);
	}*/
	//*************************************初始化运行设置页面*****************************************************************
	@RequestMapping(value=("/runtimeConfig"),method = RequestMethod.GET)
	public ModelAndView runtimeConfigIndex(@PathVariable String type) throws JpaException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Scheduling scheduling = schedulingService.findOneByJobGroup(type,user.getCurrentSapDataCollection().getId());
		RuntimeTableInfo dbReflect = runtime_TableInfo_Service.findOneByBusinesstype(user.getCurrentSapDataCollection().getId(),type);
		RuntimeBasicInfo basicInfo = runtime_BasicInfo_Service.findOneByBusinesstype(type,user.getCurrentSapDataCollection().getId());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("scheduling", scheduling==null?(new Scheduling()):scheduling);
		map.put("jobClassName", runtime_SchedulingService.getJobClassName(type));
		map.put("owner", user.getUsername());
		map.put("dbReflect", dbReflect==null?(new RuntimeTableInfo()):dbReflect);
		map.put("basicInfo", basicInfo==null?(new RuntimeBasicInfo()):basicInfo);
		map.put("type", type);
		map.put("sapclient", user.getCurrentSapDataCollection().getId());
		return new ModelAndView("/application/synConfig/runtimeConfig",map);
	}
	//*************************************运行设置-〉基本信息***************************************************************
	@RequestMapping(value=("/runtimeConfig/basicInfo"),method=RequestMethod.POST)
	public @ResponseBody void _newbasicInfo(RuntimeBasicInfo runtimeBasicInfo) throws JpaException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		runtime_BasicInfo_Service.addOne(runtimeBasicInfo,new String[]{"bo_fin_company"},user.getCurrentSapDataCollection().getId());
	}
	@RequestMapping(value=("/runtimeConfig/basicInfo/{id}"),method=RequestMethod.PUT)
	public @ResponseBody void updatebasicInfo(RuntimeBasicInfo runtimeBasicInfo) throws JpaException{
		runtime_BasicInfo_Service.updateOne(runtimeBasicInfo);
	}
	//*************************************运行设置-〉表映射*****************************************************************
	@RequestMapping(value=("/runtimeConfig/tableInfo"),method=RequestMethod.POST)
	public @ResponseBody void _newDbReflectData(RuntimeTableInfo dbReflect) throws JpaException{
		runtime_TableInfo_Service.addOne(dbReflect);
	}
	@RequestMapping(value=("/runtimeConfig/tableInfo/{id}"),method=RequestMethod.PUT)
	public @ResponseBody void updateDbReflectData(RuntimeTableInfo dbReflect) throws JpaException{
		runtime_TableInfo_Service.updateOne(dbReflect);
	}
	//*************************************运行设置-〉字段映射*****************************************************************
	@RequestMapping(value=("/runtimeConfig/columnInfo"),method=RequestMethod.GET)
	public @ResponseBody Object getDbReflectOfColumnData(@PathVariable String type,@RequestParam int page,@RequestParam int rows) throws JpaException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject json = runtime_ColumnInfo_Service.findJsonByBusinesstype(page,rows,user.getCurrentSapDataCollection(),type);
		return json==null?"{}":json;
	}
	@RequestMapping(value=("/runtimeConfig/columnInfo"),method=RequestMethod.POST)
	public @ResponseBody void _newDbReflectOfColumnData(@PathVariable String type,JqgirdNewRuntimeColumnInfo jqgirdNewDbReflectOfColumn) throws JpaException{
		jqgirdNewDbReflectOfColumn.setBusinesstype(type);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		jqgirdNewDbReflectOfColumn.setSapclient(user.getCurrentSapDataCollection().getId());
		runtime_ColumnInfo_Service.addOne(jqgirdNewDbReflectOfColumn);
	}
	@RequestMapping(value=("/runtimeConfig/columnInfo/{id}"),method=RequestMethod.PUT)
	public @ResponseBody void updateDbReflectOfColumnData(JqgirdNewRuntimeColumnInfo jqgirdNewDbReflectOfColumn) throws JpaException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		jqgirdNewDbReflectOfColumn.setSapclient(user.getCurrentSapDataCollection().getId());
		runtime_ColumnInfo_Service.updateOne(jqgirdNewDbReflectOfColumn);
	}
	@RequestMapping(value=("/runtimeConfig/columnInfo/{id}"),method=RequestMethod.DELETE)
	public @ResponseBody void deleteDbReflectOfColumnData(@PathVariable long id) throws JpaException{
		runtime_ColumnInfo_Service.deleteOne(id);
	}
	@RequestMapping(value=("/runtimeConfig/columnInfo/updateStatus"))
	public @ResponseBody void updateDbReflectOfColumnData(long id,String status) throws JpaException{
		runtime_ColumnInfo_Service.updateStatusById(id, status);
	}
	//******************************************************************************************************
	
	
	
	
}
