package com.sapGarden.system.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;

import com.sapGarden.global.constants.SystemConstants;
import com.sapGarden.system.org.model.Resource;
import com.sapGarden.system.org.model.Role;
import com.sapGarden.system.org.model.User;

public class MyAccessDecisionManager implements AccessDecisionManager{

	/*
	 * 用于存放不是菜单的资源，比如登录验证成功之后执行的url。这些url不用权限校验，但必须在平台启动时加进资源列表中，这样
	 * 说明这个url是平台资源，需要校验登录验证
	 */
	private final String[]  SYS_RESOURCE = {"/common/front"};
	@Override
	/**
	 * 	这个方法的第一个参数Authentication 是你登陆的角色所具有的权限列表。
		第二个参数是你访问的url。
		第三个参数是访问这个url所需要的权限列表。
	 */
	public void decide(Authentication authentication, Object object,Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
			InsufficientAuthenticationException {
		//如果是超级管理员，所有资源的权限都有
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(configAttributes == null||SystemConstants.SUPER_ADMINISTRATOR.equals(user.getUsername())) {    
            return;    
        }    
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		String method = request.getMethod();
		String requestUrl = ((FilterInvocation) object).getRequestUrl();  
		if(SYS_RESOURCE!=null){
        	for(int i=0;i<SYS_RESOURCE.length;i++){
        		 if(requestUrl.equals(SYS_RESOURCE[i])){
        			 return;
        		 }
        	}
        }
        //所请求的资源拥有的权限(一个资源对多个权限)，FilterInvocationSecurityMetadataSource的getAttributes取得的资源对应的权限（menuid）    
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();    
        while(iterator.hasNext()) {    
            ConfigAttribute configAttribute = iterator.next();    
            //访问所请求资源所需要的权限    
            String needPermission = configAttribute.getAttribute();   
           // System.out.println("needPermission is " + needPermission);    
            //用户所拥有的权限authentication ,UserDetailsService的loadUserByUsername取得的用户的所有权限 (menuid) 
            Collection<Role> roles = (Collection<Role>)authentication.getAuthorities();
            for(Role role : roles){
            	Set<Resource> resources = role.getResources();
            	for(Resource resource : resources){
            		String url = resource.getUrl();
            		//因为本系统是基于restful风格，所有url都是有层级关系。
            		//由于系统资源是指菜单资源，所以系统控制的权限是指菜单权限，不包括具体增删改查的功能权限（系统认为有某菜单的权限，则该用户就有该菜单下的所有操作权限）
            		//比如菜单http://www.example.com/photo，该菜单用于进入相片管理界面。
            		//而相片管理界面里可以再次点击链接http://www.example.com/photo/2010/02/22/{topic}，method=get，表示显示2010年2月22日的某个主题的照片。
            		//为了区分菜单url和具体功能的url，我们通过判断菜单是否为访问url的前缀来确定用户是否有权限操作该功能，因为restful风格的url能够体现层级关系
            		if(url==null||"".equals(url)){
            			continue;
            		}
            		if(requestUrl.indexOf(url)==0){
            			return;
            		}
            	}
            }
            /*
            for(GrantedAuthority ga : authentication.getAuthorities()) {    
                if(needPermission.equals(ga.getAuthority())) {    
                    return;    
                }    
            } */ 
        }    
        //没有权限    
        throw new AccessDeniedException(" 没有权限访问！ ");   
	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
