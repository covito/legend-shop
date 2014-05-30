<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@include file='/WEB-INF/pages/common/common.jsp'%>
<!--[if lt IE 7]>
    <link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/default/css/overlay-ie6.css" />
<![endif]-->
<script src="${pageContext.request.contextPath}/common/js/jquery.tools.min.js"></script>
<link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/default/css/overlay.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/default/css/jquery.superbox.css" type="text/css" media="all" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery.superbox-min.js"></script>
<script src="${pageContext.request.contextPath}/common/default/js/productDetail.js"></script>
<script type="text/javascript">
$(function() {
    $("#apple img[rel]").overlay({effect: 'apple'}); 
});
function basket(){
		var count = document.getElementById("count");
			if(!isNumber(count.value)){
					alert('<fmt:message key="errors.number"><fmt:param value=""/></fmt:message>');
					count.focus();
					return;
			}
		document.getElementById("form1").submit();
	}
</script>

<style>
<!--
	.prodattr{margin-left: 10px;}
-->
</style>

<c:if test="${sessionScope.SPRING_SECURITY_LAST_USERNAME != null}">
<form id="form1" method="post" action="buy">
     <input name="action" value="buy" type="hidden"/>
    <input name="prodId" value="${prod.prodId}" type="hidden"/>
    <input name="pic" value="${prod.pic}" type="hidden"/>
    <input name="name" value="${prod.name}" type="hidden"/>
    <input name="cash" value="${prod.cash}" type="hidden"/>
<table width="100%" cellspacing="0" cellpadding="0" align="center" style="border:1px solid #CCCCCC">
	<tr>
    <td class="titlebg"><fmt:message key="product.subscribing.list"/></td>
  </tr>
<tr><td>
<table width="100%">
                          <tr> 
                            <td valign="top" align="center" width="256px"> 
                              <div id="apple">
                                <img src="<ls:images item='${prod.pic}' scale='1'/>" width="150px" height="150px"  style="margin-right: 10px" rel="#${prod.prodId}"/>
                                </div>
                                <div class="apple_overlay" id="${prod.prodId}">
							    		<img src="<ls:photo item='${prod.pic}'/>" width="640px" height="470px"/>
							            <div class="details">
							                <h2>${prod.name}</h2>
							            </div>
							     </div>
							  </td>
                            <td align="center" valign="top">
                
                            <table width="100%" border="1" cellpadding="0" cellspacing="0" 
                               style="border-collapse: collapse;margin-top: 7px;border: 1px solid #CCCCCC;"  bgcolor="#ECECEC">
                                <tr align="center">
                                  <td height="30" colspan="2" style="margin-left: 10px"><b><a href="${pageContext.request.contextPath}/views/${prod.prodId}">${prod.name}</a></b></td>
                                </tr>
                                <tr bgcolor="#FFFFFF"> 
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
                                <tr bgcolor="#FFFFFF"> 
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
                                <tr bgcolor="#FFFFFF"> 
                                  <td height="25"> 
                                    <div align="left" class="prodattr"><fmt:message key="price.hint"/></div>
                                  </td>
                                  <td> 
                                    <div align="left" class="prodattr">
                                    <fmt:message key="product.price"/> <font color="#D03430"><fmt:formatNumber type="currency" value="${prod.cash}" pattern="${CURRENCY_PATTERN}"/></font>
                                    <c:if test="${prod.carriage != null}">
                                  &nbsp;&nbsp;<fmt:message key="carriage.charge"/> <font color="#D03430"><fmt:formatNumber type="currency" value="${prod.carriage}" pattern="${CURRENCY_PATTERN}"/></font>
                                  <input type="hidden" id="carriage" name="carriage" value="${prod.carriage}"/>
                                  </c:if>
                                    </div>
                                  </td>
                                </tr>
                                <c:if test="${not empty prod.cash}">
                                <tr bgcolor="#FFFFFF"> 
                                  <td height="25"> 
                                    <div align="left" class="prodattr"><fmt:message key="total.charge"/></div>
                                  </td>
                                  <td>
                                    <div align="left" class="prodattr">
                                    <font color="#D03430"><b><span id="totalFee"><fmt:formatNumber type="currency" value="${prodTotalFee}" pattern="${CURRENCY_PATTERN}"/></span></b></font></div>
                                  </td>
                                </tr>
                                </c:if>
                                 <c:if test="${not empty prod.attribute}">
                                <tr bgcolor="#FFFFFF"> 
                                  <td height="25"> 
                                    <div align="left" class="prodattr"><fmt:message key="product.attribute"/></div>
                                  </td>
                                  <td> 
                                    <div align="left" class="prodattr" style="margin-top: 3px;margin-bottom: 3px">
                                     <input type="hidden" id="attribute" name="attribute" value="${sessionScope.BASKET_HW_ATTR}"/>
                                      ${sessionScope.BASKET_HW_ATTR}
                                    </div>
                                  </td>
                                </tr>                 
                                   </c:if>
                              <c:if test="${not empty prod.cash}">
                                <tr> 
                                  <td height="29"> 
                                    <div align="left" class="prodattr"><fmt:message key="comfirm.order"/></div>
                                  </td>
                                  <td>
                                    <div align="left" class="prodattr">
                                    <c:if test="${sessionScope.BASKET_HW_COUNT != null}">
                                    	<input type="text" class="input2" id="count" name="count" maxlength="8" size="3" value="${sessionScope.BASKET_HW_COUNT}"></input>
                                    </c:if>
                                    <c:if test="${sessionScope.BASKET_HW_COUNT == null}">
                                    	<input type="text"  class="input2"  id="count" name="count" maxlength="8" size="3" value="1"></input>
                                    </c:if>
                      
                         			<input type="button" value="<fmt:message key="submit"/>" class="s" onclick="javascript:basket()"/>
                                    </div>
                                  </td>
                                </tr>
                                </c:if>
                                    </c:when>
                                    <c:otherwise>

                                    </c:otherwise>
                                </c:choose>                         
                              </table>
                            </td>
                          </tr>
                        </table>
		</td></tr>

  <tr> 
    <td class="titlebg"><fmt:message key="product.subscribed.list"/></td>
  </tr>
  <tr><td><jsp:include flush="true" page="/p/bought" /></td></tr>
</table>
  </form>
</c:if>
