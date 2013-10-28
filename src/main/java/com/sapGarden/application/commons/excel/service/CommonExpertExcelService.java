package com.sapGarden.application.commons.excel.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public interface CommonExpertExcelService {
	public  final int PERSIZE = 50000; 
	public void exportExcel(SapDataCollection sapDataCollection,JSONObject json,HttpServletResponse response)throws IOException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException;
	public void exportLogExcel(SapDataCollection sapDataCollection,JSONObject json,String optflag,String bdate,String edate,HttpServletResponse response)throws IOException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException;
}
