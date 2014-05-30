<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@include file='/WEB-INF/pages/common/common.jsp'%>
<link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/default/css/overlay.css" />
      	<script type="text/javascript">
		function turnToPage(id) {
			document.getElementById('nsortId').value= id;
			document.getElementById('subNsortId').value= null;
			pager(1);//turn to another nosrt page
		}
		
		function turnToSubSortPage(id) {
            document.getElementById('subNsortId').value= id;
            pager(1);//turn to another nosrt page
        }
		
		function pager(curPageNO){
			document.getElementById("curPageNO").value=curPageNO;
			document.getElementById("form1").submit();
		}
	</script>
    <table cellpadding="0" cellspacing="0" width="100%">
                    <tr> 
                      <td>
						<a target="_blank" href="${pageContext.request.contextPath}/sort/${sort.sortId}">
						<img src="<ls:photo item='${sort.picture}'/>" width="740px" class="banner" height="180px"></a>
					  </td>
                    </tr>
        <tr> 
          <td style="border-bottom:#cccccc 1px solid" bgcolor="#f7f7f7"> 
            <table width="100%" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="25" align="left">&nbsp;
               <a href="${pageContext.request.contextPath}/sort/${sort.sortId}">${sort.sortName}</a>
                / <a href="${pageContext.request.contextPath}/nsort?sortId=${sort.sortId}&nsortId=${nsort.nsortId}">${nsort.nsortName}</a>
                <c:if test="${subNsort.nsortName!=null}">
                /
                 <a href="${pageContext.request.contextPath}/nsort?sortId=${sort.sortId}&nsortId=${nsort.nsortId}&subNsortId=${subNsort.nsortId}">${subNsort.nsortName}</a>
                </c:if>
                &nbsp;</td>
                <td  align="right"> 
      		<c:if test="${not empty nsort}">
				<select onchange="turnToPage(this.value)">
					<c:forEach items="${requestScope.nsortList}" var="nsort">
						<c:choose>
						   <c:when test="${CurrentNsortId == nsort.nsortId}">
						   	<option id="${nsort.nsortId}" value="${nsort.nsortId}" class="c" selected>
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
			 <c:if test="${not empty hasSubSort}">
                <select onchange="turnToSubSortPage(this.value)">
                   <option id="allProduct" value="0"><fmt:message key="refer.category"/></option>
                    <c:forEach items="${requestScope.subNsortList}" var="subNsort">
                        <c:choose>
                           <c:when test="${CurrentSubNsortId == subNsort.nsortId}">
                            <option id="${subNsort.nsortId}" value="${subNsort.nsortId}" class="c" selected>
                            ${subNsort.nsortName}
                            </option>
                           </c:when>
                           <c:otherwise>
                            <option id="${subNsort.nsortId}" value="${subNsort.nsortId}" class="c">
                                ${subNsort.nsortName}
                                </option>
                           </c:otherwise>
                        </c:choose> 
                    </c:forEach> 
                </select>
            </c:if>
                </td>
              </tr>
            </table></td>
        </tr>
          <tr height="40" valign="bottom">
                      <td > 
                        <p align="right"><c:out value="${toolBar}" escapeXml="${toolBar}"></c:out></p>
                      </td>
                    </tr>
        <tr> 
          <td width="100%">
           				<form action="${pageContext.request.contextPath}/nsort" id="form1" method="get">
										<input type="hidden" id="curPageNO" name="curPageNO"
											value="${prod.curPageNO}">
										<input type="hidden" name="sortId" value="${sort.sortId}">
         							    <input type="hidden" id="nsortId" name="nsortId" value="${nsort.nsortId}">
         							    <input type="hidden" id="subNsortId" name="subNsortId" value="${subNsort.nsortId}">
          <table width="100%" cellspacing="10"
											cellpadding="0">
											<tr>
												<c:forEach items="${requestScope.list}"
													var="prodDetail" varStatus="status">
													<c:choose>
														<c:when
															test="${(status.index+1)%2==0&&(status.index+1)>=2}">
															<td width="50%" align="center">
														</c:when>
														<c:otherwise>
															<td width="50%" align="center"
																style="position: relative;border-right:#989DA5 1px dotted;">
														</c:otherwise>
													</c:choose>


													<table width="100%" align="center"
														cellpadding="0" cellspacing="0">
														<tr>
															<td rowspan="3" align="center" width="50%">
															<div id="apple">
																<a href="${pageContext.request.contextPath}/views/${prodDetail.prodId}">
																	<img src="<ls:images item='${prodDetail.pic}' scale='1'/>" width="150px"
																		height="150px" style="margin: 5px" ></a></div>
															</td>
														</tr>
														<tr align="left" >
															<td width="50%">
																<a href="${pageContext.request.contextPath}/views/${prodDetail.prodId}">
																<font color="#006699"><u>${prodDetail.name}</u> </font> </a>
															</td>
														</tr>
														<c:if test="${not empty prodDetail.cash}">
														<tr>
															<td width="50%"
																align="left" valign="top">
																<fmt:message key="prod_price" /> 
																<s><fmt:formatNumber type="currency"
																		value="${prodDetail.price}" pattern="${CURRENCY_PATTERN}"/> </s>
																<br>
																<fmt:message key="prod_cash" /> 
																<font color="red"> <fmt:formatNumber
																		type="currency" value="${prodDetail.cash}" pattern="${CURRENCY_PATTERN}"/> </font>
																<br>
															</td>
														</tr>
							                             </c:if>
													</table>

													</td>
											<c:if test="${(status.index+1)%2==0&&(status.index+1)>=2}">
												</tr>
												<tr>
											</c:if>
												</c:forEach>
											</tr>
										</table>
          </form>
          
          
          </td>
         
        </tr>
        <tr> 
          <td width="100%" height="20"> <div align="center"></div></td>
        </tr>
        <tr> 
          <td width=744px height="23"> <p align="right"><c:out value="${toolBar}"  escapeXml="${toolBar}"></c:out></p></td>
        </tr>
      </table>