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
    <title>二级分类列表</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/nsort/query" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; 
    	<a href="${pageContext.request.contextPath}/admin/sort/query">类型管理</a> &raquo; 
    	<a href="${pageContext.request.contextPath}/admin/nsort/query?sortId=${param.sortId}">二级商品类型管理</a></th></tr>
    </thead>
     <tbody><tr><td>
 <div align="left" style="padding: 3px">
 <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        <input type="hidden" id="sortId" name="sortId" value="${nsort.sortId}" />
       商品分类： <b>${nsort.sortName}</b>，  二级类型
            <input type="text" name="nsortName"  id="nsortName" maxlength="50" value="${name}" />
            <input type="submit" value="搜索"/>
            <input type="button" value="创建二级商品类型" onclick='window.location="${pageContext.request.contextPath}/admin/nsort/load?sortId=${nsort.sortId}"'/>
 </div>
 </td></tr></tbody>
    </table>
    </form>

    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/nsort/query" id="parent"
         export="false" class="${tableclass}" style="width:100%">
      <display:column title="顺序"  class="orderwidth"><%=offset++%></display:column>
      <display:column title="二级商品类型" property="nsortName"></display:column>
      <display:column title="次序" property="seq"></display:column>
      <display:column title="分类代表">
            	<ls:optionGroup type="label" required="true" cache="true"
	                beanName="YES_NO" selectedValue="${parent.sortDeputy}" defaultDisp="否"/>
      </display:column>
      <c:set var="subSortList" value="${parent.subSort}" scope="request"/>
      <display:column title="三级商品类型" >
      <c:if test="${fn:length(subSortList) > 0}">
	    <display:table name="subSortList"  id="child" cellpadding="0" cellspacing="0">
	      <display:column title="三级商品类型" property="nsortName"></display:column>
	      <display:column title="次序" property="seq"></display:column>
	      <display:column title="操作" media="html" autolink="true" style="width:100px">
	      <auth:auth ifAnyGranted="FA_SHOP">
		  <c:choose>
		  		<c:when test="${child.status == 1}">
		  			<img name="statusImg"  itemId="${child.nsortId}"  itemName="${child.nsortName}"  status="${child.status}"  class="cursor_pointer"   src="<ls:templateResource item='/common/default/images/blue_down.png'/> ">
		  		</c:when>
		  		<c:otherwise>
		  			<img  name="statusImg"   itemId="${child.nsortId}"  itemName="${child.nsortName}"  status="${child.status}"   class="cursor_pointer"  src="<ls:templateResource item='/common/default/images/yellow_up.png'/> ">
		  		</c:otherwise>
		  	</c:choose>
	      <a href= "${pageContext.request.contextPath}/admin/nsort/update/${child.nsortId}"><img alt="修改三级商品类型" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> " title="修改三级商品类型"></a>
	      <a href='javascript:deleteById("${child.nsortId}")'><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> " title="删除三级商品类型"></a>
	      </auth:auth>
      </display:column>
	    </display:table>
	    </c:if>
      </display:column>
      <display:column title="操作" media="html" style="width:100px">
      <auth:auth ifAnyGranted="FA_SHOP">
		  <c:choose>
		  		<c:when test="${parent.status == 1}">
		  			<img name="statusImg"  itemId="${parent.nsortId}"  itemName="${parent.nsortName}"  status="${parent.status}"  class="cursor_pointer"   src="<ls:templateResource item='/common/default/images/blue_down.png'/> ">
		  		</c:when>
		  		<c:otherwise>
		  			<img  name="statusImg"   itemId="${parent.nsortId}"  itemName="${parent.nsortName}"  status="${parent.status}"   class="cursor_pointer"  src="<ls:templateResource item='/common/default/images/yellow_up.png'/> ">
		  		</c:otherwise>
		  	</c:choose>
      <a href= "${pageContext.request.contextPath}/admin/nsort/load?sortId=${nsort.sortId}&parentNsortId=${parent.nsortId}"><img alt="创建三级商品类型" src="${pageContext.request.contextPath}/common/default/images/grid_add.png" title="创建三级商品类型"></a>
      <a href= "${pageContext.request.contextPath}/admin/nsort/update/${parent.nsortId}"><img alt="修改二级商品类型" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> " title="修改二级商品类型"></a>
      <a href='javascript:deleteById("${parent.nsortId}")'><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> " title="删除二级商品类型"></a>
      </auth:auth>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
    
     <script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/nsort/delete/"+id;
        }
    }

         $(document).ready(function(){
         	       $("img[name='statusImg']").click(function(event){
	          		$this = $(this);
	          		initStatus($this.attr("itemId"), $this.attr("itemName"),  $this.attr("status"), "${pageContext.request.contextPath}/admin/nsort/updatestatus/", event.target,"${pageContext.request.contextPath}");
	       			 }
	       		);
	       		highlightTableRows("parent");  
      		    highlightTableRows("child");  
       		 
          });
//-->
</script>
    
</body>
</html>

