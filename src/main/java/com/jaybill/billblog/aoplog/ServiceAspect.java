package com.jaybill.billblog.aoplog;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
/**
 * service包的切面
 * @author jaybill
 *
 */
@Aspect
@Configuration
public class ServiceAspect extends ParentAspect{
	
	private static final Logger log = LoggerFactory.getLogger(ServiceAspect.class);

	@Pointcut("execution(* com.jaybill.billblog.service.*.*(..))")
	public void executeService(){}

	@Override
	void invokeBefore(JoinPoint point) {
		String realClassName = getRealClassName(point);
	    log.debug("调用-----"+ realClassName + " 执行 " + getMethodName(point) + " 方法之前");
	}

	@Override
	void invokeAfter(JoinPoint point) {
		String realClassName = getRealClassName(point);
        log.debug("调用-----"+ realClassName + " 执行 " + getMethodName(point) + " 方法之后");
	}	
}
