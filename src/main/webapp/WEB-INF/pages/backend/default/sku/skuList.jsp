<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>

<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <style type="text/css" media="all">
       @import url("<ls:templateResource item='/css/screen.css'/>");
    </style>
    <title>单品SKU表列表</title>
</head>
<body>
    <form action="<ls:url address='/admin/sku/query'/>" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr>
			    	<th>
				    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
				    	<a href="<ls:url address='/admin/sku/query'/>">单品SKU表</a>
			    	</th>
		    	</tr>
		    </thead>
	    </table>
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        	<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            	商城名称&nbsp;<input type="text" name="userName" maxlength="50" value="${sku.userName}" />
            </auth:auth>
            <input type="submit" value="搜索"/>
            <input type="button" value="创建单品SKU表" onclick='window.location="<ls:url address='/admin/sku/load'/>"'/>
            <br>
    </form>
    <div align="center">
          <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
		<display:table name="list" requestURI="/admin/sku/query" id="item" export="false" class="${tableclass}" style="width:100%">
	       
	    
     		<display:column title="SkuId" property="skuId"></display:column>
     		<display:column title="ProdId" property="prodId"></display:column>
     		<display:column title="Properties" property="properties"></display:column>
     		<display:column title="Price" property="price"></display:column>
     		<display:column title="Stocks" property="stocks"></display:column>
     		<display:column title="ActualStocks" property="actualStocks"></display:column>
     		<display:column title="Name" property="name"></display:column>
     		<display:column title="Status" property="status"></display:column>
     		<display:column title="SkuDeliveryTime" property="skuDeliveryTime"></display:column>
     		<display:column title="OuterId" property="outerId"></display:column>
     		<display:column title="ModifyDate" property="modifyDate"></display:column>
     		<display:column title="RecDate" property="recDate"></display:column>
	       
	       
	       
	    <display:column title="Action" media="html">
		      <a href="<ls:url address='/admin/sku/load/${item.skuId}'/>" title="修改">
		     		 <img alt="修改" src="<ls:templateResource item='/img/grid_edit.png'/>">
		      </a>
		      <a href='javascript:deleteById("${item.skuId}")' title="删除">
		      		<img alt="删除" src="<ls:templateResource item='/img/grid_delete.png'/>">
		      </a>
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
			            window.location = "<ls:url address='/admin/sku/delete/" + id + "'/>";
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

