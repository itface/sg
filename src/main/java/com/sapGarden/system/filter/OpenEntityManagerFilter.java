package com.sapGarden.system.filter;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class OpenEntityManagerFilter implements Filter {

	private static EntityManagerFactory emf;
	private static final ThreadLocal<EntityManager> tl = new ThreadLocal<EntityManager>();;
	
	public void destroy() {
		// TODO Auto-generated method stub
		if(emf!=null){
			emf.close();
			System.out.println("销毁EntityManagerFactory");
		}
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		arg2.doFilter(arg0, arg1);
		EntityManager em = tl.get();
		tl.set(null);
		if(em!=null){
			em.close();
			System.out.println("关闭EntityManager");
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		 emf = Persistence.createEntityManagerFactory("entityManagerFactory");
	}
	
	public static EntityManager getEntityManager()
	{
		EntityManager em = tl.get();
		if(em==null||!em.isOpen())
		{
			em = (emf!=null)?emf.createEntityManager():null;
			tl.set(em);
			System.out.println("打开EntityManager");
		}
		return em;
	}

}

