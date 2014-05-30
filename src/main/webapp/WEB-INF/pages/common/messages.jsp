<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%-- Error Messages --%>
<c:if test="${not empty springMessages}">
	<div class="message">
        <c:forEach var="msg" items="${springMessages}">
			<img src="${pageContext.request.contextPath}/common/default/images/iconInformation.gif" alt="Info"/>${msg}<br/>
        </c:forEach>
	</div>    
</c:if>

<%-- Info Messages --%>
<c:if test="${not empty springErrors}">
	<div class="error">
        <c:forEach var="errorMsg" items="${springErrors}">
			<img src="${pageContext.request.contextPath}/common/default/images/iconWarning.gif" alt="Warning"/>${errorMsg}<br/>
        </c:forEach>
	</div>    
</c:if>



