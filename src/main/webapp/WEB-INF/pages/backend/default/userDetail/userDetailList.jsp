<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>

<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
		<script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
<title>用户详细信息列表</title>
</head>
<body>
	<%Integer offset = (Integer)request.getAttribute("offset");%>
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 用户管理  &raquo; <a href="${pageContext.request.contextPath}/admin/system/userDetail/query">用户信息管理</a></th></tr>
    </thead>
    <tbody><tr><td>
 <div align="left" style="padding: 3px">
<form action="${pageContext.request.contextPath}/admin/system/userDetail/query" id="form1" name="form1">
<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}">
			&nbsp;用户名
			<input type="text" name="userName" maxlength="50" value="${requestScope.userName}" />
			<ls:auth ifAllGranted="FA_SHOP">
			&nbsp;邮件
				<input type="text" name="userMail" maxlength="50" value="${requestScope.userMail}" />
			</ls:auth>
			    &nbsp;用户类型 
	            <select id="haveShop" name="haveShop">
					<option value="">请选择</option>	
			    	<option value="1">网店用户</option>	
	      			<option value="0" >普通用户</option>
			</select>
			&nbsp;状态 
			<select id="enabled" name="enabled">
					<option value="">请选择</option>	
			    	<option value="1">有效</option>	
	      			<option value="0" >无效</option>
			</select>
			<input type="submit" value="搜索"/>
</form>
 </div>
 </td></tr></tbody>
    </table>

<div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/system/userDetail/query" id="item"
         export="true"  class="${tableclass}" style="width:100%" sort="external">
      <display:column style="width:55px;vertical-align: middle;text-align: center;" title="顺序<input type='checkbox'  id='checkbox' name='checkbox' onClick='javascript:selAll()' />"><%=offset++%><input type="checkbox" name="strArray" value="${item.userId}" arg="${item.userName}"/></display:column>
      <display:column title="用户名" property="userName" sortable="true" sortName="u.user_name"></display:column>
      <display:column title="用户昵称" property="nickName" sortable="true" sortName="u.nick_name"></display:column>
      <ls:auth ifAllGranted="FA_SHOP">
      	<display:column title="邮件" property="userMail"></display:column>
      </ls:auth>
      <display:column title="注册IP" property="userRegip"></display:column>
      <display:column title="修改时间" sortable="true" sortName="u.modify_time">
     	 <fmt:formatDate value="${item.modifyTime}" pattern="yyyy-MM-dd HH:mm"/>
      </display:column>
      <display:column title="注册时间" sortable="true" sortName="u.user_regtime">
      	 <fmt:formatDate value="${item.userRegtime}" pattern="yyyy-MM-dd HH:mm"/>
      </display:column>
       <display:column title="状态" sortable="true" sortName="s.enabled" style="width:40px">
       <c:choose>
       		<c:when test="${item.enabled == 1}">有效</c:when>
       		<c:otherwise><font color="red">无效</font></c:otherwise>
       </c:choose>
      </display:column>
      <ls:auth ifAnyGranted="F_VIEW_ALL_DATA">
	      <display:column title="操作" media="html" style="width:75px">
	      <ls:auth ifAnyGranted="F_VIEW_ALL_DATA">
	         <a href='${pageContext.request.contextPath}/p/myaccount?userName=${item.userName}' target="_blank" title="查看用户${item.userName}信息"><img alt="查看用户${item.userName}信息" src="${pageContext.request.contextPath}/common/default/images/ind_left_login.gif"></a>
	      </ls:auth>
	      <c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
	      <c:choose>
	      	<c:when test="${item.shopId != null}">
	      		<a href='${pageContext.request.contextPath}/admin/shopDetail/load/${item.shopId}?userName=${item.userName}' title="修改用户${item.userName}当前商城"><img alt="修改用户当前商城" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
	      	</c:when>
	      	<c:otherwise>
	      		<c:if test="${supportOpenShop == 'true'}">
	      			<a href='${pageContext.request.contextPath}/admin/shopDetail/load/${item.shopId}?userName=${item.userName}'><img alt="为用户${item.userName}增加商城" src="${pageContext.request.contextPath}/common/default/images/grid_add.png"></a>
	      		</c:if>
	      	</c:otherwise>
	      </c:choose>
	      </c:if>
  	 <a href='javascript:confirmDelete("${item.userId}","${item.userName}", false)' title="删除用户${item.userName}所有信息"><img alt="删除用户${item.userName}所有信息" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
	 </display:column>
      </ls:auth>
    </display:table> 
            <ls:auth ifAnyGranted="F_VIEW_ALL_DATA">
                      <input type="button" value="刪除" style="float: left" onclick="return deleteAction();"/>   
            </ls:auth>
     <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
    </div>
    
    <script language="JavaScript" type="text/javascript">
<!--
 function deleteAction()
{
	//获取选择的记录集合
	selAry = document.getElementsByName("strArray");
	if(!checkSelect(selAry)){
	 window.alert('删除时至少选中一条记录！');
	 return false;
	}
	if(confirm("确定要删除吗？")){
	for(i=0;i<selAry.length;i++){
	  if(selAry[i].checked){
		var userId = selAry[i].value;
		var userName = selAry[i].getAttribute("arg");
			deleteUserDetail(userId,userName, true);
		 }
		}
	}
    window.location = '${pageContext.request.contextPath}/admin/system/userDetail/query';
	return true;
}

  function confirmDelete(userId,userName)
	{
	   if(confirm("确定要删除 "+userName+" 吗？")){
	   	deleteUserDetail(userId,userName, false);
	   }
	}
	
  function deleteUserDetail(userId,userName, multiDel) {
		    var data = {
	   				"userId":userId,
                	"userName": userName
                };
    			jQuery.ajax({
				url:"${pageContext.request.contextPath}/admin/system/userDetail/delete" , 
				data: data,
				type:'post', 
				async : false, //默认为true 异步   
			    dataType : 'html', 
				error: function(jqXHR, textStatus, errorThrown) {
			 		// alert(textStatus, errorThrown);
				},
				success:function(retData){
				if(!multiDel){
						 if(retData == null ||  retData == ''){
					 		 window.location = '${pageContext.request.contextPath}/admin/system/userDetail/query';
				       }else{
					 		 alert(retData.substring(6, retData.length)) ;
				       }
					}
				}
				});
	}
	
    function pager(curPageNO){
            document.getElementById("curPageNO").value=curPageNO;
            document.getElementById("form1").submit();
        }
        
     function initStatus(enabledValue,haveShopValue){
        	jQuery("#enabled").val(enabledValue);
        	jQuery("#haveShop").val(haveShopValue);
		}	
		
	jQuery(document).ready(function() {
        	initStatus( '${enabled}', '${haveShop}');
        	highlightTableRows("item"); 
	});
		
	 	
//-->
</script>
</body>
</html>

