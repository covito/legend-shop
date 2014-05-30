<%@ page language="java" isErrorPage="true"  contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@include file='/WEB-INF/pages/common/localeBundle.jsp'%>
<html >
<head>
<title>    
    <c:choose>
    	<c:when test="${User_Messages.code != null}">${User_Messages.code}</c:when>
    	<c:otherwise>401</c:otherwise>
    </c:choose> Error - LegendShop</title>
</head>
<body>
<center>
<TABLE cellSpacing="0" cellPadding="0" width="955px">
  <TBODY>
  <TR>
    <TD noWrap width="1%" rowSpan=3>
    <a href="${pageContext.request.contextPath}/index"><img src="${pageContext.request.contextPath}/common/images/legendshop.gif" width="200px" title="LegendShop"/></a>
    <TD>&nbsp;</TD></TR>
  <TR>
    <TD bgColor=#3366cc><FONT face=arial,sans-serif color=#ffffff><B>
    <c:choose>
    	<c:when test="${User_Messages.code != null}">${User_Messages.code}</c:when>
    	<c:otherwise>401</c:otherwise>
    </c:choose>
      Error</B></FONT></TD></TR>
  <TR>
    <TD>&nbsp;</TD></TR>
    </TBODY>
    </TABLE>
  
   <br>
    <table cellSpacing=0 cellPadding=0 width="955px"><tr><td>
      <BLOCKQUOTE>
     <c:choose>
    	<c:when test="${User_Messages.title != null}">
		  <H2>${User_Messages.title}</H2>
		  	${User_Messages.desc} 
		</c:when>
    	<c:otherwise>
		  	<H2>访问权限不足</H2>
		  	您的访问权限不足，请与管理员联系. <br><br>
		  	You can not access this component.
		</c:otherwise>
    </c:choose>
	<fmt:message key="error.page.message"/>
    </BLOCKQUOTE>
    </td></tr></table>
    <jsp:include page="/WEB-INF/pages/common/moreoption.jsp"></jsp:include>
      </center>


 <br>
</body>
</html>
