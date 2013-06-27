package com.sapGarden.system.org.service.impl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dao.ExtendDao;
import com.sapGarden.global.constants.SystemConstants;
import com.sapGarden.system.db.DbContextHolder;
import com.sapGarden.system.org.model.ExtjsCheckedTree;
import com.sapGarden.system.org.model.NewRole;
import com.sapGarden.system.org.model.Resource;
import com.sapGarden.system.org.model.Role;
import com.sapGarden.system.org.model.RoleJson;
import com.sapGarden.system.org.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private ExtendDao<Role> extendDao;

	

	@Override
	public List<Role> findAll(){
		return extendDao.find("from Role r");
	}
	@Override
	public JSONObject findAllJson() {
		// TODO Auto-generated method stub
		List<Role> roles = this.findAll();
		if(roles!=null&&roles.size()>0){
			RoleJson roleJson = new RoleJson(roles);
			try {
				JsonConfig jsonConfig = new JsonConfig();
				//因为的Resource和role是多对多关联的，互相关联，生成json对象时会形成死循环，所以排除user对象和roles对象
				jsonConfig.setExcludes(new String[]{"users","roles"});
				//jsonConfig.setCycleDetectionStrategy(cycleDetectionStrategy)
				JSONObject jsonObject = JSONObject.fromObject(roleJson,jsonConfig);
				return jsonObject;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Role addOneByNewRole(NewRole newRole) {
		// TODO Auto-generated method stub
		Role role = new Role(newRole);
		//roleDao.save(role);
		return extendDao.persist(role);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteOne(Long id) {
		// TODO Auto-generated method stub
		extendDao.deleteById(Role.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateOne(NewRole newRole) {
		// TODO Auto-generated method stub
		Role role = new Role(newRole);
		//roleDao.save(role);
		//因为角色跟用户的操作是关联的，所以更新时，必须把用户也更新上，否则会被认识用户为空，而删除用户与角色的关系
		//Role oldRole = extendDao.find(Role.class, role.getId());
		//role.setUsers(oldRole.getUsers());
		extendDao.save(role);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray findCheckedTreeJson() {
		// TODO Auto-generated method stub
		List<Role> roles = this.findAll();
		if(roles!=null&&roles.size()>0){
			List<ExtjsCheckedTree> tree = new LinkedList<ExtjsCheckedTree>();
			for(Role role : roles){
				ExtjsCheckedTree node = new ExtjsCheckedTree();
				node.setId(role.getId());
				node.setLeaf(true);
				node.setText(role.getRoleName());
				tree.add(node);
			}
			JSONArray jsonArray = JSONArray.fromObject(tree); 
			return jsonArray;
		}
		return null;
	}
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String findRoleSelect() {
		// TODO Auto-generated method stub
		List<Role> list = this.findAll();
		StringBuffer sb = new StringBuffer("<select>");
		if(list!=null){
			for(Role role : list){
				String roleAlias = role.getAlias();
				if(roleAlias!=null&&roleAlias.indexOf(SystemConstants.RoleAlias_USERTYPE_PREFIX)==0){
					sb.append("<option value='"+role.getId()+"'>"+role.getRoleName()+"</option>");
				}
			}
		}
		sb.append("</select>");
		return sb.toString();
	}
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Role findRoleById(long id) {
		// TODO Auto-generated method stub
		Role role = extendDao.find(Role.class, id);
		return role;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateRoleResource(long roleId, String resourceIds) {
		// TODO Auto-generated method stub
		Role role = this.findRoleById(roleId);
		Set<Resource> resources = new HashSet<Resource>(); 
		if(role!=null){
			if(resourceIds!=null&&!resourceIds.equals("")){
				String[] ids = resourceIds.split(",");
				for(int i=0;i<ids.length;i++){
					Resource resource = new Resource();
					resource.setId(Long.parseLong(ids[i]));
					resources.add(resource);
				}
			}
			role.setResources(resources);
		}
		//extendDao.save(role);
	}
}
