<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
						<c:forEach items="${requestScope.nSort2List }" var="nsort" >
											<li id="cc-cbox-item${nsort.nsortId}"  sortId="${nsort.nsortId}" class='cc-cbox-item  <c:if test="${fn:length(nsort.subSort)>0}">cc-hasChild-item</c:if>'>
													 ${nsort.nsortName }
											</li>
						</c:forEach>