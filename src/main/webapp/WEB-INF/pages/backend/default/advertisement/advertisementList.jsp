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
    <title>广告列表</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/advertisement/query" id="form1" method="post">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/advertisement/query">广告管理</a></th></tr>
    </thead>
     <tbody><tr><td>
  <div align="left" style="padding: 3px">
   <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
                <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            商城&nbsp;
            <input type="text" name="userName" maxlength="50" value="${bean.userName}" />
            <input type="submit" value="搜索"/>
            </auth:auth>
             <auth:auth ifAnyGranted="FA_SHOP">
            <input type="button" value="创建广告" onclick='window.location="${pageContext.request.contextPath}/admin/advertisement/load"'/>
            </auth:auth>
 </div>
 </td></tr></tbody>
    </table>
    </form>
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
        <c:if test="${not empty list}">
		    <display:table name="list" requestURI="/admin/advertisement/query" id="item"
		         export="true" class="${tableclass}" style="width:100%">
		      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
		       <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
		          <display:column title="商城" property="userName"  sortable="true"></display:column>
              </auth:auth>
		      <display:column title="类型"  sortable="true">
		      	<ls:optionGroup type="label" required="true" cache="true"
	                beanName="ADVERTISEMENT_TYPE" selectedValue="${item.type}" defaultDisp=""/>
		      </display:column>
		      <display:column title="链接地址"><a href="${item.linkUrl}" target="_blank">${item.linkUrl }</a></display:column>
		      <display:column title="标题" property="title" sortable="true"></display:column>
              <display:column title="图片">
		      		<a href="<ls:photo item='${item.picUrl}'/>" target="_blank"><img src="${pageContext.request.contextPath}/common/default/images/filter_search.png"></a></a>
		      </display:column>
		          <auth:auth ifAnyGranted="FA_SHOP">
		      <display:column title="操作" media="html"  style="width: 80px">
		            	 <c:choose>
					  		<c:when test="${item.enabled == 1}">
					  			<img name="statusImg"  itemId="${item.id}"  itemName="${item.title}"  status="${item.enabled}"  class="cursor_pointer"   src="<ls:templateResource item='/common/default/images/blue_down.png'/> ">
					  		</c:when>
					  		<c:otherwise>
					  			<img  name="statusImg"   itemId="${item.id}"  itemName="${item.title}"  status="${item.enabled}"   class="cursor_pointer"  src="<ls:templateResource item='/common/default/images/yellow_up.png'/> ">
					  		</c:otherwise>
					  	</c:choose>
		    	  <a href= "${pageContext.request.contextPath}/admin/advertisement/load/${item.id}" title="修改"><img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
		          <a href='javascript:deleteById("${item.id}")' title="删除"><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
		      </display:column>
		      	 </auth:auth>
		    </display:table>
        </c:if>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
  <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 设置首页、商品、商品详细信息页面的广告位<br>
   2. 广告处于上线状态页面可见，处于下线状态页面不可见<br>
   </td><tr></table> 
    <script language="JavaScript" type="text/javascript">
<!--
  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/advertisement/delete/"+id;
        }
    }
    
    $(document).ready(function(){
	          $("img[name='statusImg']").click(function(event){
	          		$this = $(this);
	          		initStatus($this.attr("itemId"), $this.attr("itemName"),  $this.attr("status"), "${pageContext.request.contextPath}/admin/advertisement/updatestatus/", event.target,"${pageContext.request.contextPath}");
	       			 }
	       		);
	       		 highlightTableRows("item");  
          });     
    
//-->
</script>
</body>
</html>

