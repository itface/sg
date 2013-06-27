package com.sapGarden.application.fi.company.service.impl;

import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.fi.company.model.bapi_companyCode_getDetail.Export_companyCode_detail;
import com.sapGarden.application.jco.commons.exception.JcoException;
import com.sapGarden.application.jco.commons.model.JcoReturnModel;
import com.sapGarden.application.jco.commons.service.CallFun_TestCall_CommonService;
import com.sapGarden.application.jco.commons.service.CommonRfcService;
import com.sapGarden.global.jqgrid.model.Jqgrid_DataJson;
@Service("bo_fin_company_testCall_service")
public class Common_testCall_serviceImpl implements CallFun_TestCall_CommonService{

	private CommonRfcService commonRfcService;
	@Autowired
	@Qualifier("bo_fin_company_CommonRfcService")
	public void setCommonRfcService(CommonRfcService commonRfcService) {
		this.commonRfcService = commonRfcService;
	}
	@Override
	public JSONObject testCall(SapDataCollection sapDataCollection,String type) throws JcoException {
		// TODO Auto-generated method stub
		List<JcoReturnModel> jcoReturnModelList = commonRfcService.callRfc(sapDataCollection,null,true);
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
	

}
