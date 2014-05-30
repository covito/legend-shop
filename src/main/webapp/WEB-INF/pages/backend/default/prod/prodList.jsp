<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<html>
<head>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
        <script type="text/javascript" src="<ls:templateResource item='/common/js/infinite-linkage.js'/>"></script>
		<script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
 		<%@ include file="/WEB-INF/pages/common/dialog.jsp"%>
<title>商品列表</title>
</head>
<body>
<%
	Integer offset = (Integer)request.getAttribute("offset");
%>
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理 &raquo; <a href="<ls:url address='/admin/product/query'/>">商品管理</a></th></tr>
    </thead>
    <tbody>
    	<tr><td>
    	    <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}"/>
			<div align="left" style="padding: 3px;">
		    <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
               商城&nbsp;
            <input type="text" id="userName" name="userName" maxlength="50" value="${prod.userName}" size="15"/>
            </auth:auth>
			<auth:auth ifNotGranted="F_VIEW_ALL_DATA">
			  商品类型&nbsp;
          					<select class="combox"  id="sortId" name="sortId"  requiredTitle="-- 一级分类 -- "  childNode="nsortId" selectedValue="${prod.sortId}"  retUrl="${pageContext.request.contextPath}/sort/loadSorts/${prod.sortId}">
							</select>
							<select class="combox"   id="nsortId" name="nsortId"  requiredTitle="-- 二级分类 --"  selectedValue="${prod.nsortId}"   showNone="false"  parentValue="${prod.sortId}" childNode="subNsortId" retUrl="${pageContext.request.contextPath}/sort/loadNSorts/{value}">
							</select>
							<select class="combox"   id="subNsortId" name="subNsortId"   requiredTitle="-- 三级分类 --"  selectedValue="${prod.subNsortId}"    showNone="false"   parentValue="${prod.nsortId}"   childNode="brandId"  retUrl="${pageContext.request.contextPath}/sort/loadSubNSorts/{value}">
							</select>
 							<select class="combox"   id="brandId" name="brandId"   requiredTitle="-- 品牌 --"  selectedValue="${prod.brandId}"    showNone="false"   parentValue="${prod.subNsortId}"  retUrl="${pageContext.request.contextPath}/brand/loadBrands/{value}">
							</select>       
        </auth:auth>  
        </div><div align="left" style="padding: 3px">
			商品&nbsp;
			<input type="text" name="name" id="name" maxlength="50" value="${prod.name}" size="32"/>
			精品
			<select id="commend" name="commend">
					<option value="">请选择</option>	
			    	<option value="Y">是</option>	
	      			<option value="N" >否</option>
			</select>
			热门
			<select id="hot" name="hot">
					<option value="">请选择</option>	
			    	<option value="Y">是</option>	
	      			<option value="N" >否</option>
			</select>
			状态
			<select id="status" name="status">
					<option value="-1">请选择</option>	
			    	<option value="1">上线</option>
	      			<option value="0" >下线</option>
			</select>		
			<input type="button" value="搜索" onclick="javascript:sendData()"/>
			  <auth:auth ifAnyGranted="FA_PROD">
		   			<input type="button" value="创建商品" onclick='window.location="${pageContext.request.contextPath}/admin/sort/publish"'/>
		 	  </auth:auth>
			</div>
    	</td></tr>
    </tbody>
    </table>
	<div align="center"  id="prodContentList" name="prodContentList">
    </div>
	<table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 建立商品的五个步骤：1.商品详细信息 2.商品相关说明图片 3.动态属性 4.动态参数  5.相关商品<br>
   1. 商品可以挂在一级，二级，三级类型上<br>
   2. 商品有一个对应的品牌，品牌对应三级类型<br>
   3. 商品状态，在开始时间和结束时间之内有效，失效后包括下线状态将不会在前台显示，推荐状态为是则在首页精品推荐中出现<br>
   4. 价格、运费、库存量为正数，不填写则不在前台显示，如果填写了库存量为0则无法订购<br>
   5. 库存量在用户下单时会减少，实际库存量在订单成交时减少<br>
   6. <img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "> 修改按钮<br>
   7. <img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "> 删除按钮<br>
   </td><tr></table> 
   
   <script language="JavaScript" type="text/javascript">
<!--

 function deleteAction(){
	//获取选择的记录集合
	selAry = document.getElementsByName("strArray");
	if(!checkSelect(selAry)){
	 window.alert('删除时至少选中一条记录！');
	 return false;
	}
	art.dialog.confirm("删除后不可恢复, 确定要删除吗？", function () {
			var totalToDel = 0;
			var onError = 0;
	   			for(i=0;i<selAry.length;i++){
					  if(selAry[i].checked){
					  totalToDel = totalToDel + 1;
					  var prodId = selAry[i].value;
					  var name = selAry[i].getAttribute("arg");
						var result =	deleteProduct(prodId,true);
						if('OK' != result){
							onError = onError + 1;
							}
						 }
			}
	   		if(onError == 0){
	   			art.dialog.tips('删除商品成功');
	   			 window.location = '${pageContext.request.contextPath}/admin/product/query';
	   		}else if(onError < totalToDel ){
	   			  art.dialog.tips('删除部分商品成功');
	   			 window.location = '${pageContext.request.contextPath}/admin/product/query';
	   		}else  if(onError == totalToDel){
	   			 art.dialog.tips('删除商品失败');
	   		}
		}, function () {
				//cancel
		});
	
	return true;
}

  function confirmDelete(prodId,name)
	{
	   art.dialog.confirm("删除后不可恢复, 确定要删除'"+name+"'吗？", function () {
	   		var result = deleteProduct(prodId,false);
	   		if('OK' == result){
	   			art.dialog.tips('删除商品成功');
	   			 window.location = '${pageContext.request.contextPath}/admin/product/query';
	   		}else{
	   			 art.dialog.tips(result);
	   		}
		   
		   
		}, function () {
				//cancel
		});
	   
	}
	
function deleteProduct(prodId, multiDel) {
				var result;
    			jQuery.ajax({
				url:"${pageContext.request.contextPath}/admin/product/delete/" + prodId , 
				type:'post', 
				async : false, //默认为true 异步   
			    dataType : 'json', 
				error: function(jqXHR, textStatus, errorThrown) {
			 		// alert(textStatus, errorThrown);
				},
				success:function(retData){
					result = retData;
					}
				});
				
				return result;
	}
		
		function pager(curPageNO){
			//document.getElementById("curPageNO").value=curPageNO;
			sendData(curPageNO);
		}
		
		    
 	  jQuery(document).ready(function(){
       		 //三级联动
              $("select.combox").initSelect();
       		  //query  content
       		   sendData($("#curPageNO").val());
          });
          
	function sendData(curPageNO){
	    var data = {
	   				"userName": $("#userName").val(),
                	"curPageNO": curPageNO,
                	"status": $("#status").val(),
                	"sortId": $("#sortId").val(),
                	"nsortId": $("#nsortId").val(),
                	"subNsortId": $("#subNsortId").val(),
                	"commend": $("#commend").val(),
                	"brandId": $("#brandId").val(),
                	"hot": $("#hot").val(),
                	"name" :$("#name").val()
                };
    			$.ajax({
				url:"${pageContext.request.contextPath}/admin/product/queryprodcontent", 
				data: data,
				type:'post', 
				async : true, //默认为true 异步   
				error: function(jqXHR, textStatus, errorThrown) {
			 		// alert(textStatus, errorThrown);
				},
				success:function(result){
				   $("#prodContentList").html(result);
				}
				});
	}
		    
//-->
</script>
</body>
</html>

