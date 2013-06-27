package com.sapGarden.application.fi.company.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.fi.company.model.Company;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getDetail.Export_companyCode_detail;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getDetail.Export_return;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getList.TableExp_companycode_list;
import com.sapGarden.application.jco.commons.exception.JcoException;
import com.sapGarden.application.jco.commons.model.CommonSynModel;
import com.sapGarden.application.jco.commons.model.ImportModel;
import com.sapGarden.application.jco.commons.model.JcoModel;
import com.sapGarden.application.jco.commons.model.JcoReturnModel;
import com.sapGarden.application.jco.commons.service.ConstructJcoModelService;
import com.sapGarden.application.jco.commons.service.JcoService;
import com.sapGarden.application.jco.commons.service.impl.AbstractRfcService;
@Service("bo_fin_company_CommonRfcService")
public class CompanyRfcServiceImpl extends AbstractRfcService{

	private final String type = "bo_fin_company";
	private ConstructJcoModelService constructGetListJcoModelService;
	private ConstructJcoModelService ConstructGetDetailJcoModelService;
	private JcoService jcoService;
	@Autowired
	public void setJcoService(JcoService jcoService) {
		this.jcoService = jcoService;
	}
	@Autowired
	@Qualifier("bo_fin_company_getList_constructJcoModel_service")
	public void setConstructGetListJcoModelService(ConstructJcoModelService constructGetListJcoModelService) {
		this.constructGetListJcoModelService = constructGetListJcoModelService;
	}
	@Autowired
	@Qualifier("bo_fin_company_getDetail_constructJcoModel_service")
	public void setConstructGetDetailJcoModelService(ConstructJcoModelService constructGetDetailJcoModelService) {
		ConstructGetDetailJcoModelService = constructGetDetailJcoModelService;
	}
	@Override
	public List<JcoReturnModel> callRfc(SapDataCollection sapDataCollection,Map<String,Object> map,boolean ifFilter) throws JcoException {
		// TODO Auto-generated method stub
		List<JcoReturnModel> jcoReturnModelList = null;
		try {
			JcoModel getListJcoModel = constructGetListJcoModelService.construct(sapDataCollection,null, null);
			JcoReturnModel comanyListJcoReturnModel = jcoService.callFunction(getListJcoModel);
			if(comanyListJcoReturnModel!=null){
				List<TableExp_companycode_list> list = (LinkedList<TableExp_companycode_list>)comanyListJcoReturnModel.getTableMap().get("COMPANYCODE_LIST");
				if(list!=null&&list.size()>0){
					jcoReturnModelList = new LinkedList<JcoReturnModel>();
					for(TableExp_companycode_list t : list){
						String companycode = t.getComp_code();
						List<ImportModel> importModelList = new LinkedList<ImportModel>();
						ImportModel importModel = new ImportModel();
						importModel.setPropertyName("COMPANYCODEID");
						importModel.setPropertyData(companycode);
						importModelList.add(importModel);
						JcoModel getDetailJcoModel = ConstructGetDetailJcoModelService.construct(sapDataCollection,importModelList, null);
						JcoReturnModel jcoReturnModel = jcoService.callFunction(getDetailJcoModel);
						if(ifFilter){
							Export_companyCode_detail detail = (Export_companyCode_detail)jcoReturnModel.getExportMap().get("COMPANYCODE_DETAIL");
							Export_companyCode_detail o = (Export_companyCode_detail)super.ifSyn(sapDataCollection,type, detail);
							(jcoReturnModel.getExportMap()).put("COMPANYCODE_DETAIL", o);
						}
						jcoReturnModelList.add(jcoReturnModel);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new JcoException(e);
		}
		return jcoReturnModelList;
	}
	@Override
	public List<CommonSynModel> getData(SapDataCollection sapDataCollection,Map<String, Object> map,boolean ifFilter)throws JcoException {
		// TODO Auto-generated method stub
		try {
			List<JcoReturnModel> jcoReturnModelList = this.callRfc(sapDataCollection,map,ifFilter);
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
						daoModel.setData(new Company(detail));
					}
					list.add(daoModel);
				}
				return list;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new JcoException(e);
		}
		return null;
	}

}
