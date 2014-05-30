<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<jsp:include flush="true" page="/topall" />
<div id="bodyer">
<table width="100%" cellspacing="0" cellpadding="0" align="center" style="margin: 5px">
    <tr> 
      <td><tiles2:insertAttribute name="main" ignore="true" /></td>
    </tr>
</table>
<jsp:include page="/bottom"/>
</div>
</body>
</html>