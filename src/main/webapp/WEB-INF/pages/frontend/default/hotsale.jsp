<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<table width="205" cellspacing="0" cellpadding="0" style="margin-bottom: 5px;margin-left: 5px;">

	<tr>
		<td class="titlebg"><fmt:message key="hotsale" /></td>
	</tr>

	<tr align="center" height="105">
		<td width="100%">
			<table width="100%">
					<c:forEach items="${requestScope.hotsaleList}" var="hotsale" varStatus="status">
						<tr>
							<td>
								<a href="${pageContext.request.contextPath}/views/${hotsale.prodId}"> 
								<img src="${pageContext.request.contextPath}/common/default/images/number/num_${status.index+1}.gif">
								</a>
							</td>
							<td align="left">
											<font color="#006699"><a
												href="${pageContext.request.contextPath}/views/${hotsale.prodId}" target="_blank">
													${hotsale.name} </a>
											</font>
							</td>
						</tr>
					</c:forEach>
			</table>
		</td>
	</tr>
</table>
