package com.sapGarden.application.fi.company.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public interface ExportExcelService {

	public void exportExcel(SapDataCollection sapDataCollection,String companyCode,HttpServletResponse response)throws IOException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException;
	public void exportLogExcel(SapDataCollection sapDataCollection,String companyCode,String optflag,String bdate,String edate,HttpServletResponse response)throws IOException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException;
}
