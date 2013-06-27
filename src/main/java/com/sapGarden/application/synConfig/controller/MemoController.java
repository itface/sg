package com.sapGarden.application.synConfig.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.global.exception.JpaException;

@Controller
@RequestMapping("/application/synConfig/{type}/memo")
public class MemoController {

	@RequestMapping
	public ModelAndView index(@PathVariable String type) throws JpaException{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", type);
		return new ModelAndView("/application/synConfig/memo",map);
	}
}
