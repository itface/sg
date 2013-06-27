package com.sapGarden.application.commons.jco;

import java.util.Properties;

import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Client;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public class JcoConnectionPool {

	/**
	 * 连接池名称
	 */
	private static final String POOL_NAME_PREFIX = "SAPGARDEN_POOL";
	/**
	 * 连接池最大连接数
	 */
	private static final int POOL_MAX_CONNECTION = 25;

	
	/**
	 * 获取jco连接
	 * @return
	 */
	public static synchronized String getPoolName(SapDataCollection sapDataCollection){
		//String classpath="D:\\Program Files\\tomcat\\apache-tomcat-6.0.33\\webapps\\sg\\WEB-INF\\classes\\jco.properties";
		/*
		try {
			InputStream   is   =  (new JcoConnectionPool()).getClass().getClassLoader().getResourceAsStream("jco.properties");
			InputStream in = new BufferedInputStream(is); 
			Properties jcoProperties = new Properties();
			jcoProperties.load(in);
			return JCO.createClient(jcoProperties);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		*/
		if(sapDataCollection!=null){
			String poolName = POOL_NAME_PREFIX+sapDataCollection.getAlias()+sapDataCollection.getSapserverusername()+sapDataCollection.getSapserverpassword();
			JCO.Pool pool = JCO.getClientPoolManager().getPool(poolName);
			if(pool==null){
				Properties jcoProperties = new Properties();
				jcoProperties.put("jco.client.ashost",sapDataCollection.getSapserverip());          
				jcoProperties.put("jco.client.client",sapDataCollection.getSapserverclient());           
				jcoProperties.put("jco.client.passwd",sapDataCollection.getSapserverpassword());           
				jcoProperties.put("jco.client.sysnr",sapDataCollection.getSapserverinstance());           
				jcoProperties.put("jco.client.user",sapDataCollection.getSapserverusername());
				//jcoProperties.put("jco.client.language","");
				JCO.addClientPool(poolName,POOL_MAX_CONNECTION,jcoProperties);
			}
			return  poolName;
		}
		return null;
	}
	/**
	 * 释放jco连接
	 * @param client
	 */
	public static void closeConnection(Client client){
		JCO.releaseClient(client);
	}
	public static synchronized void removeConnectionPool(String key){
		JCO.Pool pool = JCO.getClientPoolManager().getPool(key);
		if(pool!=null){
			JCO.removeClientPool(key);
		}
	}
}
