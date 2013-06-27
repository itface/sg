package com.sapGarden.application.commons.service.constructJqgridService.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.service.Runtime_ColumnInfo_Service;
import com.sapGarden.application.commons.service.constructJqgridService.Common_ConstructJqgrid_Service;
import com.sapGarden.global.jqgrid.model.Jqgrid_ConfigModel;
import com.sapGarden.global.jqgrid.model.Jqgrid_columnModel;
import com.sapGarden.global.jqgrid.model.Jqgrid_searchoptions;

@Service("commonData_ConstructJqgrid_Service")
public class CommonData_ConstructJqgrid_ServiceImpl implements Common_ConstructJqgrid_Service{

	private  int height=400;
	private  int width =500;
	private  boolean multiselect = false;
	private  boolean searchable=true;
	private  boolean editable=false;
	private  Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	@Autowired
	public void setRuntime_ColumnInfo_Service(Runtime_ColumnInfo_Service runtime_ColumnInfo_Service) {
		this.runtime_ColumnInfo_Service = runtime_ColumnInfo_Service;
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
				String edittype="";
				boolean search = (runtimeColumnInfo.getSearchable()==0?false:true);
				String searchtype = runtimeColumnInfo.getSearchtype();
				Jqgrid_searchoptions searchoptions = new Jqgrid_searchoptions();
				if(searchtype!=null&&searchtype.toUpperCase().equals("DATE")){
					String[] sopt ={"ge","le"};
					edittype="date";
					searchoptions = new Jqgrid_searchoptions(sopt);
				}
				boolean editable = false;
				Jqgrid_columnModel jqgrid_columnModel = new Jqgrid_columnModel(name,index,width,true,false,editable,edittype,search,searchtype,searchoptions,null,null);
				columnModelList.add(jqgrid_columnModel);
			}
		}
		return columnModelList;
	}
	@Override
	public String construct(SapDataCollection sapDataCollection, String tableName,String gridId, String gridCaption, String gridUrl,String pager) {
		// TODO Auto-generated method stub
		List<RuntimeColumnInfo> list = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection, tableName.toUpperCase());
		Jqgrid_ConfigModel jqgrid_ConfigModel = new Jqgrid_ConfigModel(pager,gridId,height,width,gridCaption,gridUrl,multiselect,searchable,editable,this.costructColumnNames(list),this.constructColumnModel(list));
		return jqgrid_ConfigModel.toJsonString();
	}


}
