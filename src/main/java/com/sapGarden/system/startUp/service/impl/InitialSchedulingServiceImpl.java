package com.sapGarden.system.startUp.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapGarden.system.scheduling.common.SchedulingAction;
import com.sapGarden.system.scheduling.constans.SchedulingConstans;
import com.sapGarden.system.scheduling.exception.SchedulingException;
import com.sapGarden.system.scheduling.model.Scheduling;
import com.sapGarden.system.scheduling.service.SchedulingService;
import com.sapGarden.system.startUp.service.StartUpInitialService;

@Service("initialSchedulingService")
public class InitialSchedulingServiceImpl implements StartUpInitialService{

	private final static transient Logger log = LoggerFactory.getLogger("errorLog");
	private SchedulingAction schedulingAction;
	private SchedulingService schedulingService;
	@Autowired
	public InitialSchedulingServiceImpl(SchedulingAction schedulingAction,SchedulingService schedulingService){
		this.schedulingAction=schedulingAction;
		this.schedulingService=schedulingService;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String now = sd.format(Calendar.getInstance().getTime());
		try {
			List<Scheduling> list = (List<Scheduling>)schedulingService.findAllAvailableScheduling();
			if(list!=null&&list.size()>0){
				for(Scheduling scheduling : list){
					if(SchedulingConstans.JOB_EXECUTEFREQUENCY_STARTUP.equals(scheduling.getExecuteFrequency())){
						schedulingAction.addStartUpTrigger(scheduling);
					}else{
						schedulingAction.addTrigger(scheduling);
					}
				}
			}
		} catch (SchedulingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(now,e);
		}catch(RuntimeException e){
			e.printStackTrace();
			log.error(now,e);
		}
	}

}
