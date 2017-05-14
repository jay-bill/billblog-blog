package com.jaybill.billblog.service;

import java.util.List;

import com.jaybill.billblog.pojo.Blog;

/**
 * 博文服务
 * @author jaybill
 *
 */
public interface BlogService {
	
	/**
	 * 随机获取博客
	 * @param sum
	 * @return
	 */
	List<Blog> getRandomBlogs(int sum);

	/**
	 * 获取blog的详细信息
	 * @param blogId
	 * @return
	 */
	Blog getOneBlog(long blogId);

	/**
	 * 新增博客
	 * @param blog
	 */
	void addBlog(Blog blog);
	
//	List<Blog> getUserBlogs(long userId);
	
}

