package com.sapGarden.application.fi.customer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.basetable.model.BaseTableModel;
import com.sapGarden.application.commons.basetable.service.BaseTableService;
import com.sapGarden.application.commons.sysdb.service.SysDbService;
import com.sapGarden.application.fi.customer.service.ValidateDbService;
@Service
public class ValidateDbServiceImpl implements ValidateDbService{

	@Autowired
	private SysDbService sysDbService;
	@Autowired
	private BaseTableService baseTableService;
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void repairTable() {
		// TODO Auto-generated method stub
		List<BaseTableModel> list1 = baseTableService.getBaseTableByTableName("KNA1");
		List<BaseTableModel> list2 = baseTableService.getBaseTableByTableName("KNB1");
		List<BaseTableModel> list3 = baseTableService.getBaseTableByTableName("KNVV");
		sysDbService.repairTable("KNA1", list1);
		sysDbService.repairTable("KNB1", list2);
		sysDbService.repairTable("KNVV", list3);
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public boolean validateColumnInfo() {
		// TODO Auto-generated method stub
		List<BaseTableModel> list1 = baseTableService.getBaseTableByTableName("KNA1");
		List<BaseTableModel> list2 = baseTableService.getBaseTableByTableName("KNB1");
		List<BaseTableModel> list3 = baseTableService.getBaseTableByTableName("KNVV");
		return sysDbService.validateColumnInfo("KNA1", list1)&&sysDbService.validateColumnInfo("KNB1", list2)&&sysDbService.validateColumnInfo("KNVV", list3);
	}
}
