package com.sapGarden.application.fi.customer.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.constants.SjlxTypeName;
import com.sapGarden.application.commons.excel.service.CommonExpertExcelService;
import com.sapGarden.application.commons.log.service.CommonService;
import com.sapGarden.application.commons.service.constructJqgridService.CommonConstructJqgridService;
import com.sapGarden.system.org.model.User;

@Controller("customer_RuntimeDataController")
@RequestMapping("/application/fi/customer/runtimeData")
public class RuntimeDataController {
	private final String basePagePath = "/application/synConfig/fi/customer";

	@Autowired
	@Qualifier("commonData")
	private CommonConstructJqgridService commonConstructJqgridService;

	
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;
	@Autowired
	@Qualifier("exportCustomerExcel")
	private CommonExpertExcelService commonExpertExcelService;
	//*************************************初始化调用函数页面*****************************************************************
	@RequestMapping
	public ModelAndView index(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("kna1GridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_CUSTOMER_KNA1,true));
		map.put("knb1GridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_CUSTOMER_KNB1,true));
		map.put("knvvGridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_CUSTOMER_KNVV,true));
		return new ModelAndView(basePagePath+"/runtimeData",map);
	}
	@RequestMapping(value = "/kna1",method=RequestMethod.GET)
	public @ResponseBody Object getKna1Config(String kunnr,int rows,int page,String sidx,String sord){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject param = new JSONObject();
		param.put("kunnr", kunnr);
		JSONObject json = commonService.findDataOfJqgridByPage(user.getCurrentSapDataCollection(),"Kna1", param, rows, page, sidx, sord);
		return json==null?"{}":json;
	}
	@RequestMapping(value = "/knb1",method=RequestMethod.GET)
	public @ResponseBody Object getKnb1Config(String kunnr,int rows,int page,String sidx,String sord){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject param = new JSONObject();
		param.put("kunnr", kunnr);
		JSONObject json = commonService.findDataOfJqgridByPage(user.getCurrentSapDataCollection(),"Knb1", param, rows, page, sidx, sord);
		return json==null?"{}":json;
	}
	@RequestMapping(value = "/knvv",method=RequestMethod.GET)
	public @ResponseBody Object getKnvvConfig(String kunnr,int rows,int page,String sidx,String sord){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject param = new JSONObject();
		param.put("kunnr", kunnr);
		JSONObject json = commonService.findDataOfJqgridByPage(user.getCurrentSapDataCollection(),"Knvv", param, rows, page, sidx, sord);
		return json==null?"{}":json;
	}
	@RequestMapping(value=("/exportExcel"),method=RequestMethod.GET)
	public @ResponseBody void exportExcel(HttpServletResponse response,String kunnr) throws SecurityException, IllegalArgumentException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, URISyntaxException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject param = new JSONObject();
		param.put("kunnr", kunnr);
		commonExpertExcelService.exportExcel(user.getCurrentSapDataCollection(),param, response);
	}

}
