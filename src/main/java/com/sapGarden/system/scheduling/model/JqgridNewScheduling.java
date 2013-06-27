package com.sapGarden.system.scheduling.model;


public class JqgridNewScheduling {

	private String id;                      
    private String jobName;                        
    private String jobGroup;   
    private String jobClass;
    private String oper;                     
    private String jobMemo;
    private String jobBeginDate;
    private String jobEndDate;
    private String executeFrequency;
    private int executeTimeHour;
    private int executeTimeMinute;
    private int repeatInterval;
    private String owner;
    private long sapclient;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getJobClass() {
		return jobClass;
	}
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	public String getJobMemo() {
		return jobMemo;
	}
	public void setJobMemo(String jobMemo) {
		this.jobMemo = jobMemo;
	}
	
	public String getJobBeginDate() {
		return jobBeginDate;
	}
	public void setJobBeginDate(String jobBeginDate) {
		this.jobBeginDate = jobBeginDate;
	}
	public String getJobEndDate() {
		return jobEndDate;
	}
	public void setJobEndDate(String jobEndDate) {
		this.jobEndDate = jobEndDate;
	}
	public String getExecuteFrequency() {
		return executeFrequency;
	}
	public void setExecuteFrequency(String executeFrequency) {
		this.executeFrequency = executeFrequency;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public int getExecuteTimeHour() {
		return executeTimeHour;
	}
	public void setExecuteTimeHour(int executeTimeHour) {
		this.executeTimeHour = executeTimeHour;
	}
	public int getExecuteTimeMinute() {
		return executeTimeMinute;
	}
	public void setExecuteTimeMinute(int executeTimeMinute) {
		this.executeTimeMinute = executeTimeMinute;
	}
	public int getRepeatInterval() {
		return repeatInterval;
	}
	public void setRepeatInterval(int repeatInterval) {
		this.repeatInterval = repeatInterval;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public long getSapclient() {
		return sapclient;
	}
	public void setSapclient(long sapclient) {
		this.sapclient = sapclient;
	}
}
