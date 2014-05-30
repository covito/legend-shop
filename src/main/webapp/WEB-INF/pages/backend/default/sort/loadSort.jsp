<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<c:forEach items="${requestScope.sortList }" var="sort" >
	<li  id="${sort.sortId}" class='cc-tree-item <c:if test="${fn:length(sort.nsort)>0}">cc-hasChild-item</c:if>'>${sort.sortName }</li>
</c:forEach>
	