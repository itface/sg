package com.sapGarden.application.fi.company.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.constants.SjlxTypeName;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.excel.service.ExcelService;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;
import com.sapGarden.application.commons.runtime.columninfo.service.Runtime_ColumnInfo_Service;
import com.sapGarden.application.fi.company.model.Company;
import com.sapGarden.application.fi.company.service.CompanyService;
import com.sapGarden.application.fi.company.service.ExportExcelService;
@Service
public class ExportExcelServiceImpl implements ExportExcelService{

	@Autowired
	private CompanyService companyService;
	@Autowired
	private Runtime_ColumnInfo_Service runtime_ColumnInfo_Service;
	@Autowired
	private ExcelService excelService;
	private  final int PERSIZE = 50000; 
	@Override
	public void exportExcel(SapDataCollection sapDataCollection,String companyCode,HttpServletResponse response) throws IOException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException {
		// TODO Auto-generated method stub
		
		List<RuntimeColumnInfo> cols = runtime_ColumnInfo_Service.findAllActiveData(sapDataCollection, SjlxTypeName.TYPE_COMPANY);
		long totalNum = companyService.findTotalNumByPage(sapDataCollection, companyCode);
		if(totalNum>0){
			long pages = totalNum%PERSIZE==0?totalNum/PERSIZE:totalNum/PERSIZE+1;
			String path = "";
			for(int i=1;i<=pages;i++){
				List<Company> datas = companyService.findByPage(sapDataCollection, companyCode, PERSIZE, i);
				String filename = ((i-1)*PERSIZE+1)+"-"+((i-1)*PERSIZE+(PERSIZE>datas.size()?datas.size():PERSIZE));
				path = excelService.generateExcel(cols, datas, sapDataCollection, SjlxTypeName.TYPE_COMPANY, filename);
			}
			excelService.downloadZip(path, SjlxTypeName.TYPE_COMPANY, response);
			excelService.deleteByFilePath(path);
		}
	}

}
