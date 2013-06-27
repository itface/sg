package com.sapGarden.system.org.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.sapGarden.system.org.model.NewUser;
import com.sapGarden.system.org.model.User;

public interface UserService {

	public List<User> findAll();
	public JSONObject findAllJson();
	public JSONObject findOneJson();
	public void addOne(User user);
	public void addOneByNewUser(NewUser newUser);
	public void updateOne(NewUser newUser);
	public void deleteOne(Long id);
	public String findUserSelect();
	public void countLogin(String username);
	public void updateLastLoginTime(String username);
	public User findByUsername(String username);
}
