package com.sapGarden.application.commons.runtime.scheduling.util;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.runtime.scheduling.exception.SchedulingException;
import com.sapGarden.application.commons.runtime.scheduling.model.CommonScheduling;


@Component
public class SchedulingUtils {

	public final static String JOBNAME_PREFIX = "NAMEOF";
	public final static String GROUPNAME_PREFIX = "GROUPOF";

	/**
	 * 二、Quartz对任务调度的领域问题进行了高度的抽象，提出了调度器、任务和触发器这3个核心的概念，并在org.quartz通过接口和类对重要的这些核心概念进行描述：
1、Job ：是一个接口，只有一个方法void execute(JobExecutionContext context)，开发者实现该接口定义运行任务，JobExecutionContext类提供了调度上下文的各种信息。Job运行时的信息保存在 JobDataMap实例中；
2、JobDetail ：Quartz在每次执行Job时，都重新创建一个Job实例，所以它不直接接受一个Job的实例，相反它接收一个Job实现类，以便运行时通过newInstance()的反射机制实例化Job。因此需要通过一个类来描述Job的实现类及其它相关的静态信息 ，如Job名字、描述、关联监听器等信息，JobDetail承担了这一角色。通过该类的构造函数可以更具体地了解它的功用：JobDetail(java.lang.String name, java.lang.String group, java.lang.Class jobClass)，该构造函数要求指定Job的实现类，以及任务在Scheduler中的组名和Job名称；
3、Trigger ：是一个类，描述触发Job执行的时间触发规则。主要有SimpleTrigger和 CronTrigger这两个子类。当仅需触发一次或者以固定时间间隔周期执行，SimpleTrigger是最适合的选择；而CronTrigger则 可以通过Cron表达式定义出各种复杂时间规则的调度方案：如每早晨9:00执行，周一、周三、周五下午5:00执行等；
4、Calendar ：org.quartz.Calendar和java.util.Calendar不同，它是一些日历 特定时间点的集合（可以简单地将org.quartz.Calendar看作java.util.Calendar的集合—— java.util.Calendar代表一个日历时间点，无特殊说明后面的Calendar即指org.quartz.Calendar）。一个 Trigger可以和多个Calendar关联，以便排除或包含某些时间点。假设，我们安排每周星期一早上10:00执行任务，但是如果碰到法定的节日，任务则不执行，这时就需要在Trigger触 发机制的基础上使用Calendar进行定点排除。针对不同时间段类型，Quartz在org.quartz.impl.calendar包下提供了若干 个Calendar的实现类，如AnnualCalendar、MonthlyCalendar、WeeklyCalendar分别针对每年、每月和每周 进行定义；
5、Scheduler ：代表一个Quartz的独立运行容器，Trigger和JobDetail可以注册到 Scheduler中，两者在Scheduler中拥有各自的组及名称，组及名称是Scheduler查找定位容器中某一对象的依据，Trigger的组 及名称必须唯一，JobDetail的组和名称也必须唯一（但可以和Trigger的组和名称相同，因为它们是不同类型的）。Scheduler定义了多 个接口方法，允许外部通过组及名称访问和控制容器中Trigger和JobDetail。Scheduler可以将Trigger绑定到某一JobDetail中，这样当Trigger触发时，对应的Job就被执行。一个Job可以对应多个Trigger，但一个Trigger只能对应一个Job 。 可 以通过SchedulerFactory创建一个Scheduler实例。Scheduler拥有一个SchedulerContext，它类似于 ServletContext，保存着Scheduler上下文信息，Job和Trigger都可以访问SchedulerContext内的信息。 SchedulerContext内部通过一个Map，以键值对的方式维护这些上下文数据，SchedulerContext为保存和获取数据提供了多个 put()和getXxx()的方法。可以通过Scheduler# getContext()获取对应SchedulerContext实例
	 */


