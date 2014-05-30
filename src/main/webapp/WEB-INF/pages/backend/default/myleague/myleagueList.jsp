<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>

<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <title>加盟商城列表</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/myleague/query" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/myleague/query">加盟商城管理</a></th></tr>
	    </thead>
	     <tbody><tr><td>
 <div align="left" style="padding: 3px">
 <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">&nbsp;商城
               <input type="text" name="userId" maxlength="50" value="${bean.userId}" />
             </auth:auth>
            <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />加盟商城
            <input type="text" name="friendId" maxlength="50" value="${bean.friendId}" />
	        <input type="submit" value="搜索"/>
 </div>
 </td></tr></tbody>
	    </table>
    </form>
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/myleague/query" id="item"
         export="true" class="${tableclass}" style="width:100%">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
      <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
        <display:column title="商城" property="userId" sortable="true"></display:column>
      </auth:auth>
      <display:column title="加盟商城">
      	<a href="<ls:domain shopName='${item.friendId}'  />"  target="_blank">${item.friendId}</a>
      </display:column>
      <display:column title="加盟商城说明" property="friendName"></display:column>
      <display:column title="次序" property="displayOrder" sortable="true"></display:column>
      <display:column title="申请时间" property="addtime" sortable="true"></display:column>
       <auth:auth ifAnyGranted="FA_SHOP">
      <display:column title="操作" media="html" class="actionwidth">
	      <a href= "${pageContext.request.contextPath}/admin/myleague/load/${item.id}"><img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
	      <a href='javascript:deleteById("${item.id}")'><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
      </display:column>
        </auth:auth>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
   <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 加盟商城将会出现在您的商城的加盟商城页面，用于商城之间的导航和组成联盟关系<br>
   2. 请在前台浏览商城时将你喜欢的商城加为加盟商城，没有任何限制<br>
   3. 删除加盟商城之后将不会在前台显示<br>
   </td><tr></table> 
     <script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/myleague/delete/"+id;
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

