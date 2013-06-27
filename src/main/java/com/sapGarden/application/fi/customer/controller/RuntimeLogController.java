package com.sapGarden.application.fi.customer.controller;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.service.commonService.CommonLogService;
import com.sapGarden.application.commons.service.constructJqgridService.Common_ConstructJqgrid_Service;
import com.sapGarden.application.fi.customer.constants.CustomerContants;
import com.sapGarden.application.fi.customer.model.Kna1Log;
import com.sapGarden.application.fi.customer.model.Knb1Log;
import com.sapGarden.application.fi.customer.model.KnvvLog;
import com.sapGarden.system.org.model.User;

@Controller("customer_RuntimeLogController")
@RequestMapping("/application/fi/customer/log")
public class RuntimeLogController {

	private final String basePagePath = "/application/synConfig/fi/customer";

	private CommonLogService commonLogService;
	private Common_ConstructJqgrid_Service common_ConstructJqgrid_Service;
	@Autowired
	public void setCommonLogService(CommonLogService commonLogService) {
		this.commonLogService = commonLogService;
	}
	@Autowired
	@Qualifier("commonLog_ConstructJqgrid_Service")
	public void setCommon_ConstructJqgrid_Service(Common_ConstructJqgrid_Service common_ConstructJqgrid_Service) {
		this.common_ConstructJqgrid_Service = common_ConstructJqgrid_Service;
	}

	//*************************************初始化调用函数页面*****************************************************************
	@RequestMapping
	public ModelAndView index() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", CustomerContants.TYPE);
		map.put("kna1LogGridOptions",common_ConstructJqgrid_Service.construct(user.getCurrentSapDataCollection(),"Kna1","kna1LogGrid","客户一般数据日志","/application/fi/customer/log/kna1Log","#kna1LogPager"));
		map.put("knb1LogGridOptions",common_ConstructJqgrid_Service.construct(user.getCurrentSapDataCollection(),"Knb1","knb1LogGrid","客户公司代码数据日志","/application/fi/customer/log/knb1Log","#knb1LogPager"));
		map.put("knvvLogGridOptions",common_ConstructJqgrid_Service.construct(user.getCurrentSapDataCollection(),"Knvv","knvvLogGrid","客户销售数据日志","/application/fi/customer/log/knvvLog","#knvvLogPager"));
		return new ModelAndView(basePagePath+"/runtimeLog",map);
	}
	//*************************************查询日志*****************************************************************
	@RequestMapping(value = "/kna1Log",method=RequestMethod.GET)
	public @ResponseBody Object getKna1LogConfig(String filters,@RequestParam int page,@RequestParam int rows){		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject json = commonLogService.findSuccessLogBySapclientAndFilters(Kna1Log.class,page, rows, user.getCurrentSapDataCollection().getId(), filters);
		return json==null?"{}":json;
	}
	@RequestMapping(value = "/knb1Log",method=RequestMethod.GET)
	public @ResponseBody Object getKnb1LogConfig(String filters,@RequestParam int page,@RequestParam int rows){		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject json = commonLogService.findSuccessLogBySapclientAndFilters(Knb1Log.class,page, rows, user.getCurrentSapDataCollection().getId(), filters);
		return json==null?"{}":json;
	}
	@RequestMapping(value = "/knvvLog",method=RequestMethod.GET)
	public @ResponseBody Object getKnvvLogConfig(String filters,@RequestParam int page,@RequestParam int rows){		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject json = commonLogService.findSuccessLogBySapclientAndFilters(KnvvLog.class,page, rows, user.getCurrentSapDataCollection().getId(), filters);
		return json==null?"{}":json;
	}

	
}
