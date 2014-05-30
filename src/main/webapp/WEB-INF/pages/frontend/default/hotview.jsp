<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<c:if test="${requestScope.hotViewList != null}">
<!--[if lt IE 7]>
    <link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/default/css/overlay-ie6.css" />
<![endif]-->
<div class="tables">
	<table class="recommond" width="100%" cellpadding="0" cellspacing="0">
	  <tr><td><fmt:message key="shop.hot.product"><fmt:param value="<lb:currentShop />"/></fmt:message></td></tr>
	  <tr><td><table style="font-weight: normal;color: #666666">
		<tr>
			<c:forEach items="${requestScope.hotViewList}" var="prod" varStatus="status">
				<%-- 
	style="position: relative;border:#989DA5 1px dotted;"
	--%>
				<td width="20%" align="center">
					<c:choose>
						<c:when test="${fn:length(prod.name) > 30}">
						<span>
							<a href="${pageContext.request.contextPath}/views/${prod.prodId}" ><img
									src="<ls:images item='${prod.pic}' scale='1'/>" width="150px" height="150px"
									title="${prod.name}" id="pic"></a></span><br>${fn:substring(prod.name,0,30)}...<br>
									
						</c:when>
						<c:otherwise>
						<span >
							<a href="${pageContext.request.contextPath}/views/${prod.prodId}"><img
									src="<ls:images item='${prod.pic}' scale='1'/>" width="150px" height="150px"
									 id="pic"></a></span><br>${prod.name}<br>
									
						</c:otherwise>
					</c:choose>
					<c:if test="${not empty prod.cash}">
						<fmt:message key="prod_cash" /> <font color="red"><fmt:formatNumber
								type="currency" value="${prod.cash}" pattern="${CURRENCY_PATTERN}"/></font>

					</c:if>
				</td>
				<c:if test="${(status.index+1)%5==0&&(status.index+1)>=5}"></tr><tr></c:if>
			</c:forEach>
		</tr>
		</table></td></tr>
	</table>
</div>
</c:if>
