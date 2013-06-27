package com.sapGarden.global.utils.dbUtils;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;


//@Component("JdbcUtils")
public class JdbcUtils extends JdbcDaoSupport{

	private JdbcTemplate jt;
	
	
	

	//@Autowired
	public void setJt(JdbcTemplate jt) {
		super.setJdbcTemplate(jt);
	}
	/**
	 * 批量插入
	 * @param jdbcTemplate
	 * @param table
	 * @param paramList
	 * @return
	 */
	public int[] simpleJdbcBatchInsert(String table,List paramList){
		SimpleJdbcInsert insert = new  SimpleJdbcInsert(super.getJdbcTemplate());
		insert.withTableName(table);
	    insert.setGeneratedKeyName("id");
	    SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(paramList.toArray());
	    int[] keys = insert.executeBatch(params);
	    return keys;
	}
	/**
	 * 插入单条
	 * @param jdbcTemplate
	 * @param table
	 * @param object
	 * @return
	 */
	public long simpleJdbcInsert(String table,Object object){
		SimpleJdbcInsert insert = new  SimpleJdbcInsert(super.getJdbcTemplate());
		insert.withTableName(table);
	    insert.setGeneratedKeyName("id");
	    SqlParameterSource ps = new BeanPropertySqlParameterSource(object);
	    Number key = insert.executeAndReturnKey(ps);
	    return key.longValue();
	}
	/**
	 * 查询多条记录
	 * @param jdbcTemplate
	 * @param sql
	 * @param rowMapper
	 * @return
	 */
	public List queryForList(String sql,Map conditionMap,RowMapper rowMapper){
		NamedParameterJdbcTemplate template=new NamedParameterJdbcTemplate(super.getJdbcTemplate());
		List list = template.query(sql, conditionMap, rowMapper);
		
		//List list = template.getJdbcOperations().query(sql, rowMapper);
		return list;
	}
	/**
	 * 查询单条记录
	 * @param jdbcTemplate
	 * @param sql
	 * @param conditionMap
	 * @param rowMapper
	 * @return
	 */
	public Object queryForObject(String sql,Map conditionMap,RowMapper rowMapper){
		NamedParameterJdbcTemplate template=new NamedParameterJdbcTemplate(super.getJdbcTemplate());
		Object model = template.queryForObject(sql, conditionMap,rowMapper);
		return model;
	}
}
