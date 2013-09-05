package com.sapGarden.application.commons.runtime.scheduling.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="sys_commonscheduling")
public class CommonScheduling implements Serializable{   
	
	
	private static final long serialVersionUID = -2416584208885724096L;

	public static final String JOBTYPE_TIME = "TIME";
	public static final String JOBTYPE_REALTIME = "REALTIME";

	@Id
	@TableGenerator(name = "commonscheduling_gen", //该表主键生成策略的名称,被@GeneratedValue.generator引用。
	                table = "sys_tb_generator",       //表生成策略所持久化的表名。
	                pkColumnName = "gen_name",    //在持久化的表中，该主键生成策略所对应键值的名称。
	                valueColumnName = "gen_value", //在持久化的表中， 该主键当前所生成的值，它的值将会随着每次创建而加。
	                pkColumnValue = "sys_commonscheduling_pk",//在持久化的表中，该生成策略所对应的主键
	                initialValue = 100,             //默认主键值为50
	                allocationSize = 1)           //每次主键值增加的大小
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "commonscheduling_gen")
    private long id;   
	

	@Column(name="jobname",length = 100)
    private String jobname;   
	
	@Column(name="jobgroup",length = 100)
    private String jobgroup;   
	
	@Column(name="jobclass",length = 200)
    private String jobclass;
	
    private int jobstatus;  
    
    @Column(name="jobmemo",length = 100)
    private String jobmemo;
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="jobbegindata")
    private Date jobbegindate;
    
    @Column(name="jobenddate")
    private Date jobenddate;
    
    @Column(name="jobtype",length = 100)
    private String jobtype;
    
    @Column(name="jobowner",length = 100)
    private String jobowner;
    
    @Column(name="sapclient")
    private long sapclient;
    
    //************循环执行类型的定时任务****************//
    @Column(name="intervalhour")
    private int intervalhour;
    
    @Column(name="intervalminute")
    private int intervalminute=1;
  //**************循环执行类型的定时任务**************//

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public String getJobgroup() {
		return jobgroup;
	}

	public void setJobgroup(String jobgroup) {
		this.jobgroup = jobgroup;
	}

	public String getJobclass() {
		return jobclass;
	}

	public void setJobclass(String jobclass) {
		this.jobclass = jobclass;
	}

	public int getJobstatus() {
		return jobstatus;
	}

	public void setJobstatus(int jobstatus) {
		this.jobstatus = jobstatus;
	}

	public String getJobmemo() {
		return jobmemo;
	}

	public void setJobmemo(String jobmemo) {
		this.jobmemo = jobmemo;
	}

	@Temporal(TemporalType.TIMESTAMP)  
	public Date getJobbegindate() {
		return jobbegindate;
	}

	public void setJobbegindate(Date jobbegindate) {
		this.jobbegindate = jobbegindate;
	}

	@Temporal(TemporalType.TIMESTAMP)  
	public Date getJobenddate() {
		return jobenddate;
	}

	public void setJobenddate(Date jobenddate) {
		this.jobenddate = jobenddate;
	}

	public String getJobtype() {
		return jobtype;
	}

	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}

	public String getJobowner() {
		return jobowner;
	}

	public void setJobowner(String jobowner) {
		this.jobowner = jobowner;
	}

	public long getSapclient() {
		return sapclient;
	}

	public void setSapclient(long sapclient) {
		this.sapclient = sapclient;
	}

	public int getIntervalhour() {
		return intervalhour;
	}

	public void setIntervalhour(int intervalhour) {
		this.intervalhour = intervalhour;
	}

	public int getIntervalminute() {
		return intervalminute;
	}

	public void setIntervalminute(int intervalminute) {
		this.intervalminute = intervalminute;
	}
   
	public String findCronExpression() {
		return null;
	}
}
