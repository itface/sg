package com.sapGarden.application.fi.customer.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Client;
import com.sap.mw.jco.JCO.Function;
import com.sap.mw.jco.JCO.ParameterList;
import com.sap.mw.jco.JCO.Repository;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.jco.JcoConnectionPool;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;
import com.sapGarden.application.fi.customer.model.Kna1;
import com.sapGarden.application.fi.customer.model.Knb1;
import com.sapGarden.application.fi.customer.model.Knvv;
import com.sapGarden.application.fi.customer.model.SapDataModel;
import com.sapGarden.application.fi.customer.service.GetSapDataService;
@Service
public class GetSapDataServiceImpl implements GetSapDataService{

	private final String JCO_Repository_NAME = "SAPGARDEN";
	private final String  RFC_NAME = "Z_SG_FI_CUSTOM_MD_OUT";
	public String getActiveColumnNames(List<RuntimeColumnInfo> list){
		StringBuffer sb = new StringBuffer();
		for(RuntimeColumnInfo runtimeColumnInfo : list){
			String name = runtimeColumnInfo.getSourceColumn().toLowerCase();
			sb.append("[").append(name).append("]");
		}
		return sb.toString();
	}
	public String getColumnNames(List<RuntimeColumnInfo> list){
		String s = "";
		if(list!=null&&list.size()>0){
			for(RuntimeColumnInfo runtimeColumnInfo : list){
				String fieldName = runtimeColumnInfo.getSourceColumn().toUpperCase();
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
				}else if("BEV1EMLGFORTS".equals(fieldName.toUpperCase())){
					fieldName="/BEV1/EMLGFORTS";
				}else if("BEV1EMLGPFAND".equals(fieldName.toUpperCase())){
					fieldName="/BEV1/EMLGPFAND";
				}
				s+=fieldName+" ";
			}
			s=s.substring(0, s.lastIndexOf(" "));
		}
		return s;
	}
	@Override
	public SapDataModel getSapData(SapDataCollection sapDataCollection,int perNum,List<RuntimeColumnInfo> kna1RuntimeColumnInfoList,
			List<RuntimeColumnInfo> knb1RuntimeColumnInfoList,
			List<RuntimeColumnInfo> knvvRuntimeColumnInfoList, String kna1s,
			String knb1s, String knvvs,Object O_CONTROL1,Object O_CONTROL2,Object O_CONTROL3,Object O_CONTROL4,Object O_CONTROL5,Object O_CONTROL6,String kunnr,String bukrs,String vkorg,String vtweg,String spart) throws SecurityException, NoSuchFieldException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		SapDataModel sapDataModel = new SapDataModel();
		List kna1List = new LinkedList<Kna1>();
		List knb1List = new LinkedList<Knb1>();
		List knvvList = new LinkedList<Knvv>();
		String kna1Keys="";
		String knb1Keys="";
		String knvvKeys="";
		Client client = null;
		try {
			String poolName1 = JcoConnectionPool.getPoolName(sapDataCollection);
			client = JCO.getClient(poolName1);
			Repository myRepository = new Repository(JCO_Repository_NAME, poolName1);
			IFunctionTemplate ift1 = myRepository.getFunctionTemplate(RFC_NAME);
			Function fun = ift1.getFunction();
			ParameterList inParam = fun.getImportParameterList();
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
			inParam.setValue(kunnr, "I_KUNNR");
			inParam.setValue(bukrs, "I_BUKRS");
			inParam.setValue(vkorg, "I_VKORG");
			inParam.setValue(vtweg, "I_VTWEG");
			inParam.setValue(spart, "I_SPART");
			client.execute(fun);
			ParameterList outParams1 = fun.getExportParameterList();
			if(outParams1.getValue("TOTAL_LINES")!=null){
				sapDataModel.setTotalNum((Integer)outParams1.getValue("TOTAL_LINES"));
			}
			if(outParams1.getValue("CUSTOM_LINES")!=null){
				sapDataModel.setTotalCustomer((Integer)outParams1.getValue("CUSTOM_LINES"));
			}
			ParameterList outParams = fun.getExportParameterList();
			String mark = outParams.getValue("MARK")==null?"":(String)outParams.getValue("MARK");
			sapDataModel.setMark(mark);
			sapDataModel.setO_CONTROL1(outParams.getValue("O_CONTROL1"));
			sapDataModel.setO_CONTROL2(outParams.getValue("O_CONTROL2"));
			sapDataModel.setO_CONTROL3(outParams.getValue("O_CONTROL3"));
			sapDataModel.setO_CONTROL4(outParams.getValue("O_CONTROL4"));
			sapDataModel.setO_CONTROL5(outParams.getValue("O_CONTROL5"));
			sapDataModel.setO_CONTROL6(outParams.getValue("O_CONTROL6"));
			JCO.Table i_kna1 = fun.getTableParameterList().getTable("I_KNA1");
			for(int i=0;i<i_kna1.getNumRows();i++){
				i_kna1.setRow(i);
				Class modelClass = Kna1.class;
				Kna1 kna1 = new Kna1();
				kna1.setSapclient(sapDataCollection.getId());
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
						if(fieldName.toUpperCase().equals("KUNNR")){
							kna1Keys+="'"+tableFieldValue+"',";
						}
						String methodName = "set"+name.substring(0, 1).toUpperCase()+name.substring(1).toLowerCase();
						Method method = kna1.getClass().getMethod(methodName, new Class[]{fieldType});
						method.invoke(kna1, new Object[]{tableFieldValue});
					}
				}
				kna1List.add(kna1);
			}
			JCO.Table i_knb1 = fun.getTableParameterList().getTable("I_KNB1");	
			
			for(int i=0;i<i_knb1.getNumRows();i++){
				i_knb1.setRow(i);
				Class modelClass = Knb1.class;
				Knb1 knb1 = new Knb1();
				knb1.setSapclient(sapDataCollection.getId());
				String datakey="";
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
						if(fieldName.toUpperCase().equals("KUNNR")||fieldName.toUpperCase().equals("BUKRS")){
							datakey+=tableFieldValue;
							
						}
						String methodName = "set"+name.substring(0, 1).toUpperCase()+name.substring(1).toLowerCase();
						Method method = knb1.getClass().getMethod(methodName, new Class[]{fieldType});
						method.invoke(knb1, new Object[]{tableFieldValue});
					}
				}
				knb1Keys+="'"+datakey+"',";
				knb1.setDatakey(datakey);
				knb1List.add(knb1);
			}
			JCO.Table i_knvv = fun.getTableParameterList().getTable("I_KNVV");
			for(int i=0;i<i_knvv.getNumRows();i++){
				i_knvv.setRow(i);
				Class modelClass = Knvv.class;
				Knvv knvv = new Knvv();
				knvv.setSapclient(sapDataCollection.getId());
				String datakey="";
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
						if(fieldName.toUpperCase().equals("KUNNR")||fieldName.toUpperCase().equals("SPART")||fieldName.toUpperCase().equals("VKORG")||fieldName.toUpperCase().equals("VTWEG")){
							datakey+=tableFieldValue;
						}
						String methodName = "set"+name.substring(0, 1).toUpperCase()+name.substring(1).toLowerCase();
						Method method = knvv.getClass().getMethod(methodName, new Class[]{fieldType});
						method.invoke(knvv, new Object[]{tableFieldValue});
					}
				}
				knvvKeys+="'"+datakey+"',";
				knvv.setDatakey(datakey);
				knvvList.add(knvv);
			}
		}finally{
			if(client!=null){
				JcoConnectionPool.closeConnection(client);
			}
		}
		if(kna1Keys!=null&&kna1Keys.length()>0){
			kna1Keys=kna1Keys.substring(0, kna1Keys.lastIndexOf(","));
		}
		if(knb1Keys!=null&&knb1Keys.length()>0){
			knb1Keys=knb1Keys.substring(0, knb1Keys.lastIndexOf(","));
		}
		if(knvvKeys!=null&&knvvKeys.length()>0){
			knvvKeys=knvvKeys.substring(0, knvvKeys.lastIndexOf(","));
		}
		sapDataModel.setKna1Key(kna1Keys);
		sapDataModel.setKnb1Key(knb1Keys);
		sapDataModel.setKnvvKey(knvvKeys);
		sapDataModel.setKna1List(kna1List);
		sapDataModel.setKnb1List(knb1List);
		sapDataModel.setKnvvList(knvvList);
		return sapDataModel;
	}

}
