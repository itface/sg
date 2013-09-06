package com.sapGarden.application.fi.company.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.constants.SjlxTypeName;
import com.sapGarden.application.commons.runtime.scheduling.model.CommonScheduling;
import com.sapGarden.application.commons.runtime.scheduling.service.CommonSchedulingService;
import com.sapGarden.application.commons.runtime.scheduling.util.SchedulingUtils;
import com.sapGarden.global.exception.JpaException;
import com.sapGarden.system.org.model.User;

@Controller
@RequestMapping("/application/fi/company/runtimeConfig")
public class RuntimeConfigController {


	@Autowired
	private CommonSchedulingService jobService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView runtimeConfigIndex() throws JpaException{

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long sapclient = user.getCurrentSapDataCollection().getId();
		String jobname = SchedulingUtils.JOBNAME_PREFIX+SjlxTypeName.TYPE_COMPANY+sapclient;
		String groupname = SchedulingUtils.GROUPNAME_PREFIX+SjlxTypeName.TYPE_COMPANY+sapclient;
		CommonScheduling job = jobService.findOneByJobGroup(groupname, sapclient);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sapclient", sapclient);
		if(job!=null&&job.getJobstatus()==1){
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map.put("jobMemo", "<span style='font-weight:normal;'>定时同步运行中;起始时间："+sf.format(job.getJobbegindate())+"，间隔时间："+job.getIntervalminute()+"分钟</span>");
		}else{
			map.put("jobMemo", "<span style='color:red;font-weight:normal;'>停止运行</span>");
		}
		return new ModelAndView("/application/synConfig/fi/company/runtimeConfig",map);
	}

	
	
	
	
}
