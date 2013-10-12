package com.sapGarden.application.commons.excel.service;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.runtime.columninfo.model.RuntimeColumnInfo;

public interface ExcelService {

	public String generateExcel(List<RuntimeColumnInfo> cols,List list,SapDataCollection sapDataCollection,String type,String excelName)throws IOException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException;
	public void downloadZip(String filepath,String zipname,HttpServletResponse response)throws IOException;
	public void downloadExcel(HttpServletResponse response,List<RuntimeColumnInfo> cols,List list,SapDataCollection sapDataCollection,String type,String excelName)throws IOException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException;
	public void deleteByFilePath(String filepath);
}
