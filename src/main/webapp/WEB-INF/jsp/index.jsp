<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>个人博客模板</title>
<meta name="keywords" content="个人博客模板" />
<meta name="description" content="个人博客模板" />
<link href="${request.contextPath}/resource/css/blog/styles.css" rel="stylesheet">
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->
</head>
<body>
<header>
 <nav id="nav">
   <ul>
    <li><a href="/" style="background:#F16E50">博客首页</a></li>
    <li><a href="/billblog-blog/weibocontroller/tomainpage.do">微博首页</a></li>
    <li><a href="#">相册首页</a></li>
    <li><a href="#">我的微博</a></li>
    <li><a href="#">我的博客</a></li>
    <li><a href="#">随便看看</a></li>
    <li><a href="#">分享专区</a></li>
    <li><a href="#">关于我们</a></li>       
    <li><a href="/writeBlog">写博客</a></li>
    <li><a href="#">登录</a></li>
   </ul>
 </nav>
 </header>
 <div class="mainContent">
    <aside>
      <div class="avatar">
        <a href="#" style=" background: url(${request.contextPath }/resource/image/photo.jpg) no-repeat; background-size: 160px 160px"><span>浴火社区</span></a>
      </div>
      <section class="topspaceinfo">
        <h1>浴火社区</h1>
        <p>凤凰涅槃，浴火重生；受过磨难，才能展翅飞翔</p>
      </section>
      <div class="userinfo"> 
        <p class="q-fans">用户：<a href="#">${user_sum}</a></p> 
        <p class="btns"><a href="#" >给站长留言</a></p>   
      </div>      
      <section class="taglist">
         <h2>社区标签</h2>
         <ul>
           <li>
           	<a>青春</a>
           </li>
           <li>
           	<a>励志</a>
           </li>
           <li>
            <a>奋斗</a>
           </li>
         </ul>
      </section>
    </aside>
    <div class="blogitem">
    	<c:set var="count" value="0"></c:set>
    	<c:forEach var="blog" items="${blog_list}">
    		  <article>
		        <h2 class="title"><a href="#">${blog.blogTitle}</a></h2>
		        <ul class="text" style="height:600px;overflow:hidden;">
		          ${blog.blogContent}
		        </ul>
		        <div class="textfoot">
		          <a href="/showOneBlog?blogId=${blog.blogId}">阅读全文</a><a href="#">评论</a><a href="#">转载</a>
		        </div>
		      </article>
		      <c:set var="count" value="${count+1}"></c:set> 
    	</c:forEach>
     	<c:if test="${count>0}">
  		      <div class="pages"><span>1</span><a href="#" hidefocus="">2</a><a href="#" hidefocus="">3</a><a href="#" class="next">下一页&gt;&gt;</a></div>
     	</c:if>    	
    </div>
    <c:if test="${count eq 0}">
     	<p style="background:#E8E5DD;text-align:center;">这家伙很懒，没有留下任何博客</p>
    </c:if>
 </div>
<footer>
   <div class="footavatar">
     <img src="${request.contextPath }/resource/image/photo.jpg" class="footphoto">
     <ul class="footinfo">
       <p class="fname"><a href="/dancesmile" >浴火社区</a>  </p>
       <p class="finfo">凤凰涅槃，浴火重生</p>
   </div>
   <section class="visitor">
     <h2>最近访客</h2>
      <ul>
        
      </ul>
   </section>
   <div class="Copyright">
     <ul>
       <a href="#">帮助中心</a><a href="#">空间客服</a><a href="#">投诉中心</a><a href="#">空间协议</a>
     </ul>
     <p>Design by DanceSmile</p>
   </div>
 </footer>
</body>
</html>