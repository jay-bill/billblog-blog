package com.jaybill.billblog.service.imf;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaybill.billblog.dao.BlogMapper;
import com.jaybill.billblog.pojo.Blog;
import com.jaybill.billblog.service.BlogService;
import com.jaybill.billblog.utils.file.FileUtil;
import com.jaybill.billblog.utils.image.ImageUtil;
import com.jaybill.billblog.utils.string.BlogHtmlStringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@Service
public class BlogServiceImf implements BlogService{

	@Autowired
	ImageUtil autoImgUtil;
	@Autowired
	private BlogHtmlStringUtil blogUtil;
	@Autowired
	BlogMapper blogMapper;
	@Autowired
	Configuration config;
	@Autowired
	FileUtil fileUtil;
	//线程池
	ExecutorService exe = null;
	CompletionService<String> cps = null;
	public BlogServiceImf(){
		exe =  Executors.newCachedThreadPool();
		cps = new ExecutorCompletionService<String>(exe);
	}
		
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

	/**
	 * 提取博客里面的图片，并开启多线程上传到fastdfs；
	 * 然后获得返回的路径集合；因为返回的路径顺序是随机的，为了正确替换blog里面的图片编码串，
	 * 需要为ImgUtil返回的字符串加上递增消息头，再排序。
	 * 这样就可以对应于博客的图片位置了。
	 */
	@Override
	public String parseBlog(String content) {
		//获取博客里面的图片
		List<String> foundTags = blogUtil.parseContent(content, "<img>");
		//存放随机返回的路径结果
		List<String> resPath = new ArrayList<String>();
		System.out.println(foundTags.size());
		for(int i=0;i<foundTags.size();i++){
			String img = foundTags.get(i);
			ImageUtil imUtil = new ImageUtil(img,i+"",autoImgUtil.getStorageClient());
			cps.submit(imUtil);//提交线程任务到线程池			
		}		
		//等待线程执行完毕，记录返回的路径
		for(int i=0;i<foundTags.size();i++){			
			try {
				Future<String> test = cps.take();
				System.out.println(test);
				String storePath = test.get();
				resPath.add(storePath);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}		
		}
		Collections.sort(resPath);//字典顺序排序
		System.out.println(resPath.size()+"要炸");
		for(int i=0;i<resPath.size();i++){
			String tmp = resPath.get(i).split("@")[1];
			blogUtil.replaceTagVal(content, foundTags.get(i), tmp);
		}
		//返回图片解码后的博客
		return content;
	}
	
	
	/**
	 * 页面静态化
	 */
	@Override
	public String staticBlog(Map<String, Object> map,HttpServletRequest request) throws Exception {
		//静态化
		config.setDefaultEncoding("UTF-8");//编码  
		config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		Template temple=config.getTemplate("template.ftl");//获取模板  
		StringWriter out = new StringWriter();//生成最终页面并写到文件  
		try {  
		    temple.process(map, out);//处理  
		    byte [] bt = out.toString().getBytes();
		    return fileUtil.upload(bt, "html");//上传
		} catch (TemplateException e) {  
		    e.printStackTrace();  
		}finally{  
		    out.close();  
		}
		return null;  
	}

}
