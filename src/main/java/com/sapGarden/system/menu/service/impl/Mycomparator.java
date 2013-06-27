package com.sapGarden.system.menu.service.impl;

import java.util.Comparator;

import com.sapGarden.system.org.model.Resource;

public class Mycomparator<T> implements Comparator<T>{

	@Override
	public int compare(T o1, T o2) {
		// TODO Auto-generated method stub
		Resource t1 = (Resource)o1;
		Resource t2 = (Resource)o2;
		if(t1.getParentId()>t2.getParentId()){
			return 1;
		}else if(t1.getParentId()<t2.getParentId()){
			return -1;
		}else{
			if(t1.getResourceIndex()>t2.getResourceIndex()){
				return 1;
			}else{
				return -1;
			}
		}
	}

}
