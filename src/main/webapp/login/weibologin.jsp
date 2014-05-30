<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns:wb="http://open.weibo.com/wb">
  <head>
		<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=2682551803" type="text/javascript" charset="utf-8"></script>
  </head>
  
  <body>
    This is my JSP page. <br>
   <wb:login-button type="3,2" onlogin="login" onlogout="logout">登录按钮</wb:login-button>


  </body>
</html>
