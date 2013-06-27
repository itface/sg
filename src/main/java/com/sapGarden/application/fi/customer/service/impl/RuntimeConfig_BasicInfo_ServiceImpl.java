package com.sapGarden.application.fi.customer.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.basetable.model.BaseTableModel;
import com.sapGarden.application.commons.basetable.service.BaseTableService;
import com.sapGarden.application.commons.runtime.baseinfo.model.RuntimeBasicInfo;
import com.sapGarden.application.commons.runtime.baseinfo.service.Runtime_BasicInfo_Service;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.service.Runtime_ColumnInfo_Service;
import com.sapGarden.application.commons.runtime.tableinfo.model.RuntimeTableInfo;
import com.sapGarden.application.commons.runtime.tableinfo.service.Runtime_TableInfo_Service;
import com.sapGarden.application.fi.customer.service.RuntimeConfig_BasicInfo_Service;
@Service
public class RuntimeConfig_BasicInfo_ServiceImpl implements RuntimeConfig_BasicInfo_Service{

	@Autowired
	private BaseTableService baseTableService;
	@Autowired
	private Runtime_BasicInfo_Service runtime_BasicInfo_Service;
	@Autowired
	private Runtime_TableInfo_Service runtime_TableInfo_Service;
	@Autowired
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addBasicInfo(RuntimeBasicInfo runtimeBasicInfo,long sapclient,List<BaseTableModel> kna1List,List<BaseTableModel> knb1List,List<BaseTableModel> knvvList){
		List<BaseTableModel> kna1List2 = baseTableService.getBaseTableByTableName("KNA1");
		List<BaseTableModel> knb1List2 = baseTableService.getBaseTableByTableName("KNB1");
		List<BaseTableModel> knvvList2 = baseTableService.getBaseTableByTableName("KNVV");
		//runtime_BasicInfo_Service.addOne(runtimeBasicInfo);
		this.initcolumnInfo(sapclient, kna1List2, knb1List2, knvvList2);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void initcolumnInfo(long sapclient,List<BaseTableModel> kna1List,List<BaseTableModel> knb1List,List<BaseTableModel> knvvList){
		RuntimeTableInfo kna1RuntimeTableInfo = new RuntimeTableInfo();
		kna1RuntimeTableInfo.setBusinesstype("KNA1");
		kna1RuntimeTableInfo.setSapclient(sapclient);
		kna1RuntimeTableInfo.setReflectTable("KNA1");
		runtime_TableInfo_Service.deleteByBusinesstypeAndSapclient("KNA1", sapclient);
		runtime_TableInfo_Service.addOne(kna1RuntimeTableInfo);
		if(kna1List!=null&&kna1List.size()>0){
			List<RuntimeColumnInfo> runtimeColumnInfoList = new LinkedList<RuntimeColumnInfo>();
			for(BaseTableModel baseTableModel : kna1List){
				RuntimeColumnInfo runtimeColumnInfo = new RuntimeColumnInfo(baseTableModel,sapclient);
				runtimeColumnInfoList.add(runtimeColumnInfo);
			}
			runtime_ColumnInfo_Service.deleteByTableAndSapclient("KNA1", sapclient);
			runtime_ColumnInfo_Service.addList(runtimeColumnInfoList);
		}
		
		RuntimeTableInfo knb1RuntimeTableInfo = new RuntimeTableInfo();
		knb1RuntimeTableInfo.setBusinesstype("KNB1");
		knb1RuntimeTableInfo.setSapclient(sapclient);
		knb1RuntimeTableInfo.setReflectTable("KNB1");
		runtime_TableInfo_Service.deleteByBusinesstypeAndSapclient("KNB1", sapclient);
		runtime_TableInfo_Service.addOne(knb1RuntimeTableInfo);
		if(knb1List!=null&&knb1List.size()>0){
			List<RuntimeColumnInfo> runtimeColumnInfoList = new LinkedList<RuntimeColumnInfo>();
			for(BaseTableModel baseTableModel : knb1List){
				RuntimeColumnInfo runtimeColumnInfo = new RuntimeColumnInfo(baseTableModel,sapclient);
				runtimeColumnInfoList.add(runtimeColumnInfo);
			}
			runtime_ColumnInfo_Service.deleteByTableAndSapclient("KNB1", sapclient);
			runtime_ColumnInfo_Service.addList(runtimeColumnInfoList);
		}
		
		RuntimeTableInfo knvvRuntimeTableInfo = new RuntimeTableInfo();
		knvvRuntimeTableInfo.setBusinesstype("KNVV");
		knvvRuntimeTableInfo.setSapclient(sapclient);
		knvvRuntimeTableInfo.setReflectTable("KNVV");
		runtime_TableInfo_Service.deleteByBusinesstypeAndSapclient("KNVV", sapclient);
		runtime_TableInfo_Service.addOne(knvvRuntimeTableInfo);
		if(knvvList!=null&&knvvList.size()>0){
			List<RuntimeColumnInfo> runtimeColumnInfoList = new LinkedList<RuntimeColumnInfo>();
			for(BaseTableModel baseTableModel : knvvList){
				RuntimeColumnInfo runtimeColumnInfo = new RuntimeColumnInfo(baseTableModel,sapclient);
				runtimeColumnInfoList.add(runtimeColumnInfo);
			}
			runtime_ColumnInfo_Service.deleteByTableAndSapclient("KNVV", sapclient);
			runtime_ColumnInfo_Service.addList(runtimeColumnInfoList);
		}
	}
}
