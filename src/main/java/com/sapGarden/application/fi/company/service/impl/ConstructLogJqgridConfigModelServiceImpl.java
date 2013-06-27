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
@Service("bo_fin_company_constructLog_JqgridConfigModel")
public class ConstructLogJqgridConfigModelServiceImpl implements ConstructJqgridConfigModelService{

	private final String id = "logGrid";
	private final int height=0;
	private final int width =0;
	private final String caption="日志信息";
	private final boolean multiselect = false;
	private final boolean searchable=true;
	private final boolean editable=false;
	private final String baseUrl="/application/synConfig/bo_fin_company/log/data";
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
		//String[] columnNames = {"操作时间","操作人","操作","操作类型","公司代码","公司代码(原)","公司名称","公司名称(原)","城市","城市(原)","国家","国家(原)","货币","货币(原)","语言","语言(原)","帐目表","帐目表(原)","会计年度变式","会计年度变式(原)","增值税登记号","增值税登记号(原)","公司","公司(原)","地址号码","地址号码(原)"};
		String[] columnNames = null;
		if(list!=null&&list.size()>0){
			columnNames = new String[list.size()*2+4];
			columnNames[0]="操作时间";
			columnNames[1]="操作人";
			columnNames[2]="操作";
			columnNames[3]="操作类型";
			int count=4;
			for(RuntimeColumnInfo runtimeColumnInfo : list){
				columnNames[count++]=runtimeColumnInfo.getSourceColumnName();
				columnNames[count++]=runtimeColumnInfo.getSourceColumnName()+"(原)";
			}
		}
		return columnNames;
	}
	private List<Jqgrid_columnModel> constructColumnModel(List<RuntimeColumnInfo> list){
		List<Jqgrid_columnModel> columnModelList = null;
		if(list!=null&&list.size()>0){
			columnModelList=new LinkedList<Jqgrid_columnModel>();
			Jqgrid_columnModel jqgrid_columnModel_1 = new Jqgrid_columnModel("opttime","opttime",100,true,false,false,"date",true,"date",new Jqgrid_searchoptions(new String[]{"ge","le"}),null,null);
			Jqgrid_columnModel jqgrid_columnModel_2 = new Jqgrid_columnModel("optuser","optuser",100,true,false,false,"",false,"text",null,null,null);
			Jqgrid_columnModel jqgrid_columnModel_3 = new Jqgrid_columnModel("opt","opt",100,true,false,false,"",false,"text",null,null,null);
			Jqgrid_columnModel jqgrid_columnModel_4 = new Jqgrid_columnModel("opttype","opttype",100,true,false,false,"",false,"text",null,null,null);
			columnModelList.add(jqgrid_columnModel_1);
			columnModelList.add(jqgrid_columnModel_2);
			columnModelList.add(jqgrid_columnModel_3);
			columnModelList.add(jqgrid_columnModel_4);
			for(RuntimeColumnInfo runtimeColumnInfo : list){
				String name = runtimeColumnInfo.getSourceColumn().toLowerCase();
				String index = name;
				int width = 100;
				boolean editable = false;
				boolean search = (runtimeColumnInfo.getSearchable()==0?false:true);
				String searchtype = runtimeColumnInfo.getSearchtype();
				Jqgrid_searchoptions searchoptions = new Jqgrid_searchoptions();
				String edittype="";
				if("DATE".equalsIgnoreCase(searchtype)){
					String[] sopt ={"ge","le"};
					edittype="date";
					searchoptions = new Jqgrid_searchoptions(sopt);
				}
				Jqgrid_columnModel jqgrid_columnModel = new Jqgrid_columnModel(name,index,width,true,false,editable,edittype,search,searchtype,searchoptions,null,null);
				columnModelList.add(jqgrid_columnModel);
				String name2 = runtimeColumnInfo.getSourceColumn().toLowerCase()+"_old";
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
		/*
		List<Jqgrid_columnModel> columnModelList = new LinkedList<Jqgrid_columnModel>();
		String[] columnIdss = {"OPTTIME","OPTUSER","OPT","OPTTYPE","COMP_CODE","COMP_CODE_OLD","COMP_NAME","COMP_NAME_OLD","CITY","CITY_OLD","COUNTRY","COUNTRY_OLD","CURRENCY","CURRENCY_OLD","LANGU","LANGU_OLD","CHRT_ACCTS","CHRT_ACCTS_OLD","FY_VARIANT","FY_VARIANT_OLD","VAT_REG_NO","VAT_REG_NO_OLD","COMPANY","COMPANY_OLD","ADDR_NO","ADDR_NO_OLD"};
		for(int i=0;i<columnIdss.length;i++){
			String name = columnIdss[i].toLowerCase();
			String index = name;
			int width = 200;
			boolean editable = false;
			boolean search = false;
			String searchtype = "text";
			String edittype="";
			Jqgrid_searchoptions searchoptions = new Jqgrid_searchoptions();
			if(name.equals("opttime")){
				String[] sopt = {"ge","le"};
				edittype="Date";
				search=true;
				searchoptions = new Jqgrid_searchoptions(sopt);
			}else if(name.equals("comp_code")){
				String[] sopt = {"eq"};
				search=true;
				searchoptions = new Jqgrid_searchoptions(sopt);
			}else if(name.equals("comp_name")){
				String[] sopt = {"eq"};
				search=true;
				searchoptions = new Jqgrid_searchoptions(sopt);
			}
			Jqgrid_columnModel jqgrid_columnModel = new Jqgrid_columnModel(name,index,width,true,false,editable,edittype,search,searchtype,searchoptions,null,null);
			columnModelList.add(jqgrid_columnModel);
		}
		return columnModelList;
		*/
	}

	

}
