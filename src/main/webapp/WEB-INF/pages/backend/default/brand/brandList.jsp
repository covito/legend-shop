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
    <title>品牌列表</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/brand/query" id="form1" method="post">
    	<table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; <a href="${pageContext.request.contextPath}/admin/brand/query">品牌管理</a></th></tr>
	    </thead>
	    <tbody><tr><td>
	    <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        <div align="left" style="padding: 3px">
        <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            商城&nbsp;<input type="text" name="userName" maxlength="50" value="${brand.userName}" />
            <input type="submit" value="搜索"/>
            </auth:auth>
              <auth:auth ifAnyGranted="FA_PROD">
            <input type="button" value="创建品牌" onclick='window.location="${pageContext.request.contextPath}/admin/brand/load"'/>
            </auth:auth>
            <input type="button" value="返回商品列表" onclick='window.location="<ls:url address='/admin/product/query'/>"'/>
            </div>
	    </td></tr></tbody>
	    </table>
        
    </form>
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/brand/query" id="item"
         export="true" class="${tableclass}" style="width:100%">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
       <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
      <display:column title="商城" property="userName"></display:column>
      </auth:auth>
      <display:column title="品牌" property="brandName"></display:column>
      <display:column title="备注" property="memo"></display:column>
       <display:column title="次序" property="seq" sortable="true" sortName="seq" style="width:50px"></display:column>
       <auth:auth ifAnyGranted="FA_PROD">
      <display:column title="操作" media="html" style="width:80px">
            <c:choose>
		  		<c:when test="${item.status == 1}">
		  			<img name="statusImg"  itemId="${item.brandId}"  itemName="${item.brandName}"  status="${item.status}"  class="cursor_pointer"   src="<ls:templateResource item='/common/default/images/blue_down.png'/> ">
		  		</c:when>
		  		<c:otherwise>
		  			<img  name="statusImg"   itemId="${item.brandId}"  itemName="${item.brandName}"  status="${item.status}"   class="cursor_pointer"  src="<ls:templateResource item='/common/default/images/yellow_up.png'/> ">
		  		</c:otherwise>
		  	</c:choose>
      <a href= "${pageContext.request.contextPath}/admin/brand/update/${item.brandId}" title="修改"><img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
      	<a href='javascript:deleteById("${item.brandId}")' title="删除"><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
      </display:column>
      </auth:auth>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
         <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 品牌带有一个品牌图片<br>
   2. 品牌可以挂在商品三级类型上，在用户选择了三级类型之后出现对应的品牌<br>
   3. <img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "> 修改按钮<br>
   4. <img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "> 删除按钮，删除品牌，如果删除品牌则不影响其下的商品<br>
   </td><tr></table> 
   
   <script language="JavaScript" type="text/javascript">
<!--
  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/brand/delete/"+id;
        }
    }
        
      $(document).ready(function(){
          		$("img[name='statusImg']").click(function(event){
	          		$this = $(this);
	          		initStatus($this.attr("itemId"), $this.attr("itemName"),  $this.attr("status"), "${pageContext.request.contextPath}/admin/brand/updatestatus/", event.target,"${pageContext.request.contextPath}");
	       			 }
	       		);
           highlightTableRows("item");
          });
//-->
</script>
</body>
</html>

