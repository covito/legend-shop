<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.legendshop.core.helper.PropertiesUtil"%>
<%@include file='/WEB-INF/pages/common/common.jsp'%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<lb:shopDetail var="shopDetail" />
	    <link href="<ls:templateResource item='/common/style/style_${shopDetail.frontEndStyle}.css'/>" rel="stylesheet" type="text/css" />
	    <link href="<ls:templateResource item='/common/style/global_${shopDetail.frontEndStyle}.css'/>" rel="stylesheet" type="text/css" />
    <link href="<ls:templateResource item='/common/default/css/searchall.css'/>" rel="stylesheet" type="text/css" />
    <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
<title>LegendShop
<c:choose>
   <c:when test="${shopDetail != null}">
     - ${shopDetail.siteName}
   </c:when>
   <c:otherwise>
   </c:otherwise>
</c:choose>
</title>
<link rel="icon" href="<ls:templateResource item='/favicon.ico'/>" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="${shopDetail.userName},${shopDetail.briefDesc}" />
<meta name="keywords" content="${shopDetail.userName},${shopDetail.briefDesc}"/>
<meta name="keywords" content="LegendShop 网购平台, 网店系统, 商城系统, 商城系统, 电子商务系统, B2C系统, 购物系统, 网上商店系统, 网上交易系统, JAVA网店, JAVA商城, JSP网店, JSP商城, SSH项目, JAVA开源项目"/>
<script>
	function submitSearchAllTopform(entityType,keyword){
		if(document.getElementById("priceStart") && document.getElementById("priceEnd")){
		var priceStart = document.getElementById("priceStart").value;
		var pricarEnd = document.getElementById("priceEnd").value;
		if(isNaN(priceStart) || isNaN(pricarEnd)){
			alert('<fmt:message key="errors.number"><fmt:param value=""/></fmt:message>');
			return;
		}
		document.getElementById("priceStartValue").value = priceStart;
		document.getElementById("priceEndValue").value = pricarEnd;	
		}
		
		if(document.getElementById("provinceid")){
			document.getElementById("provinceidValue").value = document.getElementById("provinceid").value;
		}
		if(document.getElementById("cityid")){
			document.getElementById("cityidValue").value = document.getElementById("cityid").value;
		}
		if(document.getElementById("areaid")){
			document.getElementById("areaidValue").value = document.getElementById("areaid").value;
		}
        document.getElementById("searchAllTopform").action = "<ls:url address='/searchall'/>/" + entityType +"/"+keyword;
		document.getElementById("searchAllTopform").submit();
	}
	
		function pager(curPageNO){
		//alert(curPageNO);
			document.getElementById("curPageNO").value=curPageNO;
			submitSearchAllTopform(document.getElementById("entityType").value,document.getElementById("keyword").value);
		}
</script>
</head>
<!--  id="master" -->
<body>
<center>
<table width="954px" style="border-bottom-width: 1px;border-bottom-color: red;border: 1px">
    <tr>
    <td width="160px">
    <a href="<ls:url address='/index'/>"><img src="<ls:templateResource item='/common/images/legendshop.gif'/>" width="160px" title="LegendShop"/></a>
    </td>
     <td>
	     <table width="100%">
	     <tr><td>
	     <table width="100%">
	     <tr>
		     <td align="left">
		    <%if(PropertiesUtil.getDefaultShopName()!=null && PropertiesUtil.getDefaultShopName().length() > 0){ %>
		   		<a href="<ls:domain shopName='<%=PropertiesUtil.getDefaultShopName()%>' />"><fmt:message key="shop.index"/></a>
		   <%} %>
		    <ls:plugin pluginId="advanceSearch">
		  		 <a href="<ls:url address='/all'/>"><fmt:message key="search"/></a>
		   </ls:plugin>
		   </td>
		     <td align="right">
		     	   <c:choose>
					 <c:when test="${sessionScope.SPRING_SECURITY_LAST_USERNAME != null}">
					  <span style="color: red; font-weight: bold;">${sessionScope.SPRING_SECURITY_LAST_USERNAME}</span>
					   	<a href="<ls:url address='/p/myaccount'/>")><fmt:message key="myaccount"/></a>
					   	<a href="<ls:url address='/p/order'/>")><fmt:message key="myorder"/></a>
						<a href="${pageContext.request.contextPath}/p/logout" target="_parent"><fmt:message key="logout"/></a>
       			        <auth:auth ifAnyGranted="F_ADMIN">
       			        	<ls:myshop>
       			                <a href="<ls:domain shopName='${sessionScope.SPRING_SECURITY_LAST_USERNAME}' />"><fmt:message key="myShop"/></a>
       			           </ls:myshop>
					    </auth:auth>
					    	<auth:auth ifAnyGranted="FE_BACKEND">
					       		<a href="<ls:url address='/admin/index'/>"><fmt:message key="system.management"/></a>
					      </auth:auth>
					   </c:when>
					   <c:otherwise>
					     	<a href="<ls:url address='/login'/>"><fmt:message key="login"/></a>
				   </c:otherwise>
	    		</c:choose>
		     
	    		<a href="<ls:url address='/reg'/>"><fmt:message key="register.title"/></a> 
				<a href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club"><fmt:message key="bbs"/></a>  
             </td>
	     </tr>
	     </table>
     </td>
     </tr>
     <tr>
     <td align="left">
     <form action="<ls:url address='/searchall'/>" method="post" id="searchAllTopform">
     	<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
     	<input type="hidden" id="priceStartValue" name="priceStartValue" />
     	<input type="hidden" id="priceEndValue" name="priceEndValue"/>
     	<input type="hidden" id="entityType" name="entityType" value="${entityType}"/>
     	<input type="hidden" id="provinceidValue" name="provinceidValue"/>
     	<input type="hidden" id="cityidValue" name="cityidValue"/>
     	<input type="hidden" id="areaidValue" name="areaidValue"/>
     	
        <input type="text" value="${keyword}" id="keyword" name="keyword"  maxlength="100" size="50" class="kw" onkeydown='if(event.keyCode==13)submitSearchAllTopform(0,document.getElementById("keyword").value);'></input>
		<input type="button" value='<fmt:message key="search.product"/>' class="btn" onmousedown="this.className='btn btn_h'" onmouseout="this.className='btn'" onclick='javascrpit:submitSearchAllTopform(0,document.getElementById("keyword").value)'></input>
		<c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
			<input type="button" value='<fmt:message key="search.mall"/>' class="btn" onmousedown="this.className='btn btn_h'" onmouseout="this.className='btn'" onclick='javascrpit:submitSearchAllTopform(1,document.getElementById("keyword").value)'></input>
		</c:if>
	 </form>
		 </td></tr>
     </table>
    </td> 

    </tr>
 </table>
</center>