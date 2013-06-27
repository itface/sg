package com.sapGarden.system.scheduling.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.sapGarden.global.utils.commonUtils.DateUtils;
@Entity
@Table(name="sys_scheduling")
public class Scheduling implements Serializable{   
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_SYS_SCHEDULING")
	@SequenceGenerator(name="SEQ_SYS_SCHEDULING",sequenceName="SEQ_SYS_SCHEDULING",allocationSize=1)
    private long id;                      
    private String jobName;                        
    private String jobGroup;   
    private String jobClass;
    private int jobStatus=1;                       
    private String jobMemo;
    private Date jobBeginDate;
    private Date jobEndDate;
    private String executeFrequency;
    private String owner;
    private long sapclient;
    private int executeTimeHour;
    private int executeTimeMinute;
    private long repeatInterval;
    public Scheduling(){
    	super();
    }
    public Scheduling(JqgridNewScheduling newScheduling){
    	super();
    	String newId = newScheduling.getId();
		//如果newId不为空并且不为_empty，则说明是更新，要设置id，如果不设置id，则为更新
		if(newId!=null&&!"".equals(newId)&&!"_empty".equals(newId)){
			this.id=Long.parseLong(newId);
		}
    	this.jobName=newScheduling.getJobName();
    	this.jobGroup=newScheduling.getJobGroup();
    	this.jobClass=newScheduling.getJobClass();
    	this.jobMemo=newScheduling.getJobMemo();
    	DateUtils dateUtils = new DateUtils();
    	if(newScheduling.getJobBeginDate()!=null&&!"".equals(newScheduling.getJobBeginDate())){
    		this.jobBeginDate = dateUtils.stringToSqlDate(newScheduling.getJobBeginDate());
    	}
    	if(newScheduling.getJobEndDate()!=null&&!"".equals(newScheduling.getJobEndDate())){
    		this.jobEndDate=dateUtils.stringToSqlDate(newScheduling.getJobEndDate());
    	}
    	this.executeFrequency=newScheduling.getExecuteFrequency();
    	this.executeTimeHour=newScheduling.getExecuteTimeHour();
    	this.executeTimeMinute=newScheduling.getExecuteTimeMinute();
    	this.repeatInterval = newScheduling.getRepeatInterval();
    	this.owner=newScheduling.getOwner();
    	this.sapclient=newScheduling.getSapclient();
    }
    @Transient
    public String getTriggerName(){   
        return this.getRealJobName() + "TriggerName";   
    }
    @Transient
    public String getTriggerGroup(){   
        return this.jobGroup + "TriggerGroup";
    }
    /**
     * 用于新建job，保证每个job的名称不一致
     * @return
     */
    @Transient
    public String getRealJobName(){   
        return this.jobName + this.id;
    }
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public int getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(int jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getJobMemo() {
		return jobMemo;
	}

	public void setJobMemo(String jobMemo) {
		this.jobMemo = jobMemo;
	}

	@Temporal(TemporalType.DATE)
	public Date getJobBeginDate() {
		return jobBeginDate;
	}

	public void setJobBeginDate(Date jobBeginDate) {
		this.jobBeginDate = jobBeginDate;
	}

	@Temporal(TemporalType.DATE)
	public Date getJobEndDate() {
		return jobEndDate;
	}

	public void setJobEndDate(Date jobEndDate) {
		this.jobEndDate = jobEndDate;
	}

	public String getExecuteFrequency() {
		return executeFrequency;
	}

	public void setExecuteFrequency(String executeFrequency) {
		this.executeFrequency = executeFrequency;
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
	
	public long getRepeatInterval() {
		return repeatInterval;
	}
	public void setRepeatInterval(long repeatInterval) {
		this.repeatInterval = repeatInterval;
	}
	@Transient
	public String getCronExpression() {
		String yearRange = "*";
		String dayOfWeek = "?";
		String monthRange = "*";
		String dayOfMonth = "*";
		String hourRange = executeTimeHour+" ";
		String minuteRange = executeTimeMinute+" ";
		String secondRange = "0";
		return "0 "+executeTimeMinute+" "+executeTimeHour+" * * ? *";
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
