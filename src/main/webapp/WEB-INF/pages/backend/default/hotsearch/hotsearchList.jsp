<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
	<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <script src="<ls:templateResource item='/common/js/recordstatus.js'/>" type="text/javascript"></script>
     <%@ include file="/WEB-INF/pages/common/dialog.jsp"%>
    <title>热门列表</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="<ls:url address='/admin/hotsearch/query'/>" id="form1" method="post">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; <a href="<ls:url address='/admin/hotsearch/query'/>">热门商品管理</a></th></tr>
    </thead>
     <tbody><tr><td>
 <div align="left" style="padding: 3px">
 <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
	            标题
	            <input type="text" name="title" maxlength="50" value="${bean.title}"  size="30"/>
	           商品类型
	            <lb:sortOption id="sort"  type="P"  selectedValue="${bean.sort}"/>
         <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
             商城&nbsp;<input type="text" name="userName" maxlength="50" value="${bean.userName}" />
         </auth:auth>
         <input type="submit" value="搜索"/>
         <auth:auth ifAnyGranted="FA_PROD">
         <input type="button" value="创建热门商品" onclick='window.location="${pageContext.request.contextPath}/admin/hotsearch/load"'/>
         </auth:auth>
 </div>
 </td></tr></tbody>
    </table>
    </form>
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/hotsearch/query" id="item"
         export="true" class="${tableclass}" style="width:100%">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
      <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            <display:column title="商城" property="userName"></display:column>
      </auth:auth>
      <display:column title="标题" property="title"></display:column>
      <display:column title="分类" property="sortName"></display:column>
      <display:column title="查询内容"><a href="${item.msg}" target="_blank">${item.msg}</a></display:column>
      <display:column title="顺序">${item.seq}</display:column>
      <auth:auth ifAnyGranted="FA_PROD">
      <display:column title="操作" media="html"  style="width:80px">
          <c:choose>
		  		<c:when test="${item.status == 1}">
		  			<img name="statusImg"  itemId="${item.id}"  itemName="${item.title}"  status="${item.status}"  class="cursor_pointer"   src="<ls:templateResource item='/common/default/images/blue_down.png'/> ">
		  		</c:when>
		  		<c:otherwise>
		  			<img  name="statusImg"   itemId="${item.id}"  itemName="${item.title}"  status="${item.status}"   class="cursor_pointer"  src="<ls:templateResource item='/common/default/images/yellow_up.png'/> ">
		  		</c:otherwise>
		  	</c:choose>
      <a href="<ls:url address='/admin/hotsearch/load/${item.id}'/>" title="修改"><img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
        <a href='javascript:deleteById("${item.id}")' title="删除"><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
      </display:column>
      </auth:auth>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
        </div>
   <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 热门商品出现在一级商品类型的首页<br>
   2. <img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "> 修改按钮<br>
   3. <img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "> 删除按钮<br>
   </td><tr></table> 
   
    <script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/hotsearch/delete/"+id;
        }
    }
          $(document).ready(function(){
          		$("img[name='statusImg']").click(function(event){
	          		$this = $(this);
	          		initStatus($this.attr("itemId"), $this.attr("itemName"),  $this.attr("status"), "${pageContext.request.contextPath}/admin/hotsearch/updatestatus/", event.target,"${pageContext.request.contextPath}");
	       			 }
	       		);
           highlightTableRows("item");
          });
//-->
</script>
</body>
</html>

