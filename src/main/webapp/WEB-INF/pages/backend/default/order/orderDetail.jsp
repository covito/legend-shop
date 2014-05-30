<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@include file='/WEB-INF/pages/common/common.jsp'%>

<%@ taglib uri="http://www.legendesign.net/biz" prefix="lb"%>
<html>
<head>
<lb:shopDetail var="shopDetail" >
    <link href="<ls:templateResource item='/common/style/style_${shopDetail.frontEndStyle}.css'/>" rel="stylesheet" type="text/css" />
    <link href="<ls:templateResource item='/common/style/global_${shopDetail.frontEndStyle}.css'/>" rel="stylesheet" type="text/css" />
</lb:shopDetail>
<title>
 ${sub.subNumber} <fmt:message key="product.subscribed.list"/>
</title>
</head> 
<body>
<table width="700" cellspacing="0" cellpadding="0" align="center" style="margin-top: 5px" class="tables">
        <tr>
          <td class="titlebg"><fmt:message key="your.subNember"/>:${sub.subNumber}</td>
        </tr>
        <tr>
          <td>
            <table width="100%" align="left">
              <tr> 
                <td width="45%"><div align="left" style="margin-left: 5px"><fmt:message key="dateStr"/>：<fmt:formatDate value="${sub.subDate}" pattern="yyyy-MM-dd HH:mm"/></div></td>
                <td width="55%"><div align="left" style="margin-left: 5px"><fmt:message key="Payment.Date"/>：<fmt:formatDate value="${sub.payDate}" pattern="yyyy-MM-dd HH:mm"/></div></td>
              </tr>
              <tr> 
                <td><div align="left" style="margin-left: 5px"><fmt:message key="orderer"/>：${sub.orderName}</div></td>
                <td><div align="left" style="margin-left: 5px"><fmt:message key="User"/>：${sub.userName}</div></td>
              </tr>
              <tr> 
                <td><div align="left" style="margin-left: 5px"><fmt:message key="address"/>：${sub.subAdds}</div></td>
                <td><div align="left" style="margin-left: 5px"><fmt:message key="shop.name"/>：${sub.shopName}</div></td>
              </tr>
              <tr> 
                <td><div align="left" style="margin-left: 5px"><fmt:message key="Phone"/>：${sub.subTel}</div></td>
                <td><div align="left" style="margin-left: 5px"><fmt:message key="postcode"/>：${sub.subPost}</div></td>
              </tr>
              <tr> 
                <td><div align="left" style="margin-left: 5px"><fmt:message key="Order.Status"/>：
				  <ls:optionGroup type="label" required="true" cache="true"
	                beanName="ORDER_STATUS" selectedValue="${sub.status}" defaultDisp=""/></div>
				</td>
                <td><div align="left" style="margin-left: 5px"><fmt:message key="payType"/>：${sub.payTypeName}</div></td>
              </tr>
              <tr> 
                <td><div align="left" style="margin-left: 5px"><fmt:message key="charge.amount"/>
                ：<b><fmt:formatNumber type="currency" value="${sub.actualTotal}" pattern="${CURRENCY_PATTERN}"/></b>
                <c:if test="${sub.score ne null}">(<fmt:message key="score.hint"/>：${sub.score})</c:if>
                </div></td>
                <td><div align="left" style="margin-left: 5px"><fmt:message key="Order.Update.Date"/>：<fmt:formatDate value="${sub.updateDate}" pattern="yyyy-MM-dd HH:mm"/></div></td>
              </tr>
              <tr> 
                <td  colspan="2">
                  <table width="100%" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="20%"><div align="left" style="margin-left: 5px"><fmt:message key="memo"/>：</div></td>
                      <td width="80%">${sub.other}</td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
<table width="700" align="center" cellspacing="0" cellpadding="0" style="margin-top: 5px" class="tables">
  <tr> 
    <td class="titlebg">
    <div>
   <b><fmt:message key="product.subscribed.list"/></b>
   </div>
    </td>
  </tr>
	<tr> 
    <td width="100%">
		<table width="100%" cellpadding="2" cellspacing="2">
              <tr>
                <td width="30%"> <div align="center"><fmt:message key="product.name"/></div></td>
                <td width="20%"> <div align="center"><fmt:message key="product.attribute"/></div></td>
                <td width="12%"> <div align="center"><fmt:message key="product.price"/></div></td>
                <td width="15%"> <div align="center"><fmt:message key="product.number"/></div></td>
                <td width="15%"> <div align="center"><fmt:message key="dateStr"/></div></td>
                <td width="8%"> <div align="center"><fmt:message key="price.total"/></div></td>
              </tr>
              <c:forEach items="${requestScope.baskets}" var="basket">
              <tr> 
                <td><div align="center"><a target="_blank" href="${pageContext.request.contextPath}/views/${basket.prodId}"><font color="#FF0000">${basket.prodName}</font></a></div></td>
                <td><div align="center">${basket.attribute}</div></td>
                <td> <div align="center"> 
                    ${basket.cash}
                    <c:if test="${basket.carriage != null}">
                    (<fmt:message key="carriage.charge"/>：<fmt:formatNumber type="currency" value="${basket.carriage}" pattern="${CURRENCY_PATTERN}"/>)
                    </c:if>
                    </div></td>
                <td width="65"> <div align="center">${basket.basketCount} 
                  </div></td>
                <td> <div align="center"><fmt:formatDate value="${basket.basketDate}" pattern="yyyy-MM-dd HH:mm"/>
                  </div></td>
                
                <td> <div align="center"> 
                  <fmt:formatNumber type="currency" value="${basket.total}" pattern="${CURRENCY_PATTERN}"/></div></td>
 		</c:forEach>
              <tr> 
                <td colspan="5"><div align="center"><fmt:message key="price.total"/>:</div></td>
                <td><div align="center"><fmt:formatNumber type="currency" value="${requestScope.totalcash}" pattern="${CURRENCY_PATTERN}"/>
                  </div></td>
            </table>
      </td>
  </tr>
</table>
</body>
</html>