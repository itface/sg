package com.sapGarden.system.cache;

import java.util.List;

import com.sapGarden.system.org.model.User;

public interface CurrentUserService {

	public User findUser(User user);
	public List<User> findAllUser();
}
