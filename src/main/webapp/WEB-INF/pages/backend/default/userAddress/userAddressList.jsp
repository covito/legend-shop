<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
    <style type="text/css" media="all">
       @import url("<ls:templateResource item='/common/default/css/screen.css'/>");
    </style>
    <title>UserAddress列表</title>
</head>
<body>
    <form action="<ls:url address='/admin/userAddress/query'/>" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr>
			    	<th>
				    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
				    	<a href="<ls:url address='/admin/userAddress/query'/>">UserAddress</a>
			    	</th>
		    	</tr>
		    </thead>
		     <tbody><tr><td>
 <div align="left" style="padding: 3px">
<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        	<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            	商城&nbsp;<input type="text" name="userName" maxlength="50" value="${userAddress.userName}" />
            </auth:auth>
            <input type="submit" value="搜索"/>
            <input type="button" value="创建UserAddress" onclick='window.location="<ls:url address='/admin/userAddress/load'/>"'/>
 </div>
 </td></tr></tbody>
	    </table>
        
    </form>
    <div align="center">
          <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
		<display:table name="list" requestURI="/admin/userAddress/query" id="item" export="false" class="${tableclass}" style="width:100%">
	             <display:column title="AddrId" property="addrId"></display:column>
      <display:column title="UserId" property="userId"></display:column>
      <display:column title="UserName" property="userName"></display:column>
      <display:column title="Receiver" property="receiver"></display:column>
      <display:column title="SubAdds" property="subAdds"></display:column>
      <display:column title="SubPost" property="subPost"></display:column>
      <display:column title="ProvinceId" property="provinceId"></display:column>
      <display:column title="CityId" property="cityId"></display:column>
      <display:column title="Mobile" property="mobile"></display:column>
      <display:column title="Telphone" property="telphone"></display:column>
      <display:column title="Email" property="email"></display:column>
      <display:column title="Status" property="status"></display:column>
      <display:column title="CommonAddr" property="commonAddr"></display:column>
      <display:column title="CreateTime" property="createTime"></display:column>

	    <display:column title="Action" media="html">
		      <a href="<ls:url address='/admin/userAddress/load/${item.addrId}'/>" title="修改">
		     		 <img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/>">
		      </a>
		      <a href='javascript:deleteById("${item.addrId}")' title="删除">
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
			            window.location = "<ls:url address='/admin/userAddress/delete/" + id + "'/>";
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


