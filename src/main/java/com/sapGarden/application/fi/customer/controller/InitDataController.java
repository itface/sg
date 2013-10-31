package com.sapGarden.application.fi.customer.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.constants.SjlxTypeName;
import com.sapGarden.application.commons.progress.model.NewProgress;
import com.sapGarden.application.commons.progress.service.CommonInitDataService;
import com.sapGarden.application.commons.service.constructJqgridService.CommonConstructJqgridService;
import com.sapGarden.application.fi.customer.service.ValidateDbService;
import com.sapGarden.global.json.JsonUtils;
import com.sapGarden.system.org.model.User;

@Controller("customer_InitDataController")
@RequestMapping("/application/fi/customer/initData")
public class InitDataController {

	private final String PROGRESS_SESSIONATTRIBUTE_NAME = "CUSTOMER_INITDATA";

	private final String basePagePath = "/application/synConfig/fi/customer";
	@Autowired
	@Qualifier("customerInitDataService")
	private CommonInitDataService initDataService;
	@Autowired
	private ValidateDbService validateDbService;

	@Autowired
	@Qualifier("commonData")
	private CommonConstructJqgridService commonConstructJqgridService;
	
	@RequestMapping
	public ModelAndView index(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("kna1GridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_CUSTOMER_KNA1,true));
		map.put("knb1GridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_CUSTOMER_KNB1,true));
		map.put("knvvGridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_CUSTOMER_KNVV,true));
		map.put("client", user.getCurrentSapDataCollection().getSapserverclient());
		return new ModelAndView(basePagePath+"/initData",map);
	}

	@RequestMapping(value=("/validateTable"))
	public @ResponseBody boolean validateColumn(HttpServletRequest request,HttpServletResponse response,long progressId){
		NewProgress progress = new NewProgress();
		progress.setPercentage(1);
		progress.setRunstatus(true);
		progress.setThreadid(progressId);
		//progress.setText("正在校验表结构...");
		(request.getSession()).setAttribute(PROGRESS_SESSIONATTRIBUTE_NAME, progress);
		boolean flag = initDataService.validateTable();
		progress.setPercentage(10);
		return flag;
	}
	@RequestMapping(value=("/repairTable"))
	public @ResponseBody void repairTable(HttpServletRequest request,HttpServletResponse response,long progressId){
		NewProgress progress = (request.getSession()).getAttribute(PROGRESS_SESSIONATTRIBUTE_NAME)==null?null:(NewProgress)((request.getSession()).getAttribute(PROGRESS_SESSIONATTRIBUTE_NAME));
		if(progress!=null&&progressId==progress.getThreadid()){
			progress.setPercentage(15);
			//progress.setText("正在修复表结构...");
			initDataService.repairTable();
			progress.setPercentage(17);
		}
	}
	@RequestMapping(value=("/doInit"))
	public @ResponseBody void doInit(HttpServletRequest request,HttpServletResponse response,long progressId) throws Exception {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		NewProgress progress = (request.getSession()).getAttribute(PROGRESS_SESSIONATTRIBUTE_NAME)==null?null:(NewProgress)((request.getSession()).getAttribute(PROGRESS_SESSIONATTRIBUTE_NAME));
		if(progress!=null&&progressId==progress.getThreadid()){
			initDataService.init(user.getCurrentSapDataCollection(), user.getUsername(), progressId, progress);
		}
	}
	@RequestMapping(value=("/getProgressModel"))
	public @ResponseBody JSONObject getProgressModel(HttpServletRequest request,HttpServletResponse response,long progressId){
		NewProgress progress = (request.getSession()).getAttribute(PROGRESS_SESSIONATTRIBUTE_NAME)==null?null:(NewProgress)((request.getSession()).getAttribute(PROGRESS_SESSIONATTRIBUTE_NAME));
		return JsonUtils.objectToJSONObject(progress, null);
	}
	@RequestMapping(value=("/cancelInit"))
	public @ResponseBody void cancelInit(HttpServletRequest request,HttpServletResponse response,long progressId){
		NewProgress progress = (request.getSession()).getAttribute(PROGRESS_SESSIONATTRIBUTE_NAME)==null?null:(NewProgress)((request.getSession()).getAttribute(PROGRESS_SESSIONATTRIBUTE_NAME));
		if(progress!=null&&progressId==progress.getThreadid()){
			progress.setCancel(true);
			progress.setEndtime((new Date()).getTime());
			progress.setRunstatus(false);
			//(request.getSession()).removeAttribute("COMPANY_INITDATA");
		}
		//(request.getSession()).removeAttribute("COMPANY_INITDATA");
	}
	/*
	@RequestMapping(value=("/exportExcel"),method=RequestMethod.GET)
	public @ResponseBody void exportExcel(HttpServletResponse response) throws SecurityException, IllegalArgumentException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, URISyntaxException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		commonExpertExcelService.exportExcel(user.getCurrentSapDataCollection(),null, response);
	}
	*/
}
