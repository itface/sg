package com.sapGarden.application.commons.service.constructJqgridService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.service.Runtime_ColumnInfo_Service;
import com.sapGarden.application.commons.service.constructJqgridService.Common_ConstructCompareGrid_Service;
@Service
public class Common_ConstructCompareGrid_ServiceImpl implements Common_ConstructCompareGrid_Service{

	private final int columnWidth = 200;
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	
	@Autowired
	public void setRuntime_ColumnInfo_Service(Runtime_ColumnInfo_Service runtime_ColumnInfo_Service) {
		this.runtime_ColumnInfo_Service = runtime_ColumnInfo_Service;
	}

	@Override
	public String getTableHeaderHtml(SapDataCollection sapDataCollection,String tableName) {
		// TODO Auto-generated method stub
		List<RuntimeColumnInfo> list = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection, tableName.toUpperCase());
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		sb.append(	"<td width='10px' style='border:1px solid #ccc;border-collapse:collapse'>");
		sb.append(	"<input type='checkbox' class='gridCheckboxAll'/>");
		sb.append(	"</td>");
		sb.append(	"<td width='"+columnWidth+"px' style='border:1px solid #ccc;border-collapse:collapse'>");
		sb.append(	"״̬");
		sb.append(	"</td>");
		for(RuntimeColumnInfo runtimeColumnInfo : list){
			sb.append(	"<td width='"+columnWidth+"px' style='border:1px solid #ccc;border-collapse:collapse'>");
			sb.append("S:"+runtimeColumnInfo.getSourceColumnName());
			sb.append(	"</td>");
			sb.append(	"<td width='"+columnWidth+"px' style='border:1px solid #ccc;border-collapse:collapse'>");
			sb.append("G:"+runtimeColumnInfo.getSourceColumnName());
			sb.append(	"</td>");
		}
		sb.append(	"<td  style='border:1px solid #ccc;border-collapse:collapse'>");
		sb.append(	"&nbsp;");
		sb.append(	"</td>");
		sb.append("</tr>");
		return sb.toString();
	}


	@Override
	public long getTableWidth(SapDataCollection sapDataCollection, String tableName) {
		// TODO Auto-generated method stub
		return runtime_ColumnInfo_Service.countByTableAndSapclient(tableName, sapDataCollection.getId())*columnWidth+600;
	}

}
