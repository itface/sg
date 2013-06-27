package com.sapGarden.system.scheduling.constans;

public class SchedulingConstans {

	/**
	 * 系统启动时执行的计划任务类别
	 */
	public static final String JOB_EXECUTEFREQUENCY_STARTUP="STARTUP";
	/**
	 * 每天定时执行的计划任务类别
	 */
	public static final String JOB_EXECUTEFREQUENCY_EVERYDAY="EVERYDAY";
	/**
	 * 循环执行，每几分钟执行一次
	 */
	public static final String JOB_EXECUTEFREQUENCY_REPEAT="REPEAT";
	/**
	 * SHUTDOWN状态下的定时任务是停止的计划任务
	 */
	public static final String JOB_EXECUTEFREQUENCY_SHUTDOWN="SHUTDOWN";
}
