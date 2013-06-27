package com.sapGarden.system.login.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.dataCollection.service.SapDataCollectionService;
import com.sapGarden.system.login.service.LoginService;
import com.sapGarden.system.org.model.User;
@Controller
@RequestMapping
public class LoginController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private SapDataCollectionService sapDataCollectionService;
	@RequestMapping("/login")
	public ModelAndView getLoginPage(@RequestParam(value = "error", required = false) boolean error,@RequestParam(value = "expired", required = false) boolean expired,ModelMap model){
		if(error){
			model.put("error","You have entered an invalid username or password!");
		}
		if(expired){
			model.put("error","己登录");
		}
		Map map = new HashMap();
		List<SapDataCollection> list = sapDataCollectionService.findAll();
		SapDataCollection client = new SapDataCollection();
		client.setId(0);
		client.setAlias("管理员");
		list.add(client);
		map.put("sapDataCollection", list);
		return new ModelAndView("/login",map);
	}
	@RequestMapping(value=("/common/front"),method = RequestMethod.GET)
	
	public ModelAndView loginCommon(HttpServletRequest request) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new ModelAndView("/front_main","sapclient",user.getCurrentSapDataCollection().getAlias());
	}
	@RequestMapping(value=("/common/console"),method = RequestMethod.GET)
	public ModelAndView loginAdmin(){
		return new ModelAndView("/console_main");
	}
	@RequestMapping(value=("/common/denied"),method = RequestMethod.GET)
	public ModelAndView loginDenied(){
		return new ModelAndView("/deniedPage");
	}
	@RequestMapping(value=("/common/timeout"),method = RequestMethod.GET)
	public ModelAndView loginTimeout(){
		return new ModelAndView("/timeout");
	}
	@RequestMapping(value=("/common/sessionDenied"),method = RequestMethod.GET)
	public ModelAndView loginSessionDenied(){
		return new ModelAndView("/sessionProtect");
	}
	
	@RequestMapping(value=("/common/menuJson"))
	@ResponseBody
	public String menuJson(HttpServletRequest request,String method){
		/*
		String s = "";
		if("menu1".equals(parentFuncId)){
			s="{text:'menu1',qtip:'menu1',parentFuncId:'',id:'1'}";
		}else if("menu1.1".equals(parentFuncId)){
			s="{text:'menu1.1',qtip:'menu1.1',parentFuncId:'1',id:'1.1'}";
		}else if("menu2".equals(parentFuncId)){
			s="{text:'menu2',qtip:'menu2',parentFuncId:'',id:'2'}";
		}else{
			s="{[{text:'menu1',qtip:'menu1',parentFuncId:'',id:'1'},{text:'menu1.1',qtip:'menu1.1',parentFuncId:'1',id:'1.1'},{text:'menu2',qtip:'menu2',parentFuncId:'',id:'2'}]}";
		}
		return s;
		*/
		//String method = request.getParameter("method");
		StringBuilder sb = new StringBuilder();
		if ("basic".equals(method)) {
            sb.append("[")
                .append("{").append("text:\"").append("adpter").append("\", qtip: \"").append("qtip鎻愮ず锛欰DPTER").append("\",");
                sb.append("children: [")
                    .append("{").append("text:\"").append("ext").append("\", qtip: \"").append("qtip鎻愮ず锛歟xt").append("\",")
                    .append("leaf: true").append("},")
                    .append("{").append("text:\"").append("jquery")
                    .append("\",").append("leaf: true").append("}]").append("},")
                    .append("{").append("text:\"").append("air").append("\",");
                    sb.append("children: [")
                        .append("{").append("text:\"").append("air.jsb")
                        .append("\",").append("leaf: true").append("},")
                        .append("{").append("text:\"").append("ext-air.js")
                        .append("\",").append("leaf: true").append("}]").append("},")
                        .append("{").append("text:\"").append("readMe.text").append("\",")
                        .append("leaf: true}");
            sb.append("]");
        }
        if ("constom".equals(method)) {
            sb.append("[")
                .append("{").append("text:\"").append("adpter").append("\", tip: \"").append("ADPTER").append("\",");
                sb.append("children:[")
                    .append("{").append("text:\"").append("ext").append("\", tip: \"").append("EXT").append("\",")
                    .append("leaf: true").append("},")
                    .append("{").append("text:\"").append("jquery").append("\", tip: \"").append("JQUERY").append("\",")
                    .append("leaf: true").append("}]},")
                    .append("{").append("text:\"").append("air").append("\", tip: \"").append("AIR").append("\",");
                    sb.append("children:[")
                        .append("{").append("text:\"").append("air.jsb").append("\", tip: \"").append("AIR.JSB").append("\",")
                        .append("leaf: true").append("},")
                        .append("{").append("text:\"").append("ext-air.js").append("\", tip: \"").append("EXT-AIR.JS").append("\",")
                        .append("leaf: true").append("}]").append("},")
                        .append("{").append("text:\"").append("readMe.text").append("\", tip: \"").append("README.TEXT").append("\",")
                        .append("leaf: true}");
            sb.append("]");
        }
        return sb.toString();
	}
}
