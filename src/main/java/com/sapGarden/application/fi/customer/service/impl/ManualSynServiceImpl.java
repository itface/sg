package com.sapGarden.application.fi.customer.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.log.service.CommonService;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.service.Runtime_ColumnInfo_Service;
import com.sapGarden.application.fi.customer.model.Kna1;
import com.sapGarden.application.fi.customer.model.Kna1Log;
import com.sapGarden.application.fi.customer.model.Knb1;
import com.sapGarden.application.fi.customer.model.Knb1Log;
import com.sapGarden.application.fi.customer.model.Knvv;
import com.sapGarden.application.fi.customer.model.KnvvLog;
import com.sapGarden.application.fi.customer.model.SapDataModel;
import com.sapGarden.application.fi.customer.service.GetSapDataService;
import com.sapGarden.application.fi.customer.service.ManualSynService;
import com.sapGarden.global.jqgrid.model.Jqgrid_DataJson;
@Service
public class ManualSynServiceImpl implements ManualSynService{

	@Autowired
	private GetSapDataService getSapDataService;

	@Autowired
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;

	
	@Override
	@Transactional
	public void manualSyn(SapDataCollection sapDataCollection, String user, String kna1s,String knb1s,String knvvs){
		// TODO Auto-generated method stub
		List<Kna1> kna1List = null;
		List<Knb1> knb1List = null;
		List<Knvv> knvvList = null;
		if(kna1s!=null&&!"".equals(kna1s)){
			JSONObject json = JSONObject.fromObject(kna1s);
			JSONArray jsonArray = json.getJSONArray("list");
			kna1List =JSONArray.toList(jsonArray,Kna1.class);
			commonService.addListWithLog(CommonService.OPTTYPE_MANUALSYN, sapDataCollection.getId(), user, Kna1.class, Kna1Log.class, kna1List);
		}
		if(knb1s!=null&&!"".equals(knb1s)){
			JSONObject json = JSONObject.fromObject(knb1s);
			JSONArray jsonArray = json.getJSONArray("list");
			knb1List =JSONArray.toList(jsonArray,Knb1.class);
			commonService.addListWithLog(CommonService.OPTTYPE_MANUALSYN, sapDataCollection.getId(), user, Knb1.class, Knb1Log.class, knb1List);
		}
		if(knvvs!=null&&!"".equals(knvvs)){
			JSONObject json = JSONObject.fromObject(knvvs);
			JSONArray jsonArray = json.getJSONArray("list");
			knvvList =JSONArray.toList(jsonArray,Knvv.class);
			commonService.addListWithLog(CommonService.OPTTYPE_MANUALSYN, sapDataCollection.getId(), user, Knvv.class, KnvvLog.class, knvvList);
		}
		/*
		compareDataService.initComaredData(sapDataCollection);
		compareDataService.doCompare(sapDataCollection,user, kunnr,"","", "", "");
		List<Kna1Compared> kna1List = commonService.findBySapclient(Kna1Compared.class, sapDataCollection.getId());
		List<Knb1Compared> knb1List = commonService.findBySapclient(Knb1Compared.class, sapDataCollection.getId());
		List<KnvvCompared> knvvList = commonService.findBySapclient(KnvvCompared.class, sapDataCollection.getId());
		compareDataService.comparedDataToLocal(sapDataCollection.getId(), user, CommonService.OPTTYPE_MANUALSYN, kna1List, knb1List, knvvList);
		*/
	}
	@Override
	public JSONObject getSapData(SapDataCollection sapDataCollection,
			String kunnr) throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException, InstantiationException, NoSuchFieldException {
		// TODO Auto-generated method stub
		List<Kna1> kna1Arr = new ArrayList<Kna1>();
		List<Knb1> knb1Arr = new ArrayList<Knb1>();
		List<Knvv> knvvArr = new ArrayList<Knvv>();
		Object O_CONTROL1 = null;
		Object O_CONTROL2 = null;
		Object O_CONTROL3 = null;
		Object O_CONTROL4 = null;
		Object O_CONTROL5 = null;
		Object O_CONTROL6 = null;
		SapDataModel sapDataModel = null;
		int perNum=500;
		int totalNum=0;
		List<RuntimeColumnInfo> kna1RuntimeColumnInfoList = runtime_ColumnInfo_Service.findAll(sapDataCollection,"KNA1");
		List<RuntimeColumnInfo> knb1RuntimeColumnInfoList = runtime_ColumnInfo_Service.findAll(sapDataCollection,"KNB1");
		List<RuntimeColumnInfo> knvvRuntimeColumnInfoList = runtime_ColumnInfo_Service.findAll(sapDataCollection,"KNVV");
		String activeKna1s = getSapDataService.getActiveColumnNames(kna1RuntimeColumnInfoList);
		String activeKnb1s = getSapDataService.getActiveColumnNames(knb1RuntimeColumnInfoList);
		String activeKnvvs = getSapDataService.getActiveColumnNames(knvvRuntimeColumnInfoList);
		String kna1s = getSapDataService.getColumnNames(kna1RuntimeColumnInfoList);
		String knb1s = getSapDataService.getColumnNames(knb1RuntimeColumnInfoList);
		String knvvs = getSapDataService.getColumnNames(knvvRuntimeColumnInfoList);
		sapDataModel = getSapDataService.getSapData(sapDataCollection, perNum, kna1RuntimeColumnInfoList, knb1RuntimeColumnInfoList, knvvRuntimeColumnInfoList, kna1s, knb1s, knvvs, O_CONTROL1, O_CONTROL2, O_CONTROL3, O_CONTROL4, O_CONTROL5, O_CONTROL6,kunnr,"","","","");
		totalNum=sapDataModel.getTotalNum();
		O_CONTROL1 =  sapDataModel.getO_CONTROL1();
		O_CONTROL2 =  sapDataModel.getO_CONTROL2();
		O_CONTROL3 =  sapDataModel.getO_CONTROL3();
		O_CONTROL4 =  sapDataModel.getO_CONTROL4();
		O_CONTROL5 =  sapDataModel.getO_CONTROL5();
		O_CONTROL6 =  sapDataModel.getO_CONTROL6();
		if(totalNum>0){
			while(true){
				sapDataModel = getSapDataService.getSapData(sapDataCollection, perNum, kna1RuntimeColumnInfoList, knb1RuntimeColumnInfoList, knvvRuntimeColumnInfoList, kna1s, knb1s, knvvs, O_CONTROL1, O_CONTROL2, O_CONTROL3, O_CONTROL4, O_CONTROL5, O_CONTROL6,kunnr,"","","","");
				O_CONTROL1 =  sapDataModel.getO_CONTROL1();
				O_CONTROL2 =  sapDataModel.getO_CONTROL2();
				O_CONTROL3 =  sapDataModel.getO_CONTROL3();
				O_CONTROL4 =  sapDataModel.getO_CONTROL4();
				O_CONTROL5 =  sapDataModel.getO_CONTROL5();
				O_CONTROL6 =  sapDataModel.getO_CONTROL6();
				List<Kna1> kna1List = sapDataModel.getKna1List();
				List<Knb1> knb1List = sapDataModel.getKnb1List();
				List<Knvv> knvvList = sapDataModel.getKnvvList();
				if(kna1List!=null&&kna1List.size()>0){
					kna1Arr.addAll(kna1List);
					/*
					List<Kna1Compared> kna1CompareDataModelList = new LinkedList<Kna1Compared>();
					for(Kna1 sKna1 : kna1List){
						kna1CompareDataModelList.add(new Kna1Compared(sapDataCollection.getId(),sKna1,null));
					}
					commonService.addList(kna1CompareDataModelList);
					*/
				}
				if(knb1List!=null&&knb1List.size()>0){
					knb1Arr.addAll(knb1List);
				}
				if(knvvList!=null&&knvvList.size()>0){
					knvvArr.addAll(knvvList);
				}
				if(!"".equals(sapDataModel.getMark().trim())){
					break;
				}
			}
		}
		JSONObject json = new JSONObject();
		Jqgrid_DataJson jsonData = new Jqgrid_DataJson(0,0,0,kna1Arr);
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		json.put("kna1", jsonObject);
		jsonData = new Jqgrid_DataJson(0,0,0,knb1Arr);
		jsonObject = JSONObject.fromObject(jsonData);
		json.put("knb1", jsonObject);
		jsonData = new Jqgrid_DataJson(0,0,0,knvvArr);
		jsonObject = JSONObject.fromObject(jsonData);
		json.put("knvv", jsonObject);
		return json;
	}

}
