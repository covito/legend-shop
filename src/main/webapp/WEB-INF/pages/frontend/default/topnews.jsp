<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<table width="205px" cellspacing="0" cellpadding="0" style="margin-right: 5px;margin-bottom: 4px;margin-left: 0px;margin-top: 4px" class="tables" >
	<tr>
		<td class="titlebg"><fmt:message key="newsCenter"/>
			 <a href="${pageContext.request.contextPath}/allnews"><img src="${pageContext.request.contextPath}/common/default/images/more.gif" width="45px" /></a>
		</td>
	</tr>
	<tr height="136px">
		<td valign="top" align="left">
			<c:forEach items="${requestScope.newList}" var="article">
				<div class="topnewsfixed">
						<c:choose>
                          <c:when test="${fn:length(article.newsTitle) > 20}">
	                        <div title="${article.newsTitle}">
	                            <img src="${pageContext.request.contextPath}/common/default/images/dot.gif" width="15" height="15" style="margin-left: 3px">
	                            <a href="${pageContext.request.contextPath}/news/${article.newsId}"><c:choose>
                                	<c:when test="${article.highLine == 1}"><span style="color:#FF6600">${article.newsTitle}</span></c:when>
                                	<c:otherwise>${article.newsTitle}</c:otherwise>
                                </c:choose></a>&nbsp;
	                        </div>
                          </c:when>
						  <c:otherwise>
						    <div>
                                <img src="${pageContext.request.contextPath}/common/default/images/dot.gif" width="15" height="15" style="margin-left: 3px">
                                <a href="${pageContext.request.contextPath}/news/${article.newsId}"><c:choose>
                                	<c:when test="${article.highLine == 1}"><span style="color:#FF6600">${article.newsTitle}</span></c:when>
                                	<c:otherwise>${article.newsTitle}</c:otherwise>
                                </c:choose></a>&nbsp;
                            </div>
						  </c:otherwise>
						</c:choose>
				</div>
			</c:forEach>
		</td>
	</tr>
</table>