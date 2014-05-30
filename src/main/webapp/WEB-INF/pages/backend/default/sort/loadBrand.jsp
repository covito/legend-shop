<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<c:forEach items="${requestScope.brandList }" var="brand" >
		<li  id="${brand.brandId}" class="cc-cbox-item">${brand.brandName }</li>
</c:forEach>
	