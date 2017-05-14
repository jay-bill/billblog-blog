package com.jaybill.billblog.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jaybill.billblog.config.Url;
import com.jaybill.billblog.pojo.Blog;
import com.jaybill.billblog.service.BlogService;
import com.jaybill.billblog.service.CommonService;
import com.jaybill.billblog.utils.ImageUtil;

/**
 * 博客控制器
 * @author jaybill
 *
 */
@Controller
@RequestMapping({"/","blogcontroller"})
public class BlogController {
	
	@Autowired
	private BlogService blog;
	@Autowired
	private CommonService common;
	@Autowired
	private ImageUtil imUtil;
	/**
	 * 随机选取博客
	 * @param sum 默认10篇
	 */
	@RequestMapping(value={"","showAllBlogs","showAllBl*"},method=RequestMethod.GET)
	public String showAllBlogs(@RequestParam(value="sum",defaultValue="10") int sum
			,Map<String,Object> map){
		//随机获取博文
		List<Blog> list = blog.getRandomBlogs(sum);
		//调用远程rpc获取用户总数
		long userSum = 0;
		try{
			userSum = common.selectUsersSum();
		}catch(Exception e){
			e.printStackTrace();
		}
		map.put("user_sum", userSum);
		map.put("blog_list", list);
		return "index";
	}
	
	/**
	 * 重定向到微博首页
	 * @return
	 */
	@RequestMapping(value="toweibomain.do")
	public String redirectToWeiboMain(){
		return "redirect:"+Url.weiboMainUrl;
	}
	
	@RequestMapping("showmyblogs")
	public String showMyBlogs(long userId,HttpServletRequest request){
		
		return null;
	}
	
	@RequestMapping(value="submitBlog",method=RequestMethod.POST)
	@ResponseBody
	public Blog submitBlog(Blog blog) throws IOException{		
		String storePath = imUtil.uploadToFastDFS(blog.getBlogContent());
		System.out.println(storePath);
		return blog;
	}
}
