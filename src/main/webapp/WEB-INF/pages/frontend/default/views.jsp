<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<div id="bodyer">
<table width="954px" align="center" cellpadding="0" cellspacing="0" style="margin-top: 4px">
  <tr> 
    <td valign="top">
     <!-- adv -->
    <c:forEach items="${requestScope.PROD_ADV_TOP}" var="adv">
	<table width="205px" cellpadding="0" cellspacing="0" style="margin-bottom: 4px; margin-right: 5px;" class="picstyle">
		<tr><td><a href="${adv.linkUrl}"><img src="<ls:photo item='${adv.picUrl}'/>" title="${adv.title}" width="100%"/></a></td></tr>
	 </table>
	</c:forEach>
			<!-- topnews -->
            <jsp:include page="/topnews?topsortnews=1"/>  
            <!-- adv -->
         <c:forEach items="${requestScope.PROD_ADV_MID1}" var="adv1">
			<table width="205px" cellpadding="0" cellspacing="0" style="margin-bottom: 4px; margin-right: 5px;" class="picstyle">
			  <tr><td><a href="${adv1.linkUrl}"><img src="<ls:photo item='${adv1.picUrl}'/>" title="${adv1.title}" width="100%"/></a></td></tr>
			 </table>
			</c:forEach> 
            <!-- topsort -->
            <jsp:include flush="true" page="/topsort" />
             <!-- adv -->
              <c:forEach items="${requestScope.PROD_ADV_MID2}" var="adv2">
				<table width="205px" cellpadding="0" cellspacing="0" style="margin-bottom: 4px; margin-right: 5px;" class="picstyle">
				  <tr><td><a href="${adv2.linkUrl}"><img src="<ls:photo item='${adv2.picUrl}'/>" title="${adv2.title}" width="100%"/></a></td></tr>
				 </table>
			</c:forEach> 
             <!-- hot product -->
            <jsp:include page="/hoton/${prod.sortId}" />
             <!-- adv -->
           <c:forEach items="${requestScope.PROD_ADV_BOTTOM}" var="adv3">
			<table width="205px" cellpadding="0" cellspacing="0" style="margin-bottom: 4px; margin-right: 5px;" class="picstyle">
			 <tr><td><a href="${adv3.linkUrl}"><img src="<ls:photo item='${adv3.picUrl}'/>" title="${adv3.title}" width="100%"/></a></td></tr>
			 </table>
			</c:forEach> 
 
    </td>
    <td valign="top" width="744px"><%@ include file="productdetail.jsp"%></td> 
  </tr>
</table>
</div>