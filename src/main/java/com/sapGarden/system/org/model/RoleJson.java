package com.sapGarden.system.org.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class RoleJson {
	private String total;
	private String page;
	private String records;
	private List<NewRole> rows;
	public RoleJson(List<Role> list){
		this.total=list.size()+"";
		this.page="1";
		this.records=list.size()+"";
		this.rows=new LinkedList();
		for(Role role : list){
			NewRole newRole = new NewRole();
			newRole.setId(role.getId()+"");
			newRole.setRoleName(role.getRoleName());
			newRole.setAlias(role.getAlias());
			Set<Resource> resources = role.getResources();
			String resourceIds = "";
			for(Resource resource :resources){
				resourceIds+=resource.getId()+",";
			}
			if(resourceIds!=null&&!"".equals(resourceIds)){
				resourceIds=resourceIds.substring(0, resourceIds.lastIndexOf(","));
				newRole.setResourcesIds(resourceIds);
			}
			rows.add(newRole);
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
	public List<NewRole> getRows() {
		return rows;
	}
	public void setRows(List<NewRole> rows) {
		this.rows = rows;
	}
	
}
