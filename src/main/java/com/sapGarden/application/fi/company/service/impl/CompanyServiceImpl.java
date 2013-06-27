package com.sapGarden.application.fi.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.fi.company.dao.CompanyDao;
import com.sapGarden.application.fi.company.model.Company;
import com.sapGarden.application.fi.company.service.CompanyService;
import com.sapGarden.application.jco.commons.dao.CommonExtendDao;
@Service
public class CompanyServiceImpl implements CompanyService{

	private CompanyDao companyDao;
	private CommonExtendDao companyDaoImpl;
	@Autowired
	@Qualifier("companyDaoImpl")	
	public void setCompanyLogDaoImpl(CommonExtendDao companyDaoImpl) {
		this.companyDaoImpl = companyDaoImpl;
	}
	@Autowired
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Company> find(SapDataCollection sapDataCollection,String extendCondition, String filters) {
		// TODO Auto-generated method stub
		List<Company> list = companyDaoImpl.findByCondition(sapDataCollection,filters,extendCondition);
		return list;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeAll(SapDataCollection sapDataCollection) {
		// TODO Auto-generated method stub
		companyDao.deleteBySapclient(sapDataCollection.getId());
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeById(long id)  {
		// TODO Auto-generated method stub
		companyDao.delete(id);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveList(List list){
		// TODO Auto-generated method stub
		companyDao.save(list);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveOne(Object obj)  {
		// TODO Auto-generated method stub
		companyDao.save((Company)obj);
	}

}
