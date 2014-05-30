<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<html>
<head>
<%@include file='/WEB-INF/pages/common/common.jsp'%>
<lb:shopDetail var="shopDetail" >
    <link href="<ls:templateResource item='/common/style/style_${shopDetail.frontEndStyle}.css'/>" rel="stylesheet" type="text/css" />
    <link href="<ls:templateResource item='/common/style/global_${shopDetail.frontEndStyle}.css'/>" rel="stylesheet" type="text/css" />
</lb:shopDetail>
<title>
<c:choose>
   <c:when test="${sessionScope.SPRING_SECURITY_LAST_USERNAME != null}">
    ${sessionScope.SPRING_SECURITY_LAST_USERNAME} <fmt:message key="shopingCar"/>
   </c:when>
   <c:otherwise>
    <fmt:message key="shopingCar"/>
   </c:otherwise>
</c:choose>
</title>
	<style type="text/css" media="all">
       tr.tableRowEven,tr.even {
		background-color: #f5f5f5
	}
    </style>
</head> 
<body topmargin="0">
   	<c:forEach items="${requestScope.USER_REG_ADV_950}" var="adv">
<table width="100%" cellpadding="0" cellspacing="0" style="margin-bottom: 5px" class="picstyle" >
<tr><td><a href="${adv.linkUrl}"><img src="<ls:photo item='${adv.picUrl}'/>" title="${adv.title}" width="950px"/></a></td></tr>
 </table>
</c:forEach>
<table width="100%" class="tables" cellpadding="0" cellspacing="0"  id="col1">
  <tr> 
    <td class="titlebg"><fmt:message key="product.subscribed.list"/></td>    
  </tr>
  <tr><td><jsp:include flush="true" page="/p/bought"></jsp:include></td></tr>
 </table>
  <script type="text/javascript">
<!--
     jQuery(document).ready(function(){
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
          });
//-->
</script>
</body>
</html>