package com.sapGarden.application.commons.baseDao;

import java.util.List;

public interface BaseDaoOfNotInContext<T> {

	public void persistNotInContext(T t);
	public void updateNotInContext(T t);
	public void updateNotInContext(String jpql, Object[] param);
	public List<T> findNotInContext(String jpql, Object[] param);
}
