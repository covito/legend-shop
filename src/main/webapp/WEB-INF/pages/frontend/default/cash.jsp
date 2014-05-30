<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="com.legendshop.business.common.CommonServiceUtil"%>
<%@page import="com.legendshop.spi.constants.Constants"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@include file='/WEB-INF/pages/common/common.jsp'%>
<%
	request.getSession().setAttribute(Constants.TOKEN, CommonServiceUtil.generateRandom());
%>
<c:if test="${sessionScope.SPRING_SECURITY_LAST_USERNAME != null}">
<form action="${pageContext.request.contextPath}/sub/save" method="post" id="cashSaveForm" name="cashSaveForm">
<table style="margin-top: 5px" class="tables" width="100%" cellpadding="0" cellspacing="0">
  <tr> 
    <td class="titlebg"><fmt:message key="cash.bar"/></td>
  </tr>
  <tr> 
     <td><%@include file='preCash.jsp'%></td>
  </tr>
  <tr><td><jsp:include flush="true" page="/p/cashsave"></jsp:include></td></tr>
</table>
</form>
</c:if>