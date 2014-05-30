<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.legendshop.core.helper.PropertiesUtil"%>
<%@page import="com.legendshop.core.constant.SysParameterEnum"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@include file='/WEB-INF/pages/common/common.jsp'%>

	<lb:shopDetail var="shopDetail" >
	    <link href="<ls:templateResource item='/common/style/style_${shopDetail.frontEndStyle}.css'/>" rel="stylesheet" type="text/css" />
	    <link href="<ls:templateResource item='/common/style/global_${shopDetail.frontEndStyle}.css'/>" rel="stylesheet" type="text/css" />
	</lb:shopDetail>
  <c:choose>
   <c:when test="${sessionScope.SPRING_SECURITY_LAST_USERNAME == null}">
   </c:when>
   	<c:otherwise>
    <table width="100%" cellspacing="0" cellpadding="0" align="center" style="margin-top: 5px;" class="tables">
      <tr> 
        <td class="titlebg"><fmt:message key="order.detail.hint"/></font>
        </td>
      </tr>
      <tr> 
        <td>
   
        	  <c:forEach items="${requestScope.subList}" var="sub">
        	  <form action="${pageContext.request.contextPath}/payment/payto" method="post" target="_blank">
        	    <input type="hidden" name="subNumber" id="subNumber" value="${sub.subNumber}" />
        	    <input type="hidden" name="payTypeId" id="payTypeId" value="3"/> <!-- default 货到付款 -->
        	  	<input type="hidden" name="others" id="others" value="${member.other}" />
        	  <table class="tables" style="margin: 5px" width="99%">
        	  	<tr align="left"  bgcolor="#ECECEC" >
        	  		<td>
        	  		<div style="margin-top: 5px;margin-bottom: 5px;margin-left: 30px">
        	  			<fmt:message key="shop.name"/>：<span style="color: red;font-weight: bold;"><a href="<ls:domain shopName='${sub.shopName}' />">${sub.shopName}</a></span> &nbsp;
        	  			<fmt:message key="order.number"/>：<b>${sub.subNumber}</b> &nbsp;
        	  		     <fmt:message key="dateStr"/>：<fmt:formatDate value="${sub.subDate}" pattern="yyyy-MM-dd HH:mm"/> &nbsp;
        	  			<fmt:message key="Order.Status"/>：         		
        	  			<span style="color: #666;font-weight: bold;"><ls:optionGroup type="label" required="true" cache="true"
	                      beanName="ORDER_STATUS" selectedValue="${sub.status}"/></span>&nbsp;
        	  			</div>
        	  		</td>
        	  	</tr>
        	  	<tr><td>
        	  		 	<table width="100%">
        	  		 	<tr><td><fmt:message key="picture.hint"/></td><td><fmt:message key="product.name"/></td><td width="30px"><fmt:message key="product.number"/></td><td><fmt:message key="price.hint"/></td><td>&nbsp;</td></tr>
        	  		 	<c:forEach items="${sub.basket}" var="basket">
        	  		 		<tr>
        	  		 			<td>
        	  		 				<img src="<ls:images item='${basket.pic}' scale='2'/>" width="100px" height="100px" style="border-collapse: collapse;margin: 1px;border: 1px solid #CCCCCC;"/>
        	  		 				</td>
        	  		 			<td><a target="_blank" href="${pageContext.request.contextPath}/views/${basket.prodId}"><font color="#FF0000">${basket.prodName}</font></a></td>
        	  		 			<td>${basket.basketCount}</td>
        	  		 			<td><fmt:formatNumber type="currency" value="${basket.cash}" pattern="${CURRENCY_PATTERN}"/>
        	  		 			  <c:if test="${basket.carriage != null}">
                  					(<fmt:message key="carriage.charge"/><fmt:formatNumber type="currency" value="${basket.carriage}" pattern="${CURRENCY_PATTERN}"/>)</c:if>&nbsp;&nbsp;</td>
        	  		 			<td>${basket.attribute}</td>
        	  		 		</tr>
        	  		 		 </c:forEach>
        	  		 	</table>
        	  		
        	  	</td></tr>
        	  	<tr align="left"><td>
        	  	<table  style="margin-left: 30px">
        	  	<tr height="30px"><td>
        	  	<fmt:message key="total.charge"/>
        	  	<span style="color: red;font-weight: bold;font-size: 12pt"><fmt:formatNumber type="currency" value="${sub.actualTotal}" pattern="${CURRENCY_PATTERN}"/></span> &nbsp; 
        	  	<c:if test="${sub.score ne null}"><fmt:message key="score.use"/>：${sub.score}</c:if>
        	  	<%
        	  		if(PropertiesUtil.getObject(SysParameterEnum.USE_SCORE, Boolean.class)){
        	  	%>
        	  	<c:if test="${sub.scoreId == null and (sub.status == 1 or sub.status == 7)}">
        	  		<fmt:message key="score.add"/>：<font style="color: red;font-weight: bold;font-size: 10pt"><fmt:formatNumber value="${sub.actualTotal}" pattern="#" type="number"/></font> &nbsp; 
        	  	</c:if>
        	  	<c:if test="${availableScore > 0 && sub.scoreId == null}">
	        	  	<fmt:message key="available.score"/>：<font style="color: red;font-weight: bold;font-size: 10pt">${availableScore}</font> &nbsp;
	        	  	<a  href='javascript:void(0)' onclick='javascript:useScore("${sub.subNumber}","${sub.subId}","${sub.total}")'><fmt:message key="score.buy"/></a> &nbsp;
        	  	</c:if>
        	  	<%} %>
        	  	</td>
        	  	</tr>
        	   <c:if test="${sub.status == 1 or sub.status == 7}"> <!-- 不是货到付款，等待客户付款状态 -->
        	  	<tr height="40px"><td>
        	  		     <fmt:message key="payType"/>    	  	      			            
			            <c:forEach items="${sub.payType}" var="payTypeInstance">
			            	<input type="button" value="${payTypeInstance.payTypeName}" id="${payTypeInstance.payTypeId}" class="s" onclick="payto(this)"/> 
			            </c:forEach>
			    </td></tr>
        	  	</c:if>
        	  	</table>
        	  	
        	  	</td></tr>
        	    </table>
        	    </form>
        	  </c:forEach>
      
	<table width="100%" cellpadding="0" cellspacing="0">
		<c:if test="${member.payTypeName != null}">
	              <tr> 
                    <td width="20%" height="25"> 
                      <div align="right"><fmt:message key="payType"/>：</div>
                    </td>
                    <td width="80%" height="25">
                      <div align="left">${member.payTypeName}</div>
                    </td>
                  </tr>
                  </c:if>
                  <tr> 
                    <td width="20%" height="25"> 
                      <div align="right"><fmt:message key="orderer"/>：</div>
                    </td>
                    <td width="80%" height="25">
                      <div align="left">${member.orderName} </div>
                    </td>
                  </tr>
                  <tr> 
                    <td  height="25"> 
                      <div align="right"><fmt:message key="address"/>：</div>
                    </td>
                    <td height="25"> 
                      <div align="left">${member.userAdds}</div>
                    </td>
                  </tr>
                  <tr> 
                    <td height="25"> 
                      <div align="right"><fmt:message key="postcode"/>：</div>
                    </td>
                    <td  height="25"> 
                      <div align="left">${member.userPostcode}</div>
                    </td>
                  </tr>
                  <tr> 
                    <td  height="25"> 
                      <div align="right"><fmt:message key="Phone"/>：</div>
                    </td>
                    <td  height="25"> 
                      <div align="left">${member.userTel}</div>
                    </td>
                  </tr>
                  <tr>
                    <td  height="12">
                      <div align="right"><fmt:message key="memo"/>：</div>
                    </td>
                    <td  height="25"> 
                      <div align="left">${member.other}</div>
                    </td>
                  </tr>             
                  <tr> 
                    <td colspan="2" height="21"> 
                      <table width="90%" cellspacing="0" cellpadding="0" align="center">
                        <tr> 
                          <td> 
                            <p style="word-spacing: 2; line-height: 150%; margin-top: 4; margin-bottom: 6"> 
                              <font color="#FF9900"><fmt:message key="after.order"/></font></td>
                        </tr>
                      </table>
                    </td>
                  </tr> 
                </table>
                </td>
      </tr>
    </table>
    	</c:otherwise>
