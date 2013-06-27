package com.sapGarden.system.db;

public class DbContextHolder {

	//线程内共享变量,ThreadLocal对象作为key，把对象保存在当前线程中map中
	private static final ThreadLocal<Object> contextHolder = new ThreadLocal<Object>();  
    
    public static void setDbType(Object dbType) {  
        contextHolder.set(dbType);  
    }  
      
    public static String getDbType() {  
        return (String) contextHolder.get();  
    }  
      
    public static void clearDbType() {  
        contextHolder.remove();  
    }   
}
