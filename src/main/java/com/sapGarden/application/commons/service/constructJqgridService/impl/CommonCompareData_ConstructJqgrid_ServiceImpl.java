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

@Service("commonCompareData_ConstructJqgrid_Service")
public class CommonCompareData_ConstructJqgrid_ServiceImpl implements Common_ConstructJqgrid_Service{

	private  int height=0;
	private  int width =0;
	private final String caption="校对结果(S:代表SAP系统,G:代表本系统)";
	private  boolean multiselect = true;
	private  boolean searchable=false;
	private  boolean editable=false;
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	@Autowired
	public void setRuntime_ColumnInfo_Service(Runtime_ColumnInfo_Service runtime_ColumnInfo_Service) {
		this.runtime_ColumnInfo_Service = runtime_ColumnInfo_Service;
	}
	@Override
	public String construct(SapDataCollection sapDataCollection, String tableName,String gridId, String gridCaption, String gridUrl,String pager) {
		// TODO Auto-generated method stub
		List<RuntimeColumnInfo> list = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection, tableName.toUpperCase());
		Jqgrid_ConfigModel jqgrid_ConfigModel = new Jqgrid_ConfigModel(pager,gridId,height,width,gridCaption,gridUrl,multiselect,searchable,editable,this.costructColumnNames(list),this.constructColumnModel(list));
		return jqgrid_ConfigModel.toJsonString();
	}
	private String[] costructColumnNames(List<RuntimeColumnInfo> list){
		String[] columnNames = null;
		if(list!=null&&list.size()>0){
			columnNames = new String[list.size()*2+1];
			columnNames[0]="dataid";
			int count=1;
			for(RuntimeColumnInfo runtimeColumnInfo : list){
				columnNames[count++]="S:"+runtimeColumnInfo.getSourceColumnName();
				columnNames[count++]="G:"+runtimeColumnInfo.getSourceColumnName();
			}
		}
		return columnNames;
	}
	private List<Jqgrid_columnModel> constructColumnModel(List<RuntimeColumnInfo> list){
		List<Jqgrid_columnModel> columnModelList = null;
		if(list!=null&&list.size()>0){
			columnModelList=new LinkedList<Jqgrid_columnModel>();
			Jqgrid_columnModel jqgrid_columnModel_1 = new Jqgrid_columnModel("dataid","dataid",100,true,true,false,"",false,"text",null,null,null);
			columnModelList.add(jqgrid_columnModel_1);
			for(RuntimeColumnInfo runtimeColumnInfo : list){
				String name = runtimeColumnInfo.getSourceColumn().toLowerCase()+"_s";
				String index = name;
				int width = 100;
				boolean editable = false;
				boolean search = false;
				String searchtype = runtimeColumnInfo.getSearchtype();
				Jqgrid_searchoptions searchoptions = new Jqgrid_searchoptions();
				String edittype="";
				if(searchtype!=null&&searchtype.toUpperCase().equals("DATE")){
					String[] sopt ={"ge","le"};
					edittype="date";
					searchoptions = new Jqgrid_searchoptions(sopt);
				}
				Jqgrid_columnModel jqgrid_columnModel = new Jqgrid_columnModel(name,index,width,true,false,editable,edittype,search,searchtype,searchoptions,null,null);
				columnModelList.add(jqgrid_columnModel);
				String name2 = runtimeColumnInfo.getSourceColumn().toLowerCase()+"_g";
				String index2 = name2;
				int width2 = 100;
				boolean search2 = false;
				String searchtype2 = "text";
				boolean editable2 = false;
				Jqgrid_searchoptions searchoptions2 = null;
				Jqgrid_columnModel jqgrid_columnModel2 = new Jqgrid_columnModel(name2,index2,width2,true,false,editable2,"",search2,searchtype2,searchoptions2,null,null);
				columnModelList.add(jqgrid_columnModel2);
			}
		}
		return columnModelList;
	}
	


}
