<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/default/css/overlay.css" />
<script src="${pageContext.request.contextPath}/common/default/js/productDetail.js"></script>
<lb:shopDetail var="shopDetail" />
<script type="text/javascript">
function saveToCartAction(){
		var count = document.getElementById("count");
			if(!isNumber(count.value) || count.value == 0){
					alert('<fmt:message key="errors.number"><fmt:param value=""/></fmt:message>');
					count.focus();
					return;
			}
			
  	var prodAttr = getProdAttr();
	if(prodAttr.startWith("error")){
		alert('<fmt:message key="please.select"/>：' + prodAttr.substring(5));
	}else{
		document.getElementById("prodattr").value = prodAttr;
		var count = document.getElementById("count");
		//add to cart
			jQuery.ajax({
							url: "${pageContext.request.contextPath}/addtocart", 
							data: {"prodId":${prod.prodId}, "count": count.value, "attribute": prodAttr},
							type:'post', 
							async : false, //默认为true 异步   
							dataType : 'json', 
							error:function(data){
							},   
							success:function(retData){
							    if(retData.status == 'OK'){
							   		 document.getElementById("form1").submit();
							    }else if(retData.status == 'OWNER'){
							     	alert('<fmt:message key="failed.product.owner" />');
							    }else if(retData.status == 'LESS'){
							   		 alert('<fmt:message key="product.less" />');
							    }

							}   
			});  
	
	}
}
</script>
<!--[if lt IE 7]>
    <link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/default/css/overlay-ie6.css" />
<![endif]-->
<style>
<!--
	.prodattr{margin-left: 10px;}
