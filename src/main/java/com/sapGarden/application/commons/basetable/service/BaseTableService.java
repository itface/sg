package com.sapGarden.application.commons.basetable.service;

import java.util.List;

import com.sapGarden.application.commons.basetable.model.BaseTableModel;

public interface BaseTableService {

	public List<BaseTableModel> getBaseTableByTableName(String tableName);

}
