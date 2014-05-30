<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>

<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>  
<script src="<ls:templateResource item='/plugins/My97DatePicker/WdatePicker.js'/>" type="text/javascript"></script>
<html>
<%
			Integer offset = (Integer)request.getAttribute("offset");
	%>
<head>
	<script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
<title>用户登录历史列表次数</title>
</head>
<body>
<auth:auth ifAnyGranted="F_SYSTEM">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 用户管理  &raquo; <a href="${pageContext.request.contextPath}/admin/loginHistory/sum">用户登录次数历史列表</a></th></tr>
    </thead>
     <tbody><tr><td>
 <div align="left" style="padding: 3px">
<form action="${pageContext.request.contextPath}/admin/loginHistory/sum" id="form1">
<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}">
			&nbsp;用户名称
			<input type="text" name="userName" id="userName" maxlength="50" value="${login.userName}" size="15"/>
			&nbsp;开始时间
			<input readonly="readonly"  name="startTime" id="startTime" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${login.startTime}" pattern="yyyy-MM-dd"/>' />
			&nbsp;结束时间
			<input readonly="readonly" name="endTime" id="endTime" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${login.endTime}" pattern="yyyy-MM-dd"/>' />
			<input type="submit" value="搜索" /> 
</form>
 </div>
 </td></tr></tbody>
    </table>

<div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/loginHistory/sum" id="item"
         export="true"  class="${tableclass}" style="width:100%" sort="external">
      <display:column title="顺序"  class="orderwidth"><%=offset++%></display:column>
      <display:column title="用户名">${item[0]}</display:column>
      <display:column title="登录次数">${item[1]}</display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
	</auth:auth>
	
	<script language="JavaScript" type="text/javascript">
<!--
		function pager(curPageNO){
			document.getElementById("curPageNO").value=curPageNO;
			document.getElementById("form1").submit();
		}
	 highlightTableRows("item");  	
//-->
</script>
</body>
</html>

