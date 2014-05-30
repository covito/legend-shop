<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
<head>
<title>${sessionScope.SPRING_SECURITY_LAST_USERNAME} - 后台管理</title>
<style>
body
{
  scrollbar-base-color:#C0D586;
  scrollbar-arrow-color:#FFFFFF;
  scrollbar-shadow-color:DEEFC6;
}
</style>
</head>
<frameset rows="60,*" cols="*" frameborder="no"  framespacing="0">
  <frame src="${pageContext.request.contextPath}/admin/top" name="topFrame" scrolling="no">
  <frameset cols="180,*" name="btFrame" id="btFrame" frameborder="NO"  framespacing="0">
    <frame src="${pageContext.request.contextPath}/admin/menu/2" noresize name="menu" scrolling="yes">
    <frame src="${pageContext.request.contextPath}/admin/dashboard" noresize name="main" scrolling="yes">
  </frameset>
</frameset>
<noframes>
	<body>Your browser does not support Frame!</body>
</noframes>
</html>