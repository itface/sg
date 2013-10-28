package com.sapGarden.application.fi.customer.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.log.service.CommonService;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.service.Runtime_ColumnInfo_Service;
import com.sapGarden.application.commons.service.commonService.TempCommonService;
import com.sapGarden.application.fi.customer.model.Kna1;
import com.sapGarden.application.fi.customer.model.Kna1Compared;
import com.sapGarden.application.fi.customer.model.Kna1Log;
import com.sapGarden.application.fi.customer.model.Knb1;
import com.sapGarden.application.fi.customer.model.Knb1Compared;
import com.sapGarden.application.fi.customer.model.Knb1Log;
import com.sapGarden.application.fi.customer.model.Knvv;
import com.sapGarden.application.fi.customer.model.KnvvCompared;
import com.sapGarden.application.fi.customer.model.KnvvLog;
import com.sapGarden.application.fi.customer.model.SapDataModel;
import com.sapGarden.application.fi.customer.service.CompareDataService;
import com.sapGarden.application.fi.customer.service.GetSapDataService;
import com.sapGarden.global.json.JsonUtils;
@Service
public class CompareDataServiceImpl implements CompareDataService{

	//@Autowired
	//private TempCommonService tempCommonService;
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;
	@Autowired
	private GetSapDataService getSapDataService;
	@Autowired
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;

	private int totalSapNum;
	private int totalSapKna1Num;
	private int totalSapKnb1Num;
	private int totalSapKnvvNum;
	private long totalGardenNum;
	private long totalGardenKna1Num;
	private long totalGardenKnb1Num;
	private long totalGardenKnvvNum;

	private int onlySapKna1Num;
	private int onlySapKnb1Num;
	private int onlySapKnvvNum;
	
	private int onlyGardenKna1Num;
	private int onlyGardenKnb1Num;
	private int onlyGardenKnvvNum;
	private int differenceNum;
	private int differenceKna1Num;
	private int differenceKnb1Num;
	private int differenceKnvvNum;
	private int sameNum;
	private int sameKna1Num;
	private int sameKnb1Num;
	private int sameKnvvNum;
	
	private boolean checkInitial(SapDataCollection sapDataCollection){
		if(commonService.findTotalNum(sapDataCollection, "Kna1", null)>0){
			return true;
		}else if(commonService.findTotalNum(sapDataCollection, "Knb1", null)>0){
			return true;
		}else if(commonService.findTotalNum(sapDataCollection, "Knvv", null)>0){
			return true;
		}else{
			return false;
		}
	}

