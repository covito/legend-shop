<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
     <script src="<ls:templateResource item='/common/js/recordstatus.js'/>" type="text/javascript"></script>
     <%@ include file="/WEB-INF/pages/common/dialog.jsp"%>
    <title>网站导航列表</title>
</head>
<body>
	<%
			Integer offset = (Integer)request.getAttribute("offset");
	%>
    <form action="<ls:url address='/admin/system/navigation/query'/>" id="form1" method="post">
    	<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}">
        <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr>
			    	<th>
				    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
				    	<a href="<ls:url address='/admin/system/navigation/query'/>">网站导航</a>
			    	</th>
		    	</tr>
		    </thead>
		    <tbody><tr><td>
		    	    <div align="left" style="padding: 3px">
				        <input type="button" value="创建网站导航" onclick='window.location="<ls:url address='/admin/system/navigation/load'/>"'/>
				      </div>
		     </td></tr></tbody>
	    </table>
    </form>
    <div align="center">
          <%@ include file="/WEB-INF/pages/common/messages.jsp"%>	    
		<display:table name="list" requestURI="/admin/system/navigation/query"  id="parent" export="false" sort="external" class="${tableclass}" style="width:100%">
	    	 <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
     		<display:column title="名称" property="name"></display:column>
     		<display:column title="次序" property="seq" ></display:column>  		
		<c:set var="subItemList" value="${parent.subItems}" scope="request"/>
      <display:column title="二级导航" >
      <c:if test="${fn:length(subItemList) > 0}">
	    <display:table name="subItemList"  id="child" cellpadding="0" cellspacing="0">
	      <display:column title="名称" property="name"></display:column>
	      <display:column title="次序" property="seq" style="width:40px"></display:column>
	      <display:column title="操作" media="html" autolink="true" style="width:100px">
		  <c:choose>
		  		<c:when test="${child.status == 1}">
		  			<img name="childStatusImg"  itemId="${child.itemId}"  itemName="${child.name}"  status="${child.status}"  class="cursor_pointer"   src="<ls:templateResource item='/common/default/images/blue_down.png'/> ">
		  		</c:when>
		  		<c:otherwise>
		  			<img  name="childStatusImg"   itemId="${child.itemId}"  itemName="${child.name}"  status="${child.status}"   class="cursor_pointer"  src="<ls:templateResource item='/common/default/images/yellow_up.png'/> ">
		  		</c:otherwise>
		  	</c:choose>
		  	  <a href="<ls:url address='/admin/system/navigationItem/load/${child.itemId}'/>" title="修改">
		     		 <img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/>">
		      </a>
	      <a href='javascript:deleteBychildId("${child.itemId}")'><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> " title="删除"></a>
      </display:column>
	    </display:table>
	    </c:if>
      </display:column>	
	    <display:column title="操作" media="html" style="width:100px">
	      <c:choose>
		  		<c:when test="${parent.status == 1}">
		  			<img name="parentStatusImg" itemId="${parent.naviId}" itemName="${parent.name}" status="${parent.status}"  class="cursor_pointer"   src="<ls:templateResource item='/common/default/images/blue_down.png'/> ">
		  		</c:when>
		  		<c:otherwise>
		  			<img  name="parentStatusImg"  itemId="${parent.naviId}" itemName="${parent.name}"  status="${parent.status}"   class="cursor_pointer"  src="<ls:templateResource item='/common/default/images/yellow_up.png'/> ">
		  		</c:otherwise>
		  	</c:choose>
		  	<a href="<ls:url address='/admin/system/navigation/querysearth/${parent.naviId}'/>" title="创建二级导航">
		  	<img src="<ls:templateResource item='/common/default/images/grid_add.png'/>">
		  	</a>
		      <a href="<ls:url address='/admin/system/navigation/load/${parent.naviId}'/>" title="修改">
		     		 <img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/>">
		      </a>
		      <a href='javascript:deleteById("${parent.naviId}")' title="删除">
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
			      if(confirm(" 确定删除 ?")){
			            window.location = "<ls:url address='/admin/system/navigation/delete/" + id + "'/>";
			        }
			    }
			
			  function pager(curPageNO){
			            document.getElementById("curPageNO").value=curPageNO;
			            document.getElementById("form1").submit();
			        }
			        
			   function deleteBychildId(id) {
			      if(confirm(" 确定删除 ?")){
			            window.location = "<ls:url address='/admin/system/navigationItem/delete/" + id + "'/>";
			        }
			    }
			        
			  jQuery(document).ready(function(){
          	    $("img[name='parentStatusImg']").click(function(event){
	          		$this = $(this);
	          		initStatus($this.attr("itemId"), $this.attr("itemName"),  $this.attr("status"), "${pageContext.request.contextPath}/admin/system/navigation/updatestatus/", event.target,"${pageContext.request.contextPath}");
	       			 }
	       		);
	       		
	       		 $("img[name='childStatusImg']").click(function(event){
	          		$this = $(this);
	          		initStatus($this.attr("itemId"), $this.attr("itemName"),  $this.attr("status"), "${pageContext.request.contextPath}/admin/system/navigationItem/updatestatus/", event.target,"${pageContext.request.contextPath}");
	       			 }
	       		);
              	highlightTableRows("parent");  
          });
		
		</script>
</body>
</html>