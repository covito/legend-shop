 <%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
 <table width="954px" cellpadding="3px" cellspacing="3px" align="center" >
  <td width="100%" align="center">
           <c:forEach items="${requestScope.newsBottomList}" var="newsBottom">
           	  <a href="${pageContext.request.contextPath}/news/${newsBottom.newsId}" target="_blank">${newsBottom.newsTitle}</a> 
           </c:forEach>
              ${shopDetail.siteName } 
    	    <c:if test="${shopDetail.domainName != null && !isDefaultShop}">
    	        &nbsp;<a href="http://www.miitbeian.gov.cn" target="_blank">${shopDetail.icpInfo } </a>
    	    </c:if>
  </td></tr>
    <tr> 
  <td width="100%" align="center">
    	 &copy;Copyright 2012 Power By 
    	 <a href="http://www.legendesign.net" target="_blank">LegendShop ${LEGENDSHOP_VERSION}</a> 
    	  All Rights Reserved.&nbsp;<a href="http://www.miitbeian.gov.cn" target="_blank">${applicationScope.SYSTEM_CONFIG.icpInfo}</a>
       </td></tr>
  <tr> 
</table>