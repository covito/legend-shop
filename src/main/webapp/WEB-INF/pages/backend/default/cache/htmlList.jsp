<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <title>缓存列表</title>
</head>
<body>
    <%
        Integer offset = 1;
    %>
	 <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 系统管理  &raquo; <a href="${pageContext.request.contextPath}/admin/system/cache/query">缓存列表</a></th></tr>
	    </thead>
	    	     <tbody><tr><td>
 <div align="left" style="padding: 3px">
 <auth:auth ifAnyGranted="FA_SHOP">
 	 <input type="button" value="一键清除缓存"  onclick="javascript:clearHtmlCache('${requestPath}')"/>
<c:if test="${!rootPath}"><a href="javascript:enterUpFolder()"><b>上一层</b></a></c:if> &nbsp;  &nbsp;当前路径： ${requestPath} 
</auth:auth>
 </td></tr></tbody>
	    </table>
    
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
        <form id="queryhtml" action="${pageContext.request.contextPath}/admin/cache/queryhtml">
        <input type="hidden"  name="requestFolder"  id="requestFolder" value="${requestFolder }" />
         <input type="hidden"  name="fileName"  id="fileName"/>
         <input type="hidden"  name="direct"  id="direct"/>
    <display:table name="fileList" requestURI="/admin/cache/queryhtml" id="item"
         export="false" class="${tableclass}" style="width:100%">
       <display:column title="顺序"   class="orderwidth">&nbsp;<%=offset++%>&nbsp;&nbsp;&nbsp;</display:column>
      <display:column title="文件名称" property="fileName"></display:column>
      <display:column title="是否是文件" property="isFile"></display:column>
      <display:column title="操作" >
      		<c:choose>
      			<c:when test="${item.isFile }">
      				
      				<a href="javascript:clearHtmlCache('${requestPath}/${item.fileName }')">删除</a>
      			</c:when>
      			<c:otherwise>
      				<a href="javascript:enterFolder('${item.fileName }')">进入</a>
      			</c:otherwise>
      		</c:choose>
      </display:column>
    </display:table>
    </form>
    </div>
    
    <script type="text/javascript">
      function clearHtmlCache(path) {
	    jQuery.post("${pageContext.request.contextPath}/admin/file/deleteHtmlFile", {"filePath" : path}, function(retData){
	    	 if(retData == 0 ){
	    	 alert("删除Html成功！") ;
	          window.location.reload() ;
	       }else{
	          alert("删除Html失败！" + path) ;
	       }
	    });
	}
	
	 /**
	 * 进入下一层目录
	 */
	 function enterFolder(fileName) {
	 		var folderPath = document.getElementById("requestFolder").value;
	 		document.getElementById("requestFolder").value = folderPath + "/" + fileName;
	 		
	        document.getElementById("queryhtml").submit();
	 }
	 
	 /**
	 * 进入上一层目录
	 */
	 	 function enterUpFolder() {
	 		document.getElementById("direct").value = "up";
	        document.getElementById("queryhtml").submit();
	 }
    </script>
</body>
</html>

