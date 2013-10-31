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
@Service("compareData2")
public class CompareDataGridOfCommonConstructJqgridService implements CommonConstructJqgridService{

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
		List<RuntimeColumnInfo> list = runtime_ColumnInfo_Service.findAll(sapDataCollection, type);
		List<Jqgrid_columnModel> list2 = this.constructColumnModel(list,sortable);
		Jqgrid_ConfigModel jqgrid_ConfigModel = new Jqgrid_ConfigModel("","",0,gridWidth,"","",true,false,false,this.costructColumnNames(list),list2);
		return jqgrid_ConfigModel.toJsonString();
	}
	private String[] costructColumnNames(List<RuntimeColumnInfo> list){
		//String[] columnNames = {"dataid","S:公司代码","G:公司代码","S:公司名称","G:公司名称","S:城市","G:城市","S:国家","G:国家","S:货币","G:货币","S:语言","G:语言","S:帐目表","G:帐目表","S:会计年度变式","G:会计年度变式","S:增值税登记号","G:增值税登记号","S:公司","G:公司","S:地址号码","G:地址号码"};
		//return columnNames;
		String[] columnNames = null;
		if(list!=null&&list.size()>0){
			columnNames = new String[list.size()*2+1];
			columnNames[0]="dataid";
			int count=1;
			for(RuntimeColumnInfo runtimeColumnInfo : list){
				columnNames[count++]="S_"+runtimeColumnInfo.getSourceColumnName();
				columnNames[count++]="G_"+runtimeColumnInfo.getSourceColumnName();
			}
		}
		return columnNames;
	}
	private List<Jqgrid_columnModel> constructColumnModel(List<RuntimeColumnInfo> list,boolean sortable){
		List<Jqgrid_columnModel> columnModelList = null;
		if(list!=null&&list.size()>0){
			columnModelList=new LinkedList<Jqgrid_columnModel>();
			Jqgrid_columnModel jqgrid_columnModel_1 = new Jqgrid_columnModel("dataid","dataid",100,sortable,true,false,"",false,"text",null,null,null);
			columnModelList.add(jqgrid_columnModel_1);
			gridWidth += 100;
			for(RuntimeColumnInfo runtimeColumnInfo : list){
				String name = runtimeColumnInfo.getSourceColumn().toLowerCase()+"_s";
				String index = name;
				int width = 150;
				boolean editable = false;
				boolean search = false;
				String searchtype = runtimeColumnInfo.getSearchtype();
				Jqgrid_searchoptions searchoptions = new Jqgrid_searchoptions();
				String edittype="";
				if("DATE".equalsIgnoreCase(searchtype)){
					String[] sopt ={"ge","le"};
					edittype="date";
					searchoptions = new Jqgrid_searchoptions(sopt);
				}
				Jqgrid_columnModel jqgrid_columnModel = new Jqgrid_columnModel(name,index,width,sortable,false,editable,edittype,search,searchtype,searchoptions,null,null);
				columnModelList.add(jqgrid_columnModel);
				String name2 = runtimeColumnInfo.getSourceColumn().toLowerCase()+"_g";
				String index2 = name2;
				int width2 = 150;
				boolean search2 = false;
				String searchtype2 = "text";
				boolean editable2 = false;
				Jqgrid_searchoptions searchoptions2 = null;
				Jqgrid_columnModel jqgrid_columnModel2 = new Jqgrid_columnModel(name2,index2,width2,sortable,false,editable2,"",search2,searchtype2,searchoptions2,null,null);
				columnModelList.add(jqgrid_columnModel2);
				gridWidth += 150+150;
			}
		}
		return columnModelList;
	}
}
