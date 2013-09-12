package com.sapGarden.application.commons.excel.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

public class ExcelWriter {
	// 定制浮点数格式
	private static String NUMBER_FORMAT = "#,##0.00";
	// 定制日期格式
	private static String DATE_FORMAT = "m/d/yy"; // "m/d/yy h:mm"
	private OutputStream out = null;
	private Workbook workbook = null;
	private Sheet sheet = null;
	private Row row = null;
	public ExcelWriter() {
	}
	public ExcelWriter(OutputStream out) {
		this.out = out;
		this.workbook = new SXSSFWorkbook(128);//POI3.8最新的API，解决问题的关键。keep 100 rows in memory, exceeding rows will be flushed to disk 
		this.sheet = workbook.createSheet();
	}
	/**
	 * 导出Excel文件
	 * @throws IOException
	 */
	public void export() throws FileNotFoundException, IOException {
		try {
			workbook.write(out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			throw new IOException(" 生成导出Excel文件出错! ", e);
		} catch (IOException e) {
			throw new IOException(" 写入Excel文件出错! ", e);
		}
	}

	/**
	 * 增加一行
	 * @param index 行号
	 */
	public void createRow(int index) {
		this.row = this.sheet.createRow(index);
	}

	/**
	 * 获取单元格的值
	 * @param index 列号
	 */
	public String getCell(int index){
		Cell cell = this.row.getCell((short) index);
		String strExcelCell = "";
		if (cell != null) { // add this condition
			// judge
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_FORMULA:
				strExcelCell = "FORMULA ";
				break;
			case Cell.CELL_TYPE_NUMERIC: {
				strExcelCell = String.valueOf(cell.getNumericCellValue());
			}
				break;
			case Cell.CELL_TYPE_STRING:
				strExcelCell = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				strExcelCell = "";
				break;
			default:
				strExcelCell = "";
				break;
			}
		}
		return strExcelCell;
	}
	public void setCell(int index, Object value) {
		if(value!=null){
			if(value instanceof Integer){
				this.setCell(index, ((Integer)value).intValue());
			}else if(value instanceof Double){
				this.setCell(index, ((Double)value).doubleValue());
			}else if(value instanceof String){
				this.setCell(index, (String)value);
			}else if(value instanceof Calendar){
				this.setCell(index, (Calendar)value);
			}
		}else{
			this.setCell(index, "");
		}
	}
	/**
	 * 设置单元格
	 * @param index 列号
	 * @param value 单元格填充值
	 */
	public void setCell(int index, int value) {
		Cell cell = this.row.createCell((short) index);
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
	}

	/**
	 * 设置单元格
	 * @param index 列号
	 * @param value 单元格填充值
	 */
	public void setCell(int index, double value) {
		Cell cell = this.row.createCell((short) index);
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
		CellStyle cellStyle = workbook.createCellStyle(); // 建立新的cell样式
		DataFormat format = workbook.createDataFormat();
		cellStyle.setDataFormat(format.getFormat(NUMBER_FORMAT));//设置cell样式为定制的浮点数格式
		cell.setCellStyle(cellStyle); // 设置该cell浮点数的显示格式
	}

	/**
	 * 设置单元格
	 * @param index 列号
	 * @param value 单元格填充值
	 */
	public void setCell(int index, String value) {
		Cell cell = this.row.createCell((short) index);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue(value);
	}

	/**
	 * 设置单元格
	 * @param index 列号
	 * @param value 单元格填充值
	 */
	public void setCell(int index,Calendar value) {
		Cell cell = this.row.createCell((short) index);
		cell.setCellValue(value.getTime());
		CellStyle cellStyle = workbook.createCellStyle(); // 建立新的cell样式
		DataFormat format = workbook.createDataFormat();
		cellStyle.setDataFormat(format.getFormat(DATE_FORMAT)); // 设置cell样式为定制的日期格式
		cell.setCellStyle(cellStyle); // 设置该cell日期的显示格式
	}
	 /**
		 * 将指定文件夹打包成zip
		 * @param folder
		 * @throws IOException 
		 */
	public  void zipFile(String folder) throws IOException {
		File zipFile = new File(folder + ".zip");
		if (zipFile.exists()) {
			zipFile.delete();
		}
		ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(zipFile));
		File dir = new File(folder);
		File[] fs = dir.listFiles();
		byte[] buf = null;
		if(fs!=null){
			for (File f : fs) {
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
		zipout.flush();
		zipout.close();
	}
	public static void main(String[] args) {
//		System.out.println(" 开始导出Excel文件 ");
//		try {
//			zipFile("d:\\testPoi");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		File f = new File("d:\\testPoi\\qt2.xls");
//		ExcelWriter e = new ExcelWriter();
//
//		try {
//			e = new ExcelWriter(new FileOutputStream(f));
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}
//		e.createRow(0);
//		e.setCell(0, "试题编码 ");
//		e.setCell(1, "题型");
//		e.setCell(2, "分值");
//		e.setCell(3, "难度");
//		e.setCell(4, "级别");
//		e.setCell(5, "知识点");
//		e.createRow(1);
//		e.setCell(0, "t1");
//		e.setCell(1, 1);
//		e.setCell(2, 3.0);
//		e.setCell(3, 1);
//		e.setCell(4, "重要");
//		e.setCell(5, "专业");
//		try {
//			e.export();
//			System.out.println(" 导出Excel文件[成功] ");
//		} catch (IOException ex) {
//			System.out.println(" 导出Excel文件[失败] ");
//			ex.printStackTrace();
//		}
	}
}
