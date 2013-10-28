package com.sapGarden.application.fi.customer.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.basetable.service.BaseTableService;
import com.sapGarden.application.commons.constants.SjlxTypeName;
import com.sapGarden.application.commons.runtime.baseinfo.model.RuntimeBasicInfo;
import com.sapGarden.application.commons.runtime.baseinfo.service.Runtime_BasicInfo_Service;
import com.sapGarden.application.commons.runtime.scheduling.model.CommonScheduling;
import com.sapGarden.application.commons.runtime.scheduling.service.CommonSchedulingService;
import com.sapGarden.application.commons.runtime.scheduling.util.SchedulingUtils;
import com.sapGarden.application.commons.runtime.tableinfo.model.RuntimeTableInfo;
import com.sapGarden.application.commons.runtime.tableinfo.service.Runtime_TableInfo_Service;
import com.sapGarden.application.fi.customer.service.RuntimeConfig_BasicInfo_Service;
import com.sapGarden.system.org.model.User;

@Controller("customer_RuntimeConfigController")
@RequestMapping("/application/fi/customer")
public class RuntimeConfigController {

	private final String basePagePath = "/application/synConfig/fi/customer";
	private final String jobName = "com.sapGarden.application.fi.customer.jobs.CustomerJob";
	
	
	@Autowired
	private CommonSchedulingService jobService;
	//private SchedulingService schedulingService;
	private Runtime_TableInfo_Service runtime_TableInfo_Service;
	private Runtime_BasicInfo_Service runtime_BasicInfo_Service;
	@Autowired
	private RuntimeConfig_BasicInfo_Service runtimeConfig_BasicInfo_Service;
	@Autowired
	private BaseTableService baseTableService;


	@Autowired
	public void setRuntime_TableInfo_Service(Runtime_TableInfo_Service runtime_TableInfo_Service) {
		this.runtime_TableInfo_Service = runtime_TableInfo_Service;
	}
	@Autowired
	public void setRuntime_BasicInfo_Service(Runtime_BasicInfo_Service runtime_BasicInfo_Service) {
		this.runtime_BasicInfo_Service = runtime_BasicInfo_Service;
	}
	//*************************************初始化运行设置页面*****************************************************************
	@RequestMapping(value=("/runtimeConfig"),method = RequestMethod.GET)
	public ModelAndView runtimeConfigIndex(HttpServletRequest request){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sapclient", user.getCurrentSapDataCollection().getId());
		
		long sapclient = user.getCurrentSapDataCollection().getId();
		String jobname = SchedulingUtils.JOBNAME_PREFIX+SjlxTypeName.TYPE_CUSTOMER+sapclient;
		String groupname = SchedulingUtils.GROUPNAME_PREFIX+SjlxTypeName.TYPE_CUSTOMER+sapclient;
		CommonScheduling job = jobService.findOneByJobGroup(groupname, sapclient);
		map.put("sapclient", sapclient);
		if(job!=null&&job.getJobstatus()==1){
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			map.put("jobMemo", "<span style='font-weight:normal;color:red;'>实时同步，起始时间："+sf.format(job.getJobbegindate())+"。</span>");
		}else{
			map.put("jobMemo", "<span style='color:red;font-weight:normal;'>停止运行</span>");
		}
		return new ModelAndView(basePagePath+"/runtimeConfig",map);
	}
	//*************************************运行设置-〉基本信息***************************************************************
	@RequestMapping(value=("/runtimeConfig/basicInfo"),method=RequestMethod.POST)
	public @ResponseBody void _newbasicInfo(RuntimeBasicInfo runtimeBasicInfo){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		List<BaseTableModel> kna1List = baseTableService.getBaseTableByTableName("KNA1");
//		List<BaseTableModel> knb1List = baseTableService.getBaseTableByTableName("KNB1");
//		List<BaseTableModel> knvvList = baseTableService.getBaseTableByTableName("KNVV");
		//runtimeConfig_BasicInfo_Service.addBasicInfo(runtimeBasicInfo, user.getCurrentSapClientModel().getId(), null, null, null);
		//runtimeConfig_BasicInfo_Service.addBasicInfo(runtimeBasicInfo, user.getCurrentSapClientModel().getId());
		//testService.test(runtimeBasicInfo, user.getCurrentSapClientModel().getId());
		runtime_BasicInfo_Service.addOne(runtimeBasicInfo,new String[]{"KNA1","KNB1","KNVV"},user.getCurrentSapDataCollection().getId());
	}
	@RequestMapping(value=("/runtimeConfig/basicInfo/{id}"),method=RequestMethod.PUT)
	public @ResponseBody void updatebasicInfo(RuntimeBasicInfo runtimeBasicInfo){
		runtime_BasicInfo_Service.updateOne(runtimeBasicInfo);
	}
	
}
