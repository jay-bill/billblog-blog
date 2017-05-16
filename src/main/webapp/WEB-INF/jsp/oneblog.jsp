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
    <li><a href="#">联系我们</a></li>
    <li><a href="#">登录</a></li>
   </ul>
 </nav>
 </header>
 <div class="mainContent">
    <aside>
      <div class="avatar">      
        <img alt="" src="http://localhost:8080${user_info.userHeadimage}" style="width:160px;height:160px;">
      </div>
      <section class="topspaceinfo">
        <h1>${user_info.userNickname}</h1>
        <p>${all_info.userSignature}</p>
      </section>
      <div class="userinfo"> 
        <p class="q-fans">粉丝：<a href="#">${fans_sum}</a></p> 
        <p class="btns"><a href="#" >留言</a></p>   
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
    <div class="blogitem" >
  		 <article>
        <h2 class="title"><a href="#">${oneblog.blogTitle}</a></h2>
        <ul class="text">
          ${oneblog.blogContent}
        </ul>
      </article>
    </div>
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