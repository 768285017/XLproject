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
  <meta charset="utf-8"/>  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="./css/userManage.css">
	

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
			<div id="bodyHeader" > 
				<input id="userNameInput" class="searchInput" type="text" placeholder="输入用户名"/>
				<input id="cName" class="searchInput" type="text" placeholder="输入姓名"/>
				<input id="email" class="searchInput" type="text" placeholder="输入邮箱地址"/>
				<input id="provinceName" class="searchInput" type="text" placeholder="输入省份"/>
				<button style="background-color: blue; margin-left: 80px;" class="searchButton">查看</button>
				<button style="background-color: orange; " class="searchButton">清空</button>
				<button style="background-color: cadetblue; " class="searchButton">增加</button>
				<button style="background-color: yellow " class="searchButton">删除</button>
				<button style="background-color: cornflowerblue; " class="searchButton">修改</button>
			</div>
			<div id="bodyBody">
				<table border="1"  cellpadding="2" cellspacing="0" >
				    <tr>
				        <td style="width: 50px;text-align: center;"><input type="checkbox" onclick="clickAll()"/></td>
				        <td style="width: 210px;text-align: center;">用户名</td>
				        <td style="width: 160px;text-align: center;">中文名</td>
				        <td style="width: 260px;text-align: center;">邮箱</td>
				        <td style="width: 150px;text-align: center;">省份</td>
				        <td style="width: 150px;text-align: center;">城市</td>
				        <td style="width: 180px;text-align: center;">操作</td>
				    </tr>
				    
				</table>
			</div>
			<div id="bodyFoot">
				<span id="pageLeft">
					每页<select id="pageNumber">
					<option >5</option>
					<option >10</option>
					<option >20</option>
					<option >50</option>
					</select>,
					共20条数据, 1页/2页
				</span>
				<span id="pageRight">
					<a href="" class="pageClick" id="first">首页</a>
					<a href="" class="pageClick" id="back">上一页</a>
					<a href="" class="pageClick" id="next">下一页</a>
					<a href="" class="pageClick" id="last">尾页</a>
				</span>
			</div>
	  </div>
	  <div id="registerForm">
        	<p style="font-size: 20px;">账号注册：</p>
            <form action="http://localhost:8080/myproject/register" method="post">
            	
                <p><input id="userName" name="userName" type="text" placeholder="用户名" onblur="leaveNameInput()"/></p>
                <p id="leaveNameInfo" style="display: none;color: red;size: 5pxs;" ></p>
                <p><input id="chrName" name="chrName" type="text" placeholder="真实用户名" onblur="leavechrNameInput()" style="height: 25px;width: 280px;"/></p>
                <p id="leavechrNameInfo" style="display: none;color: red;size: 5pxs;" ></p>
                <p><input id="password" name="password" type="password" placeholder="密码" onblur="leavePasswordInput()"/></p>
                 <p id="leavePasswordInfo" style="display: none;color: red;size: 5pxs;" ></p>
                <p><input id="email" type="text" name="email" placeholder="邮箱" style="width: 280px;height: 25px;" onblur="leaveEmailInput()"/></p>
                <p id="leaveEmailInfo" style="display: none;color: red;size: 5pxs;" ></p>
                <p> <select style="height: 25px;width: 280px;" name="pId" id="pId" onchange="provinceFun()">
                	<option >请选择省份</option>
                	<option value="1">湖北</option>
				    <option value="2">湖南</option>
				    <option value="3">安徽</option>
                </select></p>
                <p> <select style="height: 25px;width: 280px;" name="cId" id="cId">
                	<option >请选择城市</option>
                </select></p>
           </p>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="setNotLogin" id="setNotLogin" value="yes"/>是否同意七天免登录
            <p id="ajaxLoginInfo" style="display: none;color: red;size: 5pxs;" ></p>
            <p>
            <input id="btLogin" type="submit" value="登&nbsp;&nbsp;陆" /> </p>
            </form>
        </div>
  </body>
</html>
