package com.sapGarden.system.cache;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

public class MemcacheCached implements Cache{

	private MemcachedClient client ;
	private String name;
	private static final int defaultWeight = 0;
	
	public MemcacheCached(){
		
	}
	public MemcacheCached(String name,MemcachedClient client){
		this.name=name;
		this.client=client;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		try {
			client.flushAll();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void evict(Object key) {
		// TODO Auto-generated method stub
		try {
			this.client.delete(objectToString(key));
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public ValueWrapper get(Object key) {
		// TODO Auto-generated method stub
		try {
			Object value = this.client.get(objectToString(key));
			return (value != null ? new SimpleValueWrapper(value) : null);  
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	public void setName(String name) { 
	     this.name = name; 
	} 

	@Override
	public Object getNativeCache() {
		// TODO Auto-generated method stub
		return client;
	}
	public void setClient(MemcachedClient client){
		this.client=client;
	}

	@Override
	public void put(Object key, Object value) {
		// TODO Auto-generated method stub
		try {
			//this.client.set(objectToString(key),defaultWeight, value);
			Object obj = client.get(objectToString(key));
			if(null == obj){
				//set(java.lang.String key, int exp, java.lang.Object value, long timeout) 
				this.client.set(objectToString(key), defaultWeight, value,5*60*1000);
			}else{
				this.client.replace(objectToString(key), defaultWeight, value,5*60*1000);
			}
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	private static String objectToString(Object object) {
		if (object == null) {
			return null;
		} else if (object instanceof String) {
			return (String) object;
		} else {
			return object.toString();
		}
	}  
}
