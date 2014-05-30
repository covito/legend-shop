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
    <title>CashFlow列表</title>
</head>
<body>
    <form action="<ls:url address='/admin/cashFlow/query'/>" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr>
			    	<th>
				    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
				    	<a href="<ls:url address='/admin/cashFlow/query'/>">CashFlow</a>
			    	</th>
		    	</tr>
		    </thead>
		     <tbody><tr><td>
 <div align="left" style="padding: 3px">
<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        	<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            	商城&nbsp;<input type="text" name="userName" maxlength="50" value="${cashFlow.userName}" />
            </auth:auth>
            <input type="submit" value="搜索"/>
            <input type="button" value="创建CashFlow" onclick='window.location="<ls:url address='/admin/cashFlow/load'/>"'/>
 </div>
 </td></tr></tbody>
	    </table>
        
    </form>
    <div align="center">
          <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
		<display:table name="list" requestURI="/admin/cashFlow/query" id="item" export="false" class="${tableclass}" style="width:100%">
	             <display:column title="FlowId" property="flowId"></display:column>
      <display:column title="UserId" property="userId"></display:column>
      <display:column title="UserName" property="userName"></display:column>
      <display:column title="AdminId" property="adminId"></display:column>
      <display:column title="DetailId" property="detailId"></display:column>
      <display:column title="Detail" property="detail"></display:column>
      <display:column title="Direction" property="direction"></display:column>
      <display:column title="Money" property="money"></display:column>
      <display:column title="Action" property="action"></display:column>
      <display:column title="CreateTime" property="createTime"></display:column>

	    <display:column title="Action" media="html">
		      <a href="<ls:url address='/admin/cashFlow/load/${item.flowId}'/>" title="修改">
		     		 <img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/>">
		      </a>
		      <a href='javascript:deleteById("${item.flowId}")' title="删除">
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
			            window.location = "<ls:url address='/admin/cashFlow/delete/" + id + "'/>";
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


