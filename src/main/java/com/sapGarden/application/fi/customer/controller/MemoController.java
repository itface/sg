package com.sapGarden.application.fi.customer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.global.exception.JpaException;

@Controller("customer_MemoController")
@RequestMapping("/application/fi/customer/memo")
public class MemoController {
	private final String basePagePath = "/application/synConfig/fi/customer";

	@RequestMapping
	public ModelAndView index() throws JpaException{
		Map<String,Object> map = new HashMap<String,Object>();
		return new ModelAndView(basePagePath+"/memo",map);
	}
}
