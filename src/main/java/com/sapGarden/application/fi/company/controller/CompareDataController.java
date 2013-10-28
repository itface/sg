package com.sapGarden.application.fi.company.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

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
import com.sapGarden.application.fi.company.service.CompareDataService;
import com.sapGarden.system.org.model.User;

@Controller("company_compareDataController")
@RequestMapping("/application/fi/company/compareData")
public class CompareDataController {

	@Autowired
	@Qualifier("compareData2")
	private CommonConstructJqgridService commonConstructJqgridService;
	@Autowired
	@Qualifier("company_CompareDataService")
	private CompareDataService compareDataService;

	@RequestMapping
	public ModelAndView index(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("compareDataGridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(), SjlxTypeName.TYPE_COMPANY));
		return new ModelAndView("/application/synConfig/fi/company/compareData",map);
	}	
	@RequestMapping("/compare")
	public @ResponseBody Object comparedData() throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return compareDataService.getJqgridCompareJsonData(user.getCurrentSapDataCollection());
	}
	@RequestMapping(value="/sapSynToLocal")
	public @ResponseBody Object sapSynToLocal(String list){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		compareDataService.saveComparedDataOfJqgridToLocal(user.getCurrentSapDataCollection(), list, user.getUsername(), CommonService.OPTTYPE_COMPAREDATA, true);
		return "S";
	}
//	@RequestMapping("/loadCompareData/{type}")
//	public @ResponseBody JSONObject loadCompareData(@PathVariable String type) throws Exception{
//		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		return compareDataService.getJqgridCompareJsonData(user.getCurrentSapDataCollection());
//	}

//	@RequestMapping(value="/getComparedResult")
//	public @ResponseBody Object getComparedResult(){
//		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		return null;
//	}
	

	
	
}
