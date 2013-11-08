package com.sapGarden.system.cache;

import java.util.Collection;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

public class MemcacheCacheManager extends AbstractCacheManager {

	private Collection<Cache> caches;

	@Override
	protected Collection<? extends Cache> loadCaches() {
		// TODO Auto-generated method stub
		return this.caches;
	}

	public void setCaches(Collection<Cache> caches) {
		this.caches = caches;
	}
}
