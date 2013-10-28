package com.sapGarden.application.commons.log.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.baseDao.BaseDao;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.log.service.CommonLogService;
import com.sapGarden.application.fi.customer.model.KnvvLog;
import com.sapGarden.global.jqgrid.model.Jqgrid_DataJson;
import com.sapGarden.global.json.JsonUtils;
@Service("commonLogService")
public class CommonLogServiceImpl implements CommonLogService<Object>{

	@Autowired
	private BaseDao<Object> dao;

	@Override
	@Transactional
	public void save(Object t) {
		// TODO Auto-generated method stub
		dao.persist(t);
	}

	@Override
	public long findTotalNum(SapDataCollection sapDataCollection,String className,JSONObject json, String optflag, String bdate, String edate) {
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
		if(optflag!=null&&!"".equals(optflag)){
			sql.append(" and t.optflag=?"+(index++));
			list.add(optflag);
		}
		if(bdate!=null&&!"".equals(bdate)){
			sql.append(" and t.opttime>=?"+(index++));
			Calendar c = Calendar.getInstance();
			String year = bdate.substring(0, 4);
			String month = bdate.substring(5, 7);
			String date = bdate.substring(8, 10);
			c.set(Calendar.YEAR, Integer.parseInt(year));
			c.set(Calendar.MONTH, Integer.parseInt(month)-1);
			c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date));
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			list.add(c.getTime());
		}
		if(edate!=null&&!"".equals(edate)){
			sql.append(" and t.opttime<=?"+(index++));
			Calendar c = Calendar.getInstance();
			String year = edate.substring(0, 4);
			String month = edate.substring(5, 7);
			String date = edate.substring(8, 10);
			c.set(Calendar.YEAR, Integer.parseInt(year));
			c.set(Calendar.MONTH, Integer.parseInt(month)-1);
			c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date));
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
			list.add(c.getTime());
		}
		return dao.findTotalCount(sql.toString(), list.toArray());
	}

	@Override
	public List<Object> find(SapDataCollection sapDataCollection,String className,
			JSONObject json, String optflag, String bdate, String edate) {
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
		if(optflag!=null&&!"".equals(optflag)){
			sql.append(" and t.optflag=?"+(index++));
			list.add(optflag);
		}
		if(bdate!=null&&!"".equals(bdate)){
			sql.append(" and t.opttime>=?"+(index++));
			Calendar c = Calendar.getInstance();
			String year = bdate.substring(0, 4);
			String month = bdate.substring(5, 7);
			String date = bdate.substring(8, 10);
			c.set(Calendar.YEAR, Integer.parseInt(year));
			c.set(Calendar.MONTH, Integer.parseInt(month)-1);
			c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date));
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			list.add(c.getTime());
		}
		if(edate!=null&&!"".equals(edate)){
			sql.append(" and t.opttime<=?"+(index++));
			Calendar c = Calendar.getInstance();
			String year = edate.substring(0, 4);
			String month = edate.substring(5, 7);
			String date = edate.substring(8, 10);
			c.set(Calendar.YEAR, Integer.parseInt(year));
			c.set(Calendar.MONTH, Integer.parseInt(month)-1);
			c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date));
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
			list.add(c.getTime());
		}
		sql.append(" order by t.opttime desc");
		return dao.find(sql.toString(), list.toArray());
	}

	@Override
	public List<Object> findByPage(SapDataCollection sapDataCollection,String className,
			JSONObject json, String optflag, String bdate, String edate,
			int rows, int page, String sidx, String sord) {
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
		if(optflag!=null&&!"".equals(optflag)){
			sql.append(" and t.optflag=?"+(index++));
			list.add(optflag);
		}
		if(bdate!=null&&!"".equals(bdate)){
			sql.append(" and t.opttime>=?"+(index++));
			Calendar c = Calendar.getInstance();
			String year = bdate.substring(0, 4);
			String month = bdate.substring(5, 7);
			String date = bdate.substring(8, 10);
			c.set(Calendar.YEAR, Integer.parseInt(year));
			c.set(Calendar.MONTH, Integer.parseInt(month)-1);
			c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date));
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			list.add(c.getTime());
		}
		if(edate!=null&&!"".equals(edate)){
			sql.append(" and t.opttime<=?"+(index++));
			Calendar c = Calendar.getInstance();
			String year = edate.substring(0, 4);
			String month = edate.substring(5, 7);
			String date = edate.substring(8, 10);
			c.set(Calendar.YEAR, Integer.parseInt(year));
			c.set(Calendar.MONTH, Integer.parseInt(month)-1);
			c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date));
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
			list.add(c.getTime());
		}
		if(sidx!=null&&!"".equals(sidx)){
			sql.append(" order by t."+sidx+" "+sord);
		}else{
			sql.append(" order by t.opttime desc");
		}
		return dao.findByPage(sql.toString(), list.toArray(),page,rows);
	}

	@Override
	public JSONObject findJqgridData(SapDataCollection sapDataCollection,String className,
			JSONObject json, String optflag, String bdate, String edate,
			int rows, int page, String sidx, String sord) {
		// TODO Auto-generated method stub
		List<Object> list = this.findByPage(sapDataCollection,className, json, optflag, bdate, edate,rows,page,sidx,sord);
		if(list!=null){
			long total = this.findTotalNum(sapDataCollection,className, json, optflag, bdate, edate);
			Jqgrid_DataJson jsonData = new Jqgrid_DataJson(page,rows,total,list);
			JSONObject jsonObject = JsonUtils.objectToJSONObject(jsonData, null);
			return jsonObject;
		}
		return null;
	}

	
}
