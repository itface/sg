package com.sapGarden.application.fi.company.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.constants.SjlxTypeName;
import com.sapGarden.application.commons.excel.service.CommonExpertExcelService;
import com.sapGarden.application.commons.progress.model.NewProgress;
import com.sapGarden.application.commons.progress.service.CommonInitDataService;
import com.sapGarden.application.commons.service.constructJqgridService.CommonConstructJqgridService;
import com.sapGarden.global.json.JsonUtils;
import com.sapGarden.system.org.model.User;

@Controller("company_InitDataController")
@RequestMapping("/application/fi/company/initData")
public class InitDataController {

	private final String PROGRESS_SESSIONATTRIBUTE_NAME = "COMPANY_INITDATA";
	@Autowired
	@Qualifier("commonData")
	private CommonConstructJqgridService commonConstructJqgridService;
	@Autowired
	@Qualifier("companyInitDataService")
	private CommonInitDataService initDataService;
	@Autowired
	@Qualifier("exportCompanyExcel")
	private CommonExpertExcelService commonExpertExcelService;
	@RequestMapping
	public ModelAndView index(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dataGridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_COMPANY,true));
		map.put("client", user.getCurrentSapDataCollection().getSapserverclient());
		return new ModelAndView("/application/synConfig/fi/company/initData",map);
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
	public @ResponseBody void doInit(HttpServletRequest request,HttpServletResponse response,long progressId) throws Exception{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		NewProgress progress = (request.getSession()).getAttribute(PROGRESS_SESSIONATTRIBUTE_NAME)==null?null:(NewProgress)((request.getSession()).getAttribute(PROGRESS_SESSIONATTRIBUTE_NAME));
		if(progress!=null&&progressId==progress.getThreadid()){
			initDataService.init(user.getCurrentSapDataCollection(), user.getUsername(), progressId, progress);
		}
		/*
		long currentThreadId = Thread.currentThread().getId();
		NewProgress progress = new NewProgress();
		progress.setPercentage(15);
		progress.setRunstatus(true);
		progress.setThreadid(currentThreadId);
		(request.getSession()).setAttribute("COMPANY_INITDATA", progress);
		*/
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
	@RequestMapping(value=("/exportExcel"),method=RequestMethod.GET)
	public @ResponseBody void exportExcel(HttpServletResponse response) throws SecurityException, IllegalArgumentException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, URISyntaxException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		commonExpertExcelService.exportExcel(user.getCurrentSapDataCollection(),null, response);
	}
	/*
	@RequestMapping(value=("/removeAll"))
	public @ResponseBody void removeAll(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		init_Service.removeAll(user.getCurrentSapDataCollection());
	}
	@RequestMapping(value=("/validateColumn"))
	public @ResponseBody boolean validateColumn(){
		return validateDbService.validateColumnInfo();
	}
	//@ResponseBody的作用，这个注解的作用是将函数返回值作为请求返回值。没有这个注解的话，请求的响应应该是一个页面，不需要页面的话应该加上这个注解
	@RequestMapping(value=("/doInit"),method = RequestMethod.GET)
	public @ResponseBody void doInit() throws Exception{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Progress progress = ProgressModel.getInstance().getProgress();
		try {
			init_Service.init(user.getCurrentSapDataCollection(),user.getUsername());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ProgressModel.getInstance().getProgress().init();
			throw e;
		}
	}
	@RequestMapping(value=("/getProgressPercent"))
	public @ResponseBody int getProgressPercent(){
		Progress progress = ProgressModel.getInstance().getProgress();
		return progress.getPercent();
	}
	@RequestMapping(value=("/getTotalNum"))
	public @ResponseBody String getTotalNum(){
		Progress progress = ProgressModel.getInstance().getProgress();
		return ""+progress.getTotalNum()+","+progress.getTotalCustomer()+"";
	}
	@RequestMapping(value=("/getTotalTime"))
	public @ResponseBody long initProgressPercent(){
		Progress progress = ProgressModel.getInstance().getProgress();
		long s=progress.getBeginTime();
		long e=progress.getEndTime();
		long totalTime = (e-s)/1000;
		ProgressModel.getInstance().getProgress().init();
		return totalTime;
	}
	@RequestMapping(value=("/repairTable"))
	public @ResponseBody void repairTable(){
		validateDbService.repairTable();
	}
	@RequestMapping(value=("/cancelInit"))
	public @ResponseBody void cancelInit(){
		ProgressModel.getInstance().getProgress().setCancel(true);

	}*/
}
