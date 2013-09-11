package com.sapGarden.application.commons.progress.model;

public class NewProgress {

	private long threadid;
	/**
	 * 总共几条记录
	 */
	private long totalNum;
	/**
	 * 总共几个业务实体、比如300个客户有1000条记录
	 */
	private long totalEntity;
	/**
	 * 进度条记度，比如5代表百分之5
	 */
	private int percentage;
	/**
	 * 运行状态，false代表停止运行、true代表运行中
	 */
	private boolean runstatus;
	private long starttime;
	private long endtime;
	private boolean over;
	
	public long getThreadid() {
		return threadid;
	}
	public void setThreadid(long threadid) {
		this.threadid = threadid;
	}
	public long getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public long getTotalEntity() {
		return totalEntity;
	}
	public void setTotalEntity(long totalEntity) {
		this.totalEntity = totalEntity;
	}
	public boolean isRunstatus() {
		return runstatus;
	}
	public void setRunstatus(boolean runstatus) {
		this.runstatus = runstatus;
	}
	public long getStarttime() {
		return starttime;
	}
	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}
	public long getEndtime() {
		return endtime;
	}
	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}
	public boolean isOver() {
		return over;
	}
	public void setOver(boolean over) {
		this.over = over;
	}
	
}
