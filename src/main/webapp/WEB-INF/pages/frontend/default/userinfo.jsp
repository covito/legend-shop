<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
	<div>
	   <ul id="topnews">
	   <c:forEach items="${newsTopList}" var="newsTop">
	       <li><a href="<ls:url address='/news/${newsTop.newsId}'/>">${newsTop.newsTitle}</a></li>
	   </c:forEach>
	   <c:if test="${'userChoice' eq shopDetail.langStyle}">
        <li class="hl"><a href="<ls:url address='/changeLocale'/>?country=CN&language=zh">中文</a></li>
        <li class="hl"><a href="<ls:url address='/changeLocale'/>?country=US&language=en">English</a></li>
        </c:if>
    </ul>
    </div>
	<div>
	<ul id="topnav">
	<lb:logined >
	   <ls:myshop>
               <li><a href="<ls:domain shopName='${sessionScope.SPRING_SECURITY_LAST_USERNAME}' />"><ls:i18n key="myShop"/></a></li>
        </ls:myshop>
        <ls:auth ifAnyGranted="F_ADMIN">
	   	<c:if test="${canbeLeagueShop}">
	   		<li><a href='javascript:addMyLeague()'><ls:i18n key="addLeague"/><ls:i18n key="this.shop"/></a></li>
	   	</c:if>
      </ls:auth>
      	<ls:auth ifAnyGranted="FE_BACKEND">
				<li><a href="<ls:url address='/admin/index'/>"><b><ls:i18n key="system.management"/></b></a></li>
	  </ls:auth>
      	<li><a href="<ls:url address='/leaveword'/>"><ls:i18n key="leaveword"/></a></li>
		<li><a href="<ls:url address='/p/order'/>"><ls:i18n key="myorder"/></a></li>
	</lb:logined>
		<li class="navCartSum"><a href="<ls:url address='/p/buy'/>"><ls:i18n key="shopingCar"/> &nbsp;<span  style="color: #FF5500;font-weight: bold;" id="basketTotalCount">${sessionScope.BASKET_TOTAL_COUNT }</span>&nbsp;件</a></li>	
		<li class="hl"><a href="<ls:url address='/allnews'/>"><ls:i18n key="newsCenter"/></a></li>
	</ul>
	</div>
 <div id="headerlogin">
	<span>
 <c:choose>
   <c:when test="${sessionScope.SPRING_SECURITY_LAST_USERNAME != null}">
  <span><b>${sessionScope.SPRING_SECURITY_LAST_USERNAME}</b></span>
   	<a href="<ls:url address='/p/myaccount'/>")>[<ls:i18n key="myaccount"/>]</a>
	<a href="${pageContext.request.contextPath}/p/logout" target="_parent">[<ls:i18n key="logout"/>]</a>
   </c:when>
   <c:otherwise>
  	<a href="<ls:url address='/login'/>">[<ls:i18n key="login"/>]</a>
   </c:otherwise>
</c:choose>
<a href="<ls:url address='/reg'/>" class="n2">[<ls:i18n key="regFree"/>]</a>
</span>
</div>
