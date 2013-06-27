package com.sapGarden.system.org.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.system.db.DbContextHolder;
import com.sapGarden.system.org.model.NewRole;
import com.sapGarden.system.org.service.RoleService;

@Controller
@RequestMapping(value="/system/role")
public class RoleController {

	private RoleService roleService;
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@RequestMapping
	public ModelAndView index(){
		return new ModelAndView("/sys_role_manage");
	}
	@RequestMapping(value=("/data"),method = RequestMethod.GET)
	public @ResponseBody Object showData(){
		JSONObject jsonObject = roleService.findAllJson();
		return jsonObject==null?"{}":jsonObject;
	}
	@RequestMapping(value=("/data"),method = RequestMethod.POST)
	public @ResponseBody void _new(NewRole newRole){
		roleService.addOneByNewRole(newRole);
	}
	@RequestMapping(value=("/data/{id}"),method = RequestMethod.PUT)
	public @ResponseBody void update(NewRole newRole){
		roleService.updateOne(newRole);
	}
	@RequestMapping(value=("/data/{id}"),method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable long id){
		roleService.deleteOne(id);
	}
	@RequestMapping(value=("/checkedTree"),method = RequestMethod.GET)
	public @ResponseBody Object showCheckedTreeData(){
		JSONArray jsonArray = roleService.findCheckedTreeJson();
		return jsonArray;
	}
	@RequestMapping(value=("/getRoleSelect"),method = RequestMethod.GET)
	public @ResponseBody String getRoleSelect(){
		return roleService.findRoleSelect();
	}
	@RequestMapping(value=("/updateRoleResource"))
	public @ResponseBody void updateRoleResource(@RequestParam long roleId,@RequestParam String resourceIds){
		roleService.updateRoleResource(roleId, resourceIds);
	}
}
