package com.jaybill.billblog.dao;

import org.apache.ibatis.annotations.Mapper;

import com.jaybill.billblog.pojo.Blog;

@Mapper
public interface BlogMapper {
	Blog selectByRandom(int sum);
}