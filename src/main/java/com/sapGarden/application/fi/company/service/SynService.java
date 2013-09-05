package com.sapGarden.application.fi.company.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public interface SynService {

	/**
	 * 从sap取数并且跟本地数据对比，然后更新本地数据
	 * @param sapDataCollection
	 * @param type
	 * @param optType
	 * @param user
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean syn(SapDataCollection sapDataCollection,String opttype,String user,boolean ifLog)throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
