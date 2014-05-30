<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
 <table width="100%" cellspacing="0" cellpadding="0"  class="tables">
  <tr> 
    <td class="titlebg" width="90%"><fmt:message key="no.login.hint"/></td>
  </tr>
  <tr> 
    <td align="left">
    	<div style="margin-left: 50px;margin-bottom: 20px;margin-top: 20px">
     		<fmt:message key="nologin.hint.self">
     			<fmt:param value="${pageContext.request.contextPath}/login?returnUrl=${returnUrl}"></fmt:param>
     			<fmt:param value="${pageContext.request.contextPath}/reg"></fmt:param>
     		</fmt:message>
     	</div>
    </td>
  </tr>
</table>