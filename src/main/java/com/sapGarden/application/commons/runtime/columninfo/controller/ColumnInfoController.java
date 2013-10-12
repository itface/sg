package com.sapGarden.application.commons.runtime.columninfo.controller;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapGarden.application.commons.runtime.columninfo.model.JqgirdNewRuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.service.Runtime_ColumnInfo_Service;
import com.sapGarden.global.exception.JpaException;
import com.sapGarden.system.org.model.User;

@Controller
@RequestMapping("/application/common/columnInfo")
public class ColumnInfoController {

	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	@Autowired
	public void setRuntime_ColumnInfo_Service(Runtime_ColumnInfo_Service runtime_ColumnInfo_Service) {
		this.runtime_ColumnInfo_Service = runtime_ColumnInfo_Service;
	}
	@RequestMapping(value=("/{tablename}"),method=RequestMethod.GET)
	public @ResponseBody Object getDbReflectOfColumnData(@PathVariable String tablename,@RequestParam int page,@RequestParam int rows,String sidx,String sord) throws JpaException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject json = runtime_ColumnInfo_Service.findJsonByBusinesstype(page,rows,sidx,sord,user.getCurrentSapDataCollection(),tablename.toUpperCase());
		return json==null?"{}":json;
	}
	@RequestMapping(value=("/{tablename}"),method=RequestMethod.POST)
	public @ResponseBody void _newDbReflectOfColumnData(@PathVariable String tablename,JqgirdNewRuntimeColumnInfo jqgirdNewDbReflectOfColumn) throws JpaException{
		jqgirdNewDbReflectOfColumn.setBusinesstype(tablename.toUpperCase());
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		jqgirdNewDbReflectOfColumn.setSapclient(user.getCurrentSapDataCollection().getId());
		runtime_ColumnInfo_Service.addOne(jqgirdNewDbReflectOfColumn);
	}
	@RequestMapping(value=("/{tablename}/{id}"),method=RequestMethod.PUT)
	public @ResponseBody void updateDbReflectOfColumnData(@PathVariable String tablename,JqgirdNewRuntimeColumnInfo jqgirdNewDbReflectOfColumn) throws JpaException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		jqgirdNewDbReflectOfColumn.setSapclient(user.getCurrentSapDataCollection().getId());
		jqgirdNewDbReflectOfColumn.setBusinesstype(tablename.toUpperCase());
		runtime_ColumnInfo_Service.updateOne(jqgirdNewDbReflectOfColumn);
	}
	@RequestMapping(value=("/{id}"),method=RequestMethod.DELETE)
	public @ResponseBody void deleteDbReflectOfColumnData(@PathVariable long id) throws JpaException{
		runtime_ColumnInfo_Service.deleteOne(id);
	}
	@RequestMapping(value=("/updateStatus"))
	public @ResponseBody void updateDbReflectOfColumnData(long id,String status) throws JpaException{
		runtime_ColumnInfo_Service.updateStatusById(id, status);
	}
}
