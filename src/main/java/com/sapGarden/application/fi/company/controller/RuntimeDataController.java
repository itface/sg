package com.sapGarden.application.fi.company.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.constants.SjlxTypeName;
import com.sapGarden.application.commons.excel.service.CommonExpertExcelService;
import com.sapGarden.application.commons.log.service.CommonService;
import com.sapGarden.application.commons.service.constructJqgridService.CommonConstructJqgridService;
import com.sapGarden.application.fi.company.model.Company;
import com.sapGarden.global.utils.commonUtils.XmlUtil;
import com.sapGarden.system.org.model.User;

@Controller("company_RuntimeDataController")
@RequestMapping("/application/fi/company/runtimeData")
public class RuntimeDataController {
	@Autowired
	@Qualifier("commonData")
	private CommonConstructJqgridService commonConstructJqgridService;
	@Autowired
	@Qualifier("commonService")
	private CommonService<Company> companyService;
	@Autowired
	@Qualifier("exportCompanyExcel")
	private CommonExpertExcelService commonExpertExcelService;
	//*************************************初始化调用函数页面*****************************************************************
	@RequestMapping
	public ModelAndView index(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dataGridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_COMPANY,true));
		return new ModelAndView("/application/synConfig/fi/company/runtimeData",map);
	}
	@RequestMapping(value="/findJqgridData")
	public @ResponseBody Object findJqgridData(String companyCode,int rows,int page,String sidx,String sord){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject param = new JSONObject();
		param.put("comp_code", companyCode==null?"":companyCode);
		JSONObject json = companyService.findDataOfJqgridByPage(user.getCurrentSapDataCollection(),"Company", param,rows,page,sidx,sord);
		return json;
	}
	@RequestMapping(value=("/exportExcel"),method=RequestMethod.GET)
	public @ResponseBody void exportExcel(HttpServletResponse response,String companyCode) throws SecurityException, IllegalArgumentException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, URISyntaxException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject param = new JSONObject();
		param.put("comp_code", companyCode==null?"":companyCode);
		commonExpertExcelService.exportExcel(user.getCurrentSapDataCollection(),param, response);
	}
	@RequestMapping(value=("/restGetData"),method=RequestMethod.GET)
	public @ResponseBody String restGetData(String s) {
		
		System.out.println(s+"!!!!");
		List<Company> list = new ArrayList<Company>();
		Company company = new Company();
		company.setId(1000);
		company.setComp_name("金山软件");
		company.setComp_code("8300");
		String xml=XmlUtil.toXml(company);
		return xml;
		//list.add(company);
		//return new ModelAndView("bookXmlView","testRest", list);
		//return "hahahahahhahahah!!!!!!!!";
	}
	@RequestMapping(value=("/setData"),method=RequestMethod.POST)
	public @ResponseBody String setData(String s,String company) {
		
		System.out.println(s+"!!!!");
		System.out.println(company+"*********");
		Company com = XmlUtil.toBean(company, Company.class);
		System.out.println(com.getId()+"@@@@@");
		List<Company> list = new ArrayList<Company>();
		Company company2 = new Company();
		company2.setId(1000);
		company2.setComp_name("金山软件");
		company2.setComp_code("8300");
		String xml=XmlUtil.toXml(company2);
		return xml;
		//list.add(company);
		//return new ModelAndView("bookXmlView","testRest", list);
		//return "hahahahahhahahah!!!!!!!!";
	}
}
