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
import com.sapGarden.application.synConfig.service.CompareData_SapSynToLocal_Service;
import com.sapGarden.application.synConfig.service.CompareData_Service;
import com.sapGarden.global.exception.JpaException;
import com.sapGarden.system.org.model.User;

@Controller
@RequestMapping("/application/synConfig/{type}/compareData")
public class CompareDataController {

	private CompareData_SapSynToLocal_Service comparedData_sapSynToLocal_Service;
	private CompareData_Service compareData_Service;
	private Common_ConstructJqgrid_Service common_ConstructJqgrid_Service;
	@Autowired
	public void setCompareData_Service(CompareData_Service compareData_Service) {
		this.compareData_Service = compareData_Service;
	}
	@Autowired
	public void setComparedData_sapSynToLocal_Service(CompareData_SapSynToLocal_Service comparedData_sapSynToLocal_Service) {
		this.comparedData_sapSynToLocal_Service = comparedData_sapSynToLocal_Service;
	}
	@Autowired
	@Qualifier("compareData_ConstructGrid_ServiceImpl")
	public void setCommon_ConstructJqgrid_Service(Common_ConstructJqgrid_Service common_ConstructJqgrid_Service) {
		this.common_ConstructJqgrid_Service = common_ConstructJqgrid_Service;
	}
	//*************************************初始化调用函数页面*****************************************************************
	@RequestMapping
	public ModelAndView index(@PathVariable String type){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", type);
		map.put("compareDataGridOptions",common_ConstructJqgrid_Service.construct(type,user.getCurrentSapDataCollection()));
		return new ModelAndView("/application/synConfig/compareData",map);
	}
	//*************************************对比*****************************************************************
	@RequestMapping("/compare")
	public @ResponseBody Object comparedData(@PathVariable String type) throws Exception{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject json = compareData_Service.compare(type,user.getCurrentSapDataCollection());
		return json==null?"{}":json;
	}
	//*************************************sap数据同步到本地*****************************************************************
	@RequestMapping(value="/sapSynToLocal",method=RequestMethod.POST)
	public @ResponseBody Object sapSynToLocal(@PathVariable String type, String list) throws Exception{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		comparedData_sapSynToLocal_Service.sapSynToLocal(user.getCurrentSapDataCollection(),type, list,user.getUsername());
		return "S";
	}
	
	

	
	
}
