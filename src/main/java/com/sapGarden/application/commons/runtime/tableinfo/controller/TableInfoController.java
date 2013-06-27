package com.sapGarden.application.commons.runtime.tableinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapGarden.application.commons.runtime.tableinfo.model.RuntimeTableInfo;
import com.sapGarden.application.commons.runtime.tableinfo.service.Runtime_TableInfo_Service;
import com.sapGarden.global.exception.JpaException;

@Controller
@RequestMapping("/application/common/tableInfo")
public class TableInfoController {

	private Runtime_TableInfo_Service runtime_TableInfo_Service;
	@Autowired
	public void setRuntime_TableInfo_Service(Runtime_TableInfo_Service runtime_TableInfo_Service) {
		this.runtime_TableInfo_Service = runtime_TableInfo_Service;
	}
	//*************************************运行设置-〉表映射*****************************************************************
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody void _newDbReflectData(RuntimeTableInfo dbReflect) throws JpaException{
		runtime_TableInfo_Service.addOne(dbReflect);
	}
	@RequestMapping(value=("/{id}"),method=RequestMethod.PUT)
	public @ResponseBody void updateDbReflectData(RuntimeTableInfo dbReflect) throws JpaException{
		runtime_TableInfo_Service.updateOne(dbReflect);
	}
}
