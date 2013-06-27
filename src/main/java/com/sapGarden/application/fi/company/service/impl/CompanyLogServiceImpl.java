package com.sapGarden.application.fi.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.fi.company.dao.CompanyLogDao;
import com.sapGarden.application.fi.company.model.CompanyLog;
import com.sapGarden.application.fi.company.service.CompanyLogService;
import com.sapGarden.application.jco.commons.dao.CommonExtendDao;
@Service
public class CompanyLogServiceImpl implements CompanyLogService{

	private CommonExtendDao companyLogDaoImpl;
	private CompanyLogDao companyLogDao;
	@Autowired
	public void setCompanyLogDao(CompanyLogDao companyLogDao) {
		this.companyLogDao = companyLogDao;
	}
	@Autowired
	@Qualifier("companyLogDaoImpl")	
	public void setCompanyLogDaoImpl(CommonExtendDao companyLogDaoImpl) {
		this.companyLogDaoImpl = companyLogDaoImpl;
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<CompanyLog> find(SapDataCollection sapDataCollection,String extendCondition, String filters) {
		// TODO Auto-generated method stub
		List<CompanyLog> list = companyLogDaoImpl.findByCondition(sapDataCollection,filters,extendCondition);
		return list;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeAll(SapDataCollection sapDataCollection){
		// TODO Auto-generated method stub
		companyLogDao.deleteAllBySapclient(sapDataCollection.getId());
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeById(long id) {
		// TODO Auto-generated method stub
		companyLogDao.delete(id);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveList(List list) {
		// TODO Auto-generated method stub
		companyLogDao.save(list);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveOne(Object obj) {
		// TODO Auto-generated method stub
		companyLogDao.save((CompanyLog)obj);
	}
}
