package com.sapGarden.application.synConfig.controller;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.synConfig.service.Common_ConstructJqgrid_Service;
import com.sapGarden.application.synConfig.service.RuntimeMonitor_ProcessError_Service;
import com.sapGarden.application.synConfig.service.RuntimeMonitor_Service;
import com.sapGarden.system.org.model.User;

@Controller
@RequestMapping("/application/synConfig/{type}/runtimeMonitor")
public class RuntimeMonitorController {

	private RuntimeMonitor_ProcessError_Service runtimeMonitor_ProcessError_Service;
	private RuntimeMonitor_Service runtimeMonitor_Service;
	private Common_ConstructJqgrid_Service common_ConstructJqgrid_Service;
	@Autowired
	@Qualifier("runtimeMonitor_constructGrid_ServiceImpl")
	public void setCommon_ConstructJqgrid_Service(Common_ConstructJqgrid_Service common_ConstructJqgrid_Service) {
		this.common_ConstructJqgrid_Service = common_ConstructJqgrid_Service;
	}
	@Autowired
	public void setRuntimeMonitor_Service(RuntimeMonitor_Service runtimeMonitor_Service) {
		this.runtimeMonitor_Service = runtimeMonitor_Service;
	}
	@Autowired
	public void setRuntimeMonitor_ProcessError_Service(RuntimeMonitor_ProcessError_Service runtimeMonitor_ProcessError_Service) {
		this.runtimeMonitor_ProcessError_Service = runtimeMonitor_ProcessError_Service;
	}
	//*************************************初始化调用函数页面*****************************************************************
	@RequestMapping
	public ModelAndView index(@PathVariable String type){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", type);
		map.put("monitorGridOptions",common_ConstructJqgrid_Service.construct(type,user.getCurrentSapDataCollection()));
		return new ModelAndView("/application/synConfig/runtimeMonitor",map);
	}
	//*************************************查询日志*****************************************************************
	@RequestMapping(value = "/data",method=RequestMethod.GET)
	public @ResponseBody Object getParamConfig(@PathVariable String type,String filters) throws Exception{		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject json = runtimeMonitor_Service.findJsonByBusinesstype(user.getCurrentSapDataCollection(),type,filters);
		return json==null?"{}":json;
	}
	//*************************************处理异常*****************************************************************
	@RequestMapping(value = "/processError")
	public @ResponseBody Object processError(@PathVariable String type,String ids) throws Exception{		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return runtimeMonitor_ProcessError_Service.process(user.getCurrentSapDataCollection(),type, ids, user.getUsername());
	}
	
	
}
