package com.sapGarden.global.utils.commonUtils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	/**
	 * 根据时间字符串，转换成日期对象
	 * @param s
	 * @return
	 */
	public Date stringToDate(String s){
		if(s!=null&&s.trim().length()>=10){
			int year = Integer.parseInt(s.substring(0, 4));
			int month = Integer.parseInt(s.substring(5, 7))-1;
			int day = Integer.parseInt(s.substring(8, 10));
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, month);
			c.set(Calendar.DAY_OF_MONTH, day);
			return c.getTime();
		}else{
			return null;
		}
	}
	/**
	 * 把日期字符串转换成数据库类型的date
	 * @param s
	 * @return
	 */
	public java.sql.Date stringToSqlDate(String s){
		Date date = this.stringToDate(s);
		if(date!=null){
			return new java.sql.Date(date.getTime());
		}else{
			return null;
		}
	}
}
