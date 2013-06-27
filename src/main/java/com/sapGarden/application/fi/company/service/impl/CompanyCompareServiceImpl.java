package com.sapGarden.application.fi.company.service.impl;

import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.fi.company.model.Company;
import com.sapGarden.application.fi.company.model.CompanyCompareDataModel;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getDetail.Export_companyCode_detail;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getDetail.Export_return;
import com.sapGarden.application.fi.company.service.CompanyService;
import com.sapGarden.application.jco.commons.model.CommonCompareDataModel;
import com.sapGarden.application.jco.commons.model.JcoReturnModel;
import com.sapGarden.application.jco.commons.service.CommonRfcService;
import com.sapGarden.application.jco.commons.service.CompareData_CommonService;
import com.sapGarden.global.jqgrid.model.Jqgrid_DataJson;
import com.sapGarden.global.json.JsonUtils;
@Service("bo_fin_company_compareData_service")
public class CompanyCompareServiceImpl implements CompareData_CommonService{

	private CommonRfcService commonRfcService;
	private CompanyService companyService;
	@Autowired
	@Qualifier("bo_fin_company_CommonRfcService")
	public void setCommonRfcService(CommonRfcService commonRfcService) {
		this.commonRfcService = commonRfcService;
	}
	@Autowired
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	@Override
	public JSONObject getJqgridCompareJsonData(SapDataCollection sapDataCollection,String type) throws Exception {
		// TODO Auto-generated method stub
		Jqgrid_DataJson jsonData = new Jqgrid_DataJson(0,0,0,this.getCompareData(sapDataCollection,type));
		JSONObject jsonObject = JsonUtils.objectToJSONObject(jsonData,null);
		return jsonObject;
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<CommonCompareDataModel> getCompareData(SapDataCollection sapDataCollection,String type)throws Exception {
		// TODO Auto-generated method stub
		List<CommonCompareDataModel> compareDataModelList = new LinkedList<CommonCompareDataModel>();
		try {
			List<JcoReturnModel> jcoReturnModelList = commonRfcService.callRfc(sapDataCollection,null,true);
			if(jcoReturnModelList!=null&&jcoReturnModelList.size()>0){
				List<Company> companyList = companyService.find(sapDataCollection,null, null);
				//把sap和garden中都有的公司信息加进来
				for(JcoReturnModel jcoReturnModel : jcoReturnModelList){
					Export_return export_return  = (Export_return)jcoReturnModel.getExportMap().get("RETURN");
					String returnType = export_return.getType();
					if("E".equals(returnType.toUpperCase())||"A".equals(returnType.toUpperCase())){
						CompanyCompareDataModel errorDataModel = new CompanyCompareDataModel();
						errorDataModel.setFlag(false);
						errorDataModel.setMsg(export_return.getMessage());
						compareDataModelList.add(errorDataModel);
					}
					Export_companyCode_detail detail = (Export_companyCode_detail)jcoReturnModel.getExportMap().get("COMPANYCODE_DETAIL");
					boolean flag = true;
					for(Company company : companyList){
						//如果sap与本系统的同一公司，但公司信息不一样
						if(company.getComp_code().equals(detail.getComp_code())){
							flag=false;
							if(!((company.getComp_name()==null?"":company.getComp_name()).equals((detail.getComp_name()==null?"":detail.getComp_name()))&&
									(company.getCity()==null?"":company.getCity()).equals((detail.getCity()==null?"":detail.getCity()))&&
									(company.getCountry()==null?"":company.getCountry()).equals((detail.getCountry()==null?"":detail.getCountry()))&&
									(company.getCurrency()==null?"":company.getCurrency()).equals((detail.getCurrency()==null?"":detail.getCurrency()))&&
									(company.getLangu()==null?"":company.getLangu()).equals((detail.getLangu()==null?"":detail.getLangu()))&&
									(company.getChrt_accts()==null?"":company.getChrt_accts()).equals((detail.getChrt_accts()==null?"":detail.getChrt_accts()))&&
									(company.getFy_variant()==null?"":company.getFy_variant()).equals((detail.getFy_variant()==null?"":detail.getFy_variant()))&&
									(company.getVat_reg_no()==null?"":company.getVat_reg_no()).equals((detail.getVat_reg_no()==null?"":detail.getVat_reg_no()))&&
									(company.getAddr_no()==null?"":company.getAddr_no()).equals((detail.getAddr_no()==null?"":detail.getAddr_no()))&&
									(company.getCompany()==null?"":company.getCompany()).equals((detail.getCompany()==null?"":detail.getCompany())))){
								CompanyCompareDataModel compareDataModel = new CompanyCompareDataModel(company,detail);
								compareDataModelList.add(compareDataModel);
							}
							companyList.remove(company);
							break;
						}
					}
					//如果flag为true,说明sap中有的公司，本系统没有
					if(flag){
						CompanyCompareDataModel compareDataModel = new CompanyCompareDataModel(null,detail);
						compareDataModelList.add(compareDataModel);
					}
					
				}
				//把sap中没有，garden中有的公司添加进来
				for(Company company : companyList){
					compareDataModelList.add(new CompanyCompareDataModel(company,null));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return compareDataModelList;
	}
}
