<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<jsp:include flush="true" page="/top" />
<div id="bodyer">
<table width="952px" cellspacing="0" cellpadding="0" align="center" style="margin-top: 5px">
    <tr>
      <td valign="top" align="left">
      <c:forEach items="${requestScope.SORT_ADV_TOP}" var="adv">
		<table width="205px"  cellspacing="0" cellpadding="0" style="margin-bottom: 4px; margin-right: 5px;"  class="picstyle">
			<tr><td><a href="${adv.linkUrl}"><img src="<ls:photo item='${adv.picUrl}'/>" title="${adv.title}" width="100%"/></a></td></tr>
		 </table>
		</c:forEach>
         <jsp:include page="/topnews?topsortnews=1" flush="true"/>
         
       <c:forEach items="${requestScope.SORT_ADV_MID1}" var="adv">
		<table width="205px" cellpadding="0" cellspacing="0" style="margin-bottom: 4px; margin-right: 5px;" class="picstyle">
			<tr><td><a href="${adv.linkUrl}"><img src="<ls:photo item='${adv.picUrl}'/>" title="${adv.title}" width="100%"/></a></td></tr>
		 </table>
		</c:forEach>        
         
         <jsp:include page="/topsort" flush="true" />
         
       <c:forEach items="${requestScope.SORT_ADV_MID2}" var="adv">
		<table width="205px" cellpadding="0" cellspacing="0" style="margin-bottom: 4px; margin-right: 5px;" class="picstyle">
			<tr><td><a href="${adv.linkUrl}"><img src="<ls:photo item='${adv.picUrl}'/>" title="${adv.title}" width="100%"/></a></td></tr>
		 </table>
		</c:forEach>    
        <c:forEach items="${requestScope.SORT_ADV_BOTTOM}" var="adv">
			<table width="205px" cellpadding="0" cellspacing="0" style="margin-bottom:4px; margin-right: 5px;" class="picstyle">
				<tr><td><a href="${adv.linkUrl}"><img src="<ls:photo item='${adv.picUrl}'/>" title="${adv.title}" width="100%"/></a></td></tr>
			</table>
	    </c:forEach>
        </td>
      <td valign="top" width="744px"><tiles2:insertAttribute name="main" ignore="true" /><div id="visitedprod" name="visitedprod"></div></td>
    </tr>
    <tr> 
      <td valign="top" colspan="2">
      	<jsp:include page="/bottom"/></td>
    </tr>
</table>
</div>
	<script type="text/javascript">
					$(document).ready(function(e) {
								//call ajax
							   $.post("${pageContext.request.contextPath}/visitedprod", 
							        function(retData) {
							              	$("#visitedprod").html(retData);
									},'html');
						});
		</script>
</body>
</html>