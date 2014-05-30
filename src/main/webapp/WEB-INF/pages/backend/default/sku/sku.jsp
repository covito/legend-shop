<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>创建单品SKU表</title>
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
        <form action="<ls:url address='/admin/sku/save'/>" method="post" id="form1">
            <input id="skuId" name="skuId" value="${sku.skuId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                	创建单品SKU表
                            </div>
                        </th>
                    </tr>
                </thead>
                
		<tr>
		        <td>
		          	<div align="center">商品ID: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="ProdId" id="prodId" value="${sku.prodId}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">Sku的销售属性组合字符串（颜色，大小，等等，可通过类目API获取某类目下的销售属性）,格式是p1:v1;p2:v2: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="Properties" id="properties" value="${sku.properties}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">价格: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="Price" id="price" value="${sku.price}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">商品在付款减库存的状态下，该sku上未付款的订单数量: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="Stocks" id="stocks" value="${sku.stocks}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">实际库存: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="ActualStocks" id="actualStocks" value="${sku.actualStocks}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">SKU名称: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="Name" id="name" value="${sku.name}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">Sku状态。 1l:正常 ；0:删除: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="Status" id="status" value="${sku.status}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">Sku级别发货时间: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="SkuDeliveryTime" id="skuDeliveryTime" value="${sku.skuDeliveryTime}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">商家设置的外部id: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="OuterId" id="outerId" value="${sku.outerId}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">修改时间: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="ModifyDate" id="modifyDate" value="${sku.modifyDate}" /></p>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">记录时间: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<p><input type="text" name="RecDate" id="recDate" value="${sku.recDate}" /></p>
		        </td>
		</tr>
                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="添加" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/sku/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

