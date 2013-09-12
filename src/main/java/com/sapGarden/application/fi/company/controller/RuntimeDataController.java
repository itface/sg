package com.sapGarden.application.fi.company.controller;

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
import com.sapGarden.application.commons.service.constructJqgridService.CommonConstructJqgridService;
import com.sapGarden.application.fi.company.service.CompanyService;
import com.sapGarden.application.fi.company.service.ExportExcelService;
import com.sapGarden.system.org.model.User;

@Controller("company_RuntimeDataController")
@RequestMapping("/application/fi/company/runtimeData")
public class RuntimeDataController {
	@Autowired
	@Qualifier("commonData")
	private CommonConstructJqgridService commonConstructJqgridService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ExportExcelService excelService;
	//*************************************初始化调用函数页面*****************************************************************
	@RequestMapping
	public ModelAndView index(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dataGridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_COMPANY));
		return new ModelAndView("/application/synConfig/fi/company/runtimeData",map);
	}
	@RequestMapping(value="/findJqgridData")
	public @ResponseBody Object findJqgridData(String companyCode,int rows,int page){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject json = companyService.findDataOfJqgridByPage(user.getCurrentSapDataCollection(), companyCode,rows,page);
		return json;
	}
	@RequestMapping(value=("/exportExcel"),method=RequestMethod.GET)
	public @ResponseBody void exportExcel(HttpServletResponse response,String companyCode) throws SecurityException, IllegalArgumentException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, URISyntaxException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		excelService.exportExcel(user.getCurrentSapDataCollection(),companyCode, response);
	}
}
