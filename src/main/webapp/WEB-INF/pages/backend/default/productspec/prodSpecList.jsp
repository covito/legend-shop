<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
     <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <script src="<ls:templateResource item='/common/js/recordstatus.js'/>" type="text/javascript"></script>
 	<%@ include file="/WEB-INF/pages/common/dialog.jsp"%>
    <title>商品规格列表</title>
</head>
<body>
    <%
        Integer offset = 1;
    %>
	<table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品规格管理  &raquo; 
    	  <c:choose>
    		<c:when test="${dynamicTemp.type  == 1}"><a href="<ls:url address='/admin/prodspec/query/1'/>" >动态属性</a></c:when>
    		<c:otherwise><a href="<ls:url address='/admin/prodspec/query/2'/>" >动态参数</a></c:otherwise>
    	</c:choose>
		</th></tr>
    </thead>
    <tbody>
    	<tr><td>
    	    <form action="<ls:url address='/admin/prodspec/query/${dynamicTemp.type}'/>" id="form1" method="post">
    	    <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}"/>
			<div align="left" style="padding: 3px;">
		    <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
               商城&nbsp;
            <input type="text" name="userName" maxlength="50" value="${dynamicTemp.userName}" size="15"/>
            </auth:auth>
			<auth:auth ifNotGranted="F_VIEW_ALL_DATA">
			  商品类型&nbsp;
		    <lb:sortOption id="sortId"  type="P"  selectedValue="${dynamicTemp.sortId}"/>
        </auth:auth>  
        </div><div align="left" style="padding: 3px">
			商品规格名称&nbsp;
			<input type="text" name="name" id="name" maxlength="50" value="${dynamicTemp.name}" size="32"/>
			状态
			<select id="status" name="status">
					<option value="">请选择</option>	
			    	<option value="1">上线</option>
	      			<option value="0" >下线</option>
			</select>		
			<input type="submit" value="搜索"/>
		   <input type="button" value="创建商品规格" onclick="window.location='${pageContext.request.contextPath}/admin//prodspec/createattribute/${dynamicTemp.type}'"/>
			</div>
			   </form>
    	</td></tr>
    </tbody>
    </table>
    
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/prodspec/query" id="item"
         export="false" class="${tableclass}" style="width:100%">
       <display:column title="顺序"  class="orderwidth">&nbsp;<%=offset++%>&nbsp;&nbsp;&nbsp;</display:column>
      <display:column title="名称" property="name"></display:column>
      <display:column title="分类" property="sortName"></display:column>
      <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
      <display:column title="用户名" property="userName"></display:column>
      </auth:auth>
      <display:column title="操作"  style="width: 80px">
      <auth:auth ifAnyGranted="FA_PROD">
		  <c:choose>
		  		<c:when test="${item.status == 1}">
		  			<img name="statusImg"  itemId="${item.id}"  itemName="${item.name}"  status="${item.status}"  class="cursor_pointer"   src="<ls:templateResource item='/common/default/images/blue_down.png'/> ">
		  		</c:when>
		  		<c:otherwise>
		  			<img  name="statusImg"   itemId="${item.id}"  itemName="${item.name}"  status="${item.status}"   class="cursor_pointer"  src="<ls:templateResource item='/common/default/images/yellow_up.png'/> ">
		  		</c:otherwise>
		  	</c:choose>
		  	<a href= "${pageContext.request.contextPath}/admin/prodspec/attribute/${item.id}" title="修改"><img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
           <a href='javascript:deleteById("${item.id}")'><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> " title="删除商品规格"></a>
      </auth:auth>
      </display:column>
    </display:table>
    </div>
    
    <script type="text/javascript">
    	 $(document).ready(function(){
    		  $("img[name='statusImg']").click(function(event){
	          		$this = $(this);
	          		initStatus($this.attr("itemId"), $this.attr("itemName"),  $this.attr("status"), "${pageContext.request.contextPath}/admin/prodspec/updatestatus/", event.target,"${pageContext.request.contextPath}");
	       			 }
	       		);
       		  parseStatus('${dynamicTemp.status}');
          });
          
		  function parseStatus(statusValue){
			$("#status").val(statusValue); 
			}	
			
			
	function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/prodspec/delete/"+id;
        }
    }	
    </script>
    
</body>
</html>

