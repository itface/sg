package com.sapGarden.application.commons.dataCollection.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.sapGarden.application.commons.dataCollection.model.ExternalUser;

public interface ExternalUserService {

	public JSONObject findAllJson();
	public List<ExternalUser> findAll();
	public ExternalUser findById(long id);
	public void addOne(ExternalUser externalUser);
	public void updateOne(ExternalUser externalUser);
	public void deleteOne(Long id);
	public List<ExternalUser> findByUsernameAndSapclient(String username,String sapclientAlias);
}
