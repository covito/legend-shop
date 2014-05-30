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
			area: "required",
			areaid: {
             number:true,
             required:true
            },
		    code: {
             number:true,
             required:true
            }
		},
    messages: {
			area:"请输入地区名称",
			areaid: {
                number:"请输入正确的邮编，必须是数字",
                required:"请输入邮编"
            },
            code: {
                number:"请输入正确的区号，必须是数字",
                required:"请输入区号"
            }	
		}
    });
    
});

</script>
</head>
    <body>
        <form action="<ls:url address='/admin/system/district/savearea'/>" method="post" id="form1">
            <input id="id" name="id" value="${area.id}" type="hidden">
            <input id="cityid" name="cityid" value="${city.id}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1" style="width: 100%">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                	[${province.province }/${city.city }] -地区管理
                            </div>
                        </th>
                    </tr>
                </thead>
                
		<tr>
		        <td style="width: 30%">
		          	<div align="right">名称: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<input type="text" name="area" id="area" value="${area.area}"   maxlength="50"/>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="right">邮编: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<input type="text" name="areaid" id="areaid" value="${area.areaid}"   maxlength="20"/>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="right">区号: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<input type="text" name="code" id="code" value="${area.code}" />
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