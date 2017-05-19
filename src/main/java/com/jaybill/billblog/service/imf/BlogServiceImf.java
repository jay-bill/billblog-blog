package com.jaybill.billblog.service.imf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaybill.billblog.dao.BlogMapper;
import com.jaybill.billblog.pojo.Blog;
import com.jaybill.billblog.service.BlogService;
import com.jaybill.billblog.utils.image.ImageUtil;
import com.jaybill.billblog.utils.string.BlogHtmlStringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@Service
public class BlogServiceImf implements BlogService {

	@Autowired(required=false)
	private ImageUtil imUtil;
	@Autowired
	private BlogHtmlStringUtil blogUtil;
	@Autowired
	BlogMapper blogMapper;
	@Autowired
	Configuration config;
		
	@Override
	public List<Blog> getRandomBlogs(int sum) {
		List<Blog> list = new ArrayList<Blog>();
		for(int i=0;i<sum;i++){
			Blog b = blogMapper.selectByRandom(sum);
			if(b==null)
				break;
			if(!list.contains(b)){
				list.add(b);
			}
		}
		return list;
	}
	
	/**
	 * 阅读博客全文
	 */
	@Override
	public Blog getOneBlog(long blogId) {
		Blog blog = blogMapper.selectOneById(blogId);
		return blog;
	}

	@Override
	public void addBlog(Blog blog) {
		blogMapper.insertOneBlog(blog);
	}

	@Override
	public String parseBlog(String content) {
		//获取博客里面的图片
		List<String> foundTags = blogUtil.parseContent(content, "<img>");
		Iterator<String> it = foundTags.iterator();
		while(it.hasNext()){
			String img = it.next();
			String storePath = imUtil.uploadToFastDFS(img);
			String newImg = "<img src='"+storePath+"'/>";
			content = blogUtil.replaceTagVal(content, img, newImg);
		}
		//返回图片解码后的博客
		return content;
	}

	/**
	 * 页面静态化
	 */
	@Override
	public void staticBlog(Map<String, Object> map,HttpServletRequest request) throws Exception {
		//静态化
		config.setDefaultEncoding("UTF-8");//编码  
		config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		Template temple=config.getTemplate("template.ftl");//获取模板  
		Writer out = new OutputStreamWriter(new FileOutputStream(request.getServletContext().getRealPath("/")+"1.html"));//生成最终页面并写到文件  
		try {  
		    temple.process(map, out);//处理  
		} catch (TemplateException e) {  
		    e.printStackTrace();  
		}finally  
		{  
		    out.close();  
		}  
	}

}
