package com.sapGarden.application.commons.dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.sapGarden.application.commons.dao.CommonDao;
import com.sapGarden.global.jqgrid.model.Jqgrid_searchModel;
import com.sapGarden.global.jqgrid.model.Jqgrid_searchRulesModel;
@Repository
public class CommonDaoImpl implements CommonDao{

	//与service中的@Transaction一起使用，起到共享事务的效果，否则不能共享
	

	@PersistenceContext
	private EntityManager em;

	@Override
	public List findBySapclient(Class modelClass, long sapclient) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(modelClass);
		Root emp = cq.from(modelClass);
		EntityType emp_ = emp.getModel(); 
		cq.select(emp);
		cq.where(cb.equal(emp.get(emp_.getSingularAttribute("sapclient",long.class)), sapclient));
		Query query = em.createQuery(cq);
		return query.getResultList();
	}
	@Override
	public long count(Class modelClass, long sapclient) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(modelClass);
		Root emp = cq.from(modelClass);
		EntityType emp_ = emp.getModel(); 
		cq.select(cb.count(emp));
		cq.where(cb.equal(emp.get(emp_.getSingularAttribute("sapclient",long.class)), sapclient));
		Query query = em.createQuery(cq);
		return (Long)query.getSingleResult();
		/*
		String sql = "select count(id) from "+modelName+" t where t.sapclient='"+sapclient+"'";
		//em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(sql);
		long count = (Long)query.getSingleResult();
		return count;
		*/
	}

	@Override
	public void deleteById(Class modleClass,long id) {
		// TODO Auto-generated method stub
		// 根据主键从数据库中查询一个实体，这个方法首先从缓存中去查找，如果找不到，就从数据库中去找，并把它加入到缓存中。 
		Object obj = this.findById(modleClass, id);//em.find(modleClass,id);
		if(obj!=null){
			em.remove(obj);
		}
	}

	@Override
	public void deleteBySapclient(Class modelClass, long sapclient) {
		// TODO Auto-generated method stub
		String sql = "delete from "+modelClass.getName()+" t where t.sapclient='"+sapclient+"'";
		Query query = em.createQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List findBySapclientAndCondition(String modelName, int pageNumber,int rowsPerPage, long sapclient, String filters) {
		// TODO Auto-generated method stub
		int startNum = (pageNumber-1)*rowsPerPage+1;
		int endNum = startNum+rowsPerPage-1;
		StringBuffer where = new StringBuffer(" where  t.sapclient='"+sapclient+"' ");
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
		String sql = "from "+modelName+" t  "+where;
		Query query = em.createQuery(sql);
		query.setFirstResult(startNum);
		query.setMaxResults(endNum);
		List list = query.getResultList();
		return list;
	}

	@Override
	public void save(Object object) {
		// TODO Auto-generated method stub
		// 这个方法把一个实体加入持久化上下文中，也就是缓存中，在事务提交或者调用flush()方法的时候，把这个实体保存到数据库中（执行insert语句），如果实体已存在，则抛出EntityExistsException异常，缓存则不存在了。
		if(object instanceof List){
			List list = (List)object;
			for(int i=0;i<list.size();i++){
				Object obj = list.get(i);
				em.persist(obj);
			}
		}else{
			em.persist(object);
		}
	}

	@Override
	public void update(Object object) {
		// TODO Auto-generated method stub
		//把一个对象加入到当前的持久化上下文中，就是把一个对象从detach转变为managed，并返回这个对象。当一个对象设置了主键，并调用此方法，就会从数据库中根据主键查找到该对象把它放到持久化上下文中，当事物提交的时候，如果对象发生了改变，更新该对象的改变到数据库中，如果对象没有改变，则什么也不做，如果对象没有设置主键，则插入该对象到数据库中。
		em.merge(object);
		/*
		if(object instanceof List){
			List list = (List)object;
			for(int i=0;i<list.size();i++){
				Object obj = list.get(i);
				em.merge(obj);
			}
		}else{
			em.merge(object);
		}*/
	}

	@Override
	public void delete(Object object) {
		// TODO Auto-generated method stub
		//根据主键从数据库中删除一个对象，这个对象的状态必须是managed，否则会抛出IllegalArgumentException,在一个事务中删除一个对象,假如实体管理器对象为em, 
		//em.remove(em.merge(entity)),如果直接调用em.remove(entity);会抛出异常 IllegalArgumentException。 
		//把要删除的对象加到持久化上下文中
		//this.update(object);
		if(object instanceof List){
			List list = (List)object;
			for(int i=0;i<list.size();i++){
				Object obj = list.get(i);
				em.remove(obj);
			}
		}else{
			em.remove(object);
		}
	}
	@Override
	public void updateGardenFlag(long sapclient,Class modelClass, int value) {
		// TODO Auto-generated method stub
		String sql = "update "+modelClass.getName()+" t set t.garden_flag='"+value+"' where t.sapclient='"+sapclient+"'";
		Query query = em.createQuery(sql);
		query.executeUpdate();
	}
	@Override
	public List findByIds(Class modelClass, String ids) {
		// TODO Auto-generated method stub
		String sql = "from "+modelClass.getName()+" t where t.id in("+ids+")";
		Query query = em.createQuery(sql,modelClass);
		return query.getResultList();
	}
	@Override
	public List queryBySql(Class modelClass,String sql) {
		// TODO Auto-generated method stub
		Query query = em.createQuery(sql,modelClass);
		return query.getResultList();
	}
	@Override
	public void updateBySql(String sql) {
		// TODO Auto-generated method stub
		Query query = em.createQuery(sql);
		query.executeUpdate();
	}
	@Override
	public Object findById(Class modleClass, long id) {
		// TODO Auto-generated method stub
		return em.find(modleClass,id);
	}

	

}
