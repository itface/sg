package com.sapGarden.application.commons.sysdb.service.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.basetable.model.BaseTableModel;
import com.sapGarden.application.commons.sysdb.dao.SysDbDao;
import com.sapGarden.application.commons.sysdb.service.SysDbService;
@Service
public class SysDbServiceImpl implements SysDbService{

	@Autowired
	private SysDbDao sysDbDao;
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void repairTable(String tableName,List<BaseTableModel> list) {
		// TODO Auto-generated method stub
		List<BaseTableModel> addInitialList = new LinkedList<BaseTableModel>();
		List<BaseTableModel> alterInitialList = new LinkedList<BaseTableModel>();
		this.getInitialList(tableName,list,addInitialList, alterInitialList);
		if(addInitialList!=null&&addInitialList.size()>0){
			String sql = " alter table "+tableName+" add (";
			for(BaseTableModel model : addInitialList){
				String name = model.getColumnname()==null?"":model.getColumnname();
				String type = model.getColumntype()==null?"":model.getColumntype();
				int length = model.getColumnlength();
				String scale = model.getColumnscale()==null?"":model.getColumnscale();
				//小数
				if(!"".equals(scale)){
					sql+=name+" "+type+"("+length+",scale),";
				}else{
					sql+=name+" "+type+"("+length+"),";
				}
			}
			sql=sql.substring(0, sql.lastIndexOf(","))+")";
			sysDbDao.executeAlterTable(sql);
		}
		if(alterInitialList!=null&&alterInitialList.size()>0){
			String sql = " alter table "+tableName+" modify (";
			for(BaseTableModel model : alterInitialList){
				String name = model.getColumnname()==null?"":model.getColumnname();
				String type = model.getColumntype()==null?"":model.getColumntype();
				int length = model.getColumnlength();
				String scale = model.getColumnscale()==null?"":model.getColumnscale();
				//小数
				if(!"".equals(scale)){
					sql+=name+" "+type+"("+length+",scale),";
				}else{
					sql+=name+" "+type+"("+length+"),";
				}
			}
			sql=sql.substring(0, sql.lastIndexOf(","))+")";
			sysDbDao.executeAlterTable(sql);
		}
	}
	private void getInitialList(String tableName,List<BaseTableModel> list,List<BaseTableModel> addInitialList,List<BaseTableModel> alterInitialList){
		String columns = "";
		if(list!=null&&list.size()>0){
			for(BaseTableModel model : list){
				String name = model.getColumnname();
				columns+="'"+name+"',";
			}
			columns=columns.substring(0, columns.lastIndexOf(","));
			List list2 = sysDbDao.getColumnInfo(tableName, columns);
			if(list2==null||list2.size()<1){
				addInitialList=list;
				return;
			}
			for(BaseTableModel model : list){
				String name = model.getColumnname()==null?"":model.getColumnname();
				String type = model.getColumntype()==null?"":model.getColumntype();
				int length = model.getColumnlength();
				String scale = model.getColumnscale()==null?"":model.getColumnscale();
				boolean flag = false;
				Iterator iterator = list2.iterator();
				while( iterator.hasNext() ){
					Object[] arr = (Object[]) iterator.next();
					String name2 = arr[1]==null?"":(String)arr[1];
					if(name.equals(name2.toUpperCase())){
						flag=true;
						String type2 = arr[2]==null?"":(String)arr[2];
						int length2 = arr[3]==null?0:((BigDecimal)arr[3]).intValue();
						int precision = arr[4]==null?0:((BigDecimal)arr[4]).intValue();
						int scale2 = arr[5]==null?0:((BigDecimal)arr[5]).intValue();
						if(!type2.equals(type)){
							alterInitialList.add(model);
							break;
						}else if("VARCHAR2".equals(type)){
							if(length!=length2){
								alterInitialList.add(model);
								break;
							}
						}else if("NUMBER".equals(type)){
							if(length!=precision||!((!"".equals(scale)&&scale.equals(scale2+""))||("".equals(scale)&&scale2==0))){
								alterInitialList.add(model);
								break;
							}
						}
						break;
					}
				}
				if(!flag){
					addInitialList.add(model);
				}
			}
		}
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public boolean validateColumnInfo(String tableName,List<BaseTableModel> list) {
		// TODO Auto-generated method stub
		String columns = "";
		if(list!=null&&list.size()>0){
			for(BaseTableModel model : list){
				String name = model.getColumnname();
				columns+="'"+name+"',";
			}
			columns=columns.substring(0, columns.lastIndexOf(","));
			List list2 = sysDbDao.getColumnInfo(tableName, columns);
			if(list2==null||list2.size()<1){
				return false;
			}
			for(BaseTableModel model : list){
				String name = model.getColumnname()==null?"":model.getColumnname();
				String type = model.getColumntype()==null?"":model.getColumntype();
				int length = model.getColumnlength();
				String scale = model.getColumnscale()==null?"":model.getColumnscale();
				boolean flag = false;
				Iterator iterator = list2.iterator();
				while( iterator.hasNext() ){
					Object[] arr = (Object[]) iterator.next();
					String name2 = arr[1]==null?"":(String)arr[1];
					if(name.equals(name2.toUpperCase())){
						String type2 = arr[2]==null?"":(String)arr[2];
						int length2 = arr[3]==null?0:((BigDecimal)arr[3]).intValue();
						int precision = arr[4]==null?0:((BigDecimal)arr[4]).intValue();
						int scale2 = arr[5]==null?0:((BigDecimal)arr[5]).intValue();
						if(!type2.equals(type)){
							return false;
						}else if("VARCHAR2".equals(type)){
							if(length!=length2){
								return false;
							}
						}else if("NUMBER".equals(type)){
							if(length!=precision||!((!"".equals(scale)&&scale.equals(scale2+""))||("".equals(scale)&&scale2==0))){
								return false;
							}
						}
						flag=true;
						break;
					}
				}
				if(!flag){
					return false;
				}
			}
		}
		return true;
	}

}
