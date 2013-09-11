package com.sapGarden.application.commons.basetable.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.basetable.model.BaseTableModel;
import com.sapGarden.application.commons.basetable.service.BaseTableService;
import com.sapGarden.application.commons.dao.ExtendDao;
import com.sapGarden.system.db.DbContextHolder;
@Service
public class BaseTableServiceImpl implements BaseTableService{

	@Autowired
	private ExtendDao<BaseTableModel> extendDao;
	
	public List<BaseTableModel> getBaseTable(String tableName) {
		// TODO Auto-generated method stub
		String sql = "from BaseTableModel t where t.tablename=?1";
		List<BaseTableModel> list = extendDao.findNotInContext(sql, new Object[]{tableName});
		return list;
	}
	@Override
	public List<BaseTableModel> getBaseTableByTableName(String tableName) {
		String dbKey = DbContextHolder.getDbType();
		//因为getBaseTable方法对数据库操作是extendDao.findNotInContext，会新建EntityManager,所以可以在同一线程中连接到不同的库
		DbContextHolder.setDbType("defaultDataSource");
		List<BaseTableModel> list = this.getBaseTable(tableName);
		DbContextHolder.setDbType(dbKey);
		return list;
	}
}
