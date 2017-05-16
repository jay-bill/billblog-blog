package com.jaybill.billblog.service;

import java.util.List;

import com.jaybill.billblog.pojo.User;
import com.jaybill.billblog.pojo.Userinfo;

public interface CommonService {
	/**
	 * 获取用户的账号信息，即USER表
	 * @param userAccount
	 * @return
	 */
	User getUserBaseInfo(String userAccount);
	User getUserBaseInfoById(long userId);
	/**
	 * 获取用户的详细信息
	 * @param userAccount
	 * @return
	 */
	Userinfo getUserInfo(long userId);
	
	/**
	 * 计算粉丝的数目
	 */
	int getFansSum(long userId);
	
	/**
	 * 计算关注的人的数目
	 */
	int getBeNoticeSum(long userId);
	
	/**
	 * 计算微博的总数
	 */
	int getWeiboSum(long userId);
	
	/**
	 * 更新用户的详细信息
	 */
	void updateUserInfo(User user,Userinfo userInfo);
	
	/**
	 * 获取某个用户的图片的所有分组的图片数目
	 * @param userId
	 * @return
	 */
	 List<Integer> getDefaultImageGroupSum(long userId);
	 
	/**
	 * 获取未读的通知数目
	 * @param beinfoId
	 * @return
	 */
	public int selectSumNoRead(long beinfoId);
	
	
	/**
	 * 已读后，将通知状态改为1
	 * @param beinfoId
	 * @return
	 */
	public int updateInfoState(long beinfoId);
	
	/**
	 * 计算微博被转发的次数
	 * @param weiboId
	 * @return
	 */
	public int countForwardSum(long weiboId);
	
	/**
	 * 获取用户总数
	 * @return
	 */
	long selectUsersSum();
}
