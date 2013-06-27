package com.sapGarden.system.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
public class DynamicDataSource extends AbstractRoutingDataSource{

	/**
	 * 取得当前线程中设置的数据库连接的key,系统根据这个key去取的数据库连接，然后在当前线程使用
	 *如果没取到，则使用默认的数据库连接。
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return DbContextHolder.getDbType();
	}

}
