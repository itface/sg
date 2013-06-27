package com.sapGarden.system.org.controller;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.system.org.model.Menu;
import com.sapGarden.system.org.model.Resource;
import com.sapGarden.system.org.service.ResourceService;
@Controller
@RequestMapping(value=("/system/resource"))
public class ResourceController {

	ResourceService resourceService;
	
	@Autowired
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	@RequestMapping
	public ModelAndView index(){
		return new ModelAndView("/sys_menu_manage");
	}
	@RequestMapping(value=("/data"),method = RequestMethod.GET)
	public @ResponseBody Object showData(){
		//String json = "{\"total\":\"5\",\"page\":\"1\",\"records\":\"5\",\"rows\":[{\"id\":\"1\",\"level\":\"0\",\"parent\":\"null\",\"isLeaf\":\"true\",\"expanded\":\"false\",\"resourceName\":\"前台\",\"url\":\"/common/front\"},{\"id\":\"2\",\"level\":\"0\",\"parent\":\"null\",\"isLeaf\":\"true\",\"expanded\":\"false\",\"resourceName\":\"后台\",\"url\":\"/common/console\"},{\"id\":\"10\",\"level\":\"0\",\"parent\":\"null\",\"isLeaf\":\"true\",\"expanded\":\"false\",\"resourceName\":\"44\",\"url\":\"55\"},{\"id\":\"11\",\"level\":\"0\",\"parent\":\"null\",\"isLeaf\":\"true\",\"expanded\":\"false\",\"resourceName\":\"55\",\"url\":\"44\"},{\"id\":\"12\",\"level\":\"0\",\"parent\":\"null\",\"isLeaf\":\"true\",\"expanded\":\"false\",\"resourceName\":\"1\",\"url\":\"2\"}]}";
		JSONObject jsonObject = resourceService.findAllJson();
		//String json = "{\"total\":\"7\",\"page\":\"1\",\"records\":\"7\",\"rows\":[{\"loaded\":\"true\",\"id\":\"21\",\"level\":\"0\",\"isLeaf\":\"false\",\"ID\":\"21\",\"parent\":\"null\",\"expanded\":\"false\",\"resourceName\":\"数据类清单\",\"url\":\"\"},{\"loaded\":\"true\",\"id\":\"28\",\"level\":\"1\",\"isLeaf\":\"true\",\"ID\":\"28\",\"parent\":\"21\",\"expanded\":\"false\",\"resourceName\":\"22\",\"url\":\"\"},{\"loaded\":\"true\",\"id\":\"13\",\"level\":\"0\",\"isLeaf\":\"false\",\"ID\":\"13\",\"parent\":\"null\",\"expanded\":\"false\",\"resourceName\":\"系统维护\",\"url\":\"\"},{\"loaded\":\"true\",\"id\":\"16\",\"level\":\"1\",\"isLeaf\":\"true\",\"ID\":\"16\",\"parent\":\"13\",\"expanded\":\"false\",\"resourceName\":\"导航菜单\",\"url\":\"/system/menu\"},{\"loaded\":\"true\",\"id\":\"18\",\"level\":\"1\",\"isLeaf\":\"true\",\"ID\":\"18\",\"parent\":\"13\",\"expanded\":\"false\",\"resourceName\":\"用户管理\",\"url\":\"/system/user\"},{\"loaded\":\"true\",\"id\":\"19\",\"level\":\"1\",\"isLeaf\":\"true\",\"ID\":\"19\",\"parent\":\"13\",\"expanded\":\"false\",\"resourceName\":\"组织管理\",\"url\":\"/system/orgnization\"},{\"loaded\":\"true\",\"id\":\"17\",\"level\":\"1\",\"isLeaf\":\"true\",\"ID\":\"17\",\"parent\":\"13\",\"expanded\":\"false\",\"resourceName\":\"角色管理\",\"url\":\"/system/role\"}]}";
		//JSONObject jsonObject = JSONObject.fromObject(json);
		return jsonObject==null?"{}":jsonObject;
	}
	@RequestMapping(value=("/data"),method = RequestMethod.POST)
	public @ResponseBody void _new(Menu menu){
		resourceService.addOneByMenu(menu);
	}
	@RequestMapping(value=("/data/{id}"),method = RequestMethod.PUT)
	public @ResponseBody void update(HttpServletRequest request,Resource resource){
		resourceService.updateOne(resource);
	}
	@RequestMapping(value=("/data/{id}"),method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable long id){
		resourceService.deleteOne(id);
	}
	@RequestMapping(value=("/resourceIndex/{parentId}"),method = RequestMethod.GET)
	public @ResponseBody long getResourceIndexCount(@PathVariable long parentId){
		return resourceService.getSonNums(parentId);
	}
}
