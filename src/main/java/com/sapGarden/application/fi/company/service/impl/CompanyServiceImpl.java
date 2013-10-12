package com.sapGarden.application.fi.company.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.baseDao.BaseDao;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.log.model.CommonLogModel;
import com.sapGarden.application.fi.company.model.Company;
import com.sapGarden.application.fi.company.model.CompanyLog;
import com.sapGarden.application.fi.company.service.CompanyService;
import com.sapGarden.global.jqgrid.model.Jqgrid_DataJson;
import com.sapGarden.global.json.JsonUtils;
@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private BaseDao<Company> dao;
	@Override
	public long findTotalNumByPage(SapDataCollection sapDataCollection,String companyCode) {
		StringBuffer sql = new StringBuffer("select count(*) as nums from Company t where t.sapclient=?1");
		List list = new ArrayList();
		list.add(sapDataCollection.getId());
		int index =2;
		if(companyCode!=null&&!"".equals(companyCode)){
			sql.append(" and t.comp_code like ?"+(index++));
			list.add("%"+companyCode+"%");
		}
		return dao.findTotalCount(sql.toString(), list.toArray());
	}
	@Override
	public List<Company> find(SapDataCollection sapDataCollection) {
		// TODO Auto-generated method stub
		return dao.find("from Company t where t.sapclient=?1",new Object[]{sapDataCollection.getId()});
	}
	@Override
	public List<Company> findByPage(SapDataCollection sapDataCollection,String companyCode, int rows, int page,String sidx,String sord) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("from Company t where t.sapclient=?1");
		List list = new ArrayList();
		list.add(sapDataCollection.getId());
		int index =2;
		if(companyCode!=null&&!"".equals(companyCode)){
			sql.append(" and t.comp_code like ?"+(index++));
			list.add("%"+companyCode+"%");
		}
		if(sidx!=null&&!"".equals(sidx)){
			sql.append(" order by t."+sidx+" "+sord);
		}else{
			sql.append(" order by t.comp_code asc");
		}
		return dao.findByPage(sql.toString(), list.toArray(),page,rows);
	}
	@Override
	public Company findByComp_code(SapDataCollection sapDataCollection,String comp_code){
		return dao.findSingleResult("from Company t where t.sapclient=?1 and t.comp_code=?2", new Object[]{sapDataCollection.getId(),comp_code});
	}
	@Override
	public List<Company> findByCompanyCode(SapDataCollection sapDataCollection,String companyCode) {
		// TODO Auto-generated method stub
		return dao.find("from Company t where t.sapclient=?1 and t.comp_code like ?2",new Object[]{sapDataCollection.getId(),"%"+companyCode==null?"":companyCode+"%"});
	}
	@Override
	public JSONObject findDataOfJqgridByPage(SapDataCollection sapDataCollection,String companyCode,int rows,int page,String sidx,String sord){
		// TODO Auto-generated method stub
		List<Company> list = this.findByPage(sapDataCollection, companyCode,rows,page,sidx,sord);
		if(list!=null){
			long total = this.findTotalNumByPage(sapDataCollection, companyCode);
			Jqgrid_DataJson jsonData = new Jqgrid_DataJson(page,rows,total,list);
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
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
	public void updateWithLog(String opttype, long sapclient, String user,Class<Company> modelClass, Class<? extends CommonLogModel> logModelClass,Company t,Company oldt) {
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
