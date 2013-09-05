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
import javax.persistence.TableGenerator;
@Entity
@Table(name="knvv")
public class Knvv implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7258507889078587332L;
	@Id
	@TableGenerator(name = "knvv_gen", //该表主键生成策略的名称,被@GeneratedValue.generator引用。
	                table = "sys_tb_generator",       //表生成策略所持久化的表名。
	                pkColumnName = "gen_name",    //在持久化的表中，该主键生成策略所对应键值的名称。
	                valueColumnName = "gen_value", //在持久化的表中， 该主键当前所生成的值，它的值将会随着每次创建而加。
	                pkColumnValue = "knvv_pk",//在持久化的表中，该生成策略所对应的主键
	                initialValue = 100,             //默认主键值为50
	                allocationSize = 1)           //每次主键值增加的大小
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "knvv_gen")
	private long id;
	private long sapclient;
	private int garden_flag=0;
	private String datakey;
	private	String	mandt	;
	private	String	kunnr	;
	private	String	vkorg	;
	private	String	vtweg	;
	private	String	spart	;
	private	String	ernam	;
	private	String	erdat	;
	private	String	begru	;
	private	String	loevm	;
	private	String	versg	;
	private	String	aufsd	;
	private	String	kalks	;
	private	String	kdgrp	;
	private	String	bzirk	;
	private	String	konda	;
	private	String	pltyp	;
	private	String	awahr	;
	private	String	inco1	;
	private	String	inco2	;
	private	String	lifsd	;
	private	String	autlf	;
	private	long	antlf	;
	private	String	kztlf	;
	private	String	kzazu	;
	private	String	chspl	;
	private	String	lprio	;
	private	String	eikto	;
	private	String	vsbed	;
	private	String	faksd	;
	private	String	mrnkz	;
	private	String	perfk	;
	private	String	perrl	;
	private	String	kvakz	;
	private	double	kvawt	;
	private	String	waers	;
	private	String	klabc	;
	private	String	ktgrd	;
	private	String	zterm	;
	private	String	vwerk	;
	private	String	vkgrp	;
	private	String	vkbur	;
	private	String	vsort	;
	private	String	kvgr1	;
	private	String	kvgr2	;
	private	String	kvgr3	;
	private	String	kvgr4	;
	private	String	kvgr5	;
	private	String	bokre	;
	private	String	boidt	;
	private	String	kurst	;
	private	String	prfre	;
	private	String	prat1	;
	private	String	prat2	;
	private	String	prat3	;
	private	String	prat4	;
	private	String	prat5	;
	private	String	prat6	;
	private	String	prat7	;
	private	String	prat8	;
	private	String	prat9	;
	private	String	prata	;
	private	String	kabss	;
	private	String	kkber	;
	private	String	cassd	;
	private	String	rdoff	;
	private	String	agrel	;
	private	String	megru	;
	private	double	uebto	;
	private	double	untto	;
	private	String	uebtk	;
	private	String	pvksm	;
	private	String	podkz	;
	private	long	podtg	;
	private	String	blind	;
	private	String	carrier_notif	;
	private	String	bev1emlgpfand	;
	private	String	bev1emlgforts	;
	public Knvv(){
		
	}
	public Knvv(KnvvCompared knvvCompared) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		if(knvvCompared!=null){
			Class modelClass = Knvv.class;
			Field[] fields = modelClass.getDeclaredFields();
			for(int j=0;j<fields.length;j++){
				String name = fields[j].getName();
				if(!name.equals("serialVersionUID")&&!name.equals("id")&&!name.equals("sapclient")&&!name.equals("garden_flag")){
					Class fieldType = fields[j].getType();
					String getMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
					Method getMethod = KnvvCompared.class.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(knvvCompared, new Object[]{});
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
	public String getVkorg() {
		return vkorg;
	}
	public void setVkorg(String vkorg) {
		this.vkorg = vkorg;
	}
	public String getVtweg() {
		return vtweg;
	}
	public void setVtweg(String vtweg) {
		this.vtweg = vtweg;
	}
	public String getSpart() {
		return spart;
	}
	public void setSpart(String spart) {
		this.spart = spart;
	}
	public String getErnam() {
		return ernam;
	}
	public void setErnam(String ernam) {
		this.ernam = ernam;
	}
	public String getErdat() {
		return erdat;
	}
	public void setErdat(String erdat) {
		this.erdat = erdat;
	}
	public String getBegru() {
		return begru;
	}
	public void setBegru(String begru) {
		this.begru = begru;
	}
	public String getLoevm() {
		return loevm;
	}
	public void setLoevm(String loevm) {
		this.loevm = loevm;
	}
	public String getVersg() {
		return versg;
	}
	public void setVersg(String versg) {
		this.versg = versg;
	}
	public String getAufsd() {
		return aufsd;
	}
	public void setAufsd(String aufsd) {
		this.aufsd = aufsd;
	}
	public String getKalks() {
		return kalks;
	}
	public void setKalks(String kalks) {
		this.kalks = kalks;
	}
	public String getKdgrp() {
		return kdgrp;
	}
	public void setKdgrp(String kdgrp) {
		this.kdgrp = kdgrp;
	}
	public String getBzirk() {
		return bzirk;
	}
	public void setBzirk(String bzirk) {
		this.bzirk = bzirk;
	}
	public String getKonda() {
		return konda;
	}
	public void setKonda(String konda) {
		this.konda = konda;
	}
	public String getPltyp() {
		return pltyp;
	}
	public void setPltyp(String pltyp) {
		this.pltyp = pltyp;
	}
	public String getAwahr() {
		return awahr;
	}
	public void setAwahr(String awahr) {
		this.awahr = awahr;
	}
	public String getInco1() {
		return inco1;
	}
	public void setInco1(String inco1) {
		this.inco1 = inco1;
	}
	public String getInco2() {
		return inco2;
	}
	public void setInco2(String inco2) {
		this.inco2 = inco2;
	}
	public String getLifsd() {
		return lifsd;
	}
	public void setLifsd(String lifsd) {
		this.lifsd = lifsd;
	}
	public String getAutlf() {
		return autlf;
	}
	public void setAutlf(String autlf) {
		this.autlf = autlf;
	}
	public long getAntlf() {
		return antlf;
	}
	public void setAntlf(long antlf) {
		this.antlf = antlf;
	}
	public String getKztlf() {
		return kztlf;
	}
	public void setKztlf(String kztlf) {
		this.kztlf = kztlf;
	}
	public String getKzazu() {
		return kzazu;
	}
	public void setKzazu(String kzazu) {
		this.kzazu = kzazu;
	}
	public String getChspl() {
		return chspl;
	}
	public void setChspl(String chspl) {
		this.chspl = chspl;
	}
	public String getLprio() {
		return lprio;
	}
	public void setLprio(String lprio) {
		this.lprio = lprio;
	}
	public String getEikto() {
		return eikto;
	}
	public void setEikto(String eikto) {
		this.eikto = eikto;
	}
	public String getVsbed() {
		return vsbed;
	}
	public void setVsbed(String vsbed) {
		this.vsbed = vsbed;
	}
	public String getFaksd() {
		return faksd;
	}
	public void setFaksd(String faksd) {
		this.faksd = faksd;
	}
	public String getMrnkz() {
		return mrnkz;
	}
	public void setMrnkz(String mrnkz) {
		this.mrnkz = mrnkz;
	}
	public String getPerfk() {
		return perfk;
	}
	public void setPerfk(String perfk) {
		this.perfk = perfk;
	}
	public String getPerrl() {
		return perrl;
	}
	public void setPerrl(String perrl) {
		this.perrl = perrl;
	}
	public String getKvakz() {
		return kvakz;
	}
	public void setKvakz(String kvakz) {
		this.kvakz = kvakz;
	}
	public double getKvawt() {
		return kvawt;
	}
	public void setKvawt(double kvawt) {
		this.kvawt = kvawt;
	}
	public String getWaers() {
		return waers;
	}
	public void setWaers(String waers) {
		this.waers = waers;
	}
	public String getKlabc() {
		return klabc;
	}
	public void setKlabc(String klabc) {
		this.klabc = klabc;
	}
	public String getKtgrd() {
		return ktgrd;
	}
	public void setKtgrd(String ktgrd) {
		this.ktgrd = ktgrd;
	}
	public String getZterm() {
		return zterm;
	}
	public void setZterm(String zterm) {
		this.zterm = zterm;
	}
	public String getVwerk() {
		return vwerk;
	}
	public void setVwerk(String vwerk) {
		this.vwerk = vwerk;
	}
	public String getVkgrp() {
		return vkgrp;
	}
	public void setVkgrp(String vkgrp) {
		this.vkgrp = vkgrp;
	}
	public String getVkbur() {
		return vkbur;
	}
	public void setVkbur(String vkbur) {
		this.vkbur = vkbur;
	}
	public String getVsort() {
		return vsort;
	}
	public void setVsort(String vsort) {
		this.vsort = vsort;
	}
	public String getKvgr1() {
		return kvgr1;
	}
	public void setKvgr1(String kvgr1) {
		this.kvgr1 = kvgr1;
	}
	public String getKvgr2() {
		return kvgr2;
	}
	public void setKvgr2(String kvgr2) {
		this.kvgr2 = kvgr2;
	}
	public String getKvgr3() {
		return kvgr3;
	}
	public void setKvgr3(String kvgr3) {
		this.kvgr3 = kvgr3;
	}
	public String getKvgr4() {
		return kvgr4;
	}
	public void setKvgr4(String kvgr4) {
		this.kvgr4 = kvgr4;
	}
	public String getKvgr5() {
		return kvgr5;
	}
	public void setKvgr5(String kvgr5) {
		this.kvgr5 = kvgr5;
	}
	public String getBokre() {
		return bokre;
	}
	public void setBokre(String bokre) {
		this.bokre = bokre;
	}
	public String getBoidt() {
		return boidt;
	}
	public void setBoidt(String boidt) {
		this.boidt = boidt;
	}
	public String getKurst() {
		return kurst;
	}
	public void setKurst(String kurst) {
		this.kurst = kurst;
	}
	public String getPrfre() {
		return prfre;
	}
	public void setPrfre(String prfre) {
		this.prfre = prfre;
	}
	public String getPrat1() {
		return prat1;
	}
	public void setPrat1(String prat1) {
		this.prat1 = prat1;
	}
	public String getPrat2() {
		return prat2;
	}
	public void setPrat2(String prat2) {
		this.prat2 = prat2;
	}
	public String getPrat3() {
		return prat3;
	}
	public void setPrat3(String prat3) {
		this.prat3 = prat3;
	}
	public String getPrat4() {
		return prat4;
	}
	public void setPrat4(String prat4) {
		this.prat4 = prat4;
	}
	public String getPrat5() {
		return prat5;
	}
	public void setPrat5(String prat5) {
		this.prat5 = prat5;
	}
	public String getPrat6() {
		return prat6;
	}
	public void setPrat6(String prat6) {
		this.prat6 = prat6;
	}
	public String getPrat7() {
		return prat7;
	}
	public void setPrat7(String prat7) {
		this.prat7 = prat7;
	}
	public String getPrat8() {
		return prat8;
	}
	public void setPrat8(String prat8) {
		this.prat8 = prat8;
	}
	public String getPrat9() {
		return prat9;
	}
	public void setPrat9(String prat9) {
		this.prat9 = prat9;
	}
	public String getPrata() {
		return prata;
	}
	public void setPrata(String prata) {
		this.prata = prata;
	}
	public String getKabss() {
		return kabss;
	}
	public void setKabss(String kabss) {
		this.kabss = kabss;
	}
	public String getKkber() {
		return kkber;
	}
	public void setKkber(String kkber) {
		this.kkber = kkber;
	}
	public String getCassd() {
		return cassd;
	}
	public void setCassd(String cassd) {
		this.cassd = cassd;
	}
	public String getRdoff() {
		return rdoff;
	}
	public void setRdoff(String rdoff) {
		this.rdoff = rdoff;
	}
	public String getAgrel() {
		return agrel;
	}
	public void setAgrel(String agrel) {
		this.agrel = agrel;
	}
	public String getMegru() {
		return megru;
	}
	public void setMegru(String megru) {
		this.megru = megru;
	}
	public double getUebto() {
		return uebto;
	}
	public void setUebto(double uebto) {
		this.uebto = uebto;
	}
	public double getUntto() {
		return untto;
	}
	public void setUntto(double untto) {
		this.untto = untto;
	}
	public String getUebtk() {
		return uebtk;
	}
	public void setUebtk(String uebtk) {
		this.uebtk = uebtk;
	}
	public String getPvksm() {
		return pvksm;
	}
	public void setPvksm(String pvksm) {
		this.pvksm = pvksm;
	}
	public String getPodkz() {
		return podkz;
	}
	public void setPodkz(String podkz) {
		this.podkz = podkz;
	}
	public long getPodtg() {
		return podtg;
	}
	public void setPodtg(long podtg) {
		this.podtg = podtg;
	}
	public String getBlind() {
		return blind;
	}
	public void setBlind(String blind) {
		this.blind = blind;
	}
	public String getCarrier_notif() {
		return carrier_notif;
	}
	public void setCarrier_notif(String carrier_notif) {
		this.carrier_notif = carrier_notif;
	}
	public String getBev1emlgpfand() {
		return bev1emlgpfand;
	}
	public void setBev1emlgpfand(String bev1emlgpfand) {
		this.bev1emlgpfand = bev1emlgpfand;
	}
	public String getBev1emlgforts() {
		return bev1emlgforts;
	}
	public void setBev1emlgforts(String bev1emlgforts) {
		this.bev1emlgforts = bev1emlgforts;
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
