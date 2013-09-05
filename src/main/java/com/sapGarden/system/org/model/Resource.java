package com.sapGarden.system.org.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name="sys_org_resource")
public class Resource implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9067859396403193071L;
	@Id
	@TableGenerator(name = "sys_org_resource_gen", //该表主键生成策略的名称,被@GeneratedValue.generator引用。
	                table = "sys_tb_generator",       //表生成策略所持久化的表名。
	                pkColumnName = "gen_name",    //在持久化的表中，该主键生成策略所对应键值的名称。
	                valueColumnName = "gen_value", //在持久化的表中， 该主键当前所生成的值，它的值将会随着每次创建而加。
	                pkColumnValue = "sys_org_resource_pk",//在持久化的表中，该生成策略所对应的主键
	                initialValue = 100,             //默认主键值为50
	                allocationSize = 1)           //每次主键值增加的大小
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sys_org_resource_gen")
	private long id;
	private String url;
	private String resourceName;
	private long parentId;
	private long resourceIndex;
	private int issystem;
	private int isshow;
	private String alias;
	//@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	//@ManyToMany(mappedBy="resources")
	//在多对多情况下，主动方和被动方的意义不大，而且因为只有删除主动方才会删除关系，所以多对多的情况也两边都设成主动方，这样删除资源时，也会把资源与角色的对应关系删除
	@ManyToMany(fetch = FetchType.LAZY,mappedBy="resources",targetEntity = com.sapGarden.system.org.model.Role.class)
	//@JoinTable(name="sys_org_role_resource",inverseJoinColumns=@JoinColumn(name="roleId",referencedColumnName="ID"),joinColumns=@JoinColumn(name="resourceId",referencedColumnName="ID"))
	private Set<Role> roles= new HashSet<Role>();
	
	public Resource(){
		super();
	}
	public Resource(Menu menu){
		super();
		this.url=menu.getUrl();
		this.resourceName=menu.getResourceName();
		this.resourceIndex=Long.parseLong(menu.getResourceIndex());
		this.issystem=menu.getIssystem();
		this.isshow=menu.getIsshow();
		this.alias=menu.getAlias();
		if(menu.getParent()==null||menu.getParent().equals("null")){
			this.parentId=0;
		}else{
			this.parentId=Long.parseLong(menu.getParent());
		}
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	
	public long getResourceIndex() {
		return resourceIndex;
	}
	public void setResourceIndex(long resourceIndex) {
		this.resourceIndex = resourceIndex;
	}
	@Override
	public int hashCode() {
		int result = 0;
		result = 37 * result + (int) (id^(id >>> 32));
		result = 31 * result+(url != null ? url.hashCode() : 0);
		result = 31 * result+(resourceName != null ? resourceName.hashCode() : 0);
		result = 37 * result + (int) (parentId^(parentId >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resource other = (Resource) obj;
		return id==other.getId();
	}
	public int getIssystem() {
		return issystem;
	}
	public void setIssystem(int issystem) {
		this.issystem = issystem;
	}
	public int getIsshow() {
		return isshow;
	}
	public void setIsshow(int isshow) {
		this.isshow = isshow;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
}
