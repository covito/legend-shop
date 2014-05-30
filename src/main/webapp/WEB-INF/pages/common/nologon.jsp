<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@include file='/WEB-INF/pages/common/common.jsp'%>
<%@ taglib uri="http://www.legendesign.net/biz" prefix="lb"%>
<html>
<head>

<title><fmt:message key="operation.error"/></title>
<lb:shopDetail var="shopDetail" >
    <link href="<ls:templateResource item='/common/style/style_${shopDetail.frontEndStyle}.css'/>" rel="stylesheet" type="text/css" />
    <link href="<ls:templateResource item='/common/style/global_${shopDetail.frontEndStyle}.css'/>" rel="stylesheet" type="text/css" />
</lb:shopDetail>
<body topmargin="0">

<table width="500" cellspacing="2" cellpadding="2" align="center" >
  <tr valign="middle" height="100px"> 
    <td align="center" style="font-family: serif;font-size: 1.5em;"><ls:i18n key="no.login.hint"/></td>
  </tr>
  <tr> 
    <td height="80px"  align="left" style="margin-left: 80px;">
     <ls:i18n key="nologin.hint" login="<ls:url address='/login'/>" reg="<ls:url address='/reg'/>"/> 
      </td>
  </tr>
</table>
</body>
</html>
