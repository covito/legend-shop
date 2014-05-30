<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<c:forEach items="${requestScope.USER_REG_ADV_740}" var="adv">
<table width="100%" cellpadding="0" cellspacing="0" style="margin-bottom: 5px" class="picstyle">
<tr><td><a href="${adv.linkUrl}"><img src="<ls:photo item='${adv.picUrl}'/>" title="${adv.title}" width="740px"/></a></td></tr>
 </table>
</c:forEach>
	<table>
	<tr>
		<td width="744px">
			<table width="100%" cellspacing="0" cellpadding="0" style="border: 1px;border-collapse: collapse;color: #ccc" border="1" class="tables">
                                        <tr>
						                    <c:choose>
						                       <c:when test="${list != null}">
						                          <c:forEach items="${requestScope.list}" var="league" varStatus="status">
                                                    <td align="left">
                                                    <table style="color: #000">
                                                        <tr>
                                                            <td align="center" >
                                                             <a href="<ls:domain shopName='${league.friendId}'/>">
                                                             <c:choose>
                                                             		<c:when test="${league.banner == null}">
                                                             		                   <img src="${pageContext.request.contextPath}/common/images/legendshop.gif" 
                                                                                         height="65px"  width="230px" style="margin: 1px" title="${league.province}/${league.city}/${league.area}/${league.friendId}">
                                                             		</c:when>
                                                             		<c:otherwise>
                                                             			          <img src="<ls:photo item='${league.banner}'/>" 
                                                                                     height="65px"  width="230px" style="margin: 1px"  title="${league.province}/${league.city}/${league.area}/${league.friendId}">
                                                             		</c:otherwise>
                                                             </c:choose>
                                                             </a>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center" height="30px">
                                                            <c:choose>
                                                            	<c:when test="${league.friendName == null || league.friendName == ''}">${league.friendId}</c:when>
                                                            	<c:otherwise>${league.friendName}</c:otherwise>
                                                            </c:choose>
                                                            </td>
                                                         </tr>
                                                    </table>
                                                    </td>
                                            <c:if test="${(status.index+1)%3==0&&(status.index+1)>=3}">
                                                </tr>
                                            	<tr>
                                            </c:if>
                                                </c:forEach>
						                       </c:when>
						                       <c:otherwise>
						                       <td align="center">
						                          <fmt:message key="nothingfound"/>
						                        </td>
						                       </c:otherwise>
						                    </c:choose>   
                                            </tr>
                                        </table>
		</td>
	</tr>
	<tr>
		<td><c:if test="${toolBar!=null}">
			<p align="right">
				<c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
			</p>
			</c:if>
		</td>
	</tr>
</table>
 <form action="${pageContext.request.contextPath}/league" id="form1" method="post">
 	<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
 </form>
    <script language="JavaScript" type="text/javascript">
        function pager(curPageNO){
            document.getElementById("curPageNO").value=curPageNO;
            document.getElementById("form1").submit();
        }
</script>
