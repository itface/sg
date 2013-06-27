package com.sapGarden.application.jco.commons.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.service.Runtime_ColumnInfo_Service;
import com.sapGarden.application.jco.commons.model.CommonExportModel;
import com.sapGarden.application.jco.commons.service.CommonRfcService;

public abstract class AbstractRfcService implements CommonRfcService{

	private List<RuntimeColumnInfo> runtimeColumnInfoList = null;
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	@Autowired
	public void setRuntime_ColumnInfo_Service(Runtime_ColumnInfo_Service runtime_ColumnInfo_Service) {
		this.runtime_ColumnInfo_Service = runtime_ColumnInfo_Service;
	}
	protected CommonExportModel ifSyn(SapDataCollection sapDataCollection,String type,CommonExportModel detail) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException{
		if(this.runtimeColumnInfoList==null){
			this.runtimeColumnInfoList = runtime_ColumnInfo_Service.findAll(sapDataCollection,type);
		}
		Class clazz = detail.getClass();
		CommonExportModel syn_detail = (CommonExportModel)clazz.newInstance();
		for(RuntimeColumnInfo runtimeColumnInfo : this.runtimeColumnInfoList){
			String status = runtimeColumnInfo.getStatus();
			String colName = runtimeColumnInfo.getSourceColumn().toLowerCase();
			if(!"INACTIVE".equals(status)){
				String getMethodName = "get"+colName.substring(0, 1).toUpperCase()+colName.substring(1);
				String setMethodName = "set"+colName.substring(0, 1).toUpperCase()+colName.substring(1);
				Method getMethod = clazz.getMethod(getMethodName, new Class[]{});
				Object o = getMethod.invoke(detail,new Object[]{});
				String colValue = null;
				if(o!=null){
					colValue=(String)o;
				}
				Method setMethod = clazz.getMethod(setMethodName, new Class[]{String.class});
				setMethod.invoke(syn_detail, new Object[]{colValue});
			}
		}
		return syn_detail;
	}
	
}
