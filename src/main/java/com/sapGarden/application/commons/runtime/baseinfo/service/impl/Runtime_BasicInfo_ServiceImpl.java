package com.sapGarden.application.commons.runtime.baseinfo.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.basetable.model.BaseTableModel;
import com.sapGarden.application.commons.basetable.service.BaseTableService;
import com.sapGarden.application.commons.dao.ExtendDao;
import com.sapGarden.application.commons.runtime.baseinfo.model.RuntimeBasicInfo;
import com.sapGarden.application.commons.runtime.baseinfo.service.Runtime_BasicInfo_Service;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.service.Runtime_ColumnInfo_Service;
import com.sapGarden.application.commons.runtime.tableinfo.model.RuntimeTableInfo;
import com.sapGarden.application.commons.runtime.tableinfo.service.Runtime_TableInfo_Service;
import com.sapGarden.system.db.DbContextHolder;
@Service
public class Runtime_BasicInfo_ServiceImpl implements Runtime_BasicInfo_Service{

	@Autowired
	private BaseTableService baseTableService;
	@Autowired
	private Runtime_TableInfo_Service runtime_TableInfo_Service;
	@Autowired
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	@Autowired
	private ExtendDao<RuntimeBasicInfo> extendDao;
	

	@Transactional(propagation=Propagation.REQUIRED)
	public void initcolumnInfo(String[] tableNames,long sapclient){
		if(tableNames!=null&&tableNames.length>0){
			for(int i=0;i<tableNames.length;i++){
				//新增表
				RuntimeTableInfo runtimeTableInfo = new RuntimeTableInfo();
				runtimeTableInfo.setBusinesstype(tableNames[i]);
				runtimeTableInfo.setSapclient(sapclient);
				runtimeTableInfo.setReflectTable(tableNames[i]);
				runtime_TableInfo_Service.deleteByBusinesstypeAndSapclient(tableNames[i], sapclient);
				runtime_TableInfo_Service.addOne(runtimeTableInfo);
				//新增字段
				List<BaseTableModel>  list = baseTableService.getBaseTableByTableName(tableNames[i]);
				if(list!=null&&list.size()>0){
					List<RuntimeColumnInfo> runtimeColumnInfoList = new LinkedList<RuntimeColumnInfo>();
					for(BaseTableModel baseTableModel : list){
						RuntimeColumnInfo runtimeColumnInfo = new RuntimeColumnInfo(baseTableModel,sapclient);
						runtimeColumnInfoList.add(runtimeColumnInfo);
					}
					runtime_ColumnInfo_Service.deleteByTableAndSapclient(tableNames[i], sapclient);
					runtime_ColumnInfo_Service.addList(runtimeColumnInfoList);
				}
			}
		}
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addOne(RuntimeBasicInfo runtimeBasicInfo,String[] tableNames,long sapclient){
		// TODO Auto-generated method stub
		extendDao.persist(runtimeBasicInfo);
		this.initcolumnInfo(tableNames,sapclient);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public RuntimeBasicInfo findOneByBusinesstype(String businesstype,long sapclient){
		// TODO Auto-generated method stub
		RuntimeBasicInfo runtimeBasicInfo=null;
		List<RuntimeBasicInfo> list=extendDao.find("from RuntimeBasicInfo t where t.businesstype=? and t.sapclient=?", new Object[]{businesstype,sapclient});//runtimeBasicInfoDao.findOneByBusinesstypeAndSapclient(businesstype,sapclient);
		if(list!=null&&list.size()>0){
			runtimeBasicInfo=list.get(0);
		}
		return runtimeBasicInfo;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateOne(RuntimeBasicInfo runtimeBasicInfo){
		// TODO Auto-generated method stub
		extendDao.save(runtimeBasicInfo);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateByBusinesstypes(String btypes, long sapclient,String status) {
		// TODO Auto-generated method stub
		if(btypes!=null&&!"".equals(btypes)){
			String sql = "update RuntimeBasicInfo t set t.status=?1 where t.sapclient=?2 and lower(t.businesstype) in (?3)";
			extendDao.executeUpdate(sql, new Object[]{status,sapclient,btypes});
		}
	}

}
