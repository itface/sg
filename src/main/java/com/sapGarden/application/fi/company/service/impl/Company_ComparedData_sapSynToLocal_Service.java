package com.sapGarden.application.fi.company.service.impl;

import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.constants.LogConstants;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.fi.company.model.Company;
import com.sapGarden.application.fi.company.model.CompanyCompareDataModel;
import com.sapGarden.application.jco.commons.model.CommonCompareDataModel;
import com.sapGarden.application.jco.commons.service.CommonDaoService;
import com.sapGarden.application.jco.commons.service.CompareData_SapSynToLocal_CommonService;
import com.sapGarden.global.json.JsonUtils;
@Service("bo_fin_company_comparedData_sapSynToLocal_service")
public class Company_ComparedData_sapSynToLocal_Service implements CompareData_SapSynToLocal_CommonService{

	private final String type = "bo_fin_company";
	private CommonDaoService commonDaoService;
	@Autowired
	@Qualifier("commonDaoService")
	public void setCommonDaoService(CommonDaoService commonDaoService) {
		this.commonDaoService = commonDaoService;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void sapSynToLocal(SapDataCollection sapDataCollection,List<CommonCompareDataModel> list,String type,String opttype,String user,boolean logException)throws Exception {
		// TODO Auto-generated method stub
		try {
			if(list!=null&&list.size()>0){
				for(CommonCompareDataModel commonCompareDataModel : list){
					CompanyCompareDataModel compareDataModel = (CompanyCompareDataModel)commonCompareDataModel;
					String comp_code_s = compareDataModel.getComp_code_s();
					String comp_code_g = compareDataModel.getComp_code_g();
					//如果sap公司代码为空，则把这条记录从本系统中删除
					if(comp_code_s==null||"".equals(comp_code_s.trim())){
						//companyDao.deleteByComp_code(comp_code_g);
						commonDaoService.removeOneById(logException,compareDataModel.getDataid(), type, opttype,user);
					}else if(comp_code_g==null||"".equals(comp_code_g.trim())){
						//如果sap公司代码不为空，本系统的sap代码为空，则把sap的公司代码同步到本地
						Company company = new Company(comp_code_s,compareDataModel.getComp_name_s(),compareDataModel.getCity_s(),compareDataModel.getCountry_s(),compareDataModel.getCurrency_s(),compareDataModel.getLangu_s(),compareDataModel.getChrt_accts_s(),compareDataModel.getFy_variant_s(),compareDataModel.getVat_reg_no_s(),compareDataModel.getCompany_s(),compareDataModel.getAddr_no_s(),sapDataCollection.getId());
						//companyDao.save(company);
						commonDaoService.addOne(logException,company, type,opttype,user);
					}else{
						//如果sap公司代码不为空，本系统的sap代码也不为空，则把本系统中的该公司记录删除，同时把sap的公司代码同步到本地						
						Company company = new Company(comp_code_s,compareDataModel.getComp_name_s(),compareDataModel.getCity_s(),compareDataModel.getCountry_s(),compareDataModel.getCurrency_s(),compareDataModel.getLangu_s(),compareDataModel.getChrt_accts_s(),compareDataModel.getFy_variant_s(),compareDataModel.getVat_reg_no_s(),compareDataModel.getCompany_s(),compareDataModel.getAddr_no_s(),sapDataCollection.getId());
						company.setId(compareDataModel.getDataid());
						commonDaoService.updateOne(logException, company,compareDataModel,type,opttype, user);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void jqgridDataSapSynToLocal(SapDataCollection sapDataCollection,String list,String user) throws Exception {
		// TODO Auto-generated method stub
		if(list!=null&&!"".equals(list.trim())){
			JSONObject json = JsonUtils.objectToJSONObject(list,null);
			if(json.containsKey("data")){
				List dataList = (List)json.get("data");
				if(dataList!=null&&dataList.size()>0){
					List<CommonCompareDataModel> commonCompareDataModelList = new LinkedList<CommonCompareDataModel>();
					for(int i=0;i<dataList.size();i++){
						JSONObject jj = JSONObject.fromObject(dataList.get(i));
						CompanyCompareDataModel compareDataModel = (CompanyCompareDataModel)JSONObject.toBean(jj, CompanyCompareDataModel.class);
						commonCompareDataModelList.add(compareDataModel);
					}
					this.sapSynToLocal(sapDataCollection,commonCompareDataModelList, type, LogConstants.OPTTYPE_COMPAREDATA, user,true);
				}
			}
		}
	}
	
}
