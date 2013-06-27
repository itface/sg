package com.sapGarden.application.commons.mainPage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.system.org.model.User;
@Controller
@RequestMapping("/application/{type1}/{type2}")
public class MainPageController {
	private final String basePagePath = "/application/synConfig/common/main";
	@RequestMapping
	public ModelAndView index(HttpServletRequest request,@PathVariable String type1,@PathVariable String type2){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sapClient", user.getCurrentSapDataCollection().getId());
		map.put("basePath", request.getRequestURI());
		return new ModelAndView(basePagePath,map);
	}
}
