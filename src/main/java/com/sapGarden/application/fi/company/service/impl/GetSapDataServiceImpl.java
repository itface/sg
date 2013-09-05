package com.sapGarden.application.fi.company.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.constants.SjlxTypeName;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.jco.model.CommonExportModel;
import com.sapGarden.application.commons.jco.model.CommonSynModel;
import com.sapGarden.application.commons.jco.model.ImportModel;
import com.sapGarden.application.commons.jco.model.JcoModel;
import com.sapGarden.application.commons.jco.model.JcoReturnModel;
import com.sapGarden.application.commons.jco.service.ConstructJcoModelService;
import com.sapGarden.application.commons.jco.service.JcoService;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.service.Runtime_ColumnInfo_Service;
import com.sapGarden.application.fi.company.model.Company;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getDetail.Export_companyCode_detail;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getDetail.Export_return;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getList.TableExp_companycode_list;
import com.sapGarden.application.fi.company.service.GetSapDataService;
@Service("company_GetSapDataService")
public class GetSapDataServiceImpl implements GetSapDataService{


	@Autowired
	@Qualifier("bo_fin_company_getList_constructJcoModel_service")
	private ConstructJcoModelService constructGetListJcoModelService;
	@Autowired
	@Qualifier("bo_fin_company_getDetail_constructJcoModel_service")
	private ConstructJcoModelService ConstructGetDetailJcoModelService;
	@Autowired
	private JcoService jcoService;
	@Autowired
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	private List<RuntimeColumnInfo> runtimeColumnInfoList = null;
	
	@Override
	public List<JcoReturnModel> callRfc(SapDataCollection sapDataCollection) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{
		// TODO Auto-generated method stub
		List<JcoReturnModel> jcoReturnModelList = null;
		JcoModel getListJcoModel = constructGetListJcoModelService.construct(sapDataCollection,null, null);
		JcoReturnModel comanyListJcoReturnModel = jcoService.callFunction(getListJcoModel);
		if(comanyListJcoReturnModel!=null){
			List<TableExp_companycode_list> list = (LinkedList<TableExp_companycode_list>)comanyListJcoReturnModel.getTableMap().get("COMPANYCODE_LIST");
			if(list!=null&&list.size()>0){
				jcoReturnModelList = new LinkedList<JcoReturnModel>();
				boolean initialFlag = false;
				for(TableExp_companycode_list t : list){
					String companycode = t.getComp_code();
					List<ImportModel> importModelList = new LinkedList<ImportModel>();
					ImportModel importModel = new ImportModel();
					importModel.setPropertyName("COMPANYCODEID");
					importModel.setPropertyData(companycode);
					importModelList.add(importModel);
					JcoModel getDetailJcoModel = ConstructGetDetailJcoModelService.construct(sapDataCollection,importModelList, null);
					JcoReturnModel jcoReturnModel = jcoService.callFunction(getDetailJcoModel);
					Export_companyCode_detail detail = (Export_companyCode_detail)jcoReturnModel.getExportMap().get("COMPANYCODE_DETAIL");
					Export_companyCode_detail o = (Export_companyCode_detail)this.ifSyn(initialFlag,sapDataCollection,SjlxTypeName.TYPE_COMPANY,detail);
					(jcoReturnModel.getExportMap()).put("COMPANYCODE_DETAIL", o);
					initialFlag=true;
					jcoReturnModelList.add(jcoReturnModel);
				}
			}
		}
		return jcoReturnModelList;
	}
	@Override
	public List<CommonSynModel> getData(SapDataCollection sapDataCollection) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{
		// TODO Auto-generated method stub
		List<JcoReturnModel> jcoReturnModelList = this.callRfc(sapDataCollection);
		if(jcoReturnModelList!=null&&jcoReturnModelList.size()>0){
			List<CommonSynModel> list = new LinkedList<CommonSynModel>();
			for(JcoReturnModel jcoReturnModel : jcoReturnModelList){
				CommonSynModel daoModel = new CommonSynModel();
				Export_return export_return  = (Export_return)jcoReturnModel.getExportMap().get("RETURN");
				String returnType = export_return.getType();
				if(!"E".equals(returnType.toUpperCase())&&!"A".equals(returnType.toUpperCase())){
					daoModel.setFlag(true);
				}else{
					daoModel.setFlag(false);
				}
				daoModel.setMsg(export_return.getMessage());
				Object obj = jcoReturnModel.getExportMap().get("COMPANYCODE_DETAIL");;
				if(obj!=null){
					Export_companyCode_detail detail = (Export_companyCode_detail)jcoReturnModel.getExportMap().get("COMPANYCODE_DETAIL");
					daoModel.setData(new Company(detail,sapDataCollection.getId()));
				}
				list.add(daoModel);
			}
			return list;
		}
		return null;
	}

	private CommonExportModel ifSyn(boolean initialFlag,SapDataCollection sapDataCollection,String type,CommonExportModel detail) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException{
		if(!initialFlag){
			runtimeColumnInfoList = runtime_ColumnInfo_Service.findAll(sapDataCollection,type);
			initialFlag=true;
		}
		Class clazz = detail.getClass();
		CommonExportModel syn_detail = (CommonExportModel)clazz.newInstance();
		for(RuntimeColumnInfo runtimeColumnInfo : runtimeColumnInfoList){
			String status = runtimeColumnInfo.getStatus();
			String colName = runtimeColumnInfo.getSourceColumn().toLowerCase();
			if(!"INACTIVE".equals(status)){
				String getMethodName = "get"+colName.substring(0, 1).toUpperCase()+colName.substring(1);
				String setMethodName = "set"+colName.substring(0, 1).toUpperCase()+colName.substring(1);
				Method getMethod = clazz.getMethod(getMethodName, new Class[]{});
				Object o = getMethod.invoke(detail,new Object[]{});
				String colValue = null;
				if(o!=null){
					colValue=(String)o;
				}
				Method setMethod = clazz.getMethod(setMethodName, new Class[]{String.class});
				setMethod.invoke(syn_detail, new Object[]{colValue});
			}
		}
		return syn_detail;
	}
}
