package com.sapGarden.system.startUp.service.impl;

import java.util.List;
import java.util.Vector;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
/**
 * 此类用于平台启动时初始化定时任务，当初始化数据库连接时，把所有数据库连接放到该类管理。
 * 当初始化定时任务时，取出每个数据库连接下的定时任务，每次取完都会删除该SapDataCollection，
 * 初始化结束时，该List为空.
 * @author Administrator
 *
 */
public class SapclientCollections {

	private static final List<SapDataCollection> list = new Vector<SapDataCollection>();
	
	public synchronized static int length(){
		return list.size();
	}
	public synchronized static void add(List<SapDataCollection> l){
		if(l!=null){
			list.addAll(l);
		}
	}
	public synchronized static SapDataCollection getNext(){
		if(list!=null&&list.size()>0){
			SapDataCollection sapclient = list.get(0);
			list.remove(0);
			return sapclient;
		}
		return null;
	}
}
