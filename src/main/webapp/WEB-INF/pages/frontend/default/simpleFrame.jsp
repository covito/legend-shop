<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<jsp:include flush="true" page="/top" />
<div id="bodyer">
<table width="954px" cellspacing="0" cellpadding="0" align="center" style="margin-top: 5px">
    <tr> 
      <td><tiles2:insertAttribute name="main" ignore="true" /></td>
    </tr>
    <tr>
      <td>
      <div style="margin-top: 5px">
      	<jsp:include flush="true" page="/hotview"></jsp:include>
      </div>
      </td>
    </tr>
    <tr>
      <td valign="top">
      	<jsp:include page="/bottom"/></td>
    </tr>
</table>
</div>
</body>
</html>