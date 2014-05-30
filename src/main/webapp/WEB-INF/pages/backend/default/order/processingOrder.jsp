<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>

<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
     <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
	 <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
	 <%@ include file="/WEB-INF/pages/common/dialog.jsp"%>
<%
 Integer offset = (Integer) request.getAttribute("offset");
%>
    <table class="${tableclass}" style="width: 100%;">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 
    	<a href="${pageContext.request.contextPath}/admin/order/processing">订单管理</a> &raquo; 未处理订单
    	</th></tr>
    </thead>
     <tbody><tr><td>
     <form action="${pageContext.request.contextPath}/admin/order/processing" id="processingOrderForm" name="processingOrderForm" method="post" style="margin: 0px">
              <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}"/> 
 <div align="left" style="padding: 3px">
               &nbsp; 订单号&nbsp;<input type="text" name="subNumber" id="subNumber" value="${subForm.subNumber}" size="30" maxlength="30"/>
               &nbsp; 买家&nbsp;<input type="text" name="userName" id="userName" value="${subForm.userName}"/>
               <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
               &nbsp; 商城&nbsp;<input type="text" name="shopName" id="shopName" value="${subForm.shopName}"/>
               </auth:auth>
                &nbsp; 状态&nbsp;
                	<select id="status" name="status"  class="input">
        				<ls:optionGroup type="select" required="false" cache="true"
	                		beanName="ORDER_STATUS" selectedValue="${subForm.status}" exclude="4,5"/>
			     </select>
        <input type="submit" value="搜索" class="s"/>
           </form>
 </div>
 </td></tr></tbody>
    </table>
<table style="width:100%" class="${tableclass}"> 
      <tr>
        <td valign="top">
   <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="${pageContext.request.contextPath}/admin/order/processing" id="item"
         export="true"  class="${tableclass}" style="width:100%" sort="external">
      <display:column title="顺序"  class="orderwidth"><%=offset++%></display:column>
      <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
	     <display:column title="商城" property="shopName" sortable="true" sortName="shopName" style="width: 100px">
	     	<a href="<ls:domain shopName='${item.shopName}'  />" target="_blank">${item.shopName}</a>
	     </display:column>
     </auth:auth>
      <display:column title="订单号" sortable="true" sortName="subNumber">
		<a href='${pageContext.request.contextPath}/p/orderDetail/${item.subNumber}' target="_blank">${item.subNumber}</a>
      </display:column>
      <display:column title="价格" sortable="true" sortName="total">
      	<b><fmt:formatNumber type="currency" value="${item.total}" pattern="${CURRENCY_PATTERN}"/></b>
      </display:column>
       <display:column title="实收价格" sortable="true" sortName="actualTotal">
      	<b><fmt:formatNumber type="currency" value="${item.actualTotal}" pattern="${CURRENCY_PATTERN}"/></b>
      </display:column>
      <display:column title="商品" property="prodName" sortable="true" sortName="prodName"></display:column>
      <display:column title="买家帐号" sortable="true" sortName="userName" style="width: 100px">${item.userName}</display:column>
       <display:column title="状态" sortable="true" sortName="status" style="width: 80px">
       	       <ls:optionGroup type="label" required="true" cache="true"
	                beanName="ORDER_STATUS" selectedValue="${item.status}"/>
       </display:column>      
      <auth:auth ifAnyGranted="FA_SALE">
	     <display:column title="操作" media="html" style="width:110px">
       <a  href="javascript:void(0)" onclick='javascript:orderList.orderDetail("${item.subNumber}");'>详情</a>
        <c:if test="${item.status == 1}"> <!-- 1: 等待买家付款 -->
        	<a href="javascript:void(0)" onclick='javascript:orderList2.modifyPrice("${item.total}", "${item.subId}", "${item.subNumber}");'>修改</a>
       	</c:if>
       <c:if test="${item.status == 2}"> <!-- 2:买家已经付款 -->
             <!-- 4:交易成功 -->
         	<a href="javascript:void(0)" onclick='javascript:updateSubStatus("${item.subId}",3,"${item.subNumber}");'>发货</a>
         </c:if>
         <c:if test="${item.payTypeId == 'PGA' &&( item.status != 3 && item.status != 6)}"> <!-- 3:货到付款,没有退货又没有发货的 -->
         	<a href="javascript:void(0)" onclick='javascript:updateSubStatus("${item.subId}",3,"${item.subNumber}");'>发货</a>
         </c:if>
		<c:if test="${item.status == 6}"><!-- 6:退款中的订单 -->
					<!-- 5:交易关闭 -->
					<a href="javascript:void(0)" onclick='javascript:updateSubStatus("${item.subId}",5,"${item.subNumber}");'>同意退货</a>
		</c:if>         
          <!-- 商家不可主动删除用户的订单，如果用户一直没有支付，系统在30天后自动housekeep-->
	      </display:column>
      </auth:auth>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>   
        </td>
      </tr>

    </table>
       说明：<br>
		   1. 用户支付之后需要点击发货按钮，用户确认收货，整个订单完成，进入历史订单状态<br>
		   2. 用户申请退款，在退款之后点击关闭交易，订单完成。<br>
  		
   <script>

     function updateSubStatus(subId,status,subNumber) {
	  if(confirm('确认订单 '+subNumber+' ?')){
	 $.post("${pageContext.request.contextPath}/p/updateSubStatus" , 
	 {"subNumber": subNumber, "status": status},
        function(retData) {
	       if(retData == null ){
	           window.location.href='${pageContext.request.contextPath}/admin/order/processing';
	       }else{
	          alert('更新订单失败，订单号 ' + subNumber) ;
	       }   
		},'json');
    }
}


   function pager(curPageNO){
			document.getElementById("curPageNO").value=curPageNO;
			document.getElementById("processingOrderForm").submit();
	}
	
	var orderList={
       orderDetail:function(subNumber){ 
           var url="${pageContext.request.contextPath}/admin/order/loadBySubnumber/" + subNumber;
		    var options={id:"orderDetail",title:"订单详情",width:720,height:480,lock:false,closeFn: function(){} };
		    art.dialog.open(url,options);
       }
         };
         
 	var orderList2={
       modifyPrice:function(total, subId, subNumber){ 
           var url="${pageContext.request.contextPath}/admin/order/modifyPrice?total=" +total + "&subId=" + subId + "&subNumber=" + subNumber;
		    var options={id:"orderDetail",title:"修改价格",width:330,height:150,lock:false,close: function(){} };
		    art.dialog.open(url,options);
       }
         };        
         
	highlightTableRows("item");  
</script>