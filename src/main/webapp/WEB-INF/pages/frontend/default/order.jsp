<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
	<link href="${pageContext.request.contextPath}/common/css/css.css" rel="stylesheet" type="text/css" />
	<lb:shopDetail var="shopDetail" >
	    <link href="<ls:templateResource item='/common/style/style_${shopDetail.frontEndStyle}.css'/>" rel="stylesheet" type="text/css" />
	    <link href="<ls:templateResource item='/common/style/global_${shopDetail.frontEndStyle}.css'/>" rel="stylesheet" type="text/css" />
	</lb:shopDetail>
	<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
	<style type="text/css" media="all">
       tr.tableRowEven,tr.even {
		background-color: #f5f5f5
	}
    </style>
<%
 Integer offset = (Integer) request.getAttribute("offset");
%>
<c:if test="${sessionScope.SPRING_SECURITY_LAST_USERNAME !=null}">
    <c:forEach items="${requestScope.USER_REG_ADV_950}" var="adv">
		<table width="100%" cellpadding="0" cellspacing="0" style="margin-bottom: 4px; margin-right: 5px;" class="picstyle">
			<tr><td><a href="${adv.linkUrl}"><img src="<ls:photo item='${adv.picUrl}'/>" title="${adv.title}" width="100%"/></a></td></tr>
		 </table>
	</c:forEach>
	
	<table class="tables" width="100%" cellpadding="0" cellspacing="0">
	<form action="${pageContext.request.contextPath}/p/order" id="orderForm" name="orderForm" method="post">
      <tr>
        <td class="titlebgnormal" align="left" valign="middle">&nbsp;
         <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}"/>
         <input type="hidden" name="subCheck" id="subCheck" value="N"/>
        <a href="javascript:void(0)" onclick="changetab(1);"  id="processingbutton" name="processingbutton">
            <fmt:message key="order.processing"/>
        </a>
        <a href="javascript:void(0)" onclick="changetab(2);" id="processedbutton" name="processedbutton"><fmt:message key="order.processed"/></a>
        &nbsp;<fmt:message key="order.number"/>：<input type="text" value="${subForm.subNumber}" maxlength="30" name="subNumber" id="subNumber" class="input2" size="30"/>
               &nbsp; <fmt:message key="Order.Status"/>：
                	<select id="status" name="status" style="height: 1.50em;">
        				<ls:optionGroup type="select" required="false" cache="true"
	                		beanName="ORDER_STATUS" selectedValue="${subForm.status}"/>
			     </select>
		<input type="submit" value="<fmt:message key="search"/>" class="s"/>
        </td>
      </tr>
       </form>
        <tr>
        <td valign="top" >
        <c:choose>
		<c:when test="${requestScope.map != null && fn:length(requestScope.map) > 0}">
          <c:forEach items="${requestScope.map}" var="subList">
				<table class="tables" style="margin: 5px" width="99%" border='0'>
        	  	<tr align="left"  bgcolor="#ECECEC" >
        	  		<td>
        	  			<div style="margin-top: 5px;margin-bottom: 5px;margin-left: 5px">
        	  				<fmt:message key="shop.name"/>：<span style="color: red;font-weight: bold;"><a href="<ls:domain shopName='${subList.key}' />">${subList.key}</a></span> &nbsp;
        	  			</div>
        	  		</td>
        	  	</tr>
        	  
        	  	<tr><td>
        	  		 	<!-- 订单内容 -->
        	  		<table width="100%"  cellpadding="0" cellspacing="0"   id="col1" border ="0" style="text-align: left;">
        	  		 	<tr>
        	  		 	    <td width="150px">订单号</td>
	        	  		 	<td width="220px"><fmt:message key="picture.hint"/></td>
	        	  		 	<td>收货人</td>
	        	  		 	<td width="100px">订单金额</td>
	        	  		 	<td>时间</td>
	        	  		 	<td>状态</td>
	        	  		 	<td>操作</td>
        	  		 	</tr>
        	  		 		<c:forEach items="${subList.value}" var="sub">
        	  		 		<tr>
        	  		 		<td><div style="padding: 5px;"><a href='${pageContext.request.contextPath}/p/orderDetail/${sub.subNumber}' target="_blank">${sub.subNumber}</a></div></td>
        	  		 			<td>
        	  		 			 	<c:forEach items="${sub.basketList}" var="basket">
		        	  		 			<a target="_blank" href="${pageContext.request.contextPath}/views/${basket.prodId}">
		        	  		 				<img src="<ls:images item='${basket.pic}' scale='3'/>" width="65px" height="65px" style="border-collapse: collapse;margin: 1px;border: 1px solid #CCCCCC;" title="${basket.prodName}"/>
		        	  		 			</a>
		        	  		 			</c:forEach>
        	  		 				</td>
        	  		 			<td>${sub.orderName}</td>
        	  		 			<td style="color: #FF4400;font-weight: 700"><fmt:formatNumber type="currency" value="${sub.subTotal}" pattern="${CURRENCY_PATTERN}"/></td>
        	  		 			<td><fmt:formatDate value="${sub.subDate}" pattern="yyyy-MM-dd HH:mm" /></td>
        	  		 			<td>       		
        	  		 				<ls:optionGroup type="label" required="true" cache="true" beanName="ORDER_STATUS" selectedValue="${sub.status}"/>
	                			</td>
	                			<td>
	                			        <c:if test="${sub.status == 1 or sub.status == 7}">
								        <a href='${pageContext.request.contextPath}/p/orderDetail/${sub.subNumber}' target="_blank"><fmt:message key="payment.hint"/></a> 
								        </c:if>
								       
								         <c:if test="${sub.status == 3}"> <!-- 3:卖家已经发货 -->
								             <!-- 4:交易成功 -->
								         	<a href="javascript:void(0)" onclick='javascript:updateSubStatus("${sub.subId}",4,"${sub.subNumber}");'><fmt:message key="ensure.recieve"/></a>
								         	 <!-- 6:退款中的订单 -->
								            <a href="javascript:void(0)" onclick='javascript:updateSubStatus("${sub.subId}",6,"${sub.subNumber}");'><fmt:message key="apply.for.refundment"/></a>
								         </c:if>
								        <c:if test="${sub.status == 1 or sub.status == 7}">
								        	<a href='javascript:deleteSub("${sub.subId}","${sub.subNumber}");'><fmt:message key="delete"/></a>
								        </c:if>
	                			</td>
        	  		 		</tr>
        	  		 		 </c:forEach>
        	  		 		 	
        	  		 	</table>
        	  	</td></tr>
			
        	  	</table>
        	  	</c:forEach>
        	  	</c:when>
        	  	<c:otherwise>
				<!-- 我的订单列表为空 -->
					<tr style="background-color: white; text-align: left;">
						<td><div style="margin-left: 200px;"><img src="${pageContext.request.contextPath}/common/default/images/basket.png" /><b>您的订单还是空的，赶紧行动吧！</b></div></td>
					</tr>
				</c:otherwise>
        	  	</c:choose>
        	  	</td>
        	 </tr>
      <tr>     
        <td align="right"  style="padding: 5px;"><c:out value="${toolBar}" escapeXml="${toolBar}"></c:out></td>
      </tr>
    </table>
    <script type=text/javascript>
	  function submitOrderForm(subCheck){
            document.getElementById("subCheck").value= subCheck;
            document.getElementById("orderForm").submit();
        }
	
	function changetab(a){
		if(a==1){
			document.getElementById('processingbutton').className='selected';
			document.getElementById('processedbutton').className='';
			submitOrderForm("N");
		}else{
			document.getElementById('processedbutton').className='selected';
			document.getElementById('processingbutton').className='';
			submitOrderForm("Y");
		}
	}
	
 	
   function pager(curPageNO){
			document.getElementById("curPageNO").value=curPageNO;
			document.getElementById("orderForm").submit();
	}
        
