<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@include file='/WEB-INF/pages/common/common.jsp'%>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/default/css/overlay.css" />
   <table width="100%" cellspacing="0" cellpadding="0" class="tables">
                <tr> 
                  <td class="titlebg"><fmt:message key="contact.method"><fmt:param value="<lb:currentShop />"/></fmt:message></td>
                </tr>
                <tr> 
                  <td> <table width="100%" cellspacing="1" cellpadding="0" height="100%">
                      <tr>
                      <c:if test="${user.shopDetail.shopPic != null}">
                      <td valign="top" width="300px"><div id="apple" style="margin: 5px"><img src="<ls:photo item='${user.shopDetail.shopPic}'/>" width="300"/></div></td>
                      </c:if>
                        <td align="left" valign="top">
                            <table width="100%" align="center" cellpadding="0" cellspacing="0" style="margin-bottom: 6;margin-top: 9px">
                           <tr>
                                <td width="110px" height="29"><div align="right">
                               <fmt:message key="mall.name"/>：</div></td>
                                <td>
									${user.shopDetail.siteName}
								</td>
                              </tr>
                              <tr>
                              <tr> 
                                <td height="29"><div align="right"><fmt:message key="product.num"/>：</div></td>
                                <td height="29"><div align="left">${user.shopDetail.productNum}</div></td>
                              </tr>
                              <tr> 
                                <td height="29"><div align="right"><fmt:message key="shop.create.date"/>：</div></td>
                                <td height="29"><div align="left"><fmt:formatDate value="${user.shopDetail.recDate}" pattern="yyyy-MM-dd"/></div></td>
                              </tr>
                                <td height="29"><div align="right"><fmt:message key="user.name"/>：</div></td>
                                <td>
									${user.userName}
								</td>
                              </tr>
                              <tr>
                                <td height="29"><div align="right"><fmt:message key="real.name"/>：</div></td>
                                <td height="29"><div align="left">
                                    <b>${user.nickName}</b>
                                  </div></td>
                              </tr>
                              <c:if test="${user.userTel != null && user.userTel != ''}">                     
                              <tr> 
                                <td height="29"><div align="right"><fmt:message key="Telphone"/>：</div></td>
                                <td height="29"><div align="left">
                                    ${user.userTel}
                                  </div></td>
                              </tr>
                              </c:if>
                              <c:if test="${user.qq != null && user.qq != ''}">
                              <tr> 
                                <td height="29"><div align="right">QQ：</div></td>
                                <td height="29"><div align="left">                           
                                    <c:forEach var="qq" items="${user.qqList}">
                                    <div style="margin: 5px">
										<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${qq}&site=qq&menu=yes"><img  src="http://wpa.qq.com/pa?p=2:${qq}:42" alt="QQ:${qq}" title="QQ:${qq}"></a></div>
							        </c:forEach>
                                  </div></td>
                              </tr>
                              </c:if>
                              <c:if test="${user.msn != null  && user.msn != ''}">
                              <tr> 
                                <td height="29"><div align="right">MSN：</div></td>
                                <td height="29"><div align="left">
									<p><a href="msnim:chat?contact=${user.msn}">
									<font color="#008000">${user.msn}</font></a>
									</p>
                                  </div></td>
                              </tr>
                              </c:if>
                               <c:if test="${user.fax != null && user.fax != ''}">
                              <tr> 
                                <td height="29"><div align="right"><fmt:message key="Fax"/>：</div></td>
                                <td height="29"><div align="left">${user.fax}</div></td>
                              </tr>
                              </c:if>
                              <c:if test="${user.userPostcode != null && user.userPostcode != ''}">
                              <tr> 
                                <td height="29"><div align="right"><fmt:message key="PostCode"/>：</div></td>
                                <td height="29"><div align="left"> 
                                    ${user.userPostcode}
                                  </div></td>
                              </tr>
                              </c:if>
                              <c:if test="${user.shopDetail.shopAddr != null && user.shopDetail.shopAddr != ''}">
                              <tr> 
                                <td height="29"><div align="right"><fmt:message key="Address"/>：</div></td>
                                <td height="29"><div align="left"> 
                                    ${user.shopDetail.shopAddr}
                                  </div></td>
                              </tr>
                              </c:if>
                              <c:if test="${user.shopDetail.bankCard != null && user.shopDetail.bankCard != ''}">
                              <tr> 
                                <td height="29"><div align="right">银行汇款帐号：</div></td>
                                <td height="29"><div align="left"> 
                                    ${user.shopDetail.bankCard}
                                  </div></td>
                              </tr>
                              </c:if>
                              
                               <c:if test="${user.shopDetail.payee != null && user.shopDetail.payee != ''}">
                              <tr> 
                                <td height="29"><div align="right">收款人姓名：</div></td>
                                <td height="29"><div align="left"> 
                                    ${user.shopDetail.payee}
                                  </div></td>
                              </tr>
                              </c:if>                             
             
             
                             <c:if test="${user.shopDetail.code != null && user.shopDetail.code != ''}">
                              <tr> 
                                <td height="29"><div align="right">邮政编码：</div></td>
                                <td height="29"><div align="left"> 
                                    ${user.shopDetail.code}
                                  </div></td>
                              </tr>
                              </c:if>                   
        
                             <c:if test="${user.shopDetail.postAddr != null && user.shopDetail.postAddr != ''}">
                              <tr> 
                                <td height="29"><div align="right">汇款地址：</div></td>
                                <td height="29"><div align="left"> 
                                    ${user.shopDetail.postAddr}
                                  </div></td>
                              </tr>
                              </c:if>                       

                             <c:if test="${user.shopDetail.recipient != null && user.shopDetail.recipient != ''}">
                              <tr> 
                                <td height="29"><div align="right">邮递接收人：</div></td>
                                <td height="29"><div align="left"> 
                                    ${user.shopDetail.recipient}
                                  </div></td>
                              </tr>
                              </c:if>   
                              
                            </table>
                         </td>
                      </tr>
                    </table></td>
                </tr>
              </table>
             