-->
</style>
<table style="TABLE-LAYOUT: fixed; WORD-WRAP: break-word;" width="100%">
                    <tr> 
                      <td> 
                      <form action="${pageContext.request.contextPath}/basket/query" id="form1" method="post">
                      <input type="hidden" id="prodId" name="prodId" value="${prod.prodId}"/>
                      <input type="hidden" id="prodattr" name="prodattr"/>
                        <table>
                          <tr> 
                            <td valign="top" align="center"> 
										<!-- insert Prod pic -->  
								  <%@include file="prodpics.jsp"%> 
							  </td>
                            <td align="center" valign="top" style="width: 100%">
                            <table width="100%">
                                <tr align="center">
                                  <td height="30" colspan="2"><b>${prod.name}</b></td>
                                </tr>
                                <tr> 
                                  <td width="80px" height="25"> 
                                    <div align="left" class="prodattr"><fmt:message key="sort_name"/></div>
                                  </td>
                                  <td height="25"> 
                                    <div align="left" class="prodattr">
                                    <a href="${pageContext.request.contextPath}/sort/${prod.sortId}">${prod.sortName}</a>
                                     <c:if test="${not empty prod.nsortName}">
                                     / <a href="${pageContext.request.contextPath}/nsort?sortId=${prod.sortId}&nsortId=${prod.nsortId}">${prod.nsortName}</a>
                                     </c:if>   
                                     <c:if test="${not empty prod.subNsortName}">
                                     / <a href="${pageContext.request.contextPath}/nsort?sortId=${prod.sortId}&nsortId=${prod.nsortId}&subNsortId=${prod.subNsortId}">
                                     ${prod.subNsortName}</a>
                                     </c:if>    
                                    </div>
                                  </td>
                                </tr>
                                <c:if test="${not empty prod.brandName}">
                                <tr> 
                                  <td height="25"> 
                                    <div align="left" class="prodattr"><fmt:message key="brand.hint"/></div>
                                  </td>
                                  <td height="25">
                                    <div align="left" class="prodattr">
                                    ${prod.brandName}</div>
                                  </td>
                                </tr>
                                </c:if>
                                <c:choose>
                                <c:when test="${not empty prod.cash}">
                                <c:if test="${not empty prod.price}">
                                 <tr> 
                                  <td height="25"> 
                                    <div align="left" class="prodattr"><fmt:message key="prod_price"/></div>
                                  </td>
                                  <td> 
                                    <div align="left" class="prodattr">
                                    <font color="#D03430"><b><s><fmt:formatNumber type="currency" value="${prod.price}" pattern="${CURRENCY_PATTERN}"/></s></b>
                                    </font>&nbsp;
                                    <fmt:message key="discount"/> <fmt:formatNumber type="percent" value="${prod.cash / prod.price}"/>
                                    </div>
                                  </td>
                                </tr>
                                </c:if>
                                
                                <tr> 
                                  <td height="25"> 
                                    <div align="left" class="prodattr"><fmt:message key="price.hint"/></div>
                                  </td>
                                  <td> 
                                    <div align="left" class="prodattr">
                                    <font color="#D03430"><b><fmt:formatNumber type="currency" value="${prod.cash}" pattern="${CURRENCY_PATTERN}"/></b>
                                    </font>
                                    <c:if test="${prod.carriage!= null}">
                          			&nbsp;<fmt:message key="carriage.charge"/>&nbsp;<font color="#D03430"><fmt:formatNumber type="currency" value="${prod.carriage}" pattern="${CURRENCY_PATTERN}"/></font>
                          			</c:if>
                                    </div>
                                  </td>
                                </tr>
                                 <c:if test="${not empty prod.attribute}">
                                <tr> 
                                  <td colspan="2">
                                  <div align="left" class="prodattr">
                                    <jsp:include page="/dynamic/attribute/${prod.prodId}" flush="true"/>
                                    </div>
                                  </td>
                                </tr>                 
                                   </c:if>
                                  <tr> 
                                  <td height="25">
                                    <div align="left" class="prodattr"><fmt:message key="product.number"/></div>
                                  </td>
                                  <td> 
                                    <div align="left" class="prodattr" style="margin-top: 3px;margin-bottom: 3px">
                                      <input type="text" class="input2" id="count" name="count" value="1" size="3" maxlength="8"></input>
                                      <c:if test="${not empty prod.stocks}">
                                      (<fmt:message key="stock.hint"/>&nbsp;<font color="#D03430">${prod.stocks}</font>&nbsp;<fmt:message key="product.unit"/>
                                      <c:if test="${prod.buys > 0}">
                                      	,<fmt:message key="selled.number"/>&nbsp;<font color="#D03430">${prod.buys}</font>&nbsp;<fmt:message key="product.unit"/>
                                      </c:if>
                                      )
                                      </c:if>
                                    </div>
                                  </td>
                                </tr>  
                                <!-- 订购 -->
                                <c:if test="${shopDetail.qqList != null && fn:length(shopDetail.qqList) > 0}">
                                  <tr>
                                  <td height="25"><div align="left" class="prodattr"><fmt:message key="contact.seller"/></div></td>
                                   <td><div align="left" class="prodattr">
                                  <c:forEach var="qq" items="${shopDetail.qqList}">
										<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${qq}&site=qq&menu=yes"><img  src="http://wpa.qq.com/pa?p=2:${qq}:42" alt="QQ:${qq}" title="QQ:${qq}"></a>
							        </c:forEach>
							        </div>
                                  </td>
                                  </tr>
                                  </c:if>
                                <tr>
                                  <td height="30" colspan="2" align="center">
                                   <div>
                                   <%@include file="product/addtocart.jsp"%>
                                   <c:choose>
                                   	<c:when test="${prod.stocks != null && prod.cash != null && prod.stocks > 0}">
                                   		<a href="javascript:saveToCartAction()">
                                   		<img  src="${pageContext.request.contextPath}/common/default/images/buynows.jpg" title='<fmt:message key="order" />' />
                                   	</c:when>
                                   	<c:otherwise><a href="${pageContext.request.contextPath}/leaveword"> <img  src="${pageContext.request.contextPath}/common/default/images/productless.jpg" title='<fmt:message key="notice.product.less" />' /></a></c:otherwise>
                                   </c:choose>
                                   
                                    </div>
                                  </td>
                                </tr>
                                    </c:when>
                                    <c:otherwise>
                                    </c:otherwise>
                                </c:choose>                         
                              </table>
                              <table width="100%" cellspacing="0" cellpadding="0" style="TABLE-LAYOUT: fixed; WORD-WRAP: break-word;">
                                <tr align="left"> 
                                  <td>
                                  <pre>${prod.brief}</pre>
                                  </td>
                                </tr>
                              </table>

                            </td>
                          </tr>
                        </table>
                        </form>
                      </td>
                    </tr>

                          <tr><td><%@ include file="product/producttab.jsp"%></td></tr> 
                  </table>
            