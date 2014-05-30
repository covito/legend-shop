<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
	<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
    <script src="<ls:templateResource item='/common/js/recordstatus.js'/>" type="text/javascript"></script>
     <%@ include file="/WEB-INF/pages/common/dialog.jsp"%>
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
    <title>商品图片列表</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/imgFile/save" id="form1" method="post" enctype="multipart/form-data">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; <a href="<ls:url address='/admin/product/query'/>">商品管理</a> &raquo; <a href="${pageContext.request.contextPath}/admin/product/load">创建商品</a>
    	  <c:if test="${prod.name != null}">&raquo; <a href= "${pageContext.request.contextPath}/views/${prod.prodId}" target="_blank">${prod.name}</a></c:if>
    	</th></tr>
    </thead>
    <tr>
      <td>
		<jsp:include page="/admin/product/createsetp">
    		<jsp:param name="step" value="2"/>
    	</jsp:include>
      </td>
    </tr>
       <tr>
         <td>
          商品图片 <font color="ff0000">*</font> <input type="file" name="file" id="file" size="30"/>   
           <input type="hidden" name="productId" id="productId" value="${prod.prodId}"/><input type="submit" value="提交">
         </td>
       </tr>
    </table>
             
    </form>
     <form action="${pageContext.request.contextPath}/admin/imgFile/query" id="queryImgForm" method="post">
	     <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
     </form>
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/imgFile/query" id="item"
         export="false" class="${tableclass}" style="width:100%">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
       <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
     	 <display:column title="商城" property="userName"></display:column>
      </auth:auth>
      <display:column title="文件"><a href="<ls:photo item='${item.filePath}'/>" target="_blank">
      <img src="<ls:images item='${item.filePath}'/>" height="150px" width="150px"/></a>
      </display:column>
      <display:column title="文件类型" property="fileType"></display:column>
      <display:column title="文件大小" property="fileSize"></display:column>
      <display:column title="上传时间"><fmt:formatDate value="${item.upoadTime}" pattern="yyyy-MM-dd HH:mm" /></display:column>
      <display:column title="操作" media="html" style="width:50px">
      <auth:auth ifAnyGranted="FA_PROD">
                  <c:choose>
		  		<c:when test="${item.status == 1}">
		  			<img name="statusImg"  itemId="${item.fileId}"  itemName="图片"  status="${item.status}"  class="cursor_pointer"   src="<ls:templateResource item='/common/default/images/blue_down.png'/> ">
		  		</c:when>
		  		<c:otherwise>
		  			<img  name="statusImg"   itemId="${item.fileId}"  itemName="图片"  status="${item.status}"   class="cursor_pointer"  src="<ls:templateResource item='/common/default/images/yellow_up.png'/> ">
		  		</c:otherwise>
		  	</c:choose>
      	<a href='javascript:deleteById("${item.fileId}")' title="删除"><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
      </auth:auth>
      </display:column>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
       <table style="width: 100%; border: 0px">
           <tr>
		      <td><div align="center">
		        <auth:auth ifAnyGranted="FA_PROD"> 
		            <input type="button" value="上一步" onclick="javascript:submitPrevious()"/>
		            <input type="button" value="下一步" onclick="javascript:submitNext()"/>
		        </auth:auth> 
		      </div></td>
		    </tr>
       </table>
         <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 点击选择，再点击提交则为商品增加一个图片，大小不能超过2M<br>
   3. 支持上传的图片格式有<font color="red">.jpg,.gif,.png,.jpeg</font>,其余文件格式不支持<br>
   2. 图片处于上线状态则会在商品的详细信息页面出现，处于下线状态则不会在页面上显示<br>
   3. 图片删除则不会再显示<br>
   </td><tr></table> 
   
   <script language="JavaScript" type="text/javascript">
<!--
  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/imgFile/delete/"+id;
        }
    }
  
  function submitPrevious(){
 		 window.location = "${pageContext.request.contextPath}/admin/product/update/${prod.prodId}";
  }  
  
    function submitNext(){
 		 window.location = "${pageContext.request.contextPath}/admin/dynamic/loadAttribute/${prod.prodId}";
  }  
    
   function imgPager(curPageNO){
			   document.getElementById("curPageNO").value=curPageNO;
			   document.getElementById("queryImgForm").submit();
	 }
    
$.validator.setDefaults({
    });

    $(document).ready(function() {
    jQuery("#form1").validate({
            rules: {
            file: "required"
        },
        messages: {
            file: {
                required: "请上传商品图片"
            }
        }
    });
 highlightTableRows("item"); 
      $("img[name='statusImg']").click(function(event){
	          		$this = $(this);
	          		initStatus($this.attr("itemId"), $this.attr("itemName"),  $this.attr("status"), "${pageContext.request.contextPath}/admin/imgFile/updatestatus/", event.target,"${pageContext.request.contextPath}");
	       			 }
	 );
});


//-->
</script>
   
</body>
</html>

