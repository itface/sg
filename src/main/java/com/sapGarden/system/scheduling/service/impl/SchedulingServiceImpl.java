package com.sapGarden.system.scheduling.service.impl;



import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dao.ExtendDao;
import com.sapGarden.global.jqgrid.model.Jqgrid_DataJson;
import com.sapGarden.system.scheduling.common.SchedulingAction;
import com.sapGarden.system.scheduling.constans.SchedulingConstans;
import com.sapGarden.system.scheduling.exception.SchedulingException;
import com.sapGarden.system.scheduling.model.JqgridNewScheduling;
import com.sapGarden.system.scheduling.model.Scheduling;
import com.sapGarden.system.scheduling.service.SchedulingService;
@Service("schedulingService")
public class SchedulingServiceImpl implements SchedulingService{

	@Autowired
	private ExtendDao<Scheduling> extendDao;
	SchedulingAction schedulingAction;
	
	@Autowired
	public void setSchedulingAction(SchedulingAction schedulingAction) {
		this.schedulingAction = schedulingAction;
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addOne(JqgridNewScheduling newScheduling)throws SchedulingException{
		// TODO Auto-generated method stub
		Scheduling scheduling = new Scheduling(newScheduling);
		Scheduling job=(Scheduling)extendDao.persist(scheduling);
		schedulingAction.addTrigger(job);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteOne(long id)throws SchedulingException {
		// TODO Auto-generated method stub
		Scheduling job = (Scheduling)extendDao.find(Scheduling.class, id);
		extendDao.deleteById(Scheduling.class,id);
		schedulingAction.remove(job);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject findJsonByJobGroup(String jobGroup,long sapClient) {
		// TODO Auto-generated method stub
		List<Scheduling> list =extendDao.find("from Scheduling t where t.jobGroup=? and t.sapclient=?", new Object[]{jobGroup,sapClient});
		if(list!=null&&list.size()>0){
			Jqgrid_DataJson jsonData = new Jqgrid_DataJson(0,0,0,list);
			//JsonConfig jsonConfig = new JsonConfig();
			//因为的Resource和role是多对多关联的，互相关联，生成json对象时会形成死循环，所以排除user对象和roles对象
			//jsonConfig.setExcludes(new String[]{"jobEndDate","jobBeginDate"});
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			//把值为null的值转换为空字符串，否则前台不能接收json对象
			String jsonStr = jsonObject.toString();
			jsonStr=jsonStr.replaceAll("null", "\"\"");
			jsonObject=JSONObject.fromObject(jsonStr);
			return jsonObject;
		}
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateOne(JqgridNewScheduling jqgridNewScheduling) throws SchedulingException{
		// TODO Auto-generated method stub
		Scheduling scheduling = new Scheduling(jqgridNewScheduling);
		Scheduling job = (Scheduling)extendDao.save(scheduling);
		schedulingAction.update(job);
	}
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Scheduling findOneByJobGroup(String jobGroup,long sapClient){
		// TODO Auto-generated method stub
		Scheduling scheduling = (Scheduling)extendDao.findSingleResult("from Scheduling t where t.jobGroup=? and t.sapclient=?", new Object[]{jobGroup,sapClient});
		return scheduling;

	}
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Scheduling> findAllAvailableScheduling() {
		// TODO Auto-generated method stub
		return extendDao.find("from Scheduling s where s.executeFrequency!='"+SchedulingConstans.JOB_EXECUTEFREQUENCY_SHUTDOWN+"'");
	}

}
