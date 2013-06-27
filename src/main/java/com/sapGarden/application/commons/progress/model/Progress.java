package com.sapGarden.application.commons.progress.model;

public class Progress {

	private String type;
	private int totalNum;
	private int percent;
	private long beginTime;
	private long endTime;
	private int totalCustomer;
	private boolean cancel;
	
	public  String getType() {
		return type;
	}
	public  void setType(String type) {
		this.type = type;
	}
	public  int getTotalNum() {
		return totalNum;
	}
	public  void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public  int getPercent() {
		return percent;
	}
	public  void setPercent(int percent) {
		this.percent = percent;
	}
	public  long getBeginTime() {
		return beginTime;
	}
	public  void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}
	public  long getEndTime() {
		return endTime;
	}
	public  void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public boolean isCancel() {
		return cancel;
	}
	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}
	public int getTotalCustomer() {
		return totalCustomer;
	}
	public void setTotalCustomer(int totalCustomer) {
		this.totalCustomer = totalCustomer;
	}
	public void init(){
		this.setPercent(0);
		this.setTotalCustomer(0);
		this.setTotalNum(0);
		this.setEndTime(0);
		this.setBeginTime(0);
		this.setCancel(false);
		this.setType(null);
	}
	
}
