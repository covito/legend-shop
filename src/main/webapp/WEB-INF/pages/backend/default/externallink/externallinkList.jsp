<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>

<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <title>友情链接列表</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>

    <form action="${pageContext.request.contextPath}/admin/externallink/query" id="form1" method="post">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/externallink/query">链接管理</a></th></tr>
    </thead>
     <tbody><tr><td>
 <div align="left" style="padding: 3px">
 <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
         <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
          商城&nbsp; <input type="text" name="userName" maxlength="50" value="${bean.userName}" />
          </auth:auth>
            <input type="submit" value="搜索"/>
            <auth:auth ifAnyGranted="FA_SHOP">
            <input type="button" value="创建友情链接" onclick='window.location="${pageContext.request.contextPath}/admin/externallink/load"'/>
            </auth:auth>
 </div>
 </td></tr></tbody>
    </table>
       
    </form>
      <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/externallink/query" id="item" 
         export="true" class="${tableclass}" style="width:100%" sort="external">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
       <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
        <display:column title="商城" property="userName" sortable="true" sortName="userName"></display:column>
      </auth:auth>
      <display:column title="图片">
      	<c:if test="${item.picture != null}">
      	<img src="<ls:photo item='${item.picture}'/>" height="31px" width="81px"/>
      	</c:if>
      </display:column>
      <display:column title="链接地址"><a href="${item.url}" target="_blank">${item.url}</a></display:column>
      <display:column title="链接文字说明" property="wordlink"></display:column>
      <display:column title="描述" property="content"></display:column>
      <display:column title="次序" property="bs" sortable="true"  sortName="bs"></display:column>
      <auth:auth ifAnyGranted="FA_SHOP">
	      <display:column title="操作" media="html" class="actionwidth">
			      <a href= "${pageContext.request.contextPath}/admin/externallink/load/${item.id}" title="修改"><img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
			        <a href='javascript:deleteById("${item.id}")' title="删除"><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
		  </display:column>
      </auth:auth>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
  </div>
     <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 友情链接管理，将会出现在商城首页<br>
   2. 可以为每个友情链接增加一个图片<br>
   </td><tr></table> 
   <script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/externallink/delete/"+id;
        }
    }

        function pager(curPageNO){
            document.getElementById("curPageNO").value=curPageNO;
            document.getElementById("form1").submit();
        }
        
    highlightTableRows("item");  
//-->
</script>
</body>
</html>

