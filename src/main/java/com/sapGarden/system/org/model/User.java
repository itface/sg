package com.sapGarden.system.org.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.global.utils.commonUtils.DateUtils;

@Entity
@Table(name="sys_org_user")
public class User implements UserDetails,Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 9040849899263456768L;
	/**
	 * 　
	主键产生策略通过GenerationType来指定。GenerationType是一个枚举，它定义了主键产生策略的类型。
　　1、AUTO　自动选择一个最适合底层数据库的主键生成策略。如MySQL会自动对应auto increment。这个是默认选项，即如果只写@GeneratedValue，等价于@GeneratedValue(strategy=GenerationType.AUTO)。
　　2、IDENTITY　表自增长字段，Oracle不支持这种方式。
　　3、SEQUENCE　通过序列产生主键，MySQL不支持这种方式。
　　4、TABLE　通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植。不同的JPA实现商生成的表名是不同的，如 OpenJPA生成openjpa_sequence_table表，Hibernate生成一个hibernate_sequences表，而TopLink则生成sequence表。这些表都具有一个序列名和对应值两个字段，如SEQ_NAME和SEQ_COUNT。
　　在我们的应用中，一般选用@GeneratedValue(strategy=GenerationType.AUTO)这种方式，自动选择主键生成策略，以适应不同的数据库移植。
	 */
	@Id
	@TableGenerator(name = "sys_org_user_gen", //该表主键生成策略的名称,被@GeneratedValue.generator引用。
	                table = "sys_tb_generator",       //表生成策略所持久化的表名。
	                pkColumnName = "gen_name",    //在持久化的表中，该主键生成策略所对应键值的名称。
	                valueColumnName = "gen_value", //在持久化的表中， 该主键当前所生成的值，它的值将会随着每次创建而加。
	                pkColumnValue = "sys_org_user_pk",//在持久化的表中，该生成策略所对应的主键
	                initialValue = 100,             //默认主键值为50
	                allocationSize = 1)           //每次主键值增加的大小
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sys_org_user_gen")
	private long id;
	private String username;
	private String name;
	private String password;
	private String userdescription;
	private int userstatus;
	private long usertype;
	@Temporal(TemporalType.TIMESTAMP)
	private Date usercreatetime;
	@Temporal(TemporalType.DATE)
	private Date lastlogintime;
	private long logincount;
	/**
	//关系维护端，负责多对多关系的绑定和解除
    //@JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(Player)
    //inverseJoinColumns指定外键的名字，要关联的关系被维护端(Game)
    //其实可以不使用@JoinTable注解，默认生成的关联表名称为主表表名+下划线+从表表名，
    //即表名为player_game
    //关联到主表的外键名：主表名+下划线+主表中的主键列名,即player_id
    //关联到从表的外键名：主表中用于关联的属性名+下划线+从表的主键列名,即game_id
    //主表就是关系维护端对应的表，从表就是关系被维护端对应的表
     */
	//@ManyToMany(fetch=FetchType.EAGER)
	@ManyToMany(fetch=FetchType.EAGER)
	//@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="sys_org_user_role",joinColumns=@JoinColumn(name="username",referencedColumnName="username"),inverseJoinColumns=@JoinColumn(name="roleId",referencedColumnName="id"))
	private Set<Role> roles = new HashSet<Role>();
