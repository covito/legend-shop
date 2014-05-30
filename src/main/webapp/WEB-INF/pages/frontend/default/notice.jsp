<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<table width="205px" height="116px" cellspacing="0" cellpadding="0"  style="margin-right: 5px;margin-bottom: 0px;margin-left: 0px;margin-top: 0px" class="tables">
                    <tr> 
                      <td class="titlebg"><fmt:message key="index.notice"/></td>
                    </tr>
                    <tr> 
                    <td height="87px" valign="top">                         
   <c:choose>
   <c:when test="${requestScope.pubList == null}">
   	  <p>&nbsp;<br>
   </c:when>
   <c:otherwise>
  	 <c:forEach items="${requestScope.pubList}" var="pub" end="3">
  	 	<div class="topnewsfixed"  align="left" title="${pub.title}" > 
                  <img src="${pageContext.request.contextPath}/common/default/images/dot.gif" width="15" height="15" style="margin-left: 3px"><a href="${pageContext.request.contextPath}/pub/${pub.id}" target="_blank">${pub.title}</a>
        </div>             
  	 </c:forEach>
   </c:otherwise>
</c:choose>

                      </td>
                    </tr>
</table>