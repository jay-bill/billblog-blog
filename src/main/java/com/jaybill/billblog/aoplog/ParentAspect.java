package com.jaybill.billblog.aoplog;

import org.aspectj.lang.JoinPoint;

public abstract class ParentAspect {
	
	abstract void invokeBefore(JoinPoint point);
	
	abstract void invokeAfter(JoinPoint point);
	
	 /**
     * 获取被代理对象的真实类全名
     * @param point 连接点对象
     * @return 类全名
     */
    String getRealClassName(JoinPoint point) {
        return point.getTarget().getClass().getName();
    }

    /**
     * 获取代理执行的方法名
     * @param point 连接点对象
     * @return 调用方法名
     */
    String getMethodName(JoinPoint point) {
        return point.getSignature().getName();
    }
}
