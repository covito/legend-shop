<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.legendshop.central.install.PropertiesException"%>
<%@page import="com.legendshop.central.install.SetupImpl"%>
<%@page import="com.legendshop.central.install.Setup"%>
<%@page import="com.legendshop.central.install.DBException"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%
	String jdbc_username = request.getParameter("jdbc_username");
	String jdbc_password = request.getParameter("jdbc_password");
	String dbname = request.getParameter("dbname");
	String dbhost = request.getParameter("dbhost");
	String dbport = request.getParameter("dbport");
	String domainName = request.getParameter("domainName");
	String install_db = request.getParameter("install_db");
	try{
		Setup setup = new SetupImpl(jdbc_username, jdbc_password ,dbname,dbhost,dbport,domainName,install_db);
		setup.startSetup(request);
	}catch(DBException e){
		   RequestDispatcher rd = request.getRequestDispatcher("step_db_f.jsp");  
		   rd.forward(request, response);
	}catch(PropertiesException e){
		   RequestDispatcher rd = request.getRequestDispatcher("step_issp_f.jsp");  
		   rd.forward(request, response);
	}catch(Exception e){
		   RequestDispatcher rd = request.getRequestDispatcher("step_f.jsp");  
		   request.setAttribute("err",e);
		   rd.forward(request, response);
	}
%>
<%
Runtime runtime = Runtime.getRuntime();
long totalMemory = runtime.totalMemory();
long freeMemory  = runtime.freeMemory();
long totalKB = totalMemory/1024;
long freeKB  = freeMemory/1024;
%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎使用LegendShop - 高性能的JAVA网店系统</title>

<link href="s.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.STYLE3 {
	font-size: 16px;
	color: #000000;
	font-weight: bold;
}
.STYLE4 {color: #0000FF}
.STYLE5 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
}
-->
</style>
<script>
function s(theForm)
{
	theForm.action = theForm.systenFolder.value+theForm.action;
}

function go(url)
{
	window.open(url,'','');
}
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="98%"  align="center" cellpadding="3" cellspacing="0">
  <tr>
    <td width="87%" height="59" class="line"><img src="${pageContext.request.contextPath}/common/images/legendshop.gif"></td>
    <td width="13%" align="right" valign="middle" class="line"><CENTER>
      <span class="STYLE5">LegendShop ${LEGENDSHOP_VERSION}</span>
    </CENTER></td>
  </tr>
</table>
<br>
<table width="98%"  align="center" cellpadding="3" cellspacing="0">
  <tr>
    <td width="21%" align="left" valign="top"><table width="162"  cellpadding="3" cellspacing="1" bgcolor="#999999">
      <tr>
        <td width="215" bgcolor="#FFFFFF"><table width="163"  cellspacing="0" cellpadding="5">
          <tr>
            <td width="217" align="center" valign="middle" bgcolor="#eeeeee" class="line"><strong>安装步骤</strong></td>
          </tr>
          <tr>
            <td><img src="imgs/select.gif" width="14" height="13" align="absmiddle"> 设置系统域名</td>
          </tr>
          <tr>
            <td> <img src="imgs/select.gif" width="14" height="13" align="absmiddle"> 配置数据库帐号</td>
          </tr>
          <tr>
            <td><img src="imgs/select.gif" width="14" height="13" align="absmiddle"> 导入数据</td>
          </tr>
          <tr>
            <td><img src="imgs/select.gif" width="14" height="13" align="absmiddle"> 安装完成</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
    <td width="79%" align="left" valign="top"><table width="778" height="297"  cellpadding="3" cellspacing="0">

      <tr>
        <td width="760" height="25"><span class="STYLE3"><img src="imgs/p_success.gif" width="36" height="33" align="absmiddle">&nbsp;&nbsp;&nbsp;&nbsp;LegendShop安装成功，请重启系统！！</span></td>
      </tr>
      <tr>
        <c:choose>
        	<c:when test="${'C2C' == applicationScope.BUSINESS_MODE}">
        	<td valign="top" style="line-height:20px;"><p><br>
          * <span class="STYLE4">为了确保您网站安全，安装成功后，请把install目录删除</span><br>
          <br>
          <br><b>LegendShop</b> 默认安装已经带有3个网店<br>
          系统默认商家，访问地址${applicationScope.DOMAIN_NAME}/home,  用户名: home, 密码: home，<br>
         演示商家, 访问地址${applicationScope.DOMAIN_NAME}/shop/360buy,  用户名: 360buy, 密码: 360buy<br>
          <br>
          	超级管理员帐号：root,密码：root <br>
          </p>
          </td>
        	</c:when>
        	<c:otherwise>
		        <td valign="top" style="line-height:20px;"><p><br>
		          * <span class="STYLE4">为了确保您网站安全，安装成功后，请把install目录删除</span><br>
		          </p>
		          </td>
        	</c:otherwise>
        </c:choose>
      

      </tr>
      <tr>
        <td align="center" valign="middle">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="2" align="center" valign="middle"><hr width="100%" size="1" noshade></td>
  </tr>
  <tr>
    <td colspan="2" align="center" valign="middle">Power By LegendShop ${LEGENDSHOP_VERSION} - All Rights Reserved.</td>
  </tr>
</table>
</body>
</html>
