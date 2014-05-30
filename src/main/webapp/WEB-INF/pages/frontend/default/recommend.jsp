<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<!--[if lt IE 7]>
    <link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/default/css/overlay-ie6.css" />
<![endif]-->
<div class="recommond">
		<fmt:message key="product.recommend" />
	<table width="100%" cellpadding="0" cellspacing="15" >
		<tr>
		<c:choose>
			<c:when test="${requestScope.commendProdList != null && fn:length(requestScope.commendProdList) > 0}">
			<c:forEach items="${requestScope.commendProdList}" var="prod" varStatus="status">
				<td width="20%" align="left" style="font-weight: normal;color: #666666">
						<div>
							<a href="${pageContext.request.contextPath}/views/${prod.prodId}" >
							<img src="<ls:images item='${prod.pic}' scale="1"/>"  title="${prod.name}" id="pic"></a></div>
						<div class="topnewsfixed" title="${prod.name}">${prod.name}</div>
					<c:if test="${not empty prod.cash}">
						<fmt:message key="prod_cash" /> <font color="red"><fmt:formatNumber type="currency" value="${prod.cash}"  pattern="${CURRENCY_PATTERN}"/></font>

					</c:if>
				</td>
				<c:if test="${(status.index+1)%5==0&&(status.index+1)>=5}"></tr><tr></c:if>
			</c:forEach>
				</c:when>
				<c:otherwise>
						<td style="color: #000"><fmt:message key="nothingfound"/></td>
				</c:otherwise>
			 </c:choose>
		</tr>
	</table>
</div>

