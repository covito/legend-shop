<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<lb:shopDetail var="shopDetail" />
<c:if test="${requestScope.externalLinkList!=null}">
<table width="954px" cellpadding="0" cellspacing="0" style="margin-top: 5px" align="center">
<tr><td align="center">
<table cellpadding="2" cellspacing="2" >
<tr>
<c:forEach items="${requestScope.externalLinkList}" var="el">
  <td height="40px" align="center">
      		<a href="${el.url}" target=_blank title="${el.content}">
      		<c:choose>
      			<c:when test="${el.picture != null}">
      			<img src="<ls:photo item='${el.picture}'/>" title="${el.wordlink}" width="88px" height="31px"/><br>${el.wordlink}
      			</c:when>
      			<c:otherwise>${el.wordlink}</c:otherwise>
      		</c:choose>
      		</a>   
    </td>
     </c:forEach>
     </tr>
  </table>
  </td></tr></table>
 </c:if> 
  
 <c:choose>
 	<c:when test="${shopDetail != null}">
<table width="954px" class="tables" style="margin-top: 5px" cellpadding="0" cellspacing="0">
<tr>
 <td class="titlebg"><fmt:message key="order.step"/></td>
  </tr><tr>
 <td>
 <jsp:include page="/copy"/>
</td>
</tr>
</table> 	
 	</c:when>
 	<c:otherwise>
    <jsp:include page="/copy"/>
 	</c:otherwise>
 </c:choose>
<br>
