package com.sapGarden.system.security;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapGarden.application.commons.dao.ExtendDao;
import com.sapGarden.system.org.model.User;


public class CustomUserDetailsService implements UserDetailsService {


	Logger logger = Logger.getLogger("logError");
	@Autowired
	private ExtendDao extendDao;


	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
		UserDetails user = null;
		try {
			/*
			final String ql = "from User as u  inner join fetch u.roles where u.username = :username";
			//EntityManager em = EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerFactory);
			EntityManager em = entityManagerFactory.createEntityManager();
			Query query = em.createQuery(ql).setParameter("username", username);
			user = (UserDetails)query.getSingleResult();
			em.close();
			*/
			//执行查询，如果是懒加载，则不会查出roles
			user = (User)extendDao.findSingleResult("from User t where t.username=?", new Object[]{username});
			//user = userDao.findUserByUsername(username);
		}catch (DataAccessResourceFailureException e) {
			throw new UsernameNotFoundException(e.getMessage(), e);
		}catch (NoResultException e) {
			throw new UsernameNotFoundException(e.getMessage(), e);
		}catch (Exception e) {
			throw new UsernameNotFoundException(e.getMessage(), e);
		}
		return user;
	}
	/*
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
		UserDetails user = null;
		try {
			boolean enables = true;    
			boolean accountNonExpired = true;    
			boolean credentialsNonExpired = true;    
			boolean accountNonLocked = true;   
			final String ql = "from User as u  where u.userId = :userId";
			EntityManager em = EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerFactory);
			Query query = em.createQuery(ql).setParameter("userId", username);
			com.sapGarden.system.login.model.User userModel = (com.sapGarden.system.login.model.User)query.getSingleResult();
			if(userModel!=null){
				Collection<GrantedAuthority> grantedAuths = this.obtionGrantedAuthorities(userModel);
				user = new User(userModel.getUserId(),userModel.getPassword(),enables, accountNonExpired, credentialsNonExpired, accountNonLocked,grantedAuths);
				
			}
		} catch (DataAccessResourceFailureException e) {
			e.printStackTrace();
		}
		return user;
	}

	//取得用户的权限   
	private Set<GrantedAuthority> obtionGrantedAuthorities(com.sapGarden.system.login.model.User userModel) {    
	    Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();    
	    try {
			Set<Role> roles = userModel.getRoles();
		   if(roles!=null&&roles.size()>0){
				   for(Role role : roles){
					   Set<Resource> resources = role.getResources();
					   for(Resource resource : resources){
						   authSet.add(new GrantedAuthorityImpl(resource.getId()+""));  
					   }
				   }
		   }
		   if(userModel.getUserId().equals("admin")){
					   authSet.add(new GrantedAuthorityImpl("/common/console"));
		   }
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	    return authSet;    
	}
	*/
}
