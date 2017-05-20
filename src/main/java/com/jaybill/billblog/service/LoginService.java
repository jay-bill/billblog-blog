package com.jaybill.billblog.service;

import com.jaybill.billblog.pojo.User;

public interface LoginService {
	
	/**
	 * 根据账号密码判断用户能否登录
	 * @param user
	 * @return
	 */
	User login(User user);
	
	User signIn(String account,String pword);
	
	User selectByAccount(String account);
}