function deleteSub(subId,subNumber) {
	  if(confirm('<fmt:message key="delete.order"/> '+subNumber+' ?')){
	  $.post("${pageContext.request.contextPath}/p/deleteSub" , 
	 {"subId": subId},
        function(retData) {
	       if(retData == null ){
	          window.location.href = "${pageContext.request.contextPath}/p/order" ;
	       }else{
	          alert('<fmt:message key="entity.deleted"/>') ;
	       }
		},'json');
    }
}

     function updateSubStatus(subId,status,subNumber) {
	  if(confirm('<fmt:message key="confirm.order"/> '+subNumber+' ?')){
	$.post("${pageContext.request.contextPath}/p/updateSubStatus" , 
	 {"subNumber": subNumber, "status": status},
        function(retData) {
	       if(retData == null ){
	           window.location.reload() ;
	       }else{
	          alert('<fmt:message key="operation.fail"/>' + retData) ;
	       }   
		},'json');
    }
}	

	
 	function onloadSetup(){
		if(${subForm.subCheck == 'Y'}){
			document.getElementById('processedbutton').className='selected';
			document.getElementById('processingbutton').className='';
			document.getElementById("subCheck").value= 'Y';
		}else{
			document.getElementById('processingbutton').className='selected';
			document.getElementById('processedbutton').className='';
			document.getElementById("subCheck").value= 'N';
		}
	}
	
	    $(document).ready(function() {
	      $("#col1 tr").each(function(i){
	      if(i>0){
	         if(i%2 == 0){
	             $(this).addClass('even');
	         }else{    
	              $(this).addClass('odd'); 
	         }   
	    }
	     });   
	         $("#col1 th").addClass('sortable'); 
	         onloadSetup();
	});

</script>
</c:if>