	//@Transactional(propagation=Propagation.REQUIRED)
	public void initComaredData(SapDataCollection sapDataCollection){
		commonService.removeAll(sapDataCollection, "Kna1Compared");
		commonService.removeAll(sapDataCollection, "Knb1Compared");
		commonService.removeAll(sapDataCollection, "KnvvCompared");
	}
	//@Transactional(propagation=Propagation.REQUIRED)
	public void initGardenFlag(long sapclient){
		commonService.executeUpdateSql("update Kna1 t set t.garden_flag=0 where t.sapclient=?", new Object[]{sapclient});
		commonService.executeUpdateSql("update Knb1 t set t.garden_flag=0 where t.sapclient=?", new Object[]{sapclient});
		commonService.executeUpdateSql("update Knvv t set t.garden_flag=0 where t.sapclient=?", new Object[]{sapclient});
	}
	public void findGardenTotalNum(SapDataCollection sapDataCollection){
		totalGardenKna1Num = commonService.findTotalNum(sapDataCollection, "Kna1", null);
		totalGardenKnb1Num = commonService.findTotalNum(sapDataCollection, "Knb1", null);
		totalGardenKnvvNum = commonService.findTotalNum(sapDataCollection, "Knvv", null);
		totalGardenNum=totalGardenKna1Num+totalGardenKnb1Num+totalGardenKnvvNum;
	}
	@Override
	public JSONObject compareSapAndGarden(SapDataCollection sapDataCollection, String user,String kunnr,String bukrs,String vkorg,String vtweg,String spart)
			throws InvocationTargetException, IllegalAccessException,
			NoSuchMethodException, NoSuchFieldException {
		// TODO Auto-generated method stub
		totalSapNum=0;
		totalSapKna1Num=0;
		totalSapKnb1Num=0;
		totalSapKnvvNum=0;
		totalGardenNum=0;
		totalGardenKna1Num=0;
		totalGardenKnb1Num=0;
		totalGardenKnvvNum=0;
		onlySapKna1Num=0;
		onlySapKnb1Num=0;
		onlySapKnvvNum=0;
		onlyGardenKna1Num=0;
		onlyGardenKnb1Num=0;
		onlyGardenKnvvNum=0;
		differenceNum=0;
		differenceKna1Num=0;
		differenceKnb1Num=0;
		differenceKnvvNum=0;
		sameNum=0;
		sameKna1Num=0;
		sameKnb1Num=0;
		sameKnvvNum=0;
		if(!this.checkInitial(sapDataCollection)){
			//return;
		}
		this.initGardenFlag(sapDataCollection.getId());
		this.initComaredData(sapDataCollection);
		this.doCompare(sapDataCollection, user,kunnr,bukrs,vkorg,vtweg,spart);
		this.findGardenTotalNum(sapDataCollection);
		JSONObject json = new JSONObject();
		json.put("totalSapNum", totalSapNum);
		json.put("totalSapKna1Num", totalSapKna1Num);
		json.put("totalSapKnb1Num", totalSapKnb1Num);
		json.put("totalSapKnvvNum", totalSapKnvvNum);
		json.put("totalGardenNum", totalGardenNum);
		json.put("totalGardenKna1Num", totalGardenKna1Num);
		json.put("totalGardenKnb1Num", totalGardenKnb1Num);
		json.put("totalGardenKnvvNum", totalGardenKnvvNum);
		json.put("onlySapKna1Num", onlySapKna1Num);
		json.put("onlySapKnb1Num", onlySapKnb1Num);
		json.put("onlySapKnvvNum", onlySapKnvvNum);
		json.put("onlyGardenKna1Num", onlyGardenKna1Num);
		json.put("onlyGardenKnb1Num", onlyGardenKnb1Num);
		json.put("onlyGardenKnvvNum", onlyGardenKnvvNum);
		json.put("differenceNum", differenceNum);
		json.put("differenceKna1Num", differenceKna1Num);
		json.put("differenceKnb1Num", differenceKnb1Num);
		json.put("differenceKnvvNum", differenceKnvvNum);
		json.put("sameNum", sameNum);
		json.put("sameKna1Num", sameKna1Num);
		json.put("sameKnb1Num", sameKnb1Num);
		json.put("sameKnvvNum", sameKnvvNum);
		return json;
	}
	public void doCompare(SapDataCollection sapDataCollection,String user,String kunnr,String bukrs,String vkorg,String vtweg,String spart) throws SecurityException, IllegalArgumentException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		Object O_CONTROL1 = null;
		Object O_CONTROL2 = null;
		Object O_CONTROL3 = null;
		Object O_CONTROL4 = null;
		Object O_CONTROL5 = null;
		Object O_CONTROL6 = null;
		SapDataModel sapDataModel = null;
		int perNum=500;
		int totalNum=0;
		List<RuntimeColumnInfo> kna1RuntimeColumnInfoList = runtime_ColumnInfo_Service.findAll(sapDataCollection,"KNA1");
		List<RuntimeColumnInfo> knb1RuntimeColumnInfoList = runtime_ColumnInfo_Service.findAll(sapDataCollection,"KNB1");
		List<RuntimeColumnInfo> knvvRuntimeColumnInfoList = runtime_ColumnInfo_Service.findAll(sapDataCollection,"KNVV");
		String activeKna1s = getSapDataService.getActiveColumnNames(kna1RuntimeColumnInfoList);
		String activeKnb1s = getSapDataService.getActiveColumnNames(knb1RuntimeColumnInfoList);
		String activeKnvvs = getSapDataService.getActiveColumnNames(knvvRuntimeColumnInfoList);
		String kna1s = getSapDataService.getColumnNames(kna1RuntimeColumnInfoList);
		String knb1s = getSapDataService.getColumnNames(knb1RuntimeColumnInfoList);
		String knvvs = getSapDataService.getColumnNames(knvvRuntimeColumnInfoList);
		sapDataModel = getSapDataService.getSapData(sapDataCollection, perNum, kna1RuntimeColumnInfoList, knb1RuntimeColumnInfoList, knvvRuntimeColumnInfoList, kna1s, knb1s, knvvs, O_CONTROL1, O_CONTROL2, O_CONTROL3, O_CONTROL4, O_CONTROL5, O_CONTROL6,kunnr,bukrs,vkorg,vtweg,spart);
		totalSapNum=sapDataModel.getTotalNum();
		totalNum=sapDataModel.getTotalNum();
		O_CONTROL1 =  sapDataModel.getO_CONTROL1();
		O_CONTROL2 =  sapDataModel.getO_CONTROL2();
		O_CONTROL3 =  sapDataModel.getO_CONTROL3();
		O_CONTROL4 =  sapDataModel.getO_CONTROL4();
		O_CONTROL5 =  sapDataModel.getO_CONTROL5();
		O_CONTROL6 =  sapDataModel.getO_CONTROL6();
		if(totalNum>0){
			while(true){
				sapDataModel = getSapDataService.getSapData(sapDataCollection, perNum, kna1RuntimeColumnInfoList, knb1RuntimeColumnInfoList, knvvRuntimeColumnInfoList, kna1s, knb1s, knvvs, O_CONTROL1, O_CONTROL2, O_CONTROL3, O_CONTROL4, O_CONTROL5, O_CONTROL6,kunnr,bukrs,vkorg,vtweg,spart);
				O_CONTROL1 =  sapDataModel.getO_CONTROL1();
				O_CONTROL2 =  sapDataModel.getO_CONTROL2();
				O_CONTROL3 =  sapDataModel.getO_CONTROL3();
				O_CONTROL4 =  sapDataModel.getO_CONTROL4();
				O_CONTROL5 =  sapDataModel.getO_CONTROL5();
				O_CONTROL6 =  sapDataModel.getO_CONTROL6();
				List<Kna1> kna1List = sapDataModel.getKna1List();
				List<Knb1> knb1List = sapDataModel.getKnb1List();
				List<Knvv> knvvList = sapDataModel.getKnvvList();
				List<Kna1> kna1ListG = null;
				List<Knb1> knb1ListG = null;
				List<Knvv> knvvListG = null;
				if(kna1List!=null&&kna1List.size()>0){
					totalSapKna1Num+=kna1List.size();
					String sql = "from "+Kna1.class.getName()+" t where t.kunnr in ("+sapDataModel.getKna1Key()+") and t.sapclient='"+sapDataCollection.getId()+"'";
					kna1ListG = commonService.executeQuerySql(sql, null);
					commonService.executeUpdateSql("update "+Kna1.class.getName()+" t set t.garden_flag=1 where t.kunnr in ("+sapDataModel.getKna1Key()+") and t.sapclient='"+sapDataCollection.getId()+"'",null);
				}
				if(knb1List!=null&&knb1List.size()>0){
					totalSapKnb1Num+=knb1List.size();
					String sql = "from "+Knb1.class.getName()+" t where t.datakey in ("+sapDataModel.getKnb1Key()+") and t.sapclient='"+sapDataCollection.getId()+"'";
					knb1ListG = commonService.executeQuerySql(sql, null);
					commonService.executeUpdateSql("update "+Knb1.class.getName()+" t set t.garden_flag=1 where t.datakey in ("+sapDataModel.getKnb1Key()+") and t.sapclient='"+sapDataCollection.getId()+"'",null);
				}
				if(knvvList!=null&&knvvList.size()>0){
					totalSapKnvvNum+=knvvList.size();
					String sql = "from "+Knvv.class.getName()+" t where t.datakey in ("+sapDataModel.getKnvvKey()+") and t.sapclient='"+sapDataCollection.getId()+"'";
					knvvListG = commonService.executeQuerySql(sql, null);
					sql="update "+Knvv.class.getName()+" t set t.garden_flag=1 where t.datakey in ("+sapDataModel.getKnvvKey()+") and t.sapclient='"+sapDataCollection.getId()+"'";
					commonService.executeUpdateSql(sql,null);
				}
				List<Kna1Compared> kna1CompareDataModelList = new LinkedList<Kna1Compared>();
				if(kna1List!=null&&kna1List.size()>0){
					if(kna1ListG!=null&&kna1ListG.size()>0){
						for(Kna1 sKna1 : kna1List){
							boolean flag = true;
							for(Kna1 gKna1 : kna1ListG){
								if(sKna1.getKunnr().equals(gKna1.getKunnr())){
									flag=false;
									Field[] fields = Kna1.class.getDeclaredFields();
									for(int j=0;j<fields.length;j++){
										String name = fields[j].getName();
										if(activeKna1s.indexOf("["+name+"]")>=0){
											Class fieldType = fields[j].getType();
											String getMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
											Method getMethod = Kna1.class.getMethod(getMethodName, new Class[]{});
											Object svalue = getMethod.invoke(sKna1, new Object[]{});
											Object gvalue = getMethod.invoke(gKna1, new Object[]{});
											if(fieldType==String.class){
												svalue=(svalue==null?"":svalue);
												gvalue=(gvalue==null?"":gvalue);
												if(!gvalue.equals(svalue)){
													differenceNum+=1;
													differenceKna1Num+=1;
													Kna1Compared log = new Kna1Compared(sapDataCollection.getId(),sKna1,gKna1);
													kna1CompareDataModelList.add(log);
													break;
												}else{
													sameNum+=1;
													sameKna1Num+=1;
												}
											}else{
												svalue=svalue+"";
												gvalue=gvalue+"";
												if(!gvalue.equals(svalue)){
													differenceNum+=1;
													differenceKna1Num+=1;
													Kna1Compared log = new Kna1Compared(sapDataCollection.getId(),sKna1,gKna1);
													kna1CompareDataModelList.add(log);
													break;
												}else{
													sameNum+=1;
													sameKna1Num+=1;
												}
											}
										}
									}
									kna1ListG.remove(gKna1);
									break;
								}
							}
							if(flag){
								differenceNum+=1;
								differenceKna1Num+=1;
								onlySapKna1Num+=1;
								Kna1Compared log = new Kna1Compared(sapDataCollection.getId(),sKna1,null);
								kna1CompareDataModelList.add(log);
							}
						}
						for(Kna1 gKna1 : kna1ListG){
							differenceNum+=1;
							differenceKna1Num+=1;
							onlyGardenKna1Num+=1;
							Kna1Compared log = new Kna1Compared(sapDataCollection.getId(),null,gKna1);
							kna1CompareDataModelList.add(log);
						}
					}else{
						for(Kna1 sKna1 : kna1List){
							differenceNum+=1;
							differenceKna1Num+=1;
							onlySapKna1Num+=1;
							Kna1Compared log = new Kna1Compared(sapDataCollection.getId(),sKna1,null);
							kna1CompareDataModelList.add(log);
						}
					}
				}else{
					if(kna1ListG!=null&&kna1ListG.size()>0){
						for(Kna1 gKna1 : kna1ListG){
							differenceNum+=1;
							differenceKna1Num+=1;
							onlyGardenKna1Num+=1;
							Kna1Compared log = new Kna1Compared(sapDataCollection.getId(),null,gKna1);
							kna1CompareDataModelList.add(log);
						}
					}
				}
				List<Knb1Compared> knb1CompareDataModelList = new LinkedList<Knb1Compared>();
				if(knb1List!=null&&knb1List.size()>0){
					if(knb1ListG!=null&&knb1ListG.size()>0){
						for(Knb1 sKnb1 : knb1List){
							boolean flag = true;
							for(Knb1 gKnb1 : knb1ListG){
								if(sKnb1.getKunnr().equals(gKnb1.getKunnr())&&sKnb1.getBukrs().equals(gKnb1.getBukrs())){
									flag=false;
									Field[] fields = Knb1.class.getDeclaredFields();
									for(int j=0;j<fields.length;j++){
										String name = fields[j].getName();
										if(activeKnb1s.indexOf("["+name+"]")>=0){
											Class fieldType = fields[j].getType();
											String getMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
											Method getMethod = Knb1.class.getMethod(getMethodName, new Class[]{});
											Object svalue = getMethod.invoke(sKnb1, new Object[]{});
											Object gvalue = getMethod.invoke(gKnb1, new Object[]{});
											if(fieldType==String.class){
												svalue=(svalue==null?"":svalue);
												gvalue=(gvalue==null?"":gvalue);
												if(!gvalue.equals(svalue)){
													differenceNum+=1;
													differenceKnb1Num+=1;
													Knb1Compared log = new Knb1Compared(sapDataCollection.getId(),sKnb1,gKnb1);
													knb1CompareDataModelList.add(log);
													break;
												}else{
													sameNum+=1;
													sameKnb1Num+=1;
												}
											}else{
												svalue=svalue+"";
												gvalue=gvalue+"";
												if(!gvalue.equals(svalue)){
													differenceNum+=1;
													differenceKnb1Num+=1;
													Knb1Compared log = new Knb1Compared(sapDataCollection.getId(),sKnb1,gKnb1);
													knb1CompareDataModelList.add(log);
													break;
												}else{
													sameNum+=1;
													sameKnb1Num+=1;
												}
											}
										}
									}//
									knb1ListG.remove(gKnb1);
									break;
								}
							}
							if(flag){
								differenceNum+=1;
								differenceKnb1Num+=1;
								onlySapKnb1Num+=1;
								Knb1Compared log = new Knb1Compared(sapDataCollection.getId(),sKnb1,null);
								knb1CompareDataModelList.add(log);
							}
						}
						for(Knb1 gKnb1 : knb1ListG){
							differenceNum+=1;
							differenceKnb1Num+=1;
							onlyGardenKnb1Num+=1;
							Knb1Compared log = new Knb1Compared(sapDataCollection.getId(),null,gKnb1);
							knb1CompareDataModelList.add(log);
						}
					}else{
						for(Knb1 sKnb1 : knb1List){
							differenceNum+=1;
							differenceKnb1Num+=1;
							onlySapKnb1Num+=1;
							Knb1Compared log = new Knb1Compared(sapDataCollection.getId(),sKnb1,null);
							knb1CompareDataModelList.add(log);
						}
					}
				}else{
					if(knb1ListG!=null&&knb1ListG.size()>0){
						for(Knb1 gKnb1 : knb1ListG){
							differenceNum+=1;
							differenceKnb1Num+=1;
							onlyGardenKnb1Num+=1;
							Knb1Compared log = new Knb1Compared(sapDataCollection.getId(),null,gKnb1);
							knb1CompareDataModelList.add(log);
						}
					}
				}
				List<KnvvCompared> knvvCompareDataModelList = new LinkedList<KnvvCompared>();
				if(knvvList!=null&&knvvList.size()>0){
					if(knvvListG!=null&&knvvListG.size()>0){
						for(Knvv sKnvv : knvvList){
							boolean flag = true;
							for(Knvv gKnvv : knvvListG){
								String sKunnr = sKnvv.getKunnr()==null?"":sKnvv.getKunnr();
								String sSpart = sKnvv.getSpart()==null?"":sKnvv.getSpart();
								String sVkorg = sKnvv.getVkorg()==null?"":sKnvv.getVkorg();
								String sVtweg = sKnvv.getVtweg()==null?"":sKnvv.getVtweg();
								String gKunnr = gKnvv.getKunnr()==null?"":gKnvv.getKunnr();
								String gSpart = gKnvv.getSpart()==null?"":gKnvv.getSpart();
								String gVkorg = gKnvv.getVkorg()==null?"":gKnvv.getVkorg();
								String gVtweg = gKnvv.getVtweg()==null?"":gKnvv.getVtweg();
								if(sKunnr.equals(gKunnr)&&sSpart.equals(gSpart)&&sVkorg.equals(gVkorg)&&sVtweg.equals(gVtweg)){
									flag=false;
									Field[] fields = Knvv.class.getDeclaredFields();
									for(int j=0;j<fields.length;j++){
										String name = fields[j].getName();
										if(activeKnvvs.indexOf("["+name+"]")>=0){
											Class fieldType = fields[j].getType();
											String getMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
											Method getMethod = Knvv.class.getMethod(getMethodName, new Class[]{});
											Object svalue = getMethod.invoke(sKnvv, new Object[]{});
											Object gvalue = getMethod.invoke(gKnvv, new Object[]{});
											if(fieldType==String.class){
												svalue=(svalue==null?"":svalue);
												gvalue=(gvalue==null?"":gvalue);
												if(!gvalue.equals(svalue)){
													differenceNum+=1;
													differenceKnvvNum+=1;
													KnvvCompared log = new KnvvCompared(sapDataCollection.getId(),sKnvv,gKnvv);
													knvvCompareDataModelList.add(log);
													break;
												}else{
													sameNum+=1;
													sameKnvvNum+=1;
												}
											}else{
												svalue=svalue+"";
												gvalue=gvalue+"";
												if(!gvalue.equals(svalue)){
													differenceNum+=1;
													differenceKnvvNum+=1;
													KnvvCompared log = new KnvvCompared(sapDataCollection.getId(),sKnvv,gKnvv);
													knvvCompareDataModelList.add(log);
													break;
												}else{
													sameNum+=1;
													sameKnvvNum+=1;
												}
											}
										}
									}
									knvvListG.remove(gKnvv);
									break;
								}
							}
							if(flag){
								differenceNum+=1;
								differenceKnvvNum+=1;
								onlySapKnvvNum=0;
								KnvvCompared log = new KnvvCompared(sapDataCollection.getId(),sKnvv,null);
								knvvCompareDataModelList.add(log);
							}
						}
						for(Knvv gKnvv : knvvListG){
							differenceNum+=1;
							differenceKnvvNum+=1;
							onlyGardenKnvvNum=0;
							KnvvCompared log = new KnvvCompared(sapDataCollection.getId(),null,gKnvv);
							knvvCompareDataModelList.add(log);
						}
					}else{
						for(Knvv sKnvv : knvvList){
							differenceNum+=1;
							differenceKnvvNum+=1;
							onlySapKnvvNum=0;
							KnvvCompared log = new KnvvCompared(sapDataCollection.getId(),sKnvv,null);
							knvvCompareDataModelList.add(log);
						}
					}
				}else{
					if(knvvListG!=null&&knvvListG.size()>0){
						for(Knvv gKnvv : knvvListG){
							differenceNum+=1;
							differenceKnvvNum+=1;
							onlyGardenKnvvNum=0;
							KnvvCompared log = new KnvvCompared(sapDataCollection.getId(),null,gKnvv);
							knvvCompareDataModelList.add(log);
						}
					}
				}
				if(kna1CompareDataModelList!=null&&kna1CompareDataModelList.size()>0){
					commonService.addList(kna1CompareDataModelList);
				}
				if(knb1CompareDataModelList!=null&&knb1CompareDataModelList.size()>0){
					commonService.addList(knb1CompareDataModelList);
				}
				if(knvvCompareDataModelList!=null&&knvvCompareDataModelList.size()>0){
					commonService.addList(knvvCompareDataModelList);
				}
//				this.save(kna1List, knb1List, knvvList, user, client);
//				percentCount+=80*(((kna1List.size()+knb1List.size()+knvvList.size())+0.0)/totalNum);
//				progress.setPercent(percentCount);
				if(!"".equals(sapDataModel.getMark().trim())){
					break;
				}
			}
			String kna1Where = "";
			String knb1Where = "";
			String knvvWhere = "";
			if(kunnr!=null&&!"".equals(kunnr)){
				kna1Where+=" and kna1.kunnr='"+kunnr+"'";
				knb1Where+=" and knb1.kunnr='"+kunnr+"'";
				knvvWhere+=" and knvv.kunnr='"+kunnr+"'";
			}
			if(bukrs!=null&&!"".equals(bukrs)){
				knb1Where+=" and knb1.bukrs='"+bukrs+"'";
			}
			//String vkorg,String vtweg,String spart
			if(vkorg!=null&&!"".equals(vkorg)){
				knvvWhere+=" and knvv.vkorg='"+vkorg+"'";
			}
			if(vtweg!=null&&!"".equals(vtweg)){
				knvvWhere+=" and knvv.vtweg='"+vtweg+"'";
			}
			if(spart!=null&&!"".equals(spart)){
				knvvWhere+=" and knvv.spart='"+spart+"'";
			}
			//String where = "select kna1.id as aid,a.bid as bid,a.vid as vid from kna1 inner join (select knb1.id as bid,knvv.id as vid from knb1 inner join knvv on knb1.kunnr=knvv.kunnr "+knb1Where+" "+knvvWhere+")a on 1=1 "+kna1Where+"";
			//String kna1Ids = "select distinct(kna1.id) as aid from kna1 inner join (select knb1.id as bid,knvv.id as vid from knb1 inner join knvv on knb1.kunnr=knvv.kunnr "+knb1Where+" "+knvvWhere+")a on 1=1 "+kna1Where+"";
			String kna1Ids = "select id from "+Kna1.class.getName()+" kna1  where   (kna1.kunnr in(select kunnr from "+Knb1.class.getName()+" knb1 where 1=1 "+knb1Where+") or (select count(kunnr) from "+Knb1.class.getName()+" knb1 where 1=1 "+knb1Where+")<1)  and (kna1.kunnr in(select kunnr from "+Knvv.class.getName()+" knvv where 1=1 "+knvvWhere+") or (select count(kunnr) from "+Knvv.class.getName()+" knvv where 1=1 "+knvvWhere+")<1) "+kna1Where+"";
			String knb1Ids = "select id from "+Knb1.class.getName()+" knb1  where   (knb1.kunnr in(select kunnr from "+Knvv.class.getName()+" knvv where 1=1 "+knvvWhere+") or (select count(kunnr) from "+Knvv.class.getName()+" knvv where 1=1 "+knvvWhere+")<1)  and knb1.kunnr in(select kunnr from "+Kna1.class.getName()+" kna1 where 1=1 "+kna1Where+") "+knb1Where+"";
			String knvvIds = "select id from "+Knvv.class.getName()+" knvv  where   (knvv.kunnr in(select distinct(kunnr) from "+Knb1.class.getName()+" knb1 where 1=1 "+knb1Where+") or (select count(kunnr) from "+Knb1.class.getName()+" knb1 where 1=1 "+knb1Where+")<1)  and knvv.kunnr in(select kunnr from "+Kna1.class.getName()+" kna1 where 1=1 "+kna1Where+") "+knvvWhere+"";
			String sql = "from "+Kna1.class.getName()+" t where t.id in ("+kna1Ids+") and t.garden_flag=0 and t.sapclient='"+sapDataCollection.getId()+"'";
			List<Kna1> kna1ListG = commonService.executeQuerySql(sql, null);
			sql = "from "+Knb1.class.getName()+" t where t.id in ("+knb1Ids+") and t.garden_flag=0 and t.sapclient='"+sapDataCollection.getId()+"'";
			List<Knb1> knb1ListG = commonService.executeQuerySql(sql, null);
			sql = "from "+Knvv.class.getName()+" t where t.id in ("+knvvIds+") and t.garden_flag=0 and t.sapclient='"+sapDataCollection.getId()+"'";
			List<Knvv> knvvListG = commonService.executeQuerySql(sql, null);
			List<Kna1Compared> kna1CompareDataModelList = new LinkedList<Kna1Compared>();
			List<Knb1Compared> knb1CompareDataModelList = new LinkedList<Knb1Compared>();
			List<KnvvCompared> knvvCompareDataModelList = new LinkedList<KnvvCompared>();
			if(kna1ListG!=null&&kna1ListG.size()>0){
				for(Kna1 kna1 : kna1ListG){
					differenceNum+=1;
					differenceKna1Num+=1;
					onlyGardenKna1Num+=1;
					Kna1Compared log = new Kna1Compared(sapDataCollection.getId(),null,kna1);
					kna1CompareDataModelList.add(log);
				}
				commonService.addList(kna1CompareDataModelList);
			}
			if(knb1ListG!=null&&knb1ListG.size()>0){
				for(Knb1 knb1 : knb1ListG){
					differenceNum+=1;
					differenceKnb1Num+=1;
					onlyGardenKnb1Num+=1;
					Knb1Compared log = new Knb1Compared(sapDataCollection.getId(),null,knb1);
					knb1CompareDataModelList.add(log);
				}
				commonService.addList(knb1CompareDataModelList);
			}
			if(knvvListG!=null&&knvvListG.size()>0){
				for(Knvv knvv : knvvListG){
					differenceNum+=1;
					differenceKnvvNum+=1;
					onlyGardenKnvvNum+=1;
					KnvvCompared log = new KnvvCompared(sapDataCollection.getId(),null,knvv);
					knvvCompareDataModelList.add(log);
				}
				commonService.addList(knvvCompareDataModelList);
			}
		}
	}
	@Override
	//@Transactional(propagation=Propagation.REQUIRED)
	public void comparedDataToLocal(SapDataCollection sapDataCollection,String user,String opttype,List<Kna1Compared> kna1List,List<Knb1Compared> knb1List,List<KnvvCompared> knvvList) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if(kna1List!=null){
			for(Kna1Compared kna1Compared : kna1List){
				String skunnr = kna1Compared.getKunnr();
				String gkunnr = kna1Compared.getKunnr_old();
				Kna1 kna1 = new Kna1(kna1Compared);
				kna1.setId(kna1Compared.getOptid());
				kna1.setSapclient(sapDataCollection.getId());
				kna1.setGarden_flag(1);
				if(skunnr==null||"".equals(skunnr)){
					commonService.deleteWithLog(opttype, sapDataCollection.getId(), user, Kna1.class, Kna1Log.class, kna1);
				}else if(gkunnr==null||"".equals(gkunnr)){
					commonService.addWithLog(opttype, sapDataCollection.getId(), user, Kna1.class, Kna1Log.class, kna1);
				}else{
					JSONObject json = new JSONObject();
					json.put("id", kna1Compared.getOptid());
					Kna1 old = (Kna1)commonService.findSingleByCondition(sapDataCollection, "Kna1", json);
					commonService.updateWithLog(opttype, sapDataCollection.getId(), user, Kna1.class, Kna1Log.class, kna1, old);
				}
				commonService.removeById(kna1Compared.getId(), Kna1Compared.class);
			}
		}
		if(knb1List!=null){
			for(Knb1Compared knb1Compared : knb1List){
				String skunnr = knb1Compared.getKunnr();
				String gkunnr = knb1Compared.getKunnr_old();
				String sbukrs = knb1Compared.getBukrs();
				String gbukrs = knb1Compared.getBukrs_old();
				
				Knb1 knb1 = new Knb1(knb1Compared);
				knb1.setId(knb1Compared.getOptid());
				knb1.setSapclient(sapDataCollection.getId());
				knb1.setGarden_flag(1);
				knb1.setDatakey(knb1Compared.getDatakey());
				if(skunnr==null||"".equals(skunnr)||sbukrs==null||"".equals(sbukrs)){
					commonService.deleteWithLog(opttype, sapDataCollection.getId(), user, Knb1.class, Knb1Log.class, knb1);
				}else if(gkunnr==null||"".equals(gkunnr)||gbukrs==null||"".equals(gbukrs)){
					commonService.addWithLog(opttype, sapDataCollection.getId(), user, Knb1.class, Knb1Log.class, knb1);
				}else{
					JSONObject json = new JSONObject();
					json.put("id", knb1Compared.getOptid());
					Knb1 old = (Knb1)commonService.findSingleByCondition(sapDataCollection, "Knb1", json);
					commonService.updateWithLog(opttype, sapDataCollection.getId(), user, Knb1.class, Knb1Log.class, knb1, old);
				}
				commonService.removeById(knb1Compared.getId(), Knb1Compared.class);
			}
		}
		if(knvvList!=null){
			for(KnvvCompared knvvCompared : knvvList){
				String skunnr = knvvCompared.getKunnr();
				String gkunnr = knvvCompared.getKunnr_old();
				String sSpart = knvvCompared.getSpart();
				String gSpart = knvvCompared.getSpart_old();
				String sVkorg = knvvCompared.getVkorg();
				String gVkorg = knvvCompared.getVkorg_old();
				String sVtweg = knvvCompared.getVtweg();
				String gVtweg = knvvCompared.getVtweg_old();
				Knvv knvv = new Knvv(knvvCompared);
				knvv.setId(knvvCompared.getOptid());
				knvv.setSapclient(sapDataCollection.getId());
				knvv.setGarden_flag(1);
				knvv.setDatakey(knvvCompared.getDatakey());
				if(skunnr==null||"".equals(skunnr)||sSpart==null||"".equals(sSpart)||sVkorg==null||"".equals(sVkorg)||sVtweg==null||"".equals(sVtweg)){
					commonService.deleteWithLog(opttype, sapDataCollection.getId(), user, Knvv.class, KnvvLog.class, knvv);
				}else if(gkunnr==null||"".equals(gkunnr)||gSpart==null||"".equals(gSpart)||gVkorg==null||"".equals(gVkorg)||gVtweg==null||"".equals(gVtweg)){
					commonService.addWithLog(opttype, sapDataCollection.getId(), user, Knvv.class, KnvvLog.class, knvv);
				}else{
					JSONObject json = new JSONObject();
					json.put("id", knvvCompared.getOptid());
					Knvv old = (Knvv)commonService.findSingleByCondition(sapDataCollection, "Knvv", json);
					commonService.updateWithLog(opttype, sapDataCollection.getId(), user, Knvv.class, KnvvLog.class, knvv, old);
				}
				commonService.removeById(knvvCompared.getId(), KnvvCompared.class);
			}
		}
	}
	@Override
	public void compareSapAndGardenDataToLocal(SapDataCollection sapDataCollection,String user, String kna1Compareds,String knb1Compareds,String knvvCompareds) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		List<Kna1Compared> kna1List = null;
		List<Knb1Compared> knb1List = null;
		List<KnvvCompared> knvvList = null;
		if(kna1Compareds!=null&&!"".equals(kna1Compareds)){
			JSONObject json = JSONObject.fromObject(kna1Compareds);
			JSONArray jsonArray = json.getJSONArray("list");
			kna1List =JSONArray.toList(jsonArray,Kna1Compared.class);
		}
		if(knb1Compareds!=null&&!"".equals(knb1Compareds)){
			JSONObject json = JSONObject.fromObject(knb1Compareds);
			JSONArray jsonArray = json.getJSONArray("list");
			knb1List =JSONArray.toList(jsonArray,Knb1Compared.class);
		}
		if(knvvCompareds!=null&&!"".equals(knvvCompareds)){
			JSONObject json = JSONObject.fromObject(knvvCompareds);
			JSONArray jsonArray = json.getJSONArray("list");
			knvvList =JSONArray.toList(jsonArray,KnvvCompared.class);
		}
		this.comparedDataToLocal(sapDataCollection, user, CommonService.OPTTYPE_COMPAREDATA, kna1List, knb1List, knvvList);
	}
}
