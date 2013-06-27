package com.sapGarden.application.commons.sysdb.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.sapGarden.application.commons.sysdb.dao.SysDbDao;
@Repository
public class SysDbDaoImpl implements SysDbDao{

//与service中的@Transaction一起使用，起到共享事务的效果，否则不能共享
	

	@PersistenceContext
	private EntityManager em;

	@Override
	public void executeAlterTable(String sql) {
		// TODO Auto-generated method stub
		Query query = em.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List getColumnInfo(String table, String columnNames) {
		// TODO Auto-generated method stub
		String sql = "select t.table_name as table_name,t.column_name as column_name,t.data_type as data_type,t.data_length as data_length,t.data_precision as data_precision,t.data_scale as data_scale from USER_TAB_COLS t where t.table_name='"+table+"' and t.column_name in ("+columnNames+")";
		Query query = em.createNativeQuery(sql);
		return query.getResultList();
	}
}
