package com.sapGarden.application.jco.commons.service;

import java.util.List;

import com.sapGarden.application.jco.commons.model.CommonCompareDataModel;
import com.sapGarden.global.exception.JpaException;
/**
 * 对同步的数据保存到本地数据库的操作
 * @author Administrator
 *
 */
public interface CommonDaoService {

	public void addOne(boolean logException,Object obj,String type,String optType,String user)throws JpaException;
	public void addList(boolean logException,List objectList,String type,String optType,String user)throws JpaException;
	public void updateOne(boolean logException,Object obj,CommonCompareDataModel compareDataModel,String type,String optType,String user)throws JpaException;
	public void updateList(boolean logException,List objectList,List<CommonCompareDataModel> compareDataModelList,String type,String optType,String user)throws JpaException;
	public void removeOneById(boolean logException,long id,String type,String optType,String user)throws JpaException;
	public void removeAll(boolean logException,String type,String optType,String user)throws JpaException;
}
