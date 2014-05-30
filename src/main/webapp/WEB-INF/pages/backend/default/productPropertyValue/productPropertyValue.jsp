<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>创建属性值</title>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.validate.js'/>" /></script>
        <link rel="stylesheet" type="text/css" media="screen" href="<ls:templateResource item='/common/css/indexJpgForm.css'/>" />
        <style type="text/css" media="all">
          @import url(<ls:templateResource item='/css/screen.css'/>);
        </style>
        <script language="javascript">
		    $.validator.setDefaults({
		    });

    $(document).ready(function() {
    jQuery("#form1").validate({
            rules: {
            banner: {
                required: true,
                minlength: 5
            },
            url: "required"
        },
        messages: {
            banner: {
                required: "Please enter banner",
                minlength: "banner must consist of at least 5 characters"
            },
            url: {
                required: "Please provide a password"
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
        <form action="<ls:url address='/admin/productPropertyValue/save'/>" method="post" id="form1">
            <input id="valueId" name="valueId" value="${productPropertyValue.valueId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                	创建属性值
                            </div>
                        </th>
                    </tr>
                </thead>
                
		<tr>
		        <td>
		          	<div align="center">属性ID: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="PropId" id="propId" value="${productPropertyValue.propId}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">属性值名称: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="Name" id="name" value="${productPropertyValue.name}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">状态: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="Status" id="status" value="${productPropertyValue.status}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">图片路径: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="Pic" id="pic" value="${productPropertyValue.pic}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">排序: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="Sequence" id="sequence" value="${productPropertyValue.sequence}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">修改时间: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="ModifyDate" id="modifyDate" value="${productPropertyValue.modifyDate}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">记录时间: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="RecDate" id="recDate" value="${productPropertyValue.recDate}" /></p>
		        </td>
		</tr>
                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="添加" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/productPropertyValue/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

