package com.sapGarden.application.synConfig.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.synConfig.dao.CallFunParamInfoDao;
import com.sapGarden.application.synConfig.model.CallFunParamInfo;
import com.sapGarden.application.synConfig.model.JqgridNewCallFunParamInfo;
import com.sapGarden.application.synConfig.service.CallFun_ParamInfo_Service;
import com.sapGarden.global.exception.JpaException;
import com.sapGarden.global.jqgrid.model.Jqgrid_DataJson;
@Service
public class CallFun_ParamInfo_ServiceImpl implements CallFun_ParamInfo_Service{

	CallFunParamInfoDao callFunParamInfoDao;
	@Autowired
	public void setCallFunParamInfoDao(CallFunParamInfoDao callFunParamInfoDao) {
		this.callFunParamInfoDao = callFunParamInfoDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addOne(JqgridNewCallFunParamInfo jqgridNewCallFunParamInfo)throws JpaException {
		// TODO Auto-generated method stub
		try {
			CallFunParamInfo callFunParamInfo = new CallFunParamInfo(jqgridNewCallFunParamInfo);
			callFunParamInfoDao.save(callFunParamInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new JpaException("保存失败",e);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteOne(long id) throws JpaException {
		// TODO Auto-generated method stub
		try {
			callFunParamInfoDao.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new JpaException("删除失败",e);
		}
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public JSONObject findJsonByBusinesstype(String businesstype)throws JpaException {
		// TODO Auto-generated method stub
		try {
			List<CallFunParamInfo> list = callFunParamInfoDao.findCallFunParamInfoByBusinesstype(businesstype);
			if(list!=null&&list.size()>0){
				Jqgrid_DataJson jsonData = new Jqgrid_DataJson(0,0,0,list);
				JSONObject jsonObject = JSONObject.fromObject(jsonData);
				return jsonObject;
			}
		} catch (Exception e) {
			throw new JpaException("查询异常",e);
		}
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateOne(JqgridNewCallFunParamInfo jqgridNewCallFunParamInfo)throws JpaException {
		// TODO Auto-generated method stub
		try {
			CallFunParamInfo callFunParamInfo = new CallFunParamInfo(jqgridNewCallFunParamInfo);
			callFunParamInfoDao.save(callFunParamInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new JpaException("更新失败",e);
		}
	}

}
