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
@Service("commonMonitor")
public class MonitorGridOfCommonConstructJqgridService implements CommonConstructJqgridService{

	@Autowired
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	private int gridWidth = 0;

	@Override
	public String construct(SapDataCollection sapDataCollection, String type,boolean sortable) {
		// TODO Auto-generated method stub
		/**
		 * 单例模式，每次要初始化，否则每调一次该方法，gridWidth都会double
		 */
		gridWidth = 250;
		List<RuntimeColumnInfo> list = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection, type);
		List<Jqgrid_columnModel> list2 = this.constructColumnModel(list,sortable);
		Jqgrid_ConfigModel jqgrid_ConfigModel = new Jqgrid_ConfigModel("","",0,gridWidth,"","",true,false,false,this.costructColumnNames(list),list2);
		return jqgrid_ConfigModel.toJsonString();
	}

	private String[] costructColumnNames(List<RuntimeColumnInfo> list){
		String[] columnNames = null;
		if(list!=null&&list.size()>0){
			columnNames = new String[list.size()*2+7];
			columnNames[0]="ID";
			columnNames[1]="操作结果";
			columnNames[2]="操作时间";
			columnNames[3]="操作人";
			columnNames[4]="操作";
			columnNames[5]="操作类型";
			columnNames[6]="异常信息";
			
			int count=7;
			for(RuntimeColumnInfo runtimeColumnInfo : list){
				columnNames[count++]=runtimeColumnInfo.getSourceColumnName();
				columnNames[count++]=runtimeColumnInfo.getSourceColumnName()+"_原值";
			}
		}
		return columnNames;
	}
	private List<Jqgrid_columnModel> constructColumnModel(List<RuntimeColumnInfo> list,boolean sortable){
		List<Jqgrid_columnModel> columnModelList = null;
		if(list!=null&&list.size()>0){
			columnModelList=new LinkedList<Jqgrid_columnModel>();
			Jqgrid_columnModel jqgrid_columnModel_1 = new Jqgrid_columnModel("id","id",100,sortable,true,false,"",false,"text",null,null,null);
			Jqgrid_columnModel jqgrid_columnModel_2 = new Jqgrid_columnModel("optflag","optflag",70,sortable,false,false,"date",true,"date",null,null,null);
			Jqgrid_columnModel jqgrid_columnModel_3 = new Jqgrid_columnModel("opttime","opttime",120,sortable,false,false,"date",true,"date",null,null,null);
			Jqgrid_columnModel jqgrid_columnModel_4 = new Jqgrid_columnModel("optuser","optuser",70,sortable,false,false,"",false,"text",null,null,null);
			Jqgrid_columnModel jqgrid_columnModel_5 = new Jqgrid_columnModel("optmsg","optmsg",200,sortable,false,false,"",false,"text",null,null,null);
			Jqgrid_columnModel jqgrid_columnModel_6 = new Jqgrid_columnModel("opt","opt",70,sortable,false,false,"",false,"text",null,null,null);
			Jqgrid_columnModel jqgrid_columnModel_7 = new Jqgrid_columnModel("opttype","opttype",100,sortable,false,false,"",false,"text",null,null,null);
			columnModelList.add(jqgrid_columnModel_1);
			columnModelList.add(jqgrid_columnModel_2);
			columnModelList.add(jqgrid_columnModel_3);
			columnModelList.add(jqgrid_columnModel_4);
			columnModelList.add(jqgrid_columnModel_6);
			columnModelList.add(jqgrid_columnModel_7);
			columnModelList.add(jqgrid_columnModel_5);
			gridWidth+=100+100+150+100+200+100+100;
			for(RuntimeColumnInfo runtimeColumnInfo : list){
				String name = runtimeColumnInfo.getSourceColumn().toLowerCase();
				String index = name;
				int width = 200;
				boolean editable = false;
				String edittype="";
				boolean search = (runtimeColumnInfo.getSearchable()==0?false:true);
				String searchtype = runtimeColumnInfo.getSearchtype();
				Jqgrid_searchoptions searchoptions = new Jqgrid_searchoptions();
				if("DATE".equalsIgnoreCase(searchtype)){
					String[] sopt ={"ge","le"};
					edittype="date";
					searchoptions = new Jqgrid_searchoptions(sopt);
				}
				Jqgrid_columnModel jqgrid_columnModel = new Jqgrid_columnModel(name,index,width,sortable,false,editable,edittype,search,searchtype,searchoptions,null,null);
				columnModelList.add(jqgrid_columnModel);
				String name2 = runtimeColumnInfo.getSourceColumn().toLowerCase()+"_old";
				String index2 = name2;
				int width2 = 200;
				boolean search2 = false;
				String searchtype2 = "text";
				boolean editable2 = false;
				Jqgrid_searchoptions searchoptions2 = null;
				Jqgrid_columnModel jqgrid_columnModel2 = new Jqgrid_columnModel(name2,index2,width2,sortable,false,editable2,"",search2,searchtype2,searchoptions2,null,null);
				columnModelList.add(jqgrid_columnModel2);
				gridWidth+=200+200;
			}
		}
		return columnModelList;
	}
}
