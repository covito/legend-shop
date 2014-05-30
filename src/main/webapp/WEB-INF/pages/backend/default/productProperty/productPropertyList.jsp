<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
    <title>属性名列表</title>
</head>
<body>
    <form action="<ls:url address='/admin/productProperty/query'/>" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr>
			    	<th>
				    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; 规格管理  &raquo; 
				    	<a href="<ls:url address='/admin/productProperty/query'/>">产品规格</a>
			    	</th>
		    	</tr>
		    </thead>
	    </table>
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
            规格名称&nbsp;<input type="text" name="propName" maxlength="50" value="${productProperty.propName}" />
            <input type="submit" value="搜索"/>
            <input type="button" value="创建规格" onclick='window.location="<ls:url address='/admin/productProperty/load'/>"'/>
            <br>
    </form>
    <div align="center">
          <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
		<display:table name="list" requestURI="/admin/productProperty/query" id="item" export="false" class="${tableclass}" style="width:100%">
     		<display:column title="规格名称" property="propName"></display:column>
     		<display:column title="别名" property="memo"></display:column>
     		<display:column title="是否必须" property="isRequired"></display:column>
     		<display:column title="是否多选" property="isMulti"></display:column>
     		<display:column title="顺序" property="sequence"></display:column>
     		<display:column title="状态" property="status"></display:column>
     		<display:column title="类型" property="type"></display:column>
	   <auth:auth ifAnyGranted="FA_PROD">
	      <display:column title="操作" media="html" style="width: 80px;">
		  	<c:choose>
		  		<c:when test="${item.status == 1}">
		  			<img name="statusImg"  item_id="${item.propId}"  itemName="${item.propName}"  status="${item.status}"  class="cursor_pointer"   src="<ls:templateResource item='/common/default/images/blue_down.png'/> ">
		  		</c:when>
		  		<c:otherwise>
		  			<img  name="statusImg"   item_id="${item.propId}"  itemName="${item.propName}"  status="${item.status}"   class="cursor_pointer"  src="<ls:templateResource item='/common/default/images/yellow_up.png'/> ">
		  		</c:otherwise>
		  	</c:choose>
		  	 <a href= "${pageContext.request.contextPath}/admin/productProperty/load/${item.propId}" title="修改"><img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
		     <a href='javascript:confirmDelete("${item.propId}","${item.propName}")' title="删除"><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
	      </display:column>
      </auth:auth>
	    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
        <script language="JavaScript" type="text/javascript">
			<!--
			  function deleteById(id) {
			      if(confirm("  确定删除 ?")){
			            window.location = "<ls:url address='/admin/productProperty/delete/" + id + "'/>";
			        }
			    }
			
			        function pager(curPageNO){
			            document.getElementById("curPageNO").value=curPageNO;
			            document.getElementById("form1").submit();
			        }
			//-->
		</script>
</body>
</html>

