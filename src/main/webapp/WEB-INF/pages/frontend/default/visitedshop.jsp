<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<c:if test="${visitedShop != null && fn:length(visitedShop) > 0 && 'C2C' == applicationScope.BUSINESS_MODE}">
					<table width="100%" style="margin-bottom: 4px;margin-right: 5px;" class="tables" cellpadding="0" cellspacing="0">
					    <tr><td class="titlebg"><fmt:message key="visited.mall"/></td></tr>
						<tr><td align="left">
							  <c:forEach items="${visitedShop}" var="visitedShop" varStatus="status">
							  <div style="margin: 2px;"><a href="<ls:domain shopName='${visitedShop.siteName}' />"   target="_blank">${visitedShop.siteName}</a></div>
							  </c:forEach>
						</td></tr>
					</table>
					</c:if>
					<br/>
					<c:if test="${visitedProd != null && fn:length(visitedProd) > 0}">
					<table  width="100%" style="margin-bottom: 4px;margin-right: 5px;" class="tables" cellpadding="0" cellspacing="0">
					    <tr><td class="titlebg"><fmt:message key="visited.product"/></td></tr>
						<tr><td align="left">
							  <c:forEach items="${visitedProd}" var="visitProd" varStatus="status">
							  <div style="margin: 2px;"><a href="${pageContext.request.contextPath}/views/${visitProd.prodId}" title="${visitProd.keyWord}" target="_blank">${visitProd.name}</a></div>
							  </c:forEach>
						</td></tr>
						<tr><td></td></tr>
					</table>
</c:if>