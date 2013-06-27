package com.sapGarden.application.commons.dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.sapGarden.application.commons.dao.CommonLogDao;
import com.sapGarden.global.jqgrid.model.Jqgrid_searchModel;
import com.sapGarden.global.jqgrid.model.Jqgrid_searchRulesModel;
@Repository
public class CommonLogDaoImpl implements CommonLogDao{

	//与service中的@Transaction一起使用，起到共享事务的效果，否则不能共享
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public long countError(Class modelClass, long sapclient) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(modelClass);
		Root emp = cq.from(modelClass);
		EntityType emp_ = emp.getModel(); 
		cq.select(cb.count(emp));
		cq.where(cb.equal(emp.get(emp_.getSingularAttribute("sapclient",long.class)), sapclient));
		cq.where(cb.equal(emp.get(emp_.getSingularAttribute("optflag",String.class)), "E"));
		Query query = em.createQuery(cq);
		return (Long)query.getSingleResult();
	}

	@Override
	public long countSuccess(Class modelClass, long sapclient) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(modelClass);
		Root emp = cq.from(modelClass);
		EntityType emp_ = emp.getModel(); 
		cq.select(cb.count(emp));
		Predicate condition1 = cb.equal(emp.get(emp_.getSingularAttribute("sapclient",long.class)), sapclient);
		Predicate condition2 = cb.equal(emp.get(emp_.getSingularAttribute("optflag",String.class)), "S");
		Predicate condition3 = cb.isNull(emp.get(emp_.getSingularAttribute("processstatus",String.class)));
		cq.where(cb.and(condition1,condition2,condition3));
		/*
		cq.where(cb.equal(emp.get(emp_.getSingularAttribute("sapclient",long.class)), sapclient));
		cq.where(cb.equal(emp.get(emp_.getSingularAttribute("optflag",String.class)), "S"));
		cq.where(cb.isEmpty(emp.get(emp_.getSingularAttribute("processstatus",String.class))));
		*/
		Query query = em.createQuery(cq);
		return (Long)query.getSingleResult();
	}

	@Override
	public List findErrorLogBySapclientAndCondition(Class modelClass,int pageNumber, int rowsPerPage, long sapclient, String filters) {
		// TODO Auto-generated method stub
		int startNum = (pageNumber-1)*rowsPerPage+1;
		int endNum = startNum+rowsPerPage-1;
		StringBuffer where = new StringBuffer(" where t.optflag= 'E' and t.processstatus is null and t.sapclient='"+sapclient+"' ");
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
					if(data!=null&&!"".equals(data.trim())){
						if(op.equals("le")){
							where.append(" and "+field+" <=to_date('"+data+" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
						}else if(op.equals("ge")){
							where.append(" and "+field+" >=to_date('"+data+" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
						}
					}else{
						where.append(" and "+field+" like '%"+data+"%' ");
					}
				}
			}
		}
		String sql = "from "+modelClass.getName()+" t "+where+" order by t.opttime desc";
		Query query = em.createQuery(sql);
		query.setFirstResult(startNum);
		query.setMaxResults(endNum);
		List list = query.getResultList();
		return list;
	}

	@Override
	public List findSuccessLogBySapclientAndCondition(Class modelClass,int pageNumber, int rowsPerPage, long sapclient, String filters) {
		// TODO Auto-generated method stub
		int startNum = (pageNumber-1)*rowsPerPage+1;
		int endNum = startNum+rowsPerPage-1;
		StringBuffer where = new StringBuffer(" where t.optflag= 'S' and t.sapclient='"+sapclient+"' ");
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
					if(data!=null&&!"".equals(data.trim())){
						if(op.equals("le")){
							where.append(" and "+field+" <=to_date('"+data+" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
						}else if(op.equals("ge")){
							where.append(" and "+field+" >=to_date('"+data+" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
						}
					}else{
						where.append(" and "+field+" like '%"+data+"%' ");
					}
				}
			}
		}
		String sql = "from "+modelClass.getName()+" t "+where+" order by t.opttime desc";
		Query query = em.createQuery(sql);
		query.setFirstResult(startNum);
		query.setMaxResults(endNum);
		List list = query.getResultList();
		return list;
	}

	@Override
	public void updateProcessstatusByIds(Class modelClass, long sapclient,String ids) {
		// TODO Auto-generated method stub
		String sql = "update "+modelClass.getName()+" t set t.processstatus='已处理' where t.id in ("+ids+") and sapclient='"+sapclient+"'";
		Query query = em.createQuery(sql);
		query.executeUpdate();
	}

	

}
