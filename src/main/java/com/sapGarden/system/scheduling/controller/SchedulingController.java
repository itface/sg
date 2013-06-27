package com.sapGarden.system.scheduling.controller;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.global.exception.JpaException;
import com.sapGarden.global.exception.ObjectToJsonException;
import com.sapGarden.system.org.model.User;
import com.sapGarden.system.scheduling.exception.SchedulingException;
import com.sapGarden.system.scheduling.model.JqgridNewScheduling;
import com.sapGarden.system.scheduling.service.SchedulingService;

@Controller
@RequestMapping(value=("/system/scheduling/{jobGroup}"))
public class SchedulingController {

	SchedulingService schedulingService;
	@Autowired
	public void setSchedulingService(SchedulingService schedulingService) {
		this.schedulingService = schedulingService;
	}
	@RequestMapping
	public ModelAndView index(@PathVariable String jobGroup){
		Map<String,Object> map = new HashMap<String,Object>();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		map.put("jobGroup", jobGroup);
		map.put("sapclient", user.getCurrentSapDataCollection().getId());
		map.put("owner", user.getUsername());
		return new ModelAndView("/sys_schedule_manage",map);
	}
	@RequestMapping(value=("/data"),method=RequestMethod.GET)
	public @ResponseBody Object showData(@PathVariable String jobGroup) throws ObjectToJsonException, JpaException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject json = schedulingService.findJsonByJobGroup(jobGroup,user.getCurrentSapDataCollection().getId());
		return json==null?"{}":json;
	}
	@RequestMapping(value=("/data"),method=RequestMethod.POST)
	public @ResponseBody void _new(JqgridNewScheduling jqgridNewScheduling,@PathVariable String jobGroup) throws SchedulingException, JpaException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		jqgridNewScheduling.setJobGroup(jobGroup);
		jqgridNewScheduling.setSapclient(user.getCurrentSapDataCollection().getId());
		schedulingService.addOne(jqgridNewScheduling);
	}

	@RequestMapping(value=("/data/{id}"),method=RequestMethod.PUT)
	public @ResponseBody void update(JqgridNewScheduling jqgridNewScheduling) throws SchedulingException, JpaException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		jqgridNewScheduling.setSapclient(user.getCurrentSapDataCollection().getId());
		schedulingService.updateOne(jqgridNewScheduling);
	}
	@RequestMapping(value=("/data/{id}"),method=RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable long id) throws SchedulingException, JpaException{
		schedulingService.deleteOne(id);
	}
}
