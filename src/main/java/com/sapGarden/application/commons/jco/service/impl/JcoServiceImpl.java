package com.sapGarden.application.commons.jco.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Client;
import com.sap.mw.jco.JCO.Function;
import com.sap.mw.jco.JCO.ParameterList;
import com.sap.mw.jco.JCO.Repository;
import com.sap.mw.jco.JCO.Table;
import com.sapGarden.application.commons.jco.JcoConnectionPool;
import com.sapGarden.application.commons.jco.model.ExportModel;
import com.sapGarden.application.commons.jco.model.ImportModel;
import com.sapGarden.application.commons.jco.model.JcoModel;
import com.sapGarden.application.commons.jco.model.JcoReturnModel;
import com.sapGarden.application.commons.jco.model.TableExpModel;
import com.sapGarden.application.commons.jco.model.TableImpModel;
import com.sapGarden.application.commons.jco.service.JcoService;
@Service
public class JcoServiceImpl implements JcoService{
	private final String JCO_Repository_NAME = "SAPGARDEN";
	@Override
	public JcoReturnModel callFunction(JcoModel jcoModel) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException{
		// TODO Auto-generated method stub
		JcoReturnModel jcoReturnParamModel = new JcoReturnModel();
		Map<String,Object> tableMap = null;
		Map<String,Object> exportMap = null;
		if(jcoModel!=null){
			String bapi = jcoModel.getRfcName();
			Client client = null;
			Repository myRepository = null;
			Function fun = null;
			String msg = "";
			try {
				String poolName = JcoConnectionPool.getPoolName(jcoModel.getSapDataCollection());
				client = JCO.getClient(poolName);
				myRepository = new Repository(JCO_Repository_NAME, poolName);
				IFunctionTemplate ift = myRepository.getFunctionTemplate(bapi.toUpperCase());
				if(ift!=null){
					fun = ift.getFunction();
					//如果有import参数，设置import参数
					if(jcoModel.getImportModelList()!=null&&jcoModel.getImportModelList().size()>0){
						List<ImportModel> list = jcoModel.getImportModelList();
						ParameterList inParam = fun.getImportParameterList();
						for(ImportModel importStructureModel : list){
							String name = importStructureModel.getPropertyName();
							Object data = importStructureModel.getPropertyData();
							if(data!=null){
								inParam.setValue(data, name.toUpperCase());
							}
						}
					}
					//如果有table参数，设置table参数
					if(jcoModel.getTableImpModelList()!=null&&jcoModel.getTableImpModelList().size()>0){
						List<TableImpModel> list = jcoModel.getTableImpModelList();
						ParameterList tables = fun.getTableParameterList();
						for(TableImpModel tableImpModel : list){
							String tableName = tableImpModel.getTableName();
							List<Object> tableDataList = tableImpModel.getTableData();
							Table t = tables.getTable(tableName.toUpperCase());
							if(tableDataList!=null&&tableDataList.size()>0){
								for(Object obj:tableDataList){
									Field[] fields = obj.getClass().getDeclaredFields();
									t.appendRow();
									for(int j=0;j<fields.length;j++){
										//属性名称
										String fieldName = fields[j].getName();
										if(!fieldName.equals("serialVersionUID")){
											//属性类型
											Class fieldType = fields[j].getType();
											String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
											Method method = obj.getClass().getMethod(methodName, new Class[]{});
											Object value = method.invoke(obj, new Object[]{});
											t.setValue(value, fieldName.toUpperCase());
										}
									}
								}
							}
						}
					}
					//开始调用函数
					client.execute(fun);
					//设置exprot返回值
					if(jcoModel.getExportModelList()!=null&&jcoModel.getExportModelList().size()>0){
						List<ExportModel> list = jcoModel.getExportModelList();
						ParameterList outParams = fun.getExportParameterList();
						exportMap = new HashMap<String,Object>();
						for(ExportModel exportModel : list){
							String propertyName = exportModel.getPropertyName();
							Class exportClass = exportModel.getPropertyClass();
							if(outParams.isStructure(propertyName.toUpperCase())){
								Object newObj = exportClass.newInstance();
								JCO.Structure structure = outParams.getStructure(propertyName.toUpperCase());
								Field[] fields = exportClass.getDeclaredFields();
								for(int j=0;j<fields.length;j++){
									//属性名称
									String fieldName = fields[j].getName();
									if(!fieldName.equals("serialVersionUID")){
										//属性类型
										Class fieldType = fields[j].getType();
										Object structureFieldValue = fieldType.cast(structure.getValue(fieldName.toUpperCase()));
										String methodName = "set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
										Method method = newObj.getClass().getMethod(methodName, new Class[]{fieldType});
										method.invoke(newObj, new Object[]{structureFieldValue});
									}
								}
								exportMap.put(propertyName,newObj);
							}else{
								String s = (String)outParams.getValue(propertyName.toUpperCase());
								exportMap.put(propertyName,s);
							}
						}
					}
					//设置exprot返回值
					if(jcoModel.getTableExpModelList()!=null&&jcoModel.getTableExpModelList().size()>0){
						List<TableExpModel> list = jcoModel.getTableExpModelList();
						tableMap = new HashMap<String,Object>();
						for(TableExpModel tableExpModel : list){
							String tableName = tableExpModel.getTableName();
							Class tableExpClass = tableExpModel.getTableStructureClass();
							List<Object> expList = new LinkedList();
							//根据table名称取table的值
							JCO.Table table = fun.getTableParameterList().getTable(tableName.toUpperCase());
							//循环取出table的每条记录
							for(int i=0;i<table.getNumRows();i++){
								table.setRow(i);
								Object obj = tableExpClass.newInstance();
								//取table结构对象中申明的属性
								Field[] fields = tableExpClass.getDeclaredFields();
								//根据属性名，在table中取出对应的值，并且设置到返回值对象到属性中
								for(int j=0;j<fields.length;j++){
									//属性名称
									String fieldName = fields[j].getName();
									if(!fieldName.equals("serialVersionUID")){
										//属性类型
										Class fieldType = fields[j].getType();
										//根据属性名称在table中取出值对象
										JCO.Field tableField =table.getField(fieldName.toUpperCase());
										//把table中取出的值对象转换成属性类型的值对象
										Object tableFieldValue = fieldType.cast(tableField.getValue());
										//通过反射把table中取出的属性值放到obj中
										String methodName = "set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
										Method method = obj.getClass().getMethod(methodName, new Class[]{fieldType});
										method.invoke(obj, new Object[]{tableFieldValue});
									}
								}
								expList.add(obj);
							}
							tableMap.put(tableName,expList);
						}
					}
					jcoReturnParamModel.setExportMap(exportMap);
					jcoReturnParamModel.setTableMap(tableMap);
				}
			} finally{
				//client.disconnect();
				if(client!=null){
					JcoConnectionPool.closeConnection(client);
				}
			}
		}
		return jcoReturnParamModel;
	}
}
