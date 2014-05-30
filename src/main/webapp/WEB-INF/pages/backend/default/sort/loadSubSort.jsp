<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<c:forEach items="${requestScope.nSort3List }" var="nsort3" >
		<li id="cc-cbox-item${nsort3.nsortId}"  sortId="${nsort3.nsortId}"  class="cc-cbox-item" >${nsort3.nsortName }</li>
</c:forEach>