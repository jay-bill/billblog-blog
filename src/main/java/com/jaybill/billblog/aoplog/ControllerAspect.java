package com.jaybill.billblog.aoplog;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 *controller包的日志切面
 * @author jaybill
 *
 */
@Aspect
@Configuration
public class ControllerAspect extends ParentAspect{
	private static final Logger log = LoggerFactory.getLogger(ControllerAspect.class);
	
	//切点
	@Pointcut("execution(* com.jaybill.billblog.controller.*.*(..))")
	public void executeController(){}
	
	@Before("execution(* com.jaybill.billblog.controller.*.*(..))")
	public void invokeBefore(JoinPoint point) {
	    String realClassName = getRealClassName(point);
	    log.debug("调用-----"+ realClassName + " 执行 " + getMethodName(point) + " 方法之前");
	}

    @After("execution(* com.jaybill.billblog.controller.*.*(..))")
    public void invokeAfter(JoinPoint point) {
        String realClassName = getRealClassName(point);
        log.debug("调用-----"+ realClassName + " 执行 " + getMethodName(point) + " 方法之后");
    }      
}
