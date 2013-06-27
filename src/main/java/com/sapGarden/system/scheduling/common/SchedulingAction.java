package com.sapGarden.system.scheduling.common;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.sapGarden.system.scheduling.constans.SchedulingConstans;
import com.sapGarden.system.scheduling.exception.SchedulingException;
import com.sapGarden.system.scheduling.model.Scheduling;


@Component
public class SchedulingAction {


	SchedulerFactoryBean schedulerFactory;

	@Autowired
	public void setSchedulerFactory(SchedulerFactoryBean schedulerFactory) {
		this.schedulerFactory = schedulerFactory;
	}
	/**
	 * 添加平台启动执行任务
	 * @param scheduling
	 * @throws SchedulingException
	 */
	public void addStartUpTrigger(Scheduling scheduling) throws SchedulingException{
		if(scheduling!=null){
			try {
				Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
				SimpleTrigger  trigger = (SimpleTrigger)scheduler.getTrigger(scheduling.getTriggerName(), scheduling.getTriggerGroup());
				if(trigger==null){
					JobDetail jobDetail = new JobDetail(scheduling.getRealJobName(),scheduling.getJobGroup(),Class.forName(scheduling.getJobClass()));   
					JobDataMap dataMap = jobDetail.getJobDataMap();
					dataMap.put("owner", scheduling.getOwner());
					dataMap.put("sapClient", scheduling.getSapclient());
					//平台启动时执行的计划任务，延迟5秒
					Date triggerTime = new Date((new Date()).getTime()+5000l);
					SimpleTrigger simpleTrigger = new SimpleTrigger(scheduling.getTriggerName(), scheduling.getTriggerGroup(),triggerTime);
					scheduler.scheduleJob(jobDetail, simpleTrigger);
					if(!scheduler.isStarted()){
						scheduler.start();
					}
				}
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				throw new SchedulingException("添加系统启动计划任务【"+scheduling.getJobName()+"】异常",e);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				throw new SchedulingException("添加系统启动计划任务【"+scheduling.getJobName()+"】异常,没有找到计划任务执行类",e);
			}
		}
	}
	/**
	 * 添加simpleTrigger
	 * @param scheduling
	 * @throws SchedulingException
	 */
	public void addSimpleTrigger(Scheduling scheduling) throws SchedulingException{
		if(scheduling!=null){
			try {
				Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
				SimpleTrigger  trigger = (SimpleTrigger)scheduler.getTrigger(scheduling.getTriggerName(), scheduling.getTriggerGroup());
				if(trigger==null){
					JobDetail jobDetail = new JobDetail(scheduling.getRealJobName(),scheduling.getJobGroup(),Class.forName(scheduling.getJobClass()));   
					JobDataMap dataMap = jobDetail.getJobDataMap();
					dataMap.put("owner", scheduling.getOwner());
					dataMap.put("sapClient", scheduling.getSapclient());
					SimpleTrigger simpleTrigger = new SimpleTrigger(scheduling.getTriggerName(), scheduling.getTriggerGroup(),SimpleTrigger.REPEAT_INDEFINITELY,scheduling.getRepeatInterval()*60*1000);
					//设置开始时间和结束时间
				    if(scheduling.getJobBeginDate()!=null){
				    	simpleTrigger.setStartTime(new Date(scheduling.getJobBeginDate().getTime()));
				    }
				    if(scheduling.getJobEndDate()!=null){
				    	simpleTrigger.setEndTime(new Date(scheduling.getJobEndDate().getTime()));
				    }
					scheduler.scheduleJob(jobDetail, simpleTrigger);
					if(!scheduler.isStarted()){
						scheduler.start();
					}
				}
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				throw new SchedulingException("添加系统启动计划任务【"+scheduling.getJobName()+"】异常",e);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				throw new SchedulingException("添加系统启动计划任务【"+scheduling.getJobName()+"】异常,没有找到计划任务执行类",e);
			}
		}
	}
	public void addCronTrigger(Scheduling scheduling) throws SchedulingException{
		if(scheduling!=null){
			try {
				Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
				CronTrigger trigger = (CronTrigger)scheduler.getTrigger(scheduling.getTriggerName(), scheduling.getTriggerGroup());
				if (null == trigger) {   
				    // Trigger不存在，那么创建一个。一个Job可以对应多个Trigger，但一个Trigger只能对应一个Job。
				    JobDetail jobDetail = new JobDetail(scheduling.getRealJobName(),scheduling.getJobGroup(),Class.forName(scheduling.getJobClass()));   
				    JobDataMap dataMap = jobDetail.getJobDataMap();
					dataMap.put("owner", scheduling.getOwner());
					dataMap.put("sapClient", scheduling.getSapclient());
				    //jobDetail.getJobDataMap().put("targetObjectId", schedulingJob.getJobId());   
				    trigger = new CronTrigger(scheduling.getTriggerName(), scheduling.getTriggerGroup(), scheduling.getCronExpression()); 
				    //设置开始时间和结束时间
				    if(scheduling.getJobBeginDate()!=null){
				    	trigger.setStartTime(scheduling.getJobBeginDate());
				    }
				    if(scheduling.getJobEndDate()!=null){
				    	trigger.setEndTime(scheduling.getJobEndDate());
				    }
				    scheduler.scheduleJob(jobDetail, trigger);  
				    if(!scheduler.isStarted()){
						scheduler.start();
					}
				}
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				throw new SchedulingException("添加计划任务【"+scheduling.getJobName()+"】异常",e);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				throw new SchedulingException("添加计划任务【"+scheduling.getJobName()+"】异常,没有找到计划任务执行类",e);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				throw new SchedulingException("添加计划任务【"+scheduling.getJobName()+"】异常,构建CronTrigger异常",e);
			}
		}
	}
	/**
	 * 新增trigger
	 * @param scheduling
	 * @throws SchedulingException
	 */
	public void addTrigger(Scheduling scheduling) throws SchedulingException{
		if(scheduling!=null){
			//如果不是随平台启动，就动态加入。如果是随平台启动，则会在启动时自动执行。随平台启动不用设置时间，什么时候启动什么时候执行
			if(!SchedulingConstans.JOB_EXECUTEFREQUENCY_STARTUP.equals(scheduling.getExecuteFrequency())){
				if(SchedulingConstans.JOB_EXECUTEFREQUENCY_SHUTDOWN.equals(scheduling.getExecuteFrequency())){
					this.remove(scheduling);
				}else if(SchedulingConstans.JOB_EXECUTEFREQUENCY_REPEAT.equals(scheduling.getExecuteFrequency())){
					this.addSimpleTrigger(scheduling);
				}else{
					this.addCronTrigger(scheduling);
				}
				
			}
		}
	}
	public void remove(Scheduling scheduling) throws SchedulingException {
		if(scheduling!=null){
			try {
				Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
				scheduler.deleteJob(scheduling.getRealJobName(), scheduling.getJobGroup());//删除任务
				/*
				CronTrigger trigger = (CronTrigger)scheduler.getTrigger(scheduling.getTriggerName(), scheduling.getTriggerGroup()); 
				SimpleTrigger simpleTrigger = (SimpleTrigger)scheduler.getTrigger(scheduling.getTriggerName(), scheduling.getTriggerGroup());
				if (null != trigger||simpleTrigger!=null) {  
					scheduler.pauseTrigger(scheduling.getTriggerName(), scheduling.getTriggerGroup());//停止触发器
					scheduler.unscheduleJob(scheduling.getTriggerName(), scheduling.getTriggerGroup());//移除触发器
					scheduler.deleteJob(scheduling.getRealJobName(), scheduling.getJobGroup());//删除任务
				}*/
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				throw new SchedulingException("删除计划任务异常",e);
			}
		}
	}
	public void update(Scheduling scheduling) throws SchedulingException{
		if(scheduling!=null){
				/*
				Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
				CronTrigger trigger = (CronTrigger)scheduler.getTrigger(scheduling.getTriggerName(), scheduling.getTriggerGroup());   
				if (null != trigger) {  
					if(scheduling.getJobBeginDate()!=null){
					    trigger.setStartTime(scheduling.getJobBeginDate());
				    }else{
				    	trigger.setStartTime(null);
				    }
				    if(scheduling.getJobEndDate()!=null){
				    	trigger.setEndTime(scheduling.getJobEndDate());
				    }else{
				    	trigger.setEndTime(null);
				    }
					 // Trigger已存在,并且触发规则不一致，那么更新相应的定时设置
					if(!trigger.getCronExpression().equals(scheduling.getCronExpression())){
						 trigger.setCronExpression(scheduling.getCronExpression());   
				         scheduler.rescheduleJob(trigger.getName(), trigger.getGroup(), trigger);
					}
				}else{
					this.addCronTrigger(scheduling);
				}*/
				this.remove(scheduling);
				this.addTrigger(scheduling);
				
		}
	}
	/*
	public void pauseTrigger(Scheduling scheduling)throws ParseException, SchedulerException{
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        CronTrigger trigger = (CronTrigger)scheduler.getTrigger(scheduling.getTriggerName(), scheduling.getTriggerGroup()); 
        if (null != trigger) { 
        	scheduler.pauseTrigger(trigger.getKey());
        }
	}
	*/
	public void printJob(){
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			String[] triggerGroupname = scheduler.getTriggerGroupNames();
			System.out.println("******************开始输出triggername***************************************");
			for(int i=0;i<triggerGroupname.length;i++){
				String[] triggername = scheduler.getTriggerNames(triggerGroupname[i]);
				for(int j=0;j<triggername.length;j++){
					System.out.println(triggername[j]);
				}
			}
			System.out.println("******************结束输出triggername***************************************");
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws ParseException{
		CronTrigger ct = new CronTrigger("foo", "goo", "0 0/10 * * * ?"); // fire every ten minutes, all day every day

	    // construct a date of March 17, 2012 10:03:00
	    Calendar futureDate = Calendar.getInstance(); 
	    futureDate.set(Calendar.YEAR, 2012);
	    futureDate.set(Calendar.MONTH, GregorianCalendar.DECEMBER);
	    futureDate.set(Calendar.DAY_OF_MONTH, 17);
	    futureDate.set(Calendar.HOUR_OF_DAY, 10);
	    futureDate.set(Calendar.MINUTE, 3);
	    futureDate.set(Calendar.SECOND, 0);
	    futureDate.set(Calendar.MILLISECOND, 0);

	    Calendar futureDate2 = Calendar.getInstance(); 
	    futureDate2.set(Calendar.YEAR, 2012);
	    futureDate2.set(Calendar.MONTH, GregorianCalendar.DECEMBER);
	    futureDate2.set(Calendar.DAY_OF_MONTH, 17);
	    futureDate2.set(Calendar.HOUR_OF_DAY, 10);
	    futureDate2.set(Calendar.MINUTE, 40);
	    futureDate2.set(Calendar.SECOND, 0);
	    futureDate2.set(Calendar.MILLISECOND, 0);
	    // use the date as the startTime
	    ct.setStartTime(futureDate.getTime());
	    ct.setEndTime(futureDate2.getTime());

	    // check what time the trigger will first fire
	    List fireTimes = TriggerUtils.computeFireTimes(ct, null, 5);
	    Iterator it = fireTimes.iterator();
	    while(it.hasNext()){
	    	 Date firstFireTime = (Date) it.next();
	    	 System.out.println("First fire time: " +fireTimes.size()+","+ firstFireTime.toLocaleString());
	    }
	}
}
