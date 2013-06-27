package com.sapGarden.application.commons.sysdb.service;

import java.util.List;

import com.sapGarden.application.commons.basetable.model.BaseTableModel;

public interface SysDbService {

	public boolean validateColumnInfo(String tableName,List<BaseTableModel> list);
	public void repairTable(String tableName,List<BaseTableModel> list);
}
