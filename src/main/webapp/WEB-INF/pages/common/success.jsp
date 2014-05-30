<%@ page language="java" isErrorPage="true"  contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<html >
<head>
     <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
<title>操作成功</title>
</head>
<body>
<br>
<center>
<table class="${tableclass}" style="width: 100%">
		<thead>
          <tr height="39px">
            <td style="font-weight: bold;font-size: 1.5em;" align="center">
            <center>
            <c:choose>
            	<c:when test="${ERROR_MESSAGE == null}"><fmt:message key="operation.successful"/></c:when>
            	<c:otherwise><font color="red"><fmt:message key="operation.fail"/></font></c:otherwise>
            </c:choose>
            </center>
            </td>
          </tr>
        </thead>  
        <tbody>
      <tr>
       <td align="center">
         ${ERROR_MESSAGE}
       </td>
      </tr>
          <tr height="28px">
            <td class="titlebg">
            <div align="center">
            <c:if test="${not empty CALL_BACK}">
                 <a href="${pageContext.request.contextPath}/${CALL_BACK}"><fmt:message key="go.back"/></a>
            </c:if>
              <a href="javascript:history.go(-1)"><fmt:message key="previous.page"/></a>
              </div>
            </td>
          </tr>
          </tbody>
  </table>
</center>
</body>
</html>
