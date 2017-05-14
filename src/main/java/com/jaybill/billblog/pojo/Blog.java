package com.jaybill.billblog.pojo;

import java.util.Date;

public class Blog {
    private Long blogId;

    private Long userId;

    private String blogTitle;

    private Date blogDate;

    private Integer likeSum;

    private Integer commentSum;

    private Integer forwardSum;

    private Integer collectSum;

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle == null ? null : blogTitle.trim();
    }

    public Date getBlogDate() {
        return blogDate;
    }

    public void setBlogDate(Date blogDate) {
        this.blogDate = blogDate;
    }

    public Integer getLikeSum() {
        return likeSum;
    }

    public void setLikeSum(Integer likeSum) {
        this.likeSum = likeSum;
    }

    public Integer getCommentSum() {
        return commentSum;
    }

    public void setCommentSum(Integer commentSum) {
        this.commentSum = commentSum;
    }

    public Integer getForwardSum() {
        return forwardSum;
    }

    public void setForwardSum(Integer forwardSum) {
        this.forwardSum = forwardSum;
    }

    public Integer getCollectSum() {
        return collectSum;
    }

    public void setCollectSum(Integer collectSum) {
        this.collectSum = collectSum;
    }
    
    private String blogContent;

    private String blogImage;

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent == null ? null : blogContent.trim();
    }

    public String getBlogImage() {
        return blogImage;
    }

    public void setBlogImage(String blogImage) {
        this.blogImage = blogImage == null ? null : blogImage.trim();
    }
}