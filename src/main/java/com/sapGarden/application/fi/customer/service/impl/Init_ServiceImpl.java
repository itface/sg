package com.sapGarden.application.fi.customer.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.log.service.CommonServiceWithLog;
import com.sapGarden.application.commons.progress.model.Progress;
import com.sapGarden.application.commons.progress.model.ProgressModel;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.service.Runtime_ColumnInfo_Service;
import com.sapGarden.application.commons.service.commonService.CommonService;
import com.sapGarden.application.fi.customer.model.Kna1;
import com.sapGarden.application.fi.customer.model.Kna1Log;
import com.sapGarden.application.fi.customer.model.Knb1;
import com.sapGarden.application.fi.customer.model.Knb1Log;
import com.sapGarden.application.fi.customer.model.Knvv;
import com.sapGarden.application.fi.customer.model.KnvvLog;
import com.sapGarden.application.fi.customer.model.SapDataModel;
import com.sapGarden.application.fi.customer.service.GetSapDataService;
import com.sapGarden.application.fi.customer.service.Init_Service;
@Service
public class Init_ServiceImpl implements Init_Service{


	private CommonService commonService;
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	private GetSapDataService getSapDataService;
	
	@Autowired
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	@Autowired
	public void setRuntime_ColumnInfo_Service(Runtime_ColumnInfo_Service runtime_ColumnInfo_Service) {
		this.runtime_ColumnInfo_Service = runtime_ColumnInfo_Service;
	}
	@Autowired
	public void setGetSapDataService(GetSapDataService getSapDataService) {
		this.getSapDataService = getSapDataService;
	}
	//@Transactional(propagation=Propagation.REQUIRED)
	public void init(SapDataCollection sapDataCollection,String user) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException{
		Progress progress = ProgressModel.getInstance().getProgress();
		if(progress.isCancel()){
			ProgressModel.getInstance().getProgress().init();
			return;
		}
		long s = System.currentTimeMillis();
		progress.setBeginTime(s);
		progress.setPercent(5);
		this.initDataToLocal(sapDataCollection, user);
		if(progress.isCancel()){
			ProgressModel.getInstance().getProgress().init();
			return;
		}else{
			progress.setPercent(99);
			//ִ��
			long e = System.currentTimeMillis();
			progress.setEndTime(e);
		}
	}
	
