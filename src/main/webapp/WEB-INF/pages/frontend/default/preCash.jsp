<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
<script src="<ls:templateResource item='/common/js/jquery.formatCurrency-1.4.0.min.js'/>" type="text/javascript"></script>
<script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/default/css/bought.css" type="text/css" media="all" />
<lb:shopDetail var="shopDetail" >
    <link href="<ls:templateResource item='/common/style/style_${shopDetail.frontEndStyle}.css'/>" rel="stylesheet" type="text/css" />
    <link href="<ls:templateResource item='/common/style/global_${shopDetail.frontEndStyle}.css'/>" rel="stylesheet" type="text/css" />
</lb:shopDetail>
<form id="cashAction" action="${pageContext.request.contextPath}/p/cash" method="post">
		<table width="100%" cellpadding="0" cellspacing="0"  border="0" >
		<thead>
			<tr style="font-weight: bold">
				<td width="42%" height="25"><fmt:message key="product.name" /></td>
				<td width="12%"><fmt:message key="product.price" /></td>
				<td width="12%"><fmt:message key="product.attribute" /></td>
				<td width="8%"><fmt:message key="product.number" /></td>
				<td width="150px"><fmt:message key="price.total" /></td>
				<td width="100px"><fmt:message key="operation"/></td>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${requestScope.baskets}" var="basket">
				<tr class="item-content">
					<td><input type="hidden" name="strArray" value="${basket.basketId}" arg="${basket.prodId}" />
					<!-- 商品 -->
					<div class="item-pic J_ItemPic img-loaded" >
						<a  target="_blank"  href="${pageContext.request.contextPath}/views/${basket.prodId}"  title="${basket.prodName}" >
					      		<img src="<ls:images item='${basket.pic}'  scale='2'/>"  width="100px" height="100px" />
					      </a>
				 	</div>
				 	<c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
					<div class="item-title">店铺：<a href="<ls:domain shopName='${basket.shopName}' />">${basket.shopName}</a></div>
					</c:if>
					<div class="item-title"><a target="_blank" href="${pageContext.request.contextPath}/views/${basket.prodId}">${basket.prodName}</a></div>
					</td>
					<td>
					<fmt:formatNumber type="currency" value="${basket.cash}" pattern="${CURRENCY_PATTERN}"/>
					<input id="cash${basket.basketId}" name="cash${basket.basketId}"  type="hidden" value="${basket.cash}">
					</td>
					<td>${basket.attribute}</div>
					</td>
					<td>x ${basket.basketCount}<input type="hidden"  id="basketCount${basket.basketId}"  name="basketCount${basket.basketId}"  value="${basket.basketCount}"></td>
					<td class="td-sum">
					<div id="currencyLabel"><fmt:formatNumber type="currency" value="${basket.total}" pattern="${CURRENCY_PATTERN}"/></div>
					<c:if test="${basket.carriage != null && basket.carriage > 0}">
                		  <fmt:message key="carriage.charge"/><fmt:formatNumber type="currency" value="${basket.carriage}" pattern="${CURRENCY_PATTERN}"/>
                    </c:if>
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/p/clear?basketId=${basket.basketId}"
							title='<fmt:message key="product.name.delete"/>'><fmt:message key="delete"/></a>
					</td>
				</tr>
			</c:forEach>
			<tr>
					<td height="25" colspan="8">
						<div align="right" style="margin-right: 86px;">
							<b><fmt:message key="price.total" />
									<span id="totalCurrencyLabel">
										<fmt:formatNumber type="currency" value="${requestScope.totalcash}" pattern="${CURRENCY_PATTERN}"/>
									</span>
							</b>
						</div>
					</td>
			</tr>
	</tbody>
</table>
</form>

  