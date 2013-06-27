package com.sapGarden.system.org.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.sapGarden.system.org.model.Menu;
import com.sapGarden.system.org.model.Resource;

public interface ResourceService {

	public void addOne(Resource resource);
	public void addOneByMenu(Menu menu);
	public void updateOne(Resource resource);
	public void deleteOne(Long id);
	public void dalateByList(List<Resource> list);
	public List<Resource> findSystemResource();
	public List<Resource> findNotSystemResource();
	public JSONObject findAllJson();
	public List<Resource> findAllResource();
	/**
	 * 获取同一层级的节点个数
	 * @return
	 */
	public long getSonNums(long parentId);
	
	public Resource findById(long id);
}
