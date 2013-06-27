package com.sapGarden.application.commons.testConnection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapGarden.application.commons.testConnection.service.TestConnectionService;

@Controller
@RequestMapping("/system/testConnection")
public class TestConnectionController {
	@Autowired
	private TestConnectionService testConnectionService;
	

	@RequestMapping
	public @ResponseBody String   testConnection(long id){
		return testConnectionService.testConnection(id);
	}
}
