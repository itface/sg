package com.sapGarden.application.synConfig.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sapGarden.application.synConfig.model.CallFunParamInfo;

public interface CallFunParamInfoDao extends CrudRepository<CallFunParamInfo,Long>{

	public List<CallFunParamInfo> findCallFunParamInfoByBusinesstype(String businesstype);
}
