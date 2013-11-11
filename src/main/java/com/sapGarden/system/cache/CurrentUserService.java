package com.sapGarden.system.cache;

import java.util.List;

import com.sapGarden.system.org.model.User;

public interface CurrentUserService {

	public User findUser(String userid);
	public List<User> findAllUser();
	public void setUser(User user);
	public void removeUser(String userid);
}
