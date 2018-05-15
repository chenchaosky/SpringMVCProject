<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  	<head>  
    	<base href="<%=basePath%>">  
   		<title>User Login</title>  
   		<meta http-equiv="pragma" content="no-cache">  
   		<meta http-equiv="cache-control" content="no-cache">  
  		<meta http-equiv="expires" content="0">      
   		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
   		<meta http-equiv="description" content="This is my page">  
 	</head>  
 	<body>    
     	<form action="${pageContext.request.contextPath}/clientLogin" method="post">  
        	name:     <input type="text" name="username"> <br><br>  
        	password: <input type="text" name="password"> <br><br>  
        		      <input type="submit" value="submit">  
      	</form>  
  	</body>  
</html>  