</c:choose>
<script type="text/javascript">
	function payto(obj){
		var payForm = obj.form;
		var subNumber = payForm["subNumber"].value;
		var payTypeId =  obj.id;
		payForm["payTypeId"].value = payTypeId;
		if(obj.id != 'PGA'){
			payForm.submit();
		}else{
			alert('<fmt:message key="purchase.finished"/>');
			<!-- 7： 货到付款  -->
			updateSubStatus(subNumber,payTypeId,7);
			window.location="${pageContext.request.contextPath}/p/order";
		}
		
	}
	
	function updateSubStatus(subNumber,payTypeId,status) {
	     var data ={"subNumber": subNumber, "status": status,"payTypeId":payTypeId};
		    jQuery.ajax({
				url: "${pageContext.request.contextPath}/p/updateSubStatus", 
				type:'post', 
				data:data,
				async : false, //默认为true 异步   
			    dataType : 'json', 
				error: function(jqXHR, textStatus, errorThrown) {
				},
				success:function(retData){
				}
		});
	}
	
	function useScore(subNumber,subId,total){
		var score = ${availableScore};
		var scoreCash;
	    var data = {
                	"score": score
                };
		    jQuery.ajax({
				url: "${pageContext.request.contextPath}/p/calMoneySocre", 
				type:'post', 
				data:data,
				async : false, //默认为true 异步   
			    dataType : 'json', 
				error: function(jqXHR, textStatus, errorThrown) {
				},
				success:function(retData){
						scoreCash = retData;
				}
				});
	    if(scoreCash > total){
	    	scoreCash = total;
	    }
	    
	    
		if(confirm('<fmt:message key="score.use.confirm"><fmt:param value="' +scoreCash + '"/></fmt:message>')){
			var userScoreData = {
                	"subId": subId, "score": score
                };
		    jQuery.ajax({
				url: "${pageContext.request.contextPath}/p/userScore", 
				type:'post', 
				data:userScoreData,
				async : false, //默认为true 异步   
			    dataType : 'json', 
				error: function(jqXHR, textStatus, errorThrown) {
				},
				success:function(retData){
						window.location.href ='/p/orderDetail/'+subNumber;
				}
				});
		}
	}
</script>