package com.sapGarden.system.org.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dao.ExtendDao;
import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.global.constants.SystemConstants;
import com.sapGarden.system.org.model.NewUser;
import com.sapGarden.system.org.model.User;
import com.sapGarden.system.org.model.UserJson;
import com.sapGarden.system.org.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private ExtendDao<User> extendDao;


	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addOneByNewUser(NewUser newUser) {
		// TODO Auto-generated method stub
		User user = new User(newUser);
		extendDao.persist(user);
		//userDao.save(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteOne(Long id) {
		// TODO Auto-generated method stub
		extendDao.deleteById(User.class, id);
		//userDao.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject findAllJson() {
		// TODO Auto-generated method stub
		List<User> users = this.findAll();
		if(users!=null&&users.size()>0){
			UserJson userJson = new UserJson(users);
			try {
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.setExcludes(new String[]{"users","roles","sapClientModels"});
				JSONObject jsonObject = JSONObject.fromObject(userJson,jsonConfig);
				return jsonObject;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject findOneJson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateOne(NewUser newUser) {
		// TODO Auto-generated method stub
		User user = new User(newUser);
		user = extendDao.save(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addOne(User user) {
		// TODO Auto-generated method stub
		//userDao.save(user);
		extendDao.persist(user);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String findUserSelect() {
		// TODO Auto-generated method stub
		List<User> list = extendDao.find("select t from "+User.class.getName()+" t join t.roles r where t.username!='"+SystemConstants.SUPER_ADMINISTRATOR+"' and r.roleName='系统接入用户'");
		StringBuffer sb = new StringBuffer("<select>");
		if(list!=null){
			sb.append("<option value='' extendProperty=''>空</option>");
			for(User user : list){
				Set<SapDataCollection> sapDataCollections = user.getSapDataCollections();
				Iterator it = sapDataCollections.iterator();
				SapDataCollection sapDataCollection = (SapDataCollection)it.next();
				sb.append("<option value='"+user.getUsername()+"' extendProperty='"+sapDataCollection.getAlias()+"'>"+user.getUsername()+"</option>");
			}
		}
		sb.append("</select>");
		return sb.toString();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return extendDao.find("from "+User.class.getName()+" t where t.username!='"+SystemConstants.SUPER_ADMINISTRATOR+"'");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void countLogin(String username) {
		// TODO Auto-generated method stub
		extendDao.executeUpdate("update "+User.class.getName()+" t set t.logincount=t.logincount+1 where t.username=?", new Object[]{username});
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateLastLoginTime(String username) {
		// TODO Auto-generated method stub
		extendDao.executeUpdate("update "+User.class.getName()+" t set t.lastlogintime=sysdate where t.username=?", new Object[]{username});
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return extendDao.findSingleResult("from "+User.class.getName()+" t where t.username=?1",new Object[]{username});
	}

}
