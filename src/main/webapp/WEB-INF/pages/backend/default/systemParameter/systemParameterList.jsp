<%@page import="com.legendshop.spi.constants.SysParamGroupEnum"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>

<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <title>系统配置参数列表</title>
</head>
<body>
    <%
    	Integer offset = (Integer) request.getAttribute("offset");
            String groupId = (String)request.getAttribute("groupId");
    %>
	 <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 系统管理  &raquo; <a href="${pageContext.request.contextPath}/admin/system/systemParameter/query/<%=groupId %>">
	    	<%=SysParamGroupEnum.getName(groupId)%></a>
	    	</th></tr>
	    </thead>
	     <tbody><tr><td>
 <div align="left" style="padding: 3px">
<form action="${pageContext.request.contextPath}/admin/system/systemParameter/query/${groupId}" id="form1" method="post">
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
           &nbsp; 参数名称&nbsp;<input type="text" name="name" id="name" maxlength="50" size="50" value="${bean.name}" />
            <input type="submit" value="搜索"/>
    </form>
 </div>
 </td></tr></tbody>
	    </table>
    
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/system/systemParameter/query/${groupId}" id="item"
         export="false" class="${tableclass}" style="width:100%">
      <display:column title="顺序"  class="orderwidth"><%=offset++%></display:column>
      <display:column title="功能说明" property="memo"></display:column>
      <display:column title="参数名称" property="name"></display:column>
      <display:column title="参数值">
      	<c:choose>
      		<c:when test="${item.type == 'Selection'}">
      			  <ls:optionGroup type="label" required="true" cache="true"
	                beanName="${item.optional}" selectedValue="${item.value}"/>
      		</c:when>
      		<c:when test="${item.type == 'Password'}">
      			   <c:if test="${not empty item.value}">[protected]</c:if>
      		</c:when>
      		<c:otherwise>${item.value}</c:otherwise>
      	</c:choose>
      </display:column>
      <display:column title="操作" media="html">
      <a href= "${pageContext.request.contextPath}/admin/system/systemParameter/load/${item.name}" title="修改"><img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
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
            window.location = "${pageContext.request.contextPath}/admin/system/systemParameter/delete/"+id;
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

