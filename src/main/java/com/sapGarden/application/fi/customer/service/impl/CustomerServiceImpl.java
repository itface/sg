package com.sapGarden.application.fi.customer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Client;
import com.sap.mw.jco.JCO.Function;
import com.sap.mw.jco.JCO.ParameterList;
import com.sap.mw.jco.JCO.Repository;
import com.sap.mw.jco.JCO.Structure;
import com.sapGarden.application.commons.dataCollection.model.ExternalUser;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.dataCollection.service.ExternalUserService;
import com.sapGarden.application.commons.dataCollection.service.SapDataCollectionService;
import com.sapGarden.application.commons.jco.JcoConnectionPool;
import com.sapGarden.application.fi.customer.model.Customer;
import com.sapGarden.application.fi.customer.service.CustomerService;
import com.sapGarden.system.db.DbContextHolder;
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private ExternalUserService externalUserService;
	@Autowired
	private SapDataCollectionService sapDataCollectionService;
	 String rfcFunctionName = "ZKS_OA_LO_CUSTOMER_BAPI";//BAPI名称
	 String importName = "IS_CUST";//输入参数
	 String exportName1 = "EP_KUNNR";//输出参数1(客户编码)
	 String exportName2 = "EP_MESSAGE";//输出参数2(返回消息)
	
	//SAP字段
		private String KTOKD = "KTOKD";//客户帐户组
		private String BUKRS = "BUKRS";//公司代码
		private String VKORG = "VKORG";//销售组织
		private String VTWEG = "VTWEG";//分销渠道
		private String SPART = "SPART";//产品组
		private String KUNNR = "KUNNR";//客户编号1
		private String NAME1 = "NAME1";//名称
		private String NAME2 = "NAME2";//名称2
		private String ANRED = "ANRED";//标题
		private String SORTL = "SORTL";//排序字段
		private String STRAS = "STRAS";//住宅号及街道
		private String PSTLZ = "PSTLZ";//邮政编码
		private String LAND1 = "LAND1";//国家代码
		private String REGIO = "REGIO";//地区(州,省,县)
		private String TELF1 = "TELF1";//第一个电话号
		private String TELF2 = "TELF2";//第二个电话号
		private String TELFX = "TELFX";//传真号
		private String LIFNR = "LIFNR";//供应商或债权人的账号
		private String VBUND = "VBUND";//公司标识
		private String STCEG = "STCEG";//增值税登记号
		private String BANKL = "BANKL";//银行编号
		private String KOINH = "KOINH";//账户持有人姓名
		private String PAFKT = "PAFKT";//功能
		private String NAME3 = "NAME3";//名称
		private String AKONT = "AKONT";//总账中的统驭科目
		private String WAERS = "WAERS";//货币码
		private String IP_JYLX = "IP_JYLX";//禁用类型
		private String PAFK2 = "PAFK2";//功能1
		private String NAME4 = "NAME4";//名称1
		private String PAFK3 = "PAFK3";//功能2
		private String NAME5 = "NAME5";//名称2
		private String ORT01 = "ORT01"; //城市
		private String RKHBH = ""; // SAP返回的客户编号
