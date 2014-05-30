<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<jsp:include flush="true" page="/top" />
<tiles2:insertAttribute name="main" ignore="true" />
<jsp:include page="/bottom"/>
</body>
</html>