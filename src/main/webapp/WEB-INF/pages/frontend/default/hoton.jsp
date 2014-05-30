<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<c:if test="${requestScope.hotonList != null}">
<table width="205px" style="margin-bottom: 4px;margin-right: 5px;" class="tables" cellpadding="0" cellspacing="0">
                      <tr>
                        <td class="titlebg"><fmt:message key="hot.sale"/></td>
                      </tr>
                      <tr> 
                        <td> 
<c:forEach items="${requestScope.hotonList}" var="hoton">
	                                <table width="100%" cellpadding="1" cellspacing="0">
   									<tr> 
                                      <td width="15" height="22" align="center">
                                      	<img src="${pageContext.request.contextPath}/common/default/images/dot.gif" style="margin-left: 3px">
                                      </td>
                                       <td width="98%" align="left" title="${hoton.name}">
                                                        <div class="topnewsfixed" title="${hoton.name}">
                                                        <a href="${pageContext.request.contextPath}/views/${hoton.prodId}">${hoton.name}</a>
                                                        </div>
                                       </td>
                                    </tr>
                                 </table>
                        </c:forEach>

</td>
                      </tr>
                    </table>
       </c:if>