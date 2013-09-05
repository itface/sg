package com.sapGarden.application.commons.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.baseDao.BaseDao;
import com.sapGarden.application.commons.log.service.LogDaoService;
@Service
public class LogDaoServiceImpl implements LogDaoService{

	@Autowired
	private BaseDao dao;
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void add(Object obj) {
		// TODO Auto-generated method stub
		dao.persist(obj);
	}
}
