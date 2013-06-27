package com.sapGarden.system.security;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dataCollection.model.SapDataCollection;
import com.sapGarden.application.commons.dataCollection.service.SapDataCollectionService;
import com.sapGarden.global.constants.SystemConstants;
import com.sapGarden.global.exception.CustomException;
import com.sapGarden.system.org.model.User;
import com.sapGarden.system.org.service.UserService;


public class ValidatedUsernamePasswordAuthenticationFilter  extends UsernamePasswordAuthenticationFilter{

	@Autowired
	private SapDataCollectionService sapDataCollectionService;
	@Autowired
	private UserService userService;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException {
		Authentication authentication = null;
		//Locale locale = request.getLocale(); 
		//String ip = request.getRemoteAddr();
		try {
			authentication = super.attemptAuthentication(request, response);	
			if(authentication!=null){
				User user = (User)authentication.getPrincipal();
				String s = request.getParameter("sapDataCollection");
				if(SystemConstants.SUPER_ADMINISTRATOR.equals(user.getUsername())){
					SapDataCollection sapDataCollection = null;
					if("0".equals(s)){
						sapDataCollection = new SapDataCollection();
						sapDataCollection.setId(0);
						sapDataCollection.setAlias("管理员");
					}else{
						sapDataCollection = sapDataCollectionService.findById(Long.parseLong(s));
					}
					user.setCurrentSapDataCollection(sapDataCollection);
				}else{
					Set<SapDataCollection> sapDataCollections = user.getSapDataCollections();
					boolean flag = true;
					if(sapDataCollections!=null){
						for(SapDataCollection sapDataCollection : sapDataCollections){
							if((sapDataCollection.getId()+"").equals(s)){
								user.setCurrentSapDataCollection(sapDataCollection);
								flag=false;
							}
						}
						if(flag){
							throw new CustomException();
						}
					}
				}
				userService.updateLastLoginTime(user.getUsername());
				userService.countLogin(user.getUsername());
			}
			//super.setFilterProcessesUrl(filterProcessesUrl);
			/*
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String sapclient = request.getParameter("sapclient");
			Set<SapClientModel> sapClientModels = user.getSapClientModels();
			boolean flag = true;
			if(sapClientModels!=null){
				for(SapClientModel sapClientModel : sapClientModels){
					if((sapClientModel.getId()+"").equals(sapclient)){
						user.setCurrentSapClientModel(sapClientModel);
						flag=false;
					}
				}
				if(!flag){
					throw new CustomException();
				}
			}
			*/
		} catch (AuthenticationException e) {
			request.setAttribute("error", "用户名或密码错误");
			throw e;
		}catch (CustomException e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "没有sap客户端的权限");
			throw new CustomAuthenticationException("没有sap客户端的权限");
		}
		return authentication;
	}
}
