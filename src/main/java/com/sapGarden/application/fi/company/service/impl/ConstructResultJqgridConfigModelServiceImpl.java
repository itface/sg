package com.sapGarden.application.fi.company.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.service.Runtime_ColumnInfo_Service;
import com.sapGarden.global.jqgrid.model.Jqgrid_ConfigModel;
import com.sapGarden.global.jqgrid.model.Jqgrid_columnModel;
import com.sapGarden.global.jqgrid.model.Jqgrid_searchoptions;
import com.sapGarden.global.jqgrid.service.ConstructJqgridConfigModelService;
@Service("bo_fin_company_constructResult_JqgridConfigModel")
public class ConstructResultJqgridConfigModelServiceImpl implements ConstructJqgridConfigModelService{

	private final String id = "resultGrid";
	private final int height=0;
	private final int width =0;
	private final String caption="公司信息";
	private final boolean multiselect = false;
	private final boolean editable=false;
	private final boolean searchable=false;
	private final String baseUrl="";
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	@Autowired
	public void setRuntime_ColumnInfo_Service(Runtime_ColumnInfo_Service runtime_ColumnInfo_Service) {
		this.runtime_ColumnInfo_Service = runtime_ColumnInfo_Service;
	}

	@Override
	public String construct(SapDataCollection sapDataCollection,String businesstype) {
		// TODO Auto-generated method stub
		List<RuntimeColumnInfo> list = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection, businesstype);
		Jqgrid_ConfigModel jqgrid_ConfigModel = new Jqgrid_ConfigModel("",id,height,width,caption,baseUrl,multiselect,searchable,editable,this.costructColumnNames(list),this.constructColumnModel(list));
		return jqgrid_ConfigModel.toJsonString();
	}

	private String[] costructColumnNames(List<RuntimeColumnInfo> list){
		String[] columnNames = null;
		if(list!=null&&list.size()>0){
			columnNames = new String[list.size()];
			int count=0;
			for(RuntimeColumnInfo runtimeColumnInfo : list){
				columnNames[count++]=runtimeColumnInfo.getSourceColumnName();
			}
		}
		return columnNames;
	}
	private List<Jqgrid_columnModel> constructColumnModel(List<RuntimeColumnInfo> list){
		List<Jqgrid_columnModel> columnModelList = null;
		if(list!=null&&list.size()>0){
			columnModelList=new LinkedList<Jqgrid_columnModel>();
			for(RuntimeColumnInfo runtimeColumnInfo : list){
				String name = runtimeColumnInfo.getSourceColumn().toLowerCase();
				String index = name;
				int width = 100;
				boolean search = false;
				String searchtype = "text";
				boolean editable = false;
				Jqgrid_searchoptions searchoptions = null;
				Jqgrid_columnModel jqgrid_columnModel = new Jqgrid_columnModel(name,index,width,true,false,editable,"",search,searchtype,searchoptions,null,null);
				columnModelList.add(jqgrid_columnModel);
			}
		}
		return columnModelList;
	}
}