//	public  void test(){
//		SapDataCollection sapDataCollection = new SapDataCollection();
//		sapDataCollection.setAlias("210(192.168.9.70#00)");
//		sapDataCollection.setSapserverusername("wangrongtao");
//		sapDataCollection.setSapserverpassword("kingsoft@3");
//		sapDataCollection.setSapserverip("192.168.9.70");
//		sapDataCollection.setSapserverclient("210");
//		sapDataCollection.setSapserverinstance("00");
//		String poolName1 = JcoConnectionPool.getPoolName(sapDataCollection);
//		Client client = JCO.getClient(poolName1);
//		Repository myRepository = new Repository("SAPGARDEN", poolName1);
//		IFunctionTemplate ift1 = myRepository.getFunctionTemplate(rfcFunctionName);
//		Function funGetList = ift1.getFunction();
//		ParameterList importPList = funGetList.getImportParameterList();
//		Structure cusStructure = importPList.getStructure(importName);
//		String BUKRSi = "3300"; // 公司代码
//		String AKONTi = "11310000";//总账中的统驭科目
////		String XSFWBHi = "3300/00/00"; // 销售范围编号
////		String AKONTii = "11310000";//总账中的统驭科目
//		String WAERSi = "CNY";//货币
//		String KTOKDi = "ZD02";//客户分类
//		String KUNNRi = "";//新增时，客户编号为空,@getMetaDataVariable(SQXZ) = ZD01
//		//如果子表销售范围不为空
//		//拆分销售范围字段
//		String VKORGi = "3300";//销售组织
//		String VTWEGi = "00";//分销渠道
//		String SPARTi = "00";//产品组
//		
//		String NAME1i = "fghjiko";//客户名称
//		String NAME2i = "abcfd";//客户名称2
//		
//		String ANREDi = "公司";	//称谓
//		String SORTLi = "111";//搜索项1
//		String STRASi = "aadfsa";//住宅号及街道
//		String PSTLZi = "341100";//邮编
//		String LAND1i = "";//国家代码
//		//String REGIOii = "";////地区(州,省,县)
//		String REGIOi = "";//地区(州,省,县)
//		String TELF1i = "";//第一个电话号
//		String TELF2i = "";////第二个电话号
//		String TELFXi = "";//传真号
//		String LIFNRi = "";//供应商或债权人的账号
//		String VBUNDi = "";//公司标识
//		String STCEGi = "";//增值税登记号
//		String YHZHi = "";//银行账户
//		String BANKLi = "";//银行编号
//		String KOINHi = "";//账户持有人姓名
//		String PAFKTi = "";//联系人
//		String NAME3i = "";//系统名称
////		String lxr1 = "";//联系人1
////		String xtmc1 = "";//系统名称1
////		String lxr2 = "";//联系人2
////		String xtmc2 = "";//系统名称2
//		String STRASiCs = "" ; //当住宅长度超过35 ， 把多余的放到此字段里
//		String yy = "";
//		if(!YHZHi.equals("") || !KOINHi.equals("")){
//			yy = YHZHi+"/"+KOINHi;
//		}else{
//			yy = "";
//		}
//		String lxr1i = "";
//		String xtmc1i = "";
//		String lxr2i = "";
//		String xtmc2i = "";
//		
//		cusStructure.setValue(KTOKDi, KTOKD);
//		cusStructure.setValue(BUKRSi, BUKRS);
//		cusStructure.setValue(VKORGi, VKORG);
//		cusStructure.setValue(VTWEGi, VTWEG);
//		cusStructure.setValue(SPARTi, SPART);
//		cusStructure.setValue(KUNNRi, KUNNR);
//		cusStructure.setValue(NAME1i.trim(), NAME1);
//		cusStructure.setValue(NAME2i, NAME2);
//		cusStructure.setValue(ANREDi, ANRED);
//		cusStructure.setValue(SORTLi.trim(), SORTL);
//		cusStructure.setValue(STRASi, STRAS);
//		cusStructure.setValue(STRASiCs,ORT01);
//		cusStructure.setValue(PSTLZi, PSTLZ);
//		cusStructure.setValue(LAND1i, LAND1);
//		cusStructure.setValue(REGIOi, REGIO);
//		cusStructure.setValue(TELF1i, TELF1);
//		cusStructure.setValue(TELF2i, TELF2);
//		cusStructure.setValue(TELFXi, TELFX);
//		cusStructure.setValue(LIFNRi, LIFNR);
//		cusStructure.setValue(VBUNDi, VBUND);
//		cusStructure.setValue(STCEGi, STCEG);
//		cusStructure.setValue(BANKLi, BANKL);
//		cusStructure.setValue(yy, KOINH);
//		cusStructure.setValue(NAME3i, PAFKT);
//		cusStructure.setValue(PAFKTi, NAME3);
//		cusStructure.setValue(AKONTi, AKONT);
//		cusStructure.setValue(WAERSi, WAERS);
//		cusStructure.setValue(xtmc1i, PAFK2);
//		cusStructure.setValue(lxr1i, NAME4);
//		cusStructure.setValue(xtmc2i, PAFK3);
//		cusStructure.setValue(lxr2i, NAME5);
//		importPList.setValue(cusStructure, importName);
//		client.execute(funGetList);
//		RKHBH = funGetList.getExportParameterList().getString(exportName1);
//		String mes = funGetList.getExportParameterList().getString(exportName2);
//		System.out.println(RKHBH+":"+mes);
//	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CustomerServiceImpl serice = new CustomerServiceImpl();
		//serice.test();
		System.out.println(Math.round(-11.5)+","+Math.round(-1.5)+","+Math.round(-1.6)+","+Math.round(1.5));
	}
	@Override
	public String addCustomer(Customer customer,String user,String sapclientAlias) {
		// TODO Auto-generated method stub
		DbContextHolder.setDbType("defaultDataSource");
		List<ExternalUser> list = externalUserService.findByUsernameAndSapclient(user,sapclientAlias);
		String khbh=null;
		if(list!=null&&list.size()>0){
			SapDataCollection sapDataCollection = sapDataCollectionService.findByAlias(sapclientAlias);
			if(sapDataCollection!=null){
				String poolName1 = JcoConnectionPool.getPoolName(sapDataCollection);
				Client client = JCO.getClient(poolName1);
				Repository myRepository = new Repository("SAPGARDEN", poolName1);
				IFunctionTemplate ift1 = myRepository.getFunctionTemplate(rfcFunctionName);
				Function funGetList = ift1.getFunction();
				ParameterList importPList = funGetList.getImportParameterList();
				Structure cusStructure = importPList.getStructure(importName);
				cusStructure.setValue(customer.getKTOKD(), KTOKD);
				cusStructure.setValue(customer.getBUKRS(), BUKRS);
				cusStructure.setValue(customer.getVKORG(), VKORG);
				cusStructure.setValue(customer.getVTWEG(), VTWEG);
				cusStructure.setValue(customer.getSPART(), SPART);
				cusStructure.setValue(customer.getKUNNR(), KUNNR);
				cusStructure.setValue(customer.getNAME1(), NAME1);
				cusStructure.setValue(customer.getNAME2(), NAME2);
				cusStructure.setValue(customer.getANRED(), ANRED);
				cusStructure.setValue(customer.getSORTL(), SORTL);
				cusStructure.setValue(customer.getSTRAS(), STRAS);
				cusStructure.setValue(customer.getORT01(),ORT01);
				cusStructure.setValue(customer.getPSTLZ(), PSTLZ);
				cusStructure.setValue(customer.getLAND1(), LAND1);
				cusStructure.setValue(customer.getREGIO(), REGIO);
				cusStructure.setValue(customer.getTELF1(), TELF1);
				cusStructure.setValue(customer.getTELF2(), TELF2);
				cusStructure.setValue(customer.getTELFX(), TELFX);
				cusStructure.setValue(customer.getLIFNR(), LIFNR);
				cusStructure.setValue(customer.getVBUND(), VBUND);
				cusStructure.setValue(customer.getSTCEG(), STCEG);
				cusStructure.setValue(customer.getBANKL(), BANKL);
				cusStructure.setValue(customer.getKOINH(), KOINH);
				cusStructure.setValue(customer.getPAFKT(), PAFKT);
				cusStructure.setValue(customer.getNAME3(), NAME3);
				cusStructure.setValue(customer.getAKONT(), AKONT);
				cusStructure.setValue(customer.getWAERS(), WAERS);
				cusStructure.setValue(customer.getPAFK2(), PAFK2);
				cusStructure.setValue(customer.getNAME4(), NAME4);
				cusStructure.setValue(customer.getPAFK3(), PAFK3);
				cusStructure.setValue(customer.getNAME5(), NAME5);
				importPList.setValue(cusStructure, importName);
				client.execute(funGetList);
				khbh = funGetList.getExportParameterList().getString(exportName1);
				String mes = funGetList.getExportParameterList().getString(exportName2);
				System.out.println(RKHBH+":"+mes);
			}
		}
		return khbh;
	}
	@Override
	public String updateCustomer(Customer customer,String user,String sapclientAlias) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String delCustomer(Customer customer,String use,String sapclientAlias) {
		// TODO Auto-generated method stub
		return null;
	}

}
