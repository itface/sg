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
import com.sapGarden.application.commons.jco.model.JcoReturnModel;
import com.sapGarden.application.fi.company.model.Company;
import com.sapGarden.application.fi.company.model.CompanyLog;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getDetail.Export_companyCode_detail;
import com.sapGarden.application.fi.company.service.CompanyService;
import com.sapGarden.application.fi.company.service.GetSapDataService;
import com.sapGarden.application.fi.company.service.ManualSynService;
import com.sapGarden.global.jqgrid.model.Jqgrid_DataJson;
import com.sapGarden.global.json.JsonUtils;
@Service("company_ManualSynService")
public class ManualSynServiceImpl implements ManualSynService{

	@Autowired
	@Qualifier("company_GetSapDataService")
	private GetSapDataService getSapDataService;
	@Autowired
	private CompanyService companyService;
	@Override
	public JSONObject testCall(SapDataCollection sapDataCollection) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		// TODO Auto-generated method stub
		List<JcoReturnModel> jcoReturnModelList = getSapDataService.callRfc(sapDataCollection);
		if(jcoReturnModelList!=null&&jcoReturnModelList.size()>0){
			List<Export_companyCode_detail> detailList = new LinkedList<Export_companyCode_detail>();
			for(JcoReturnModel jcoReturnModel : jcoReturnModelList){
				Export_companyCode_detail detail = (Export_companyCode_detail)jcoReturnModel.getExportMap().get("COMPANYCODE_DETAIL");
				detailList.add(detail);
			}
			Jqgrid_DataJson jsonData = new Jqgrid_DataJson(0,0,0,detailList);
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			return jsonObject;
		}
		return null;
	}

	@Override
	@Transactional
	public void saveManuSynDataOfJqgridToLocal(SapDataCollection sapDataCollection, String list, String user,String opttype,boolean ifLog){
		if(list!=null&&!"".equals(list.trim())){
			JSONObject json = JsonUtils.objectToJSONObject(list,null);
			if(json.containsKey("data")){
				List dataList = (List)json.get("data");
				if(dataList!=null&&dataList.size()>0){
					for(int i=0;i<dataList.size();i++){
						JSONObject jj = JSONObject.fromObject(dataList.get(i));
						Company newComp = (Company)JSONObject.toBean(jj, Company.class);
						Company oldComp = companyService.findByComp_code(sapDataCollection, newComp.getComp_code());
						companyService.addWithLog(opttype, sapDataCollection.getId(), user, Company.class, CompanyLog.class, newComp);
						companyService.deleteWithLog(opttype, sapDataCollection.getId(), user, Company.class, CompanyLog.class, oldComp);
					}
				}
			}
		}
	}
}
