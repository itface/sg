package com.sapGarden.system.cache.userSession;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;

public interface CurrentSapDataCollectionCacheService {

	public SapDataCollection findCurrentSapDataCollection(String userid);
	public SapDataCollection setCurrentSapDataCollection(String userid,SapDataCollection sapDataCollection);
	public void removeCurrentSapDataCollection(String userid);
}
