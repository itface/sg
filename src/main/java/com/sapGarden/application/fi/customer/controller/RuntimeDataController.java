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

import com.sapGarden.application.commons.service.commonService.CommonService;
import com.sapGarden.application.commons.service.constructJqgridService.Common_ConstructJqgrid_Service;
import com.sapGarden.application.fi.customer.model.Kna1;
import com.sapGarden.application.fi.customer.model.Knb1;
import com.sapGarden.application.fi.customer.model.Knvv;
import com.sapGarden.global.json.JsonUtils;
import com.sapGarden.system.org.model.User;

@Controller("customer_RuntimeDataController")
@RequestMapping("/application/fi/customer/runtimeData")
public class RuntimeDataController {
	private final String basePagePath = "/application/synConfig/fi/customer";

	private CommonService commonService;
	private Common_ConstructJqgrid_Service common_ConstructJqgrid_Service;
	@Autowired
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	@Autowired
	@Qualifier("commonData_ConstructJqgrid_Service")
	public void setCommon_ConstructJqgrid_Service(Common_ConstructJqgrid_Service common_ConstructJqgrid_Service) {
		this.common_ConstructJqgrid_Service = common_ConstructJqgrid_Service;
	}

	//*************************************初始化调用函数页面*****************************************************************
	@RequestMapping
	public ModelAndView index(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("type", CustomerContants.TYPE);
		map.put("kna1GridOptions",common_ConstructJqgrid_Service.construct(user.getCurrentSapDataCollection(),"Kna1","kna1Grid","客户一般数据","/application/fi/customer/runtimeData/kna1","#kna1Pager"));
		map.put("knb1GridOptions",common_ConstructJqgrid_Service.construct(user.getCurrentSapDataCollection(),"Knb1","knb1Grid","客户公司代码数据","/application/fi/customer/runtimeData/knb1","#knb1Pager"));
		map.put("knvvGridOptions",common_ConstructJqgrid_Service.construct(user.getCurrentSapDataCollection(),"Knvv","knvvGrid","客户销售数据","/application/fi/customer/runtimeData/knvv","#knvvPager"));
		return new ModelAndView(basePagePath+"/runtimeData",map);
	}
	//*************************************查询*****************************************************************
//	@RequestMapping(value = "/loadData",method=RequestMethod.GET)
//	public @ResponseBody Object loadData(@RequestParam String cur_page,@RequestParam String dir){
//		return JsonUtils.objectToJSONObject("{\"totalRecords\":3,\"curPage\":1,\"data\":[{\"country\":1,\"name\":2,\"id\":3},{\"country\":7,\"id\":5,\"name\":8}]}",null);
//	}
	@RequestMapping(value = "/kna1",method=RequestMethod.GET)
	public @ResponseBody Object getKna1Config(@RequestParam String filters,@RequestParam int page,@RequestParam int rows){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject json = commonService.findJsonBySapclientAndFilters(Kna1.class,page, rows, user.getCurrentSapDataCollection().getId(), filters);
		return json==null?"{}":json;
	}
	@RequestMapping(value = "/knb1",method=RequestMethod.GET)
	public @ResponseBody Object getKnb1Config(@RequestParam String filters,@RequestParam int page,@RequestParam int rows){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject json = commonService.findJsonBySapclientAndFilters(Knb1.class,page, rows, user.getCurrentSapDataCollection().getId(), filters);
		return json==null?"{}":json;
	}
	@RequestMapping(value = "/knvv",method=RequestMethod.GET)
	public @ResponseBody Object getKnvvConfig(@RequestParam String filters,@RequestParam int page,@RequestParam int rows){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject json = commonService.findJsonBySapclientAndFilters(Knvv.class,page, rows, user.getCurrentSapDataCollection().getId(), filters);
		return json==null?"{}":json;
	}
}
