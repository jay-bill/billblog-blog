﻿<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!Doctype html>
<html>
<head>
<meta charset="utf-8">
<title>浴火社区-博客编辑</title>
<link rel="stylesheet" href="assets/design/css/trumbowyg.css">
<script src="assets/jquery.min.js"></script>
<script src="assets/trumbowyg.js"></script>
<script src="assets/plugins/base64/trumbowyg.base64.js"></script>

</head>

<body>
<div style="margin:10px 25px 0px 25px;">
	<input id="titleDiv" type="text" placeholder="请输入标题......" style="height:40px;font-size:30px;color:#666;">
</div>
<div id="odiv" style="display:none;position:absolute;z-index:100;">
    <img src="assets/pic/sx.png" title="缩小" border="0" alt="缩小" onclick="sub(-1);"/>
    <img src="assets/pic/fd.png" title="放大" border="0" alt="放大" onclick="sub(1)"/>
    <img src="assets/pic/cz.png" title="重置" border="0" alt="重置" onclick="sub(0)"/>
    <img src="assets/pic/sc.png" title="删除" border="0" alt="删除" onclick="del();odiv.style.display='none';"/>
</div>
<div id="customized-buttonpane" class="editor" onmousedown="show_element(event)" style="clear:both">
	
</div>
<form id="submitForm" action="/submitBlog" method="post" style="display:none;">
	<input id="blogTitle" type="hidden" name="blogTitle">
	<input id="blogContent" type="hidden" name="blogContent">
	 <input type="hidden" name="${_csrf.parameterName }" 
			                        value="${_csrf.token }">
</form>
<button onclick="submit()" style="margin-left:25px;">提交</button>
</body>
<script type="text/javascript">
	function submit(){
		var content=$("#customized-buttonpane").html();
		var title = $("#titleDiv").val();
		$("#blogTitle").val(title);
		$("#blogContent").val(content);
		$("#submitForm").submit();
	}
</script>
</html>
