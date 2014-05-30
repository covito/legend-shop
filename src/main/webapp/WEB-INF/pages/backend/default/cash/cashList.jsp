<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <style type="text/css" media="all">
       @import url("<ls:templateResource item='/common/default/css/screen.css'/>");
    </style>
    <title>Cash列表</title>
</head>
<body>
    <form action="<ls:url address='/admin/cash/query'/>" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr>
			    	<th>
				    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
				    	<a href="<ls:url address='/admin/cash/query'/>">Cash</a>
			    	</th>
		    	</tr>
		    </thead>
		     <tbody><tr><td>
 <div align="left" style="padding: 3px">
<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        	<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            	商城&nbsp;<input type="text" name="userName" maxlength="50" value="${cash.userName}" />
            </auth:auth>
            <input type="submit" value="搜索"/>
            <input type="button" value="创建Cash" onclick='window.location="<ls:url address='/admin/cash/load'/>"'/>
 </div>
 </td></tr></tbody>
	    </table>
    </form>
    <div align="center">
          <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
		<display:table name="list" requestURI="/admin/cash/query" id="item" export="false" class="${tableclass}" style="width:100%">
	             <display:column title="CashId" property="cashId"></display:column>
      <display:column title="UserId" property="userId"></display:column>
      <display:column title="UserName" property="userName"></display:column>
      <display:column title="Code" property="code"></display:column>
      <display:column title="ShopId" property="shopId"></display:column>
      <display:column title="ProdId" property="prodId"></display:column>
      <display:column title="PartnerId" property="partnerId"></display:column>
      <display:column title="SubId" property="subId"></display:column>
      <display:column title="Detail" property="detail"></display:column>
      <display:column title="Money" property="money"></display:column>
      <display:column title="Status" property="status"></display:column>
      <display:column title="BeginTime" property="beginTime"></display:column>
      <display:column title="EndTime" property="endTime"></display:column>
      <display:column title="Ip" property="ip"></display:column>
      <display:column title="CreateTime" property="createTime"></display:column>

	    <display:column title="Action" media="html">
		      <a href="<ls:url address='/admin/cash/load/${item.cashId}'/>" title="修改">
		     		 <img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/>">
		      </a>
		      <a href='javascript:deleteById("${item.cashId}")' title="删除">
		      		<img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/>">
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
			            window.location = "<ls:url address='/admin/cash/delete/" + id + "'/>";
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


