package com.sapGarden.system.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sapGarden.system.org.model.User;

/**
 * 因为@AfterThrowing有些异常不能捕获，比如与其它的通知的优先级问题以及设置需要捕获的路径等问题，所以不使用aop方法来捕获异常。使用自定义HandlerExceptionResolver
 * @author Administrator
 *
 */
//@Aspect
//@Order(1)
//@Component
public class CommonLog {

	//private static final transient Logger log = LoggerFactory.getLogger(CommonLog.class);
	//Logger errorLogger = Logger.getLogger("errorLog");
//	@Around(value="execution(* com.sapGarden..*.*(..)) and !execution(* com.sapGarden.system.log..*.*(..)) and !execution(* com.sapGarden.system.security..*.*(..))")
//	public Object afterThrowingAdvice(ProceedingJoinPoint pjp) {
//		 Object retVal = null;
//		try {
//			retVal = pjp.proceed();
//        } catch (Throwable e) {
//        	log.error(pjp.toString(),e);
//        }
//        return retVal;
	//@AfterThrowing(value="execution(* com.sapGarden..*.*(..)) and !execution(* com.sapGarden.system.log..*.*(..)) and !execution(* com.sapGarden.system.security..*.*(..))",argNames= "exception",throwing = "exception")
	public void afterThrowingAdvice(Throwable  exception) {
		/*
		 * System.out.println("出现异常:" + exception.getMessage());
		 * System.out.println(exception.getClass().getName());
		 * System.out.println("异常所在类:" + jp.getTarget().getClass().getName());
		 * System.out.println("" + jp.getSignature().getName()+ "方法 throw
		 * exception");
		 */
		// User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		User user = null;
//		String className = "";//jp.getClass().getName();
//		if(user!=null){
//			log.error(user.getUsername()+className,exception);
//		}else{
//			log.error(className, exception);
//		}
	}
	/*在使用spring框架时,通常用它的aop来记录日志,但在spring mvc采用@Controller注解时,对Controller进行Aop拦截不起作用,原因是该注解的Controller已被spring容器内部代理了.
	 * 可以用HandlerInterceptorAdapter拦截器拦截所有请求。
	//在使用spring mvc时,通常用它的aop来记录日志（或者拦截其它的操作）,但在spring mvc采用@Controller注解时,对Controller进行Aop拦截不起作用,原因是该注解的Controller已被spring容器内部代理了. 
	@Around(value="execution(* org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter.handle(..))")public void beforeAdvice(ProceedingJoinPoint pjp){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null){
			accessLogger.info("aaa");
		}else{
			accessLogger.info("aaa");
		}
	}*/
}
