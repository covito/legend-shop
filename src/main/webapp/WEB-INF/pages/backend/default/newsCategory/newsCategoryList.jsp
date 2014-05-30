<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <title>栏目列表</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/newsCategory/query" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/newsCategory/query">栏目管理</a></th></tr>
    </thead>
     <tbody><tr><td>
 <div align="left" style="padding: 3px">
 <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        	栏目名称
	            <input type="text" name="newsCategoryName" maxlength="50" value="${bean.newsCategoryName}" />
	     状态
	           <select id="status" name="status">
				  <ls:optionGroup type="select" required="true" cache="true"
	                beanName="ONOFF_STATUS" selectedValue="${bean.status}"/>
	            </select>
         <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
                 商城 
               <input type="text" name="userName" maxlength="50" value="${bean.userName}" />
        </auth:auth>
               <input type="submit" value="搜索"/>
              <auth:auth ifAnyGranted="FA_SHOP">
            		<input type="button" value="创建栏目" onclick='window.location="${pageContext.request.contextPath}/admin/newsCategory/load"'/>
            </auth:auth>
            <input type="button" value="新闻列表" onclick='window.location="${pageContext.request.contextPath}/admin/news/query/1"'/>
 </div>
 </td></tr></tbody>
    </table>
    </form>
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/newsCategory/query" id="item"
         export="true" class="${tableclass}" style="width:100%">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
       <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            <display:column title="商城" property="userName"></display:column>
      </auth:auth>
      <display:column title="栏目名称" property="newsCategoryName"></display:column>
      <display:column title="状态">
           <c:choose>
                <c:when test="${item.status == 0}"><font color="red">下线</font></c:when>
                <c:otherwise>上线</c:otherwise>
           </c:choose>
      
      </display:column>
      <display:column title="建立时间" property="newsCategoryDate" format="{0,date,yyyy-MM-dd HH:mm}" sortable="true"></display:column>
          <auth:auth ifAnyGranted="FA_SHOP">
		      <display:column title="操作" media="html" style="width:40px">
		      <a href= "${pageContext.request.contextPath}/admin/newsCategory/load/${item.newsCategoryId}" title="修改"><img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
		      <a href='javascript:deleteById("${item.newsCategoryId}")'title="删除"><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
		      </display:column>
            </auth:auth>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
 <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 每个文章都是挂在栏目下面，是文章的分类<br>
   2. 栏目处于上线状态页面可见，处于下线状态页面不可见<br>
   </td><tr></table> 
    <script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/newsCategory/delete/"+id;
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

