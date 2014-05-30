<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <script src="<ls:templateResource item='/plugins/My97DatePicker/WdatePicker.js'/>" type="text/javascript"></script>
    <title>用户访问历史列表</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/visitLog/query" id="form1" method="post">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 报表管理  &raquo; <a href="${pageContext.request.contextPath}/admin/visitLog/query">浏览历史</a></th></tr>
    </thead>
     <tbody><tr><td>
<div align="left" style="padding: 3px">
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
         &nbsp; 商城 <input type="text" name="shopName" maxlength="50" value="${bean.shopName}" />
        </auth:auth>
        	&nbsp;开始&nbsp;
			 <input readonly="readonly"  name="startTime" id="startTime" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>' />
			&nbsp;结束
			 <input readonly="readonly" name="endTime" id="endTime" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${bean.endTime}" pattern="yyyy-MM-dd"/>' />
            </div><div align="left" style="padding: 3px">
            用户名&nbsp;<input type="text" name="userName" maxlength="50" value="${bean.userName}" />
           
            &nbsp;页面
                <select id="page" name="page">
				  <ls:optionGroup type="select" required="false" cache="true"
	                beanName="VISIT_LOG_TYPE" selectedValue="${bean.page}"/>
	            </select>
	        &nbsp;商品
	        <input type="text" name="productName" maxlength="50" value="${bean.productName}" />
            <input type="submit" value="搜索"/>
            </div>
 </td></tr></tbody>
    </table>
    </form>
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/visitLog/query" id="item"
         export="true"  class="${tableclass}" style="width:100%" sort="external">
      <display:column title="顺序"  class="orderwidth"><%=offset++%></display:column>
      <display:column title="用户名" property="userName" sortable="true" sortName="userName"></display:column>
      <display:column title="IP" property="ip" sortable="true" sortName="ip"></display:column>
      <display:column title="国家" property="country" sortable="true" sortName="country"></display:column>
      <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
      <display:column title="地区" property="area" sortable="true" sortName="area"></display:column>
      	<display:column title="商城名" property="shopName" sortable="true" sortName="shopName"></display:column>
      </auth:auth>
      <display:column title="商品名" >
      	<c:if test="${item.productName != null}">
      		<a href="${pageContext.request.contextPath}/views/${item.productId}"  target="_blank">${item.productName}</a>
      	</c:if>
      </display:column>
      <display:column title="页面">
      	<c:if test="${item.page == 0}">首页</c:if>
      	<c:if test="${item.page == 1}">商品</c:if>
      </display:column>
      <display:column title="日期">
      	<fmt:formatDate value="${item.date}" pattern="yyyy-MM-dd HH:mm"/>
      </display:column>
      <display:column title="访问次数" property="visitNum">
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
     <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 页面访问历史，记录用户于何时何地访问您的商城<br>
   2. 如果是系统用户访问，可以在论坛中找到该用户的联系方式<br>
   </td><tr></table>    
     <script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/visitLog/delete/"+id;
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

