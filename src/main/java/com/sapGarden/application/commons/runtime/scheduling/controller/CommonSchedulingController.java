package com.sapGarden.application.commons.runtime.scheduling.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.runtime.scheduling.exception.SchedulingException;
import com.sapGarden.application.commons.runtime.scheduling.model.CommonScheduling;
import com.sapGarden.application.commons.runtime.scheduling.service.CommonSchedulingService;
import com.sapGarden.application.commons.runtime.scheduling.util.SchedulingUtils;
import com.sapGarden.application.fi.company.model.Company;
import com.sapGarden.application.fi.company.model.CompanyLog;
import com.sapGarden.application.fi.company.service.CompanyService;
import com.sapGarden.application.fi.company.service.SynService;
import com.sapGarden.system.org.model.User;

@Controller
@RequestMapping("/application/commonScheduling/{type1}/{type2}")
public class CommonSchedulingController {
	
	private final String JOBCLASSPATH = "com.sapGarden.application.commons.runtime.scheduling.jobs.JobOf";

	@Autowired
	private CommonSchedulingService commonSchedulingService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private SynService synService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView  page(@PathVariable String type1,@PathVariable String type2){
		type2=type2.toUpperCase();		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long sapclient = user.getCurrentSapDataCollection().getId();
		String jobname = SchedulingUtils.JOBNAME_PREFIX+type2+sapclient;
		String groupname = SchedulingUtils.GROUPNAME_PREFIX+type2+sapclient;
		CommonScheduling job = commonSchedulingService.findOneByJobGroup(groupname, sapclient);
		Map<String,Object> map = new HashMap<String,Object>();
		if(job==null){
			job = new CommonScheduling();
			job.setJobclass(JOBCLASSPATH+type2);
			job.setJobgroup(jobname);
			job.setJobname(groupname);
			job.setSapclient(sapclient);
			job.setJobowner(user.getUsername());
		}
		map.put("job", job);
		map.put("type", type2);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		map.put("currentStatus", job.getJobstatus()==0?"<span style='color:red;'>停止运行</span>":"<span style='color:red;'>定时同步，起始时间："+sf.format(job.getJobbegindate())+"，间隔时间："+job.getIntervalminute()+"分钟</span>");
		if("realtime".equals(type1)){
			return new ModelAndView("/application/synConfig/common/scheduling_realtime",map);
		}
		return new ModelAndView("/application/synConfig/common/scheduling_time",map);
	}
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody void _new(CommonScheduling job)throws SchedulingException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		commonSchedulingService.addOne(job,user.getCurrentSapDataCollection()); 
	}

	@RequestMapping(method=RequestMethod.PUT)
	public @ResponseBody void update(CommonScheduling job)throws SchedulingException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		commonSchedulingService.updateOne(job,user.getCurrentSapDataCollection());
		//synService.syn(user.getCurrentSapDataCollection());
		//companyService.addWithLog("test", user.getCurrentSapDataCollection().getId(), "admin", Company.class, CompanyLog.class, new Company());
	}
	@RequestMapping(method=RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable long id)throws SchedulingException{
		commonSchedulingService.deleteOne(id);
	}
}
