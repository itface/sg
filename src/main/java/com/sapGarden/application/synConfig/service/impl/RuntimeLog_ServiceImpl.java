package com.sapGarden.application.synConfig.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.jco.commons.constants.Constant;
import com.sapGarden.application.jco.commons.service.BasicService;
import com.sapGarden.application.synConfig.service.RuntimeLog_Service;
import com.sapGarden.global.jqgrid.model.Jqgrid_DataJson;
import com.sapGarden.global.json.JsonUtils;
import com.sapGarden.global.utils.commonUtils.BeanUtils;
@Service
public class RuntimeLog_ServiceImpl implements RuntimeLog_Service{

	private BeanUtils beanUtils;
	@Autowired
	public void setBeanUtils(BeanUtils beanUtils) {
		this.beanUtils = beanUtils;
	}
	@Override
	public JSONObject findJsonByBusinesstype(SapDataCollection sapDataCollection,String type,String filters) throws Exception {
		// TODO Auto-generated method stub
		List list = null;
		Object constantObject = beanUtils.getBeanByName(type+"_constants");
		if(constantObject!=null){
			Constant constant = (Constant)constantObject;
			BasicService service = constant.getLogService();
			list = service.find(sapDataCollection," optflag= 'S' ", filters);
		}
		if(list!=null&&list.size()>0){
			Jqgrid_DataJson jsonData = new Jqgrid_DataJson(0,0,0,list);
			return JsonUtils.objectToJSONObject(jsonData,null);
		}
		return null;
	}
}
