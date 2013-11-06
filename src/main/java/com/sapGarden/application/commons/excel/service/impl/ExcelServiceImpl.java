package com.sapGarden.application.commons.excel.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.excel.service.ExcelService;
import com.sapGarden.application.commons.excel.util.ExcelWriter;
@Service
public class ExcelServiceImpl implements ExcelService{


	private String getBasePath() throws URISyntaxException{
		String classPath = ExcelServiceImpl.class.getClassLoader().getResource("").toURI().getPath();
		File classDic = new File(classPath);
		String path = classDic.getParent()+"\\tempExcel";
		File pathDic = new File(path);
		if(!pathDic.exists()){
			pathDic.mkdir();
		}
		return path;
	}
	@Override
	public String generateExcel(String[] cols,String[] fields,List list,Class dataClass,SapDataCollection sapDataCollection,String type,String excelName) throws IOException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException {
		// TODO Auto-generated method stub
		String basePath = getBasePath();
		String filepath = basePath+File.separator+type+sapDataCollection.getId();
		File pathDic = new File(filepath);
		if(!pathDic.exists()){
			pathDic.mkdir();
		}
		File file = new File(filepath+File.separator+excelName+".xls");
		if(file.exists()){
			file.delete();
		}else{
			file.createNewFile();
		}
		OutputStream os = new FileOutputStream(file); 
		ExcelWriter excelWriter = new ExcelWriter(os);
		int rowNum = 0;
		excelWriter.createRow(rowNum++);
		for(int j=0;j<cols.length;j++){
			excelWriter.setCell(j, cols[j]);
		}
		if(list!=null&&list.size()>0){
			for(Object obj : list){
				excelWriter.createRow(rowNum++);
				for(int j=0;j<fields.length;j++){
					String field = fields[j];
					String setFieldMethod = "get"+field.substring(0, 1).toUpperCase()+field.substring(1).toLowerCase();
					Method method = dataClass.getMethod(setFieldMethod, new Class[]{});
					Object value = method.invoke(obj, new Object[]{});
					if(value instanceof Date){
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						excelWriter.setCell(j, sf.format(value));
					}else if("optflag".equals(field)){
						if("E".equals(value)){
							excelWriter.setCell(j, "操作失败");
						}else if("S".equals(value)){
							excelWriter.setCell(j, "操作成功");
						}
					}else{
						excelWriter.setCell(j, value);
					}
				}
			}
		}
		excelWriter.export();
		return filepath;
	}
	@Override
	public void downloadExcel(HttpServletResponse response,
			String[] cols,String[] fields, List list,Class dataClass,
			SapDataCollection sapDataCollection, String type, String excelName)
			throws IOException, SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, URISyntaxException {
		// TODO Auto-generated method stub
		response.setHeader("Content-Disposition", "attachment; filename=\"" + excelName  + ".xls");  
	    response.setContentType("application/octet-stream; charset=UTF-8");
		ExcelWriter excelWriter = new ExcelWriter(response.getOutputStream());
		int rowNum = 0;
		excelWriter.createRow(rowNum++);
		for(int j=0;j<cols.length;j++){
			excelWriter.setCell(j,cols[j]);
			//RuntimeColumnInfo col = cols.get(j);
			//excelWriter.setCell(j, col.getTargetColumnName()+"("+col.getTargetColumn()+")");
		}
		if(list!=null&&list.size()>0){
			for(Object obj : list){
				excelWriter.createRow(rowNum++);
				for(int j=0;j<fields.length;j++){
					String field = fields[j];
					String setFieldMethod = "get"+field.substring(0, 1).toUpperCase()+field.substring(1).toLowerCase();
					Method method = dataClass.getMethod(setFieldMethod, new Class[]{});
					Object value = method.invoke(obj, new Object[]{});
					if(value instanceof Date){
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						excelWriter.setCell(j, sf.format(value));
					}else if("optflag".equals(field)){
						if("E".equals(value)){
							excelWriter.setCell(j, "操作失败");
						}else if("S".equals(value)){
							excelWriter.setCell(j, "操作成功");
						}
					}else{
						excelWriter.setCell(j, value);
					}
				}
			}
		}
		excelWriter.export();
	}
	@Override
	public void downloadZip(String filepath,String zipname,HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.setHeader("Content-Disposition", "attachment; filename=\"" + zipname  + ".zip");  
	    response.setContentType("application/octet-stream; charset=UTF-8");
		ZipOutputStream zipout = new ZipOutputStream(response.getOutputStream());
		File dir = new File(filepath);
		File[] fs = dir.listFiles();
		byte[] buf = null;
		if(fs!=null){
			for (File f : fs) {
				if(!f.isDirectory()){
					zipout.putNextEntry(new ZipEntry(f.getName()));
					FileInputStream fileInputStream = new FileInputStream(f);
					buf = new byte[2048];
					BufferedInputStream origin = new BufferedInputStream(fileInputStream,2048);
					int len;
					while ((len = origin.read(buf,0,2048))!=-1) {
						zipout.write(buf,0,len);
					}
				    zipout.flush();
				    origin.close();
				    fileInputStream.close(); 
				}
			}
		}
		zipout.flush();
		zipout.close();
	}
	@Override
	public void deleteByFilePath(String path) {
		// TODO Auto-generated method stub
		File file = new File(path);
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				deleteByFilePath(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				temp.delete();// 再删除空文件夹
			}
		}
		file.delete();
	}
	

}
