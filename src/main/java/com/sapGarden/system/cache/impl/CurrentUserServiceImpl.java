package com.sapGarden.system.cache.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.code.ssm.api.InvalidateAssignCache;
import com.google.code.ssm.api.ParameterDataUpdateContent;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughAssignCache;
import com.google.code.ssm.api.ReadThroughMultiCache;
import com.google.code.ssm.api.ReadThroughMultiCacheOption;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.google.code.ssm.api.UpdateAssignCache;
import com.sapGarden.system.cache.CurrentUserService;
import com.sapGarden.system.org.model.User;
@Service
public class CurrentUserServiceImpl implements CurrentUserService{


	@Override
	@ReadThroughMultiCache(option = @ReadThroughMultiCacheOption(generateKeysFromResult = true),namespace = "currentUser")
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ReadThroughAssignCache(namespace = "currentUser", expiration = 3000)
	public User findUser(@ParameterValueKeyProvider String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@UpdateAssignCache(assignedKey = "username",namespace = "currentUser", expiration = 3000)
	public void setUser(@ParameterValueKeyProvider @ParameterDataUpdateContent User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@InvalidateAssignCache(namespace = "currentUser") 
	public void removeUser(@ParameterValueKeyProvider String userid) {
		// TODO Auto-generated method stub
		
	}

	

}
