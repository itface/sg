package com.sapGarden.application.fi.company.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.baseDao.BaseDao;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.fi.company.model.CompanyLog;
import com.sapGarden.application.fi.company.service.CompanyLogService;
import com.sapGarden.global.jqgrid.model.Jqgrid_DataJson;
import com.sapGarden.global.json.JsonUtils;
@Service
public class CompanyLogServiceImpl implements CompanyLogService{

	@Autowired
	private BaseDao<CompanyLog> dao;
	@Override
	public long findTotalNum(SapDataCollection sapDataCollection,String companyCode, String optflag, String bdate, String edate) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("select count(*) as nums from CompanyLog t where t.sapclient=?1");
		List list = new ArrayList();
		list.add(sapDataCollection.getId());
		int index =2;
		if(companyCode!=null&&!"".equals(companyCode)){
			sql.append(" and t.comp_code=?"+(index++));
			list.add(companyCode);
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
	public List<CompanyLog> find(SapDataCollection sapDataCollection,String companyCode, String optflag, String bdate, String edate) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("from CompanyLog t where t.sapclient=?1");
		List list = new ArrayList();
		list.add(sapDataCollection.getId());
		int index =2;
		if(companyCode!=null&&!"".equals(companyCode)){
			sql.append(" and t.comp_code=?"+(index++));
			list.add(companyCode);
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
	public JSONObject findJqgridData(SapDataCollection sapDataCollection,String companyCode, String optflag, String bdate, String edate,int rows,int page) {
		// TODO Auto-generated method stub
		List<CompanyLog> list = this.findByPage(sapDataCollection, companyCode, optflag, bdate, edate,rows,page);
		if(list!=null){
			long total = this.findTotalNum(sapDataCollection, companyCode, optflag, bdate, edate);
			Jqgrid_DataJson jsonData = new Jqgrid_DataJson(page,rows,total,list);
			JSONObject jsonObject = JsonUtils.objectToJSONObject(jsonData, null);
			return jsonObject;
		}
		return null;
	}
	@Override
	public List<CompanyLog> findByPage(SapDataCollection sapDataCollection,String companyCode, String optflag, String bdate, String edate,int rows, int page) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("from CompanyLog t where t.sapclient=?1");
		List list = new ArrayList();
		list.add(sapDataCollection.getId());
		int index =2;
		if(companyCode!=null&&!"".equals(companyCode)){
			sql.append(" and t.comp_code=?"+(index++));
			list.add(companyCode);
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
		return dao.findByPage(sql.toString(), list.toArray(),page,rows);
	}
	

}
