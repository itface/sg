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
@Service("bo_fin_company_constructCompareData_JqgridConfigModel")
public class ConstructCompareDataJqgridConfigModelServiceImpl implements ConstructJqgridConfigModelService{

	private final String id = "compareDataGrid";
	private final int height=0;
	private final int width =0;
	private final String caption="校对结果(S:代表SAP系统,G:代表本系统)";
	private final boolean multiselect = true;
	private final boolean searchable=false;
	private final boolean editable=false;
	private final String baseUrl="";
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	@Autowired
	public void setRuntime_ColumnInfo_Service(Runtime_ColumnInfo_Service runtime_ColumnInfo_Service) {
		this.runtime_ColumnInfo_Service = runtime_ColumnInfo_Service;
	}


	@Override
	public String construct(SapDataCollection sapDataCollection,String businesstype)  {
		// TODO Auto-generated method stub
		List<RuntimeColumnInfo> list = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection, businesstype);
		Jqgrid_ConfigModel jqgrid_ConfigModel = new Jqgrid_ConfigModel("",id,height,width,caption,baseUrl,multiselect,searchable,editable,this.costructColumnNames(list),this.constructColumnModel(list));
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
				if("DATE".equalsIgnoreCase(searchtype)){
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
		/*
		List<Jqgrid_columnModel> columnModelList = new LinkedList<Jqgrid_columnModel>();
		String[] columnIdss = {"dataid","COMP_CODE_S","COMP_CODE_G","COMP_NAME_S","COMP_NAME_G","CITY_S","CITY_G","COUNTRY_S","COUNTRY_G","CURRENCY_S","CURRENCY_G","LANGU_S","LANGU_G","CHRT_ACCTS_S","CHRT_ACCTS_G","FY_VARIANT_S","FY_VARIANT_G","VAT_REG_NO_S","VAT_REG_NO_G","COMPANY_S","COMPANY_G","ADDR_NO_S","ADDR_NO_G"};
		for(int i=0;i<columnIdss.length;i++){
			String name = columnIdss[i].toLowerCase();
			String index = name;
			int width = 200;
			boolean editable = false;
			boolean search = false;
			String searchtype = "text";
			boolean hidden = false;
			if(name.equals("dataid")){
				hidden=true;
			}
			Jqgrid_searchoptions searchoptions = new Jqgrid_searchoptions();
			Jqgrid_columnModel jqgrid_columnModel = new Jqgrid_columnModel(name,index,width,true,hidden,editable,"",search,searchtype,searchoptions,null,null);
			columnModelList.add(jqgrid_columnModel);
		}
		return columnModelList;
		*/
	}

	
}
