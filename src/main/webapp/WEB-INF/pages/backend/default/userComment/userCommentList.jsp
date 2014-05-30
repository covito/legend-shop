<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>

<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
<script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
<title>用户消息列表</title>
</head>
<body>
<%
			Integer offset = (Integer)request.getAttribute("offset");
	%>
	<form id="form1" name="form1" action="${pageContext.request.contextPath}/admin/userComment/query">
	<table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; <a href="${pageContext.request.contextPath}/admin/userComment/query?status=0">消息管理</a></th></tr>
    </thead>
     <tbody><tr><td>
 <div align="left" style="padding: 3px">
<input type="hidden" name="curPageNO" id="curPageNO" value="${curPageNO}"/>
			用户名称&nbsp;
			<input type="text" name="userName" maxlength="50" value="<%=request.getAttribute("userName")%>" />
			<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
			商城&nbsp;<input type="text" name="search" maxlength="50" value="<%=request.getAttribute("search")%>" />
			</auth:auth>
			<select id="status" name="status">
				<option value="">全部</option>
				<option value="0">未读</option>
				<option value="1">已读</option>
			</select>
			<input type="submit" value="搜索"/>
 </div>
 </td></tr></tbody>
    </table>
		
<c:if test="${requestScope.list != null && fn:length(requestScope.list) > 0}">
<div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/userComment/query" id="item"
         export="true"  class="${tableclass}" style="width:100%" sort="external">
      <display:column title="顺序"  class="orderwidth"><%=offset++%></display:column>
       <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
     		<display:column title="商城" property="toUserName" sortable="true" sortName="toUserName"></display:column>
     </auth:auth>
      <display:column title="登录用户" sortable="true" sortName="userName">
	    <c:choose>
	    	<c:when test="${item.userName !=null && item.userName != 'null'}">${item.userName}</c:when>
	    	<c:otherwise>未登录</c:otherwise>
	    </c:choose>
      </display:column>
      <display:column title="用户昵称" property="yourName" sortable="true" sortName="yourName"></display:column>
      <display:column title="消息内容" property="content" sortable="true" sortName="content"></display:column>
      <display:column title="回复内容" property="answer" sortable="true" sortName="answer"></display:column>
      <display:column title="添加时间" property="recDate" sortable="true" sortName="recDate"></display:column>
      <display:column title="IP来源" property="postip" sortable="true" sortName="postip"></display:column>
      <display:column title="状态" sortable="true" sortName="status">
	    <c:choose>
	    	<c:when test="${item.status==1}">已读</c:when>
	    	<c:when test="${item.status==0}">未读</c:when>
	    	<c:otherwise>其他</c:otherwise>
	    </c:choose>
     </display:column>
      <auth:auth ifAnyGranted="FA_PROD">
	      <display:column title="操作" media="html">
			<a href="javascript:openWindows('${item.id}')" title="回复"><img alt="回复" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
		     <auth:auth ifAnyGranted="FA_SHOP">
		        <a href='${pageContext.request.contextPath}/admin/deleteUserComment?id=${item.id}' onclick="return confirmDelete();" title="删除"><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
		     </auth:auth>
	      </display:column>
      </auth:auth>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
</c:if>
</form>

		<table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 用户在您的商城上浏览之后可以通过站内信给您发信息，状态为未读<br>
   2. 登录用户为当前登录用户名，如果用户没有登录发表信息，则显示为匿名用户<br>
   3. 当您阅读该信息后，可以回复该信息，该信息状态为已读<br>
   </td><tr></table>
   <script language="JavaScript" type="text/javascript">
<!--
  function confirmDelete()
	{
	    return con = confirm(" 确定要删除？");
	}
	
  function openWindows(id) {
 		 var win = window.open('${pageContext.request.contextPath}/admin/userComment/update/'+id + '','回复用户消息','height=350px,width=650px, scroll=no, status=yes,top=250,left=250');	
		 win.focus() ;
		 } 
		 
        function pager(curPageNO){
            document.getElementById("curPageNO").value=curPageNO;
            document.getElementById("form1").submit();
        }
        
        window.onload=function(){
		 $("#status").val('${status}');
		 highlightTableRows("item");
		}
		
       //-->
</script>
</body>
</html>

