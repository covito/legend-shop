<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<table width="205px" cellspacing="0" cellpadding="0"  style="margin-bottom: 4px;margin-left: 4px;" class="tables">
	<tr>
		<td class="titlebg" valign="middle" width="200px"><fmt:message key="new.product" /></td>
	</tr>
	<tr align="center" height="260px">
		<td width="200px" valign="top">
			<table cellspacing="2" cellpadding="0" width="98%" border="0">
				<c:if test="${newestProdList != null}">
					<c:forEach items="${requestScope.newestProdList}" var="prod" varStatus="status">
						<tr align="left" valign="top">
						<c:choose>
                          <c:when test="${fn:length(prod.name) > 14}">
                             <td valign="middle"  width="12px">
                             <img src="${pageContext.request.contextPath}/common/default/images/number/num_${status.index+1}.gif" height="13px">
                            </td>
                            <td title="${prod.name}"  align="left">
                            <div class="topnewsfixed">
								<font color="#006699">
                                   <a href="${pageContext.request.contextPath}/views/${prod.prodId}" style="margin-left: 3px;">${prod.name}</a>
                                </font>
                                </div>
                            </td>
                          </c:when>
                          <c:otherwise>
                          <td valign="middle" width="12px">
                             <img src="${pageContext.request.contextPath}/common/default/images/number/num_${status.index+1}.gif">
                            </td>
                            <td align="left">
                              <div class="topnewsfixed" >
                              <font color="#006699">
                               <a href="${pageContext.request.contextPath}/views/${prod.prodId}" style="margin-left: 3px;">${prod.name}</a>
                              </font>
                              </div>
                            </td>
                          </c:otherwise>
                        </c:choose>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</td>
	</tr>
</table>