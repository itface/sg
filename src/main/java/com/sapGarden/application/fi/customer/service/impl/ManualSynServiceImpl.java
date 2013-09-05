package com.sapGarden.application.fi.customer.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.log.service.CommonServiceWithLog;
import com.sapGarden.application.commons.service.commonService.CommonService;
import com.sapGarden.application.fi.customer.model.Kna1Compared;
import com.sapGarden.application.fi.customer.model.Knb1Compared;
import com.sapGarden.application.fi.customer.model.KnvvCompared;
import com.sapGarden.application.fi.customer.service.CompareDataService;
import com.sapGarden.application.fi.customer.service.ManualSynService;
@Service
public class ManualSynServiceImpl implements ManualSynService{

	private CommonService commonService;
	private CompareDataService compareDataService;
	@Autowired
	public void setCompareDataService(CompareDataService compareDataService) {
		this.compareDataService = compareDataService;
	}
	@Autowired
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	@Override
	public void manualSyn(SapDataCollection sapDataCollection, String user, String kunnr) throws SecurityException, IllegalArgumentException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		compareDataService.initComaredData(sapDataCollection.getId());
		compareDataService.doCompare(sapDataCollection,user, kunnr,"","", "", "");
		List<Kna1Compared> kna1List = commonService.findBySapclient(Kna1Compared.class, sapDataCollection.getId());
		List<Knb1Compared> knb1List = commonService.findBySapclient(Knb1Compared.class, sapDataCollection.getId());
		List<KnvvCompared> knvvList = commonService.findBySapclient(KnvvCompared.class, sapDataCollection.getId());
		compareDataService.comparedDataToLocal(sapDataCollection.getId(), user, CommonServiceWithLog.OPTTYPE_MANUALSYN, kna1List, knb1List, knvvList);
	}

}
