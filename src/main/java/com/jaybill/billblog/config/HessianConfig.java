package com.jaybill.billblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import com.jaybill.billblog.service.CommonService;
import com.jaybill.billblog.service.LoginService;

@Configuration
public class HessianConfig {
	@Bean
	public HessianProxyFactoryBean hessianFactory(){
		HessianProxyFactoryBean proxy = new HessianProxyFactoryBean();
		proxy.setServiceUrl(Url.hessianUrl);
		proxy.setServiceInterface(CommonService.class);
		return proxy;
	}
	
	@Bean(name="loginHessianFactory")
	public HessianProxyFactoryBean loginHessianFactory(){
		HessianProxyFactoryBean proxy = new HessianProxyFactoryBean();
		proxy.setOverloadEnabled(true);
		proxy.setServiceUrl(Url.hessianLoginUrl);
		proxy.setServiceInterface(LoginService.class);
		return proxy;
	}
}
