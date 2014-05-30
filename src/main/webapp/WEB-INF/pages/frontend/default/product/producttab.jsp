<%@ page language="java" pageEncoding="UTF-8"%>
	<lb:shopDetail var="shopDetail" />	
<link rel="stylesheet" type="text/css" media='screen' href="<ls:templateResource item='/common/default/css/productTab.css'/>" />
<script type="text/javascript" language="javascript" src="<ls:templateResource item='/common/default/js/productTab.js'/>"></script>
<DIV id="con">
	<UL id="tags">
	  <LI class="selectTag">
	 	 <A onClick="selectTag('tagContent0',this)" href="javascript:void(0)"><fmt:message key="prod_content"/></A>
	  </LI>
	  <LI>
		<A onClick="selectTag('tagContent1',this)" href="javascript:void(0)"><fmt:message key="product.parameter"/></A>
	 </LI> 
	   <LI>
	  	<A onClick="selectTag('tagContent2',this)" href="javascript:void(0)"><fmt:message key="product.comment"/></A> 
	  </LI>
	  <c:if test="${requestScope.productList != null && fn:length(requestScope.productList) > 0}">
	  <LI>
	  	<A onClick="selectTag('tagContent3',this)" href="javascript:void(0)"><fmt:message key="related.products"/></A> 
	  </LI>
	  </c:if>
	</UL>
	<DIV id="tagContent">
		<DIV class="tagContent selectTag" id="tagContent0">
			 ${prod.content}
			 <br></br>
			 ${shopDetail.detailDesc}
			  <br/>
		</DIV> 
		<DIV class="tagContent" id="tagContent1">
		<div id="prodParameter" name="prodParameter"></div>
			<br/><br/>
			 ${shopDetail.detailDesc}
			  <br/>
		</DIV>
		<DIV class="tagContent" id="tagContent2">
		<div id="productComment" name="productComment"></div>
				<%@ include file="productcomment.jsp"%>
		</DIV>
		<c:if test="${requestScope.productList != null && fn:length(requestScope.productList) > 0}">
		<div class="tagContent" id="tagContent3">
		<table width=100%" cellpadding="0" cellspacing="10" align="left">
		<tr>
			<c:forEach items="${requestScope.productList}" var="prod" varStatus="status">
				<td width="33%" align="left">
					<c:choose>
						<c:when test="${fn:length(prod.name) > 30}">
						<span>
							<a href="<ls:url address='/views/${prod.prodId}'/>" >
							<img src="<ls:images item='${prod.pic}' scale='1'/>" width="150px" height="150px"
									title="${prod.name}" id="pic"></a></span><br>${fn:substring(prod.name,0,30)}...<br>
									
						</c:when>
						<c:otherwise>
						<span>
				     <a href="<ls:url address='/views/${prod.prodId}'/>">
				     <img src="<ls:images item='${prod.pic}' scale='1'/>" width="150px" height="150px" id="pic"></a>
				     </span><br>${prod.name}<br>
						</c:otherwise>
					</c:choose>
					<c:if test="${not empty prod.cash}">
						<fmt:message key="prod_cash" /><font color="red"><fmt:formatNumber
								type="currency" value="${prod.cash}" pattern="${CURRENCY_PATTERN}"/></font>
					</c:if>
				</td>
				<c:if test="${(status.index+1)%3==0&&(status.index+1)>=3}">
					</tr>
					<tr>
				</c:if>
			</c:forEach>
		</tr>
	</table>

   <table>
   	<tr><td></td></tr>
   </table>

		</div>
		</c:if>
	</DIV>
</DIV>
  <SCRIPT type=text/javascript>
	function queryParameter(){
           	$.ajax({
							url: "${pageContext.request.contextPath}/dynamic/queryDynamicParameter", 
							data: {"prodId": ${prod.prodId}},
							type:'post', 
							async : true, //默认为true 异步   
							dataType : 'json', 
							error:function(data){
							},   
							success:function(data){
								 document.getElementById("prodParameter").innerHTML =data;
							}   
			});    
		 }
		
function getProductComment(){
		loadProdComment(${prod.prodId}, "1");
		}
		
  	function pager(curPageNO){
           loadProdComment(${prod.prodId}, curPageNO);
		}
		
		function loadProdComment(prodId, curPageNO){
		var data = {"prodId":prodId, "curPageNO": curPageNO};
					jQuery.ajax({
							url: "${pageContext.request.contextPath}/productcomment/load", 
							data: data,
							type:'post', 
							async : true, //默认为true 异步   
							dataType : 'json', 
							error:function(data){
							},   
							success:function(data){
								 document.getElementById("productComment").innerHTML =data;
							}   
			});  
		}
</SCRIPT>


