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

import org.springframework.format.annotation.DateTimeFormat;

import com.sapGarden.application.commons.log.model.CommonLogModel;
@Entity
@Table(name="kna1_log")
public class Kna1Log extends CommonLogModel implements Serializable{

	


	/**
	 * 
	 */
	private static final long serialVersionUID = 5631765794491750909L;
	@Id
//	@TableGenerator(name = "kna1_log_gen", //该表主键生成策略的名称,被@GeneratedValue.generator引用。
//	                table = "sys_tb_generator",       //表生成策略所持久化的表名。
//	                pkColumnName = "gen_name",    //在持久化的表中，该主键生成策略所对应键值的名称。
//	                valueColumnName = "gen_value", //在持久化的表中， 该主键当前所生成的值，它的值将会随着每次创建而加。
//	                pkColumnValue = "kna1_log_pk",//在持久化的表中，该生成策略所对应的主键
//	                initialValue = 100,             //默认主键值为50
//	                allocationSize = 1)           //每次主键值增加的大小
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "kna1_log_gen")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
	private	String	mandt_old	;
	private	String	kunnr_old	;
	private	String	land1_old	;
	private	String	name1_old	;
	private	String	name2_old	;
	private	String	ort01_old	;
	private	String	pstlz_old	;
	private	String	regio_old	;
	private	String	sortl_old	;
	private	String	stras_old	;
	private	String	telf1_old	;
	private	String	telfx_old	;
	private	String	xcpdk_old	;
	private	String	adrnr_old	;
	private	String	mcod1_old	;
	private	String	mcod2_old	;
	private	String	mcod3_old	;
	private	String	anred_old	;
	private	String	aufsd_old	;
	private	String	bahne_old	;
	private	String	bahns_old	;
	private	String	bbbnr_old	;
	private	String	bbsnr_old	;
	private	String	begru_old	;
	private	String	brsch_old	;
	private	String	bubkz_old	;
	private	String	datlt_old	;
	private	String	erdat_old	;
	private	String	ernam_old	;
	private	String	exabl_old	;
	private	String	faksd_old	;
	private	String	fiskn_old	;
	private	String	knazk_old	;
	private	String	knrza_old	;
	private	String	konzs_old	;
	private	String	ktokd_old	;
	private	String	kukla_old	;
	private	String	lifnr_old	;
	private	String	lifsd_old	;
	private	String	locco_old	;
	private	String	loevm_old	;
	private	String	name3_old	;
	private	String	name4_old	;
	private	String	niels_old	;
	private	String	ort02_old	;
	private	String	pfach_old	;
	private	String	pstl2_old	;
	private	String	counc_old	;
	private	String	cityc_old	;
	private	String	rpmkr_old	;
	private	String	sperr_old	;
	private	String	spras_old	;
	private	String	stcd1_old	;
	private	String	stcd2_old	;
	private	String	stkza_old	;
	private	String	stkzu_old	;
	private	String	telbx_old	;
	private	String	telf2_old	;
	private	String	teltx_old	;
	private	String	telx1_old	;
	private	String	lzone_old	;
	private	String	xzemp_old	;
	private	String	vbund_old	;
	private	String	stceg_old	;
	private	String	dear1_old	;
	private	String	dear2_old	;
	private	String	dear3_old	;
	private	String	dear4_old	;
	private	String	dear5_old	;
	private	String	gform_old	;
	private	String	bran1_old	;
	private	String	bran2_old	;
	private	String	bran3_old	;
	private	String	bran4_old	;
	private	String	bran5_old	;
	private	String	ekont_old	;
	private	double	umsat_old	;
	private	String	umjah_old	;
	private	String	uwaer_old	;
	private	String	jmzah_old	;
	private	String	jmjah_old	;
	private	String	katr1_old	;
	private	String	katr2_old	;
	private	String	katr3_old	;
	private	String	katr4_old	;
	private	String	katr5_old	;
	private	String	katr6_old	;
	private	String	katr7_old	;
	private	String	katr8_old	;
	private	String	katr9_old	;
	private	String	katr10_old	;
	private	String	stkzn_old	;
	private	long	umsa1_old	;
	private	String	txjcd_old	;
	private	String	periv_old	;
	private	String	abrvw_old	;
	private	String	ktocd_old	;
	private	String	pfort_old	;
	private	String	werks_old	;
	private	String	dtams_old	;
	private	String	dtaws_old	;
	private	String	duefl_old	;
	private	String	hzuor_old	;
	private	String	sperz_old	;
	private	String	etikg_old	;
	private	String	civve_old	;
	private	String	milve_old	;
	private	String	kdkg1_old	;
	private	String	kdkg2_old	;
	private	String	kdkg3_old	;
	private	String	kdkg4_old	;
	private	String	kdkg5_old	;
	private	String	xknza_old	;
	private	String	fityp_old	;
	private	String	stcdt_old	;
	private	String	stcd3_old	;
	private	String	stcd4_old	;
	private	String	xicms_old	;
	private	String	xxipi_old	;
	private	String	xsubt_old	;
	private	String	cfopc_old	;
	private	String	txlw1_old	;
	private	String	txlw2_old	;
	private	String	ccc01_old	;
	private	String	ccc02_old	;
	private	String	ccc03_old	;
	private	String	ccc04_old	;
	private	String	cassd_old	;
	private	String	knurl_old	;
	private	String	confs_old	;
	private	String	updat_old	;
	private	String	uptim_old	;
	private	String	nodel_old	;
	private	String	dear6_old	;
	private	String	psofg_old	;
	private	String	psois_old	;
	private	String	pson1_old	;
	private	String	pson2_old	;
	private	String	pson3_old	;
	private	String	psovn_old	;
	private	String	psotl_old	;
	private	String	psohs_old	;
	private	String	psost_old	;
	private	String	psoo1_old	;
	private	String	psoo2_old	;
	private	String	psoo3_old	;
	private	String	psoo4_old	;
	private	String	psoo5_old	;
	private	double	vsor_palhgt_old	;
	private	String	vsor_pal_ul_old	;
	private	String	vsor_pk_mat_old	;
	private	String	vsor_matpal_old	;
	private	String	vsor_i_no_lyr_old	;
	private	String	vsor_one_mat_old	;
	private	String	vsor_one_sort_old	;
	private	String	vsor_uld_side_old	;
	private	String	vsor_load_pref_old	;
	private	String	vsor_dpoint_old	;
	private	String	alc_old	;
	private	String	pmt_office_old	;
	private	String	j_1kfrepre_old	;
	private	String	j_1kftbus_old	;
	private	String	j_1kftind_old	;
	private	String	inspbydebi_old	;
	private	String	inspatdebi_old	;
	public Kna1Log(){
		
	}
	/*
	public Kna1Log(long sapclient,Kna1 skna1,Kna1 gkna1) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		this.sapclient=sapclient;
		if(skna1!=null){
			Class modelClass = skna1.getClass();
			Field[] fields = modelClass.getDeclaredFields();
			for(int j=0;j<fields.length;j++){
				String name = fields[j].getName();
				if(!name.equals("serialVersionUID")&&!name.equals("id")&&!name.equals("sapclient")){
					Class fieldType = fields[j].getType();
					String getMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
					Method getMethod = modelClass.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(skna1, new Object[]{});
					String methodName = "set"+name.substring(0, 1).toUpperCase()+name.substring(1);
					Method method = Kna1Log.class.getMethod(methodName, new Class[]{fieldType});
					method.invoke(this, new Object[]{value});
				}
			}
		}
		if(gkna1!=null){
			Class modelClass = gkna1.getClass();
			Field[] fields = modelClass.getDeclaredFields();
			this.id=gkna1.getId();
			for(int j=0;j<fields.length;j++){
				String name = fields[j].getName();
				if(!name.equals("serialVersionUID")&&!name.equals("id")&&!name.equals("sapclient")){
					Class fieldType = fields[j].getType();
					String getMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
					Method getMethod = modelClass.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(gkna1, new Object[]{});
					String methodName = "set"+name.substring(0, 1).toUpperCase()+name.substring(1)+"_old";
					Method method = Kna1Log.class.getMethod(methodName, new Class[]{fieldType});
					method.invoke(this, new Object[]{value});
				}
			}
		}
	}
	*/
	/**
	 * 用于记录日志时，构造日志对象
	 */
	public Kna1Log(Kna1 kna1,long sapclient,String optmsg,String opt,String optflag,String opttype,String optuser,Date opttime) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		this.sapclient=sapclient;
		this.opt=opt;
		this.optflag=optflag;
		this.optmsg=optmsg;
		this.opttime=opttime;
		this.optuser=optuser;
		this.opttype=opttype;
		if(kna1!=null){
			this.optid=kna1.getId();
			Class modelClass = kna1.getClass();
			Field[] fields = modelClass.getDeclaredFields();
			for(int j=0;j<fields.length;j++){
				String name = fields[j].getName();
				if(!name.equals("serialVersionUID")&&!name.equals("id")&&!name.equals("sapclient")&&!name.equals("garden_flag")){
					Class fieldType = fields[j].getType();
					String getMethodName = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
					Method getMethod = modelClass.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(kna1, new Object[]{});
					String methodName = "set"+name.substring(0, 1).toUpperCase()+name.substring(1);
					Method method = Kna1Log.class.getMethod(methodName, new Class[]{fieldType});
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
	public String getLand1_old() {
		return land1_old;
	}
	public void setLand1_old(String land1_old) {
		this.land1_old = land1_old;
	}
	public String getName1_old() {
		return name1_old;
	}
	public void setName1_old(String name1_old) {
		this.name1_old = name1_old;
	}
	public String getName2_old() {
		return name2_old;
	}
	public void setName2_old(String name2_old) {
		this.name2_old = name2_old;
	}
	public String getOrt01_old() {
		return ort01_old;
	}
	public void setOrt01_old(String ort01_old) {
		this.ort01_old = ort01_old;
	}
	public String getPstlz_old() {
		return pstlz_old;
	}
	public void setPstlz_old(String pstlz_old) {
		this.pstlz_old = pstlz_old;
	}
	public String getRegio_old() {
		return regio_old;
	}
	public void setRegio_old(String regio_old) {
		this.regio_old = regio_old;
	}
	public String getSortl_old() {
		return sortl_old;
	}
	public void setSortl_old(String sortl_old) {
		this.sortl_old = sortl_old;
	}
	public String getStras_old() {
		return stras_old;
	}
	public void setStras_old(String stras_old) {
		this.stras_old = stras_old;
	}
	public String getTelf1_old() {
		return telf1_old;
	}
	public void setTelf1_old(String telf1_old) {
		this.telf1_old = telf1_old;
	}
	public String getTelfx_old() {
		return telfx_old;
	}
	public void setTelfx_old(String telfx_old) {
		this.telfx_old = telfx_old;
	}
	public String getXcpdk_old() {
		return xcpdk_old;
	}
	public void setXcpdk_old(String xcpdk_old) {
		this.xcpdk_old = xcpdk_old;
	}
	public String getAdrnr_old() {
		return adrnr_old;
	}
	public void setAdrnr_old(String adrnr_old) {
		this.adrnr_old = adrnr_old;
	}
	public String getMcod1_old() {
		return mcod1_old;
	}
	public void setMcod1_old(String mcod1_old) {
		this.mcod1_old = mcod1_old;
	}
	public String getMcod2_old() {
		return mcod2_old;
	}
	public void setMcod2_old(String mcod2_old) {
		this.mcod2_old = mcod2_old;
	}
	public String getMcod3_old() {
		return mcod3_old;
	}
	public void setMcod3_old(String mcod3_old) {
		this.mcod3_old = mcod3_old;
	}
	public String getAnred_old() {
		return anred_old;
	}
	public void setAnred_old(String anred_old) {
		this.anred_old = anred_old;
	}
	public String getAufsd_old() {
		return aufsd_old;
	}
	public void setAufsd_old(String aufsd_old) {
		this.aufsd_old = aufsd_old;
	}
	public String getBahne_old() {
		return bahne_old;
	}
	public void setBahne_old(String bahne_old) {
		this.bahne_old = bahne_old;
	}
	public String getBahns_old() {
		return bahns_old;
	}
	public void setBahns_old(String bahns_old) {
		this.bahns_old = bahns_old;
	}
	public String getBbbnr_old() {
		return bbbnr_old;
	}
	public void setBbbnr_old(String bbbnr_old) {
		this.bbbnr_old = bbbnr_old;
	}
	public String getBbsnr_old() {
		return bbsnr_old;
	}
	public void setBbsnr_old(String bbsnr_old) {
		this.bbsnr_old = bbsnr_old;
	}
	public String getBegru_old() {
		return begru_old;
	}
	public void setBegru_old(String begru_old) {
		this.begru_old = begru_old;
	}
	public String getBrsch_old() {
		return brsch_old;
	}
	public void setBrsch_old(String brsch_old) {
		this.brsch_old = brsch_old;
	}
	public String getBubkz_old() {
		return bubkz_old;
	}
	public void setBubkz_old(String bubkz_old) {
		this.bubkz_old = bubkz_old;
	}
	public String getDatlt_old() {
		return datlt_old;
	}
	public void setDatlt_old(String datlt_old) {
		this.datlt_old = datlt_old;
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
	public String getExabl_old() {
		return exabl_old;
	}
	public void setExabl_old(String exabl_old) {
		this.exabl_old = exabl_old;
	}
	public String getFaksd_old() {
		return faksd_old;
	}
	public void setFaksd_old(String faksd_old) {
		this.faksd_old = faksd_old;
	}
	public String getFiskn_old() {
		return fiskn_old;
	}
	public void setFiskn_old(String fiskn_old) {
		this.fiskn_old = fiskn_old;
	}
	public String getKnazk_old() {
		return knazk_old;
	}
	public void setKnazk_old(String knazk_old) {
		this.knazk_old = knazk_old;
	}
	public String getKnrza_old() {
		return knrza_old;
	}
	public void setKnrza_old(String knrza_old) {
		this.knrza_old = knrza_old;
	}
	public String getKonzs_old() {
		return konzs_old;
	}
	public void setKonzs_old(String konzs_old) {
		this.konzs_old = konzs_old;
	}
	public String getKtokd_old() {
		return ktokd_old;
	}
	public void setKtokd_old(String ktokd_old) {
		this.ktokd_old = ktokd_old;
	}
	public String getKukla_old() {
		return kukla_old;
	}
	public void setKukla_old(String kukla_old) {
		this.kukla_old = kukla_old;
	}
	public String getLifnr_old() {
		return lifnr_old;
	}
	public void setLifnr_old(String lifnr_old) {
		this.lifnr_old = lifnr_old;
	}
	public String getLifsd_old() {
		return lifsd_old;
	}
	public void setLifsd_old(String lifsd_old) {
		this.lifsd_old = lifsd_old;
	}
	public String getLocco_old() {
		return locco_old;
	}
	public void setLocco_old(String locco_old) {
		this.locco_old = locco_old;
	}
	public String getLoevm_old() {
		return loevm_old;
	}
	public void setLoevm_old(String loevm_old) {
		this.loevm_old = loevm_old;
	}
	public String getName3_old() {
		return name3_old;
	}
	public void setName3_old(String name3_old) {
		this.name3_old = name3_old;
	}
	public String getName4_old() {
		return name4_old;
	}
	public void setName4_old(String name4_old) {
		this.name4_old = name4_old;
	}
	public String getNiels_old() {
		return niels_old;
	}
	public void setNiels_old(String niels_old) {
		this.niels_old = niels_old;
	}
	public String getOrt02_old() {
		return ort02_old;
	}
	public void setOrt02_old(String ort02_old) {
		this.ort02_old = ort02_old;
	}
	public String getPfach_old() {
		return pfach_old;
	}
	public void setPfach_old(String pfach_old) {
		this.pfach_old = pfach_old;
	}
	public String getPstl2_old() {
		return pstl2_old;
	}
	public void setPstl2_old(String pstl2_old) {
		this.pstl2_old = pstl2_old;
	}
	public String getCounc_old() {
		return counc_old;
	}
	public void setCounc_old(String counc_old) {
		this.counc_old = counc_old;
	}
	public String getCityc_old() {
		return cityc_old;
	}
	public void setCityc_old(String cityc_old) {
		this.cityc_old = cityc_old;
	}
	public String getRpmkr_old() {
		return rpmkr_old;
	}
	public void setRpmkr_old(String rpmkr_old) {
		this.rpmkr_old = rpmkr_old;
	}
	public String getSperr_old() {
		return sperr_old;
	}
	public void setSperr_old(String sperr_old) {
		this.sperr_old = sperr_old;
	}
	public String getSpras_old() {
		return spras_old;
	}
	public void setSpras_old(String spras_old) {
		this.spras_old = spras_old;
	}
	public String getStcd1_old() {
		return stcd1_old;
	}
	public void setStcd1_old(String stcd1_old) {
		this.stcd1_old = stcd1_old;
	}
	public String getStcd2_old() {
		return stcd2_old;
	}
	public void setStcd2_old(String stcd2_old) {
		this.stcd2_old = stcd2_old;
	}
	public String getStkza_old() {
		return stkza_old;
	}
	public void setStkza_old(String stkza_old) {
		this.stkza_old = stkza_old;
	}
	public String getStkzu_old() {
		return stkzu_old;
	}
	public void setStkzu_old(String stkzu_old) {
		this.stkzu_old = stkzu_old;
	}
	public String getTelbx_old() {
		return telbx_old;
	}
	public void setTelbx_old(String telbx_old) {
		this.telbx_old = telbx_old;
	}
	public String getTelf2_old() {
		return telf2_old;
	}
	public void setTelf2_old(String telf2_old) {
		this.telf2_old = telf2_old;
	}
	public String getTeltx_old() {
		return teltx_old;
	}
	public void setTeltx_old(String teltx_old) {
		this.teltx_old = teltx_old;
	}
	public String getTelx1_old() {
		return telx1_old;
	}
	public void setTelx1_old(String telx1_old) {
		this.telx1_old = telx1_old;
	}
	public String getLzone_old() {
		return lzone_old;
	}
	public void setLzone_old(String lzone_old) {
		this.lzone_old = lzone_old;
	}
	public String getXzemp_old() {
		return xzemp_old;
	}
	public void setXzemp_old(String xzemp_old) {
		this.xzemp_old = xzemp_old;
	}
	public String getVbund_old() {
		return vbund_old;
	}
	public void setVbund_old(String vbund_old) {
		this.vbund_old = vbund_old;
	}
	public String getStceg_old() {
		return stceg_old;
	}
	public void setStceg_old(String stceg_old) {
		this.stceg_old = stceg_old;
	}
	public String getDear1_old() {
		return dear1_old;
	}
	public void setDear1_old(String dear1_old) {
		this.dear1_old = dear1_old;
	}
	public String getDear2_old() {
		return dear2_old;
	}
	public void setDear2_old(String dear2_old) {
		this.dear2_old = dear2_old;
	}
	public String getDear3_old() {
		return dear3_old;
	}
	public void setDear3_old(String dear3_old) {
		this.dear3_old = dear3_old;
	}
	public String getDear4_old() {
		return dear4_old;
	}
	public void setDear4_old(String dear4_old) {
		this.dear4_old = dear4_old;
	}
	public String getDear5_old() {
		return dear5_old;
	}
	public void setDear5_old(String dear5_old) {
		this.dear5_old = dear5_old;
	}
	public String getGform_old() {
		return gform_old;
	}
	public void setGform_old(String gform_old) {
		this.gform_old = gform_old;
	}
	public String getBran1_old() {
		return bran1_old;
	}
	public void setBran1_old(String bran1_old) {
		this.bran1_old = bran1_old;
	}
	public String getBran2_old() {
		return bran2_old;
	}
	public void setBran2_old(String bran2_old) {
		this.bran2_old = bran2_old;
	}
	public String getBran3_old() {
		return bran3_old;
	}
	public void setBran3_old(String bran3_old) {
		this.bran3_old = bran3_old;
	}
	public String getBran4_old() {
		return bran4_old;
	}
	public void setBran4_old(String bran4_old) {
		this.bran4_old = bran4_old;
	}
	public String getBran5_old() {
		return bran5_old;
	}
	public void setBran5_old(String bran5_old) {
		this.bran5_old = bran5_old;
	}
	public String getEkont_old() {
		return ekont_old;
	}
	public void setEkont_old(String ekont_old) {
		this.ekont_old = ekont_old;
	}
	public double getUmsat_old() {
		return umsat_old;
	}
	public void setUmsat_old(double umsat_old) {
		this.umsat_old = umsat_old;
	}
	public String getUmjah_old() {
		return umjah_old;
	}
	public void setUmjah_old(String umjah_old) {
		this.umjah_old = umjah_old;
	}
	public String getUwaer_old() {
		return uwaer_old;
	}
	public void setUwaer_old(String uwaer_old) {
		this.uwaer_old = uwaer_old;
	}
	public String getJmzah_old() {
		return jmzah_old;
	}
	public void setJmzah_old(String jmzah_old) {
		this.jmzah_old = jmzah_old;
	}
	public String getJmjah_old() {
		return jmjah_old;
	}
	public void setJmjah_old(String jmjah_old) {
		this.jmjah_old = jmjah_old;
	}
	public String getKatr1_old() {
		return katr1_old;
	}
	public void setKatr1_old(String katr1_old) {
		this.katr1_old = katr1_old;
	}
	public String getKatr2_old() {
		return katr2_old;
	}
	public void setKatr2_old(String katr2_old) {
		this.katr2_old = katr2_old;
	}
	public String getKatr3_old() {
		return katr3_old;
	}
	public void setKatr3_old(String katr3_old) {
		this.katr3_old = katr3_old;
	}
	public String getKatr4_old() {
		return katr4_old;
	}
	public void setKatr4_old(String katr4_old) {
		this.katr4_old = katr4_old;
	}
	public String getKatr5_old() {
		return katr5_old;
	}
	public void setKatr5_old(String katr5_old) {
		this.katr5_old = katr5_old;
	}
	public String getKatr6_old() {
		return katr6_old;
	}
	public void setKatr6_old(String katr6_old) {
		this.katr6_old = katr6_old;
	}
	public String getKatr7_old() {
		return katr7_old;
	}
	public void setKatr7_old(String katr7_old) {
		this.katr7_old = katr7_old;
	}
	public String getKatr8_old() {
		return katr8_old;
	}
	public void setKatr8_old(String katr8_old) {
		this.katr8_old = katr8_old;
	}
	public String getKatr9_old() {
		return katr9_old;
	}
	public void setKatr9_old(String katr9_old) {
		this.katr9_old = katr9_old;
	}
	public String getKatr10_old() {
		return katr10_old;
	}
	public void setKatr10_old(String katr10_old) {
		this.katr10_old = katr10_old;
	}
	public String getStkzn_old() {
		return stkzn_old;
	}
	public void setStkzn_old(String stkzn_old) {
		this.stkzn_old = stkzn_old;
	}
	public Long getUmsa1_old() {
		return umsa1_old;
	}
	public void setUmsa1_old(Long umsa1_old) {
		this.umsa1_old = umsa1_old;
	}
	public String getTxjcd_old() {
		return txjcd_old;
	}
	public void setTxjcd_old(String txjcd_old) {
		this.txjcd_old = txjcd_old;
	}
	public String getPeriv_old() {
		return periv_old;
	}
	public void setPeriv_old(String periv_old) {
		this.periv_old = periv_old;
	}
	public String getAbrvw_old() {
		return abrvw_old;
	}
	public void setAbrvw_old(String abrvw_old) {
		this.abrvw_old = abrvw_old;
	}
	public String getKtocd_old() {
		return ktocd_old;
	}
	public void setKtocd_old(String ktocd_old) {
		this.ktocd_old = ktocd_old;
	}
	public String getPfort_old() {
		return pfort_old;
	}
	public void setPfort_old(String pfort_old) {
		this.pfort_old = pfort_old;
	}
	public String getWerks_old() {
		return werks_old;
	}
	public void setWerks_old(String werks_old) {
		this.werks_old = werks_old;
	}
	public String getDtams_old() {
		return dtams_old;
	}
	public void setDtams_old(String dtams_old) {
		this.dtams_old = dtams_old;
	}
	public String getDtaws_old() {
		return dtaws_old;
	}
	public void setDtaws_old(String dtaws_old) {
		this.dtaws_old = dtaws_old;
	}
	public String getDuefl_old() {
		return duefl_old;
	}
	public void setDuefl_old(String duefl_old) {
		this.duefl_old = duefl_old;
	}
	public String getHzuor_old() {
		return hzuor_old;
	}
	public void setHzuor_old(String hzuor_old) {
		this.hzuor_old = hzuor_old;
	}
	public String getSperz_old() {
		return sperz_old;
	}
	public void setSperz_old(String sperz_old) {
		this.sperz_old = sperz_old;
	}
	public String getEtikg_old() {
		return etikg_old;
	}
	public void setEtikg_old(String etikg_old) {
		this.etikg_old = etikg_old;
	}
	public String getCivve_old() {
		return civve_old;
	}
	public void setCivve_old(String civve_old) {
		this.civve_old = civve_old;
	}
	public String getMilve_old() {
		return milve_old;
	}
	public void setMilve_old(String milve_old) {
		this.milve_old = milve_old;
	}
	public String getKdkg1_old() {
		return kdkg1_old;
	}
	public void setKdkg1_old(String kdkg1_old) {
		this.kdkg1_old = kdkg1_old;
	}
	public String getKdkg2_old() {
		return kdkg2_old;
	}
	public void setKdkg2_old(String kdkg2_old) {
		this.kdkg2_old = kdkg2_old;
	}
	public String getKdkg3_old() {
		return kdkg3_old;
	}
	public void setKdkg3_old(String kdkg3_old) {
		this.kdkg3_old = kdkg3_old;
	}
	public String getKdkg4_old() {
		return kdkg4_old;
	}
	public void setKdkg4_old(String kdkg4_old) {
		this.kdkg4_old = kdkg4_old;
	}
	public String getKdkg5_old() {
		return kdkg5_old;
	}
	public void setKdkg5_old(String kdkg5_old) {
		this.kdkg5_old = kdkg5_old;
	}
	public String getXknza_old() {
		return xknza_old;
	}
	public void setXknza_old(String xknza_old) {
		this.xknza_old = xknza_old;
	}
	public String getFityp_old() {
		return fityp_old;
	}
	public void setFityp_old(String fityp_old) {
		this.fityp_old = fityp_old;
	}
	public String getStcdt_old() {
		return stcdt_old;
	}
	public void setStcdt_old(String stcdt_old) {
		this.stcdt_old = stcdt_old;
	}
	public String getStcd3_old() {
		return stcd3_old;
	}
	public void setStcd3_old(String stcd3_old) {
		this.stcd3_old = stcd3_old;
	}
	public String getStcd4_old() {
		return stcd4_old;
	}
	public void setStcd4_old(String stcd4_old) {
		this.stcd4_old = stcd4_old;
	}
	public String getXicms_old() {
		return xicms_old;
	}
	public void setXicms_old(String xicms_old) {
		this.xicms_old = xicms_old;
	}
	public String getXxipi_old() {
		return xxipi_old;
	}
	public void setXxipi_old(String xxipi_old) {
		this.xxipi_old = xxipi_old;
	}
	public String getXsubt_old() {
		return xsubt_old;
	}
	public void setXsubt_old(String xsubt_old) {
		this.xsubt_old = xsubt_old;
	}
	public String getCfopc_old() {
		return cfopc_old;
	}
	public void setCfopc_old(String cfopc_old) {
		this.cfopc_old = cfopc_old;
	}
	public String getTxlw1_old() {
		return txlw1_old;
	}
	public void setTxlw1_old(String txlw1_old) {
		this.txlw1_old = txlw1_old;
	}
	public String getTxlw2_old() {
		return txlw2_old;
	}
	public void setTxlw2_old(String txlw2_old) {
		this.txlw2_old = txlw2_old;
	}
	public String getCcc01_old() {
		return ccc01_old;
	}
	public void setCcc01_old(String ccc01_old) {
		this.ccc01_old = ccc01_old;
	}
	public String getCcc02_old() {
		return ccc02_old;
	}
	public void setCcc02_old(String ccc02_old) {
		this.ccc02_old = ccc02_old;
	}
	public String getCcc03_old() {
		return ccc03_old;
	}
	public void setCcc03_old(String ccc03_old) {
		this.ccc03_old = ccc03_old;
	}
	public String getCcc04_old() {
		return ccc04_old;
	}
	public void setCcc04_old(String ccc04_old) {
		this.ccc04_old = ccc04_old;
	}
	public String getCassd_old() {
		return cassd_old;
	}
	public void setCassd_old(String cassd_old) {
		this.cassd_old = cassd_old;
	}
	public String getKnurl_old() {
		return knurl_old;
	}
	public void setKnurl_old(String knurl_old) {
		this.knurl_old = knurl_old;
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
	public String getDear6_old() {
		return dear6_old;
	}
	public void setDear6_old(String dear6_old) {
		this.dear6_old = dear6_old;
	}
	public String getPsofg_old() {
		return psofg_old;
	}
	public void setPsofg_old(String psofg_old) {
		this.psofg_old = psofg_old;
	}
	public String getPsois_old() {
		return psois_old;
	}
	public void setPsois_old(String psois_old) {
		this.psois_old = psois_old;
	}
	public String getPson1_old() {
		return pson1_old;
	}
	public void setPson1_old(String pson1_old) {
		this.pson1_old = pson1_old;
	}
	public String getPson2_old() {
		return pson2_old;
	}
	public void setPson2_old(String pson2_old) {
		this.pson2_old = pson2_old;
	}
	public String getPson3_old() {
		return pson3_old;
	}
	public void setPson3_old(String pson3_old) {
		this.pson3_old = pson3_old;
	}
	public String getPsovn_old() {
		return psovn_old;
	}
	public void setPsovn_old(String psovn_old) {
		this.psovn_old = psovn_old;
	}
	public String getPsotl_old() {
		return psotl_old;
	}
	public void setPsotl_old(String psotl_old) {
		this.psotl_old = psotl_old;
	}
	public String getPsohs_old() {
		return psohs_old;
	}
	public void setPsohs_old(String psohs_old) {
		this.psohs_old = psohs_old;
	}
	public String getPsost_old() {
		return psost_old;
	}
	public void setPsost_old(String psost_old) {
		this.psost_old = psost_old;
	}
	public String getPsoo1_old() {
		return psoo1_old;
	}
	public void setPsoo1_old(String psoo1_old) {
		this.psoo1_old = psoo1_old;
	}
	public String getPsoo2_old() {
		return psoo2_old;
	}
	public void setPsoo2_old(String psoo2_old) {
		this.psoo2_old = psoo2_old;
	}
	public String getPsoo3_old() {
		return psoo3_old;
	}
	public void setPsoo3_old(String psoo3_old) {
		this.psoo3_old = psoo3_old;
	}
	public String getPsoo4_old() {
		return psoo4_old;
	}
	public void setPsoo4_old(String psoo4_old) {
		this.psoo4_old = psoo4_old;
	}
	public String getPsoo5_old() {
		return psoo5_old;
	}
	public void setPsoo5_old(String psoo5_old) {
		this.psoo5_old = psoo5_old;
	}
	public double getVsor_palhgt_old() {
		return vsor_palhgt_old;
	}
	public void setVsor_palhgt_old(double vsor_palhgt_old) {
		this.vsor_palhgt_old = vsor_palhgt_old;
	}
	public String getVsor_pal_ul_old() {
		return vsor_pal_ul_old;
	}
	public void setVsor_pal_ul_old(String vsor_pal_ul_old) {
		this.vsor_pal_ul_old = vsor_pal_ul_old;
	}
	public String getVsor_pk_mat_old() {
		return vsor_pk_mat_old;
	}
	public void setVsor_pk_mat_old(String vsor_pk_mat_old) {
		this.vsor_pk_mat_old = vsor_pk_mat_old;
	}
	public String getVsor_matpal_old() {
		return vsor_matpal_old;
	}
	public void setVsor_matpal_old(String vsor_matpal_old) {
		this.vsor_matpal_old = vsor_matpal_old;
	}
	public String getVsor_i_no_lyr_old() {
		return vsor_i_no_lyr_old;
	}
	public void setVsor_i_no_lyr_old(String vsor_i_no_lyr_old) {
		this.vsor_i_no_lyr_old = vsor_i_no_lyr_old;
	}
	public String getVsor_one_mat_old() {
		return vsor_one_mat_old;
	}
	public void setVsor_one_mat_old(String vsor_one_mat_old) {
		this.vsor_one_mat_old = vsor_one_mat_old;
	}
	public String getVsor_one_sort_old() {
		return vsor_one_sort_old;
	}
	public void setVsor_one_sort_old(String vsor_one_sort_old) {
		this.vsor_one_sort_old = vsor_one_sort_old;
	}
	public String getVsor_uld_side_old() {
		return vsor_uld_side_old;
	}
	public void setVsor_uld_side_old(String vsor_uld_side_old) {
		this.vsor_uld_side_old = vsor_uld_side_old;
	}
	public String getVsor_load_pref_old() {
		return vsor_load_pref_old;
	}
	public void setVsor_load_pref_old(String vsor_load_pref_old) {
		this.vsor_load_pref_old = vsor_load_pref_old;
	}
	public String getVsor_dpoint_old() {
		return vsor_dpoint_old;
	}
	public void setVsor_dpoint_old(String vsor_dpoint_old) {
		this.vsor_dpoint_old = vsor_dpoint_old;
	}
	public String getAlc_old() {
		return alc_old;
	}
	public void setAlc_old(String alc_old) {
		this.alc_old = alc_old;
	}
	public String getPmt_office_old() {
		return pmt_office_old;
	}
	public void setPmt_office_old(String pmt_office_old) {
		this.pmt_office_old = pmt_office_old;
	}
	public String getJ_1kfrepre_old() {
		return j_1kfrepre_old;
	}
	public void setJ_1kfrepre_old(String j_1kfrepre_old) {
		this.j_1kfrepre_old = j_1kfrepre_old;
	}
	public String getJ_1kftbus_old() {
		return j_1kftbus_old;
	}
	public void setJ_1kftbus_old(String j_1kftbus_old) {
		this.j_1kftbus_old = j_1kftbus_old;
	}
	public String getJ_1kftind_old() {
		return j_1kftind_old;
	}
	public void setJ_1kftind_old(String j_1kftind_old) {
		this.j_1kftind_old = j_1kftind_old;
	}
	public String getInspbydebi_old() {
		return inspbydebi_old;
	}
	public void setInspbydebi_old(String inspbydebi_old) {
		this.inspbydebi_old = inspbydebi_old;
	}
	public String getInspatdebi_old() {
		return inspatdebi_old;
	}
	public void setInspatdebi_old(String inspatdebi_old) {
		this.inspatdebi_old = inspatdebi_old;
	}
	public void setOptid(Long optid) {
		this.optid = optid;
	}
	public void setSapclient(Long sapclient) {
		this.sapclient = sapclient;
	}
	
	
	
	
}
