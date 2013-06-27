package com.sapGarden.application.commons.application.dataCollectionMenu.controller;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.dataCollection.service.SapDataCollectionService;

@Controller
@RequestMapping("/system/dataCollectionMenu")
public class DataCollectionMenuController {
	@Autowired
	private SapDataCollectionService sapDataCollectionService;

	
	@RequestMapping(value=("/{roleId}"))
	public ModelAndView index(@PathVariable long roleId){
		Map map = new HashMap();
		map.put("roleId", roleId);
		return new ModelAndView("application/synConfig/common/dataCollectionMenu",map);
	}
	@RequestMapping(value=("/data/{sapDataCollectionId}"),method = RequestMethod.GET)
	public @ResponseBody Object showData(@PathVariable long sapDataCollectionId){
		JSONObject jsonObject = sapDataCollectionService.findAllJson();
		return jsonObject==null?"{}":jsonObject;
	}
	@RequestMapping(value=("/fullMenu/{roleId}"))
	public ModelAndView fullMenu(@PathVariable long roleId){
		Map map = new HashMap();
		map.put("roleId", roleId);
		return new ModelAndView("application/synConfig/common/fullMenu",map);
	}
//	@RequestMapping(value=("/fullMenu/updateRoleResource"))
//	public @ResponseBody void updateRoleResource(@RequestParam long roleId,@RequestParam String resourceIds,@RequestParam String btypes){
//		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		//因为更新时有两个操作，一个是更新菜单，一个是更新数据项的激活状态，而这两个操作可能不是在一个库中，所以需要设置数据库连接
//		DbContextHolder.setDbType("dataSource"+user.getCurrentSapDataCollection().getId());
//		dataCollectionMenuService.updateRoleResource(user.getCurrentSapDataCollection().getId(), roleId, resourceIds, btypes);
//	}
//	@RequestMapping(value=("/sapDataCollection/data"),method = RequestMethod.POST)
//	public @ResponseBody String _new(NewSapDataCollection newSapDataCollection){
//		return sapDataCollectionService.addOneByNewSapClient(newSapDataCollection);
//	}
//	@RequestMapping(value=("/sapDataCollection/data/{id}"),method = RequestMethod.PUT)
//	public @ResponseBody String update(HttpServletRequest request,NewSapDataCollection newSapDataCollection){
//		return sapDataCollectionService.updateOne(newSapDataCollection);
//	}
//	@RequestMapping(value=("/sapDataCollection/data/{id}"),method = RequestMethod.DELETE)
//	public @ResponseBody void delete(@PathVariable long id){
//		sapDataCollectionService.deleteOne(id);
//	}
//	@RequestMapping(value=("/sapDataCollection/checkedTree"),method = RequestMethod.GET)
//	public @ResponseBody Object showCheckedTreeData(){
//		JSONArray jsonArray = sapDataCollectionService.findCheckedTreeJson();
//		return jsonArray;
//	}
}
