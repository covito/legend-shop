<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
    <c:forEach items="${requestScope.USER_REG_ADV_740}" var="adv">
	<table width="100%" cellpadding="0" cellspacing="0" style="margin-bottom: 5px;" class="picstyle">
		<tr><td><a href="${adv.linkUrl}"><img src="<ls:photo item='${adv.picUrl}'/>" title="${adv.title}" width="100%"/></a></td></tr>
	 </table>
	</c:forEach>
	<c:if test="${pub != null}">
		<table width="746px" cellpadding="0" cellspacing="0" style="TABLE-LAYOUT: fixed; word-break: break-all" class="tables">
			<tr>
				 <td class="titlebg"><b>${pub.title}</td>
			</tr>
			<tr>
				<td height="200px" valign="top">
					<div align="center">
						<div align="left" width="100%" style="margin-left: 8px;margin-right: 5px">
							${pub.msg}
						</div>
					</div>
				</td>
			</tr>
		</table>
	</c:if>