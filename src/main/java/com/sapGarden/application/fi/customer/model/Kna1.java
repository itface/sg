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
@Table(name="kna1")
public class Kna1 implements Serializable{
	private static final long serialVersionUID = -113L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_kna1")
	@SequenceGenerator(name="seq_kna1",sequenceName="seq_kna1",allocationSize=1)
	private long id;
	private long sapclient;
	private int garden_flag=0;
	private	String	mandt	;
	private	String	kunnr	;
	private	String	land1	;
	private	String	name1	;
	private	String	name2	;
	private	String	ort01	;
	private	String	pstlz	;
	private	String	regio	;
	private	String	sortl	;
	private	String	stras	;
	private	String	telf1	;
	private	String	telfx	;
	private	String	xcpdk	;
	private	String	adrnr	;
	private	String	mcod1	;
	private	String	mcod2	;
	private	String	mcod3	;
	private	String	anred	;
	private	String	aufsd	;
	private	String	bahne	;
	private	String	bahns	;
	private	String	bbbnr	;
	private	String	bbsnr	;
	private	String	begru	;
	private	String	brsch	;
	private	String	bubkz	;
	private	String	datlt	;
	private	String	erdat	;
	private	String	ernam	;
	private	String	exabl	;
	private	String	faksd	;
	private	String	fiskn	;
	private	String	knazk	;
	private	String	knrza	;
	private	String	konzs	;
	private	String	ktokd	;
	private	String	kukla	;
	private	String	lifnr	;
	private	String	lifsd	;
	private	String	locco	;
	private	String	loevm	;
	private	String	name3	;
	private	String	name4	;
	private	String	niels	;
	private	String	ort02	;
	private	String	pfach	;
	private	String	pstl2	;
	private	String	counc	;
	private	String	cityc	;
	private	String	rpmkr	;
	private	String	sperr	;
	private	String	spras	;
	private	String	stcd1	;
	private	String	stcd2	;
	private	String	stkza	;
	private	String	stkzu	;
	private	String	telbx	;
	private	String	telf2	;
	private	String	teltx	;
	private	String	telx1	;
	private	String	lzone	;
	private	String	xzemp	;
	private	String	vbund	;
	private	String	stceg	;
	private	String	dear1	;
	private	String	dear2	;
	private	String	dear3	;
	private	String	dear4	;
	private	String	dear5	;
	private	String	gform	;
	private	String	bran1	;
	private	String	bran2	;
	private	String	bran3	;
	private	String	bran4	;
	private	String	bran5	;
	private	String	ekont	;
	private	double	umsat	;
	private	String	umjah	;
	private	String	uwaer	;
	private	String	jmzah	;
	private	String	jmjah	;
	private	String	katr1	;
	private	String	katr2	;
	private	String	katr3	;
	private	String	katr4	;
	private	String	katr5	;
	private	String	katr6	;
	private	String	katr7	;
	private	String	katr8	;
	private	String	katr9	;
	private	String	katr10	;
	private	String	stkzn	;
	private	double	umsa1	;
	private	String	txjcd	;
	private	String	periv	;
	private	String	abrvw	;
	private	String	inspbydebi	;
	private	String	inspatdebi	;
	private	String	ktocd	;
	private	String	pfort	;
	private	String	werks	;
	private	String	dtams	;
	private	String	dtaws	;
	private	String	duefl	;
	private	String	hzuor	;
	private	String	sperz	;
	private	String	etikg	;
	private	String	civve	;
	private	String	milve	;
	private	String	kdkg1	;
	private	String	kdkg2	;
	private	String	kdkg3	;
	private	String	kdkg4	;
	private	String	kdkg5	;
	private	String	xknza	;
	private	String	fityp	;
	private	String	stcdt	;
	private	String	stcd3	;
	private	String	stcd4	;
	private	String	xicms	;
	private	String	xxipi	;
	private	String	xsubt	;
	private	String	cfopc	;
	private	String	txlw1	;
	private	String	txlw2	;
	private	String	ccc01	;
	private	String	ccc02	;
	private	String	ccc03	;
	private	String	ccc04	;
	private	String	cassd	;
	private	String	knurl	;
	private	String	j_1kfrepre	;
	private	String	j_1kftbus	;
	private	String	j_1kftind	;
	private	String	confs	;
	private	String	updat	;
	private	String	uptim	;
	private	String	nodel	;
	private	String	dear6	;
	private	double	vsor_palhgt	;
	private	String	vsor_pal_ul	;
	private	String	vsor_pk_mat	;
	private	String	vsor_matpal	;
	private	String	vsor_i_no_lyr	;
	private	String	vsor_one_mat	;
	private	String	vsor_one_sort	;
	private	String	vsor_uld_side	;
	private	String	vsor_load_pref	;
	private	String	vsor_dpoint	;
	private	String	alc	;
	private	String	pmt_office	;
	private	String	psofg	;
	private	String	psois	;
	private	String	pson1	;
	private	String	pson2	;
	private	String	pson3	;
	private	String	psovn	;
	private	String	psotl	;
	private	String	psohs	;
	private	String	psost	;
	private	String	psoo1	;
	private	String	psoo2	;
	private	String	psoo3	;
	private	String	psoo4	;
	private	String	psoo5	;
	public Kna1(){
		
	}
	public Kna1(Kna1Compared kna1Compared) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		if(kna1Compared!=null){
			Class modelClass = Kna1.class;
			Field[] fields = modelClass.getDeclaredFields();
			for(int j=0;j<fields.length;j++){
				String name = fields[j].getName();
				if(!name.equals("serialVersionUID")&&!name.equals("id")&&!name.equals("sapclient")&&!name.equals("garden_flag")){
					Class fieldType = fields[j].getType();
					String getMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
					Method getMethod = Kna1Compared.class.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(kna1Compared, new Object[]{});
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
	public String getLand1() {
		return land1;
	}
	public void setLand1(String land1) {
		this.land1 = land1;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getOrt01() {
		return ort01;
	}
	public void setOrt01(String ort01) {
		this.ort01 = ort01;
	}
	public String getPstlz() {
		return pstlz;
	}
	public void setPstlz(String pstlz) {
		this.pstlz = pstlz;
	}
	public String getRegio() {
		return regio;
	}
	public void setRegio(String regio) {
		this.regio = regio;
	}
	public String getSortl() {
		return sortl;
	}
	public void setSortl(String sortl) {
		this.sortl = sortl;
	}
	public String getStras() {
		return stras;
	}
	public void setStras(String stras) {
		this.stras = stras;
	}
	public String getTelf1() {
		return telf1;
	}
	public void setTelf1(String telf1) {
		this.telf1 = telf1;
	}
	public String getTelfx() {
		return telfx;
	}
	public void setTelfx(String telfx) {
		this.telfx = telfx;
	}
	public String getXcpdk() {
		return xcpdk;
	}
	public void setXcpdk(String xcpdk) {
		this.xcpdk = xcpdk;
	}
	public String getAdrnr() {
		return adrnr;
	}
	public void setAdrnr(String adrnr) {
		this.adrnr = adrnr;
	}
	public String getMcod1() {
		return mcod1;
	}
	public void setMcod1(String mcod1) {
		this.mcod1 = mcod1;
	}
	public String getMcod2() {
		return mcod2;
	}
	public void setMcod2(String mcod2) {
		this.mcod2 = mcod2;
	}
	public String getMcod3() {
		return mcod3;
	}
	public void setMcod3(String mcod3) {
		this.mcod3 = mcod3;
	}
	public String getAnred() {
		return anred;
	}
	public void setAnred(String anred) {
		this.anred = anred;
	}
	public String getAufsd() {
		return aufsd;
	}
	public void setAufsd(String aufsd) {
		this.aufsd = aufsd;
	}
	public String getBahne() {
		return bahne;
	}
	public void setBahne(String bahne) {
		this.bahne = bahne;
	}
	public String getBahns() {
		return bahns;
	}
	public void setBahns(String bahns) {
		this.bahns = bahns;
	}
	public String getBbbnr() {
		return bbbnr;
	}
	public void setBbbnr(String bbbnr) {
		this.bbbnr = bbbnr;
	}
	public String getBbsnr() {
		return bbsnr;
	}
	public void setBbsnr(String bbsnr) {
		this.bbsnr = bbsnr;
	}
	public String getBegru() {
		return begru;
	}
	public void setBegru(String begru) {
		this.begru = begru;
	}
	public String getBrsch() {
		return brsch;
	}
	public void setBrsch(String brsch) {
		this.brsch = brsch;
	}
	public String getBubkz() {
		return bubkz;
	}
	public void setBubkz(String bubkz) {
		this.bubkz = bubkz;
	}
	public String getDatlt() {
		return datlt;
	}
	public void setDatlt(String datlt) {
		this.datlt = datlt;
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
	public String getExabl() {
		return exabl;
	}
	public void setExabl(String exabl) {
		this.exabl = exabl;
	}
	public String getFaksd() {
		return faksd;
	}
	public void setFaksd(String faksd) {
		this.faksd = faksd;
	}
	public String getFiskn() {
		return fiskn;
	}
	public void setFiskn(String fiskn) {
		this.fiskn = fiskn;
	}
	public String getKnazk() {
		return knazk;
	}
	public void setKnazk(String knazk) {
		this.knazk = knazk;
	}
	public String getKnrza() {
		return knrza;
	}
	public void setKnrza(String knrza) {
		this.knrza = knrza;
	}
	public String getKonzs() {
		return konzs;
	}
	public void setKonzs(String konzs) {
		this.konzs = konzs;
	}
	public String getKtokd() {
		return ktokd;
	}
	public void setKtokd(String ktokd) {
		this.ktokd = ktokd;
	}
	public String getKukla() {
		return kukla;
	}
	public void setKukla(String kukla) {
		this.kukla = kukla;
	}
	public String getLifnr() {
		return lifnr;
	}
	public void setLifnr(String lifnr) {
		this.lifnr = lifnr;
	}
	public String getLifsd() {
		return lifsd;
	}
	public void setLifsd(String lifsd) {
		this.lifsd = lifsd;
	}
	public String getLocco() {
		return locco;
	}
	public void setLocco(String locco) {
		this.locco = locco;
	}
	public String getLoevm() {
		return loevm;
	}
	public void setLoevm(String loevm) {
		this.loevm = loevm;
	}
	public String getName3() {
		return name3;
	}
	public void setName3(String name3) {
		this.name3 = name3;
	}
	public String getName4() {
		return name4;
	}
	public void setName4(String name4) {
		this.name4 = name4;
	}
	public String getNiels() {
		return niels;
	}
	public void setNiels(String niels) {
		this.niels = niels;
	}
	public String getOrt02() {
		return ort02;
	}
	public void setOrt02(String ort02) {
		this.ort02 = ort02;
	}
	public String getPfach() {
		return pfach;
	}
	public void setPfach(String pfach) {
		this.pfach = pfach;
	}
	public String getPstl2() {
		return pstl2;
	}
	public void setPstl2(String pstl2) {
		this.pstl2 = pstl2;
	}
	public String getCounc() {
		return counc;
	}
	public void setCounc(String counc) {
		this.counc = counc;
	}
	public String getCityc() {
		return cityc;
	}
	public void setCityc(String cityc) {
		this.cityc = cityc;
	}
	public String getRpmkr() {
		return rpmkr;
	}
	public void setRpmkr(String rpmkr) {
		this.rpmkr = rpmkr;
	}
	public String getSperr() {
		return sperr;
	}
	public void setSperr(String sperr) {
		this.sperr = sperr;
	}
	public String getSpras() {
		return spras;
	}
	public void setSpras(String spras) {
		this.spras = spras;
	}
	public String getStcd1() {
		return stcd1;
	}
	public void setStcd1(String stcd1) {
		this.stcd1 = stcd1;
	}
	public String getStcd2() {
		return stcd2;
	}
	public void setStcd2(String stcd2) {
		this.stcd2 = stcd2;
	}
	public String getStkza() {
		return stkza;
	}
	public void setStkza(String stkza) {
		this.stkza = stkza;
	}
	public String getStkzu() {
		return stkzu;
	}
	public void setStkzu(String stkzu) {
		this.stkzu = stkzu;
	}
	public String getTelbx() {
		return telbx;
	}
	public void setTelbx(String telbx) {
		this.telbx = telbx;
	}
	public String getTelf2() {
		return telf2;
	}
	public void setTelf2(String telf2) {
		this.telf2 = telf2;
	}
	public String getTeltx() {
		return teltx;
	}
	public void setTeltx(String teltx) {
		this.teltx = teltx;
	}
	public String getTelx1() {
		return telx1;
	}
	public void setTelx1(String telx1) {
		this.telx1 = telx1;
	}
	public String getLzone() {
		return lzone;
	}
	public void setLzone(String lzone) {
		this.lzone = lzone;
	}
	public String getXzemp() {
		return xzemp;
	}
	public void setXzemp(String xzemp) {
		this.xzemp = xzemp;
	}
	public String getVbund() {
		return vbund;
	}
	public void setVbund(String vbund) {
		this.vbund = vbund;
	}
	public String getStceg() {
		return stceg;
	}
	public void setStceg(String stceg) {
		this.stceg = stceg;
	}
	public String getDear1() {
		return dear1;
	}
	public void setDear1(String dear1) {
		this.dear1 = dear1;
	}
	public String getDear2() {
		return dear2;
	}
	public void setDear2(String dear2) {
		this.dear2 = dear2;
	}
	public String getDear3() {
		return dear3;
	}
	public void setDear3(String dear3) {
		this.dear3 = dear3;
	}
	public String getDear4() {
		return dear4;
	}
	public void setDear4(String dear4) {
		this.dear4 = dear4;
	}
	public String getDear5() {
		return dear5;
	}
	public void setDear5(String dear5) {
		this.dear5 = dear5;
	}
	public String getGform() {
		return gform;
	}
	public void setGform(String gform) {
		this.gform = gform;
	}
	public String getBran1() {
		return bran1;
	}
	public void setBran1(String bran1) {
		this.bran1 = bran1;
	}
	public String getBran2() {
		return bran2;
	}
	public void setBran2(String bran2) {
		this.bran2 = bran2;
	}
	public String getBran3() {
		return bran3;
	}
	public void setBran3(String bran3) {
		this.bran3 = bran3;
	}
	public String getBran4() {
		return bran4;
	}
	public void setBran4(String bran4) {
		this.bran4 = bran4;
	}
	public String getBran5() {
		return bran5;
	}
	public void setBran5(String bran5) {
		this.bran5 = bran5;
	}
	public String getEkont() {
		return ekont;
	}
	public void setEkont(String ekont) {
		this.ekont = ekont;
	}
	public double getUmsat() {
		return umsat;
	}
	public void setUmsat(double umsat) {
		this.umsat = umsat;
	}
	public String getUmjah() {
		return umjah;
	}
	public void setUmjah(String umjah) {
		this.umjah = umjah;
	}
	public String getUwaer() {
		return uwaer;
	}
	public void setUwaer(String uwaer) {
		this.uwaer = uwaer;
	}
	public String getJmzah() {
		return jmzah;
	}
	public void setJmzah(String jmzah) {
		this.jmzah = jmzah;
	}
	public String getJmjah() {
		return jmjah;
	}
	public void setJmjah(String jmjah) {
		this.jmjah = jmjah;
	}
	public String getKatr1() {
		return katr1;
	}
	public void setKatr1(String katr1) {
		this.katr1 = katr1;
	}
	public String getKatr2() {
		return katr2;
	}
	public void setKatr2(String katr2) {
		this.katr2 = katr2;
	}
	public String getKatr3() {
		return katr3;
	}
	public void setKatr3(String katr3) {
		this.katr3 = katr3;
	}
	public String getKatr4() {
		return katr4;
	}
	public void setKatr4(String katr4) {
		this.katr4 = katr4;
	}
	public String getKatr5() {
		return katr5;
	}
	public void setKatr5(String katr5) {
		this.katr5 = katr5;
	}
	public String getKatr6() {
		return katr6;
	}
	public void setKatr6(String katr6) {
		this.katr6 = katr6;
	}
	public String getKatr7() {
		return katr7;
	}
	public void setKatr7(String katr7) {
		this.katr7 = katr7;
	}
	public String getKatr8() {
		return katr8;
	}
	public void setKatr8(String katr8) {
		this.katr8 = katr8;
	}
	public String getKatr9() {
		return katr9;
	}
	public void setKatr9(String katr9) {
		this.katr9 = katr9;
	}
	public String getKatr10() {
		return katr10;
	}
	public void setKatr10(String katr10) {
		this.katr10 = katr10;
	}
	public String getStkzn() {
		return stkzn;
	}
	public void setStkzn(String stkzn) {
		this.stkzn = stkzn;
	}
	public double getUmsa1() {
		return umsa1;
	}
	public void setUmsa1(double umsa1) {
		this.umsa1 = umsa1;
	}
	public String getTxjcd() {
		return txjcd;
	}
	public void setTxjcd(String txjcd) {
		this.txjcd = txjcd;
	}
	public String getPeriv() {
		return periv;
	}
	public void setPeriv(String periv) {
		this.periv = periv;
	}
	public String getAbrvw() {
		return abrvw;
	}
	public void setAbrvw(String abrvw) {
		this.abrvw = abrvw;
	}
	public String getInspbydebi() {
		return inspbydebi;
	}
	public void setInspbydebi(String inspbydebi) {
		this.inspbydebi = inspbydebi;
	}
	public String getInspatdebi() {
		return inspatdebi;
	}
	public void setInspatdebi(String inspatdebi) {
		this.inspatdebi = inspatdebi;
	}
	public String getKtocd() {
		return ktocd;
	}
	public void setKtocd(String ktocd) {
		this.ktocd = ktocd;
	}
	public String getPfort() {
		return pfort;
	}
	public void setPfort(String pfort) {
		this.pfort = pfort;
	}
	public String getWerks() {
		return werks;
	}
	public void setWerks(String werks) {
		this.werks = werks;
	}
	public String getDtams() {
		return dtams;
	}
	public void setDtams(String dtams) {
		this.dtams = dtams;
	}
	public String getDtaws() {
		return dtaws;
	}
	public void setDtaws(String dtaws) {
		this.dtaws = dtaws;
	}
	public String getDuefl() {
		return duefl;
	}
	public void setDuefl(String duefl) {
		this.duefl = duefl;
	}
	public String getHzuor() {
		return hzuor;
	}
	public void setHzuor(String hzuor) {
		this.hzuor = hzuor;
	}
	public String getSperz() {
		return sperz;
	}
	public void setSperz(String sperz) {
		this.sperz = sperz;
	}
	public String getEtikg() {
		return etikg;
	}
	public void setEtikg(String etikg) {
		this.etikg = etikg;
	}
	public String getCivve() {
		return civve;
	}
	public void setCivve(String civve) {
		this.civve = civve;
	}
	public String getMilve() {
		return milve;
	}
	public void setMilve(String milve) {
		this.milve = milve;
	}
	public String getKdkg1() {
		return kdkg1;
	}
	public void setKdkg1(String kdkg1) {
		this.kdkg1 = kdkg1;
	}
	public String getKdkg2() {
		return kdkg2;
	}
	public void setKdkg2(String kdkg2) {
		this.kdkg2 = kdkg2;
	}
	public String getKdkg3() {
		return kdkg3;
	}
	public void setKdkg3(String kdkg3) {
		this.kdkg3 = kdkg3;
	}
	public String getKdkg4() {
		return kdkg4;
	}
	public void setKdkg4(String kdkg4) {
		this.kdkg4 = kdkg4;
	}
	public String getKdkg5() {
		return kdkg5;
	}
	public void setKdkg5(String kdkg5) {
		this.kdkg5 = kdkg5;
	}
	public String getXknza() {
		return xknza;
	}
	public void setXknza(String xknza) {
		this.xknza = xknza;
	}
	public String getFityp() {
		return fityp;
	}
	public void setFityp(String fityp) {
		this.fityp = fityp;
	}
	public String getStcdt() {
		return stcdt;
	}
	public void setStcdt(String stcdt) {
		this.stcdt = stcdt;
	}
	public String getStcd3() {
		return stcd3;
	}
	public void setStcd3(String stcd3) {
		this.stcd3 = stcd3;
	}
	public String getStcd4() {
		return stcd4;
	}
	public void setStcd4(String stcd4) {
		this.stcd4 = stcd4;
	}
	public String getXicms() {
		return xicms;
	}
	public void setXicms(String xicms) {
		this.xicms = xicms;
	}
	public String getXxipi() {
		return xxipi;
	}
	public void setXxipi(String xxipi) {
		this.xxipi = xxipi;
	}
	public String getXsubt() {
		return xsubt;
	}
	public void setXsubt(String xsubt) {
		this.xsubt = xsubt;
	}
	public String getCfopc() {
		return cfopc;
	}
	public void setCfopc(String cfopc) {
		this.cfopc = cfopc;
	}
	public String getTxlw1() {
		return txlw1;
	}
	public void setTxlw1(String txlw1) {
		this.txlw1 = txlw1;
	}
	public String getTxlw2() {
		return txlw2;
	}
	public void setTxlw2(String txlw2) {
		this.txlw2 = txlw2;
	}
	public String getCcc01() {
		return ccc01;
	}
	public void setCcc01(String ccc01) {
		this.ccc01 = ccc01;
	}
	public String getCcc02() {
		return ccc02;
	}
	public void setCcc02(String ccc02) {
		this.ccc02 = ccc02;
	}
	public String getCcc03() {
		return ccc03;
	}
	public void setCcc03(String ccc03) {
		this.ccc03 = ccc03;
	}
	public String getCcc04() {
		return ccc04;
	}
	public void setCcc04(String ccc04) {
		this.ccc04 = ccc04;
	}
	public String getCassd() {
		return cassd;
	}
	public void setCassd(String cassd) {
		this.cassd = cassd;
	}
	public String getKnurl() {
		return knurl;
	}
	public void setKnurl(String knurl) {
		this.knurl = knurl;
	}
	public String getJ_1kfrepre() {
		return j_1kfrepre;
	}
	public void setJ_1kfrepre(String j_1kfrepre) {
		this.j_1kfrepre = j_1kfrepre;
	}
	public String getJ_1kftbus() {
		return j_1kftbus;
	}
	public void setJ_1kftbus(String j_1kftbus) {
		this.j_1kftbus = j_1kftbus;
	}
	public String getJ_1kftind() {
		return j_1kftind;
	}
	public void setJ_1kftind(String j_1kftind) {
		this.j_1kftind = j_1kftind;
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
	public String getDear6() {
		return dear6;
	}
	public void setDear6(String dear6) {
		this.dear6 = dear6;
	}
	public double getVsor_palhgt() {
		return vsor_palhgt;
	}
	public void setVsor_palhgt(double vsor_palhgt) {
		this.vsor_palhgt = vsor_palhgt;
	}
	public String getVsor_pal_ul() {
		return vsor_pal_ul;
	}
	public void setVsor_pal_ul(String vsor_pal_ul) {
		this.vsor_pal_ul = vsor_pal_ul;
	}
	public String getVsor_pk_mat() {
		return vsor_pk_mat;
	}
	public void setVsor_pk_mat(String vsor_pk_mat) {
		this.vsor_pk_mat = vsor_pk_mat;
	}
	public String getVsor_matpal() {
		return vsor_matpal;
	}
	public void setVsor_matpal(String vsor_matpal) {
		this.vsor_matpal = vsor_matpal;
	}
	public String getVsor_i_no_lyr() {
		return vsor_i_no_lyr;
	}
	public void setVsor_i_no_lyr(String vsor_i_no_lyr) {
		this.vsor_i_no_lyr = vsor_i_no_lyr;
	}
	public String getVsor_one_mat() {
		return vsor_one_mat;
	}
	public void setVsor_one_mat(String vsor_one_mat) {
		this.vsor_one_mat = vsor_one_mat;
	}
	public String getVsor_one_sort() {
		return vsor_one_sort;
	}
	public void setVsor_one_sort(String vsor_one_sort) {
		this.vsor_one_sort = vsor_one_sort;
	}
	public String getVsor_uld_side() {
		return vsor_uld_side;
	}
	public void setVsor_uld_side(String vsor_uld_side) {
		this.vsor_uld_side = vsor_uld_side;
	}
	public String getVsor_load_pref() {
		return vsor_load_pref;
	}
	public void setVsor_load_pref(String vsor_load_pref) {
		this.vsor_load_pref = vsor_load_pref;
	}
	public String getVsor_dpoint() {
		return vsor_dpoint;
	}
	public void setVsor_dpoint(String vsor_dpoint) {
		this.vsor_dpoint = vsor_dpoint;
	}
	public String getAlc() {
		return alc;
	}
	public void setAlc(String alc) {
		this.alc = alc;
	}
	public String getPmt_office() {
		return pmt_office;
	}
	public void setPmt_office(String pmt_office) {
		this.pmt_office = pmt_office;
	}
	public String getPsofg() {
		return psofg;
	}
	public void setPsofg(String psofg) {
		this.psofg = psofg;
	}
	public String getPsois() {
		return psois;
	}
	public void setPsois(String psois) {
		this.psois = psois;
	}
	public String getPson1() {
		return pson1;
	}
	public void setPson1(String pson1) {
		this.pson1 = pson1;
	}
	public String getPson2() {
		return pson2;
	}
	public void setPson2(String pson2) {
		this.pson2 = pson2;
	}
	public String getPson3() {
		return pson3;
	}
	public void setPson3(String pson3) {
		this.pson3 = pson3;
	}
	public String getPsovn() {
		return psovn;
	}
	public void setPsovn(String psovn) {
		this.psovn = psovn;
	}
	public String getPsotl() {
		return psotl;
	}
	public void setPsotl(String psotl) {
		this.psotl = psotl;
	}
	public String getPsohs() {
		return psohs;
	}
	public void setPsohs(String psohs) {
		this.psohs = psohs;
	}
	public String getPsost() {
		return psost;
	}
	public void setPsost(String psost) {
		this.psost = psost;
	}
	public String getPsoo1() {
		return psoo1;
	}
	public void setPsoo1(String psoo1) {
		this.psoo1 = psoo1;
	}
	public String getPsoo2() {
		return psoo2;
	}
	public void setPsoo2(String psoo2) {
		this.psoo2 = psoo2;
	}
	public String getPsoo3() {
		return psoo3;
	}
	public void setPsoo3(String psoo3) {
		this.psoo3 = psoo3;
	}
	public String getPsoo4() {
		return psoo4;
	}
	public void setPsoo4(String psoo4) {
		this.psoo4 = psoo4;
	}
	public String getPsoo5() {
		return psoo5;
	}
	public void setPsoo5(String psoo5) {
		this.psoo5 = psoo5;
	}
	public int getGarden_flag() {
		return garden_flag;
	}
	public void setGarden_flag(int garden_flag) {
		this.garden_flag = garden_flag;
	}
	
	
}
