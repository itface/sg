package com.sapGarden.application.fi.customer.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="knb1_compared")
public class Knb1Compared implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3397410354939709192L;
	@Id
//	@TableGenerator(name = "knb1_compared_gen", //该表主键生成策略的名称,被@GeneratedValue.generator引用。
//	                table = "sys_tb_generator",       //表生成策略所持久化的表名。
//	                pkColumnName = "gen_name",    //在持久化的表中，该主键生成策略所对应键值的名称。
//	                valueColumnName = "gen_value", //在持久化的表中， 该主键当前所生成的值，它的值将会随着每次创建而加。
//	                pkColumnValue = "knb1_compared_pk",//在持久化的表中，该生成策略所对应的主键
//	                initialValue = 100,             //默认主键值为50
//	                allocationSize = 1)           //每次主键值增加的大小
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "knb1_compared_gen")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private long sapclient;
	private long optid;
	private int sapkeyflag;
	private String datakey;
	/*
	@Temporal(TemporalType.TIMESTAMP)
	private Date opttime;
	private String optuser;
	private String opt;
	private long optid;
	private String optflag;
	private String opttype;
	private String optmsg;
	private String processstatus;
	*/
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
	private		String		mandt_old	;
	private		String		kunnr_old	;
	private		String		bukrs_old	;
	private		String		pernr_old	;
	private		String		erdat_old	;
	private		String		ernam_old	;
	private		String		sperr_old	;
	private		String		loevm_old	;
	private		String		zuawa_old	;
	private		String		busab_old	;
	private		String		akont_old	;
	private		String		begru_old	;
	private		String		knrze_old	;
	private		String		knrzb_old	;
	private		String		zamim_old	;
	private		String		zamiv_old	;
	private		String		zamir_old	;
	private		String		zamib_old	;
	private		String		zamio_old	;
	private		String		zwels_old	;
	private		String		xverr_old	;
	private		String		zahls_old	;
	private		String		zterm_old	;
	private		String		wakon_old	;
	private		String		vzskz_old	;
	private		String		zindt_old	;
	private		String		zinrt_old	;
	private		String		eikto_old	;
	private		String		zsabe_old	;
	private		String		kverm_old	;
	private		String		fdgrv_old	;
	private		String		vrbkz_old	;
	private		double		vlibb_old	;
	private		long		vrszl_old	;
	private		long		vrspr_old	;
	private		String		vrsnr_old	;
	private		String		verdt_old	;
	private		String		perkz_old	;
	private		String		xdezv_old	;
	private		String		xausz_old	;
	private		double		webtr_old	;
	private		String		remit_old	;
	private		String		datlz_old	;
	private		String		xzver_old	;
	private		String		togru_old	;
	private		long		kultg_old	;
	private		String		hbkid_old	;
	private		String		xpore_old	;
	private		String		blnkz_old	;
	private		String		altkn_old	;
	private		String		zgrup_old	;
	private		String		urlid_old	;
	private		String		mgrup_old	;
	private		String		lockb_old	;
	private		String		uzawe_old	;
	private		String		ekvbd_old	;
	private		String		sregl_old	;
	private		String		xedip_old	;
	private		String		frgrp_old	;
	private		String		vrsdg_old	;
	private		String		tlfxs_old	;
	private		String		intad_old	;
	private		String		xknzb_old	;
	private		String		guzte_old	;
	private		String		gricd_old	;
	private		String		gridt_old	;
	private		String		wbrsl_old	;
	private		String		confs_old	;
	private		String		updat_old	;
	private		String		uptim_old	;
	private		String		nodel_old	;
	private		String		tlfns_old	;
	private		String		cession_kz_old	;
	private		String		gmvkzd_old	;
	public Knb1Compared(){
		
	}
	public Knb1Compared(long sapclient,Knb1 sknb1,Knb1 gknb1) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		this.sapclient=sapclient;
		if(sknb1!=null){
			Class modelClass = sknb1.getClass();
			Field[] fields = modelClass.getDeclaredFields();
			this.sapkeyflag=1;
			for(int j=0;j<fields.length;j++){
				String name = fields[j].getName();
				if(!name.equals("serialVersionUID")&&!name.equals("id")&&!name.equals("sapclient")&&!name.equals("garden_flag")){
					Class fieldType = fields[j].getType();
					String getMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1).toLowerCase();
					Method getMethod = modelClass.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(sknb1, new Object[]{});
					String methodName = "set"+name.substring(0, 1).toUpperCase()+name.substring(1).toLowerCase();
					Method method = Knb1Compared.class.getMethod(methodName, new Class[]{fieldType});
					method.invoke(this, new Object[]{value});
				}
			}
		}
		if(gknb1!=null){
			Class modelClass = gknb1.getClass();
			this.optid=gknb1.getId();
			Field[] fields = modelClass.getDeclaredFields();
			for(int j=0;j<fields.length;j++){
				String name = fields[j].getName();
				if(!name.equals("serialVersionUID")&&!name.equals("id")&&!name.equals("sapclient")&&!name.equals("garden_flag")&&!name.equals("datakey")){
					Class fieldType = fields[j].getType();
					String getMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
					Method getMethod = modelClass.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(gknb1, new Object[]{});
					String methodName = "set"+name.substring(0, 1).toUpperCase()+name.substring(1)+"_old";
					Method method = Knb1Compared.class.getMethod(methodName, new Class[]{fieldType});
					method.invoke(this, new Object[]{value});
				}
			}
		}
	}
	
	public int getSapkeyflag() {
		return sapkeyflag;
	}
	public void setSapkeyflag(int sapkeyflag) {
		this.sapkeyflag = sapkeyflag;
	}
	public long getOptid() {
		return optid;
	}
	public void setOptid(long optid) {
		this.optid = optid;
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
	public String getMandt_old() {
		return mandt_old;
	}
	public void setMandt_old(String mandt_old) {
		this.mandt_old = mandt_old;
	}
	public String getKunnr_old() {
		return kunnr_old;
	}
	public void setKunnr_old(String kunnr_old) {
		this.kunnr_old = kunnr_old;
	}
	public String getBukrs_old() {
		return bukrs_old;
	}
	public void setBukrs_old(String bukrs_old) {
		this.bukrs_old = bukrs_old;
	}
	public String getPernr_old() {
		return pernr_old;
	}
	public void setPernr_old(String pernr_old) {
		this.pernr_old = pernr_old;
	}
	public String getErdat_old() {
		return erdat_old;
	}
	public void setErdat_old(String erdat_old) {
		this.erdat_old = erdat_old;
	}
	public String getErnam_old() {
		return ernam_old;
	}
	public void setErnam_old(String ernam_old) {
		this.ernam_old = ernam_old;
	}
	public String getSperr_old() {
		return sperr_old;
	}
	public void setSperr_old(String sperr_old) {
		this.sperr_old = sperr_old;
	}
	public String getLoevm_old() {
		return loevm_old;
	}
	public void setLoevm_old(String loevm_old) {
		this.loevm_old = loevm_old;
	}
	public String getZuawa_old() {
		return zuawa_old;
	}
	public void setZuawa_old(String zuawa_old) {
		this.zuawa_old = zuawa_old;
	}
	public String getBusab_old() {
		return busab_old;
	}
	public void setBusab_old(String busab_old) {
		this.busab_old = busab_old;
	}
	public String getAkont_old() {
		return akont_old;
	}
	public void setAkont_old(String akont_old) {
		this.akont_old = akont_old;
	}
	public String getBegru_old() {
		return begru_old;
	}
	public void setBegru_old(String begru_old) {
		this.begru_old = begru_old;
	}
	public String getKnrze_old() {
		return knrze_old;
	}
	public void setKnrze_old(String knrze_old) {
		this.knrze_old = knrze_old;
	}
	public String getKnrzb_old() {
		return knrzb_old;
	}
	public void setKnrzb_old(String knrzb_old) {
		this.knrzb_old = knrzb_old;
	}
	public String getZamim_old() {
		return zamim_old;
	}
	public void setZamim_old(String zamim_old) {
		this.zamim_old = zamim_old;
	}
	public String getZamiv_old() {
		return zamiv_old;
	}
	public void setZamiv_old(String zamiv_old) {
		this.zamiv_old = zamiv_old;
	}
	public String getZamir_old() {
		return zamir_old;
	}
	public void setZamir_old(String zamir_old) {
		this.zamir_old = zamir_old;
	}
	public String getZamib_old() {
		return zamib_old;
	}
	public void setZamib_old(String zamib_old) {
		this.zamib_old = zamib_old;
	}
	public String getZamio_old() {
		return zamio_old;
	}
	public void setZamio_old(String zamio_old) {
		this.zamio_old = zamio_old;
	}
	public String getZwels_old() {
		return zwels_old;
	}
	public void setZwels_old(String zwels_old) {
		this.zwels_old = zwels_old;
	}
	public String getXverr_old() {
		return xverr_old;
	}
	public void setXverr_old(String xverr_old) {
		this.xverr_old = xverr_old;
	}
	public String getZahls_old() {
		return zahls_old;
	}
	public void setZahls_old(String zahls_old) {
		this.zahls_old = zahls_old;
	}
	public String getZterm_old() {
		return zterm_old;
	}
	public void setZterm_old(String zterm_old) {
		this.zterm_old = zterm_old;
	}
	public String getWakon_old() {
		return wakon_old;
	}
	public void setWakon_old(String wakon_old) {
		this.wakon_old = wakon_old;
	}
	public String getVzskz_old() {
		return vzskz_old;
	}
	public void setVzskz_old(String vzskz_old) {
		this.vzskz_old = vzskz_old;
	}
	public String getZindt_old() {
		return zindt_old;
	}
	public void setZindt_old(String zindt_old) {
		this.zindt_old = zindt_old;
	}
	public String getZinrt_old() {
		return zinrt_old;
	}
	public void setZinrt_old(String zinrt_old) {
		this.zinrt_old = zinrt_old;
	}
	public String getEikto_old() {
		return eikto_old;
	}
	public void setEikto_old(String eikto_old) {
		this.eikto_old = eikto_old;
	}
	public String getZsabe_old() {
		return zsabe_old;
	}
	public void setZsabe_old(String zsabe_old) {
		this.zsabe_old = zsabe_old;
	}
	public String getKverm_old() {
		return kverm_old;
	}
	public void setKverm_old(String kverm_old) {
		this.kverm_old = kverm_old;
	}
	public String getFdgrv_old() {
		return fdgrv_old;
	}
	public void setFdgrv_old(String fdgrv_old) {
		this.fdgrv_old = fdgrv_old;
	}
	public String getVrbkz_old() {
		return vrbkz_old;
	}
	public void setVrbkz_old(String vrbkz_old) {
		this.vrbkz_old = vrbkz_old;
	}
	public double getVlibb_old() {
		return vlibb_old;
	}
	public void setVlibb_old(double vlibb_old) {
		this.vlibb_old = vlibb_old;
	}
	public long getVrszl_old() {
		return vrszl_old;
	}
	public void setVrszl_old(long vrszl_old) {
		this.vrszl_old = vrszl_old;
	}
	public long getVrspr_old() {
		return vrspr_old;
	}
	public void setVrspr_old(long vrspr_old) {
		this.vrspr_old = vrspr_old;
	}
	public String getVrsnr_old() {
		return vrsnr_old;
	}
	public void setVrsnr_old(String vrsnr_old) {
		this.vrsnr_old = vrsnr_old;
	}
	public String getVerdt_old() {
		return verdt_old;
	}
	public void setVerdt_old(String verdt_old) {
		this.verdt_old = verdt_old;
	}
	public String getPerkz_old() {
		return perkz_old;
	}
	public void setPerkz_old(String perkz_old) {
		this.perkz_old = perkz_old;
	}
	public String getXdezv_old() {
		return xdezv_old;
	}
	public void setXdezv_old(String xdezv_old) {
		this.xdezv_old = xdezv_old;
	}
	public String getXausz_old() {
		return xausz_old;
	}
	public void setXausz_old(String xausz_old) {
		this.xausz_old = xausz_old;
	}
	public double getWebtr_old() {
		return webtr_old;
	}
	public void setWebtr_old(double webtr_old) {
		this.webtr_old = webtr_old;
	}
	public String getRemit_old() {
		return remit_old;
	}
	public void setRemit_old(String remit_old) {
		this.remit_old = remit_old;
	}
	public String getDatlz_old() {
		return datlz_old;
	}
	public void setDatlz_old(String datlz_old) {
		this.datlz_old = datlz_old;
	}
	public String getXzver_old() {
		return xzver_old;
	}
	public void setXzver_old(String xzver_old) {
		this.xzver_old = xzver_old;
	}
	public String getTogru_old() {
		return togru_old;
	}
	public void setTogru_old(String togru_old) {
		this.togru_old = togru_old;
	}
	public long getKultg_old() {
		return kultg_old;
	}
	public void setKultg_old(long kultg_old) {
		this.kultg_old = kultg_old;
	}
	public String getHbkid_old() {
		return hbkid_old;
	}
	public void setHbkid_old(String hbkid_old) {
		this.hbkid_old = hbkid_old;
	}
	public String getXpore_old() {
		return xpore_old;
	}
	public void setXpore_old(String xpore_old) {
		this.xpore_old = xpore_old;
	}
	public String getBlnkz_old() {
		return blnkz_old;
	}
	public void setBlnkz_old(String blnkz_old) {
		this.blnkz_old = blnkz_old;
	}
	public String getAltkn_old() {
		return altkn_old;
	}
	public void setAltkn_old(String altkn_old) {
		this.altkn_old = altkn_old;
	}
	public String getZgrup_old() {
		return zgrup_old;
	}
	public void setZgrup_old(String zgrup_old) {
		this.zgrup_old = zgrup_old;
	}
	public String getUrlid_old() {
		return urlid_old;
	}
	public void setUrlid_old(String urlid_old) {
		this.urlid_old = urlid_old;
	}
	public String getMgrup_old() {
		return mgrup_old;
	}
	public void setMgrup_old(String mgrup_old) {
		this.mgrup_old = mgrup_old;
	}
	public String getLockb_old() {
		return lockb_old;
	}
	public void setLockb_old(String lockb_old) {
		this.lockb_old = lockb_old;
	}
	public String getUzawe_old() {
		return uzawe_old;
	}
	public void setUzawe_old(String uzawe_old) {
		this.uzawe_old = uzawe_old;
	}
	public String getEkvbd_old() {
		return ekvbd_old;
	}
	public void setEkvbd_old(String ekvbd_old) {
		this.ekvbd_old = ekvbd_old;
	}
	public String getSregl_old() {
		return sregl_old;
	}
	public void setSregl_old(String sregl_old) {
		this.sregl_old = sregl_old;
	}
	public String getXedip_old() {
		return xedip_old;
	}
	public void setXedip_old(String xedip_old) {
		this.xedip_old = xedip_old;
	}
	public String getFrgrp_old() {
		return frgrp_old;
	}
	public void setFrgrp_old(String frgrp_old) {
		this.frgrp_old = frgrp_old;
	}
	public String getVrsdg_old() {
		return vrsdg_old;
	}
	public void setVrsdg_old(String vrsdg_old) {
		this.vrsdg_old = vrsdg_old;
	}
	public String getTlfxs_old() {
		return tlfxs_old;
	}
	public void setTlfxs_old(String tlfxs_old) {
		this.tlfxs_old = tlfxs_old;
	}
	public String getIntad_old() {
		return intad_old;
	}
	public void setIntad_old(String intad_old) {
		this.intad_old = intad_old;
	}
	public String getXknzb_old() {
		return xknzb_old;
	}
	public void setXknzb_old(String xknzb_old) {
		this.xknzb_old = xknzb_old;
	}
	public String getGuzte_old() {
		return guzte_old;
	}
	public void setGuzte_old(String guzte_old) {
		this.guzte_old = guzte_old;
	}
	public String getGricd_old() {
		return gricd_old;
	}
	public void setGricd_old(String gricd_old) {
		this.gricd_old = gricd_old;
	}
	public String getGridt_old() {
		return gridt_old;
	}
	public void setGridt_old(String gridt_old) {
		this.gridt_old = gridt_old;
	}
	public String getWbrsl_old() {
		return wbrsl_old;
	}
	public void setWbrsl_old(String wbrsl_old) {
		this.wbrsl_old = wbrsl_old;
	}
	public String getConfs_old() {
		return confs_old;
	}
	public void setConfs_old(String confs_old) {
		this.confs_old = confs_old;
	}
	public String getUpdat_old() {
		return updat_old;
	}
	public void setUpdat_old(String updat_old) {
		this.updat_old = updat_old;
	}
	public String getUptim_old() {
		return uptim_old;
	}
	public void setUptim_old(String uptim_old) {
		this.uptim_old = uptim_old;
	}
	public String getNodel_old() {
		return nodel_old;
	}
	public void setNodel_old(String nodel_old) {
		this.nodel_old = nodel_old;
	}
	public String getTlfns_old() {
		return tlfns_old;
	}
	public void setTlfns_old(String tlfns_old) {
		this.tlfns_old = tlfns_old;
	}
	public String getCession_kz_old() {
		return cession_kz_old;
	}
	public void setCession_kz_old(String cession_kz_old) {
		this.cession_kz_old = cession_kz_old;
	}
	public String getGmvkzd_old() {
		return gmvkzd_old;
	}
	public void setGmvkzd_old(String gmvkzd_old) {
		this.gmvkzd_old = gmvkzd_old;
	}
	public String getDatakey() {
		return datakey;
	}
	public void setDatakey(String datakey) {
		this.datakey = datakey;
	}

	
	

}
