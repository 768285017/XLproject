<%@ page language="java" import="java.util.*"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="./css/header.css">
	<link rel="stylesheet" type="text/css" href="./css/downloadDiv.css">
	

  </head>
  
  <body>
	<div id="header">
		<img id="logo" src="./images/logo.png">
		<div id="rightHeader">
			<span class="userTab">欢迎你：${user.name} 【安全退出】</span>	<br><br>
			<span class="tab">首页</span>|
			<span class="tab"><a href="/myproject/queryResource?roleId=1">资源下载</a></span>|
			<span class="tab"><a href="/myproject/queryResource?roleId=2">用户管理</a></span>|
			<span class="tab"> <a href="/myproject/queryResource?roleId=3">资源管理</a></span>|
			<span class="tab"><a href="/myproject/queryResource?roleId=4">个人中心</a></span>
		</div>
		
	  </div>
	  <hr id="hrId"/>
	  <div id="body" >
		<c:forEach items="${allDownloadList}" var="downloadList" >
		<div id="downloadDiv">
			<p>${downloadList.name}</p>
			<span><img class="fileLogo" src=${downloadList.image}> </span>
			<a class="downloadResource" href="/myproject/downloadResource?fId="+${downloadList.id}>下载</a>
			<div id="fileContent">
				<p>${downloadList.description}</p>
				<span>时间:${downloadList.time}</span>
				<span>&nbsp;&nbsp;大小:${downloadList.size}M</span>
				<span>&nbsp;&nbsp;星级:<div class="starClass"></div></span>		
			</div>
			
		</div>
		</c:forEach>

		<!-- <div id="downloadDiv">
			<p>《Java应用开发》教案（word版）</p>
			<span><img class="fileLogo" src="./images/doc.png"/></span>
			<span><a class="downloadResource" rel="">下载</a></span>
			<div id="fileContent">
				<p>编程王国的强者-Java，教案由聂老师倾心撰写，图文并茂，内容翔实，引导初学者一步步步入Java精彩大世界</p>
				<span>时间:2220-231-43</span>
				<span>&nbsp;&nbsp;大小:555</span>
				<span>&nbsp;&nbsp;星际:3434</span>		
			</div>
			
		</div> -->
	  </div>
  </body>
</html>
