<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
	<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <title>缓存列表</title>
</head>
<body>
    <%
        Integer offset = 1;
    %>
	 <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 系统管理  &raquo; <a href="${pageContext.request.contextPath}/admin/system/cache/query">缓存列表</a></th></tr>
	    </thead>
	    	     <tbody><tr><td>
 <div align="left" style="padding: 3px">
 <auth:auth ifAnyGranted="F_SYSTEM">
	<input type="button" value="一键清除缓存"  onclick="javascript:clearCache()"/>
</auth:auth>
 </td></tr></tbody>
	    </table>
    
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="cacheList" requestURI="/admin/system/cache/query" id="item"
         export="false" class="${tableclass}" style="width:100%">
       <display:column title="顺序"   class="orderwidth">&nbsp;<%=offset++%>&nbsp;&nbsp;&nbsp;</display:column>
      <display:column title="缓存名称" property="key"></display:column>
      <display:column title="缓存内容" property="value"></display:column>
    </display:table>
    </div>
    
    <script type="text/javascript">
      function clearCache() {
	 $.post("${pageContext.request.contextPath}/admin/system/cache/removeall" , 
        function(retData) {
	       if(retData == null ){
	          window.location.reload() ;
	       }else{
	          alert("删除缓存失败！") ;
	       }
		},'json');
}
	highlightTableRows("item");
    </script>
</body>
</html>

