package com.sapGarden.application.commons.log.service.impl;

import java.util.List;

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
	@Transactional
	public void add(Object obj) {
		// TODO Auto-generated method stub
		dao.persist(obj);
	}
	@Override
	@Transactional
	public void addList(List<Object> list) {
		// TODO Auto-generated method stub
		dao.saveList(list);
	}
}
