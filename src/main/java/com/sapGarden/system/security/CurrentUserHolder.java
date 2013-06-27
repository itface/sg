package com.sapGarden.system.security;



import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;

import com.sapGarden.system.org.model.User;



public final class CurrentUserHolder {

	private CurrentUserHolder() {
	}

	private static SessionRegistry sessionRegistry;
	
	public SessionRegistry getSessionRegistry() {
		return sessionRegistry;
	}
	public void setSessionRegistry(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}
	public static User get() {
		try {
			SecurityContextHolder.getContext().getAuthentication().getAuthorities();//当前用户能访问的资源
			return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (ClassCastException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		}
	}
	public static int getOnlineUserNum(){
		return sessionRegistry.getAllPrincipals().size();
	}
	/**
	 * getAllPrincipals：返回拥有活跃session的Principal对象（典型情况下为UserDetails对象）所组成的List；
  getAllSessions(principal, includeExpired)：得到指定Principal的SessionInformation组成的List，包含了每个session的信息。也能够包含过期的session。
  
  //根据传入的loginId，踢出某用户 
public String destroy() throws Exception { 
   SessionInformation[] sessions_arrs = sessionRegistry.getAllSessions( 
     loginId, false); 
   if (sessions_arrs != null && sessions_arrs.length > 0) { 
    for (int i = 0; i < sessions_arrs.length; i++) { 
     sessions_arrs[i].expireNow(); 
     // sessionRegistry.removeSessionInformation(sessions_arrs[i].getSessionId()); 
    } 
   } 
   return RELOAD; 
} 
	 * @return
	 */
	public static List<Object> getOnlineUserList(){
		for(Object principal: sessionRegistry.getAllPrincipals()) {
			// a principal may have multiple active sessions    
			  for(SessionInformation session : sessionRegistry.getAllSessions(principal, false)){
				  session.expireNow();//销毁一个用户的会话,T出系统
				  session.refreshLastRequest();
				  User userModel = (User) session.getPrincipal();
			  }
		}
		return sessionRegistry.getAllPrincipals();
	}
}
