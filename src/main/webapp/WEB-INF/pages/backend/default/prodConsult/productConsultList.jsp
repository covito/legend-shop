<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>

<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
<script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
<script src="<ls:templateResource item='/plugins/My97DatePicker/WdatePicker.js'/>" type="text/javascript"></script>
<title>咨询管理</title>
</head>
<body>
	<%
		Integer offset = (Integer) request.getAttribute("offset");
	%>
	<form action="${pageContext.request.contextPath}/admin/productConsult/query" id="form1" method="post">
		<table class="${tableclass}" style="width: 100%">
			<thead>
				<tr>
					<th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理 &raquo; <a
						href="${pageContext.request.contextPath}/admin/productConsult/query">咨询管理</a></th>
				</tr>
			</thead>
			
				<tbody>
					<tr>
						<td>
							<div align="left" style="padding: 3px">
								<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
								<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
								 商城&nbsp;<input type="text" name="userName" maxlength="50" value="${productConsult.userName}" />
								 	</auth:auth>
								   商品名称&nbsp;<input type="text" name="prodName" maxlength="50" value="${productConsult.prodName}" /> 
								  类型&nbsp;
								  <select  id="pointType" name="pointType"><ls:optionGroup type="select" required="false" cache="true"
	               						beanName="CONSULT_TYPE" selectedValue="${productConsult.pointType}"/></select>
	                已回复&nbsp;
	                	                <select  id="replyed"  name="replyed">
						                      <ls:optionGroup type="select" required="false" cache="true"
	               						beanName="YES_NO" selectedValue="${productConsult.replyed}"/>
						                </select>

							</div>	  <div align="left" style="padding: 3px">
								  &nbsp;开始时间
			 <input readonly="readonly"  name="startTime" id="startTime" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${productConsult.startTime}" pattern="yyyy-MM-dd"/>' />
			&nbsp;结束时间 
			 <input readonly="readonly" name="endTime" id="endTime" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${productConsult.endTime}" pattern="yyyy-MM-dd"/>' />
								<input type="submit" value="搜索" />
							</div></td>
					</tr>
				</tbody>
		
		</table>

	</form>
	<div align="center">
		<%@ include file="/WEB-INF/pages/common/messages.jsp"%>
		<display:table name="list" requestURI="/admin/productConsult/query" id="item" export="true" class="${tableclass}"
			style="width:100%" sort="external">
			<display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
			<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
	      		<display:column title="商城">
					<a href="<ls:domain shopName='${item.userName}'  />">${item.userName}</a>
				</display:column>
	      </auth:auth>
			<display:column title="商品" >
			<a href="<ls:url address='/views/${item.prodId}'/>"  target="_blank" title="${item.prodName}" >${item.prodName}</a>
			</display:column>
			<display:column title="咨询" property="content"></display:column>
			<display:column title="类型"  style="width:60px">
			      	<ls:optionGroup type="label" required="false" cache="true"
	                beanName="CONSULT_TYPE" selectedValue="${item.pointType}" defaultDisp=""/>
			</display:column>
			<display:column title="咨询人" property="askUserName"></display:column>
			<display:column title="时间"><fmt:formatDate value="${item.recDate}" pattern="yyyy-MM-dd HH:mm"/></display:column>
			<display:column title="操作" media="html" class="actionwidth">
				<a href="${pageContext.request.contextPath}/admin/productConsult/load/${item.id}" title="回复"><img alt="回复"
					src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "> </a>
				<auth:auth ifAllGranted="FA_PROD">
					<a href='javascript:deleteById("${item.id}")' title="删除"><img alt="删除"
						src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "> </a>
				</auth:auth>
			</display:column>
		</display:table>
		<c:if test="${not empty toolBar}">
			<c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
		</c:if>
	</div>
	<table style="width: 100%; border: 0px">
		<tr>
			<td align="left">说明：<br> 1. 用户在前台商品页面中输入咨询内容<br> </td>
		<tr>
	</table>

	<script language="JavaScript" type="text/javascript">
	<!--
		function deleteById(id) {
			if (confirm("  确定删除 ?")) {
				window.location = "${pageContext.request.contextPath}/admin/productConsult/delete/"
						+ id;
			}
		}
		highlightTableRows("item");
	//-->
	</script>
</body>
</html>

