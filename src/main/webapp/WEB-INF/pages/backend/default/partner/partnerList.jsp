<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
     <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <title>供应商列表</title>
</head>
<body>
    <form action="<ls:url address='/admin/partner/query'/>" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr>
			    	<th>
				    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
				    	<a href="<ls:url address='/admin/partner/query'/>">供应商管理</a>
			    	</th>
		    	</tr>
		    </thead>
		     <tbody><tr><td>
 <div align="left" style="padding: 3px">
 <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
        	<auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
            	商城&nbsp;<input type="text" name="userName" maxlength="50" value="${partner.userName}" />
            </auth:auth>
            &nbsp;&nbsp;登录名&nbsp;<input type="text" name="partnerName" maxlength="50" value="${partner.partnerName}" />
            <input type="submit" value="搜索"/>
            <input type="button" value="创建供应商" onclick='window.location="<ls:url address='/admin/partner/load'/>"'/>
 </div>
 </td></tr></tbody>
	    </table>
       
    </form>
    <div align="center">
          <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
		<display:table name="list" requestURI="/admin/partner/query" id="item" export="false" class="${tableclass}" style="width:100%">
	<display:column title="顺序"  class="orderwidth">${item_rowNum}</display:column>
      <display:column title="登录名" property="partnerName"></display:column>
      <display:column title="名称" property="title"></display:column>
      <display:column title="主页" property="homepage"></display:column>
      <display:column title="联系人" property="contact"></display:column>
      <display:column title="联系电话" property="phone"></display:column>
      <display:column title="评论满意" property="commentGood"></display:column>
      <display:column title="评论一般" property="commentNone"></display:column>
      <display:column title="评论失望" property="commentBad"></display:column>

	    <display:column title="操作" media="html">
		      <a href="<ls:url address='/admin/partner/load/${item.partnerId}'/>" title="修改">
		     		 <img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/>">
		      </a>
		      <a href='javascript:deleteById("${item.partnerId}")' title="删除">
		      		<img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/>">
		      </a>
		      
		      <a href="<ls:url address='/admin/partner/changePassword/${item.partnerId}'/>" title="修改密码">修改密码</a>
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
			            window.location = "<ls:url address='/admin/partner/delete/" + id + "'/>";
			        }
			    }
			  highlightTableRows("item");  
			//-->
		</script>
</body>
</html>


