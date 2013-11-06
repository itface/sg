package com.sapGarden.application.commons.runtime.scheduling.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.baseDao.BaseDao;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.runtime.scheduling.exception.SchedulingException;
import com.sapGarden.application.commons.runtime.scheduling.model.CommonScheduling;
import com.sapGarden.application.commons.runtime.scheduling.service.CommonSchedulingService;
import com.sapGarden.application.commons.runtime.scheduling.util.SchedulingUtils;
@Service
public class CommonSchedulingServiceImpl implements CommonSchedulingService{

	@Autowired
	private BaseDao<CommonScheduling> dao;
	@Autowired
	private SchedulingUtils schedulingUtil;
	
	@Override
	public CommonScheduling findOneByJobGroup(String jobGroup, long sapClient) {
		// TODO Auto-generated method stub
		return dao.findSingleResult("from CommonScheduling t where t.jobgroup=?1 and t.sapclient=?2", new Object[]{jobGroup,sapClient});
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addOne(CommonScheduling job,SapDataCollection sapDataCollection) throws SchedulingException {
		// TODO Auto-generated method stub
		dao.persist(job);
		schedulingUtil.addTrigger(job,sapDataCollection);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void updateOne(CommonScheduling job,SapDataCollection sapDataCollection) throws SchedulingException{
		// TODO Auto-generated method stub
		dao.update(job);
		schedulingUtil.update(job,sapDataCollection);
		if(job.getJobstatus()==0){
			dao.deleteById(CommonScheduling.class, job.getId());
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteOne(long id) throws SchedulingException {
		// TODO Auto-generated method stub
		CommonScheduling job = dao.find(CommonScheduling.class, id);
		schedulingUtil.remove(job);
		dao.deleteById(CommonScheduling.class, id);
	}

	@Override
	public List<CommonScheduling> findAllAvailableScheduling() {
		// TODO Auto-generated method stub
		return dao.find("from CommonScheduling t where t.jobstatus=1 and t.jobtype='TIME'", new Object[]{});
	}

	@Override
	public void initAllJob(SapDataCollection sapDataCollection) throws SchedulingException {
		// TODO Auto-generated method stub
		List<CommonScheduling> list = this.findAllAvailableScheduling();
		if(list!=null&&list.size()>0){
			for(CommonScheduling job : list){
				schedulingUtil.addTrigger(job,sapDataCollection);
			}
		}
	}

}
