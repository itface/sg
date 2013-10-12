package com.sapGarden.application.fi.company.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.basetable.model.BaseTableModel;
import com.sapGarden.application.commons.basetable.service.BaseTableService;
import com.sapGarden.application.commons.constants.SjlxTypeName;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.jco.model.CommonSynModel;
import com.sapGarden.application.commons.progress.model.NewProgress;
import com.sapGarden.application.commons.sysdb.service.SysDbService;
import com.sapGarden.application.fi.company.model.Company;
import com.sapGarden.application.fi.company.service.CompanyService;
import com.sapGarden.application.fi.company.service.GetSapDataService;
import com.sapGarden.application.fi.company.service.InitDataService;
@Service
public class InitDataServiceImpl implements InitDataService{

	@Autowired
	private SysDbService sysDbService;
	@Autowired
	private BaseTableService baseTableService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private GetSapDataService getSapDataService;
	@Override
	public boolean validateTable() {
		// TODO Auto-generated method stub
		List<BaseTableModel> list= baseTableService.getBaseTableByTableName(SjlxTypeName.TYPE_COMPANY_TABLE);
		return sysDbService.validateColumnInfo(SjlxTypeName.TYPE_COMPANY_TABLE, list);
	}

	@Override
	public void repairTable() {
		// TODO Auto-generated method stub
		List<BaseTableModel> list= baseTableService.getBaseTableByTableName(SjlxTypeName.TYPE_COMPANY_TABLE);
		sysDbService.repairTable(SjlxTypeName.TYPE_COMPANY_TABLE, list);
	}
	
	private boolean ifRun(long threadid,NewProgress progress){
		if(progress!=null&&progress.isRunstatus()&&progress.getThreadid()==threadid){
			return true;
		}else{
			return false;
		}
	}

	@Override
	@Transactional
	public void init(SapDataCollection sapDataCollection, String user,long threadid,NewProgress progress) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		// TODO Auto-generated method stub
		if(!ifRun(threadid,progress)){
			progress=null;
			return;
		}
		progress.setStarttime((new Date()).getTime());
		companyService.removeAll(sapDataCollection);
		progress.setPercentage(25);
		progress.setPercentage(26);
		if(!ifRun(threadid,progress)){
			progress=null;
			return;
		}
		List<CommonSynModel> list = getSapDataService.getData(sapDataCollection);
		progress.setPercentage(40);
		if(list!=null&&list.size()>0){
			progress.setTotalNum(list.size());
			progress.setTotalEntity(list.size());
			BigDecimal b =  new   BigDecimal((59.0)/list.size());
			double persize =  b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			double countsize =0;
			for(CommonSynModel commonSynModel : list){
				if(!ifRun(threadid,progress)){
					progress=null;
					return;
				}
				Company company = (Company)commonSynModel.getData();
				companyService.add(company);
				//当保存每一条记录所占进度大于百分之一时，取整
				if(persize>1){
					if(progress.getPercentage()+(int)persize<100){
						progress.setPercentage(progress.getPercentage()+(int)persize);
					}
				}else{
					countsize+=persize;
					if(countsize>1){
						if(progress.getPercentage()+(int)countsize<100){
							progress.setPercentage(progress.getPercentage()+(int)countsize);
						}
						countsize=0;
					}
				}
			}
		}
		progress.setPercentage(99);
		progress.setEndtime((new Date()).getTime());
		progress.setRunstatus(false);
		progress.setOver(true);
	}


	

}
