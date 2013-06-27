package com.sapGarden.system.org.service.impl;

import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dao.ExtendDao;
import com.sapGarden.system.org.model.Menu;
import com.sapGarden.system.org.model.Resource;
import com.sapGarden.system.org.model.ResourceJson;
import com.sapGarden.system.org.model.ResourceRowsJson;
import com.sapGarden.system.org.model.Role;
import com.sapGarden.system.org.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	private ExtendDao<Resource> extendDao;
	
//	private ResourceDao resourceDao;
//	@Autowired
//	public void setResourceDao(ResourceDao resourceDao) {
//		this.resourceDao = resourceDao;
//	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addOne(Resource resource) {
		// TODO Auto-generated method stub
		//resourceDao.save(resource);
		extendDao.persist(resource);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteOne(Long id) {
		// TODO Auto-generated method stub
		//先更新兄弟节点的resourceIndex，再执行删除
		extendDao.executeUpdate("update Resource r set r.resourceIndex=r.resourceIndex-1 where r.parentId=(select r2.parentId from Resource r2 where r2.id=?) and r.resourceIndex>(select r3.resourceIndex from Resource r3 where r3.id=?)", new Object[]{id});
		extendDao.deleteById(Resource.class, id);
		List<Resource> list = extendDao.find("from Resource r where t.parentId=?", new Object[]{});
		//resourceDao.updateResourceIndexWhenDel(id);
		//resourceDao.delete(id);
		//List<Resource> list = resourceDao.findResourcesByParentId(id);
		if(list!=null){
			//递归删除子节点
			for(Resource resource : list){
				this.deleteOne(resource.getId());
			}
			this.dalateByList(list);
		}
		
	}

	public void dalateByList(List<Resource> list){
		//resourceDao.delete(list);
		extendDao.deleteList(list);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateOne(Resource resource) {
		// TODO Auto-generated method stub
		//更新时，先判断index是否改变，如果改变，需要改变其它相关节点的index
		Resource oldResource = (Resource)extendDao.find(Resource.class, resource.getId());
		if(oldResource.getResourceIndex()>resource.getResourceIndex()){
			extendDao.executeUpdate("update Resource r set r.resourceIndex=r.resourceIndex+1 where r.parentId=? and r.resourceIndex>=? and r.resourceIndex<? and r.id!=?", new Object[]{resource.getParentId(),resource.getResourceIndex(),oldResource.getResourceIndex(),resource.getId()});
			//resourceDao.updateResourceIndexUp(resource.getParentId(),resource.getId(),oldResource.getResourceIndex(),resource.getResourceIndex());
		}else if(oldResource.getResourceIndex()<resource.getResourceIndex()){
			extendDao.executeUpdate("update Resource r set r.resourceIndex=r.resourceIndex-1 where r.parentId=? and r.resourceIndex<=? and r.resourceIndex>? and r.id!=?", new Object[]{resource.getParentId(),resource.getResourceIndex(),oldResource.getResourceIndex(),resource.getId()});
			//resourceDao.updateResourceIndexDown(resource.getParentId(),resource.getId(),oldResource.getResourceIndex(),resource.getResourceIndex());
		}
		//因为角色跟资源的操作是关联的，所以更新时，必须把角色也更新上，否则会被认识色为空，而删除资源与角色的关系
		//resource.setRoles(oldResource.getRoles());
		extendDao.save(resource);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addOneByMenu(Menu menu) {
		// TODO Auto-generated method stub
		//因为新增时jqgrid传到后台的参数id=_empty,parent=null,不符合resource的数据格式，所以用Menu来临时转换一下
		Resource resource = new Resource(menu);
		long num = this.getSonNums(resource.getParentId());
		//如果新增加的记录的index大于现有的index，则直接新增，否则要更新想关兄弟节点的index
		if(resource.getResourceIndex()>num){
			this.addOne(resource);
		}else{
			//新增的这条记录的index默认为最大
			extendDao.executeUpdate("update Resource r set r.resourceIndex=r.resourceIndex+1 where r.parentId=? and r.resourceIndex>=? and r.resourceIndex<? and r.id!=?", new Object[]{resource.getParentId(),resource.getResourceIndex(),num+1,resource.getId()});
			//resourceDao.updateResourceIndexUp(resource.getParentId(),resource.getId(),num+1,resource.getResourceIndex());
			//resourceDao.updateResourceIndexDown(resource.getParentId(),resource.getId(), resource.getResourceIndex());
			this.addOne(resource);
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject findAllJson() {
		// TODO Auto-generated method stub
		List<Resource> list = new LinkedList();
		List<Resource> resourceList = this.findAllResource();
		this.sortResourceRowsJson(resourceList,list,0);
		List<ResourceRowsJson> rowsJson = null;
		if(list!=null&&list.size()>0){
			rowsJson = new LinkedList();
			for(Resource resource : list){
				rowsJson.add(new ResourceRowsJson(resource,extendDao));
			}
			if(rowsJson.size()>0){
				ResourceJson resourceJson = new ResourceJson(rowsJson);
				try {
					JSONObject jsonObject = JSONObject.fromObject(resourceJson);
					return jsonObject;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 因为jqgrid的treegrid要求子节点与父节点在一起，否则没有树型效果
	 * List<Resource> list己经按id排好序，但是要细化排序，然子节点与父节点在一起
	 * @param list
	 * @param targetList
	 * @param parent
	 */
	private void sortResourceRowsJson(List<Resource> list,List<Resource> targetList,long parent){
		if(list!=null&&list.size()>0){
			if(list.size()==targetList.size()){
				return;
			}
			for(Resource resource : list){
				if(resource.getParentId()==parent){
					targetList.add(resource);
					this.sortResourceRowsJson(list, targetList, resource.getId());
				}
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getSonNums(long parentId) {
		// TODO Auto-generated method stub
		return extendDao.findTotalCount("select count(r.id) from Resource r where r.parentId=?", new Object[]{parentId});
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Resource> findSystemResource() {
		// TODO Auto-generated method stub
		List<Resource> resourceList = extendDao.find("from Resource as r where r.issystem=1 order by r.parentId,r.resourceIndex");
		return resourceList;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Resource> findNotSystemResource() {
		// TODO Auto-generated method stub
		List<Resource> resourceList = extendDao.find("from Resource as r where r.issystem!=1 or r.parentId=0 order by r.parentId,r.resourceIndex");
		return resourceList;
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Resource> findAllResource() {
		// TODO Auto-generated method stub
		return extendDao.find("from Resource as r order by r.parentId,r.resourceIndex");
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Resource findById(long id) {
		// TODO Auto-generated method stub
		return extendDao.find(Resource.class, id);
	}
}
