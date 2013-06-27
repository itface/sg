package com.sapGarden.application.fi.customer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.progress.model.Progress;
import com.sapGarden.application.commons.progress.model.ProgressModel;
import com.sapGarden.application.fi.customer.service.Init_Service;
import com.sapGarden.application.fi.customer.service.ValidateDbService;
import com.sapGarden.system.org.model.User;

@Controller("customer_InitDataController")
@RequestMapping("/application/fi/customer/initData")
public class InitDataController {

	private final String basePagePath = "/application/synConfig/fi/customer";
	@Autowired
	private Init_Service init_Service;
	@Autowired
	private ValidateDbService validateDbService;

	@RequestMapping
	public ModelAndView index(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		return new ModelAndView(basePagePath+"/initData",map);
	}
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

	}
}
