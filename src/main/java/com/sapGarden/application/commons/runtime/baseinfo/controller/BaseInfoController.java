package com.sapGarden.application.commons.runtime.baseinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapGarden.application.commons.runtime.baseinfo.service.Runtime_BasicInfo_Service;
import com.sapGarden.system.org.model.User;

@Controller
@RequestMapping("/application/baseinfo")
public class BaseInfoController {
	@Autowired
	private Runtime_BasicInfo_Service Runtime_BasicInfo_Service;
	
	@RequestMapping(value=("/updateByBusinesstypes"))
	public @ResponseBody void   updateByBusinesstypes(String btypes,String status){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Runtime_BasicInfo_Service.updateByBusinesstypes(btypes, user.getCurrentSapDataCollection().getId(),status);
	}
}
