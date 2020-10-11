<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
			<img id= "imageId" src="./images/indexBGI.jpg" />
	  </div>
  </body>
</html>
