package com.sapGarden.application.commons.runtime.scheduling.service;

import java.util.List;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.runtime.scheduling.exception.SchedulingException;
import com.sapGarden.application.commons.runtime.scheduling.model.CommonScheduling;

public interface CommonSchedulingService {


	public CommonScheduling findOneByJobGroup(String jobGroup,long sapClient);
	public void addOne(CommonScheduling job,SapDataCollection sapDataCollection)throws SchedulingException;
	public void updateOne(CommonScheduling job,SapDataCollection sapDataCollection)throws SchedulingException;
	public void deleteOne(long id)throws SchedulingException;
	/**
	 * 取当前sapclient中所有开启的定时任务(本系统只有一种定时任务jobtype为TIME,另一种是实时同步，不需要定时任务)，因为coonection中已经绑定了sapclient，所以不需要再设置
	 * @return
	 */
	public List<CommonScheduling> findAllAvailableScheduling();
	/**
	 * 平台启动时，初始化所在sapclient的定时任务
	 * @throws SchedulingException
	 */
	public void initAllJob(SapDataCollection sapDataCollection) throws SchedulingException;
}
