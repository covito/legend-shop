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
    <title>商城信息</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/shopDetail/query" id="form1" method="post">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/shopDetail/query">商城管理</a></th></tr>
    </thead>
            <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
     <tbody><tr><td>
 <div align="left" style="padding: 3px">
  <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
           商城&nbsp;<input type="text" name="userName" maxlength="50" value="${shopDetail.userName}" size="30"/>
           &nbsp;类型&nbsp;	
           <select id="type" name="type">
           <ls:optionGroup type="select" required="false" cache="true"
	                beanName="SHOP_TYPE" selectedValue="${shopDetail.type}"/>
	        </select>
	      &nbsp;状态&nbsp;
	      <select id="status" name="status">
	      <ls:optionGroup type="select" required="false" cache="true"
	                beanName="SHOP_STATUS" selectedValue="${shopDetail.status}" />
	        </select>	
            <input type="submit" value="搜索"/>
             <c:if test="${fn:length(list) == 0}">
            <input type="button" value="创建商城" onclick='window.location="${pageContext.request.contextPath}/admin/shopDetail/load"'/>
            </c:if>
 </div>
 </td></tr></tbody>
           </auth:auth>
    </table>
      
    </form>
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/shopDetail/query" id="item"
         export="true" class="${tableclass}" style="width:100%" sort="external">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
      <c:if test="${'C2C' == applicationScope.BUSINESS_MODE }">
      <display:column title="商城">
      	<a href="<ls:domain shopName='${item.userName}'  />" target="_blank">${item.userName}</a>
      </display:column>
      </c:if>
      <display:column title="网站名称" property="siteName">
      </display:column>
      <display:column title="商品数量" property="productNum"  sortable="true" sortName="productNum" style="width:70px"></display:column>
      <display:column title="下线商品" property="offProductNum" sortable="true" sortName="offProductNum" style="width:70px"></display:column> 
      <display:column title="状态"  class="width:90px">
      <font color="red">
				  <ls:optionGroup type="label" required="false" cache="true"
	                beanName="SHOP_STATUS" selectedValue="${item.status}" defaultDisp=""/></font>
	                <c:if test="${item.status == -1}">
			             <auth:auth ifAnyGranted="FA_SHOP,F_VIEW_ALL_DATA">
			              [<span class="shopStatusSetting cursor_pointer"  onclick="javascript:auditShop('${item.userId}','${item.shopId}')">审核</span>]
			              </auth:auth>
	              </c:if>
      </display:column>
      <auth:auth ifAnyGranted="FA_SHOP,F_VIEW_ALL_DATA">
      <display:column title="操作" media="html" class="actionwidth">
      <a href= "${pageContext.request.contextPath}/admin/shopDetail/load/${item.shopId}" title="修改"><img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
              <a href='javascript:deleteById("${item.shopId}")' title="删除"><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
      </display:column>
         </auth:auth>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
    </div>
         <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 商城为上线状态才可以正常访问<br>
   2. 您可以选择不同的页面风格，或者每天随即挑取其中一个风格<br>
   </td><tr></table>
   
    <script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/shopDetail/delete/"+id;
        }
    }

        function pager(curPageNO){
            document.getElementById("curPageNO").value=curPageNO;
            document.getElementById("form1").submit();
        }
        
        //更改商城状态
	function auditShop(userId,shopId) {
 			art.dialog({
			    id: 'shopStatus',
			    content: '更改商城状态',
			    button: [
			        {
			            name: '同意申请',
			            callback: function () {
			           		sendRequest(userId, shopId, 1);
			                this.content('同意申请').time(2);
			                return false;
			            },
			            focus: true
			        },
			        {
			            name: '拒绝申请',
			            callback: function () {
			            	sendRequest(userId, shopId, -2);
			                this.content('拒绝申请').time(2);
			                return false;
			            }
			        },
			        {
			            name: '关闭商城',
			            callback: function () {
			            	sendRequest(userId, shopId, -3);
			                this.content('违规关闭，用户将不能登录后台').time(2);
			                return false;
			            }
			        }
			    ]
			});

	}
	
	function sendRequest(userId, shopId, status){
		var data = {
                	"userId": userId,
                	"shopId": shopId,
                	"status": status
                };
		    jQuery.ajax({
				url:"${pageContext.request.contextPath}/admin/shopDetail/audit" , 
				type:'post', 
				data:data,
				async : true, //默认为true 异步   
			    dataType : 'html', 
				success:function(retData){
						 if(retData == null ||  retData == ''){
					 		// alert('更改状态成功') ;
					 		 window.location.reload();
				       }else{
					 		//  alert('更改状态失败') ;	
					 		  window.location.reload();
				       }
				}
				});
	}
        
      jQuery(document).ready(function() {
             highlightTableRows("item"); 
         });

//-->
</script>
</body>
</html>

