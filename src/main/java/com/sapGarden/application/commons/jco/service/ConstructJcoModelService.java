package com.sapGarden.application.commons.jco.service;

import java.util.List;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.jco.model.ImportModel;
import com.sapGarden.application.commons.jco.model.JcoModel;
import com.sapGarden.application.commons.jco.model.TableImpModel;
/**
 * 用于构件rfc的输入输出参数对象
 * @author Administrator
 *
 */
public interface ConstructJcoModelService {

	public JcoModel construct(SapDataCollection sapDataCollection,List<ImportModel> importModelList,List<TableImpModel> tableImpModelList);
}
