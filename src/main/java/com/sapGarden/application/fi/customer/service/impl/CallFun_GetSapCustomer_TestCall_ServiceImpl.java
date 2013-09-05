package com.sapGarden.application.fi.customer.service.impl;

import java.lang.reflect.InvocationTargetException;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.fi.customer.service.CallFun_GetSapCustomer_TestCall_Service;

@Service
public class CallFun_GetSapCustomer_TestCall_ServiceImpl implements CallFun_GetSapCustomer_TestCall_Service{

	@Override
	public JSONObject testCall(SapDataCollection sapDataCollection)
			throws InvocationTargetException, IllegalAccessException,
			NoSuchMethodException, NoSuchFieldException {
		// TODO Auto-generated method stub
		return null;
	}

/*
	private CallFun_GetSapCustomer_Service callFun_GetSapCustomer_Service;
	@Autowired
	public void setCallFun_GetSapCustomer_Service(CallFun_GetSapCustomer_Service callFun_GetSapCustomer_Service) {
		this.callFun_GetSapCustomer_Service = callFun_GetSapCustomer_Service;
	}
	@Override
	public JSONObject testCall(SapClientModel sapclient) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = callFun_GetSapCustomer_Service.call(sapclient);
		Jqgrid_DataJson jsonData1 = new Jqgrid_DataJson(0,0,0,(List)map.get("kna1"));
		Jqgrid_DataJson jsonData2 = new Jqgrid_DataJson(0,0,0,(List)map.get("knb1"));
		Jqgrid_DataJson jsonData3 = new Jqgrid_DataJson(0,0,0,(List)map.get("knvv"));
		HashMap<String,Object> map2 = new HashMap();
		map2.put("kna1", jsonData1);
		map2.put("knb1", jsonData2);
		map2.put("knvv", jsonData3);
		JSONObject json = JsonUtils.objectToJSONObject(map2,null);
		//System.out.println(json.toString());
		return json;
	}
*/
}
