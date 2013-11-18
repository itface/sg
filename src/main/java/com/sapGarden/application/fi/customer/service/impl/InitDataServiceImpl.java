package com.sapGarden.application.fi.customer.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.basetable.model.BaseTableModel;
import com.sapGarden.application.commons.basetable.service.BaseTableService;
import com.sapGarden.application.commons.constants.SjlxTypeName;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.log.service.CommonService;
import com.sapGarden.application.commons.progress.model.NewProgress;
import com.sapGarden.application.commons.progress.service.CommonInitDataService;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.service.Runtime_ColumnInfo_Service;
import com.sapGarden.application.commons.sysdb.service.SysDbService;
import com.sapGarden.application.fi.customer.model.Kna1;
import com.sapGarden.application.fi.customer.model.Knb1;
import com.sapGarden.application.fi.customer.model.Knvv;
import com.sapGarden.application.fi.customer.model.SapDataModel;
import com.sapGarden.application.fi.customer.service.GetSapDataService;
@Service("customerInitDataService")
public class InitDataServiceImpl implements CommonInitDataService{


	@Autowired
	private SysDbService sysDbService;
	@Autowired
	private BaseTableService baseTableService;
	@Autowired
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	@Autowired
	private GetSapDataService getSapDataService;
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;

	@Override
	public boolean validateTable() {
		// TODO Auto-generated method stub
		List<BaseTableModel> kna1list= baseTableService.getBaseTableByTableName(SjlxTypeName.TYPE_CUSTOMER_KNA1);
		List<BaseTableModel> knb1list= baseTableService.getBaseTableByTableName(SjlxTypeName.TYPE_CUSTOMER_KNB1);
		List<BaseTableModel> knvvlist= baseTableService.getBaseTableByTableName(SjlxTypeName.TYPE_CUSTOMER_KNVV);
		return sysDbService.validateColumnInfo(SjlxTypeName.TYPE_CUSTOMER_KNA1, kna1list)&&sysDbService.validateColumnInfo(SjlxTypeName.TYPE_CUSTOMER_KNB1, knb1list)&&sysDbService.validateColumnInfo(SjlxTypeName.TYPE_CUSTOMER_KNVV, knvvlist);
	}

	@Override
	public void repairTable() {
		// TODO Auto-generated method stub
		List<BaseTableModel> kna1list= baseTableService.getBaseTableByTableName(SjlxTypeName.TYPE_CUSTOMER_KNA1);
		List<BaseTableModel> knb1list= baseTableService.getBaseTableByTableName(SjlxTypeName.TYPE_CUSTOMER_KNB1);
		List<BaseTableModel> knvvlist= baseTableService.getBaseTableByTableName(SjlxTypeName.TYPE_CUSTOMER_KNVV);
		sysDbService.repairTable(SjlxTypeName.TYPE_CUSTOMER_KNA1, kna1list);
		sysDbService.repairTable(SjlxTypeName.TYPE_CUSTOMER_KNB1, knb1list);
		sysDbService.repairTable(SjlxTypeName.TYPE_CUSTOMER_KNVV, knvvlist);
	}
	
	private boolean ifRun(long threadid,NewProgress progress){
		if(progress!=null&&progress.isRunstatus()&&progress.getThreadid()==threadid){
			return true;
		}else{
			return false;
		}
	}