//	@ManyToMany(fetch=FetchType.EAGER)
//	@JoinTable(name="sys_user_sapclient",joinColumns=@JoinColumn(name="username",referencedColumnName="username"),inverseJoinColumns=@JoinColumn(name="sapclientid",referencedColumnName="id"))
//	private Set<SapClientModel> sapClientModels = new HashSet<SapClientModel>();
//	@Transient
//	private SapClientModel currentSapClientModel;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="SYS_USER_SAPDATACOLLECTION",joinColumns=@JoinColumn(name="username",referencedColumnName="username"),inverseJoinColumns=@JoinColumn(name="SAPDATACOLLECTIONID",referencedColumnName="id"))
	private Set<SapDataCollection> sapDataCollections = new HashSet<SapDataCollection>();
	@Transient
	private SapDataCollection currentSapDataCollection;
	
	
	public User(NewUser newUser){
		super();
		String newId = newUser.getId();
		//如果newId不为空并且不为_empty，则说明是更新，要设置id，如果不设置id，则为更新
		if(newId!=null&&!"".equals(newId)&&!"_empty".equals(newId)){
			DateUtils dateUtils = new DateUtils();
			this.id=Long.parseLong(newId);
			this.password=newUser.getPassword();
			this.setLogincount(Long.parseLong(newUser.getLogincount()));
			this.setLastlogintime(dateUtils.stringToDate(newUser.getLastlogintime()));
			this.setUserstatus(Integer.parseInt(newUser.getUserstatus()));
		}else{
			//如果是新增，初始化密码置为123456
			PasswordEncoder encoder = new Md5PasswordEncoder();
		    String hashedPass = encoder.encodePassword(newUser.getPassword(), null);
			this.password=hashedPass;
			this.usercreatetime=new Date();
		}
		
		this.username=newUser.getUsername();
		this.name=newUser.getName();
		this.setUserdescription(newUser.getUserdescription());
		this.setUsercreatetime(new Date((new java.util.Date()).getTime()));
		String roleIds = newUser.getRoleIds();
		//添加sap数据集所对应的角色
		if(roleIds!=null&&!"".equals(roleIds)){
			String[] roleIdArr = roleIds.split(",");
			for(int i=0;i<roleIdArr.length;i++){
				Role role = new Role();
				role.setId(Integer.parseInt(roleIdArr[i]));
				roles.add(role);
			}
		}
		//添加用户类型所对应的角色
		this.usertype=Long.parseLong(newUser.getUsertype());
		Role role = new Role();
		role.setId(this.usertype);
		roles.add(role);
		String collections = newUser.getSapDataCollections();
		if(collections!=null&&!"".equals(collections)){
			String[] collectionsArr = collections.split(",");
			for(int i=0;i<collectionsArr.length;i++){
				SapDataCollection collection = new SapDataCollection();
				collection.setId(Integer.parseInt(collectionsArr[i]));
				sapDataCollections.add(collection);
			}
		}
	}
	public User(){
		super();
	}
	@Column(name = "id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return getRoles();
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	@Column(name = "username")
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public int hashCode() {
		int result = 0;
		result = (username != null ? username.hashCode() : 0);
		result = 31 * result+(password != null ? password.hashCode() : 0);
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
		User other = (User) obj;
		return username.equals(other.getUsername())&&password.equals(other.getPassword());
	}
	public Set<SapDataCollection> getSapDataCollections() {
		return sapDataCollections;
	}
	public void setSapDataCollections(Set<SapDataCollection> sapDataCollections) {
		this.sapDataCollections = sapDataCollections;
	}
	public SapDataCollection getCurrentSapDataCollection() {
		return currentSapDataCollection;
	}
	public void setCurrentSapDataCollection(SapDataCollection currentSapDataCollection) {
		this.currentSapDataCollection = currentSapDataCollection;
	}
	public String getUserdescription() {
		return userdescription;
	}
	public void setUserdescription(String userdescription) {
		this.userdescription = userdescription;
	}
	public int getUserstatus() {
		return userstatus;
	}
	public void setUserstatus(int userstatus) {
		this.userstatus = userstatus;
	}
	public Date getUsercreatetime() {
		return usercreatetime;
	}
	public void setUsercreatetime(Date usercreatetime) {
		this.usercreatetime = usercreatetime;
	}
	public Date getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	public long getLogincount() {
		return logincount;
	}
	public void setLogincount(long logincount) {
		this.logincount = logincount;
	}
	public long getUsertype() {
		return usertype;
	}
	public void setUsertype(long usertype) {
		this.usertype = usertype;
	}
	
	
	
}
