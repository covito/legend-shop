<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.legendshop.core.helper.PropertiesUtil"%>
<%@page import="com.legendshop.core.constant.SysParameterEnum"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
     <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
      <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
      <script src="<ls:templateResource item='/common/js/recordstatus.js'/>" type="text/javascript"></script>
       <%@ include file="/WEB-INF/pages/common/dialog.jsp"%>
<title>首页广告图片列表</title>
</head>
<body>
<%
	Integer offset = (Integer)request.getAttribute("offset");
%>
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/indexjpg/query">首页Flash图片管理</a></th></tr>
    </thead>
     <tbody><tr><td>
 <div align="left" style="padding: 3px">
<form id="form1" action="${pageContext.request.contextPath}/admin/indexjpg/query">
	        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}">
			<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
			商城
			<input type="text" name="userName" id="userName" maxlength="50" value="${indexJpg.userName}" />
			<input type="submit" value="搜索"/>
			</auth:auth>
			<auth:auth ifAnyGranted="FA_SHOP">
				<input type="button" value="创建首页图片" onclick='window.location="${pageContext.request.contextPath}/admin/indexjpg/load"'/>
			</auth:auth>
	</form>
 </div>
 </td></tr></tbody>
    </table>
	<div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/indexjpg/query" id="item"
         export="true" class="${tableclass}" style="width:100%" sort="external">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
       <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            <display:column title="商城" property="userName"></display:column>
      </auth:auth>
       <display:column title="标题" property="title"></display:column>
       <display:column title="图片" style="width:35px"><a href="<ls:photo item='${item.img}'/>" target="_blank"><img src="<ls:photo item='${item.img}'/>" height="145" width="265"/></a></display:column>
      <display:column title="次序" property="seq" sortable="true" sortName="seq"  style="width:35px"></display:column>
      	 <auth:auth ifAnyGranted="FA_SHOP">
     	 <display:column title="操作" media="html"  style="width:80px">
      	 <c:choose>
		  		<c:when test="${item.status == 1}">
		  			<img name="statusImg"  itemId="${item.id}"  itemName="${item.title}"  status="${item.status}"  class="cursor_pointer"   src="<ls:templateResource item='/common/default/images/blue_down.png'/> ">
		  		</c:when>
		  		<c:otherwise>
		  			<img  name="statusImg"   itemId="${item.id}"  itemName="${item.title}"  status="${item.status}"   class="cursor_pointer"  src="<ls:templateResource item='/common/default/images/yellow_up.png'/> ">
		  		</c:otherwise>
		  	</c:choose>
	    	 <a href="${pageContext.request.contextPath}/admin/indexjpg/update/${item.id}" title="修改"><img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
	        <a href='javascript:deleteById("${item.id}")' title="删除"><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
      	</display:column>
        </auth:auth>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
        </div>
	
	
<table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 首页中上部广告图片，文字均可以加上超链接<br>
   2. 创建首页中上部广告图片，注意:最多上传<b><%=PropertiesUtil.getObject(SysParameterEnum.MAX_INDEX_JPG,Integer.class)%></b>张图片<br>
   3. <img src="${pageContext.request.contextPath}/common/default/images/grid_refresh.png" title="文字连接">指向图片、文字连接地址<br>
   4. <img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "> 修改按钮<br>
   5. <img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "> 删除按钮<br>
   </td><tr></table> 
   
   <script language="JavaScript" type="text/javascript">
<!--
function deleteById(id) {
    if(confirm("  确定删除 ?")){
          window.location = "${pageContext.request.contextPath}/admin/indexjpg/delete/"+id ;
      }
  }
  
 	  $(document).ready(function(){
          	 $("img[name='statusImg']").click(function(event){
	          		$this = $(this);
	          		initStatus($this.attr("itemId"), $this.attr("itemName"),  $this.attr("status"), "${pageContext.request.contextPath}/admin/indexjpg/updatestatus/", event.target,"${pageContext.request.contextPath}");
	       			 }
	       		);
              	highlightTableRows("item");   
          });     
        //-->
</script>
</body>
</html>

