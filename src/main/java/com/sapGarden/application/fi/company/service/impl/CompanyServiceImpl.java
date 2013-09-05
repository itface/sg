package com.sapGarden.application.fi.company.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.baseDao.BaseDao;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.jco.model.CommonCompareDataModel;
import com.sapGarden.application.commons.log.model.CommonLogModel;
import com.sapGarden.application.commons.log.service.CommonServiceWithLog;
import com.sapGarden.application.fi.company.model.Company;
import com.sapGarden.application.fi.company.model.CompanyCompareDataModel;
import com.sapGarden.application.fi.company.model.CompanyLog;
import com.sapGarden.application.fi.company.service.CompanyService;
import com.sapGarden.global.jqgrid.model.Jqgrid_DataJson;
import com.sapGarden.global.json.JsonUtils;
@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private BaseDao<Company> dao;
	@Override
	public List<Company> find(SapDataCollection sapDataCollection) {
		// TODO Auto-generated method stub
		return dao.find("from Company t where t.sapclient=?1",new Object[]{sapDataCollection.getId()});
	}
	@Override
	public List<Company> findByCompanyCode(SapDataCollection sapDataCollection,String companyCode) {
		// TODO Auto-generated method stub
		return dao.find("from Company t where t.sapclient=?1 and t.comp_code like ?2",new Object[]{sapDataCollection.getId(),"%"+companyCode==null?"":companyCode+"%"});
	}
	@Override
	public JSONObject findJqgridDataByCompanyCode(SapDataCollection sapDataCollection, String companyCode) {
		// TODO Auto-generated method stub
		List<Company> list = this.findByCompanyCode(sapDataCollection, companyCode);
		if(list!=null){
			Jqgrid_DataJson jsonData = new Jqgrid_DataJson(0,0,0,list);
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			return jsonObject;
		}
		return null;
	}
	@Override
	public JSONObject findDataOfJqgrid(SapDataCollection sapDataCollection) {
		// TODO Auto-generated method stub
		List<Company> list = this.find(sapDataCollection);
		if(list!=null&&list.size()>0){
			Jqgrid_DataJson jsonData = new Jqgrid_DataJson(0,0,0,list);
			JSONObject jsonObject = JsonUtils.objectToJSONObject(jsonData,null);
			return jsonObject;
		}
		return null;
	}
	
	@Override
	@Transactional
	public void addList(List<Company> list) {
		// TODO Auto-generated method stub
		dao.saveList(list);
	}

	@Override
	@Transactional
	public void add(Company company) {
		// TODO Auto-generated method stub
		dao.persist(company);
	}

	@Override
	@Transactional
	public void removeAll(SapDataCollection sapDataCollection) {
		// TODO Auto-generated method stub
		dao.executeUpdate("delete from Company t where t.sapclient=?1", new Object[]{sapDataCollection.getId()});
	}

	@Override
	@Transactional
	public void removeById(long id) {
		// TODO Auto-generated method stub
		dao.deleteById(Company.class, id);
	}
	@Override
	@Transactional
	public void update(Company company) {
		// TODO Auto-generated method stub
		dao.update(company);
	}

	@Override
	@Transactional
	public void addWithLog(String opttype, long sapclient, String user,Class<Company> modelClass, Class<? extends CommonLogModel> logModelClass,Company t) {
		// TODO Auto-generated method stub
		dao.persist(t);
		//throw new RuntimeException();
	}

	@Override
	@Transactional
	public void updateWithLog(String opttype, long sapclient, String user,Class<Company> modelClass, Class<? extends CommonLogModel> logModelClass,Company t) {
		// TODO Auto-generated method stub
		dao.update(t);
	}

	@Override
	@Transactional
	public void deleteWithLog(String opttype, long sapclient, String user,Class<Company> modelClass, Class<? extends CommonLogModel> logModelClass,Company t) {
		// TODO Auto-generated method stub
		dao.deleteById(Company.class, t.getId());
	}
	

	

	

	

	
}
