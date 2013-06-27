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
@Service("bo_fin_company_constructData_JqgridConfigModel")
public class ConstructDataJqgridConfigModelServiceImpl implements ConstructJqgridConfigModelService{

	private final String id = "dataGrid";
	private final int height=0;
	private final int width =0;
	private final String caption="公司信息";
	private final boolean multiselect = false;
	private final boolean searchable=true;
	private final boolean editable=false;
	private final String baseUrl="/application/synConfig/bo_fin_company/runtimeData/data";
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
		/*
		String[] columnNames = {"公司代码","公司名称","城市","国家","货币","语言","帐目表","会计年度变式","增值税登记号","公司","地址号码"};
		return columnNames;
		*/
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
				if("DATE".equalsIgnoreCase(searchtype)){
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
		/*
		List<Jqgrid_columnModel> columnModelList = new LinkedList<Jqgrid_columnModel>();
		String[] columnIdss = {"COMP_CODE","COMP_NAME","CITY","COUNTRY","CURRENCY","LANGU","CHRT_ACCTS","FY_VARIANT","VAT_REG_NO","COMPANY","ADDR_NO"};
		for(int i=0;i<columnIdss.length;i++){
			String name = columnIdss[i].toLowerCase();
			String index = name;
			int width = 200;
			boolean editable = false;
			boolean search = false;
			String searchtype = "text";
			Jqgrid_searchoptions searchoptions = new Jqgrid_searchoptions();
			if(name.equals("comp_code")){
				String[] sopt = {"eq"};
				search=true;
				searchoptions = new Jqgrid_searchoptions(sopt);
			}else if(name.equals("comp_name")){
				String[] sopt = {"eq"};
				search=true;
				searchoptions = new Jqgrid_searchoptions(sopt);
			}
			Jqgrid_columnModel jqgrid_columnModel = new Jqgrid_columnModel(name,index,width,true,false,editable,"",search,searchtype,searchoptions,null,null);
			columnModelList.add(jqgrid_columnModel);
		}
		return columnModelList;
		*/
	}
}
