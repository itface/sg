package com.sapGarden.application.fi.company.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.fi.company.model.Company;
import com.sapGarden.application.jco.commons.dao.CommonExtendDao;
import com.sapGarden.global.jqgrid.model.Jqgrid_searchModel;
import com.sapGarden.global.jqgrid.model.Jqgrid_searchRulesModel;
@Repository("companyDaoImpl")
public class CompanyDaoImpl implements  CommonExtendDao{

	private EntityManagerFactory entityManagerFactory;
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	public List<Company> findByCondition(SapDataCollection sapDataCollection,String filters,String extendCondition){
		String comp_code="";
		String comp_name="";
		if(filters!=null&&!"".equals(filters.trim())){
			JSONObject j = JSONObject.fromObject(filters);
			Jqgrid_searchModel searchModel = (Jqgrid_searchModel)JSONObject.toBean(j, Jqgrid_searchModel.class);			
			String group = searchModel.getGroupOp();
			List rules = searchModel.getRules();;
			if(rules!=null){
				for(int i=0;i<rules.size();i++){
					JSONObject jj = JSONObject.fromObject(rules.get(i));
					Jqgrid_searchRulesModel searchRule = (Jqgrid_searchRulesModel)JSONObject.toBean(jj, Jqgrid_searchRulesModel.class);
					String field = searchRule.getField();
					String data = searchRule.getData();
					String op = searchRule.getOp();
					if(field.equals("comp_code")){
						comp_code = (data==null?"":data);
					}else if(field.equals("comp_name")){
						comp_name = (data==null?"":data);
					}
				}
			}
		}
		String sql = "select * from bo_fin_company t where t.sapclient="+sapDataCollection.getId()+" and t.comp_code like '%"+comp_code+"%' and t.comp_name like '%"+comp_name+"%' order by id desc";
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createNativeQuery(sql, Company.class);
		List<Company> list = query.getResultList();
		em.close();
		return list;
	}

}
