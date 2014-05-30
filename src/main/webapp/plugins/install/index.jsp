<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%
Runtime runtime = Runtime.getRuntime();
long totalMemory = runtime.totalMemory();
long freeMemory  = runtime.freeMemory();
long totalKB = totalMemory/1024/1024;
long freeKB  = freeMemory/1024/1024;
request.setAttribute("ServerPort",request.getServerPort());
request.setAttribute("ServerName",request.getServerName());
%>

<%@page import="org.apache.commons.lang.StringUtils"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎使用LegendShop - 高性能的JAVA网购平台</title>

<link href="s.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.STYLE2 {color: #999999}
.STYLE3 {color: #0000FF}
.STYLE4 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
}
-->
</style>
<script>

</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="98%"  align="center" cellpadding="3" cellspacing="0">
  <tr>
    <td width="87%" height="59" class="line"><img src="${pageContext.request.contextPath}/common/images/legendshop.gif"></td>
    <td width="13%" align="right" valign="middle" class="line"><CENTER>
      <span class="STYLE4">LegendShop ${LEGENDSHOP_VERSION}</span>
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
            <td><img src="imgs/20050129171621546.gif" align="absmiddle"> 设置系统域名</td>
          </tr>
          <tr>
            <td><span class="STYLE2">配置数据库帐号</span></td>
          </tr>
          <tr>
            <td><span class="STYLE2">导入数据</span></td>
          </tr>
          <tr>
            <td class="STYLE2">安装完成</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
    <td width="79%" align="left" valign="top"><table width="778"  cellpadding="3" cellspacing="0">

      <tr>
        <td width="760">
<form name="form1" method="post" action="setup.jsp">
<fieldset >
    <legend style="font-size:15px;padding-left:5px;padding-right:5px;font-weight:bold">安装必读</legend>
	<table width="95%"  align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td align="left" style="line-height:20px;">&nbsp;</td>
      </tr>
      <tr>
        <td align="left" style="line-height:20px;">您将安装：<br>
        <c:choose>
        	<c:when test="${'C2C' == applicationScope.BUSINESS_MODE}">
        		<b>C2C版：</b>多用户的Java企业级电子商务软件<br></br>
        	</c:when>
        	<c:otherwise>
        		<b>B2C版：</b> 单用户的Java企业级电子商务软件<br></br>
        	</c:otherwise>
        </c:choose>
       
        详情请看： <a href="http://www.legendesign.net" target="_blank">官方网站</a>&nbsp;&nbsp;
        </td>
      </tr>
      <tr>
        <td align="left" style="line-height:20px;">&nbsp;</td>
      </tr>
    </table>
</fieldset> 
          <br/>
          <br/>
          <fieldset >
    <legend style="font-size:15px;padding-left:5px;padding-right:5px;font-weight:bold">系统要求</legend>
	<table width="95%"  align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td align="left" style="line-height:20px;">&nbsp;</td>
      </tr>
      <tr>
        <td align="left" style="line-height:20px;">操作系统：win server、linux、unix<br>
JDK：jdk1.5以上 <br>
应用服务器：tomcat6 、weblogic8 、jboss4 以上<br>
数据库：Mysql5 以上</td>
      </tr>
      <tr>
        <td align="left" style="line-height:20px;">&nbsp;</td>
      </tr>
    </table>
</fieldset> 
          <br/>
          <br/>
