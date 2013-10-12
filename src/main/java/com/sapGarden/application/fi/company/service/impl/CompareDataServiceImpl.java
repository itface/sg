package com.sapGarden.application.fi.company.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.jco.model.CommonCompareDataModel;
import com.sapGarden.application.commons.jco.model.JcoReturnModel;
import com.sapGarden.application.commons.log.model.CommonLogModel;
import com.sapGarden.application.fi.company.model.Company;
import com.sapGarden.application.fi.company.model.CompanyCompareDataModel;
import com.sapGarden.application.fi.company.model.CompanyLog;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getDetail.Export_companyCode_detail;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getDetail.Export_return;
import com.sapGarden.application.fi.company.service.CompanyService;
import com.sapGarden.application.fi.company.service.CompareDataService;
import com.sapGarden.application.fi.company.service.GetSapDataService;
import com.sapGarden.global.jqgrid.model.Jqgrid_DataJson;
import com.sapGarden.global.json.JsonUtils;
@Service("company_CompareDataService")
public class CompareDataServiceImpl implements CompareDataService{

	@Autowired
	@Qualifier("company_GetSapDataService")
	private GetSapDataService getSapDataService;
	@Autowired
	private CompanyService companyService;
	private int totalSapNum;
	private int totalGardenNum;
	private int onlySapNum;
	private int onlyGardenNum;
	private int differenceNum;
	private int sameNum;
	@Override
	public JSONObject getJqgridCompareJsonData(SapDataCollection sapDataCollection) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{
		// TODO Auto-generated method stub
		//初始化
		totalSapNum=0;
		totalGardenNum=0;
		onlySapNum=0;
		onlyGardenNum=0;
		differenceNum=0;
		sameNum=0;
		Jqgrid_DataJson jsonData = new Jqgrid_DataJson(0,0,0,this.getCompareData(sapDataCollection));
		//JSONObject jsonObject = JsonUtils.objectToJSONObject(jsonData,null);
		//String s = "{totalSapNum:"+totalSapNum+",totalGardenNum:"+totalGardenNum+",onlySapNum:"+onlySapNum+",onlyGardenNum:"+onlyGardenNum+",differenceNum:"+differenceNum+",sameNum:"+sameNum+"}";
		JSONObject json = JsonUtils.objectToJSONObject(jsonData,null);
		json.put("totalSapNum", totalSapNum);
		json.put("totalGardenNum", totalGardenNum);
		json.put("onlySapNum", onlySapNum);
		json.put("onlyGardenNum", onlyGardenNum);
		json.put("differenceNum", differenceNum);
		json.put("sameNum", sameNum);
		return json;
	}
	@Override
	public List<CommonCompareDataModel> getCompareData(SapDataCollection sapDataCollection) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{
		// TODO Auto-generated method stub
		List<CommonCompareDataModel> compareDataModelList = new LinkedList<CommonCompareDataModel>();
			List<JcoReturnModel> jcoReturnModelList = getSapDataService.callRfc(sapDataCollection);
			if(jcoReturnModelList!=null&&jcoReturnModelList.size()>0){
				totalSapNum=jcoReturnModelList.size();
				List<Company> companyList = companyService.find(sapDataCollection);
				totalGardenNum=(companyList==null?0:companyList.size());
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
					if(companyList!=null){
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
									differenceNum++;
								}else{
									sameNum++;
								}
								companyList.remove(company);
								break;
							}
						}
					}
					//如果flag为true,说明sap中有的公司，本系统没有
					if(flag){
						CompanyCompareDataModel compareDataModel = new CompanyCompareDataModel(null,detail);
						compareDataModelList.add(compareDataModel);
						onlySapNum++;
					}
					
				}
				//把sap中没有，garden中有的公司添加进来
				if(companyList!=null){
					for(Company company : companyList){
						compareDataModelList.add(new CompanyCompareDataModel(company,null));
						onlyGardenNum++;
					}
				}
			}
		return compareDataModelList;
	}
	@Override
	@Transactional
	public void saveCompareDataList(boolean ifLog,long sapclient,String opttype,String user,List<CommonCompareDataModel> list) {
		// TODO Auto-generated method stub
		if(list!=null&&list.size()>0){
			for(CommonCompareDataModel commonCompareDataModel : list){
				CompanyCompareDataModel compareDataModel = (CompanyCompareDataModel)commonCompareDataModel;
				String comp_code_s = compareDataModel.getComp_code_s();
				String comp_code_g = compareDataModel.getComp_code_g();
				//如果sap公司代码为空，则把这条记录从本系统中删除
				if(comp_code_s==null||"".equals(comp_code_s.trim())){
					if(ifLog){
						Company company = new Company(comp_code_g,compareDataModel.getComp_name_g(),compareDataModel.getCity_g(),compareDataModel.getCountry_g(),compareDataModel.getCurrency_g(),compareDataModel.getLangu_g(),compareDataModel.getChrt_accts_g(),compareDataModel.getFy_variant_g(),compareDataModel.getVat_reg_no_g(),compareDataModel.getCompany_g(),compareDataModel.getAddr_no_g(),sapclient);
						company.setId(compareDataModel.getDataid());
						companyService.deleteWithLog(opttype, sapclient, user,Company.class, CompanyLog.class,company);
					}else{
						companyService.removeById(compareDataModel.getDataid());
					}
				}else if(comp_code_g==null||"".equals(comp_code_g.trim())){
					//如果sap公司代码不为空，本系统的sap代码为空，则把sap的公司代码同步到本地
					Company company = new Company(comp_code_s,compareDataModel.getComp_name_s(),compareDataModel.getCity_s(),compareDataModel.getCountry_s(),compareDataModel.getCurrency_s(),compareDataModel.getLangu_s(),compareDataModel.getChrt_accts_s(),compareDataModel.getFy_variant_s(),compareDataModel.getVat_reg_no_s(),compareDataModel.getCompany_s(),compareDataModel.getAddr_no_s(),sapclient);
					if(ifLog){
						companyService.addWithLog(opttype, sapclient, user, Company.class, CompanyLog.class, company);
					}else{
						companyService.add(company);
					}
				}else{
					//如果sap公司代码不为空，本系统的sap代码也不为空，则把本系统中的该公司记录删除，同时把sap的公司代码同步到本地						
					Company scompany = new Company(comp_code_s,compareDataModel.getComp_name_s(),compareDataModel.getCity_s(),compareDataModel.getCountry_s(),compareDataModel.getCurrency_s(),compareDataModel.getLangu_s(),compareDataModel.getChrt_accts_s(),compareDataModel.getFy_variant_s(),compareDataModel.getVat_reg_no_s(),compareDataModel.getCompany_s(),compareDataModel.getAddr_no_s(),sapclient);
					scompany.setId(compareDataModel.getDataid());
					if(ifLog){
						Company gcompany = new Company(comp_code_g,compareDataModel.getComp_name_g(),compareDataModel.getCity_g(),compareDataModel.getCountry_g(),compareDataModel.getCurrency_g(),compareDataModel.getLangu_g(),compareDataModel.getChrt_accts_g(),compareDataModel.getFy_variant_g(),compareDataModel.getVat_reg_no_g(),compareDataModel.getCompany_g(),compareDataModel.getAddr_no_g(),sapclient);
						gcompany.setId(compareDataModel.getDataid());
						companyService.updateWithLog(opttype, sapclient, user, Company.class, CompanyLog.class,scompany,gcompany);
					}else{
						companyService.update(scompany);
					}
				}
			}
		}
	}
	@Override
	@Transactional
	public void saveComparedDataOfJqgridToLocal(SapDataCollection sapDataCollection, String list, String user,String opttype,boolean ifLog) {
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
					this.saveCompareDataList(ifLog, sapDataCollection.getId(), opttype, user, commonCompareDataModelList);
				}
			}
		}
	}
}
