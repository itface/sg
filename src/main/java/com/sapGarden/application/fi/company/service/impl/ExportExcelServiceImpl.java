package com.sapGarden.application.fi.company.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
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
import com.sapGarden.application.fi.company.model.Company;
import com.sapGarden.application.fi.company.model.CompanyLog;
@Service("exportCompanyExcel")
public class ExportExcelServiceImpl implements CommonExpertExcelService{

	@Autowired
	@Qualifier("commonService")
	private CommonService<Company> companyService;
	@Autowired
	@Qualifier("commonLogService")
	private CommonLogService<CompanyLog> companyLogService;
	@Autowired
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	@Autowired
	private ExcelService excelService;
	@Override
	public void exportExcel(SapDataCollection sapDataCollection,JSONObject json,HttpServletResponse response) throws IOException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException {
		// TODO Auto-generated method stub	
		List<RuntimeColumnInfo> cols = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection, SjlxTypeName.TYPE_COMPANY);
		long totalNum = companyService.findTotalNum(sapDataCollection,"Company", json);
		String[] titles = new String[cols.size()],fields=new String[cols.size()];
		int k=0;
		for(RuntimeColumnInfo col : cols){
			titles[k]=col.getTargetColumnName()+"("+col.getTargetColumn()+")";
			fields[k]=col.getTargetColumn();
			k++;
		}
		if(totalNum>0){
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			String today = sf.format(c.getTime());
			String frefixOfFileName = "companycode_"+today;
			long pages = totalNum%PERSIZE==0?totalNum/PERSIZE:totalNum/PERSIZE+1;
			if(pages>1){
				String path = "";
				for(int i=1;i<=pages;i++){
					List<Company> datas = companyService.findByPage(sapDataCollection,"Company", json, PERSIZE, i,null,null);
					String filename = frefixOfFileName+"_"+i;
					path = excelService.generateExcel(titles,fields, datas,Company.class, sapDataCollection, SjlxTypeName.TYPE_COMPANY, filename);
				}
				String zipname = frefixOfFileName;
				excelService.downloadZip(path, zipname, response);
				excelService.deleteByFilePath(path);
			}else{
				String filename = frefixOfFileName;
				List<Company> datas = companyService.findByPage(sapDataCollection,"Company", json, PERSIZE, 1,null,null);
				excelService.downloadExcel(response, titles,fields, datas,Company.class, sapDataCollection, SjlxTypeName.TYPE_COMPANY, filename);
			}
		}
	}
	@Override
	public void exportLogExcel(SapDataCollection sapDataCollection,
			JSONObject json,String optflag,String bdate,String edate, HttpServletResponse response)
			throws IOException, SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, URISyntaxException {
		// TODO Auto-generated method stub
		List<RuntimeColumnInfo> list = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection, SjlxTypeName.TYPE_COMPANY);
		String[] titles = new String[list.size()*2+6],fields=new String[list.size()*2+6];
		if(list!=null&&list.size()>0){
			titles[0]="操作结果";
			fields[0]="optflag";
			titles[1]="操作时间";
			fields[1]="opttime";
			titles[2]="操作人";
			fields[2]="optuser";
			titles[3]="操作";
			fields[3]="opt";
			titles[4]="操作类型";
			fields[4]="opttype";
			titles[5]="异常信息";
			fields[5]="optmsg";
			int count=6;
			for(RuntimeColumnInfo runtimeColumnInfo : list){
				titles[count]=runtimeColumnInfo.getTargetColumnName();
				fields[count]=runtimeColumnInfo.getTargetColumn();
				count++;
				titles[count]=runtimeColumnInfo.getTargetColumnName()+"_原值";
				fields[count]=runtimeColumnInfo.getTargetColumn()+"_old";
				count++;
			}
		}
		long totalNum = companyLogService.findTotalNum(sapDataCollection,"CompanyLog", json, optflag, bdate, edate);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String today = sf.format(c.getTime());
		String frefixOfFileName = "companyLog_"+today;
		long pages = totalNum%PERSIZE==0?totalNum/PERSIZE:totalNum/PERSIZE+1;
		if(pages>1){
			String path = "";
			for(int i=1;i<=pages;i++){
				List<CompanyLog> datas = companyLogService.findByPage(sapDataCollection,"CompanyLog", json, optflag, bdate, edate, PERSIZE, i, null, null);
				String filename = frefixOfFileName+"_"+i;
				path = excelService.generateExcel(titles,fields, datas,CompanyLog.class, sapDataCollection, SjlxTypeName.TYPE_COMPANYLOG, filename);
			}
			String zipname = frefixOfFileName;
			excelService.downloadZip(path, zipname, response);
			excelService.deleteByFilePath(path);
		}else{
			String filename = frefixOfFileName;
			List<CompanyLog> datas = companyLogService.find(sapDataCollection,"CompanyLog", json, optflag, bdate, edate);
			excelService.downloadExcel(response,titles,fields, datas,CompanyLog.class, sapDataCollection, SjlxTypeName.TYPE_COMPANYLOG, filename);
		}
	}

}