	/**
	 * 添加平台启动执行任务
	 * @param scheduling
	 * @throws SchedulingException
	 */
	/*
	public void addStartUpTrigger(CommonScheduling job) throws SchedulingException{
		if(job!=null){
			try {
				Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
				SimpleTrigger  trigger = (SimpleTrigger)scheduler.getTrigger(job.getJobname(),job.getJobgroup());
				if(trigger==null){
					JobDetail jobDetail = new JobDetail(job.getJobname(),job.getJobgroup(),Class.forName(job.getJobclass()));   
					JobDataMap dataMap = jobDetail.getJobDataMap();
					dataMap.put("owner", job.getJobowner());
					dataMap.put("sapclient", job.getSapclient());
					//平台启动时执行的计划任务，延迟5秒
					Date triggerTime = new Date((new Date()).getTime()+5000l);
					SimpleTrigger simpleTrigger = new SimpleTrigger(job.getJobname(),job.getJobgroup(),triggerTime);
					scheduler.scheduleJob(jobDetail, simpleTrigger);
					if(!scheduler.isStarted()){
						scheduler.start();
					}
				}
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				throw new SchedulingException("添加系统启动计划任务【"+job.getJobname()+"】异常",e);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				throw new SchedulingException("添加系统启动计划任务【"+job.getJobname()+"】异常,没有找到计划任务执行类",e);
			}
		}
	}*/
	/**
	 * 添加simpleTrigger
	 * @param scheduling
	 * @throws SchedulingException
	 */
	private void addSimpleTrigger(CommonScheduling job,SapDataCollection sapDataCollection) throws SchedulingException{
		if(job!=null){
			try {
				Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
				SimpleTrigger  trigger = (SimpleTrigger)scheduler.getTrigger(job.getJobname(),job.getJobgroup());
				if(trigger==null){
					JobDetail jobDetail = new JobDetail(job.getJobname(),job.getJobgroup(),Class.forName(job.getJobclass()));   
					JobDataMap dataMap = jobDetail.getJobDataMap();
					dataMap.put("owner", job.getJobowner());
					dataMap.put("sapclient", sapDataCollection);
					SimpleTrigger simpleTrigger = new SimpleTrigger(job.getJobname(),job.getJobgroup(),SimpleTrigger.REPEAT_INDEFINITELY,(job.getIntervalminute()*60*1000));
					//设置开始时间和结束时间
				    if(job.getJobbegindate()!=null){
				    	simpleTrigger.setStartTime(new Date(job.getJobbegindate().getTime()));
				    }
				    if(job.getJobenddate()!=null){
				    	simpleTrigger.setEndTime(new Date(job.getJobenddate().getTime()));
				    }
					scheduler.scheduleJob(jobDetail, simpleTrigger);
					if(!scheduler.isStarted()){
						scheduler.start();
					}
					System.out.println("*********************添加计划任务【"+job.getJobname()+"】成功！！！！！*********************");
				}
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				throw new SchedulingException("*********************添加计划任务【"+job.getJobname()+"】异常*********************",e);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				throw new SchedulingException("*********************添加计划任务【"+job.getJobname()+"】异常,没有找到计划任务执行类*********************",e);
			}
		}
	}
	/*
	public void addCronTrigger(CommonScheduling job) throws SchedulingException{
		if(job!=null){
			try {
				Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
				CronTrigger trigger = (CronTrigger)scheduler.getTrigger(job.getJobname(),job.getJobgroup());
				if (null == trigger) {   
				    // Trigger不存在，那么创建一个。一个Job可以对应多个Trigger，但一个Trigger只能对应一个Job。
				    JobDetail jobDetail = new JobDetail(job.getJobname(),job.getJobgroup(),Class.forName(job.getJobclass()));   
				    JobDataMap dataMap = jobDetail.getJobDataMap();
				    dataMap.put("owner", job.getJobowner());
					dataMap.put("sapclient", job.getSapclient());
				    //jobDetail.getJobDataMap().put("targetObjectId", schedulingJob.getJobId());   
				    trigger = new CronTrigger(job.getJobname(),job.getJobgroup(), job.findCronExpression()); 
				    //设置开始时间和结束时间
				    if(job.getJobbegindate()!=null){
				    	trigger.setStartTime(job.getJobbegindate());
				    }
				    if(job.getJobenddate()!=null){
				    	trigger.setEndTime(job.getJobenddate());
				    }
				    scheduler.scheduleJob(jobDetail, trigger);  
				    if(!scheduler.isStarted()){
						scheduler.start();
					}
				}
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				throw new SchedulingException("添加计划任务【"+job.getJobname()+"】异常",e);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				throw new SchedulingException("添加计划任务【"+job.getJobname()+"】异常,没有找到计划任务执行类",e);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				throw new SchedulingException("添加计划任务【"+job.getJobname()+"】异常,构建CronTrigger异常",e);
			}
		}
	}
	*/
	/**
	 * 新增trigger
	 * @param scheduling
	 * @throws SchedulingException
	 */
	public void addTrigger(CommonScheduling job,SapDataCollection sapDataCollection) throws SchedulingException{
		if(job!=null){
			//如果不是随平台启动，就动态加入。如果是随平台启动，则会在启动时自动执行。随平台启动不用设置时间，什么时候启动什么时候执行
			if(job.getJobtype().equals(CommonScheduling.JOBTYPE_TIME)&&job.getJobstatus()==1){
				//this.remove(job);
				this.addSimpleTrigger(job,sapDataCollection);
			}
		}
	}
	public void remove(CommonScheduling job) throws SchedulingException {
		if(job!=null){
			try {
				Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
				scheduler.pauseJob(job.getJobname(),job.getJobgroup());// 停止触发器 
				scheduler.unscheduleJob(job.getJobname(),job.getJobgroup());//移除触发器
				scheduler.deleteJob(job.getJobname(),job.getJobgroup());//删除任务
				System.out.println("*********************删除计划任务【"+job.getJobname()+"】成功！！！！！*********************");
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				throw new SchedulingException("*********************删除计划任务异常*********************",e);
			}
		}
	}
	public void update(CommonScheduling job,SapDataCollection sapDataCollection) throws SchedulingException{
		if(job!=null){
				this.remove(job);
				this.addTrigger(job,sapDataCollection);
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
//	public void printJob(){
//		try {
//			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//			String[] triggerGroupname = scheduler.getTriggerGroupNames();
//			System.out.println("******************开始输出triggername***************************************");
//			for(int i=0;i<triggerGroupname.length;i++){
//				String[] triggername = scheduler.getTriggerNames(triggerGroupname[i]);
//				for(int j=0;j<triggername.length;j++){
//					System.out.println(triggername[j]);
//				}
//			}
//			System.out.println("******************结束输出triggername***************************************");
//		} catch (SchedulerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public static void main(String[] args) throws ParseException{
//		CronTrigger ct = new CronTrigger("foo", "goo", "0 0/10 * * * ?"); // fire every ten minutes, all day every day
//
//	    // construct a date of March 17, 2012 10:03:00
//	    Calendar futureDate = Calendar.getInstance(); 
//	    futureDate.set(Calendar.YEAR, 2012);
//	    futureDate.set(Calendar.MONTH, GregorianCalendar.DECEMBER);
//	    futureDate.set(Calendar.DAY_OF_MONTH, 17);
//	    futureDate.set(Calendar.HOUR_OF_DAY, 10);
//	    futureDate.set(Calendar.MINUTE, 3);
//	    futureDate.set(Calendar.SECOND, 0);
//	    futureDate.set(Calendar.MILLISECOND, 0);
//
//	    Calendar futureDate2 = Calendar.getInstance(); 
//	    futureDate2.set(Calendar.YEAR, 2012);
//	    futureDate2.set(Calendar.MONTH, GregorianCalendar.DECEMBER);
//	    futureDate2.set(Calendar.DAY_OF_MONTH, 17);
//	    futureDate2.set(Calendar.HOUR_OF_DAY, 10);
//	    futureDate2.set(Calendar.MINUTE, 40);
//	    futureDate2.set(Calendar.SECOND, 0);
//	    futureDate2.set(Calendar.MILLISECOND, 0);
//	    // use the date as the startTime
//	    ct.setStartTime(futureDate.getTime());
//	    ct.setEndTime(futureDate2.getTime());
//
//	    // check what time the trigger will first fire
//	    List fireTimes = TriggerUtils.computeFireTimes(ct, null, 5);
//	    Iterator it = fireTimes.iterator();
//	    while(it.hasNext()){
//	    	 Date firstFireTime = (Date) it.next();
//	    	 System.out.println("First fire time: " +fireTimes.size()+","+ firstFireTime.toLocaleString());
//	    }
//	}
}
