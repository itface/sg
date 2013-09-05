package com.sapGarden.application.fi.company.service.impl.bapi_companyCode_getDetail;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.jco.model.ExportModel;
import com.sapGarden.application.commons.jco.model.ImportModel;
import com.sapGarden.application.commons.jco.model.JcoModel;
import com.sapGarden.application.commons.jco.model.TableImpModel;
import com.sapGarden.application.commons.jco.service.ConstructJcoModelService;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getDetail.Export_companyCode_detail;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getDetail.Export_return;

@Service("bo_fin_company_getDetail_constructJcoModel_service")
public class ConstructJcoModelServiceImpl implements ConstructJcoModelService{

	private final String RFCNAME = "BAPI_COMPANYCODE_GETDETAIL";
	private final String IMP_COMPANYCODEID_NAME = "COMPANYCODEID";
	private final Class EXP_COMPANYCODE_DETAIL_CLASS = Export_companyCode_detail.class;
	private final String EXP_COMPANYCODE_DETAIL_NAME = "COMPANYCODE_DETAIL";
	private final Class EXP_RETURN_CLASS = Export_return.class;
	private final String EXP_RETURN_NAME = "RETURN";
	@Override
	public JcoModel construct(SapDataCollection sapDataCollection,List<ImportModel> importModelList,List<TableImpModel> tableImpModelList) {
		// TODO Auto-generated method stub
		return new JcoModel(sapDataCollection,RFCNAME,null,null,importModelList,this.constructExportList());
	}
	private List<ExportModel> constructExportList(){
		List<ExportModel> list = new LinkedList<ExportModel>();
		ExportModel exportModel = new ExportModel();
		exportModel.setPropertyClass(EXP_COMPANYCODE_DETAIL_CLASS);
		exportModel.setPropertyName(EXP_COMPANYCODE_DETAIL_NAME);
		list.add(exportModel);
		ExportModel exportModel2 = new ExportModel();
		exportModel2.setPropertyClass(EXP_RETURN_CLASS);
		exportModel2.setPropertyName(EXP_RETURN_NAME);
		list.add(exportModel2);
		return list;
	}
}
