<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>地区管理</title>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.validate.js'/>" /></script>
         <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
        <script language="javascript">
		    $.validator.setDefaults({
		    });
    $(document).ready(function() {
    jQuery("#form1").validate({
			rules: {
			province: "required",
			 provinceid: {
             number:true,
             required:true
            }
		},
    messages: {
			province:"请输入省份",
			provinceid: {
                number:"请输入正确的邮编，必须是数字",
                required:"请输入邮编"
            }		
		}
    });
    
});

</script>
</head>
    <body>
        <form action="<ls:url address='/admin/system/district/save'/>" method="post" id="form1">
            <input id="id" name="id" value="${province.id}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1" style="width: 100%">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                	省份管理
                            </div>
                        </th>
                    </tr>
                </thead>
                
		<tr>
		        <td style="width: 30%">
		          	<div align="right">名称: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<input type="text" name="province" id="province" value="${province.province}"   maxlength="50"/>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="right">邮编: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<input type="text" name="provinceid" id="provinceid" value="${province.provinceid}"   maxlength="20"/>
		        </td>
		</tr>
                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="提交" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/system/district/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>