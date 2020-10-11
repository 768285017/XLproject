<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'error.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="./css/error.css">
	

  </head>
  
  <body onload="outTime()">
	<img style="margin-top: 120px; margin-left: 100px;" src="./images/errorLogo.jpg">
	<div id="errorInfo">
		<h2><p style="color: red;">${info} </p></h2>
		<h2><span id="txt"></span>秒后自动返回登录页面</h2>
		<h2><a href="http://localhost:8080/myproject/login.html" style="color: red;">点击跳转</a></h2>
	</div>  
	
  </body>
  <script type="text/javascript">
	  var c = 10;
	  function outTime(){
		document.getElementById("txt").innerHTML = c;
	  	if(c === 0){
			window.location = "login.html"
		}else{
			c--;		
			setTimeout('outTime()',1000);
		}
	  }
  </script>
</html>
