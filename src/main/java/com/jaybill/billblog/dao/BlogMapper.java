package com.jaybill.billblog.dao;

import org.apache.ibatis.annotations.Mapper;

import com.jaybill.billblog.pojo.Blog;

@Mapper
public interface BlogMapper {
	/**
	 * 随机获取
	 * @param sum
	 * @return
	 */
	Blog selectByRandom(int sum);

	/**
	 * 获取一条
	 * @param blogId
	 * @return
	 */
	Blog selectOneById(long blogId);

	/**
	 * 新增一个
	 * @param blog
	 */
	void insertOneBlog(Blog blog);
}