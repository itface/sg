package com.sapGarden.application.synConfig.service;

import net.sf.json.JSONObject;

import com.sapGarden.application.synConfig.model.JqgridNewCallFunParamInfo;
import com.sapGarden.global.exception.JpaException;

public interface CallFun_ParamInfo_Service {

	public JSONObject findJsonByBusinesstype(String businesstype)throws JpaException;
	public void addOne(JqgridNewCallFunParamInfo jqgridNewCallFunParamInfo)throws JpaException;
	public void updateOne(JqgridNewCallFunParamInfo jqgridNewCallFunParamInfo)throws JpaException;
	public void deleteOne(long id)throws JpaException;
}