	@Override
	//@Transactional
	public void init(SapDataCollection sapDataCollection, String user,long threadid,NewProgress progress) throws SecurityException, IllegalArgumentException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		if(!ifRun(threadid,progress)){
			progress=null;
			return;
		}
		progress.setStarttime((new Date()).getTime());
		progress.setPercentage(20);
		progress.setText("正在清空Garden中的原有的客户数据....");
		commonService.removeAll(sapDataCollection, "Kna1");
		commonService.removeAll(sapDataCollection, "Knb1");
		commonService.removeAll(sapDataCollection, "Knvv");
		progress.setPercentage(25);
		progress.setText("Garden中的原有客户数据清空完毕。");
		if(!ifRun(threadid,progress)){
			progress=null;
			return;
		}
		progress.setPercentage(26);
		progress.setText("正在从SAP（"+sapDataCollection.getSapserverclient()+"）读取数据，初始化Garden中客户数据....");
		List<Kna1> kna1List = null;
		List<Knb1> knb1List = null;
		List<Knvv> knvvList = null;	
		Object O_CONTROL1 = null;
		Object O_CONTROL2 = null;
		Object O_CONTROL3 = null;
		Object O_CONTROL4 = null;
		Object O_CONTROL5 = null;
		Object O_CONTROL6 = null;
		SapDataModel sapDataModel = null;
		List<RuntimeColumnInfo> knb1RuntimeColumnInfoList = runtime_ColumnInfo_Service.findAll(sapDataCollection,"KNB1");
		List<RuntimeColumnInfo> knvvRuntimeColumnInfoList = runtime_ColumnInfo_Service.findAll(sapDataCollection,"KNVV");
		List<RuntimeColumnInfo> kna1RuntimeColumnInfoList = runtime_ColumnInfo_Service.findAll(sapDataCollection,"KNA1");
		String kna1s = getSapDataService.getColumnNames(kna1RuntimeColumnInfoList);
		String knb1s = getSapDataService.getColumnNames(knb1RuntimeColumnInfoList);
		String knvvs = getSapDataService.getColumnNames(knvvRuntimeColumnInfoList);
		progress.setPercentage(27);
		int totalNum=0;
		int perNum=300;
		sapDataModel = getSapDataService.getSapData(sapDataCollection, perNum, kna1RuntimeColumnInfoList, knb1RuntimeColumnInfoList, knvvRuntimeColumnInfoList, kna1s, knb1s, knvvs, O_CONTROL1, O_CONTROL2, O_CONTROL3, O_CONTROL4, O_CONTROL5, O_CONTROL6,"","","","","");
		totalNum=sapDataModel.getTotalNum();
		progress.setTotalNum(sapDataModel.getTotalNum());
		progress.setTotalEntity(sapDataModel.getTotalCustomer());
		O_CONTROL1 =  sapDataModel.getO_CONTROL1();
		O_CONTROL2 =  sapDataModel.getO_CONTROL2();
		O_CONTROL3 =  sapDataModel.getO_CONTROL3();
		O_CONTROL4 =  sapDataModel.getO_CONTROL4();
		O_CONTROL5 =  sapDataModel.getO_CONTROL5();
		O_CONTROL6 =  sapDataModel.getO_CONTROL6();
		progress.setPercentage(30);
		BigDecimal b =  new   BigDecimal(((69.0)/(totalNum/perNum)));
		double persize =  b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		double countsize =0;
		if(totalNum>0){
			while(true){
				if(progress.isCancel()){
					return;
				}
				sapDataModel = getSapDataService.getSapData(sapDataCollection, perNum, kna1RuntimeColumnInfoList, knb1RuntimeColumnInfoList, knvvRuntimeColumnInfoList, kna1s, knb1s, knvvs, O_CONTROL1, O_CONTROL2, O_CONTROL3, O_CONTROL4, O_CONTROL5, O_CONTROL6,"","","","","");
				
				O_CONTROL1 =  sapDataModel.getO_CONTROL1();
				O_CONTROL2 =  sapDataModel.getO_CONTROL2();
				O_CONTROL3 =  sapDataModel.getO_CONTROL3();
				O_CONTROL4 =  sapDataModel.getO_CONTROL4();
				O_CONTROL5 =  sapDataModel.getO_CONTROL5();
				O_CONTROL6 =  sapDataModel.getO_CONTROL6();
				kna1List = sapDataModel.getKna1List();
				knb1List = sapDataModel.getKnb1List();
				knvvList = sapDataModel.getKnvvList();
				int totle = kna1List.size()+knb1List.size()+knvvList.size();
				double getsapdatatime = persize/4;
				//当保存每一条记录所占进度大于百分之一时，取整
				if(getsapdatatime>1){
					if(progress.getPercentage()+(int)getsapdatatime<100){
						progress.setPercentage(progress.getPercentage()+(int)getsapdatatime);
					}
				}
				if(kna1List!=null&&kna1List.size()>0){
					this.save(kna1List);
//					for(Kna1 kna1 : kna1List){
//						commonService.add(kna1);
//					}
				}
				double kna1savetime = (persize-getsapdatatime)*(kna1List.size()*1.0/totle);
				//当保存每一条记录所占进度大于百分之一时，取整
				if(kna1savetime>1){
					if(progress.getPercentage()+(int)kna1savetime<100){
						progress.setPercentage(progress.getPercentage()+(int)kna1savetime);
					}
				}else{
					countsize+=kna1savetime;
					if(countsize>1){
						if(progress.getPercentage()+(int)countsize<100){
							progress.setPercentage(progress.getPercentage()+(int)countsize);
						}
						countsize=0;
					}
				}
				if(progress.isCancel()){
					return;
				}
				if(knb1List!=null&&knb1List.size()>0){
					this.save(knb1List);
//					for(Knb1 knb1 : knb1List){
//						commonService.add(knb1);
//					}
				}
				double knb1savetime = (persize-getsapdatatime)*(knb1List.size()*1.0/totle);
				//当保存每一条记录所占进度大于百分之一时，取整
				if(knb1savetime>1){
					if(progress.getPercentage()+(int)knb1savetime<100){
						progress.setPercentage(progress.getPercentage()+(int)knb1savetime);
					}
				}else{
					countsize+=knb1savetime;
					if(countsize>1){
						if(progress.getPercentage()+(int)countsize<100){
							progress.setPercentage(progress.getPercentage()+(int)countsize);
						}
						countsize=0;
					}
				}
				if(progress.isCancel()){
					return;
				}
				if(knvvList!=null&&knvvList.size()>0){
					this.save(knvvList);
//					for(Knvv knvv : knvvList){
//						commonService.add(knvv);
//					}
				}
				
				double knvvsavetime = (persize-getsapdatatime)*(knvvList.size()*1.0/totle);
				//当保存每一条记录所占进度大于百分之一时，取整
				if(knvvsavetime>1){
					if(progress.getPercentage()+(int)knvvsavetime<100){
						progress.setPercentage(progress.getPercentage()+(int)knvvsavetime);
					}
				}else{
					countsize+=knvvsavetime;
					if(countsize>1){
						if(progress.getPercentage()+(int)countsize<100){
							progress.setPercentage(progress.getPercentage()+(int)countsize);
						}
						countsize=0;
					}
				}
				if(progress.isCancel()){
					return;
				}
				if(!"".equals(sapDataModel.getMark().trim())){
					break;
				}
			}
		}
		progress.setPercentage(99);
		progress.setEndtime((new Date()).getTime());
		progress.setRunstatus(false);
		progress.setOver(true);
	}
	public void save(List list){
		commonService.addList(list);
	}
}
