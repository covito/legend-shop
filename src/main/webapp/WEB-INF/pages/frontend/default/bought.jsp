<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
<script src="<ls:templateResource item='/common/js/jquery.formatCurrency-1.4.0.min.js'/>" type="text/javascript"></script>
<script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/default/css/bought.css" type="text/css" media="all" />
<lb:shopDetail var="shopDetail" >
    <link href="<ls:templateResource item='/common/style/style_${shopDetail.frontEndStyle}.css'/>" rel="stylesheet" type="text/css" />
    <link href="<ls:templateResource item='/common/style/global_${shopDetail.frontEndStyle}.css'/>" rel="stylesheet" type="text/css" />
</lb:shopDetail>
<form id="cashAction" action="${pageContext.request.contextPath}/p/cash" method="post">
		<table width="100%" cellpadding="0" cellspacing="0" >
		<c:choose>
			<c:when test="${requestScope.baskets != null && fn:length(requestScope.baskets) > 0}">
		<thead>
			<tr style="font-weight: bold">
				<td width="70px">全选<input type='checkbox'  id='checkbox' name='checkbox' onClick='javascript:selAll()'  checked="checked"/></td>
				<td width="42%" height="25"><fmt:message key="product.name" /></td>
				<td width="12%"><fmt:message key="product.price" /></td>
				<td width="12%"><fmt:message key="product.attribute" /></td>
				<td width="8%"><fmt:message key="product.number" /></td>
				<td width="150px"><fmt:message key="price.total" /></td>
				<td width="100px"><fmt:message key="operation"/></td>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${requestScope.baskets}" var="basket">
				<tr class="item-content">
					<td><input type="checkbox" name="strArray" value="${basket.basketId}" arg="${basket.prodId}" checked="checked"/></td>
					<td>
					<!-- 商品 -->
					<div class="item-pic J_ItemPic img-loaded" >
						<a  target="_blank"  href="${pageContext.request.contextPath}/views/${basket.prodId}"  title="${basket.prodName}" >
					      		<img src="<ls:images item='${basket.pic}' scale='2'/>"  width="100px" height="100px" />
					      </a>
				 	</div>
				 	<c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
					<div class="item-title">店铺：<a href="<ls:domain shopName='${basket.shopName}' />">${basket.shopName}</a></div>
					</c:if>
					<div class="item-title"><a target="_blank" href="${pageContext.request.contextPath}/views/${basket.prodId}">${basket.prodName}</a></div>
					</td>
					<td>
					<fmt:formatNumber type="currency" value="${basket.cash}" pattern="${CURRENCY_PATTERN}"/>
					<input id="cash${basket.basketId}" name="cash${basket.basketId}"  type="hidden" value="${basket.cash}">
					</td>
					<td>${basket.attribute}</div>
					</td>
					<td>
					<div class="item-amount" value="${basket.basketId}">
						<a class="J_Minus minus" >-</a>
							<input type="text"  class="text text-amount"  id="basketCount${basket.basketId}"  name="basketCount${basket.basketId}"  value="${basket.basketCount}">
						<a class="J_Plus plus" >+</a>
						</div>
					<div class="amount-msg J_AmountMsg"></div>
					</td>
					<td class="td-sum">
					<div id="currencyLabel"><fmt:formatNumber type="currency" value="${basket.total}" pattern="${CURRENCY_PATTERN}"/></div>
					<input  class="amount"  type="hidden" value="${basket.total}">
					<c:if test="${basket.carriage != null && basket.carriage > 0}">
                		  <fmt:message key="carriage.charge"/><fmt:formatNumber type="currency" value="${basket.carriage}" pattern="${CURRENCY_PATTERN}"/>
                		  <input  class="amount"  type="hidden" value="${basket.carriage}">
                    </c:if>
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/p/clear?basketId=${basket.basketId}"
							title='<fmt:message key="product.name.delete"/>'><fmt:message key="delete"/></a>
					</td>
				</tr>
			</c:forEach>
			<tr>
					<td height="25" colspan="8">
						<div align="right" style="margin-right: 86px;">
							<b><fmt:message key="price.total" />
									<span id="totalCurrencyLabel">
										<fmt:formatNumber type="currency" value="${requestScope.totalcash}" pattern="${CURRENCY_PATTERN}"/>
									</span>
							</b>
						</div>
					</td>
			</tr>
			<tr>
			<td colspan="8">
						<div style="margin: 3px">
							<a href="${pageContext.request.contextPath}/p/buy" onMouseOver="btnMouseOver('mbtn0')"
								onMouseOut="btnMouseOut('mbtn0')"
								onMouseDown="btnMouseDown('mbtn0')"> <img name="mbtn0" />
							</a>
							<a href="${pageContext.request.contextPath}/p/clear" onMouseOver="btnMouseOver('mbtn1')"
								onMouseOut="btnMouseOut('mbtn1')"
								onMouseDown="btnMouseDown('mbtn1')"> <img name="mbtn1" />
							</a>
							<a onMouseOver="btnMouseOver('mbtn2')"
								onMouseOut="btnMouseOut('mbtn2')"
								onMouseDown="btnMouseDown('mbtn2')" style="cursor: pointer;"> <img name="mbtn2"  onclick="javascript:submitOrderAction()"/>
							</a>
						</div>
				<script type="text/javascript">
						<!--
						var btnCount = 3;
						var staCount = 3;
						var btnImages = new Array();
						for (i= 0; i< btnCount; i++)
						{
						    btnImages[i] = new Array();
						    for (j= 0; j< staCount; j++)
						    {
						        btnImages[i][j] = new Image();
						        btnImages[i][j].src = '${pageContext.request.contextPath}<fmt:message key="img.btn"/>' + i + '_' + j + '.gif';
						    }
						
						}
						    document.images['mbtn0'].src = '${pageContext.request.contextPath}<fmt:message key="img.btn"/>' + '0_0.gif';
						    document.images['mbtn1'].src = '${pageContext.request.contextPath}<fmt:message key="img.btn"/>' + '1_0.gif';
						    document.images['mbtn2'].src = '${pageContext.request.contextPath}<fmt:message key="img.btn"/>' + '2_0.gif';
						    
						function btnMouseOut(img)
						{
						    document.images[img].src = btnImages[img.substring(img.indexOf('mbtn')+4,img.length)][0].src;
						};
						function btnMouseOver(img)
						{
						    document.images[img].src = btnImages[img.substring(img.indexOf('mbtn')+4,img.length)][1].src;
						};
						function btnMouseDown(img)
						{
						    document.images[img].src = btnImages[img.substring(img.indexOf('mbtn')+4,img.length)][2].src;
						};
						//-->
						</script>
					</td>
			</tr>
	</tbody>
		</c:when>
				<c:otherwise>
				<!-- 购物车为空 -->
						<tr style="background-color: white; text-align: left;">
						<td style="float: right;"><img src="${pageContext.request.contextPath}/common/default/images/basket.png" /></td>
						<td><b>您的购物车还是空的，赶紧行动吧！</b></td></tr>
				</c:otherwise>
			</c:choose>
