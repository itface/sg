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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.constants.SjlxTypeName;
import com.sapGarden.application.commons.log.service.CommonService;
import com.sapGarden.application.commons.service.constructJqgridService.CommonConstructJqgridService;
import com.sapGarden.application.fi.customer.service.CompareDataService;
import com.sapGarden.system.org.model.User;

@Controller("customer_compareDataController")
@RequestMapping("/application/fi/customer/compareData")
public class CompareDataController {
	private final String basePagePath = "/application/synConfig/fi/customer";


	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;
	@Autowired
	private CompareDataService compareDataService;
	@Autowired
	@Qualifier("compareData")
	private CommonConstructJqgridService commonConstructJqgridService;
	@RequestMapping
	public ModelAndView index(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("kna1GridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_CUSTOMER_KNA1));
		map.put("knb1GridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_CUSTOMER_KNB1));
		map.put("knvvGridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_CUSTOMER_KNVV));
		return new ModelAndView(basePagePath+"/compareData",map);
	}
	@RequestMapping("/compare")
	public @ResponseBody Object comparedData(String kunnr,String bukrs,String vkorg,String vtweg,String spart) throws Exception{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return compareDataService.compareSapAndGarden(user.getCurrentSapDataCollection(),user.getUsername(),kunnr,bukrs,vkorg,vtweg,spart);
	}
	@RequestMapping("/loadCompareData/{type}")
	public @ResponseBody Object loadCompareData(@PathVariable String type,String kunnr,String bukrs,String vkorg,String vtweg,String spart,int rows,int page,String sidx,String sord) throws Exception{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(type.equals("kna1comare")){
			return commonService.findDataOfJqgridByPage(user.getCurrentSapDataCollection(), "Kna1Compared", null, rows, page, sidx, sord);
		}else if(type.equals("knb1comare")){
			return commonService.findDataOfJqgridByPage(user.getCurrentSapDataCollection(), "Knb1Compared", null, rows, page, sidx, sord);
		}else if(type.equals("knvvcomare")){
			return commonService.findDataOfJqgridByPage(user.getCurrentSapDataCollection(), "KnvvCompared", null, rows, page, sidx, sord);
		}
		return "";
	}
	@RequestMapping(value="/sapSynToLocal")
	public @ResponseBody Object sapSynToLocal(String kna1Compareds,String knb1Compareds,String knvvCompareds) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		compareDataService.compareSapAndGardenDataToLocal(user.getCurrentSapDataCollection(), user.getUsername(), kna1Compareds, knb1Compareds, knvvCompareds);
		return "S";
	}
/*
	@RequestMapping(value="/getComparedResult")
	public @ResponseBody Object getComparedResult(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return compareDataService.getComparedResult(user.getCurrentSapDataCollection());
	}
	

	*/
	
}
