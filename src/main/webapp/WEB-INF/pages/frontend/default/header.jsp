<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/common.jsp'%> 
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@page import="com.legendshop.core.helper.PropertiesUtil"%>
<%@page import="com.legendshop.core.helper.ThreadLocalContext"%>
<lb:shopDetail var="shopDetail" />
<link href="${pageContext.request.contextPath}/common/default/css/header.css" rel="stylesheet" type="text/css" />
<div id="pagetop">
	<div id="headertop">
		<table width="954px" cellspacing="0" cellpadding="0"
			style="margin-bottom: 5px;" align="center" border="0">
			<tr>
				<td valign="top" align="left">
					<div id="site-nav">
						<ul class="quick-menu">
						 <ls:plugin pluginId="advanceSearch">
							<li class="first">
								<a href="${pageContext.request.contextPath}/all" target="_blank"><b><ls:i18n key="search.total.index" /></b></a>
							</li>
							</ls:plugin>
							<c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
								<li><a href=" ${pageContext.request.contextPath}/home"><ls:i18n key="shop.total.index" /></a></li>
							</c:if>
							<% if(!ThreadLocalContext.getCurrentShopName(request, response).equals(PropertiesUtil.getDefaultShopName())) {%>
							<li>
								<ls:i18n key="nows.location" />
								<c:if test="${shopDetail.province != null}">
								  		 ${shopDetail.province}/${shopDetail.city}/${shopDetail.area}/
								  </c:if>
								<b><a href="${pageContext.request.contextPath}/shopcontact"><lb:currentShop /></a>
								</b>
							</li>
							<% } %>
							<c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
								<li>
									<a href="${pageContext.request.contextPath}/reg?openshop=1" target="_blank"><ls:i18n
											key="register.shop" />
									</a>
								</li>
							</c:if>
							<li class="favorite">
								<a href='#'
									onclick="javascript:bookmark('<ls:i18n key="welcome.to.legendshop"/><%=PropertiesUtil.getDefaultShopName()%> - LegendShop购物商城','<ls:domain shopName='<%=PropertiesUtil.getDefaultShopName()%>' />');">
									<ls:i18n key="shop.favorite" />
								</a>
							</li>
							<li class="services menu-item last">
								<div class="menu">
									<a class="menu-hd"
										href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club"
										target="_blank"><ls:i18n key="club.navigation" /><B></B>
									</a>
									
									<!-- navigation start -->
									<div class="menu-bd"
										style="WIDTH: 250px; HEIGHT: 250px; _width: 202px;">
										<div class="menu-bd-panel">
									   
									   <c:forEach items="${requestScope.navigationList}" var="navigation" end="4">
									   		<dl>
												<dt>
													<b>${navigation.name}</b>
												<dd>
													<c:forEach items="${navigation.subItems}" var="item">
														<a href="${item.link}" target="_blank">${item.name}</a>
													</c:forEach>
												</dd>
												</dt>
											</dl>
									   </c:forEach>
										</div>
										<s class=r></s><s class=rt></s><s class=lt></s><s class=b
											style="WIDTH: 169px"></s><s class="b b2" style="WIDTH: 169px"></s><s
											class=rb></s><s class=lb></s>
									</div>
									
									<!-- navigation end -->
									
								</div>
							</li>
						</ul>
					</div>
				</td>
			</tr>
		</table>
	</div>
</div>