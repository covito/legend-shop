<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>创建产品规范</title>
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
        <form action="<ls:url address='/admin/productSpec/save'/>" method="post" id="form1">
            <input id="prodSpecId" name="prodSpecId" value="${productSpec.prodSpecId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                	创建产品规范
                            </div>
                        </th>
                    </tr>
                </thead>
                
		<tr>
		        <td>
		          	<div align="center">商品ID: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="ProductId" id="productId" value="${productSpec.productId}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">属性ID: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="PropId" id="propId" value="${productSpec.propId}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">属性值ID: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="ValueId" id="valueId" value="${productSpec.valueId}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">是否Sku: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="IsSku" id="isSku" value="${productSpec.isSku}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">Stock Keeping Unit（库存量单位）ID: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="SkuId" id="skuId" value="${productSpec.skuId}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">修改时间: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="ModifyDate" id="modifyDate" value="${productSpec.modifyDate}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">记录时间: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="RecDate" id="recDate" value="${productSpec.recDate}" /></p>
		        </td>
		</tr>
                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="添加" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/productSpec/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

