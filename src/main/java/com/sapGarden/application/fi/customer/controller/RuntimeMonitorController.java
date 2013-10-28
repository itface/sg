package com.sapGarden.application.fi.customer.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.constants.SjlxTypeName;
import com.sapGarden.application.commons.excel.service.CommonExpertExcelService;
import com.sapGarden.application.commons.log.service.CommonLogService;
import com.sapGarden.application.commons.service.constructJqgridService.CommonConstructJqgridService;
import com.sapGarden.system.org.model.User;

@Controller("customer_runtimeMonitor")
@RequestMapping("/application/fi/customer/runtimeMonitor")
public class RuntimeMonitorController {


	@Autowired
	@Qualifier("commonMonitor")
	private CommonConstructJqgridService commonConstructJqgridService;
	@Autowired
	@Qualifier("exportCustomerExcel")
	private CommonExpertExcelService commonExpertExcelService;
	@Autowired
	@Qualifier("commonLogService")
	private CommonLogService companyLogService;
	
	//*************************************初始化调用函数页面*****************************************************************
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		//c.add(Calendar.DATE, 1);
		String edate = sf.format(c.getTime());
		c.add(Calendar.MONTH, -1);
		String bdate = sf.format(c.getTime());
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.WEEK_OF_YEAR, -1);
		String lastweekdate = sf.format(c.getTime()); 
		map.put("bdate", bdate);
		map.put("edate", edate);
		map.put("bdateOfMonthRange", bdate);
		map.put("edateOfMonthRange", edate);
		map.put("bdateOfWeekRange", lastweekdate);
		map.put("edateOfWeekRange", edate);
		map.put("kna1GridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_CUSTOMER_KNA1));
		map.put("knb1GridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_CUSTOMER_KNB1));
		map.put("knvvGridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_CUSTOMER_KNVV));
		return new ModelAndView("/application/synConfig/fi/customer/runtimeMonitor",map);
	}
	@RequestMapping(value="/findLog/kna1")
	public @ResponseBody Object findKna1Log(String companyCode,String optflag,String bdate,String edate,int rows,int page,String sidx,String sord){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject json = companyLogService.findJqgridData(user.getCurrentSapDataCollection(),"Kna1Log", null, optflag, bdate, edate,rows,page,sidx,sord);
		return json;
	}
	@RequestMapping(value="/findLog/knb1")
	public @ResponseBody Object findKnb1Log(String companyCode,String optflag,String bdate,String edate,int rows,int page,String sidx,String sord){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject json = companyLogService.findJqgridData(user.getCurrentSapDataCollection(),"Knb1Log", null, optflag, bdate, edate,rows,page,sidx,sord);
		return json;
	}
	@RequestMapping(value="/findLog/knvv")
	public @ResponseBody Object findKnvvLog(String companyCode,String optflag,String bdate,String edate,int rows,int page,String sidx,String sord){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject json = companyLogService.findJqgridData(user.getCurrentSapDataCollection(),"KnvvLog", null, optflag, bdate, edate,rows,page,sidx,sord);
		return json;
	}
	@RequestMapping(value="/exportLog")
	public @ResponseBody void exportLog(HttpServletResponse response,String companyCode,String optflag,String bdate,String edate) throws SecurityException, IllegalArgumentException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, URISyntaxException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		commonExpertExcelService.exportLogExcel(user.getCurrentSapDataCollection(), null, optflag, bdate, edate, response);
	}
}
