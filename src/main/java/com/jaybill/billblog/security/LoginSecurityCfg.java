package com.jaybill.billblog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.jaybill.billblog.service.imf.UserService;

@Configuration
//@EnableWebSecurity: 禁用Boot的默认Security配置，配合@Configuration启用自定义配置
//（需要扩展WebSecurityConfigurerAdapter）
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true): 启用Security注解，
//例如最常用的@PreAuthorize
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LoginSecurityCfg extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserService userService;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userService);
    }
	//configure(HttpSecurity): Request层面的配置
    //定义URL路径应该受到保护，哪些不应该
	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
    	//允许访问的路径
    	.antMatchers("/**/*.html","/**/*.js","/**/*.css",
    			"/**/*.woff2","/**/*.woff","/**/*.jpg","/**/*.jpeg",
    			"/**/*.png","/**/*.tff",
    			"/","/showAll*")
    	.permitAll()
        .anyRequest().authenticated() //任何请求,登录后可以访问
        .and()
        .formLogin()
        .usernameParameter("userAccount")
        .passwordParameter("userPassword")
        .loginPage("/login")
        .failureUrl("/login?error")
        .permitAll() //登录页面用户任意访问
        .and()
        .logout().permitAll(); //注销行为任意访问
    }
}
