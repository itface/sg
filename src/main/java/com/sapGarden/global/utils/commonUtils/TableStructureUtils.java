package com.sapGarden.global.utils.commonUtils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sapGarden.global.model.TableStructure;
@Component
public class TableStructureUtils {

	EntityManagerFactory entityManagerFactory;
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	public List<TableStructure> getTableStructure(String table){
		String sql="select t.TABLE_NAME as table_name,t.COLUMN_NAME as column_name,t.DATA_TYPE as data_type from USER_TAB_COLS t where t.TABLE_NAME='"+table.toUpperCase()+"'";
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createNativeQuery(sql,TableStructure.class);
 	    List<TableStructure> list = query.getResultList();
 	    em.close();
 	    return list;
	}
	
}
