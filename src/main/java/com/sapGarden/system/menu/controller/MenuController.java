package com.sapGarden.system.menu.controller;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapGarden.system.menu.service.MenuService;

@Controller
@RequestMapping(value="/system/menu")
public class MenuController {

	MenuService menuService;
	@Autowired
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	@RequestMapping(value="/node",method=RequestMethod.GET)
	public @ResponseBody Object getData(){
		JSONArray jsonArray = menuService.findAllMenuJson();
		return jsonArray;
	}
	@RequestMapping(value="/checkedTreeNode",method=RequestMethod.GET)
	public @ResponseBody Object getCheckedTreeData(){
		JSONArray jsonArray = menuService.findCheckedTreeJson();
		return jsonArray;
	}
	@RequestMapping(value="/notSystemMenuCheckedTree/{roleId}",method=RequestMethod.GET)
	public @ResponseBody Object getNotSystemMenuCheckedTree(@PathVariable long roleId){
		JSONArray jsonArray = menuService.findNotSystemMenuCheckedTree(roleId);//menuService.findNotSelectedMenuCheckedTree(roleId);//
		return jsonArray;
	}
	@RequestMapping(value="/getMenuCheckedTreeByRoleId/{roleId}",method=RequestMethod.GET)
	public @ResponseBody Object getMenuCheckedTreeByRoleId(@PathVariable long roleId){
		JSONArray jsonArray = menuService.getMenuCheckedTreeByRoleId(roleId);
		return jsonArray;
	}
}
