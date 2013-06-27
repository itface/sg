package com.sapGarden.application.fi.customer.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="knb1")
public class Knb1 implements Serializable{

	private static final long serialVersionUID = 129L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_knb1")
	@SequenceGenerator(name="seq_knb1",sequenceName="seq_knb1",allocationSize=1)
	private long id;
	private long sapclient;
	private int garden_flag=0;
	private String datakey;
	private String mandt;
	private String kunnr;
	private String bukrs;
	private String pernr;
	private String erdat;
	private String ernam;
	private String sperr;
	private String loevm;
	private String zuawa;
	private String busab;
	private String akont;
	private String begru;
	private String knrze;
	private String knrzb;
	private String zamim;
	private String zamiv;
	private String zamir;
	private String zamib;
	private String zamio;
	private String zwels;
	private String xverr;
	private String zahls;
	private String zterm;
	private String wakon;
	private String vzskz;
	private String zindt;
	private String zinrt;
	private String eikto;
	private String zsabe;
	private String kverm;
	private String fdgrv;
	private String vrbkz;
	private double vlibb;
	private long vrszl;
	private long vrspr;
	private String vrsnr;
	private String verdt;
	private String perkz;
	private String xdezv;
	private String xausz;
	private double webtr;
	private String remit;
	private String datlz;
	private String xzver;
	private String togru;
	private long kultg;
	private String hbkid;
	private String xpore;
	private String blnkz;
	private String altkn;
	private String zgrup;
	private String urlid;
	private String mgrup;
	private String lockb;
	private String uzawe;
	private String ekvbd;
	private String sregl;
	private String xedip;
	private String frgrp;
	private String vrsdg;
	private String tlfxs;
	private String intad;
	private String xknzb;
	private String guzte;
	private String gricd;
	private String gridt;
	private String wbrsl;
	private String confs;
	private String updat;
	private String uptim;
	private String nodel;
	private String tlfns;
	private String cession_kz;
	private String gmvkzd;
	public Knb1(){
		
	}
	public Knb1(Knb1Compared knb1Compared) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		if(knb1Compared!=null){
			Class modelClass = Knb1.class;
			Field[] fields = modelClass.getDeclaredFields();
			for(int j=0;j<fields.length;j++){
				String name = fields[j].getName();
				if(!name.equals("serialVersionUID")&&!name.equals("id")&&!name.equals("sapclient")&&!name.equals("garden_flag")){
					Class fieldType = fields[j].getType();
					String getMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
					Method getMethod = Knb1Compared.class.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(knb1Compared, new Object[]{});
					String methodName = "set"+name.substring(0, 1).toUpperCase()+name.substring(1);
					Method method = modelClass.getMethod(methodName, new Class[]{fieldType});
					method.invoke(this, new Object[]{value});
				}
			}
		}
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSapclient() {
		return sapclient;
	}
	public void setSapclient(long sapclient) {
		this.sapclient = sapclient;
	}
	public String getMandt() {
		return mandt;
	}
	public void setMandt(String mandt) {
		this.mandt = mandt;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getBukrs() {
		return bukrs;
	}
	public void setBukrs(String bukrs) {
		this.bukrs = bukrs;
	}
	public String getPernr() {
		return pernr;
	}
	public void setPernr(String pernr) {
		this.pernr = pernr;
	}
	public String getErdat() {
		return erdat;
	}
	public void setErdat(String erdat) {
		this.erdat = erdat;
	}
	public String getErnam() {
		return ernam;
	}
	public void setErnam(String ernam) {
		this.ernam = ernam;
	}
	public String getSperr() {
		return sperr;
	}
	public void setSperr(String sperr) {
		this.sperr = sperr;
	}
	public String getLoevm() {
		return loevm;
	}
	public void setLoevm(String loevm) {
		this.loevm = loevm;
	}
	public String getZuawa() {
		return zuawa;
	}
	public void setZuawa(String zuawa) {
		this.zuawa = zuawa;
	}
	public String getBusab() {
		return busab;
	}
	public void setBusab(String busab) {
		this.busab = busab;
	}
	public String getAkont() {
		return akont;
	}
	public void setAkont(String akont) {
		this.akont = akont;
	}
	public String getBegru() {
		return begru;
	}
	public void setBegru(String begru) {
		this.begru = begru;
	}
	public String getKnrze() {
		return knrze;
	}
	public void setKnrze(String knrze) {
		this.knrze = knrze;
	}
	public String getKnrzb() {
		return knrzb;
	}
	public void setKnrzb(String knrzb) {
		this.knrzb = knrzb;
	}
	public String getZamim() {
		return zamim;
	}
	public void setZamim(String zamim) {
		this.zamim = zamim;
	}
	public String getZamiv() {
		return zamiv;
	}
	public void setZamiv(String zamiv) {
		this.zamiv = zamiv;
	}
	public String getZamir() {
		return zamir;
	}
	public void setZamir(String zamir) {
		this.zamir = zamir;
	}
	public String getZamib() {
		return zamib;
	}
	public void setZamib(String zamib) {
		this.zamib = zamib;
	}
	public String getZamio() {
		return zamio;
	}
	public void setZamio(String zamio) {
		this.zamio = zamio;
	}
	public String getZwels() {
		return zwels;
	}
	public void setZwels(String zwels) {
		this.zwels = zwels;
	}
	public String getXverr() {
		return xverr;
	}
	public void setXverr(String xverr) {
		this.xverr = xverr;
	}
	public String getZahls() {
		return zahls;
	}
	public void setZahls(String zahls) {
		this.zahls = zahls;
	}
	public String getZterm() {
		return zterm;
	}
	public void setZterm(String zterm) {
		this.zterm = zterm;
	}
	public String getWakon() {
		return wakon;
	}
	public void setWakon(String wakon) {
		this.wakon = wakon;
	}
	public String getVzskz() {
		return vzskz;
	}
	public void setVzskz(String vzskz) {
		this.vzskz = vzskz;
	}
	public String getZindt() {
		return zindt;
	}
	public void setZindt(String zindt) {
		this.zindt = zindt;
	}
	public String getZinrt() {
		return zinrt;
	}
	public void setZinrt(String zinrt) {
		this.zinrt = zinrt;
	}
	public String getEikto() {
		return eikto;
	}
	public void setEikto(String eikto) {
		this.eikto = eikto;
	}
	public String getZsabe() {
		return zsabe;
	}
	public void setZsabe(String zsabe) {
		this.zsabe = zsabe;
	}
	public String getKverm() {
		return kverm;
	}
	public void setKverm(String kverm) {
		this.kverm = kverm;
	}
	public String getFdgrv() {
		return fdgrv;
	}
	public void setFdgrv(String fdgrv) {
		this.fdgrv = fdgrv;
	}
	public String getVrbkz() {
		return vrbkz;
	}
	public void setVrbkz(String vrbkz) {
		this.vrbkz = vrbkz;
	}
	public double getVlibb() {
		return vlibb;
	}
	public void setVlibb(double vlibb) {
		this.vlibb = vlibb;
	}
	public long getVrszl() {
		return vrszl;
	}
	public void setVrszl(long vrszl) {
		this.vrszl = vrszl;
	}
	public long getVrspr() {
		return vrspr;
	}
	public void setVrspr(long vrspr) {
		this.vrspr = vrspr;
	}
	public String getVrsnr() {
		return vrsnr;
	}
	public void setVrsnr(String vrsnr) {
		this.vrsnr = vrsnr;
	}
	public String getVerdt() {
		return verdt;
	}
	public void setVerdt(String verdt) {
		this.verdt = verdt;
	}
	public String getPerkz() {
		return perkz;
	}
	public void setPerkz(String perkz) {
		this.perkz = perkz;
	}
	public String getXdezv() {
		return xdezv;
	}
	public void setXdezv(String xdezv) {
		this.xdezv = xdezv;
	}
	public String getXausz() {
		return xausz;
	}
	public void setXausz(String xausz) {
		this.xausz = xausz;
	}
	public double getWebtr() {
		return webtr;
	}
	public void setWebtr(double webtr) {
		this.webtr = webtr;
	}
	public String getRemit() {
		return remit;
	}
	public void setRemit(String remit) {
		this.remit = remit;
	}
	public String getDatlz() {
		return datlz;
	}
	public void setDatlz(String datlz) {
		this.datlz = datlz;
	}
	public String getXzver() {
		return xzver;
	}
	public void setXzver(String xzver) {
		this.xzver = xzver;
	}
	public String getTogru() {
		return togru;
	}
	public void setTogru(String togru) {
		this.togru = togru;
	}
	public long getKultg() {
		return kultg;
	}
	public void setKultg(long kultg) {
		this.kultg = kultg;
	}
	public String getHbkid() {
		return hbkid;
	}
	public void setHbkid(String hbkid) {
		this.hbkid = hbkid;
	}
	public String getXpore() {
		return xpore;
	}
	public void setXpore(String xpore) {
		this.xpore = xpore;
	}
	public String getBlnkz() {
		return blnkz;
	}
	public void setBlnkz(String blnkz) {
		this.blnkz = blnkz;
	}
	public String getAltkn() {
		return altkn;
	}
	public void setAltkn(String altkn) {
		this.altkn = altkn;
	}
	public String getZgrup() {
		return zgrup;
	}
	public void setZgrup(String zgrup) {
		this.zgrup = zgrup;
	}
	public String getUrlid() {
		return urlid;
	}
	public void setUrlid(String urlid) {
		this.urlid = urlid;
	}
	public String getMgrup() {
		return mgrup;
	}
	public void setMgrup(String mgrup) {
		this.mgrup = mgrup;
	}
	public String getLockb() {
		return lockb;
	}
	public void setLockb(String lockb) {
		this.lockb = lockb;
	}
	public String getUzawe() {
		return uzawe;
	}
	public void setUzawe(String uzawe) {
		this.uzawe = uzawe;
	}
	public String getEkvbd() {
		return ekvbd;
	}
	public void setEkvbd(String ekvbd) {
		this.ekvbd = ekvbd;
	}
	public String getSregl() {
		return sregl;
	}
	public void setSregl(String sregl) {
		this.sregl = sregl;
	}
	public String getXedip() {
		return xedip;
	}
	public void setXedip(String xedip) {
		this.xedip = xedip;
	}
	public String getFrgrp() {
		return frgrp;
	}
	public void setFrgrp(String frgrp) {
		this.frgrp = frgrp;
	}
	public String getVrsdg() {
		return vrsdg;
	}
	public void setVrsdg(String vrsdg) {
		this.vrsdg = vrsdg;
	}
	public String getTlfxs() {
		return tlfxs;
	}
	public void setTlfxs(String tlfxs) {
		this.tlfxs = tlfxs;
	}
	public String getIntad() {
		return intad;
	}
	public void setIntad(String intad) {
		this.intad = intad;
	}
	public String getXknzb() {
		return xknzb;
	}
	public void setXknzb(String xknzb) {
		this.xknzb = xknzb;
	}
	public String getGuzte() {
		return guzte;
	}
	public void setGuzte(String guzte) {
		this.guzte = guzte;
	}
	public String getGricd() {
		return gricd;
	}
	public void setGricd(String gricd) {
		this.gricd = gricd;
	}
	public String getGridt() {
		return gridt;
	}
	public void setGridt(String gridt) {
		this.gridt = gridt;
	}
	public String getWbrsl() {
		return wbrsl;
	}
	public void setWbrsl(String wbrsl) {
		this.wbrsl = wbrsl;
	}
	public String getConfs() {
		return confs;
	}
	public void setConfs(String confs) {
		this.confs = confs;
	}
	public String getUpdat() {
		return updat;
	}
	public void setUpdat(String updat) {
		this.updat = updat;
	}
	public String getUptim() {
		return uptim;
	}
	public void setUptim(String uptim) {
		this.uptim = uptim;
	}
	public String getNodel() {
		return nodel;
	}
	public void setNodel(String nodel) {
		this.nodel = nodel;
	}
	public String getTlfns() {
		return tlfns;
	}
	public void setTlfns(String tlfns) {
		this.tlfns = tlfns;
	}
	public String getCession_kz() {
		return cession_kz;
	}
	public void setCession_kz(String cession_kz) {
		this.cession_kz = cession_kz;
	}
	public String getGmvkzd() {
		return gmvkzd;
	}
	public void setGmvkzd(String gmvkzd) {
		this.gmvkzd = gmvkzd;
	}
	public int getGarden_flag() {
		return garden_flag;
	}
	public void setGarden_flag(int garden_flag) {
		this.garden_flag = garden_flag;
	}
	public String getDatakey() {
		return datakey;
	}
	public void setDatakey(String datakey) {
		this.datakey = datakey;
	}

	

}
