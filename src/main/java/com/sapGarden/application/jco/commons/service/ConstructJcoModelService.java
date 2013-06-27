package com.sapGarden.application.jco.commons.service;

import java.util.List;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.jco.commons.model.ImportModel;
import com.sapGarden.application.jco.commons.model.JcoModel;
import com.sapGarden.application.jco.commons.model.TableImpModel;

public interface ConstructJcoModelService {

	public JcoModel construct(SapDataCollection sapDataCollection,List<ImportModel> importModelList,List<TableImpModel> tableImpModelList);
}
