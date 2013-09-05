package com.sapGarden.application.fi.company.service.impl.bapi_companyCode_getList;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.jco.model.ExportModel;
import com.sapGarden.application.commons.jco.model.ImportModel;
import com.sapGarden.application.commons.jco.model.JcoModel;
import com.sapGarden.application.commons.jco.model.TableExpModel;
import com.sapGarden.application.commons.jco.model.TableImpModel;
import com.sapGarden.application.commons.jco.service.ConstructJcoModelService;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getList.Export_return;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getList.TableExp_companycode_list;
@Service("bo_fin_company_getList_constructJcoModel_service")
public class ConstructJcoModelServiceImpl implements ConstructJcoModelService{

	private final String RFCNAME = "BAPI_COMPANYCODE_GETLIST";
	private final Class TABLE_EXP_COMPANYCODE_LIST_CLASS = TableExp_companycode_list.class;
	private final String TABLE_EXP_COMPANYCODE_LIST_NAME = "COMPANYCODE_LIST";
	private final Class EXP_RETURN_CLASS = Export_return.class;
	private final String EXP_RETURN_NAME = "RETURN";
	@Override
	public JcoModel construct(SapDataCollection sapDataCollection,List<ImportModel> importModelList,List<TableImpModel> tableImpModelList) {
		// TODO Auto-generated method stub
		return new JcoModel(sapDataCollection,RFCNAME,null,this.constructTableExpList(),null,this.constructExportList());
	}

	private List<TableExpModel> constructTableExpList(){
		List<TableExpModel> list = new LinkedList<TableExpModel>();
		TableExpModel tableExpModel = new TableExpModel();
		tableExpModel.setTableName(TABLE_EXP_COMPANYCODE_LIST_NAME);
		tableExpModel.setTableStructureClass(TABLE_EXP_COMPANYCODE_LIST_CLASS);
		list.add(tableExpModel);
		return list;
	}
	private List<ExportModel> constructExportList(){
		List<ExportModel> list = new LinkedList<ExportModel>();
		ExportModel exportModel = new ExportModel();
		exportModel.setPropertyClass(EXP_RETURN_CLASS);
		exportModel.setPropertyName(EXP_RETURN_NAME);
		list.add(exportModel);
		return list;
	}
}
