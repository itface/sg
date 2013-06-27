package com.sapGarden.application.synConfig.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.jco.commons.exception.JcoException;
import com.sapGarden.application.synConfig.service.CallFun_ParamInfo_Service;
import com.sapGarden.application.synConfig.service.CallFun_TestCall_Service;
import com.sapGarden.application.synConfig.service.Common_ConstructJqgrid_Service;
import com.sapGarden.system.org.model.User;

@Controller
@RequestMapping("/application/synConfig/{type}/callFun")
public class CallFunController {
	private Common_ConstructJqgrid_Service common_ConstructJqgrid_Service;
	private CallFun_ParamInfo_Service callFunParamInfoService;
	private CallFun_TestCall_Service callFun_TestCall_service;
	@Autowired
	public void setCallFunParamInfoService(CallFun_ParamInfo_Service callFunParamInfoService) {
		this.callFunParamInfoService = callFunParamInfoService;
	}
	@Autowired
	@Qualifier("callFun_constructResultGrid_ServiceImpl")
	public void setCommon_ConstructJqgrid_Service(Common_ConstructJqgrid_Service common_ConstructJqgrid_Service) {
		this.common_ConstructJqgrid_Service = common_ConstructJqgrid_Service;
	}
	@Autowired
	public void setCallFun_TestCall_service(CallFun_TestCall_Service callFun_TestCall_service) {
		this.callFun_TestCall_service = callFun_TestCall_service;
	}
	//*************************************初始化调用函数页面*****************************************************************
	@RequestMapping
	public ModelAndView callFunConfigIndex(@PathVariable String type){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", type);
		map.put("resultGridOptions",common_ConstructJqgrid_Service.construct(type,user.getCurrentSapDataCollection()));
		return new ModelAndView("/application/synConfig/callFunctionConfig",map);
	}
	//*************************************参数设置增删改查*****************************************************************
	/*
	@RequestMapping(value = "/paramInfo",method=RequestMethod.GET)
	public @ResponseBody Object getParamConfig(@PathVariable String type) throws JpaException{
		JSONObject json = callFunParamInfoService.findJsonByBusinesstype(type);
		return json==null?"{}":json;
	}
	@RequestMapping(value=("/paramInfo"),method=RequestMethod.POST)
	public @ResponseBody void _newParamConfig(@PathVariable String type,JqgridNewCallFunParamInfo jqgridNewCallFunParamInfo) throws JpaException{
		jqgridNewCallFunParamInfo.setBusinesstype(type);
		callFunParamInfoService.addOne(jqgridNewCallFunParamInfo);
	}
	@RequestMapping(value=("/paramInfo/{id}"),method=RequestMethod.PUT)
	public @ResponseBody void updateParamConfig(JqgridNewCallFunParamInfo jqgridNewCallFunParamInfo) throws JpaException{
		callFunParamInfoService.updateOne(jqgridNewCallFunParamInfo);
	}
	@RequestMapping(value=("/paramInfo/{id}"),method=RequestMethod.DELETE)
	public @ResponseBody void deleteParamConfig(@PathVariable long id) throws JpaException{
		callFunParamInfoService.deleteOne(id);
	}*/
	//****************************************************************************************
	@RequestMapping(value = "/testCallFun",method=RequestMethod.GET)
	public @ResponseBody Object test(@PathVariable String type) throws JcoException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return callFun_TestCall_service.testCall(type,user.getCurrentSapDataCollection());
	}
	
	
	
	
}
