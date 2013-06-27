package com.sapGarden.application.commons.sysdb.dao;

import java.util.List;

public interface SysDbDao {

	public List getColumnInfo(String table,String columnNames);
	public void executeAlterTable(String sql);
}
