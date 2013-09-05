package com.sapGarden.system.org.model;

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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.springframework.security.core.GrantedAuthority;
@Entity
@Table(name="sys_org_role")
public class Role implements GrantedAuthority,Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3220356205621901483L;
	@Id
	@TableGenerator(name = "sys_org_role_gen", //该表主键生成策略的名称,被@GeneratedValue.generator引用。
	                table = "sys_tb_generator",       //表生成策略所持久化的表名。
	                pkColumnName = "gen_name",    //在持久化的表中，该主键生成策略所对应键值的名称。
	                valueColumnName = "gen_value", //在持久化的表中， 该主键当前所生成的值，它的值将会随着每次创建而加。
	                pkColumnValue = "sys_org_role_pk",//在持久化的表中，该生成策略所对应的主键
	                initialValue = 100,             //默认主键值为50
	                allocationSize = 1)           //每次主键值增加的大小
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sys_org_role_gen")
	private long id;
	private String roleName;
	private String alias;
	//只需要设置mappedBy="roles"表明Role实体是关系被维护端就可以了
    //级联保存、级联删除等之类的属性在多对多关系中是不需要设置,不能说删除了用户就删除角色，也不能说删除角色就删除用户
	//@ManyToMany(mappedBy="roles",cascade={CascadeType.PERSIST})
	//在多对多情况下，主动方和被动方的意义不大，而且因为只有删除主动方才会删除关系，所以多对多的情况也两边都设成主动方，这样删除角色时，也会把用户与角色的对应关系删除
	/**
	 *  1、只有OneToOne,OneToMany,ManyToMany上才有mappedBy属性，ManyToOne不存在该属性； 
		2、mappedBy标签一定是定义在the owned side(被拥有方的)，他指向the owning side(拥有方)；
		3、mappedBy的含义，应该理解为，拥有方能够自动维护 跟被拥有方的关系；当然，如果从被拥有方，通过手工强行来维护拥有方的关系也是可以做到的。
		4、mappedBy跟JoinColumn/JoinTable总是处于互斥的一方，可以理解为正是由于拥有方的关联被拥有方的字段存在，拥有方才拥有了被拥有方。mappedBy这方定义的JoinColumn/JoinTable总是失效的，不会建立对应的字段或者表 
	 
	 */
	@ManyToMany(fetch = FetchType.LAZY,mappedBy="roles",targetEntity = com.sapGarden.system.org.model.User.class)//,cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE},mappedBy="roles",targetEntity = com.sapGarden.system.org.model.User.class
	//@JoinTable(name="sys_org_user_role",inverseJoinColumns=@JoinColumn(name="username",referencedColumnName="username"),joinColumns=@JoinColumn(name="roleId",referencedColumnName="id"))
	private Set<User> users= new HashSet<User>();
	
	//@ManyToMany(fetch=FetchType.EAGER)
	@ManyToMany(fetch=FetchType.EAGER)
	//@LazyCollection(LazyCollectionOption.FALSE)
	//排序，方便生成菜单树
	@OrderBy("parentId,resourceIndex")
	@JoinTable(name="sys_org_role_resource",joinColumns=@JoinColumn(name="roleId",referencedColumnName="ID"),inverseJoinColumns=@JoinColumn(name="resourceId",referencedColumnName="ID"))
	private Set<Resource> resources= new HashSet<Resource>();
	
	public Role(){
		super();
	}
	public Role(NewRole newRole){
		super();
		String newId = newRole.getId();
		//如果newId不为空并且不为_empty，则说明是更新，要设置id，如果不设置id，则为更新
		if(newId!=null&&!"".equals(newId)&&!"_empty".equals(newId)){
			this.id=Long.parseLong(newId);
		}
		this.roleName=newRole.getRoleName();
		this.alias=newRole.getAlias();
		String resourcesIds = newRole.getResourcesIds();
		if(resourcesIds!=null&&!"".equals(resourcesIds)){
			String[] resourceIdArr = resourcesIds.split(",");
			for(int i=0;i<resourceIdArr.length;i++){
				Resource resource = new Resource();
				resource.setId(Integer.parseInt(resourceIdArr[i]));
				resources.add(resource);
			}
		}
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Set<Resource> getResources() {
		return resources;
	}
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.getId()+"";
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	@Override
	public int hashCode() {
		int result = 0;
		result = 37 * result + (int) (id^(id >>> 32));
		result = 31 * result+(roleName != null ? roleName.hashCode() : 0);
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
		Role other = (Role) obj;
		return id==other.getId();
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
}
