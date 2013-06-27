package com.sapGarden.application.fi.customer.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.service.constructJqgridService.impl.Common_ConstructCompareGrid_ServiceImpl;
import com.sapGarden.application.fi.customer.model.Kna1Compared;
import com.sapGarden.application.fi.customer.service.CompareDataService;
import com.sapGarden.system.org.model.User;

@Controller("customer_compareDataController")
@RequestMapping("/application/fi/customer/compareData")
public class CompareDataController {
	private final String basePagePath = "/application/synConfig/fi/customer";


	private CompareDataService compareDataService;
	//private Common_ConstructSimpleGrid_Service common_ConstructSimpleGrid_Service;
	private Common_ConstructCompareGrid_ServiceImpl common_ConstructCompareGrid_ServiceImpl;
	//private CommonService commonService;

//	@Autowired
//	public void setCommon_ConstructSimpleGrid_Service(Common_ConstructSimpleGrid_Service common_ConstructSimpleGrid_Service) {
//		this.common_ConstructSimpleGrid_Service = common_ConstructSimpleGrid_Service;
//	}
	@Autowired
	//@Qualifier("customer_CompareDataService")
	public void setCompareDataService(CompareDataService compareDataService) {
		this.compareDataService = compareDataService;
	}
//	@Autowired
//	public void setCommonService(CommonService commonService) {
//		this.commonService = commonService;
//	}
	@Autowired
	public void setCommon_ConstructCompareGrid_ServiceImpl(
			Common_ConstructCompareGrid_ServiceImpl common_ConstructCompareGrid_ServiceImpl) {
		this.common_ConstructCompareGrid_ServiceImpl = common_ConstructCompareGrid_ServiceImpl;
	}
	//*************************************初始化调用函数页面*****************************************************************
	@RequestMapping
	public ModelAndView index(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("kna1TableWidth", common_ConstructCompareGrid_ServiceImpl.getTableWidth(user.getCurrentSapDataCollection(), "KNA1"));
		map.put("kna1TableHeader", common_ConstructCompareGrid_ServiceImpl.getTableHeaderHtml(user.getCurrentSapDataCollection(), "KNA1"));
		map.put("knb1TableWidth", common_ConstructCompareGrid_ServiceImpl.getTableWidth(user.getCurrentSapDataCollection(), "KNB1"));
		map.put("knb1TableHeader", common_ConstructCompareGrid_ServiceImpl.getTableHeaderHtml(user.getCurrentSapDataCollection(), "KNB1"));
		map.put("knvvTableWidth", common_ConstructCompareGrid_ServiceImpl.getTableWidth(user.getCurrentSapDataCollection(), "KNVV"));
		map.put("knvvTableHeader", common_ConstructCompareGrid_ServiceImpl.getTableHeaderHtml(user.getCurrentSapDataCollection(), "KNVV"));
		//map.put("kna1GridOptions",common_ConstructSimpleGrid_Service.construct(user.getCurrentSapClientModel(), "KNA1", "kna1Grid", ""));
		//map.put("knb1GridOptions",common_ConstructSimpleGrid_Service.construct(user.getCurrentSapClientModel(), "KNB1", "knb1Grid", ""));
		//map.put("knvvGridOptions",common_ConstructSimpleGrid_Service.construct(user.getCurrentSapClientModel(), "KNVV", "knvvGrid", ""));
		return new ModelAndView(basePagePath+"/compareData",map);
	}
	//*************************************对比*****************************************************************
	
	@RequestMapping("/compare")
	public @ResponseBody Object comparedData(String kunnr,String bukrs,String vkorg,String vtweg,String spart) throws Exception{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		compareDataService.compare(user.getCurrentSapDataCollection(),user.getUsername(),kunnr,bukrs,vkorg,vtweg,spart);
		return null;
	}
	@RequestMapping("/loadCompareData/{type}")
	public @ResponseBody String loadCompareData(@PathVariable String type) throws Exception{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*
		List list = new LinkedList();
		if(type.equals("kna1")){
			list = commonService.findBySapclient(Kna1Compared.class, user.getCurrentSapClientModel().getId());
		}else if(type.equals("knb1")){
			list = commonService.findBySapclient(Knb1Compared.class, user.getCurrentSapClientModel().getId());
		}else if(type.equals("knvv")){
			list = commonService.findBySapclient(KnvvCompared.class, user.getCurrentSapClientModel().getId());
		}
		JsonModel jsonModel = new JsonModel();
		jsonModel.setList(list);
		*/
		return compareDataService.getDataTableHtml(user.getCurrentSapDataCollection(), type);
	}
	//*************************************sap数据同步到本地*****************************************************************
	@RequestMapping(value="/sapSynToLocal")
	public @ResponseBody Object sapSynToLocal(String kna1Compareds,String knb1Compareds,String knvvCompareds) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		compareDataService.sapSynToLocal(user.getCurrentSapDataCollection().getId(), user.getUsername(), kna1Compareds, knb1Compareds, knvvCompareds);
		return "S";
	}

	@RequestMapping(value="/getComparedResult")
	public @ResponseBody Object getComparedResult(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return compareDataService.getComparedResult(user.getCurrentSapDataCollection());
	}
	

	
	
}
