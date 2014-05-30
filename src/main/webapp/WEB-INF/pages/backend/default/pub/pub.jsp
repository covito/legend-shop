<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<html>
    <head>
        <title>创建公告</title>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
         <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
         <script src="<ls:templateResource item='/plugins/My97DatePicker/WdatePicker.js'/>" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
        <script language="javascript">
    $.validator.setDefaults({
    });
	jQuery.validator.addMethod("compareDate", function(value, element, param) {
            var startDate = jQuery(param).val();
            if(startDate == null || startDate == '' || value == null || value == ''){//只要有一个为空就不比较
            	return true;
            }
            var date1 = new Date(startDate);
            var date2 = new Date(value);
            return date1 < date2;
     
     }, '开始时间不能大于结束时间'); 
     
    $(document).ready(function() {
    jQuery("#form1").validate({
            rules: {
            title: {
                required: true,
                minlength: 2
            },
            msg: "required",
            endDate:{ 
           		 compareDate: "#startDate"
            }
        },
        messages: {
            title: {
                required: "请输入标题",
                minlength: "请认真输入标题"
            },
            msg: {
                required: "请输入链接地址"
            },
            endDate:{
            	compareDate: "结束日期必须大于开始日期!"
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
        <form action="${pageContext.request.contextPath}/admin/pub/save" method="post" id="form1">
            <input id="id" name="id" value="${bean.id}" type="hidden">
            <div align="center">
            <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/pub/query">公告管理</a> &raquo; 创建公告</th></tr>
		    </thead>
		    </table>
            <table  style="width: 100%" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建公告
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td>
          <div align="right">标题 <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="title" id="title" value="${bean.title}" size="50"/>
        </td>
      </tr>
      <tr>
        <td>
          <div align="right">选择有效时间</div>
       </td>
        <td>
           			&nbsp;开始时间
			 <input readonly="readonly"  name="startDate" id="startDate" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${bean.startDate}" pattern="yyyy-MM-dd"/>' />
			&nbsp;结束时间 
			 <input readonly="readonly" name="endDate" id="endDate" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${bean.endDate}" pattern="yyyy-MM-dd"/>' />
               
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">内容<font color="ff0000">*</font></div>
       </td>
        <td>
          <auth:auth ifAnyGranted="FA_SHOP">
			<FCK:editor instanceName="msg" height="400px" width="100%" basePath="/plugins/fckeditor">
                <jsp:attribute name="value">${bean.msg}</jsp:attribute>
            </FCK:editor>
            </auth:auth>
            <auth:auth ifNotGranted="FA_SHOP">
                你无权编辑该内容，请与管理员联系
            </auth:auth>
        </td>
      </tr>
      <tr>
                    <td colspan="2">
                        <div align="center">
                        <auth:auth ifAnyGranted="FA_SHOP">
                            <input type="submit" value="提交" />
                        </auth:auth>
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='${pageContext.request.contextPath}/admin/pub/query'" />
                        </div>
                    </td>
                </tr>
            </table>
            </div>
        </form>
    </body>
</html>

