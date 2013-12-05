package com.sapGarden.application.commons.dataCollection.service.impl;

import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dao.ExtendDao;
import com.sapGarden.application.commons.dataCollection.model.NewSapDataCollection;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.dataCollection.service.SapDataCollectionService;
import com.sapGarden.global.jqgrid.model.Jqgrid_DataJson;
import com.sapGarden.global.json.JsonUtils;
import com.sapGarden.system.org.model.ExtjsCheckedTree;
import com.sapGarden.system.org.model.NewRole;
import com.sapGarden.system.org.model.Role;
import com.sapGarden.system.org.model.User;
import com.sapGarden.system.org.service.RoleService;
import com.sapGarden.system.startUp.service.StartUpInitialService;
@Service("sapDataCollectionService")
public class SapDataCollectionServiceImpl implements SapDataCollectionService{

	@Autowired
	private ExtendDao<SapDataCollection> extendDao;
	@Autowired
	private RoleService roleService;
	@Autowired
	@Qualifier("initialDbConnectionService")
	private StartUpInitialService startUpInitialService;
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public String addOneByNewSapClient(NewSapDataCollection newSapDataCollection) {
		// TODO Auto-generated method stub
		SapDataCollection sapDataCollection = new SapDataCollection(newSapDataCollection);
		if(this.countByAlias(sapDataCollection.getAlias())>0){
			return "sap数据集不能重复";
		}else if(this.countDb(sapDataCollection.getGardendbip(), sapDataCollection.getGardendbinstance())>0){
			return "garden数据库不能重复";
		}else{
			//创建一个sap数据集，相当于创建一个角色，用于控制菜单显示。sap数据集角色只控制应用菜单，系统菜单由用户类型控制。
			NewRole newRole = new NewRole();
			newRole.setId("_empty");
			newRole.setRoleName(sapDataCollection.getAlias());
			Role role = roleService.addOneByNewRole(newRole);
			sapDataCollection.setRoleid(role.getId());
			extendDao.save(sapDataCollection);
			//new Thread(startUpInitialService).start();
			startUpInitialService.run();
		}
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteOne(Long id) {
		// TODO Auto-generated method stub
		//删除sap数据集的同事，删除与它对应的角色
		SapDataCollection sapDataCollection = extendDao.find(SapDataCollection.class, id);
		long roleid = sapDataCollection.getRoleid();
		extendDao.deleteById(SapDataCollection.class, id);
		roleService.deleteOne(roleid);
	}

	@Override

	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<SapDataCollection> findAll() {
		// TODO Auto-generated method stub
		return extendDao.find("from SapDataCollection t");
	}

	@Override

	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public JSONObject findAllJson() {
		// TODO Auto-generated method stub
		List<SapDataCollection> list = this.findAll();
		if(list!=null&&list.size()>0){
			Jqgrid_DataJson jsonData = new Jqgrid_DataJson(0,0,0,list);
			JSONObject jsonObject = JsonUtils.objectToJSONObject(jsonData,new String[]{"users","sapDataCollections"});
			return jsonObject;
		}
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public SapDataCollection findById(long id) {
		// TODO Auto-generated method stub
		return extendDao.find(SapDataCollection.class, id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public String updateOne(NewSapDataCollection newSapDataCollection) {
		// TODO Auto-generated method stub
		SapDataCollection sapDataCollection = new SapDataCollection(newSapDataCollection);
		List<SapDataCollection> list = extendDao.find("from SapDataCollection t where t.alias=?", new Object[]{sapDataCollection.getAlias()});
		if(list!=null&&list.size()>0){
			if(list.size()==1){
				for(SapDataCollection sc : list){
					if(sc.getId()!=sapDataCollection.getId()){
						return "sap数据集不能重复";
					}
				}
			}else{
				return "sap数据集不能重复";
			}
			
		}
		list = extendDao.find("from SapDataCollection t where t.gardendbip=?1 and t.gardendbinstance=?2", new Object[]{sapDataCollection.getGardendbip(),sapDataCollection.getGardendbinstance()});
		if(list!=null&&list.size()>0){
			if(list.size()==1){
				for(SapDataCollection sc : list){
					if(sc.getId()!=sapDataCollection.getId()){
						return "garden数据库不能重复";
					}
				}
			}else{
				return "garden数据库不能重复";
			}
		}
		SapDataCollection newsapDataCollection = extendDao.save(sapDataCollection);
		startUpInitialService.run();
		//更新当前sapclient
		if(SecurityContextHolder.getContext().getAuthentication()!=null&&SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null){
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			//如果当前登录的client就是修改的那个client，则更新
			if(user.getCurrentSapDataCollection().getId()==newsapDataCollection.getId()){
				user.setCurrentSapDataCollection(newsapDataCollection);
			}
		}
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public JSONArray findCheckedTreeJson() {
		// TODO Auto-generated method stub
		List<SapDataCollection> sapDataCollectionList = extendDao.find("from SapDataCollection");
		if(sapDataCollectionList!=null&&sapDataCollectionList.size()>0){
			List<ExtjsCheckedTree> tree = new LinkedList<ExtjsCheckedTree>();
			for(SapDataCollection sapDataCollection : sapDataCollectionList){
				ExtjsCheckedTree node = new ExtjsCheckedTree();
				node.setId(sapDataCollection.getId());
				node.setLeaf(true);
				node.setText(sapDataCollection.getAlias());
				node.setAttributes(sapDataCollection.getRoleid()+"");
				tree.add(node);
			}
			JSONArray jsonArray = JSONArray.fromObject(tree); 
			return jsonArray;
		}
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public long countByAlias(String alias) {
		// TODO Auto-generated method stub
		long count = extendDao.findTotalCount("select count(*) as num from SapDataCollection t where t.alias=?", new Object[]{alias});
		return count;
	}
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public long countDb(String dbIp,String dbInstance) {
		// TODO Auto-generated method stub
		long count = extendDao.findTotalCount("select count(*) as num from SapDataCollection t where t.gardendbip=?1 and t.gardendbinstance=?2", new Object[]{dbIp,dbInstance});
		return count;
	}

	@Override
	public SapDataCollection findByAlias(String alias) {
		// TODO Auto-generated method stub
		List<SapDataCollection> list = extendDao.findNotInContext("from SapDataCollection t where t.alias=?1", new Object[]{alias});
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
