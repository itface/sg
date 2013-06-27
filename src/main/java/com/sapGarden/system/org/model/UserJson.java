package com.sapGarden.system.org.model;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.global.constants.SystemConstants;

public class UserJson {

	private String total;
	private String page;
	private String records;
	private List<NewUser> rows;
	public UserJson(List<User> list){
		this.total=list.size()+"";
		this.page="1";
		this.records=list.size()+"";
		this.rows=new LinkedList();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(User user : list){
			NewUser newUser = new NewUser();
			newUser.setId(user.getId()+"");
			newUser.setName(user.getName());
			newUser.setPassword(user.getPassword());
			newUser.setRepassword(user.getPassword());
			newUser.setUsername(user.getUsername());
			newUser.setUserdescription(user.getUserdescription());
			newUser.setUserstatus(user.getUserstatus()+"");
			newUser.setUsercreatetime(user.getUsercreatetime()==null?"":sd.format(user.getUsercreatetime()));
			newUser.setLastlogintime(user.getLastlogintime()==null?"":sd.format(user.getLastlogintime()));
			newUser.setLogincount(user.getLogincount()+"");
			newUser.setStatus(user.getUserstatus()==0?"非激活":"激活");
			newUser.setUsertype(user.getUsertype()+"");
			Set<Role> roles = user.getRoles();
			String roleIds = "";
			for(Role role :roles){
				String roleAlias = role.getAlias();
				if(roleAlias!=null&&roleAlias.indexOf(SystemConstants.RoleAlias_USERTYPE_PREFIX)==0){
					newUser.setType(role.getRoleName());
				}else{
					roleIds+=role.getId()+",";
				}
			}
			if(roleIds!=null&&!"".equals(roleIds)){
				roleIds=roleIds.substring(0, roleIds.lastIndexOf(","));
				newUser.setRoleIds(roleIds);
			}
			String sapDataCollectionIds="";
			String sapDataCollectionAlias="";
			Set<SapDataCollection> sapDataCollections = user.getSapDataCollections();
			if(sapDataCollections!=null&&sapDataCollections.size()>0){
				for(SapDataCollection sapDataCollection :sapDataCollections){
					sapDataCollectionIds+=sapDataCollection.getId()+",";
					sapDataCollectionAlias+=sapDataCollection.getAlias()+",";
				}
				if(sapDataCollectionIds!=null&&!"".equals(sapDataCollectionIds)){
					sapDataCollectionIds=sapDataCollectionIds.substring(0, sapDataCollectionIds.lastIndexOf(","));
					newUser.setSapDataCollections(sapDataCollectionIds);
				}
				if(sapDataCollectionAlias!=null&&!"".equals(sapDataCollectionAlias)){
					sapDataCollectionAlias=sapDataCollectionAlias.substring(0, sapDataCollectionAlias.lastIndexOf(","));
					newUser.setTempSapDataCollections(sapDataCollectionAlias);
				}
			}
			rows.add(newUser);
		}
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getRecords() {
		return records;
	}
	public void setRecords(String records) {
		this.records = records;
	}
	public List<NewUser> getRows() {
		return rows;
	}
	public void setRows(List<NewUser> rows) {
		this.rows = rows;
	}
	
}
