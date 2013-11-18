package com.sapGarden.application.commons.log.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.baseDao.BaseDao;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.log.service.CommonService;
import com.sapGarden.global.jqgrid.model.Jqgrid_DataJson;
@Service("commonService")
public class CommonServiceImpl implements CommonService<Object>{

	@Autowired
	private BaseDao<Object> dao;
	
	@Override
	public long findTotalNum(SapDataCollection sapDataCollection,String className,JSONObject json) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("select count(*) as nums from "+className+" t where t.sapclient=?1");
		List list = new ArrayList();
		list.add(sapDataCollection.getId());
		int index =2;
		if(json!=null){
			Set keys = json.keySet();
			Iterator it = keys.iterator();
			while(it.hasNext()){
				Object key = it.next();
				if(key!=null&&!"".equals(key.toString())){
					sql.append(" and t."+key+" like ?"+(index++));
					list.add("%"+json.getString(key.toString())+"%");
				}
			}
		}
		return dao.findTotalCount(sql.toString(), list.toArray());
	}

	@Override
	public List findByPage(SapDataCollection sapDataCollection,String className,
			JSONObject json, int rows, int page, String sidx, String sord) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("from "+className+" t where t.sapclient=?1");
		List list = new ArrayList();
		list.add(sapDataCollection.getId());
		int index =2;
		if(json!=null){
			Set keys = json.keySet();
			Iterator it = keys.iterator();
			while(it.hasNext()){
				Object key = it.next();
				if(key!=null&&!"".equals(key.toString())){
					sql.append(" and t."+key+" like ?"+(index++));
					list.add("%"+json.getString(key.toString())+"%");
				}
			}
		}
		if(sidx!=null&&!"".equals(sidx)){
			sql.append(" order by t."+sidx+" "+sord);
		}
		return dao.findByPage(sql.toString(), list.toArray(),page,rows);
	}

	@Override
	public Object findSingleByCondition(SapDataCollection sapDataCollection,String className,
			JSONObject json) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("from "+className+" t where t.sapclient=?1 ");
		List list = new ArrayList();
		list.add(sapDataCollection.getId());
		int index =2;
		if(json!=null){
			Set keys = json.keySet();
			Iterator it = keys.iterator();
			while(it.hasNext()){
				Object key = it.next();
				if(key!=null&&!"".equals(key.toString())){
					sql.append(" and t."+key+" like ?"+(index++));
					list.add("%"+json.getString(key.toString())+"%");
				}
			}
		}
		return dao.findSingleResult(sql.toString(),list.toArray());
	}

	@Override
	public List findAllByCondition(SapDataCollection sapDataCollection,String className,
			JSONObject json) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("from "+className+" t where t.sapclient=?1 ");
		List list = new ArrayList();
		list.add(sapDataCollection.getId());
		int index =2;
		if(json!=null){
			Set keys = json.keySet();
			Iterator it = keys.iterator();
			while(it.hasNext()){
				Object key = it.next();
				if(key!=null&&!"".equals(key.toString())){
					sql.append(" and t."+key+" like ?"+(index++));
					list.add("%"+json.getString(key.toString())+"%");
				}
			}
		}
		return dao.find(sql.toString(),list.toArray());
	}

	@Override
	public JSONObject findDataOfJqgridByPage(
			SapDataCollection sapDataCollection,String className, JSONObject json, int rows,
			int page, String sidx, String sord) {
		// TODO Auto-generated method stub
		List<Object> list = this.findByPage(sapDataCollection,className, json,rows,page,sidx,sord);
		if(list!=null){
			long total = this.findTotalNum(sapDataCollection,className, json);
			Jqgrid_DataJson jsonData = new Jqgrid_DataJson(page,rows,total,list);
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			return jsonObject;
		}
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addList(List t) {
		// TODO Auto-generated method stub
		dao.saveList(t);
		//dao.saveBatchList(t);
	}

	@Override
	@Transactional
	public void add(Object t) {
		// TODO Auto-generated method stub
		dao.persist(t);
	}

	@Override
	@Transactional
	public void update(Object t) {
		// TODO Auto-generated method stub
		dao.update(t);
	}

	@Override
	@Transactional
	public void removeAll(SapDataCollection sapDataCollection,String className) {
		// TODO Auto-generated method stub
		dao.executeUpdate("delete from "+className+" t where t.sapclient=?1", new Object[]{sapDataCollection.getId()});
	}

	@Override
	@Transactional
	public void removeById(long id,Class clazz) {
		// TODO Auto-generated method stub
		dao.deleteById(clazz, id);
	}

	@Override
	@Transactional
	public void addListWithLog(String opttype, long sapclient, String user,
			Class modelClass, Class logModelClass, List t) {
		// TODO Auto-generated method stub
		dao.saveList(t);
	}

	@Override
	@Transactional
	public void addWithLog(String opttype, long sapclient, String user,
			Class modelClass, Class logModelClass, Object t) {
		// TODO Auto-generated method stub
		dao.persist(t);
	}

	@Override
	@Transactional
	public void updateWithLog(String opttype, long sapclient, String user,
			Class modelClass, Class logModelClass, Object t, Object oldt) {
		// TODO Auto-generated method stub
		dao.update(t);
	}

	@Override
	@Transactional
	public void deleteWithLog(String opttype, long sapclient, String user,
			Class modelClass, Class logModelClass, Object t) {
		// TODO Auto-generated method stub
		dao.delete(t);
	}
	@Override
	@Transactional
	public void executeUpdateSql(String sql, Object[] param) {
		// TODO Auto-generated method stub
		dao.executeUpdate(sql, param);
	}

	@Override
	public List executeQuerySql(String sql, Object[] param) {
		// TODO Auto-generated method stub
		return dao.find(sql, param);
	}

}
