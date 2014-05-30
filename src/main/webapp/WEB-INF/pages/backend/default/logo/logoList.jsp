<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>

<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
	<script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
	<title>Logo 列表</title>
</head>
<body>
	<%
	    Integer offset = (Integer) request.getAttribute("offset");
	%>
	<form action="${pageContext.request.contextPath}/admin/logo/query" id="form1" method="post">
	    <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/logo/query">Logo管理</a></th></tr>
	    </thead>
	     <tbody><tr><td>
 <div align="left" style="padding: 3px">
<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
		<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
			商城&nbsp;
			<input type="text" name="userName" maxlength="50"
				value="${logo.userName}" size="15" />
			<input type="submit" value="搜索"/>
		</auth:auth>
		<c:if test="${fn:length(list) == 0}">
			<input type="button" value="创建首页Logo" onclick='window.location="${pageContext.request.contextPath}/admin/logo/load"'/>
		</c:if>
 </div>
 </td></tr></tbody>
	    </table>
	</form>
	<div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
        <display:table name="list" requestURI="/admin/logo/query" id="item"
            export="true" class="${tableclass}" style="width:100%">
            <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
              <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
           		 <display:column title="商城" property="userName" sortable="true"></display:column>
           		 <display:column title="商城简述">${item.briefDesc}</display:column>    
             </auth:auth>
            <display:column title="图片"><img src="<ls:photo item='${item.logoPic}'/>" height="65px" width="180px"/></display:column>      
          <auth:auth ifAnyGranted="FA_SHOP">
            <display:column title="操作" media="html" class="actionwidth">
                 <a href= "${pageContext.request.contextPath}/admin/logo/load/${item.userId}" title="修改"><img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a> 
                 <a href='javascript:deleteById("${item.userId}")' title="删除"><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
            </display:column>
            </auth:auth>
        </display:table>
		<c:if test="${not empty toolBar}">
			<c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
		</c:if>
		</div>
	    <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 每个商城可以设置自己的Logo，如果不设置则采用默认Logo<br>
   </td><tr></table> 	
		<script language="JavaScript" type="text/javascript">
<!--
  function deleteById(id) {
	  if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/logo/delete/"+id;
	    }
    }
		highlightTableRows("item"); 
//-->
</script>
</body>
</html>

