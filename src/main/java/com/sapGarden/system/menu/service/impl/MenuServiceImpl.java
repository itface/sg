package com.sapGarden.system.menu.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sapGarden.application.commons.dao.ExtendDao;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.dataCollection.service.SapDataCollectionService;
import com.sapGarden.global.constants.SystemConstants;
import com.sapGarden.system.menu.model.CheckedTreeMenu;
import com.sapGarden.system.menu.model.Menu;
import com.sapGarden.system.menu.service.MenuService;
import com.sapGarden.system.org.model.Resource;
import com.sapGarden.system.org.model.Role;
import com.sapGarden.system.org.model.User;
import com.sapGarden.system.org.service.ResourceService;
import com.sapGarden.system.org.service.RoleService;
import com.sapGarden.system.org.service.UserService;
@Service
public class MenuServiceImpl implements MenuService{
	@Autowired
	private UserService userService;
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private SapDataCollectionService sapDataCollectionService;
	@Autowired
	private ExtendDao extendDao;
	
	@Override
	public JSONArray findAllMenuJson() {
		// TODO Auto-generated method stub
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//如果是管理员，加载所有菜单。否则根据权限加载
		if(user.getUsername().equals(SystemConstants.SUPER_ADMINISTRATOR)){
			long sapclient = user.getCurrentSapDataCollection().getId();
			List<Resource> resourceList = new LinkedList<Resource>();
			List<SapDataCollection> sapDataCollectionList = new LinkedList();
			if(sapclient==0){
				resourceList = (List<Resource>)resourceService.findSystemResource();
				sapDataCollectionList.addAll(sapDataCollectionService.findAll());
			}else{
				//resourceList = (List<Resource>)resourceService.findAllResource();
				List<Resource> systemResources = (List<Resource>)resourceService.findSystemResource();
				long roleid = user.getCurrentSapDataCollection().getRoleid();
				Role role = roleService.findRoleById(roleid);
				Set<Resource> resources = role.getResources();
				resources.addAll(systemResources);
				resourceList.addAll(resources);
				Collections.sort(resourceList, new Mycomparator());
				sapDataCollectionList.add(user.getCurrentSapDataCollection());
			}
			if(resourceList!=null&&resourceList.size()>0){
				List<Menu> menuList = new LinkedList();
				//List<SapDataCollection> sapDataCollectionList = sapDataCollectionService.findAll();
				this.resourceToMenu(menuList, resourceList,null,sapDataCollectionList);
				if(menuList.size()>0){
					JSONArray jsonArray = JSONArray.fromObject(menuList);
					return jsonArray;
				}
			}
		}else{
			SapDataCollection sapDataCollection = user.getCurrentSapDataCollection();
			long roleid = sapDataCollection.getRoleid();
			//因为用户信息存在缓存中，所果修改完权限，刷新是看不到效果的，所以这里实时去取用户信息
			user = userService.findByUsername(user.getUsername());
			Set<Role> roles = user.getRoles();//(List<Role>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			//Set<Role> roles = user.getRoles();
			//用户至少有两个角色，一个是sap数据集角色，用于控制可以防问哪个sap数据集，这个角色对应的菜单都是应用菜单，不包括系统菜单
			//别一个角色是用户类型，用于控制可以防问哪些系统菜单
			long usertype = user.getUsertype();
			if(roles!=null){
				List<Resource> resourceList =	new LinkedList<Resource>();
				Set<Resource> resourcesSet = new HashSet<Resource>();
				for(Role role : roles){
					if(role.getId()==roleid||role.getId()==usertype){
						Set<Resource> resources = role.getResources();
						if(resources!=null&&resources.size()>0){
							//resourceList.addAll(resources);
							resourcesSet.addAll(resources);
						}
					}					
				}
				resourceList.addAll(resourcesSet);
				Collections.sort(resourceList, new Mycomparator());
				if(resourceList.size()>0){
					List<Menu> menuList = new LinkedList();
					List<SapDataCollection> sapDataCollectionList = new LinkedList();
					sapDataCollectionList.add(sapDataCollection);
//					if(user.getSapDataCollections()!=null&&user.getSapDataCollections().size()>0){
//						sapDataCollectionList.addAll(user.getSapDataCollections());
//					}
					this.resourceToMenu(menuList, resourceList,null,sapDataCollectionList);
					if(menuList.size()>0){
						JSONArray jsonArray = JSONArray.fromObject(menuList);
						return jsonArray;
					}
				}
			}
		}
		return null;
	}
	/**
	 * 把List<Resource>，转换成List<Menu> menuList。
	 * 
	 * @param menuList
	 * @param resourceList
	 * @param father
	 */
	private void resourceToMenu(List<Menu> menuList,List<Resource> resourceList,Menu father,List<SapDataCollection> sapDataCollectionList){
		//如果father为空，说明是根节点，第一层菜单
		if(father==null){
			father = new Menu();
			for(Resource resource : resourceList){
				//循环找出父节点为根节点的节点
				if(resource.getParentId()==0){
					father.setId(resource.getId());
					father.setText(resource.getResourceName());
					father.setUrl(resource.getUrl());
					father.setHidden(resource.getIsshow()==0?true:false);
					//递归调用本方法，以当前节点为父节点，找出当前节点的子节点
					this.resourceToMenu(menuList,resourceList, father,sapDataCollectionList);
					//只用添加根目录到menulist中就行，其它都是根目录的子节点，在根目录的children中
					menuList.add(father);
				}
			}
		}else{
			//如果father不为空，说明不是根目录，都在根children中
			List<Menu> sonList = new LinkedList();
			for(Resource resource : resourceList){
				if(resource.getParentId()==father.getId()){
					Menu son = new Menu();
					son.setId(resource.getId());
					son.setText(resource.getResourceName());
					son.setUrl(resource.getUrl());
					son.setHidden(resource.getIsshow()==0?true:false);
					//递归，找出子节点的子节点
					this.resourceToMenu(menuList,resourceList,son,sapDataCollectionList);
					if(SystemConstants.RESOURCE_DATACOLLECTIONMENU_ALIAS.equals(resource.getAlias())){
						int count =-1;
						List<Menu> mlist = new LinkedList();
						for(SapDataCollection sapDataCollection:sapDataCollectionList){
							Menu m = new Menu();
							m.setId(count);
							m.setText(sapDataCollection.getSapserverclient());
							m.setLeaf(true);
							m.setUrl(SystemConstants.RESOURCE_DATACOLLECTIONMENU_URL_PREFIX+"/"+sapDataCollection.getRoleid());
							mlist.add(m);
							count--;
						}
						if(mlist.size()>0){
							son.setChildren(mlist);
						}
					}
					sonList.add(son);
				}
			}
			//如果有子节点，说明不是根结点，同时把子节点放到father中。否则说明是叶子节点，返回，是递归出口
			if(sonList.size()>0){
				father.setLeaf(false);
				father.setChildren(sonList);
			}else{
				if(father.url==null||"".equals(father.url.trim())){
					father.setLeaf(false);
				}else{
					father.setLeaf(true);
				}
				return;
			}
		}
		
	}
	private void resourceToCheckedTreeMenu(List<CheckedTreeMenu> menuList,List<Resource> resourceList,CheckedTreeMenu father,Set<Long> checkedResource){
		//如果father为空，说明是根节点，第一层菜单
		if(father==null){
			father = new CheckedTreeMenu();
			for(Resource resource : resourceList){
				//循环找出父节点为根节点的节点
				if(resource.getParentId()==0){
					father.setId(resource.getId());
					father.setText(resource.getResourceName());
					father.setUrl(resource.getUrl());
					//递归调用本方法，以当前节点为父节点，找出当前节点的子节点
					this.resourceToCheckedTreeMenu(menuList,resourceList,father,checkedResource);
					//只用添加根目录到menulist中就行，其它都是根目录的子节点，在根目录的children中
					menuList.add(father);
				}
			}
		}else{
			//如果father不为空，说明不是根目录，都在根children中
			List<CheckedTreeMenu> sonList = new LinkedList();
			for(Resource resource : resourceList){
				if(resource.getParentId()==father.getId()){
					CheckedTreeMenu son = new CheckedTreeMenu();
					son.setId(resource.getId());
					son.setText(resource.getResourceName());
					son.setUrl(resource.getUrl());
					//递归，找出子节点的子节点
					this.resourceToCheckedTreeMenu(menuList,resourceList,son,checkedResource);
					sonList.add(son);
				}
			}
			//如果有子节点，说明不是根结点，同时把子节点放到father中。否则说明是叶子节点，返回，是递归出口
			if(sonList.size()>0){
				father.setLeaf(false);
				father.setChildren(sonList);
			}else{
				father.setLeaf(true);
				//因为树是关联选中的，所以只要选中叶子节点就行,如果选中了非叶子节点，则该节点下选有的子节点都会被选中
				if(checkedResource!=null&&checkedResource.contains(father.getId())){
					father.setChecked(true);
				}
				return;
			}
		}
		
	}
	@Override
	public JSONArray findCheckedTreeJson() {
		// TODO Auto-generated method stub
		List<Resource> resourceList = (List<Resource>)resourceService.findAllResource();
		if(resourceList!=null&&resourceList.size()>0){
			List<CheckedTreeMenu> menuList = new LinkedList();
			this.resourceToCheckedTreeMenu(menuList,resourceList,null,null);
			if(menuList.size()>0){
				JSONArray jsonArray = JSONArray.fromObject(menuList); 
				return jsonArray;
			}
		}
		return null;
	}
	@Override
	public JSONArray findNotSystemMenuCheckedTree(long roleId) {
		// TODO Auto-generated method stub
		List<Resource> resourceList = (List<Resource>)resourceService.findNotSystemResource();
		if(resourceList!=null&&resourceList.size()>0){
			List<CheckedTreeMenu> menuList = new LinkedList();
			Role role = roleService.findRoleById(roleId);
			Set<Long> resourceIds = null;
			if(role!=null){
				Set<Resource> resources = role.getResources();
				if(resources!=null){
					resourceIds=new HashSet<Long>();
					for(Resource resource : resources){
						resourceIds.add(resource.getId());
					}
				}
			}
			this.resourceToCheckedTreeMenu(menuList,resourceList,null,resourceIds);
			if(menuList.size()>0){
				JSONArray jsonArray = JSONArray.fromObject(menuList); 
				return jsonArray;
			}
		}
		return null;
	}
	@Override
	public JSONArray getMenuCheckedTreeByRoleId(long roleId) {
		// TODO Auto-generated method stub
		Role role = roleService.findRoleById(roleId);
		if(role!=null){
			Set<Resource> resources = role.getResources();
			if(resources!=null){
				List<Resource> resourceList = new LinkedList<Resource>();
				resourceList.addAll(resources);
				if(resourceList!=null&&resourceList.size()>0){
					List<CheckedTreeMenu> menuList = new LinkedList();
					this.resourceToCheckedTreeMenu(menuList,resourceList,null,null);
					if(menuList.size()>0){
						menuList.get(0).text=role.getRoleName();
						JSONArray jsonArray = JSONArray.fromObject(menuList); 
						return jsonArray;
					}
				}
			}
		}
		return null;
	}
	@Override
	public JSONArray getDataCollectionTreeByRoleId(long roleId) {
		// TODO Auto-generated method stub
		Role role = roleService.findRoleById(roleId);
		if(role!=null){
			Set<Resource> resources = role.getResources();
			if(resources!=null){
				List<Resource> resourceList = new LinkedList<Resource>();
				resourceList.addAll(resources);
				if(resourceList!=null&&resourceList.size()>0){
					List<Menu> menuList = new LinkedList<Menu>();
					this.dataCollectionExtTree(menuList,resourceList,null);
					if(menuList.size()>0){
						menuList.get(0).text=role.getRoleName();
						JSONArray jsonArray = JSONArray.fromObject(menuList); 
						return jsonArray;
					}
				}
			}
		}
		return null;
	}
	private void dataCollectionExtTree(List<Menu> menuList,List<Resource> resourceList,Menu father){
		//如果father为空，说明是根节点，第一层菜单
		if(father==null){
			father = new Menu();
			for(Resource resource : resourceList){
				//循环找出父节点为根节点的节点
				if(resource.getParentId()==0){
					father.setId(resource.getId());
					father.setText(resource.getResourceName());
					father.setUrl(resource.getUrl());
					//递归调用本方法，以当前节点为父节点，找出当前节点的子节点
					this.dataCollectionExtTree(menuList,resourceList,father);
					//只用添加根目录到menulist中就行，其它都是根目录的子节点，在根目录的children中
					menuList.add(father);
				}
			}
		}else{
			//如果father不为空，说明不是根目录，都在根children中
			List<Menu> sonList = new LinkedList<Menu>();
			for(Resource resource : resourceList){
				if(resource.getParentId()==father.getId()){
					Menu son = new Menu();
					son.setId(resource.getId());
					son.setText(resource.getResourceName());
					son.setUrl(resource.getUrl());
					//递归，找出子节点的子节点
					this.dataCollectionExtTree(menuList,resourceList,son);
					sonList.add(son);
				}
			}
			//如果有子节点，说明不是根结点，同时把子节点放到father中。否则说明是叶子节点，返回，是递归出口
			if(sonList.size()>0){
				father.setLeaf(false);
				father.setChildren(sonList);
			}else{
				father.setLeaf(true);
			}
		}
		
	}
}
