package com.sapGarden.application.jco.commons.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.jco.commons.constants.Constant;
import com.sapGarden.application.jco.commons.model.CommonCompareDataModel;
import com.sapGarden.application.jco.commons.service.CommonDaoService;
import com.sapGarden.global.exception.JpaException;
import com.sapGarden.global.utils.commonUtils.BeanUtils;
@Service("commonDaoService")
@Order(2)
public class CommonDaoServiceImpl implements CommonDaoService{


	private PagingAndSortingRepository dao = null;
	private BeanUtils beanUtils;
	@Autowired
	public void setBeanUtils(BeanUtils beanUtils) {
		this.beanUtils = beanUtils;
	}
	public void init(String type){
		Object constantObject = beanUtils.getBeanByName(type+"_constants");
		if(constantObject!=null){
			Constant constant = (Constant)constantObject;
			dao = constant.getDao();
		}else{
			dao = null;
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void addList(boolean logException,List objectList, String type,String optType,String user)throws JpaException{
		// TODO Auto-generated method stub
		try {
			this.init(type);
			dao.save(objectList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new JpaException(e);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void addOne(boolean logException,Object obj, String type,String optType,String user)throws JpaException {
		// TODO Auto-generated method stub
		try {
			this.init(type);
			dao.save(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new JpaException(e);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void removeAll(boolean logException,String type,String optType,String user)throws JpaException {
		// TODO Auto-generated method stub
		try {
			this.init(type);
			dao.deleteAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new JpaException(e);
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void removeOneById(boolean logException,long id, String type,String optType,String user)throws JpaException {
		// TODO Auto-generated method stub
		try {
			this.init(type);
			dao.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new JpaException(e);
		}
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateList(boolean logException, List objectList,List<CommonCompareDataModel> compareDataModelList, String type,String optType, String user) throws JpaException {
		// TODO Auto-generated method stub
		try {
			this.init(type);
			dao.save(objectList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new JpaException(e);
		}
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateOne(boolean logException, Object obj,CommonCompareDataModel compareDataModel, String type,String optType, String user) throws JpaException {
		// TODO Auto-generated method stub
		try {
			this.init(type);
			dao.save(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new JpaException(e);
		}
	}

}
