<!DOCTYPE> 
<html> 
<head> 
　　<title>演示</title> 
　　<meta http-equiv="pragma" content="no-cache"> 
　　<meta http-equiv="cache-control" content="no-cache"> 
　　<meta http-equiv="expires" content="0"> 
　　<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"> 
　　<meta http-equiv="description" content="This is my page"> 
　　<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
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
	        <img alt="" style="width:160px;height:160px;">
	      </div>
	      <section class="topspaceinfo">
	        <h1></h1>
	        <p></p>
	      </section>
	      <div class="userinfo"> 
	        <p class="q-fans">粉丝：<a href="#"></a></p> 
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
	     <img src="" class="footphoto">
	     <ul class="footinfo">
	       <p class="fname"><a href="" >浴火社区</a>  </p>
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