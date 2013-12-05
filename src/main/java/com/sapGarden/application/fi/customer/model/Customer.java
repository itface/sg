package com.sapGarden.application.fi.customer.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("customer")
public class Customer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4321957909622238697L;
	
	private String KTOKD ;//客户帐户组
	private String BUKRS ;//公司代码
	private String VKORG ;//销售组织
	private String VTWEG ;//分销渠道
	private String SPART ;//产品组
	private String KUNNR ;//客户编号1
	private String NAME1 ;//名称
	private String NAME2 ;//名称2
	private String ANRED ;//标题
	private String SORTL ;//排序字段
	private String STRAS ;//住宅号及街道
	private String PSTLZ ;//邮政编码
	private String LAND1 ;//国家代码
	private String REGIO ;//地区(州,省,县)
	private String TELF1 ;//第一个电话号
	private String TELF2 ;//第二个电话号
	private String TELFX ;//传真号
	private String LIFNR ;//供应商或债权人的账号
	private String VBUND ;//公司标识
	private String STCEG ;//增值税登记号
	private String BANKL ;//银行编号
	private String KOINH ;//账户持有人姓名
	private String PAFKT ;//功能
	private String NAME3 ;//名称
	private String AKONT ;//总账中的统驭科目
	private String WAERS ;//货币码
	private String IP_JYLX ;//禁用类型
	private String PAFK2 ;//功能1
	private String NAME4 ;//名称1
	private String PAFK3 ;//功能2
	private String NAME5 ;//名称2
	private String ORT01 ; //城市
	private String RKHBH ; // SAP返回的客户编号
	
	public String getKTOKD() {
		return KTOKD;
	}
	public void setKTOKD(String kTOKD) {
		KTOKD = kTOKD;
	}
	public String getBUKRS() {
		return BUKRS;
	}
	public void setBUKRS(String bUKRS) {
		BUKRS = bUKRS;
	}
	public String getVKORG() {
		return VKORG;
	}
	public void setVKORG(String vKORG) {
		VKORG = vKORG;
	}
	public String getVTWEG() {
		return VTWEG;
	}
	public void setVTWEG(String vTWEG) {
		VTWEG = vTWEG;
	}
	public String getSPART() {
		return SPART;
	}
	public void setSPART(String sPART) {
		SPART = sPART;
	}
	public String getKUNNR() {
		return KUNNR;
	}
	public void setKUNNR(String kUNNR) {
		KUNNR = kUNNR;
	}
	public String getNAME1() {
		return NAME1;
	}
	public void setNAME1(String nAME1) {
		NAME1 = nAME1;
	}
	public String getNAME2() {
		return NAME2;
	}
	public void setNAME2(String nAME2) {
		NAME2 = nAME2;
	}
	public String getANRED() {
		return ANRED;
	}
	public void setANRED(String aNRED) {
		ANRED = aNRED;
	}
	public String getSORTL() {
		return SORTL;
	}
	public void setSORTL(String sORTL) {
		SORTL = sORTL;
	}
	public String getSTRAS() {
		return STRAS;
	}
	public void setSTRAS(String sTRAS) {
		STRAS = sTRAS;
	}
	public String getPSTLZ() {
		return PSTLZ;
	}
	public void setPSTLZ(String pSTLZ) {
		PSTLZ = pSTLZ;
	}
	public String getLAND1() {
		return LAND1;
	}
	public void setLAND1(String lAND1) {
		LAND1 = lAND1;
	}
	public String getREGIO() {
		return REGIO;
	}
	public void setREGIO(String rEGIO) {
		REGIO = rEGIO;
	}
	public String getTELF1() {
		return TELF1;
	}
	public void setTELF1(String tELF1) {
		TELF1 = tELF1;
	}
	public String getTELF2() {
		return TELF2;
	}
	public void setTELF2(String tELF2) {
		TELF2 = tELF2;
	}
	public String getTELFX() {
		return TELFX;
	}
	public void setTELFX(String tELFX) {
		TELFX = tELFX;
	}
	public String getLIFNR() {
		return LIFNR;
	}
	public void setLIFNR(String lIFNR) {
		LIFNR = lIFNR;
	}
	public String getVBUND() {
		return VBUND;
	}
	public void setVBUND(String vBUND) {
		VBUND = vBUND;
	}
	public String getSTCEG() {
		return STCEG;
	}
	public void setSTCEG(String sTCEG) {
		STCEG = sTCEG;
	}
	public String getBANKL() {
		return BANKL;
	}
	public void setBANKL(String bANKL) {
		BANKL = bANKL;
	}
	public String getKOINH() {
		return KOINH;
	}
	public void setKOINH(String kOINH) {
		KOINH = kOINH;
	}
	public String getPAFKT() {
		return PAFKT;
	}
	public void setPAFKT(String pAFKT) {
		PAFKT = pAFKT;
	}
	public String getNAME3() {
		return NAME3;
	}
	public void setNAME3(String nAME3) {
		NAME3 = nAME3;
	}
	public String getAKONT() {
		return AKONT;
	}
	public void setAKONT(String aKONT) {
		AKONT = aKONT;
	}
	public String getWAERS() {
		return WAERS;
	}
	public void setWAERS(String wAERS) {
		WAERS = wAERS;
	}
	public String getIP_JYLX() {
		return IP_JYLX;
	}
	public void setIP_JYLX(String iP_JYLX) {
		IP_JYLX = iP_JYLX;
	}
	public String getPAFK2() {
		return PAFK2;
	}
	public void setPAFK2(String pAFK2) {
		PAFK2 = pAFK2;
	}
	public String getNAME4() {
		return NAME4;
	}
	public void setNAME4(String nAME4) {
		NAME4 = nAME4;
	}
	public String getPAFK3() {
		return PAFK3;
	}
	public void setPAFK3(String pAFK3) {
		PAFK3 = pAFK3;
	}
	public String getNAME5() {
		return NAME5;
	}
	public void setNAME5(String nAME5) {
		NAME5 = nAME5;
	}
	public String getORT01() {
		return ORT01;
	}
	public void setORT01(String oRT01) {
		ORT01 = oRT01;
	}
	public String getRKHBH() {
		return RKHBH;
	}
	public void setRKHBH(String rKHBH) {
		RKHBH = rKHBH;
	}
	
}
