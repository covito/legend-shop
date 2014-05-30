<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<html>
<head>
<style type="text/css">
	a:visited {  text-decoration: underline }
	a:hover {  text-decoration: underline }
	a:active { text-decoration: underline }
	a:link { text-decoration: underline }
	BODY {
	PADDING-RIGHT: 0px; PADDING-LEFT: 0px; BACKGROUND: #ffffff; PADDING-BOTTOM: 0px; MARGIN: 0px auto; PADDING-TOP: 0px;
  }
#master {
	width: 100%;
	background: url('../../common/style/master_bg.gif') repeat-x ;
	z-index: 10;
}

H1 {
	FONT-SIZE: 22px
}
UL {
	MARGIN: 1em
}
LI {
	LINE-HEIGHT: 1.6em; FONT-FAMILY: 宋体
}

</style>
<title>IP 地址查询</title>
<script type="text/javascript">
function isIP() {
    var result = true;
    var strIP = document.getElementById("ipAddress");
	if (strIP.value == null || strIP.value == ''){
		result = false;
	}
	if(result){
    var re=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g //匹配IP地址的正则表达式
	if(re.test(strIP.value))
	{
		if( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256){
		  result = true;
		} else{
			result = false;
		}
	}else{
	result = false;
	}
	}
	if(!result){
		alert("请输入正确的IP地址");
		}
	return result;
}

</script>
</head>
<!--  id="master" -->
<body>
<center>
<form action="${pageContext.request.contextPath}/ipsearch" method="post" onsubmit="return isIP();">
<table width="954px" style="border-bottom-width: 1px;border-bottom-color: red;border: 1px">
    <tr>
    <td width="160px">
    <a href="${pageContext.request.contextPath}/index"><img src="${pageContext.request.contextPath}/common/images/legendshop.gif" width="160px" title="LegendShop"/></a>
    </td>
     <td>
	     <table width="100%">
	     <tr><td>
	     <table width="100%">
	     <tr>
		     <td align="left">商家 买家 新闻 
		     <a href="${pageContext.request.contextPath}/index">高级搜索</a></td>
		     <td align="right">
		     	   <c:choose>
					 <c:when test="${SPRING_SECURITY_LAST_USERNAME != null}">
					  <span style="color: red; font-weight: bold;">${sessionScope.SPRING_SECURITY_LAST_USERNAME}</span>
					   	<a href="${pageContext.request.contextPath}/p/myaccount")><fmt:message key="myaccount"/></a>
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
		     
	    		<a href="${pageContext.request.contextPath}/reg">用户注册</a> 
	    		<a href="${pageContext.request.contextPath}/reg">我要加盟</a>
				<a href="../club">论坛</a>  
             </td>
	     </tr>
	     </table>
     </td>
     </tr>
     <tr>
     <td align="left">
        <b>请输入IP地址</b>&nbsp;
        <input type="text" name="ipAddress" id="ipAddress" style="height: 26px" maxlength="100" size="50" value="${ipAddress}"></input>
		<input type="submit" value="搜地址" style="margin-left: 0px;height: 27px"></input>
		 </td></tr>
     </table>
    </td> 

    </tr>
 </table>
   <br>  <br> 
   <c:if test="${address != null}">
      该IP地址位于:<H1> <b>${address}</b></H1>
   </c:if>
  
   </form>
   <table cellSpacing=0 cellPadding=0 width="955px" ><tr><td>
   <BLOCKQUOTE>
  <OL>
        您可以尝试访问以下链接:
    <UL type=square> 
     <ls:plugin pluginId="advanceSearch">
      <LI><A href="${pageContext.request.contextPath}/all">高级搜索</A> - 搜索各种商城和商品，或者到商城首页 </LI>
      </ls:plugin>
      <LI><A href="${pageContext.request.contextPath}/index">首页</A> -  到当前商城首页 </LI>
      <LI><A href="${pageContext.request.contextPath}/reg">注册</A> - 注册一个用户或者商家</LI>
      <LI><A href="${pageContext.request.contextPath}/login">登录</A> - 登录系统</LI>
      <LI><A href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club">论坛</A> - 买家和卖家的经验交流基地</LI>
      <LI><A href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club/jforum.page?module=posts&action=insert&forum_id=4">我要投诉</A> - 到论坛上投诉 </LI>
   </UL>
  </OL>
  </BLOCKQUOTE>
   </td></tr></table>
</center>
 <br>
  <jsp:include page="/copy"/>
</body>
</html>
