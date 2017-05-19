package com.jaybill.billblog.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.jaybill.billblog.config.Url;
import com.jaybill.billblog.pojo.Blog;
import com.jaybill.billblog.pojo.User;
import com.jaybill.billblog.service.BlogService;
import com.jaybill.billblog.service.CommonService;
import com.jaybill.billblog.service.LoginService;
import com.jaybill.billblog.utils.image.ImageUtil;
import com.jaybill.billblog.utils.string.BlogHtmlStringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * 博客控制器
 * @author jaybill
 *
 */
@Controller
@RequestMapping({"/","blogcontroller"})
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private CommonService common;
	@Autowired
	private LoginService login;	
	/**
	 * 随机选取博客
	 * @param sum 默认10篇
	 */
	@RequestMapping(value={"","showAllBlogs","showAllBl*"},method=RequestMethod.GET)
	public String showAllBlogs(@RequestParam(value="sum",defaultValue="10") int sum
			,Map<String,Object> map){
		//随机获取博文
		List<Blog> list = blogService.getRandomBlogs(sum);
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
	
	/**
	 * 发表博客
	 * @param blog
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="submitBlog",method=RequestMethod.POST)
	public String submitBlog(Blog blog,HttpServletRequest request,Map<String,Object> map) throws IOException{	
		//对博客内容进行解析，并上传图片
		String content = blogService.parseBlog(blog.getBlogContent());
		//构造blog对象
		Blog newBlog = new Blog(blog.getBlogTitle(),new Timestamp(new Date().getTime()),
				content,(long)9);		
		//静态化
		map.put("oneblog", blog);
		//静态化
		try {
			newBlog.setBlogHref(blogService.staticBlog(map,request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//插入数据库
		blogService.addBlog(newBlog);
		//共享到session
		request.getSession().setAttribute("tmp_blog", newBlog);
		System.out.println(newBlog.getBlogHref());
		if(newBlog.getBlogHref()!=null){
			return "redirect:"+newBlog.getBlogHref();
		}
		return "redirect:showOneBlog?blogId=-1";
	}
	
	/**
	 * 阅读博客全文
	 * @param blogId
	 * @param map
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="showOneBlog")
	public String showOneBlog(long blogId,Map<String,Object> map
			,HttpServletRequest request){
		Object obj = request.getSession().getAttribute("tmp_blog");
		Blog blog = null;
		if(obj!=null){
			blog = (Blog)obj;		
		}else{
			blog = blogService.getOneBlog(blogId);
		}
		map.put("oneblog", blog);
		//静态化
		try {
			blogService.staticBlog(map,request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//除移
		request.getSession().removeAttribute("tmp_blog");
		//获取博客作者的信息
		long userId = blog.getUserId();
		//rpc
		try{
			map.put("user_info", common.getUserBaseInfoById(userId));
			map.put("all_info",common.getUserInfo(userId));
			map.put("fans_sum", common.getFansSum(userId));
		}catch(Exception e){
			e.printStackTrace();
		}		
		return "oneblog";
	}
	
	@RequestMapping(value="signin",method=RequestMethod.POST)
	public String login(String userAccount,String userPassword){		
		User u = login.signIn(userAccount,userPassword);
		if(u!=null)
			return "redirect:showAllBlogs";
		else
			return "redirect:/login.html";
	}
}
