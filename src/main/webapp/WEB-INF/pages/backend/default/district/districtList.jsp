<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
    <script src="<ls:templateResource item='/common/js/recordstatus.js'/>" type="text/javascript"></script>
     <script type="text/javascript" src="<ls:templateResource item='/common/js/infinite-linkage.js'/>"></script>
     <%@ include file="/WEB-INF/pages/common/dialog.jsp"%>
    <title>网站导航列表</title>
</head>
<body>
    <form action="<ls:url address='/admin/system/district/query'/>" id="form1" method="post">
        <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr>
			    	<th>
				    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 系统管理  &raquo; 
				    	<a href="<ls:url address='/admin/system/district/query'/>">地区管理</a>
			    	</th>
		    	</tr>
		    </thead>
	    </table>
    </form>   
    	您可以自己添加地区数据。</br>
    	添加，编辑或删除操作后需要点击“提交”按钮才生效。
     <div align="left"> 选择地区：
        <select class="combox"  id="provinceid" name="provinceid"  requiredTitle="true"  childNode="cityid" selectedValue="${entity.provinceid}"  retUrl="${pageContext.request.contextPath}/common/loadProvinces"  onchange="changeCities(this.value)">
		</select>
		<select class="combox"   id="cityid" name="cityid"  requiredTitle="true"  selectedValue="${entity.cityid}"   showNone="false"  parentValue="${entity.provinceid}" childNode="areaid" retUrl="${pageContext.request.contextPath}/common/loadCities/{value}"   onchange="changAreas(this.value)">
		</select>
		<select class="combox"   id="areaid" name="areaid"   requiredTitle="true"  selectedValue="${entity.areaid}"    showNone="false"   parentValue="${entity.cityid}"  retUrl="${pageContext.request.contextPath}/common/loadAreas/{value}">
		</select>
    </div>
	<div align="center"  id="districtContentList" name="districtContentList"></div>
	
		<input type="button" value="增加" style="float: left" onclick="javascript:addDisctrct()"/>    
        <script language="JavaScript" type="text/javascript">
        	//默认一上来就初始三级联动和文本框加载省份
			  jQuery(document).ready(function(){
			 	 //三级联动
          	    $("select.combox").initSelect();
          	    var areaid = '${entity.areaid}';
          	    var cityid = '${entity.cityid}';
          	    var provinceid = '${entity.provinceid}';
          	   var url = "${pageContext.request.contextPath}/admin/system/district/queryProvince";
          	    if(areaid != ''){
          	    	url = "${pageContext.request.contextPath}/admin/system/district/queryAreas/"+ '${entity.cityid}';
          	    }else if(cityid != ''){
          	    	url = "${pageContext.request.contextPath}/admin/system/district/queryCities/"+ '${entity.provinceid}';
          	    }
          	    
          	    sendData(url);
          });
          
   			//通过点击下拉框来把文本框的省份变成城市
			function changeCities(provinceid){
               var url = "${pageContext.request.contextPath}/admin/system/district/queryCities/"+ provinceid;
          	    sendData(url);
			}       
          
             //通过点击下拉框来把文本框的城市变成地区
			function changAreas(cityid){
               var url = "${pageContext.request.contextPath}/admin/system/district/queryAreas/"+ cityid;
          	    sendData(url);
			}       
          
	function sendData(url){
    			$.ajax({
				"url":url, 
				type:'post', 
				async : true, //默认为true 异步   
				error: function(jqXHR, textStatus, errorThrown) {
			 		// alert(textStatus, errorThrown);
				},
				success:function(result){
				   $("#districtContentList").html(result);
				}
				});
	}

	//页面的删除
	
	 function deleteAction(type){
	//获取选择的记录集合
	selAry = document.getElementsByName("strArray");
	if(!checkSelect(selAry)){
	 window.alert('删除时至少选中一条记录！');
	 return false;
	}
	art.dialog.confirm("删除后不可恢复, 确定要删除吗？", function () {
				var ids = []; 
	   			for(i=0;i<selAry.length;i++){
					  if(selAry[i].checked){
					  ids.push(selAry[i].value);
					 }
				}
			var result = deleteDistrict(ids,type);
			if('OK' == result){
				art.dialog.tips('删除地区成功');
				window.location = '${pageContext.request.contextPath}/admin/system/district/query';
			}else{
				art.dialog.tips('删除地区失败');
			}
		}, function () {
				//cancel
		});
	
	return true;
}

function deleteDistrict(ids,type) {
				var result;
				var idsJson = JSON.stringify(ids);
				var ids = {"ids": idsJson};
				var url = "/admin/system/district/" + type;
    			jQuery.ajax({
				"url":url , 
				data: ids,
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

	//删除省份
  function confirmDeletePrivonce(provinceid,province){
	   art.dialog.confirm("删除后不可恢复, 确定要删除'"+province+"'吗？", function () {
	   		var url="${pageContext.request.contextPath}/admin/system/district/deleteprovince/" + provinceid;
	   		var result = deleteLocation(url);
	   		if('OK' == result){
	   			 art.dialog.tips('删除成功!');
	   			 window.location = '${pageContext.request.contextPath}/admin/system/district/query';
	   		}else{
	   			 art.dialog.tips(result);
	   		}
		});
	}

	//删除城市
  function confirmDeleteCity(cityid,city){
	   art.dialog.confirm("删除后不可恢复, 确定要删除'"+city+"'吗？", function () {
	   		var url="${pageContext.request.contextPath}/admin/system/district/deletecity/" + cityid;
	   		var result = deleteLocation(url);
	   		if('OK' == result){
	   			art.dialog.tips('删除成功!');
	   			 window.location = '${pageContext.request.contextPath}/admin/system/district/query';
	   		}
		   
		});
	   
	}
	

	//删除地区
  function confirmDeleteArea(areaid,area)
	{
	   art.dialog.confirm("删除后不可恢复, 确定要删除'"+area+"'吗？", function () {
	   		var url="${pageContext.request.contextPath}/admin/system/district/deletearea/" + areaid;
	   		var result = deleteLocation(url);
	   		if('OK' == result){
	   			art.dialog.tips('删除成功!');
	   			 window.location = '${pageContext.request.contextPath}/admin/system/district/query';
	   		}else{
	   			art.dialog.tips('删除失败!');
	   		}
		   
		});
	   
	}
	
	function deleteLocation(url) {
				var result;
    			jQuery.ajax({
				"url":url, 
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
	
	function addDisctrct(){
		var provinceid = $("#provinceid").val();
		var cityid = $("#cityid").val();
		if(provinceid == null || provinceid == '' ){
			window.location.href= "${pageContext.request.contextPath}/admin/system/district/addprovince";
		}else {
			if(cityid == null || cityid == ''){
				window.location.href= "${pageContext.request.contextPath}/admin/system/district/addcity/" + provinceid;
			}else{
				window.location.href= "${pageContext.request.contextPath}/admin/system/district/addarea/" + cityid;
			}
		}
	}
	
		</script>
</body>
</html>