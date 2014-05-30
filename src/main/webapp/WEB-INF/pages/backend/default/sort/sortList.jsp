<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>

<html>
<head>
     <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
     <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
     <script src="<ls:templateResource item='/common/js/recordstatus.js'/>" type="text/javascript"></script>
 	<%@ include file="/WEB-INF/pages/common/dialog.jsp"%>
<title>分类列表</title>
</head>
<body>
<%
			Integer offset = (Integer)request.getAttribute("offset");
	%>
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; <a href="${pageContext.request.contextPath}/admin/sort/query">类型管理</a></th></tr>
    </thead>
    <tbody>
    	<tr><td>
    	 <form id="form1" action="${pageContext.request.contextPath}/admin/sort/query" method="post">
		<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}">
		<div align="left" style="padding: 3px">
			&nbsp; 类型
			<input type="text" name="sortName"  id="sortName" maxlength="50"  value="${sort.sortName}" />
			<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
			&nbsp; 商城
			<input type="text" name="userName"  id="userName" maxlength="50" value="${sort.userName}" />
			</auth:auth>	
	        </div>
	        <div align="left" style="padding: 3px">
			&nbsp; Header菜单			
	      <select id="headerMenu" name="headerMenu">
	      <ls:optionGroup type="select" required="false" cache="true"
	                beanName="YES_NO" selectedValue="${sort.headerMenu}" />
	        </select>	
			&nbsp; 导航菜单			
	      <select id="navigationMenu" name="navigationMenu">
	      <ls:optionGroup type="select" required="false" cache="true"
	                beanName="YES_NO" selectedValue="${sort.navigationMenu}" />
	        </select>	
			<input type="submit" value="搜索"/>
			<auth:auth ifAnyGranted="FA_PROD">
					<input type="button" value="创建商品类型" onclick='window.location="${pageContext.request.contextPath}/admin/sort/load"'/>
			</auth:auth>
			<input type="button" value="返回商品列表" onclick='window.location="<ls:url address='/admin/product/query'/>"'/>
			</div>
			</form>
    	</td></tr>
    </tbody>
    </table>
   
  <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/sort/query" id="item"
         export="true" class="${tableclass}" style="width:100%" sort="external">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
      <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
      		<display:column title="商城" property="userName" sortable="true" sortName="userName"></display:column>
      </auth:auth>
      <display:column title="名称" property="sortName" sortable="true" sortName="sortName"></display:column>
      <display:column title="Header菜单">          
      	<ls:optionGroup type="label" required="false" cache="true"
	                beanName="YES_NO" selectedValue="${item.headerMenu}" defaultDisp="否"/>
      </display:column>
      <display:column title="导航菜单">          
      	<ls:optionGroup type="label" required="false" cache="true"
	                beanName="YES_NO" selectedValue="${item.navigationMenu}" defaultDisp="否"/>
      </display:column>
	 <display:column title="次序" property="seq" sortable="true" sortName="seq"></display:column>
	 <auth:auth ifAnyGranted="FA_PROD">
      <display:column title="操作" media="html" style="width:100px">
		  	  <c:choose>
		  		<c:when test="${item.status == 1}">
		  			<img name="statusImg"  itemId="${item.sortId}"  itemName="${item.sortName}"  status="${item.status}"  class="cursor_pointer"   src="<ls:templateResource item='/common/default/images/blue_down.png'/> ">
		  		</c:when>
		  		<c:otherwise>
		  			<img  name="statusImg"   itemId="${item.sortId}"  itemName="${item.sortName}"  status="${item.status}"   class="cursor_pointer"  src="<ls:templateResource item='/common/default/images/yellow_up.png'/> ">
		  		</c:otherwise>
		  	</c:choose>
      <c:if test="${item.sortType ne 'G'}">
    	<a href="${pageContext.request.contextPath}/admin/nsort/query?sortId=${item.sortId}" title="创建二级商品类型"><img  src="<ls:templateResource item='/common/default/images/grid_add.png'/>"></a>
      </c:if>
     <a href="${pageContext.request.contextPath}/admin/sort/update/${item.sortId}" title="修改"><img  src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
        <a href='javascript:deleteSort("${item.sortId}")' title="删除"><img  src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
      </display:column>
      </auth:auth>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
        </div>
     <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 商品类型分为三级，一级商品类型带有一个说明图片，用于页面广告介绍，三级分类下可以挑选对应的品牌<br>
   2. 商品可以挂在一级，二级或者三级之上，三个级别有关联关系<br>
   3. <img alt="创建商品类型" src="${pageContext.request.contextPath}/common/default/images/grid_add.png">&nbsp;创建商品类型，在不同列表中创建不同级别的商品类型<br>
   4. <img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "> 修改按钮，在一级商品类型列表中编辑一级商品类型，其他级别一样<br>
   5. <img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "> 删除按钮，在一级商品类型列表中编辑删除一级商品类型，其他级别一样，删除前确保其下的元素已经删除<br>
   </td><tr></table> 
   
   <script language="JavaScript" type="text/javascript">
<!--
  function confirmDelete()
	{
	    return con = confirm("确定要删除吗？");
	}
	
  function deleteSort(sortId){
  if(confirmDelete()){
  	 window.location.href = "${pageContext.request.contextPath}/admin/sort/delete/"+sortId ;
  	}
  }
  
		  jQuery(document).ready(function(){
          	    $("img[name='statusImg']").click(function(event){
	          		$this = $(this);
	          		initStatus($this.attr("itemId"), $this.attr("itemName"),  $this.attr("status"), "${pageContext.request.contextPath}/admin/sort/updatestatus/", event.target,"${pageContext.request.contextPath}");
	       			 }
	       		);
              	highlightTableRows("item");  
          });
//-->
</script>
</body>
</html>

