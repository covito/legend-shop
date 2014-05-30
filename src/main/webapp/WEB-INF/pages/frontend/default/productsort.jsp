<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/default/css/overlay.css" />
<script type="text/javascript">
		function turnToPage(id) {
			document.getElementById('nsortId').value= id;
			document.getElementById('PagingForm').submit();
		}
		
		function pager(curPageNO){
			document.getElementById("curPageNO").value=curPageNO;
			document.getElementById("form1").submit();
		}
	</script>
	<table cellspacing="0" cellpadding="0" width="744px">
	    <tr>
               <c:choose>
                    <c:when test="${sort.picture !=null}">
                      <td><img src="<ls:photo item='${sort.picture}'/>" width="740px" height="240px" class="banner"></a>
                      </td>
                    </c:when>
                    <c:otherwise>
                        <td height="0px" width="735px"></td>                      
                    </c:otherwise>
                </c:choose>
    </tr>
    <c:if test="${sort.sortName != null}">
	<tr>
		<td style="border-bottom:#cccccc 1px solid;background-color:#f7f7f7 " width="100%">
			<table width="744px">
				<tr>
					<td height="25" align="left">
						${sort.sortName} 
				<c:if test="${requestScope.hotProdList!=null}">&nbsp; <fmt:message key="hot.product"/>：
				<c:forEach items="${requestScope.hotProdList}" var="search">
					<a href="<ls:url address='/searchall'/>/0/${search.msg }">${search.title}</a>
			     </c:forEach>
			     </c:if>
					</td>
					<td align="right">
						<c:if test="${not empty requestScope.nsortList}">
							<select onchange="turnToPage(this.value)">
								<option id="allProduct" value="0">
									<fmt:message key="all.category" />
								</option>
								<c:forEach items="${requestScope.nsortList}" var="nsort">
									<c:choose>
										<c:when test="${CurrentNsortId == nsort.nsortId}">
											<option id="${nsort.nsortId}" value="${nsort.nsortId}"
												class="c" selected>
												${nsort.nsortName}
											</option>
										</c:when>
										<c:otherwise>
											<option id="${nsort.nsortId}" value="${nsort.nsortId}" class="c">
												${nsort.nsortName}
											</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</c:if>
						<form id="PagingForm" method="post" action="${pageContext.request.contextPath}/nsort">
							<input type="hidden" id="sortId" name="sortId" value="${sort.sortId}">
							<input type="hidden" id="nsortId" name="nsortId">
						</form>

					</td>
				</tr>
			</table>
		</td>
	</tr>
	</c:if>
	<c:if test="${toolBar!=null}">
		<tr height="40" valign="bottom">
			<td>
				<p align="right">
					<c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
				</p>
			</td>
		</tr>
	</c:if>
	<tr>
		<td>
            <table>
                            <tr>
                                <td valign="top">
                                    <form action="${pageContext.request.contextPath}/sort" id="form1" method="post">
                                        <input type="hidden" id="curPageNO" name="curPageNO" value="${prod.curPageNO}"/>
                                        <input type="hidden" name="sortId" value="${sort.sortId}">
                                        <table width="100%" cellspacing="0"
                                            cellpadding="0">
                                            <tr>
						                    <c:choose>
						                       <c:when test="${list != null && fn:length(list) > 0}">
						                          <c:forEach items="${requestScope.list}"
                                                    var="prodDetail" varStatus="status">
                                                    <td  align="center">
                                                    <table>
                                                        <tr>
                                                            <td align="center" >
                                                            <div id="apple">
                                                                <a href="${pageContext.request.contextPath}/views/${prodDetail.prodId}">
                                                                    <img src="<ls:images item='${prodDetail.pic}' scale='1'/>" width="150px"  height="150px" style="margin: 2px" ></a>
                                                            </div>
                                                            </td>
                                                        </tr>
                                                        <tr align="center">   
                                                        <td width="100%" align="center" title="${prodDetail.name}">
                                                        <div class="topnewsfixed" title="${prod.name}">
                                                        <a href="${pageContext.request.contextPath}/views/${prodDetail.prodId}">${prodDetail.name}</a>
                                                        </div>
                                                        </td>                                                        
                                                        </tr>
                                                        <tr>
                                                            <td align="center" valign="top">
                                                             <c:if test="${not empty prodDetail.cash}">
                                                                <fmt:message key="prod_cash" /> 
                                                                <font color="red"><fmt:formatNumber
                                                                        type="currency" value="${prodDetail.cash}" pattern="${CURRENCY_PATTERN}"/></font>
                                                                <!-- 
                                                                <c:if test="${empty userName}">
                                                                <a href="${pageContext.request.contextPath}/basket?prodId=${prodDetail.prodId}" rel="#overlay">
                                                                <img src="${pageContext.request.contextPath}/common/default/images/order.gif" align="middle" title="<fmt:message key='order'/>"></a>
                                                                </c:if>
                                                                <c:if test="${not empty userName}">
                                                                <a href="${pageContext.request.contextPath}/basket?prodId=${prodDetail.prodId}" rel="superbox[iframe][525x420]">
                                                                <img  src="${pageContext.request.contextPath}/common/default/images/order.gif" align="middle" title="<fmt:message key='order'/>"></a>                                                                 
                                                                </c:if>
                                                                 -->
                                                              </c:if>
                                                            </td>
                                                        </tr>
                            
                                                    </table>

                                                    </td>
                                            <c:if test="${(status.index+1)%4==0&&(status.index+1)>=4}">
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
                                    </form>
                                </td>
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

