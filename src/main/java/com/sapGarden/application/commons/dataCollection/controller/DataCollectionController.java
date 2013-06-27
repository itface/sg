package com.sapGarden.application.commons.dataCollection.controller;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.application.commons.dataCollection.model.NewSapDataCollection;
import com.sapGarden.application.commons.dataCollection.service.SapDataCollectionService;

@Controller
@RequestMapping("/system/dataCollection")
public class DataCollectionController {
	@Autowired
	private SapDataCollectionService sapDataCollectionService;

	@RequestMapping
	public ModelAndView index(){
		return new ModelAndView("application/synConfig/common/dataCollection");
	}
	@RequestMapping(value=("/sapDataCollection/data"),method = RequestMethod.GET)
	public @ResponseBody Object showData(){
		JSONObject jsonObject = sapDataCollectionService.findAllJson();
		return jsonObject==null?"{}":jsonObject;
	}
	@RequestMapping(value=("/sapDataCollection/data"),method = RequestMethod.POST)
	public @ResponseBody String _new(NewSapDataCollection newSapDataCollection){
		return sapDataCollectionService.addOneByNewSapClient(newSapDataCollection);
	}
	@RequestMapping(value=("/sapDataCollection/data/{id}"),method = RequestMethod.PUT)
	public @ResponseBody String update(HttpServletRequest request,NewSapDataCollection newSapDataCollection){
		return sapDataCollectionService.updateOne(newSapDataCollection);
	}
	@RequestMapping(value=("/sapDataCollection/data/{id}"),method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable long id){
		sapDataCollectionService.deleteOne(id);
	}
	@RequestMapping(value=("/sapDataCollection/checkedTree"),method = RequestMethod.GET)
	public @ResponseBody Object showCheckedTreeData(){
		JSONArray jsonArray = sapDataCollectionService.findCheckedTreeJson();
		return jsonArray;
	}
}
