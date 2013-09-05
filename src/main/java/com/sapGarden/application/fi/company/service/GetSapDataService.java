package com.sapGarden.application.fi.company.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.jco.model.CommonSynModel;
import com.sapGarden.application.commons.jco.model.JcoReturnModel;
/**
 * 从sap取数
 * @author Administrator
 *
 */
public interface GetSapDataService {

	/**
	 * 调rfc返回原始List<JcoReturnModel>
	 * @param sapDataCollection
	 * @param map
	 * @param ifFilter
	 * @return
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public List<JcoReturnModel> callRfc(SapDataCollection sapDataCollection)throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
	/**
	 * 解析callRfc方法取的的原始List<JcoReturnModel>
	 * @param sapDataCollection
	 * @param map
	 * @param ifFilter
	 * @return
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public List<CommonSynModel> getData(SapDataCollection sapDataCollection)throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
