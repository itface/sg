package com.sapGarden.application.commons.service.constructJqgridService.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.service.Runtime_ColumnInfo_Service;
import com.sapGarden.application.commons.service.constructJqgridService.CommonConstructJqgridService;
import com.sapGarden.global.jqgrid.model.Jqgrid_ConfigModel;
import com.sapGarden.global.jqgrid.model.Jqgrid_columnModel;
import com.sapGarden.global.jqgrid.model.Jqgrid_searchoptions;
@Service("commonData")
public class DataGridOfCommonConstructJqgridService implements CommonConstructJqgridService{

	@Autowired
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	private int gridWidth = 0;
	@Override
	public String construct(SapDataCollection sapDataCollection, String type,boolean sortable) {
		// TODO Auto-generated method stub
		/**
		 * 单例模式，每次要初始化，否则每调一次该方法，gridWidth都会double
		 */
		gridWidth = 200;
		List<RuntimeColumnInfo> list = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection, type);
		List<Jqgrid_columnModel> list2 = this.constructColumnModel(list,sortable);
		Jqgrid_ConfigModel jqgrid_ConfigModel = new Jqgrid_ConfigModel("","",0,gridWidth,"","",false,false,false,this.costructColumnNames(list),list2);
		return jqgrid_ConfigModel.toJsonString();
	}

	private String[] costructColumnNames(List<RuntimeColumnInfo> list){
		String[] columnNames = null;
		if(list!=null&&list.size()>0){
			columnNames = new String[list.size()+1];
			columnNames[0]="sapclient";
			int count=1;
			for(RuntimeColumnInfo runtimeColumnInfo : list){
				columnNames[count++]=runtimeColumnInfo.getSourceColumnName();
			}
		}
		return columnNames;
	}
	private List<Jqgrid_columnModel> constructColumnModel(List<RuntimeColumnInfo> list,boolean sortable){
		List<Jqgrid_columnModel> columnModelList = null;
		if(list!=null&&list.size()>0){
			columnModelList=new LinkedList<Jqgrid_columnModel>();
			Jqgrid_columnModel jqgrid_columnModel_1 = new Jqgrid_columnModel("sapclient","sapclient",100,sortable,true,false,"",false,"text",null,null,null);
			columnModelList.add(jqgrid_columnModel_1);
			for(RuntimeColumnInfo runtimeColumnInfo : list){
				String name = runtimeColumnInfo.getSourceColumn().toLowerCase();
				String index = name;
				int width = 150;
				boolean search = false;
				String searchtype = "text";
				boolean editable = false;
				Jqgrid_searchoptions searchoptions = null;
				Jqgrid_columnModel jqgrid_columnModel = new Jqgrid_columnModel(name,index,width,sortable,false,editable,"",search,searchtype,searchoptions,null,null);
				columnModelList.add(jqgrid_columnModel);
				gridWidth+=150;
			}
		}
		return columnModelList;
	}
}
