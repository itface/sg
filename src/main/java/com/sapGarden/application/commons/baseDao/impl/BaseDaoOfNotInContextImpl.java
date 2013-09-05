package com.sapGarden.application.commons.baseDao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sapGarden.application.commons.baseDao.BaseDaoOfNotInContext;
@Repository
public class BaseDaoOfNotInContextImpl<T> implements BaseDaoOfNotInContext<T>{

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public void persistNotInContext(T t) {
		// TODO Auto-generated method stub
		EntityManager em2 = entityManagerFactory.createEntityManager();
		try{
			em2.persist(t);
		}finally{
			em2.close();
		}
	}
	@Override
	public void updateNotInContext(T t) {
		// TODO Auto-generated method stub
		EntityManager em2 = entityManagerFactory.createEntityManager();
		try{
			t = em2.merge(t);
		}finally{
			em2.close();
		}
	}
	@Override
	public void updateNotInContext(String jpql, Object[] param) {
		// TODO Auto-generated method stub
		EntityManager em2 = entityManagerFactory.createEntityManager();
		try{
			Query query = em2.createQuery(jpql);
			if(param!=null){
				for (int i = 1; i <= param.length; i++) {
					query.setParameter(i, param[i - 1]);
				}
			}
			query.executeUpdate();
		}finally{
			em2.close();
		}
		
	}
	@Override
	public List<T> findNotInContext(String jpql, Object[] param) {
		// TODO Auto-generated method stub
		List<T> list = null;
		Query query;
		EntityManager em2 = entityManagerFactory.createEntityManager();
		try {
			query = em2.createQuery(jpql);
			for (int i = 1; i <= param.length; i++) {
				query.setParameter(i, param[i - 1]);
			}
			list = query.getResultList();
		}finally{
			em2.close();
		}
		return list;
	}
}
