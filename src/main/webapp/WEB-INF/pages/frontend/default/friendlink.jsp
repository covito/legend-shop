<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
                              <table width="180" cellspacing="0" cellpadding="0" style="margin-bottom: 5px;margin-right: 5px;">
                                <tr> 
                                  <td align="center"><img src="${pageContext.request.contextPath}/common/default/images/promo_list_top.gif" width="100%" height="1"></td>
                                </tr>
                                <tr><td class="titlebg"><fmt:message key="friend.link"/></td></tr>
                                <tr> 
                                  <td bgcolor="#ECECEC"> <p style="line-height:150%" align=center>                          
								      <c:forEach items="${requestScope.externalLinkList}" var="el">
								      	<a href="${el.url}" target=_blank>${el.wordlink}</a><br>
								      </c:forEach>
                                    </p></td>
                                </tr>
                              </table>