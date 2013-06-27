package com.sapGarden.application.fi.company.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.fi.company.model.CompanyLog;
import com.sapGarden.application.jco.commons.dao.CommonExtendDao;
import com.sapGarden.global.jqgrid.model.Jqgrid_searchModel;
import com.sapGarden.global.jqgrid.model.Jqgrid_searchRulesModel;
@Repository("companyLogDaoImpl")
public class CompanyLogDaoImpl implements CommonExtendDao{

	private EntityManagerFactory entityManagerFactory;
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	public List<CompanyLog> findByCondition(SapDataCollection sapDataCollection,String filters,String extendCondition){
		String comp_code="";
		String comp_name="";
		String dataRange="";
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
					}else if(data!=null&&!"".equals(data.trim())&&field.equals("opttime")){
						if(op.equals("le")){
							dataRange+=" and opttime <=to_date('"+data+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
						}else if(op.equals("ge")){
							dataRange+=" and opttime >=to_date('"+data+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
						}
					}
				}
			}
		}
		if(comp_code==null||"".equals(comp_code)){
			comp_code=" 1=1 ";
		}else{
			comp_code=" comp_code like '%"+comp_code+"%' ";
		}
		if(comp_name==null||"".equals(comp_name)){
			comp_name=" 1=1 ";
		}else{
			comp_name=" comp_name like '%"+comp_name+"%' ";
		}
		String sql = "select * from bo_fin_company_log t where sapclient="+sapDataCollection.getId()+" and "+comp_code+" and "+comp_name+" "+dataRange+" "+(extendCondition==null?"":" and "+extendCondition+" order by opttime desc");
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createNativeQuery(sql, CompanyLog.class);
		List<CompanyLog> list = query.getResultList();
		em.close();
		return list;
	}
}
