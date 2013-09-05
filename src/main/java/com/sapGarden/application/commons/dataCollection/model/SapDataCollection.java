package com.sapGarden.application.commons.dataCollection.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.sapGarden.system.org.model.User;
@Entity
@Table(name="sys_sapdatacollection")
public class SapDataCollection implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7526789028780535496L;
	@Id
	@TableGenerator(name = "sys_sapdatacollection_gen", //该表主键生成策略的名称,被@GeneratedValue.generator引用。
	                table = "sys_tb_generator",       //表生成策略所持久化的表名。
	                pkColumnName = "gen_name",    //在持久化的表中，该主键生成策略所对应键值的名称。
	                valueColumnName = "gen_value", //在持久化的表中， 该主键当前所生成的值，它的值将会随着每次创建而加。
	                pkColumnValue = "sys_sapdatacollection_pk",//在持久化的表中，该生成策略所对应的主键
	                initialValue = 100,             //默认主键值为50
	                allocationSize = 1)           //每次主键值增加的大小
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sys_sapdatacollection_gen")
	private long id;
	private String alias;
	private String gardendbip;
	private String gardendbinstance;
	private String gardendbschema;
	private String gardendbusername;
	private String gardendbpassword;
	private String sapserverip;
	private String sapserverinstance;
	private String sapserversystemid;
	private String sapserverclient;
	private String sapserverusername;
	private String sapserverpassword;
	private String gardenusername;
	private String collectiondescription;
	private long roleid;
	@ManyToMany(fetch = FetchType.LAZY,mappedBy="sapDataCollections",targetEntity = com.sapGarden.system.org.model.User.class)
	//@JoinTable(name="SYS_USER_SAPDATACOLLECTION",inverseJoinColumns=@JoinColumn(name="username",referencedColumnName="username"),joinColumns=@JoinColumn(name="sapDataCollectionId",referencedColumnName="id"))
	private Set<User> users= new HashSet<User>();
	
	public SapDataCollection(){
		
	}
	public SapDataCollection(NewSapDataCollection newSapDataCollection){
		String newId = newSapDataCollection.getId();
		if(newId!=null&&!"".equals(newId)&&!"_empty".equals(newId)){
			this.id=Long.parseLong(newId);
			this.roleid=Long.parseLong(newSapDataCollection.getRoleid());
		}
		this.alias= newSapDataCollection.getSapserverclient()+"("+newSapDataCollection.getSapserverip()+"#"+newSapDataCollection.getSapserverinstance()+")";
		this.gardendbip=newSapDataCollection.getGardendbip();
		this.gardendbinstance=newSapDataCollection.getGardendbinstance();
		this.gardendbschema=newSapDataCollection.getGardendbschema();
		this.gardendbusername=newSapDataCollection.getGardendbusername();
		this.gardendbpassword=newSapDataCollection.getGardendbpassword();
		this.sapserverip=newSapDataCollection.getSapserverip();
		this.sapserverinstance=newSapDataCollection.getSapserverinstance();
		this.sapserversystemid=newSapDataCollection.getSapserversystemid();
		this.sapserverclient=newSapDataCollection.getSapserverclient();
		this.sapserverusername=newSapDataCollection.getSapserverusername();
		this.sapserverpassword=newSapDataCollection.getSapserverpassword();
		this.gardenusername=newSapDataCollection.getGardenusername();
		this.collectiondescription=newSapDataCollection.getCollectiondescription();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getGardendbip() {
		return gardendbip;
	}
	public void setGardendbip(String gardendbip) {
		this.gardendbip = gardendbip;
	}
	public String getGardendbinstance() {
		return gardendbinstance;
	}
	public void setGardendbinstance(String gardendbinstance) {
		this.gardendbinstance = gardendbinstance;
	}
	public String getGardendbschema() {
		return gardendbschema;
	}
	public void setGardendbschema(String gardendbschema) {
		this.gardendbschema = gardendbschema;
	}
	public String getGardendbusername() {
		return gardendbusername;
	}
	public void setGardendbusername(String gardendbusername) {
		this.gardendbusername = gardendbusername;
	}
	public String getGardendbpassword() {
		return gardendbpassword;
	}
	public void setGardendbpassword(String gardendbpassword) {
		this.gardendbpassword = gardendbpassword;
	}
	public String getSapserverip() {
		return sapserverip;
	}
	public void setSapserverip(String sapserverip) {
		this.sapserverip = sapserverip;
	}
	public String getSapserverinstance() {
		return sapserverinstance;
	}
	public void setSapserverinstance(String sapserverinstance) {
		this.sapserverinstance = sapserverinstance;
	}
	public String getSapserversystemid() {
		return sapserversystemid;
	}
	public void setSapserversystemid(String sapserversystemid) {
		this.sapserversystemid = sapserversystemid;
	}
	public String getSapserverclient() {
		return sapserverclient;
	}
	public void setSapserverclient(String sapserverclient) {
		this.sapserverclient = sapserverclient;
	}
	public String getSapserverusername() {
		return sapserverusername;
	}
	public void setSapserverusername(String sapserverusername) {
		this.sapserverusername = sapserverusername;
	}
	public String getSapserverpassword() {
		return sapserverpassword;
	}
	public void setSapserverpassword(String sapserverpassword) {
		this.sapserverpassword = sapserverpassword;
	}
	public String getGardenusername() {
		return gardenusername;
	}
	public void setGardenusername(String gardenusername) {
		this.gardenusername = gardenusername;
	}
	public String getCollectiondescription() {
		return collectiondescription;
	}
	public void setCollectiondescription(String collectiondescription) {
		this.collectiondescription = collectiondescription;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public long getRoleid() {
		return roleid;
	}
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	
}
