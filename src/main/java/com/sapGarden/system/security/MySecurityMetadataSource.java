package com.sapGarden.system.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.sapGarden.system.org.model.Resource;
import com.sapGarden.system.org.service.ResourceService;




public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource{

	
	
	//private EntityManagerFactory entityManagerFactory;
	private final String[]  SYS_RESOURCE = {"/common/front"};
	private ResourceService resourceService;
	
	
	
	
//	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
//		this.entityManagerFactory = entityManagerFactory;
//	}
	/**
	 * 因为系统启动时就会执行loadResourceDefine，放到静态的resourceMap中，这样每次请求过来时调用getAttributes，不需要重新去数据库中加载所有资源。
	 * 但是如果管理员修改了一个资源的权限，不做另外的操作只改数据库中的数据时起不到效果的，所以此时，我们需要人为的调用loadResourceDefine方法，最好做个类似
	 * 于“刷新缓存”的功能来重新加载资源。
	 */
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null; 

	@Autowired
	public MySecurityMetadataSource(ResourceService resourceService) {    
		this.resourceService = resourceService;  
        loadResourceDefine();    
    }    
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)throws IllegalArgumentException {
		 String requestUrl = ((FilterInvocation) object).getRequestUrl();    
	     if(resourceMap == null) {    
	    	 loadResourceDefine();
	     }    
	     //AbstractSecurityInterceptor的源码发现,如果在对应的FilterInvocationSecurityMetadataSource 中的getAttributes(Object object)方法返回null的话，AccessDecisionManager就不会起作用了
	     /*
	     if(resourceMap.get(requestUrl)==null){
	    	 Collection<ConfigAttribute> returnCollection = new ArrayList<ConfigAttribute>(); 
	    	 returnCollection.add(new SecurityConfig("ROLE_NO_USER")); 
	    	 return returnCollection;
	     }*/
	     return resourceMap.get(requestUrl);  
	}

	//加载所有资源与权限的关系    
    private void loadResourceDefine() {       
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>(); 
          //因为本方法是在平台起动时就调用执行，EntityManagerFactory还未生成，所以手动调用
            /*
            String[] locations = { "classpath:applicationContext.xml","classpath:spring-jpa.xml" };
            ApplicationContext context = new ClassPathXmlApplicationContext(locations);
            EntityManagerFactory entityManagerFactory= (EntityManagerFactory)context.getBean("entityManagerFactory");
            */
            //只加载叶子节点的Resource
            /*
            String ql = "from Resource";
            EntityManager em = entityManagerFactory.createEntityManager();
    	    //EntityManager em = EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerFactory);
            Query query = em.createQuery(ql);
    	    List<Resource> resources = query.getResultList();
            em.close();
            */
            List<Resource> resources = resourceService.findAllResource();
            /*
    	    ql="select t.TABLE_NAME,t.COLUMN_NAME,t.DATA_TYPE from USER_TAB_COLS t where t.TABLE_NAME='SYS_ORG_ROLE'";
    	    Query query = em.createNativeQuery(ql);
    	    List<TableStructure> tableStructureList = query.getResultList();
    	    */
            if(resources!=null&&resources.size()>0){
            	 for (Resource resource : resources) {    
                     Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();    
                                     //以权限名封装为Spring的security Object    
                     ConfigAttribute configAttribute = new SecurityConfig(resource.getId()+"");    
                     configAttributes.add(configAttribute); 
                     if(resource.getUrl()==null||"".equals(resource.getUrl().trim())){
                    	 resourceMap.put(resource.getId()+"", configAttributes); 
                     }else{
                    	 resourceMap.put(resource.getUrl(), configAttributes);    
                     }
                 }    
            }
            //添加一些系统菜单，只有添加在resourceMap，springsecurity才能校验，否则不校验
            if(SYS_RESOURCE!=null){
            	for(int i=0;i<SYS_RESOURCE.length;i++){
            		Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>(); 
            		ConfigAttribute configAttribute = new SecurityConfig(-i+"");    
                    configAttributes.add(configAttribute); 
                    resourceMap.put(SYS_RESOURCE[i], configAttributes);
            	}
            }
    }    
    
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
