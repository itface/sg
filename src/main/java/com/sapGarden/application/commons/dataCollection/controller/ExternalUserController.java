package com.sapGarden.application.commons.dataCollection.controller;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapGarden.application.commons.dataCollection.model.ExternalUser;
import com.sapGarden.application.commons.dataCollection.model.NewExternalUser;
import com.sapGarden.application.commons.dataCollection.service.ExternalUserService;

@Controller
@RequestMapping("/system/dataCollection")
public class ExternalUserController {
	@Autowired
	private ExternalUserService externalUserService;
	@RequestMapping(value=("/externalUser/data"),method = RequestMethod.GET)
	public @ResponseBody Object showData(){
		JSONObject jsonObject = externalUserService.findAllJson();
		//返回结果不能为null，如果为空则jqgrid子表不刷新。假设删除最后一条记录，如果返回null，其实数据库中已经删除，但在页面中还没有删除
		return jsonObject==null?"{}":jsonObject;
	}
	@RequestMapping(value=("/externalUser/data"),method = RequestMethod.POST)
	public @ResponseBody void _new(NewExternalUser newExternalUser){
		externalUserService.addOne(new ExternalUser(newExternalUser));
	}
	@RequestMapping(value=("/externalUser/data/{id}"),method = RequestMethod.PUT)
	public @ResponseBody void update(NewExternalUser newExternalUser){
		externalUserService.updateOne(new ExternalUser(newExternalUser));
	}
	@RequestMapping(value=("/externalUser/data/{id}"),method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable long id){
		externalUserService.deleteOne(id);
	}
	
//	@RequestMapping(value=("/sapDataCollection/checkedTree"),method = RequestMethod.GET)
//	public @ResponseBody Object showCheckedTreeData(){
//		JSONArray jsonArray = sapDataCollectionService.findCheckedTreeJson();
//		return jsonArray;
//	}
}
