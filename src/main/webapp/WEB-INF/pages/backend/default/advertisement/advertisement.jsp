<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<html>
    <head>
        <title>创建广告</title>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
        <script language="javascript">
    $.validator.setDefaults({
    });

    $(document).ready(function() {
    jQuery("#form1").validate({
            rules: {
            linkUrl: {
                required: true,
                minlength: 3
            },
            file:  {
				required: "#picUrlName:blank"
			}
        },
        messages: {
            linkUrl: {
                required: "请输入链接地址",
                minlength: "请认真填写，链接地址不能少于3个字符"
            },
        file: {
        	required : "请上传广告图片"
        }
    }
    });
 
      $("#col1 tr").each(function(i){
      if(i>0){
         if(i%2 == 0){
             $(this).addClass('even');
         }else{
              $(this).addClass('odd'); 
         }   
    }
     });   
         $("#col1 th").addClass('sortable'); 
});
</script>
</head>
    <body>
        <form action="${pageContext.request.contextPath}/admin/advertisement/save" method="post" id="form1" enctype="multipart/form-data">
            <input id="id" name="id" value="${bean.id}" type="hidden">
            <div align="center">
                <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/advertisement/query">广告管理</a> &raquo; 创建广告</th></tr>
    </thead>
    </table>
            <table  align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建广告
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td width="28%">
          <div align="right">广告类型: <font color="ff0000">*</font></div>
       </td>
        <td>
              <select id="type" name="type">
				  <ls:optionGroup type="select" required="true" cache="true"
	                beanName="ADVERTISEMENT_TYPE" selectedValue="${bean.type}"/>
	            </select>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">链接地址<font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="linkUrl" id="linkUrl" value="${bean.linkUrl}" size="50"/>
        </td>
      </tr>
      <tr>
        <td>
          <div align="right">标题</div>
       </td>
        <td>
           <input type="text" name="title" id="title" value="${bean.title}" size="50" maxlength="255"/>
        </td>
      </tr>
      <tr>
        <td>
          <div align="right">需求描述</div>
       </td>
        <td>
           <input type="text" name="sourceInput" id="sourceInput" value="${bean.sourceInput}" size="50" maxlength="255"/>
        </td>
      </tr>
      <tr>
        <td>
          <div align="right">状态<font color="ff0000">*</font></div>
       </td>
        <td>
				<select id="enabled" name="enabled">
				  <ls:optionGroup type="select" required="true" cache="true"
	                beanName="ONOFF_STATUS" selectedValue="${bean.enabled}"/>
	            </select>
        </td>
      </tr>
      
            <tr>
                    <td>
                        <div align="right">
                            上传广告图片(对联广告只能上传一张，大小为110*300)
                            <font color="ff0000">*</font>
                        </div>
                    </td>
                    <td>
                        
                            <input type="file" name="file" id="file" size="50"/>
                            <input type="hidden" name="picUrlName" id="picUrlName" size="80"
                                value="${bean.picUrl}" />
                        
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
                                onclick="window.location='${pageContext.request.contextPath}/admin/advertisement/query'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

