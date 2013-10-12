package com.sapGarden.application.fi.company.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.sapGarden.application.commons.service.constructJqgridService.CommonConstructJqgridService;
import com.sapGarden.application.fi.company.service.CompanyLogService;
import com.sapGarden.system.org.model.User;

@Controller("company_runtimeMonitor")
@RequestMapping("/application/fi/company/runtimeMonitor")
public class RuntimeMonitorController {


	@Autowired
	@Qualifier("commonMonitor")
	private CommonConstructJqgridService commonConstructJqgridService;
	@Autowired
	private CompanyLogService companyLogService;
	
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
		map.put("monitorGridOptions",commonConstructJqgridService.construct(user.getCurrentSapDataCollection(),SjlxTypeName.TYPE_COMPANY));
		return new ModelAndView("/application/synConfig/fi/company/runtimeMonitor",map);
	}
	@RequestMapping(value="/findLog")
	public @ResponseBody Object findLog(String companyCode,String optflag,String bdate,String edate,int rows,int page,String sidx,String sord){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject json = companyLogService.findJqgridData(user.getCurrentSapDataCollection(), companyCode, optflag, bdate, edate,rows,page,sidx,sord);
		return json;
	}
}
