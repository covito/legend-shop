<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<table width="205px" cellspacing="0" cellpadding="0"
	bgcolor="#FFFFF6" style="margin-bottom: 5px;margin-right: 5px;table-layout: fixed;" class="tables">
	<tr>
		<td class="titlebg"><fmt:message key="newsCenter"/>
		          <a href="${pageContext.request.contextPath}/allnews"><img src="${pageContext.request.contextPath}/common/default/images/more.gif" width="45"></a></td>
	</tr>
	<tr height="135px">
		<td valign="top" align="left">
			<c:forEach items="${requestScope.newList}" var="article">
				<div class="topnewsfixed">
                        <c:choose>
                          <c:when test="${fn:length(article.newsTitle) > 18}">
                            <div  title="${article.newsTitle}" class="topnewsfixed">
                                <img src="${pageContext.request.contextPath}/common/default/images/dot.gif" style="margin-left: 3px"><a href="${pageContext.request.contextPath}/news/${article.newsId}">${article.newsTitle}</a>
                            </div>
                          </c:when>
                          <c:otherwise>
                            <div class="topnewsfixed">
                                <img src="${pageContext.request.contextPath}/common/default/images/dot.gif" width="15" height="15" style="margin-left: 3px"><a href="${pageContext.request.contextPath}/news/${article.newsId}">${article.newsTitle}</a>
                            </div>
                          </c:otherwise>
                        </c:choose>
				</div>
			</c:forEach>
		</td>
	</tr>
</table>