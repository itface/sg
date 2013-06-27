package com.sapGarden.system.menu.service;

import net.sf.json.JSONArray;

public interface MenuService {
	public JSONArray findAllMenuJson();
	public JSONArray findCheckedTreeJson();
	public JSONArray findNotSystemMenuCheckedTree(long roleId);
	public JSONArray getMenuCheckedTreeByRoleId(long roleId);
}
