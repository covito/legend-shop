<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/common.jsp'%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <link href="${pageContext.request.contextPath}/common/default/css/searchall.css" rel="stylesheet" type="text/css" />
<title>
<lb:shopDetail var="shopDetail" />
<c:choose>
	<c:when test="${shopDetail != null}">
		${shopDetail.siteName}
	</c:when>
	<c:otherwise>
		LegendShop - Java企业级商城系统
	</c:otherwise>
</c:choose>
</title>
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="${shopDetail.userName},${shopDetail.briefDesc}" />
<meta name="keywords" content="${shopDetail.userName},${shopDetail.briefDesc}"/>
<meta name="keywords" content="LegendShop 网购平台, 网店系统, 商城系统, 商城系统, 电子商务系统, B2C系统, 购物系统, 网上商店系统, 网上交易系统, JAVA网店, JAVA商城, JSP网店, JSP商城, SSH项目, JAVA开源项目"/>

<script>
	function submitSearchAllform(entityType,keyword){
		//var keyword = document.getElementById("keyword").value;
        document.getElementById("searchAllform").action = "${pageContext.request.contextPath}/searchall/" + entityType +"/"+keyword;
		document.getElementById("searchAllform").submit();
	}
</script>
</head>
<body>
<table width="100%">
    <tr><td style="border-bottom-width: 1px; border-bottom-style: solid;border-bottom-color: #CCC">
    <table width="100%">
    	<tr>
    		<td align="left">
    		    <a href="${pageContext.request.contextPath}/index"><fmt:message key="shop.index"/></a>
				&nbsp;
	    		 <c:choose>
					 <c:when test="${sessionScope.SPRING_SECURITY_LAST_USERNAME != null}">
					  <span style="color: red; font-weight: bold;">${sessionScope.SPRING_SECURITY_LAST_USERNAME}</span>
					   	<a href="${pageContext.request.contextPath}/p/myaccount"><fmt:message key="myaccount"/></a>
					   	<a href="${pageContext.request.contextPath}/p/order"><fmt:message key="myorder"/></a>
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
					     	<a href="${pageContext.request.contextPath}/login"><fmt:message key="login"/></a>
				   </c:otherwise>
	    		</c:choose>
	    		<a href="${pageContext.request.contextPath}/reg"><fmt:message key="register.title"/></a> 
	    		<c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
	    			<a href="${pageContext.request.contextPath}/reg?openshop=1"><fmt:message key="register.shop"/></a>
	    		</c:if>
    		</td>
    		<td align="right">
				<a href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club"><fmt:message key="bbs"/></a>		
    	   </td>
    	</tr>
    </table>
    </td></tr>
	<tr><td>
	<div align="center" style="margin: 40px">
	<a href="${pageContext.request.contextPath}/index"><img src="${pageContext.request.contextPath}/common/images/legendshop.gif" width="240px"/></a>
	</div>
	</td></tr>

	<tr><td align="center">
	<form action="${pageContext.request.contextPath}/searchall" method="post" id="searchAllform" name="searchAllform">
	<table>
	<tr><td align="left">
		<input type="text" value="${keyword}" id="keyword" name="keyword" maxlength="100" size="50" class="kw"  onkeydown='if(event.keyCode==13)submitSearchAllform(0,document.getElementById("keyword").value);'></input>
		<input type="button" value='<fmt:message key="search.product"/>' class="btn" onmousedown="this.className='btn btn_h'" onmouseout="this.className='btn'" onclick='javascript:submitSearchAllform(0,document.getElementById("keyword").value)'></input>
		<c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
			<input type="button" value='<fmt:message key="search.mall"/>' class="btn" onmousedown="this.className='btn btn_h'" onmouseout="this.className='btn'" onclick='javascript:submitSearchAllform(1,document.getElementById("keyword").value)'></input>
		</c:if>
	</td></tr>
	</table>
	</form>
	</td></tr>
</table>
<center>
<c:if test="${'TEST' == applicationScope.RUNTIME_MODE}">
<table width="550px" style="margin: 10px">
	<tr><td width="80px" align="right">热门商家:</td>
	<td align="left">
		<a href="${pageContext.request.contextPath}/searchall/1/LegendShop">LegendShop软件商城</a>
		<a href="${pageContext.request.contextPath}/searchall/1/360buy">360buy天天购物网</a>
    </td></tr>
	<tr><td align="right">热门商品:</td>
	<td align="left">
	<a href="${pageContext.request.contextPath}/searchall/0/戴尔">戴尔</a>
	<a href="${pageContext.request.contextPath}/searchall/0/CPU">CPU</a>
	<a href="${pageContext.request.contextPath}/searchall/0/索尼 SONY">SONY</a>
	<a href="${pageContext.request.contextPath}/searchall/0/创维">创维</a>
	<a href="${pageContext.request.contextPath}/searchall/0/三星 SAMSUNG">三星SAMSUNG</a>
	</td>
	</tr>
</table>
</c:if>
<br><br><br><br><br><br>
<jsp:include page="/copy"/>
</center>


