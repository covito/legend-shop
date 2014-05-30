<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<c:if test="${visitedProd != null && fn:length(visitedProd) > 0}">
<!--[if lt IE 7]>
    <link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/default/css/overlay-ie6.css" />
<![endif]-->
<table><tr><td>
<div style="font-weight: bold; font-family:sans-serif; font-size:15px;margin-left: 10px;margin-top: 10px"><fmt:message key="visited.product"/></div>
</td></tr></table>

	<table cellpadding="0" cellspacing="10">
		<tr align="left">
			<c:forEach items="${visitedProd}" var="prod" varStatus="status">
				<td align="left">
					<c:choose>
						<c:when test="${fn:length(prod.name) > 9}">
						<span>
							<a href="${pageContext.request.contextPath}/views/${prod.id}" >
							<img src="<ls:images item='${prod.pic}' scale='2'/>" title="${prod.name}" id="prodvisited" height="100px" width="100px"></a>
							</span><br>${fn:substring(prod.name,0,9)}...<br>
						</c:when>
						<c:otherwise>
						<span >
							<a href="${pageContext.request.contextPath}/views/${prod.id}">
							<img src="<ls:images item='${prod.pic}'  scale='2' />" id="prodvisited"  height="100px" width="100px"></a>
						</span><br>${prod.name}<br>
						</c:otherwise>
					</c:choose>
				</td>
				<c:if test="${(status.index+1)%6==0&&(status.index+1)>=6}"></tr><tr></c:if>
			</c:forEach>
		</tr>
	</table>
</c:if>

