<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
<table class="tables" cellpadding="0" cellspacing="0">
                                     <tr> 
                                      <td class="titlebg">
                                          <c:choose>
                                            <c:when test="${(not empty User_Messages.code) and (User_Messages.code != 200)}">
                                                <SPAN class="afterOperate"><fmt:message key="operation.fail"/></SPAN>
                                            </c:when>
                                            <c:otherwise>
                                                <SPAN class="afterOperate"><fmt:message key="operation.successful"/></SPAN>
                                            </c:otherwise>
                                        </c:choose>
                                        <br>
                                      </td>
                                    </tr>
                                    <tr>
                                      <td  bgcolor="#FFFFF6" align="left" >
                                       <div style="margin-left: 85px">
										    <c:if test="${User_Messages.title != null}">
										    <br>
										    			  <H2>${User_Messages.title}</H2>
												  	        ${User_Messages.desc} 
										    </c:if>
										</div>
										<div style="margin-top: 10px">
											<jsp:include page="/WEB-INF/pages/common/moreoption.jsp"></jsp:include>
										</div>
                                      </td>
                                    </tr>
                                    <tr><td  bgcolor="#FFFFF6">
                                     <br></br>
                                     <div align="center"> 
                                         <input type="submit" onclick="javascript:turnToIndex()" value='<fmt:message key="submit"/>' class="s"/>
                                      </div>
                                      <br></br>
                                    </td></tr>
                                  </table>
	            <script type="text/javascript">
		             function turnToIndex(){
		             	window.location.href = "${pageContext.request.contextPath}/index";
		             }
	             </script>