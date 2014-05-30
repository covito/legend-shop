<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>

<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <title>PayType 列表</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/paytype/query" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/paytype/query">支付管理</a></th></tr>
    </thead>
     <tbody><tr><td>
 <div align="left" style="padding: 3px">
<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
           <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
           	用户名&nbsp;<input type="text" name="userName" maxlength="50" value="${bean.userName}" /> 
           	<input type="submit" value="搜索"/>
         </auth:auth>
         <auth:auth ifAnyGranted="FA_PAY">
		 		  <input type="button" value="创建支付方式" onclick='window.location="${pageContext.request.contextPath}/admin/paytype/load"'/>
		   </auth:auth>
 </div>
 </td></tr></tbody>
    </table>
        
    </form>
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/paytype/query" id="item"
         export="true" class="${tableclass}" style="width:100%" sort="external">
      <display:column title="顺序"  class="orderwidth"><%=offset++%></display:column>
       <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
      	<display:column title="用户名" property="userName"></display:column>
      </auth:auth>
      <display:column title="支付方式" property="payTypeName"></display:column>
      <display:column title="合作身份者ID(Partner)" property="partner"></display:column>
      <display:column title="安全检验码" property="validateKey"></display:column>
      <display:column title="签约账号" property="sellerEmail"></display:column>
      <auth:auth ifAnyGranted="FA_PAY">
		      <display:column title="操作" media="html" style="width:40px">
		      <a href= "${pageContext.request.contextPath}/admin/paytype/load/${item.payId}" title="修改"><img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
		      	<a href='javascript:deleteById("${item.payId}")' title="删除"><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
		      </display:column>
          </auth:auth>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
        </div>
       <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 创建支付方式，每个支付方式只需建立一个，建立之后用户在订购商品时可以选择该支付方式<br>
   2. <img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "> 修改按钮<br>
   3. <img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "> 删除按钮<br>
   </td><tr></table> 
   <script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/paytype/delete/"+id;
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

