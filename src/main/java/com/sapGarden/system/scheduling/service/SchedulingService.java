package com.sapGarden.system.scheduling.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.sapGarden.system.scheduling.exception.SchedulingException;
import com.sapGarden.system.scheduling.model.JqgridNewScheduling;
import com.sapGarden.system.scheduling.model.Scheduling;

public interface SchedulingService {

	public JSONObject findJsonByJobGroup(String jobGroup,long sapClient);
	public Scheduling findOneByJobGroup(String jobGroup,long sapClient);
	public void addOne(JqgridNewScheduling newScheduling)throws SchedulingException;
	public void updateOne(JqgridNewScheduling jqgridNewScheduling)throws SchedulingException;
	public void deleteOne(long id)throws SchedulingException;
	public List<Scheduling> findAllAvailableScheduling();
}