<fieldset >
    <legend style="font-size:15px;padding-left:5px;padding-right:5px;font-weight:bold">您当前系统环境</legend>
	<table width="95%"  cellspacing="0" cellpadding="3" align="center">
              
              <tr bgcolor=#f5f5f5>
                <td bgcolor="#FFFFFF" class="messageText">&nbsp;</td>
                <td bgcolor="#FFFFFF" class="messageText">&nbsp;</td>
              </tr>
              <tr bgcolor=#f5f5f5>
                <td width="30%" bgcolor="#FFFFFF" class="messageText">Request Protocol</td>
                <td bgcolor="#FFFFFF" class="messageText STYLE3"><%= request.getProtocol() %> </td>
              </tr>
              <tr bgcolor=#f5f5f5>
                <td bgcolor="#FFFFFF" class="messageText">Context path</td>
                <td bgcolor="#FFFFFF" class="messageText STYLE3"><%= request.getContextPath() %> </td>
              </tr>
              <tr bgcolor=#f5f5f5>
                <td bgcolor="#FFFFFF" class="messageText">Character Encoding</td>
                <td bgcolor="#FFFFFF" class="messageText STYLE3"><% out.print(request.getCharacterEncoding()); %>                </td>
              </tr>
              <tr bgcolor=#f5f5f5>
                <td bgcolor="#FFFFFF" class="messageText">Server name</td>
                <td bgcolor="#FFFFFF" class="messageText STYLE3"><%= request.getServerName() %> </td>
              </tr>
              <tr bgcolor=#f5f5f5>
                <td bgcolor="#FFFFFF" class="messageText">Server port</td>
                <td bgcolor="#FFFFFF" class="messageText STYLE3"><%= request.getServerPort() %> </td>
              </tr>
              <tr bgcolor=#f5f5f5>
                <td bgcolor="#FFFFFF" class="messageText">Remote address</td>
                <td bgcolor="#FFFFFF" class="messageText STYLE3"><%= request.getRemoteAddr() %> </td>
              </tr>
              <tr bgcolor=#f5f5f5>
                <td bgcolor="#FFFFFF" class="messageText">Remote host</td>
                <td bgcolor="#FFFFFF" class="messageText STYLE3"><%= request.getRemoteHost() %> </td>
              </tr>
              <tr bgcolor=#f5f5f5>
                <td bgcolor="#FFFFFF" class="messageText">Locale</td>
                <td bgcolor="#FFFFFF" class="messageText STYLE3"><%= request.getLocale() %> </td>
              </tr>
              <tr bgcolor=#f5f5f5>
                <td bgcolor="#FFFFFF" class="messageText">Root Real Path</td>
                <td bgcolor="#FFFFFF" class="messageText STYLE3"><%= pageContext.getServletContext().getRealPath("/") %> </td>
              </tr>
              <tr bgcolor=#f5f5f5>
                <td bgcolor="#FFFFFF" class="messageText">WEB-INF/classes Real Path</td>
                <td bgcolor="#FFFFFF" class="messageText STYLE3"><%= pageContext.getServletContext().getRealPath("/WEB-INF/classes") %> </td>
              </tr>
              <tr bgcolor=#f5f5f5>
                <td bgcolor="#FFFFFF" class="messageText">Total Memory</td>
                <td bgcolor="#FFFFFF" class="messageText STYLE3"><%=totalKB%> MB</td>
              </tr>
              <tr bgcolor=#f5f5f5>
                <td bgcolor="#FFFFFF" class="messageText">Free Memory</td>
                <td bgcolor="#FFFFFF" class="messageText STYLE3"><%=freeKB%> MB</td>
              </tr>
              <tr bgcolor=#f5f5f5>
                <td bgcolor="#FFFFFF" class="messageText">&nbsp;</td>
                <td bgcolor="#FFFFFF" class="messageText">&nbsp;</td>
              </tr>
            </table>
</fieldset>	

            
          <br/>
          <br/>
<fieldset >
    <legend style="font-size:15px;padding-left:5px;padding-right:5px;font-weight:bold">域名设置</legend>
<table width="95%"  align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>
	                <span class="STYLE2">- 无论您的操作是window还是linux，请使用 /，不要用 \<br>
                - 请注意，路径最后以 / 结尾</span><br>
                <br></td>
  </tr>
  <tr>
    <td>
    <!-- 
    <input name="path" type="text" class="inp" id="path" value="<%=StringUtils.replace(pageContext.getServletContext().getRealPath("/"),"\\","/")%>" size="60">
     -->
     <c:if test="${ServerPort == 80}">
     	<input type="text" class="inp" id="domainName" name="domainName" value="http://${ServerName}/" size="60" />
     </c:if>
     <c:if test="${ServerPort != 80}">
    	 <input type="text" class="inp" id="domainName"  name="domainName" value="http://${ServerName}:${ServerPort}/" size="60">
     </c:if>
     
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
</fieldset>	
              <br>
              <br>
              <input name="Submit" type="submit" class="btn" value=" 下一步 " >
              </p>
 
          <p><strong><font color="#FF0000"> </font></strong> </p>
            </form>		  
         </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
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
    <td colspan="2" align="center" valign="middle">LegendShop ${LEGENDSHOP_VERSION} - All Rights Reserved.</td>
  </tr>
</table>
</body>
</html>
