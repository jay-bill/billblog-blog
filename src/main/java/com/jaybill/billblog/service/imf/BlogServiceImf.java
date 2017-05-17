package com.jaybill.billblog.service.imf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaybill.billblog.dao.BlogMapper;
import com.jaybill.billblog.pojo.Blog;
import com.jaybill.billblog.service.BlogService;
import com.jaybill.billblog.utils.image.ImageUtil;
import com.jaybill.billblog.utils.string.BlogHtmlStringUtil;

@Service
public class BlogServiceImf implements BlogService {

	@Autowired(required=false)
	private ImageUtil imUtil;
	@Autowired
	private BlogHtmlStringUtil blogUtil;
	@Autowired
	BlogMapper blogMapper;
		
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

}
