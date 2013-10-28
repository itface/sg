package com.sapGarden.application.fi.customer.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.constants.SjlxTypeName;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.excel.service.CommonExpertExcelService;
import com.sapGarden.application.commons.excel.service.ExcelService;
import com.sapGarden.application.commons.log.service.CommonLogService;
import com.sapGarden.application.commons.log.service.CommonService;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.service.Runtime_ColumnInfo_Service;
import com.sapGarden.application.fi.company.model.CompanyLog;
import com.sapGarden.application.fi.customer.model.Kna1;
import com.sapGarden.application.fi.customer.model.Kna1Log;
import com.sapGarden.application.fi.customer.model.Knb1;
import com.sapGarden.application.fi.customer.model.Knb1Log;
import com.sapGarden.application.fi.customer.model.Knvv;
import com.sapGarden.application.fi.customer.model.KnvvLog;
@Service("exportCustomerExcel")
public class ExportCustomerExcelServiceImpl implements CommonExpertExcelService{

	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;
	@Autowired
	@Qualifier("commonLogService")
	private CommonLogService commonLogService;
	@Autowired
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	@Autowired
	private ExcelService excelService;
	@Override
	public void exportExcel(SapDataCollection sapDataCollection,
			JSONObject json, HttpServletResponse response) throws IOException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			URISyntaxException {
		// TODO Auto-generated method stub
		String path = "";
		List<RuntimeColumnInfo> kna1cols = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection, SjlxTypeName.TYPE_CUSTOMER_KNA1);
		List<RuntimeColumnInfo> knb1cols = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection, SjlxTypeName.TYPE_CUSTOMER_KNB1);
		List<RuntimeColumnInfo> knvvcols = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection, SjlxTypeName.TYPE_CUSTOMER_KNVV);
		long kna1totalNum = commonService.findTotalNum(sapDataCollection,"Kna1", json);
		long knb1totalNum = commonService.findTotalNum(sapDataCollection,"Knb1", json);
		long knvvtotalNum = commonService.findTotalNum(sapDataCollection,"Knvv", json);
		String[] kna1titles = new String[kna1cols.size()],kna1fields=new String[kna1cols.size()];
		String[] knb1titles = new String[kna1cols.size()],knb1fields=new String[knb1cols.size()];
		String[] knvvtitles = new String[kna1cols.size()],knvvfields=new String[knvvcols.size()];
		int k=0;
		for(RuntimeColumnInfo col : kna1cols){
			kna1titles[k]=col.getTargetColumnName()+"("+col.getTargetColumn()+")";
			kna1fields[k]=col.getTargetColumn();
			k++;
		}
		k=0;
		for(RuntimeColumnInfo col : knb1cols){
			knb1titles[k]=col.getTargetColumnName()+"("+col.getTargetColumn()+")";
			knb1fields[k]=col.getTargetColumn();
			k++;
		}
		k=0;
		for(RuntimeColumnInfo col : knvvcols){
			knvvtitles[k]=col.getTargetColumnName()+"("+col.getTargetColumn()+")";
			knvvfields[k]=col.getTargetColumn();
			k++;
		}
		Calendar c = Calendar.getInstance();
		if(kna1totalNum>0){
			long pages = kna1totalNum%PERSIZE==0?kna1totalNum/PERSIZE:kna1totalNum/PERSIZE+1;
			if(pages>1){
				for(int i=1;i<=pages;i++){
					List<Kna1> datas = commonService.findByPage(sapDataCollection,"Kna1", json, PERSIZE, i,null,null);
					String filename = "customer_"+sapDataCollection.getSapserverclient()+"_"+c.get(Calendar.YEAR)+(c.get(Calendar.MONTH)+1)+c.get(Calendar.DATE)+"_kna1_"+i;
					path = excelService.generateExcel(kna1titles,kna1fields, datas,Kna1.class, sapDataCollection, SjlxTypeName.TYPE_CUSTOMER, filename);
				}
			}else{
				String filename = "customer_"+sapDataCollection.getSapserverclient()+"_"+c.get(Calendar.YEAR)+(c.get(Calendar.MONTH)+1)+c.get(Calendar.DATE)+"_kna1";
				List<Kna1> datas = commonService.findByPage(sapDataCollection,"Kna1", json, PERSIZE, 1,null,null);
				path = excelService.generateExcel(kna1titles,kna1fields, datas,Kna1.class, sapDataCollection, SjlxTypeName.TYPE_CUSTOMER, filename);
			}
		}
		if(knb1totalNum>0){
			long pages = knb1totalNum%PERSIZE==0?knb1totalNum/PERSIZE:knb1totalNum/PERSIZE+1;
			if(pages>1){
				for(int i=1;i<=pages;i++){
					List<Knb1> datas = commonService.findByPage(sapDataCollection,"Knb1", json, PERSIZE, i,null,null);
					String filename = "customer_"+sapDataCollection.getSapserverclient()+"_"+c.get(Calendar.YEAR)+(c.get(Calendar.MONTH)+1)+c.get(Calendar.DATE)+"_knb1_"+i;
					path = excelService.generateExcel(knb1titles,knb1fields, datas,Knb1.class, sapDataCollection, SjlxTypeName.TYPE_CUSTOMER, filename);
				}
			}else{
				String filename = "customer_"+sapDataCollection.getSapserverclient()+"_"+c.get(Calendar.YEAR)+(c.get(Calendar.MONTH)+1)+c.get(Calendar.DATE)+"_knb1";
				List<Knb1> datas = commonService.findByPage(sapDataCollection,"Knb1", json, PERSIZE, 1,null,null);
				path = excelService.generateExcel(knb1titles,knb1fields, datas,Knb1.class, sapDataCollection, SjlxTypeName.TYPE_CUSTOMER, filename);
			}
		}
		if(knvvtotalNum>0){
			long pages = knvvtotalNum%PERSIZE==0?knvvtotalNum/PERSIZE:knvvtotalNum/PERSIZE+1;
			if(pages>1){
				for(int i=1;i<=pages;i++){
					List<Knvv> datas = commonService.findByPage(sapDataCollection,"Knvv", json, PERSIZE, i,null,null);
					String filename = "customer_"+sapDataCollection.getSapserverclient()+"_"+c.get(Calendar.YEAR)+(c.get(Calendar.MONTH)+1)+c.get(Calendar.DATE)+"_knvv_"+i;
					path = excelService.generateExcel(knvvtitles,knvvfields, datas,Knvv.class, sapDataCollection, SjlxTypeName.TYPE_CUSTOMER, filename);
				}
			}else{
				String filename = "customer_"+sapDataCollection.getSapserverclient()+"_"+c.get(Calendar.YEAR)+(c.get(Calendar.MONTH)+1)+c.get(Calendar.DATE)+"_knvv";
				List<Knvv> datas = commonService.findByPage(sapDataCollection,"Knvv", json, PERSIZE, 1,null,null);
				path = excelService.generateExcel(knvvtitles,knvvfields, datas,Knvv.class, sapDataCollection, SjlxTypeName.TYPE_CUSTOMER, filename);
			}
		}
		String zipname = "customer_"+sapDataCollection.getSapserverclient()+"_"+c.get(Calendar.YEAR)+(c.get(Calendar.MONTH)+1)+c.get(Calendar.DATE);
		excelService.downloadZip(path, zipname, response);
		excelService.deleteByFilePath(path);
	}

	@Override
	public void exportLogExcel(SapDataCollection sapDataCollection,
			JSONObject json, String optflag, String bdate, String edate,
			HttpServletResponse response) throws IOException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			URISyntaxException {
		// TODO Auto-generated method stub
		List<RuntimeColumnInfo> kna1list = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection, SjlxTypeName.TYPE_CUSTOMER_KNA1);
		List<RuntimeColumnInfo> knb1list = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection, SjlxTypeName.TYPE_CUSTOMER_KNB1);
		List<RuntimeColumnInfo> knvvlist = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection, SjlxTypeName.TYPE_CUSTOMER_KNVV);
		String[] kna1titles = new String[kna1list.size()*2+6],kna1fields=new String[kna1list.size()*2+6];
		String[] knb1titles = new String[knb1list.size()*2+6],knb1fields=new String[knb1list.size()*2+6];
		String[] knvvtitles = new String[knvvlist.size()*2+6],knvvfields=new String[knvvlist.size()*2+6];
		if(kna1list!=null&&kna1list.size()>0){
			kna1titles[0]="操作结果";
			kna1fields[0]="optflag";
			kna1titles[1]="操作时间";
			kna1fields[1]="opttime";
			kna1titles[2]="操作人";
			kna1fields[2]="optuser";
			kna1titles[3]="操作";
			kna1fields[3]="opt";
			kna1titles[4]="操作类型";
			kna1fields[4]="opttype";
			kna1titles[5]="异常信息";
			kna1fields[5]="optmsg";
			int count=6;
			for(RuntimeColumnInfo runtimeColumnInfo : kna1list){
				kna1titles[count]=runtimeColumnInfo.getTargetColumnName();
				kna1fields[count]=runtimeColumnInfo.getTargetColumn();
				count++;
				kna1titles[count]=runtimeColumnInfo.getTargetColumnName()+"_原值";
				kna1fields[count]=runtimeColumnInfo.getTargetColumn()+"_old";
				count++;
			}
		}
		if(knb1list!=null&&knb1list.size()>0){
			knb1titles[0]="操作结果";
			knb1fields[0]="optflag";
			knb1titles[1]="操作时间";
			knb1fields[1]="opttime";
			knb1titles[2]="操作人";
			knb1fields[2]="optuser";
			knb1titles[3]="操作";
			knb1fields[3]="opt";
			knb1titles[4]="操作类型";
			knb1fields[4]="opttype";
			knb1titles[5]="异常信息";
			knb1fields[5]="optmsg";
			int count=6;
			for(RuntimeColumnInfo runtimeColumnInfo : knb1list){
				knb1titles[count]=runtimeColumnInfo.getTargetColumnName();
				knb1fields[count]=runtimeColumnInfo.getTargetColumn();
				count++;
				knb1titles[count]=runtimeColumnInfo.getTargetColumnName()+"_原值";
				knb1fields[count]=runtimeColumnInfo.getTargetColumn()+"_old";
				count++;
			}
		}
		if(knvvlist!=null&&knvvlist.size()>0){
			knvvtitles[0]="操作结果";
			knvvfields[0]="optflag";
			knvvtitles[1]="操作时间";
			knvvfields[1]="opttime";
			knvvtitles[2]="操作人";
			knvvfields[2]="optuser";
			knvvtitles[3]="操作";
			knvvfields[3]="opt";
			knvvtitles[4]="操作类型";
			knvvfields[4]="opttype";
			knvvtitles[5]="异常信息";
			knvvfields[5]="optmsg";
			int count=6;
			for(RuntimeColumnInfo runtimeColumnInfo : knvvlist){
				knvvtitles[count]=runtimeColumnInfo.getTargetColumnName();
				knvvfields[count]=runtimeColumnInfo.getTargetColumn();
				count++;
				knvvtitles[count]=runtimeColumnInfo.getTargetColumnName()+"_原值";
				knvvfields[count]=runtimeColumnInfo.getTargetColumn()+"_old";
				count++;
			}
		}
		long kna1totalNum = commonLogService.findTotalNum(sapDataCollection,"Kna1Log", json, optflag, bdate, edate);
		long knb1totalNum = commonLogService.findTotalNum(sapDataCollection,"Knb1Log", json, optflag, bdate, edate);
		long knvvtotalNum = commonLogService.findTotalNum(sapDataCollection,"KnvvLog", json, optflag, bdate, edate);
		Calendar c = Calendar.getInstance();
		long kna1pages = kna1totalNum%PERSIZE==0?kna1totalNum/PERSIZE:kna1totalNum/PERSIZE+1;
		long knb1pages = knb1totalNum%PERSIZE==0?knb1totalNum/PERSIZE:knb1totalNum/PERSIZE+1;
		long knvvpages = knvvtotalNum%PERSIZE==0?knvvtotalNum/PERSIZE:knvvtotalNum/PERSIZE+1;
		String path = "";
		if(kna1pages>1){
			for(int i=1;i<=kna1pages;i++){
				List<CompanyLog> datas = commonLogService.findByPage(sapDataCollection,"Kna1Log", json, optflag, bdate, edate, PERSIZE, i, null, null);
				String filename = "CustomerLog_"+sapDataCollection.getSapserverclient()+"_"+c.get(Calendar.YEAR)+(c.get(Calendar.MONTH)+1)+c.get(Calendar.DATE)+"_kna1_"+i;
				path = excelService.generateExcel(kna1titles,kna1fields, datas,Kna1Log.class, sapDataCollection, SjlxTypeName.TYPE_CUSTOMER, filename);
			}
		}else{
			String filename = "CustomerLog_"+sapDataCollection.getSapserverclient()+"_"+c.get(Calendar.YEAR)+(c.get(Calendar.MONTH)+1)+c.get(Calendar.DATE)+"_kna1";
			List<CompanyLog> datas = commonLogService.find(sapDataCollection,"Kna1Log", json, optflag, bdate, edate);
			path = excelService.generateExcel(kna1titles,kna1fields, datas,Kna1Log.class, sapDataCollection, SjlxTypeName.TYPE_CUSTOMER, filename);
		}
		if(knb1pages>1){
			for(int i=1;i<=knb1pages;i++){
				List<CompanyLog> datas = commonLogService.findByPage(sapDataCollection,"Knb1Log", json, optflag, bdate, edate, PERSIZE, i, null, null);
				String filename = "CustomerLog_"+sapDataCollection.getSapserverclient()+"_"+c.get(Calendar.YEAR)+(c.get(Calendar.MONTH)+1)+c.get(Calendar.DATE)+"_knb1_"+i;
				path = excelService.generateExcel(knb1titles,knb1fields, datas,Knb1Log.class, sapDataCollection, SjlxTypeName.TYPE_CUSTOMER, filename);
			}
		}else{
			String filename = "CustomerLog_"+sapDataCollection.getSapserverclient()+"_"+c.get(Calendar.YEAR)+(c.get(Calendar.MONTH)+1)+c.get(Calendar.DATE)+"_knb1";
			List<CompanyLog> datas = commonLogService.find(sapDataCollection,"Knb1Log", json, optflag, bdate, edate);
			path = excelService.generateExcel(knb1titles,knb1fields, datas,Knb1Log.class, sapDataCollection, SjlxTypeName.TYPE_CUSTOMER, filename);
		}
		if(knvvpages>1){
			for(int i=1;i<=knvvpages;i++){
				List<CompanyLog> datas = commonLogService.findByPage(sapDataCollection,"KnvvLog", json, optflag, bdate, edate, PERSIZE, i, null, null);
				String filename = "CustomerLog_"+sapDataCollection.getSapserverclient()+"_"+c.get(Calendar.YEAR)+(c.get(Calendar.MONTH)+1)+c.get(Calendar.DATE)+"_knvv_"+i;
				path = excelService.generateExcel(knvvtitles,knvvfields, datas,KnvvLog.class, sapDataCollection, SjlxTypeName.TYPE_CUSTOMER, filename);
			}
		}else{
			String filename = "CustomerLog_"+sapDataCollection.getSapserverclient()+"_"+c.get(Calendar.YEAR)+(c.get(Calendar.MONTH)+1)+c.get(Calendar.DATE)+"_knvv";
			List<CompanyLog> datas = commonLogService.find(sapDataCollection,"KnvvLog", json, optflag, bdate, edate);
			path = excelService.generateExcel(knvvtitles,knvvfields, datas,KnvvLog.class, sapDataCollection, SjlxTypeName.TYPE_CUSTOMER, filename);
		}
		String zipname = "customerLog_"+sapDataCollection.getSapserverclient()+"_"+c.get(Calendar.YEAR)+(c.get(Calendar.MONTH)+1)+c.get(Calendar.DATE);
		excelService.downloadZip(path, zipname, response);
		excelService.deleteByFilePath(path);
	}

}
