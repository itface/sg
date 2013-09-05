package com.sapGarden.application.fi.customer.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.constants.SjlxTypeName;
import com.sapGarden.application.commons.service.constructJqgridService.Common_ConstructJqgrid_Service;
import com.sapGarden.application.fi.customer.service.CallFun_GetSapCustomer_TestCall_Service;
import com.sapGarden.system.org.model.User;

@Controller("customer_CallFunController")
@RequestMapping("/application/fi/customer/callFun")
public class CallFunController {
	private final String basePagePath = "/application/synConfig/fi/customer";
	
	private Common_ConstructJqgrid_Service common_ConstructJqgrid_Service;
	private CallFun_GetSapCustomer_TestCall_Service callFun_GetSapCustomer_TestCall_Service;
	@Autowired
	@Qualifier("commonData_ConstructJqgrid_Service")
	public void setCommon_ConstructJqgrid_Service(Common_ConstructJqgrid_Service common_ConstructJqgrid_Service) {
		this.common_ConstructJqgrid_Service = common_ConstructJqgrid_Service;
	}
	@Autowired
	public void setCallFun_GetSapCustomer_TestCall_Service(CallFun_GetSapCustomer_TestCall_Service callFun_GetSapCustomer_TestCall_Service) {
		this.callFun_GetSapCustomer_TestCall_Service = callFun_GetSapCustomer_TestCall_Service;
	}
	//*************************************初始化调用函数页面*****************************************************************
	@RequestMapping
	public ModelAndView callFunConfigIndex(HttpServletRequest request){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type",SjlxTypeName.TYPE_CUSTOMER);
		map.put("basePath", request.getRequestURI());
		map.put("kna1GridOptions",common_ConstructJqgrid_Service.construct(user.getCurrentSapDataCollection(),"Kna1","kna1Grid","客户一般数据","","#kna1Pager"));
		map.put("knb1GridOptions",common_ConstructJqgrid_Service.construct(user.getCurrentSapDataCollection(),"Knb1","knb1Grid","客户公司代码数据","","#knb1Pager"));
		map.put("knvvGridOptions",common_ConstructJqgrid_Service.construct(user.getCurrentSapDataCollection(),"Knvv","knvvGrid","客户销售数据","","#knvvPager"));
		return new ModelAndView(basePagePath+"/callFunctionConfig",map);
	}
	//****************************************************************************************
	@RequestMapping(value = "/testCallFun",method=RequestMethod.GET)
	public @ResponseBody Object test() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return callFun_GetSapCustomer_TestCall_Service.testCall(user.getCurrentSapDataCollection());
	}
	
	
	
	
	
}
