package com.jaybill.billblog.service.imf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaybill.billblog.dao.BlogMapper;
import com.jaybill.billblog.pojo.Blog;
import com.jaybill.billblog.service.BlogService;

@Service
public class BlogServiceImf implements BlogService {

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

}
