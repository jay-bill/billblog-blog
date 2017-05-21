package com.jaybill.billblog.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
	/**
	 * 解析博客内容，解码博客里面的图片，上传到fastdfs，替换原有的图片路径
	 * @param blog
	 * @return
	 */
	String parseBlog(String content);

	/**
	 * 页面静态化，并返回新路径
	 * @param map
	 * @param request
	 * @return
	 * @throws Exception
	 */
	String staticBlog(Map<String, Object> map,HttpServletRequest request) throws Exception;
	
}

