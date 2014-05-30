<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<html>
    <head>
        <title>创建栏目</title>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
        <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
        <script language="javascript">
    jQuery.validator.setDefaults({
    });

    jQuery(document).ready(function() {
    jQuery("#form1").validate({
            rules: {
            newsCategoryName: {
                required: true,
                minlength: 2
            }
        },
        messages: {
            newsCategoryName: {
                required: "请输入栏目名称",
                minlength: "栏目名称不能少于2个字"
            }
        }
    });
 
			highlightTableRows("col1");
          //斑马条纹
     	   $("#col1 tr:nth-child(even)").addClass("even");	  
});
</script>
</head>
    <body>
        <form action="${pageContext.request.contextPath}/admin/newsCategory/save" method="post" id="form1">
            <input id="newsCategoryId" name="newsCategoryId" value="${bean.newsCategoryId}" type="hidden">
            <div align="center">
		    <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/newsCategory/query">栏目管理</a> &raquo; 创建栏目</th></tr>
		    </thead>
		    </table>
            <table  align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建栏目
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td width="150px">
          <div align="right">栏目名称 <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="newsCategoryName" id="newsCategoryName" value="${bean.newsCategoryName}" size="50"/>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">状态 <font color="ff0000">*</font></div>
       </td>
        <td>
            <select id="status" name="status">
            <c:choose>
                <c:when test="${bean.status == '0'}">
                    <option value="1">上线</option>
                    <option value="0" selected="selected">下线</option>
                </c:when>
                <c:otherwise>
                    <option value="1" selected="selected">上线</option>
                    <option value="0">下线</option>
                </c:otherwise>
            </c:choose>
            </select> 
        </td>
      </tr>
              <tr>
                    <td colspan="2">
                        <div align="center">
                             <auth:auth ifAnyGranted="FA_SHOP"><input type="submit" value="提交"/></auth:auth>
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='${pageContext.request.contextPath}/admin/newsCategory/query'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

