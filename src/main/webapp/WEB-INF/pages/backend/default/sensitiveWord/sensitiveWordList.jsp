<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
    <title>敏感字过滤表列表</title>
</head>
	<%
			Integer offset = (Integer)request.getAttribute("offset");
	%>
<body>
    <form action="<ls:url address='/admin/system/sensitiveWord/query'/>" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr>
			    	<th>
				    	<a href="<ls:url address='/admin/system/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
				    	<a href="<ls:url address='/admin/system/sensitiveWord/query'/>">敏感字过滤表</a>
			    	</th>
		    	</tr>
		    </thead>
		    <tbody><tr><td>
		    	    <div align="left" style="padding: 3px">
				       	    <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
				       	    &nbsp; 关键字：
						<input type="text" name="words"  id="words" maxlength="50"  value="${sensitiveWord.words}" />
				            <input type="submit" value="搜索"/>
				            <input type="button" value="创建敏感字过滤表" onclick='window.location="<ls:url address='/admin/system/sensitiveWord/load'/>"'/>
				      </div>
		     </td></tr></tbody>
	    </table>
    </form>
    <div align="center">
          <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
		<display:table name="list" requestURI="/admin/system/sensitiveWord/query" id="item" export="false" sort="external" class="${tableclass}" style="width:100%">
	    
	 <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
     		<display:column title="敏感字" property="words"></display:column>
     		<display:column title="是否全局敏感字"  style="width:100px">
	     			<ls:optionGroup type="label" required="true" cache="true" beanName="YES_NO" selectedValue="${item.isGlobal}"/> 
     		</display:column>
	    <display:column title="操作" media="html"  style="width: 80px;">
		      <a href="<ls:url address='/admin/system/sensitiveWord/load/${item.sensId}'/>" title="修改">
		     		 <img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/>">
		      </a>
		      <a href='javascript:deleteById("${item.sensId}")' title="删除">
		      		<img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/>">
		      </a>
	      </display:column>
	    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
        <script language="JavaScript" type="text/javascript">
		
			  function deleteById(id) {
			      if(confirm("  确定删除 ?")){
			            window.location = "<ls:url address='/admin/system/sensitiveWord/delete/" + id + "'/>";
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

