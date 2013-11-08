package com.sapGarden.system.cache.userSession.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.system.cache.userSession.CurrentSapDataCollectionCacheService;

@Service
public class UserSapDataCollectionCacheServiceImpl implements CurrentSapDataCollectionCacheService{

	
	
	@Override
	@Cacheable(value="userSapclient",key="#userid")
	public SapDataCollection findCurrentSapDataCollection(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@CachePut(value="userSapclient",key="#userid")
	public SapDataCollection setCurrentSapDataCollection(String userid,SapDataCollection sapDataCollection) {
		// TODO Auto-generated method stub
		return sapDataCollection;
	}

	@Override
	@CacheEvict(value="userSapclient",key="#userid")
	public void removeCurrentSapDataCollection(String userid) {
		// TODO Auto-generated method stub
		
	}

}
