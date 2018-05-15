<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Welcome Page</title>
	<script src="js/jquery-3.1.0.js" type="text/javascript"></script>
	<script src="js/script.js" type="text/javascript"></script>
  </head>
  <body>
  	<div>
		UserName：${user.username}<br>
		Password：${user.password}<br>
		var: ${var}<br>
		<a href="${pageContext.request.contextPath}/showUser">Show User</a><br/>
		<div>
			<input id="myButton" type="button" value="Post Data Test1"/>
		</div>
	</div>
  </body>
</html>