</table>
</form>
  <script type="text/javascript">
<!--
     jQuery(document).ready(function(){
    	 //初始化
     	$(".J_Minus").each(function(){
     		$this = $(this);
     		var value = $this.next().val();
     		if(value > 1){
     			$this.html("-");
     		}else{
     			$this.html("");
     		}
     	});
     	
     	//数量输入框
     	$(".text-amount").change(function(){
     		$this = $(this);
     		var value = $this.val();
     		if(isNaN(value)){//非数字
     			$this.val(1);
     		}
     		if(value > 127){
     			$this.val(127);
     		}
     		if(value < 1){
     			$this.val(1);
     		}
     		 //calculate total
     		 reCalculateTotal($this,$this.val());
     	});
     	
     	//减少数量按钮
      	$(".minus").click(function (){
      		$this = $(this);
      		$next = $this.next();
      		var value = parseFloat($next.val());
      		if(value > 1){
	      		if(value > 2){
	      			$this.html("-");
	      		}else{
	      			$this.html("");
	      		}
      			$next.val(value - 1);
      			$next.next().html("+");
      			reCalculateTotal($this,value - 1);	
      		}else{
      			$this.html("");
      		}
      	});
      	
      	//增加数量按钮
      	$(".plus").click(function (){
      		$this = $(this);
      		$prev =$this.prev();
      		var value = parseFloat($prev.val());
      		if(value >0){
      			$prev.prev().html("-");
      		}
      		if(value <127){
      			$prev.val(value +1);
      			if(value >= 126){
      				$this.html("");
      			}else{
      				$this.html("+");
      			}
      		}else{
      			$this.html("");
      		}
      		reCalculateTotal($this,value +1);
      		});
          });
          
          //重新计算总数和每个购物车的小计总数
          function reCalculateTotal($this,targetValue){
          		//每个购物车总数
         	 	var id = $this.parent().attr("value");
      			var price = $("#cash" + id).val();
	      		var amount = $this.parent().parent().next().find("#currencyLabel");
	      		var number = price * targetValue;
	      		amount.html(number);
	      		amount.formatCurrency({symbol:'￥',groupDigits:false,roundToDecimalPlace:2,region: 'zh-CN'});
	      		//alert(id + ", "+ price + ", " + targetValue +", " +amount);
	      		var amountValue = $this.parent().parent().next().find(".amount:first");
	      		amountValue.val(number);
	      		
	      		//总数
	      		var total = 0;
			     $(".amount").each(function(){
		     		$this = $(this);
		     		var value = $this.val();
		     		if(value != null){
		     			total = parseFloat(total) + parseFloat(value);
		     		}
		     	});
		     	var $totalCurrencyLabel = $("#totalCurrencyLabel");
		     	$totalCurrencyLabel.html(total);
		     	 $totalCurrencyLabel.formatCurrency({symbol:'￥',groupDigits:false,roundToDecimalPlace:2,region: 'zh-CN'});
          }
          
		function submitOrderAction(){
			//获取选择的记录集合
			selAry = document.getElementsByName("strArray");
			if(!checkSelect(selAry)){
			 window.alert('删除时至少选中一条记录！');
			 return false;
			}
			$("#cashAction").submit();
		}
//-->
</script>

  