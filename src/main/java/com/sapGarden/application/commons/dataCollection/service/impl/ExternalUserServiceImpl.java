package com.sapGarden.application.commons.dataCollection.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dao.ExtendDao;
import com.sapGarden.application.commons.dataCollection.model.ExternalUser;
import com.sapGarden.application.commons.dataCollection.service.ExternalUserService;
import com.sapGarden.global.jqgrid.model.Jqgrid_DataJson;
import com.sapGarden.global.json.JsonUtils;
import com.sapGarden.global.paramQueryGrid.ParamQueryGrid_DataJson;
@Service
public class ExternalUserServiceImpl implements ExternalUserService{

	@Autowired
	private ExtendDao<ExternalUser> extendDao;
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addOne(ExternalUser externalUser) {
		// TODO Auto-generated method stub
		extendDao.persist(externalUser);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteOne(Long id) {
		// TODO Auto-generated method stub
		extendDao.deleteById(ExternalUser.class, id);
	}

	@Override
	public List<ExternalUser> findAll() {
		// TODO Auto-generated method stub
		return extendDao.find("from ExternalUser t");
	}

	@Override
	public JSONObject findAllJson() {
		// TODO Auto-generated method stub
		List<ExternalUser> list  = this.findAll();
		if(list!=null&&list.size()>0){
			Jqgrid_DataJson jsonData = new Jqgrid_DataJson(0,0,0,list);
			JSONObject jsonObject = JsonUtils.objectToJSONObject(jsonData,null);
			return jsonObject;
		}
		return null;
	}

	@Override
	public ExternalUser findById(long id) {
		// TODO Auto-generated method stub
		return extendDao.find(ExternalUser.class, id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateOne(ExternalUser externalUser) {
		// TODO Auto-generated method stub
		extendDao.save(externalUser);
	}

	@Override
	public List<ExternalUser> findByUsernameAndSapclient(String username,String sapclientAlias){
		// TODO Auto-generated method stub
		List list = extendDao.findNotInContext("from ExternalUser t where t.username=?1 and t.sapdatacollection=?2", new Object[]{username,sapclientAlias});
		return list;
	}

}
