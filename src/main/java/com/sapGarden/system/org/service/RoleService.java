package com.sapGarden.system.org.service;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sapGarden.system.org.model.NewRole;
import com.sapGarden.system.org.model.Role;

public interface RoleService {

	public JSONObject findAllJson();
	public Role addOneByNewRole(NewRole newRole);
	public void updateOne(NewRole newRole);
	public void deleteOne(Long id);
	public JSONArray findCheckedTreeJson();
	public List<Role> findAll();
	public Role findRoleById(long id);
	public String findRoleSelect();
	public void updateRoleResource(long roleId,String resourceIds);
}
