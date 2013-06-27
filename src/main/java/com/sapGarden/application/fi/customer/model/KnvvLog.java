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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="knvv_log")
public class KnvvLog implements Serializable{

	private static final long serialVersionUID = -126L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_kna1")
	@SequenceGenerator(name="seq_kna1",sequenceName="seq_kna1",allocationSize=1)
	private long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date opttime;
	private String optuser;
	private String opt;
	private long optid;
	private String optflag;
	private String opttype;
	private String optmsg;
	private String processstatus;
	private long sapclient;
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
	private	String	mandt_old	;
	private	String	kunnr_old	;
	private	String	vkorg_old	;
	private	String	vtweg_old	;
	private	String	spart_old	;
	private	String	ernam_old	;
	private	String	erdat_old	;
	private	String	begru_old	;
	private	String	loevm_old	;
	private	String	versg_old	;
	private	String	aufsd_old	;
	private	String	kalks_old	;
	private	String	kdgrp_old	;
	private	String	bzirk_old	;
	private	String	konda_old	;
	private	String	pltyp_old	;
	private	String	awahr_old	;
	private	String	inco1_old	;
	private	String	inco2_old	;
	private	String	lifsd_old	;
	private	String	autlf_old	;
	private	long	antlf_old	;
	private	String	kztlf_old	;
	private	String	kzazu_old	;
	private	String	chspl_old	;
	private	String	lprio_old	;
	private	String	eikto_old	;
	private	String	vsbed_old	;
	private	String	faksd_old	;
	private	String	mrnkz_old	;
	private	String	perfk_old	;
	private	String	perrl_old	;
	private	String	kvakz_old	;
	private	double	kvawt_old	;
	private	String	waers_old	;
	private	String	klabc_old	;
	private	String	ktgrd_old	;
	private	String	zterm_old	;
	private	String	vwerk_old	;
	private	String	vkgrp_old	;
	private	String	vkbur_old	;
	private	String	vsort_old	;
	private	String	kvgr1_old	;
	private	String	kvgr2_old	;
	private	String	kvgr3_old	;
	private	String	kvgr4_old	;
	private	String	kvgr5_old	;
	private	String	bokre_old	;
	private	String	boidt_old	;
	private	String	kurst_old	;
	private	String	prfre_old	;
	private	String	prat1_old	;
	private	String	prat2_old	;
	private	String	prat3_old	;
	private	String	prat4_old	;
	private	String	prat5_old	;
	private	String	prat6_old	;
	private	String	prat7_old	;
	private	String	prat8_old	;
	private	String	prat9_old	;
	private	String	prata_old	;
	private	String	kabss_old	;
	private	String	kkber_old	;
	private	String	cassd_old	;
	private	String	rdoff_old	;
	private	String	agrel_old	;
	private	String	megru_old	;
	private	double	uebto_old	;
	private	double	untto_old	;
	private	String	uebtk_old	;
	private	String	pvksm_old	;
	private	String	podkz_old	;
	private	long	podtg_old	;
	private	String	blind_old	;
	private	String	carrier_notif_old	;
	private	String	bev1emlgpfand_old	;
	private	String	bev1emlgforts_old	;
	public KnvvLog(){
		
	}
	/*
	public KnvvLog(long sapclient,Knvv sknvv,Knvv gknvv) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		this.sapclient=sapclient;
		if(sknvv!=null){
			Class modelClass = sknvv.getClass();
			Field[] fields = modelClass.getDeclaredFields();
			for(int j=0;j<fields.length;j++){
				String name = fields[j].getName();
				if(!name.equals("serialVersionUID")&&!name.equals("id")&&!name.equals("sapclient")){
					Class fieldType = fields[j].getType();
					String getMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
					Method getMethod = modelClass.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(sknvv, new Object[]{});
					String methodName = "set"+name.substring(0, 1).toUpperCase()+name.substring(1);
					Method method = KnvvLog.class.getMethod(methodName, new Class[]{fieldType});
					method.invoke(this, new Object[]{value});
				}
			}
		}
		if(gknvv!=null){
			Class modelClass = gknvv.getClass();
			this.id=gknvv.getId();
			Field[] fields = modelClass.getDeclaredFields();
			for(int j=0;j<fields.length;j++){
				String name = fields[j].getName();
				if(!name.equals("serialVersionUID")&&!name.equals("id")&&!name.equals("sapclient")){
					Class fieldType = fields[j].getType();
					String getMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
					Method getMethod = modelClass.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(gknvv, new Object[]{});
					String methodName = "set"+name.substring(0, 1).toUpperCase()+name.substring(1)+"_old";
					Method method = KnvvLog.class.getMethod(methodName, new Class[]{fieldType});
					method.invoke(this, new Object[]{value});
				}
			}
		}
	}
	*/
	public KnvvLog(Knvv knvv,long sapclient,String optmsg,String opt,String optflag,String opttype,String optuser,Date opttime) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		this.sapclient=sapclient;
		this.opt=opt;
		this.optflag=optflag;
		this.optmsg=optmsg;
		this.opttime=opttime;
		this.optuser=optuser;
		this.opttype=opttype;
		if(knvv!=null){
			Class modelClass = knvv.getClass();
			Field[] fields = modelClass.getDeclaredFields();
			for(int j=0;j<fields.length;j++){
				String name = fields[j].getName();
				if(!name.equals("serialVersionUID")&&!name.equals("id")&&!name.equals("sapclient")&&!name.equals("garden_flag")&&!name.equals("datakey")){
					Class fieldType = fields[j].getType();
					String getMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
					Method getMethod = modelClass.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(knvv, new Object[]{});
					String methodName = "set"+name.substring(0, 1).toUpperCase()+name.substring(1);
					Method method = KnvvLog.class.getMethod(methodName, new Class[]{fieldType});
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
	public Date getOpttime() {
		return opttime;
	}
	public void setOpttime(Date opttime) {
		this.opttime = opttime;
	}
	public String getOptuser() {
		return optuser;
	}
	public void setOptuser(String optuser) {
		this.optuser = optuser;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public long getOptid() {
		return optid;
	}
	public void setOptid(long optid) {
		this.optid = optid;
	}
	public String getOptflag() {
		return optflag;
	}
	public void setOptflag(String optflag) {
		this.optflag = optflag;
	}
	public String getOpttype() {
		return opttype;
	}
	public void setOpttype(String opttype) {
		this.opttype = opttype;
	}
	public String getOptmsg() {
		return optmsg;
	}
	public void setOptmsg(String optmsg) {
		this.optmsg = optmsg;
	}
	public String getProcessstatus() {
		return processstatus;
	}
	public void setProcessstatus(String processstatus) {
		this.processstatus = processstatus;
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
	public String getVkorg_old() {
		return vkorg_old;
	}
	public void setVkorg_old(String vkorg_old) {
		this.vkorg_old = vkorg_old;
	}
	public String getVtweg_old() {
		return vtweg_old;
	}
	public void setVtweg_old(String vtweg_old) {
		this.vtweg_old = vtweg_old;
	}
	public String getSpart_old() {
		return spart_old;
	}
	public void setSpart_old(String spart_old) {
		this.spart_old = spart_old;
	}
	public String getErnam_old() {
		return ernam_old;
	}
	public void setErnam_old(String ernam_old) {
		this.ernam_old = ernam_old;
	}
	public String getErdat_old() {
		return erdat_old;
	}
	public void setErdat_old(String erdat_old) {
		this.erdat_old = erdat_old;
	}
	public String getBegru_old() {
		return begru_old;
	}
	public void setBegru_old(String begru_old) {
		this.begru_old = begru_old;
	}
	public String getLoevm_old() {
		return loevm_old;
	}
	public void setLoevm_old(String loevm_old) {
		this.loevm_old = loevm_old;
	}
	public String getVersg_old() {
		return versg_old;
	}
	public void setVersg_old(String versg_old) {
		this.versg_old = versg_old;
	}
	public String getAufsd_old() {
		return aufsd_old;
	}
	public void setAufsd_old(String aufsd_old) {
		this.aufsd_old = aufsd_old;
	}
	public String getKalks_old() {
		return kalks_old;
	}
	public void setKalks_old(String kalks_old) {
		this.kalks_old = kalks_old;
	}
	public String getKdgrp_old() {
		return kdgrp_old;
	}
	public void setKdgrp_old(String kdgrp_old) {
		this.kdgrp_old = kdgrp_old;
	}
	public String getBzirk_old() {
		return bzirk_old;
	}
	public void setBzirk_old(String bzirk_old) {
		this.bzirk_old = bzirk_old;
	}
	public String getKonda_old() {
		return konda_old;
	}
	public void setKonda_old(String konda_old) {
		this.konda_old = konda_old;
	}
	public String getPltyp_old() {
		return pltyp_old;
	}
	public void setPltyp_old(String pltyp_old) {
		this.pltyp_old = pltyp_old;
	}
	public String getAwahr_old() {
		return awahr_old;
	}
	public void setAwahr_old(String awahr_old) {
		this.awahr_old = awahr_old;
	}
	public String getInco1_old() {
		return inco1_old;
	}
	public void setInco1_old(String inco1_old) {
		this.inco1_old = inco1_old;
	}
	public String getInco2_old() {
		return inco2_old;
	}
	public void setInco2_old(String inco2_old) {
		this.inco2_old = inco2_old;
	}
	public String getLifsd_old() {
		return lifsd_old;
	}
	public void setLifsd_old(String lifsd_old) {
		this.lifsd_old = lifsd_old;
	}
	public String getAutlf_old() {
		return autlf_old;
	}
	public void setAutlf_old(String autlf_old) {
		this.autlf_old = autlf_old;
	}
	public long getAntlf_old() {
		return antlf_old;
	}
	public void setAntlf_old(long antlf_old) {
		this.antlf_old = antlf_old;
	}
	public String getKztlf_old() {
		return kztlf_old;
	}
	public void setKztlf_old(String kztlf_old) {
		this.kztlf_old = kztlf_old;
	}
	public String getKzazu_old() {
		return kzazu_old;
	}
	public void setKzazu_old(String kzazu_old) {
		this.kzazu_old = kzazu_old;
	}
	public String getChspl_old() {
		return chspl_old;
	}
	public void setChspl_old(String chspl_old) {
		this.chspl_old = chspl_old;
	}
	public String getLprio_old() {
		return lprio_old;
	}
	public void setLprio_old(String lprio_old) {
		this.lprio_old = lprio_old;
	}
	public String getEikto_old() {
		return eikto_old;
	}
	public void setEikto_old(String eikto_old) {
		this.eikto_old = eikto_old;
	}
	public String getVsbed_old() {
		return vsbed_old;
	}
	public void setVsbed_old(String vsbed_old) {
		this.vsbed_old = vsbed_old;
	}
	public String getFaksd_old() {
		return faksd_old;
	}
	public void setFaksd_old(String faksd_old) {
		this.faksd_old = faksd_old;
	}
	public String getMrnkz_old() {
		return mrnkz_old;
	}
	public void setMrnkz_old(String mrnkz_old) {
		this.mrnkz_old = mrnkz_old;
	}
	public String getPerfk_old() {
		return perfk_old;
	}
	public void setPerfk_old(String perfk_old) {
		this.perfk_old = perfk_old;
	}
	public String getPerrl_old() {
		return perrl_old;
	}
	public void setPerrl_old(String perrl_old) {
		this.perrl_old = perrl_old;
	}
	public String getKvakz_old() {
		return kvakz_old;
	}
	public void setKvakz_old(String kvakz_old) {
		this.kvakz_old = kvakz_old;
	}
	public double getKvawt_old() {
		return kvawt_old;
	}
	public void setKvawt_old(double kvawt_old) {
		this.kvawt_old = kvawt_old;
	}
	public String getWaers_old() {
		return waers_old;
	}
	public void setWaers_old(String waers_old) {
		this.waers_old = waers_old;
	}
	public String getKlabc_old() {
		return klabc_old;
	}
	public void setKlabc_old(String klabc_old) {
		this.klabc_old = klabc_old;
	}
	public String getKtgrd_old() {
		return ktgrd_old;
	}
	public void setKtgrd_old(String ktgrd_old) {
		this.ktgrd_old = ktgrd_old;
	}
	public String getZterm_old() {
		return zterm_old;
	}
	public void setZterm_old(String zterm_old) {
		this.zterm_old = zterm_old;
	}
	public String getVwerk_old() {
		return vwerk_old;
	}
	public void setVwerk_old(String vwerk_old) {
		this.vwerk_old = vwerk_old;
	}
	public String getVkgrp_old() {
		return vkgrp_old;
	}
	public void setVkgrp_old(String vkgrp_old) {
		this.vkgrp_old = vkgrp_old;
	}
	public String getVkbur_old() {
		return vkbur_old;
	}
	public void setVkbur_old(String vkbur_old) {
		this.vkbur_old = vkbur_old;
	}
	public String getVsort_old() {
		return vsort_old;
	}
	public void setVsort_old(String vsort_old) {
		this.vsort_old = vsort_old;
	}
	public String getKvgr1_old() {
		return kvgr1_old;
	}
	public void setKvgr1_old(String kvgr1_old) {
		this.kvgr1_old = kvgr1_old;
	}
	public String getKvgr2_old() {
		return kvgr2_old;
	}
	public void setKvgr2_old(String kvgr2_old) {
		this.kvgr2_old = kvgr2_old;
	}
	public String getKvgr3_old() {
		return kvgr3_old;
	}
	public void setKvgr3_old(String kvgr3_old) {
		this.kvgr3_old = kvgr3_old;
	}
	public String getKvgr4_old() {
		return kvgr4_old;
	}
	public void setKvgr4_old(String kvgr4_old) {
		this.kvgr4_old = kvgr4_old;
	}
	public String getKvgr5_old() {
		return kvgr5_old;
	}
	public void setKvgr5_old(String kvgr5_old) {
		this.kvgr5_old = kvgr5_old;
	}
	public String getBokre_old() {
		return bokre_old;
	}
	public void setBokre_old(String bokre_old) {
		this.bokre_old = bokre_old;
	}
	public String getBoidt_old() {
		return boidt_old;
	}
	public void setBoidt_old(String boidt_old) {
		this.boidt_old = boidt_old;
	}
	public String getKurst_old() {
		return kurst_old;
	}
	public void setKurst_old(String kurst_old) {
		this.kurst_old = kurst_old;
	}
	public String getPrfre_old() {
		return prfre_old;
	}
	public void setPrfre_old(String prfre_old) {
		this.prfre_old = prfre_old;
	}
	public String getPrat1_old() {
		return prat1_old;
	}
	public void setPrat1_old(String prat1_old) {
		this.prat1_old = prat1_old;
	}
	public String getPrat2_old() {
		return prat2_old;
	}
	public void setPrat2_old(String prat2_old) {
		this.prat2_old = prat2_old;
	}
	public String getPrat3_old() {
		return prat3_old;
	}
	public void setPrat3_old(String prat3_old) {
		this.prat3_old = prat3_old;
	}
	public String getPrat4_old() {
		return prat4_old;
	}
	public void setPrat4_old(String prat4_old) {
		this.prat4_old = prat4_old;
	}
	public String getPrat5_old() {
		return prat5_old;
	}
	public void setPrat5_old(String prat5_old) {
		this.prat5_old = prat5_old;
	}
	public String getPrat6_old() {
		return prat6_old;
	}
	public void setPrat6_old(String prat6_old) {
		this.prat6_old = prat6_old;
	}
	public String getPrat7_old() {
		return prat7_old;
	}
	public void setPrat7_old(String prat7_old) {
		this.prat7_old = prat7_old;
	}
	public String getPrat8_old() {
		return prat8_old;
	}
	public void setPrat8_old(String prat8_old) {
		this.prat8_old = prat8_old;
	}
	public String getPrat9_old() {
		return prat9_old;
	}
	public void setPrat9_old(String prat9_old) {
		this.prat9_old = prat9_old;
	}
	public String getPrata_old() {
		return prata_old;
	}
	public void setPrata_old(String prata_old) {
		this.prata_old = prata_old;
	}
	public String getKabss_old() {
		return kabss_old;
	}
	public void setKabss_old(String kabss_old) {
		this.kabss_old = kabss_old;
	}
	public String getKkber_old() {
		return kkber_old;
	}
	public void setKkber_old(String kkber_old) {
		this.kkber_old = kkber_old;
	}
	public String getCassd_old() {
		return cassd_old;
	}
	public void setCassd_old(String cassd_old) {
		this.cassd_old = cassd_old;
	}
	public String getRdoff_old() {
		return rdoff_old;
	}
	public void setRdoff_old(String rdoff_old) {
		this.rdoff_old = rdoff_old;
	}
	public String getAgrel_old() {
		return agrel_old;
	}
	public void setAgrel_old(String agrel_old) {
		this.agrel_old = agrel_old;
	}
	public String getMegru_old() {
		return megru_old;
	}
	public void setMegru_old(String megru_old) {
		this.megru_old = megru_old;
	}
	public double getUebto_old() {
		return uebto_old;
	}
	public void setUebto_old(double uebto_old) {
		this.uebto_old = uebto_old;
	}
	public double getUntto_old() {
		return untto_old;
	}
	public void setUntto_old(double untto_old) {
		this.untto_old = untto_old;
	}
	public String getUebtk_old() {
		return uebtk_old;
	}
	public void setUebtk_old(String uebtk_old) {
		this.uebtk_old = uebtk_old;
	}
	public String getPvksm_old() {
		return pvksm_old;
	}
	public void setPvksm_old(String pvksm_old) {
		this.pvksm_old = pvksm_old;
	}
	public String getPodkz_old() {
		return podkz_old;
	}
	public void setPodkz_old(String podkz_old) {
		this.podkz_old = podkz_old;
	}
	public long getPodtg_old() {
		return podtg_old;
	}
	public void setPodtg_old(long podtg_old) {
		this.podtg_old = podtg_old;
	}
	public String getBlind_old() {
		return blind_old;
	}
	public void setBlind_old(String blind_old) {
		this.blind_old = blind_old;
	}
	public String getCarrier_notif_old() {
		return carrier_notif_old;
	}
	public void setCarrier_notif_old(String carrier_notif_old) {
		this.carrier_notif_old = carrier_notif_old;
	}
	public String getBev1emlgpfand_old() {
		return bev1emlgpfand_old;
	}
	public void setBev1emlgpfand_old(String bev1emlgpfand_old) {
		this.bev1emlgpfand_old = bev1emlgpfand_old;
	}
	public String getBev1emlgforts_old() {
		return bev1emlgforts_old;
	}
	public void setBev1emlgforts_old(String bev1emlgforts_old) {
		this.bev1emlgforts_old = bev1emlgforts_old;
	}

	
	

}
