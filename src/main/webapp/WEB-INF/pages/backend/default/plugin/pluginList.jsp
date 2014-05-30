<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>

<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
     <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
 	<%@ include file="/WEB-INF/pages/common/dialog.jsp"%>
    <title>插件列表</title>
</head>
<body>
    <%
        Integer offset = 1;
    %>
	 <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 系统管理  &raquo; <a href="${pageContext.request.contextPath}/admin/system/plugin/query">插件列表</a></th></tr>
	    </thead>
	    </table>
    
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="pluginList" requestURI="/admin/system/plugin/query" id="item"
         export="false" class="${tableclass}" style="width:100%">
       <display:column title="顺序"   class="orderwidth">&nbsp;<%=offset++%>&nbsp;&nbsp;&nbsp;</display:column>
      <display:column title="名称" property="pluginConfig.pulginId"></display:column>
      <display:column title="作者" property="pluginConfig.provider"></display:column>
      <display:column title="版本" property="pluginConfig.pulginVersion"></display:column>
      <display:column title="更改后是否必须重启" property="pluginConfig.required"></display:column>
      <display:column title="描述" property="pluginConfig.description"></display:column>
      <display:column title="操作"  style="width: 80px">
      		<c:choose>
		  		<c:when test="${item.pluginConfig.status == 'Y' }">
		  			<img name="statusImg"  item_id="${item.pluginConfig.pulginId}"  itemName="${item.pluginConfig.pulginId}"  status="${item.pluginConfig.status}"  class="cursor_pointer"   src="<ls:templateResource item='/common/default/images/blue_down.png'/> ">
		  		</c:when>
		  		<c:otherwise>
		  			<img  name="statusImg"   item_id="${item.pluginConfig.pulginId}"  itemName="${item.pluginConfig.pulginId}"  status="${item.pluginConfig.status}"   class="cursor_pointer"  src="<ls:templateResource item='/common/default/images/yellow_up.png'/> ">
		  		</c:otherwise>
		  	</c:choose>
      </display:column>
    </display:table>
    </div>
    
    <script type="text/javascript">
    	 $(document).ready(function(){
          	$("img[name='statusImg']").click(function(event){
          	var item_id = $(this).attr("item_id");
          	var status = $(this).attr("status");
          	var desc;
          	var toStatus;
          	   if(status == 'Y'){
          	   		toStatus  = 'turnoff';
          	   		desc = $(this).attr("itemName")+' 下线?';
          	   }else{
          	       toStatus = 'turnon';
          	       desc = $(this).attr("itemName")+' 上线?';
          	   }
           	 art.dialog({
			    content: desc,
			    lock:false,
			    ok: function () {
				       jQuery.ajax({
							url:"${pageContext.request.contextPath}/admin/system/plugin/" + toStatus + "/" + item_id, 
							type:'get', 
							dataType : 'json', 
							async : false, //默认为true 异步   
							error:function(){
							alert('error');   
							},   
							success:function(data){
								if(data == 'Y'){
									$(event.target).attr("src","<ls:templateResource item='/common/default/images/blue_down.png'/>");
								}else if(data == 'N'){
									$(event.target).attr("src","<ls:templateResource item='/common/default/images/yellow_up.png'/>");
								}
							   	$(event.target).attr("status",data);
							}   
							});  
				    
			        return true;
			    },
			    cancelVal: '关闭',
			    cancel: true //为true等价于function(){}
			});
			
       			 }
       		 );
              	
       		 
          });
    </script>
    
</body>
</html>

