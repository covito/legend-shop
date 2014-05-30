<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<jsp:include flush="true" page="/top" />
<div id="bodyer">
<table width="954px" cellspacing="0" cellpadding="0" align="center" style="margin-top: 5px">
    <tr> 
      <td valign="top" align="left">  
         <jsp:include page="/topnews?topsortnews=1" flush="true"/>
         <jsp:include page="/topsort" flush="true" />
         <jsp:include page="/hoton/${sort.sortId}" flush="true"/>
        </td>
      <td valign="top" width="744px"><tiles2:insertAttribute name="main" ignore="true" /></td>
    </tr>
    <tr> 
      <td valign="top" colspan="2">
      	<jsp:include page="/bottom"/></td>
    </tr>
</table>
</div>
</body>
</html>