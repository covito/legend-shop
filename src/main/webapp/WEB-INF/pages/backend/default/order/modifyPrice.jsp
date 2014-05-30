<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
<head>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
		<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/common/js/randomimage.js"></script>
		<script src="${pageContext.request.contextPath}/common/default/js/productDetail.js"></script>
	  <%@ include file="/WEB-INF/pages/common/dialog.jsp"%>
<title>修改价格</title>
</head>
<body>
	        <input type="hidden" id="rand" name="rand"/>
			<input type="hidden" id="cannonull" name="cannonull" value='<fmt:message key="randomimage.errors.required"/>'/>
			<input type="hidden" id="charactors4" name="charactors4" value='<ls:i18n key="randomimage.charactors.required" length="4"/>'/>
			<input type="hidden" id="errorImage" name="errorImage" value='<fmt:message key="error.image.validation"/>'/>
         订单<b>${param.subNumber}</b><br>
         原价<b><fmt:formatNumber type="currency" value="${param.total}" pattern="${CURRENCY_PATTERN}"/></b><br>
		最新价格：<input type="text" id="price" name="price"/>
		<input name="submit" type="button" value='确认' class="s"  height="30px" onclick="javascript:changePrice();">
		<!-- 
		<br>
		<img id="randImage" name="randImage" src="<ls:templateResource item='/captcha.svl'/>"/>
		<input type="text" id="randNum" name="randNum" class="inputbutton2" maxlength="7" size="7" tabindex="3" >
		 &nbsp;<a href="javascript:void(0)" onclick="javascript:changeRandImg('${pageContext.request.contextPath}')" style="font-weight: bold;"><fmt:message key="change.random2"/></a>
		 <br>
		  -->
<script type="text/javascript">	
    function changePrice(total,subId,subNumber) {
   	var price = document.getElementById("price");
   	
   	if(price.value == null || price.value == "" || !isNumber(price.value) || price.value < 0){
   		price.focus();
   		alert("请输入正确的价格");
   		return;
   	}
 //&& validateRandNum('${pageContext.request.contextPath}')
   	if(price.value!= null && price.value != "null" ){
   	      var subId = '${param.subId}';
		 $.post("${pageContext.request.contextPath}/admin/order/adminChangeSubPrice" , 
		 {"subId": subId, "totalPrice": price.value},
	        function(retData) {
		       if(retData == 'OK' ){
		       	 art.dialog.close();
		         var win = art.dialog.open.origin;//来源页面
   				 win.location.reload();
		       }else{
		          alert('更新价格失败，订单号 ' + subNumber) ;
		       }

			},'json');
	   	}

   }
	</script>
</body>
</html>