	private void initDataToLocal(SapDataCollection sapDataCollection,String user) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException  {
		// TODO Auto-generated method stub
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
		Progress progress = ProgressModel.getInstance().getProgress();
		if(progress.isCancel()){
			return;
		}
		List<RuntimeColumnInfo> knb1RuntimeColumnInfoList = runtime_ColumnInfo_Service.findAll(sapDataCollection,"KNB1");
		List<RuntimeColumnInfo> knvvRuntimeColumnInfoList = runtime_ColumnInfo_Service.findAll(sapDataCollection,"KNVV");
		List<RuntimeColumnInfo> kna1RuntimeColumnInfoList = runtime_ColumnInfo_Service.findAll(sapDataCollection,"KNA1");
		String kna1s = getSapDataService.getColumnNames(kna1RuntimeColumnInfoList);
		String knb1s = getSapDataService.getColumnNames(knb1RuntimeColumnInfoList);
		String knvvs = getSapDataService.getColumnNames(knvvRuntimeColumnInfoList);
		progress.setPercent(10);
		int totalNum=0;
		int perNum=300;
		sapDataModel = getSapDataService.getSapData(sapDataCollection, perNum, kna1RuntimeColumnInfoList, knb1RuntimeColumnInfoList, knvvRuntimeColumnInfoList, kna1s, knb1s, knvvs, O_CONTROL1, O_CONTROL2, O_CONTROL3, O_CONTROL4, O_CONTROL5, O_CONTROL6,"","","","","");
		totalNum=sapDataModel.getTotalNum();
		progress.setTotalNum(sapDataModel.getTotalNum());
		progress.setTotalCustomer(sapDataModel.getTotalCustomer());
		O_CONTROL1 =  sapDataModel.getO_CONTROL1();
		O_CONTROL2 =  sapDataModel.getO_CONTROL2();
		O_CONTROL3 =  sapDataModel.getO_CONTROL3();
		O_CONTROL4 =  sapDataModel.getO_CONTROL4();
		O_CONTROL5 =  sapDataModel.getO_CONTROL5();
		O_CONTROL6 =  sapDataModel.getO_CONTROL6();
		progress.setPercent(20);
		int percentCount = 20;
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
				this.save(kna1List, knb1List, knvvList, user, sapDataCollection);
				percentCount+=80*(((kna1List.size()+knb1List.size()+knvvList.size())+0.0)/totalNum);
				progress.setPercent(percentCount);
				if(!"".equals(sapDataModel.getMark().trim())){
					break;
				}
			}
		}
		/*
		try{
			if(progress.isCancel()){
				return;
			}
			String poolName1 = JcoConnectionPool.getPoolName(sapclient);
			client = JCO.getClient(poolName1);
			myRepository = new Repository(JCO_Repository_NAME, poolName1);
			IFunctionTemplate ift1 = myRepository.getFunctionTemplate(RFC_NAME);
			fun = ift1.getFunction();
			client.execute(fun);
			ParameterList outParams1 = fun.getExportParameterList();
			totalNum=(Integer)outParams1.getValue("TOTAL_LINES");
			int totalCustomer = (Integer)outParams1.getValue("CUSTOM_LINES");
			progress.setTotalNum(totalNum);
			progress.setTotalCustomer(totalCustomer);
			O_CONTROL1 = outParams1.getValue("O_CONTROL1");
			O_CONTROL2 = outParams1.getValue("O_CONTROL2");
			O_CONTROL3 = outParams1.getValue("O_CONTROL3");
			O_CONTROL4 = outParams1.getValue("O_CONTROL4");
			O_CONTROL5 = outParams1.getValue("O_CONTROL5");
			O_CONTROL6 = outParams1.getValue("O_CONTROL6");
		}finally{
			if(client!=null){
				JcoConnectionPool.closeConnection(client);
			}
		}
		progress.setPercent(20);
		int percentCount = 20;
		if(totalNum>0){
			while(maxLoop<100){
				try {
					if(progress.isCancel()){
						return;
					}
					maxLoop++;
					String poolName = JcoConnectionPool.getPoolName(sapclient);
					client = JCO.getClient(poolName);
					myRepository = new Repository(JCO_Repository_NAME, poolName);
					IFunctionTemplate ift = myRepository.getFunctionTemplate(RFC_NAME);
					if(ift!=null){
						fun = ift.getFunction();
						ParameterList inParam = fun.getImportParameterList();
						//���ô������
						inParam.setValue(perNum, "MAX_LINES");
						inParam.setValue(kna1s, "I_FIELDS_KNA1");
						inParam.setValue(knb1s, "I_FIELDS_KNB1");
						inParam.setValue(knvvs, "I_FIELDS_KNVV");
						inParam.setValue(O_CONTROL1, "I_CONTROL1");
						inParam.setValue(O_CONTROL2, "I_CONTROL2");
						inParam.setValue(O_CONTROL3, "I_CONTROL3");
						inParam.setValue(O_CONTROL4, "I_CONTROL4");
						inParam.setValue(O_CONTROL5, "I_CONTROL5");
						inParam.setValue(O_CONTROL6, "I_CONTROL6");
						client.execute(fun);
						ParameterList outParams = fun.getExportParameterList();
						String mark = outParams.getValue("MARK")==null?"":(String)outParams.getValue("MARK");

						O_CONTROL1 = outParams.getValue("O_CONTROL1");
						O_CONTROL2 = outParams.getValue("O_CONTROL2");
						O_CONTROL3 = outParams.getValue("O_CONTROL3");
						O_CONTROL4 = outParams.getValue("O_CONTROL4");
						O_CONTROL5 = outParams.getValue("O_CONTROL5");
						O_CONTROL6 = outParams.getValue("O_CONTROL6");
						JCO.Table i_kna1 = fun.getTableParameterList().getTable("I_KNA1");
						kna1List = new LinkedList<Kna1>();
						for(int i=0;i<i_kna1.getNumRows();i++){
							i_kna1.setRow(i);
							Class modelClass = Kna1.class;
							Kna1 kna1 = new Kna1();
							kna1.setSapclient(sapclient.getId());
							//Field[] fields = modelClass.getDeclaredFields();
							for(RuntimeColumnInfo runtimeColumnInfo : kna1RuntimeColumnInfoList){
								String name = runtimeColumnInfo.getSourceColumn().toUpperCase();
								String fieldName = name;
								Field field = modelClass.getDeclaredField(name.toLowerCase());
								Class fieldType = field.getType();
								if("VSOR_PALHGT".equals(fieldName.toUpperCase())){
									fieldName="/VSO/R_PALHGT";
								}else if("VSOR_PAL_UL".equals(fieldName.toUpperCase())){
									fieldName="/VSO/R_PAL_UL";
								}else if("VSOR_PK_MAT".equals(fieldName.toUpperCase())){
									fieldName="/VSO/R_PK_MAT";
								}else if("VSOR_MATPAL".equals(fieldName.toUpperCase())){
									fieldName="/VSO/R_MATPAL";
								}else if("VSOR_I_NO_LYR".equals(fieldName.toUpperCase())){
									fieldName="/VSO/R_I_NO_LYR";
								}else if("VSOR_ONE_MAT".equals(fieldName.toUpperCase())){
									fieldName="/VSO/R_ONE_MAT";
								}else if("VSOR_ONE_SORT".equals(fieldName.toUpperCase())){
									fieldName="/VSO/R_ONE_SORT";
								}else if("VSOR_ULD_SIDE".equals(fieldName.toUpperCase())){
									fieldName="/VSO/R_ULD_SIDE";
								}else if("VSOR_LOAD_PREF".equals(fieldName.toUpperCase())){
									fieldName="/VSO/R_LOAD_PREF";
								}else if("VSOR_DPOINT".equals(fieldName.toUpperCase())){
									fieldName="/VSO/R_DPOINT";
								}
								
								JCO.Field tableField =i_kna1.getField(fieldName.toUpperCase());
								Object tableFieldValue = tableField.getValue();
								if(tableFieldValue!=null){
									if(tableFieldValue instanceof Date){
										SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
										tableFieldValue = sf.format(Date.class.cast(tableField.getValue()));
									}else if("umsat".equals(fieldName.toLowerCase())||"umsa1".equals(fieldName.toLowerCase())||"/VSO/R_PALHGT".equals(fieldName.toUpperCase())){
										tableFieldValue=Double.parseDouble(tableFieldValue.toString());
									}else{
										tableFieldValue=fieldType.cast(tableFieldValue);
									}
									String methodName = "set"+name.substring(0, 1).toUpperCase()+name.substring(1).toLowerCase();
									Method method = kna1.getClass().getMethod(methodName, new Class[]{fieldType});
									method.invoke(kna1, new Object[]{tableFieldValue});
								}
							}
							kna1List.add(kna1);
						}
						JCO.Table i_knb1 = fun.getTableParameterList().getTable("I_KNB1");	
						knb1List = new LinkedList<Knb1>();
						for(int i=0;i<i_knb1.getNumRows();i++){
							i_knb1.setRow(i);
							Class modelClass = Knb1.class;
							Knb1 knb1 = new Knb1();
							knb1.setSapclient(sapclient.getId());
							for(RuntimeColumnInfo runtimeColumnInfo : knb1RuntimeColumnInfoList){
								String name = runtimeColumnInfo.getSourceColumn().toUpperCase();
								String fieldName = name;
								Field field = modelClass.getDeclaredField(name.toLowerCase());
								Class fieldType = field.getType();
								JCO.Field tableField =i_knb1.getField(fieldName.toUpperCase());
								Object tableFieldValue = tableField.getValue();
								if(tableFieldValue!=null){
									if(tableFieldValue instanceof Date){
										SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
										tableFieldValue = sf.format(Date.class.cast(tableField.getValue()));
									}else if("VLIBB".equals(fieldName.toUpperCase())||"WEBTR".equals(fieldName.toUpperCase())){
										tableFieldValue=Double.parseDouble(tableFieldValue.toString());
									}else if("VRSZL".equals(fieldName.toUpperCase())||"NUMBER".equals(fieldName.toUpperCase())||"KULTG".equals(fieldName.toUpperCase())||"VRSPR".equals(fieldName.toUpperCase())){
										tableFieldValue=Long.parseLong(tableFieldValue.toString());
									}else{
										tableFieldValue=fieldType.cast(tableFieldValue);
									}
									String methodName = "set"+name.substring(0, 1).toUpperCase()+name.substring(1).toLowerCase();
									Method method = knb1.getClass().getMethod(methodName, new Class[]{fieldType});
									method.invoke(knb1, new Object[]{tableFieldValue});
								}
							}
							knb1List.add(knb1);
						}
						JCO.Table i_knvv = fun.getTableParameterList().getTable("I_KNVV");
						knvvList = new LinkedList<Knvv>();	
						for(int i=0;i<i_knvv.getNumRows();i++){
							i_knvv.setRow(i);
							Class modelClass = Knvv.class;
							Knvv knvv = new Knvv();
							knvv.setSapclient(sapclient.getId());
							for(RuntimeColumnInfo runtimeColumnInfo : knvvRuntimeColumnInfoList){
								String name = runtimeColumnInfo.getSourceColumn().toUpperCase();
								String fieldName = name;
								Field field = modelClass.getDeclaredField(name.toLowerCase());
								Class fieldType = field.getType();
								if("BEV1EMLGFORTS".equals(fieldName.toUpperCase())){
									fieldName="/BEV1/EMLGFORTS";
								}else if("BEV1EMLGPFAND".equals(fieldName.toUpperCase())){
									fieldName="/BEV1/EMLGPFAND";
								}
								JCO.Field tableField =i_knvv.getField(fieldName.toUpperCase());
								Object tableFieldValue = tableField.getValue();
								if(tableFieldValue!=null){
									if(tableFieldValue instanceof Date){
										SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
										tableFieldValue = sf.format(Date.class.cast(tableField.getValue()));
									}else if("UNTTO".equals(fieldName.toUpperCase())||"UEBTO".equals(fieldName.toUpperCase())||"KVAWT".equals(fieldName.toUpperCase())){
										tableFieldValue=Double.parseDouble(tableFieldValue.toString());
									}else if("ANTLF".equals(fieldName.toUpperCase())||"PODTG".equals(fieldName.toUpperCase())){
										tableFieldValue=Long.parseLong(tableFieldValue.toString());
									}else{
										tableFieldValue=fieldType.cast(tableFieldValue);
									}
									String methodName = "set"+name.substring(0, 1).toUpperCase()+name.substring(1).toLowerCase();
									Method method = knvv.getClass().getMethod(methodName, new Class[]{fieldType});
									method.invoke(knvv, new Object[]{tableFieldValue});
								}
							}
							knvvList.add(knvv);
						}
						if(!"".equals(mark.trim())){
							break;
						}
					}
				}finally{
					if(client!=null){
						JcoConnectionPool.closeConnection(client);
					}
				}
				this.save(kna1List, knb1List, knvvList, user, sapclient);
				percentCount+=80*(((kna1List.size()+knb1List.size()+knvvList.size())+0.0)/totalNum);
				progress.setPercent(percentCount);
			}
		}
		*/
	}
	//@Transactional(propagation=Propagation.REQUIRED)
	private void save(List<Kna1> kna1List,List<Knb1> knb1List,List<Knvv> knvvList,String user,SapDataCollection sapDataCollection){
		if(kna1List!=null&&kna1List.size()>0){
			//kna1Dao.save(kna1List);
			commonService.saveList(CommonServiceWithLog.OPTTYPE_INIT, false, sapDataCollection.getId(), user, Kna1.class, Kna1Log.class, kna1List);
		}
		if(knb1List!=null&&knb1List.size()>0){
			//knb1Dao.save(knb1List);
			commonService.saveList(CommonServiceWithLog.OPTTYPE_INIT, false, sapDataCollection.getId(), user, Knb1.class, Knb1Log.class, knb1List);
		}
		if(knvvList!=null&&knvvList.size()>0){
			//knvvDao.save(knvvList);
			commonService.saveList(CommonServiceWithLog.OPTTYPE_INIT, false, sapDataCollection.getId(), user, Knvv.class, KnvvLog.class, knvvList);
		}
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeAll(SapDataCollection sapDataCollection) {
		// TODO Auto-generated method stub
		commonService.deleteBySapclient(Kna1.class, sapDataCollection.getId());
		commonService.deleteBySapclient(Knb1.class, sapDataCollection.getId());
		commonService.deleteBySapclient(Knvv.class, sapDataCollection.getId());
	}
}
