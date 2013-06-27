package com.sapGarden.application.fi.customer.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.constants.LogConstants;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.service.Runtime_ColumnInfo_Service;
import com.sapGarden.application.commons.service.commonService.CommonService;
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
@Service
public class CompareDataServiceImpl implements CompareDataService{

	private CommonService commonService;
	private GetSapDataService getSapDataService;
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	@Autowired
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	@Autowired
	public void setGetSapDataService(GetSapDataService getSapDataService) {
		this.getSapDataService = getSapDataService;
	}
	@Autowired
	public void setRuntime_ColumnInfo_Service(Runtime_ColumnInfo_Service runtime_ColumnInfo_Service) {
		this.runtime_ColumnInfo_Service = runtime_ColumnInfo_Service;
	}
	private boolean checkInitial(long sapclient){
		if(commonService.countBySapclient(Kna1.class, sapclient)>0){
			return true;
		}else if(commonService.countBySapclient(Kna1.class, sapclient)>0){
			return true;
		}else if(commonService.countBySapclient(Kna1.class, sapclient)>0){
			return true;
		}else{
			return false;
		}
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void initComaredData(long sapclient){
		commonService.deleteBySapclient(Kna1Compared.class, sapclient);
		commonService.deleteBySapclient(Knb1Compared.class, sapclient);
		commonService.deleteBySapclient(KnvvCompared.class, sapclient);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void initGardenFlag(long sapclient){
		commonService.updateGardenFlag(Kna1.class,0, sapclient);
		commonService.updateGardenFlag(Knb1.class,0, sapclient);
		commonService.updateGardenFlag(Knvv.class,0, sapclient);
	}
	@Override
	public void compare(SapDataCollection sapDataCollection, String user,String kunnr,String bukrs,String vkorg,String vtweg,String spart)
			throws InvocationTargetException, IllegalAccessException,
			NoSuchMethodException, NoSuchFieldException {
		// TODO Auto-generated method stub
		if(!this.checkInitial(sapDataCollection.getId())){
			return;
		}
		this.initGardenFlag(sapDataCollection.getId());
		this.initComaredData(sapDataCollection.getId());
		this.doCompare(sapDataCollection, user,kunnr,bukrs,vkorg,vtweg,spart);
	}

	@Override
	public String getDataTableHtml(SapDataCollection sapDataCollection, String type)
			throws InvocationTargetException, IllegalAccessException,
			NoSuchMethodException, NoSuchFieldException {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		List list = new LinkedList();
		List<RuntimeColumnInfo> runtimeColumnInfoList = new LinkedList();
		Class clazz = null;
		if(type.equals("kna1")){
			clazz=Kna1Compared.class;
			list = commonService.findBySapclient(Kna1Compared.class, sapDataCollection.getId());
			runtimeColumnInfoList = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection,"KNA1");
		}else if(type.equals("knb1")){
			clazz=Knb1Compared.class;
			list = commonService.findBySapclient(Knb1Compared.class, sapDataCollection.getId());
			runtimeColumnInfoList = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection,"KNB1");
		}else if(type.equals("knvv")){
			clazz=KnvvCompared.class;
			list = commonService.findBySapclient(KnvvCompared.class, sapDataCollection.getId());
			runtimeColumnInfoList = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection,"KNVV");
		}
		for(int i=0;i<list.size();i++){
			Object obj = list.get(i);
			Method getIdMethod = clazz.getMethod("getId", new Class[]{});
			Method getOptidMethod = clazz.getMethod("getOptid", new Class[]{});
			Method getSapkeyflagMethod = clazz.getMethod("getSapkeyflag", new Class[]{});
			String datakey ="";
			if(!"kna1".equals(type)){
				Method getDatakeyflagMethod = clazz.getMethod("getDatakey", new Class[]{});
				datakey = getDatakeyflagMethod.invoke(obj, new Object[]{})==null?"":(String)getDatakeyflagMethod.invoke(obj, new Object[]{});
			}
			long id = (Long)getIdMethod.invoke(obj, new Object[]{});
			long optid = (Long)getOptidMethod.invoke(obj, new Object[]{});
			int sapkeyflag = (Integer)getSapkeyflagMethod.invoke(obj, new Object[]{});
			String zt = "&nbsp;";
			String background = "#FFFFFF";
			if(optid>0&&sapkeyflag<1){
				zt="SAP无,GARDEN有";
			}else if(optid<1&&sapkeyflag>0){
				zt="SAP有,GARDEN无";
			}
			if(optid<1||sapkeyflag<1){
				background="#FF0000";
			}
			sb.append("<tr height='40px' style='background:"+background+"' class='"+type+"Tr'>");
			sb.append(	"<td width='10px' style='border:1px solid #ccc;border-collapse:collapse'>");
			sb.append(	"<input type='checkbox' class='gridCheckbox' value='"+optid+"'/>");
			sb.append("<input type='hidden' name='id' value='"+id+"'/>");
			sb.append("<input type='hidden' name='optid' value='"+optid+"'/>");
			sb.append("<input type='hidden' name='datakey' value='"+datakey+"'/>");
			sb.append(	"</td>");
			sb.append(	"<td  style='border:1px solid #ccc;border-collapse:collapse'>");
			sb.append(	zt);
			sb.append(	"</td>");
			for(RuntimeColumnInfo runtimeColumnInfo : runtimeColumnInfoList){
				String name = runtimeColumnInfo.getSourceColumn().toLowerCase();
				String getMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
				Method getMethod = clazz.getMethod(getMethodName, new Class[]{});
				String oldGetMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1)+"_old";
				Method oldGetMethod = clazz.getMethod(oldGetMethodName, new Class[]{});
				Object value = getMethod.invoke(obj, new Object[]{});
				Object oldValue = oldGetMethod.invoke(obj, new Object[]{});
				String s1 = value==null?"":value.toString();
				String s2 = oldValue==null?"":oldValue.toString();
				String tdBg = "";
				if(optid>0&&sapkeyflag>0){
					if(!s1.equals(s2)){
						tdBg = "background:#FF0000";
					}
				}
				sb.append("<td style='border:1px solid #ccc;border-collapse:collapse;"+tdBg+"'>");
				sb.append("&nbsp;");
				sb.append(s1);
				sb.append("<input type='hidden' name='"+name+"' value='"+s1+"'");
				sb.append("</td>");
				sb.append("<td style='border:1px solid #ccc;border-collapse:collapse;"+tdBg+"'>");
				sb.append("&nbsp;");
				sb.append(s2);
				sb.append("<input type='hidden' name='"+name+"_old"+"' value='"+s2+"'");
				sb.append("</td>");
			}
			sb.append(	"<td  style='border:1px solid #ccc;border-collapse:collapse'>");
			sb.append(	"&nbsp;");
			sb.append(	"</td>");
			sb.append("</tr>");
			sb.append("</tr>");
		}
		return sb.toString();
	}
	@Override
	public void doCompare(SapDataCollection sapDataCollection,String user,String kunnr,String bukrs,String vkorg,String vtweg,String spart) throws SecurityException, IllegalArgumentException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		
		Object O_CONTROL1 = null;
		Object O_CONTROL2 = null;
		Object O_CONTROL3 = null;
		Object O_CONTROL4 = null;
		Object O_CONTROL5 = null;
		Object O_CONTROL6 = null;
		SapDataModel sapDataModel = null;
		int perNum=1000;
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
					String sql = "from "+Kna1.class.getName()+" t where t.kunnr in ("+sapDataModel.getKna1Key()+") and t.sapclient='"+sapDataCollection.getId()+"'";
					kna1ListG = commonService.queryBySql(Kna1.class,sql);
					commonService.updateBySql("update "+Kna1.class.getName()+" t set t.garden_flag=1 where t.kunnr in ("+sapDataModel.getKna1Key()+") and t.sapclient='"+sapDataCollection.getId()+"'");
				}
				if(knb1List!=null&&knb1List.size()>0){
					String sql = "from "+Knb1.class.getName()+" t where t.datakey in ("+sapDataModel.getKnb1Key()+") and t.sapclient='"+sapDataCollection.getId()+"'";
					knb1ListG = commonService.queryBySql(Knb1.class,sql);
					commonService.updateBySql("update "+Knb1.class.getName()+" t set t.garden_flag=1 where t.datakey in ("+sapDataModel.getKnb1Key()+") and t.sapclient='"+sapDataCollection.getId()+"'");
				}
				if(knvvList!=null&&knvvList.size()>0){
					String sql = "from "+Knvv.class.getName()+" t where t.datakey in ("+sapDataModel.getKnvvKey()+") and t.sapclient='"+sapDataCollection.getId()+"'";
					knvvListG = commonService.queryBySql(Knvv.class,sql);
					sql="update "+Knvv.class.getName()+" t set t.garden_flag=1 where t.datakey in ("+sapDataModel.getKnvvKey()+") and t.sapclient='"+sapDataCollection.getId()+"'";
					commonService.updateBySql(sql);
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
													Kna1Compared log = new Kna1Compared(sapDataCollection.getId(),sKna1,gKna1);
													kna1CompareDataModelList.add(log);
													break;
												}
											}else{
												svalue=svalue+"";
												gvalue=gvalue+"";
												if(!gvalue.equals(svalue)){
													Kna1Compared log = new Kna1Compared(sapDataCollection.getId(),sKna1,gKna1);
													kna1CompareDataModelList.add(log);
													break;
												}
											}
										}
									}
									kna1ListG.remove(gKna1);
									break;
								}
							}
							if(flag){
								Kna1Compared log = new Kna1Compared(sapDataCollection.getId(),sKna1,null);
								kna1CompareDataModelList.add(log);
							}
						}
						for(Kna1 gKna1 : kna1ListG){
							Kna1Compared log = new Kna1Compared(sapDataCollection.getId(),null,gKna1);
							kna1CompareDataModelList.add(log);
						}
					}else{
						for(Kna1 sKna1 : kna1List){
							Kna1Compared log = new Kna1Compared(sapDataCollection.getId(),sKna1,null);
							kna1CompareDataModelList.add(log);
						}
					}
				}else{
					if(kna1ListG!=null&&kna1ListG.size()>0){
						for(Kna1 gKna1 : kna1ListG){
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
													Knb1Compared log = new Knb1Compared(sapDataCollection.getId(),sKnb1,gKnb1);
													knb1CompareDataModelList.add(log);
													break;
												}
											}else{
												svalue=svalue+"";
												gvalue=gvalue+"";
												if(!gvalue.equals(svalue)){
													Knb1Compared log = new Knb1Compared(sapDataCollection.getId(),sKnb1,gKnb1);
													knb1CompareDataModelList.add(log);
													break;
												}
											}
										}
									}//
									knb1ListG.remove(gKnb1);
									break;
								}
							}
							if(flag){
								Knb1Compared log = new Knb1Compared(sapDataCollection.getId(),sKnb1,null);
								knb1CompareDataModelList.add(log);
							}
						}
						for(Knb1 gKnb1 : knb1ListG){
							Knb1Compared log = new Knb1Compared(sapDataCollection.getId(),null,gKnb1);
							knb1CompareDataModelList.add(log);
						}
					}else{
						for(Knb1 sKnb1 : knb1List){
							Knb1Compared log = new Knb1Compared(sapDataCollection.getId(),sKnb1,null);
							knb1CompareDataModelList.add(log);
						}
					}
				}else{
					if(knb1ListG!=null&&knb1ListG.size()>0){
						for(Knb1 gKnb1 : knb1ListG){
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
													KnvvCompared log = new KnvvCompared(sapDataCollection.getId(),sKnvv,gKnvv);
													knvvCompareDataModelList.add(log);
													break;
												}
											}else{
												svalue=svalue+"";
												gvalue=gvalue+"";
												if(!gvalue.equals(svalue)){
													KnvvCompared log = new KnvvCompared(sapDataCollection.getId(),sKnvv,gKnvv);
													knvvCompareDataModelList.add(log);
													break;
												}
											}
										}
									}
									knvvListG.remove(gKnvv);
									break;
								}
							}
							if(flag){
								KnvvCompared log = new KnvvCompared(sapDataCollection.getId(),sKnvv,null);
								knvvCompareDataModelList.add(log);
							}
						}
						for(Knvv gKnvv : knvvListG){
							KnvvCompared log = new KnvvCompared(sapDataCollection.getId(),null,gKnvv);
							knvvCompareDataModelList.add(log);
						}
					}else{
						for(Knvv sKnvv : knvvList){
							KnvvCompared log = new KnvvCompared(sapDataCollection.getId(),sKnvv,null);
							knvvCompareDataModelList.add(log);
						}
					}
				}else{
					if(knvvListG!=null&&knvvListG.size()>0){
						for(Knvv gKnvv : knvvListG){
							KnvvCompared log = new KnvvCompared(sapDataCollection.getId(),null,gKnvv);
							knvvCompareDataModelList.add(log);
						}
					}
				}
				if(kna1CompareDataModelList!=null&&kna1CompareDataModelList.size()>0){
					commonService.saveList(null, false, sapDataCollection.getId(), user, null, null, kna1CompareDataModelList);
				}
				if(knb1CompareDataModelList!=null&&knb1CompareDataModelList.size()>0){
					commonService.saveList(null, false, sapDataCollection.getId(), user, null, null, knb1CompareDataModelList);
				}
				if(knvvCompareDataModelList!=null&&knvvCompareDataModelList.size()>0){
					commonService.saveList(null, false, sapDataCollection.getId(), user, null, null, knvvCompareDataModelList);
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
			List<Kna1> kna1ListG = commonService.queryBySql(Kna1.class,sql);
			sql = "from "+Knb1.class.getName()+" t where t.id in ("+knb1Ids+") and t.garden_flag=0 and t.sapclient='"+sapDataCollection.getId()+"'";
			List<Knb1> knb1ListG = commonService.queryBySql(Knb1.class,sql);
			sql = "from "+Knvv.class.getName()+" t where t.id in ("+knvvIds+") and t.garden_flag=0 and t.sapclient='"+sapDataCollection.getId()+"'";
			List<Knvv> knvvListG = commonService.queryBySql(Knvv.class,sql);
			List<Kna1Compared> kna1CompareDataModelList = new LinkedList<Kna1Compared>();
			List<Knb1Compared> knb1CompareDataModelList = new LinkedList<Knb1Compared>();
			List<KnvvCompared> knvvCompareDataModelList = new LinkedList<KnvvCompared>();
			if(kna1ListG!=null&&kna1ListG.size()>0){
				for(Kna1 kna1 : kna1ListG){
					Kna1Compared log = new Kna1Compared(sapDataCollection.getId(),null,kna1);
					kna1CompareDataModelList.add(log);
				}
				commonService.saveList(null, false, sapDataCollection.getId(), user, null, null, kna1CompareDataModelList);
			}
			if(knb1ListG!=null&&knb1ListG.size()>0){
				for(Knb1 knb1 : knb1ListG){
					Knb1Compared log = new Knb1Compared(sapDataCollection.getId(),null,knb1);
					knb1CompareDataModelList.add(log);
				}
				commonService.saveList(null, false, sapDataCollection.getId(), user, null, null, knb1CompareDataModelList);
			}
			if(knvvListG!=null&&knvvListG.size()>0){
				for(Knvv knvv : knvvListG){
					KnvvCompared log = new KnvvCompared(sapDataCollection.getId(),null,knvv);
					knvvCompareDataModelList.add(log);
				}
				commonService.saveList(null, false, sapDataCollection.getId(), user, null, null, knvvCompareDataModelList);
			}
		}
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void comparedDataToLocal(long sapclient,String user,String opttype,List<Kna1Compared> kna1List,List<Knb1Compared> knb1List,List<KnvvCompared> knvvList) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if(kna1List!=null){
			for(Kna1Compared kna1Compared : kna1List){
				String skunnr = kna1Compared.getKunnr();
				String gkunnr = kna1Compared.getKunnr_old();
				if(skunnr==null||"".equals(skunnr)){
					commonService.removeById(opttype, true, sapclient, user, Kna1.class, Kna1Log.class, kna1Compared.getOptid());
				}else if(gkunnr==null||"".equals(gkunnr)){
					Kna1 kna1 = new Kna1(kna1Compared);
					kna1.setSapclient(sapclient);
					kna1.setGarden_flag(1);
					commonService.save(opttype, true, sapclient, user, Kna1.class, Kna1Log.class, kna1);
				}else{
					Kna1 kna1 = new Kna1(kna1Compared);
					kna1.setId(kna1Compared.getOptid());
					kna1.setSapclient(sapclient);
					kna1.setGarden_flag(1);
					commonService.update(opttype, true, sapclient, user, Kna1.class, Kna1Log.class, kna1);
				}
				commonService.removeById(opttype, false, sapclient, user, Kna1Compared.class, null, kna1Compared.getId());
			}
		}
		if(knb1List!=null){
			for(Knb1Compared knb1Compared : knb1List){
				String skunnr = knb1Compared.getKunnr();
				String gkunnr = knb1Compared.getKunnr_old();
				String sbukrs = knb1Compared.getBukrs();
				String gbukrs = knb1Compared.getBukrs_old();
				if(skunnr==null||"".equals(skunnr)||sbukrs==null||"".equals(sbukrs)){
					commonService.removeById(opttype, true, sapclient, user, Knb1.class, Knb1Log.class, knb1Compared.getOptid());
				}else if(gkunnr==null||"".equals(gkunnr)||gbukrs==null||"".equals(gbukrs)){
					Knb1 knb1 = new Knb1(knb1Compared);
					knb1.setSapclient(sapclient);
					knb1.setGarden_flag(1);
					commonService.save(opttype, true, sapclient, user, Knb1.class, Knb1Log.class, knb1);
				}else{
					Knb1 knb1 = new Knb1(knb1Compared);
					knb1.setId(knb1Compared.getOptid());
					knb1.setSapclient(sapclient);
					knb1.setGarden_flag(1);
					commonService.update(opttype, true, sapclient, user, Knb1.class, Knb1Log.class, knb1);
				}
				commonService.removeById(opttype, false, sapclient, user, Knb1Compared.class, null, knb1Compared.getId());
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
				if(skunnr==null||"".equals(skunnr)||sSpart==null||"".equals(sSpart)||sVkorg==null||"".equals(sVkorg)||sVtweg==null||"".equals(sVtweg)){
					commonService.removeById(opttype, true, sapclient, user, Knvv.class, KnvvLog.class, knvvCompared.getOptid());
				}else if(gkunnr==null||"".equals(gkunnr)||gSpart==null||"".equals(gSpart)||gVkorg==null||"".equals(gVkorg)||gVtweg==null||"".equals(gVtweg)){
					Knvv knvv = new Knvv(knvvCompared);
					knvv.setSapclient(sapclient);
					knvv.setGarden_flag(1);
					commonService.save(opttype, true, sapclient, user, Knvv.class, KnvvLog.class, knvv);
				}else{
					Knvv knvv = new Knvv(knvvCompared);
					knvv.setId(knvvCompared.getOptid());
					knvv.setSapclient(sapclient);
					knvv.setGarden_flag(1);
					commonService.update(opttype, true, sapclient, user, Knvv.class, KnvvLog.class, knvv);
				}
				commonService.removeById(opttype, false, sapclient, user, KnvvCompared.class, null, knvvCompared.getId());
			}
		}
	}
	@Override
	public void sapSynToLocal(long sapclient,String user, String kna1Compareds,String knb1Compareds,String knvvCompareds) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
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
		this.comparedDataToLocal(sapclient, user, LogConstants.OPTTYPE_COMPAREDATA, kna1List, knb1List, knvvList);
	}
	@Override
	public String getComparedResult(SapDataCollection sapDataCollection) {
		// TODO Auto-generated method stub
		long kna1 = commonService.countBySapclient(Kna1Compared.class, sapDataCollection.getId());
		long knb1 = commonService.countBySapclient(Knb1Compared.class, sapDataCollection.getId());
		long knvv = commonService.countBySapclient(KnvvCompared.class, sapDataCollection.getId());
		return "客户一般数据有"+kna1+"条有差异，客户公司代码数据有"+knb1+"条有差异，客户销售数据有"+knvv+"条有差异。";
	}
}
