<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<html>
	<head>
		<title>增加相关商品</title>
		<%@include file='/WEB-INF/pages/common/common.jsp'%>
	    <%@ include file="/WEB-INF/pages/common/dialog.jsp"%>
		<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
		<script src="<ls:templateResource item='/common/js/chooseBox.js'/>" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/common/style/global_base.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">		
       function save(obj){
          obj.disabled = "disabled" ;
          var idMap = [];
          var nameMap = [];
          for(i=0;i<document.getElementById("Select2").options.length;i++){
            var brandId = document.getElementById("Select2").options[i].value;
            var brandName = document.getElementById("Select2").options[i].text;
            idMap.push(brandId) ;
           nameMap.push(brandName);
          } 
          var idJson = JSON.stringify(idMap);
          var nameJson = JSON.stringify(nameMap);
       jQuery.post("${pageContext.request.contextPath}/admin/product/saveRelProd/" +${prodId} , 
        {
            "idJson": idJson,
           "nameJson": nameJson
              },
        function(retData) {
              		obj.disabled = false ;
				   if(retData == null ||  retData == ''){
					 		 alert('操作成功') ;
				       }else{
					 		  alert('操作失败') ;	
				       }
				 
		},'json');
                
       }

	function loadDataByName(){
		 	var prodName = "%"+document.getElementById("selectname").value+"%";
      jQuery.post("${pageContext.request.contextPath}/admin/product/getUsableProductItemByName/" +${prodId} , 
        {
            "prodName": prodName
              },
        function(dataArray) {
                try{ 
                $Select1 = $("#Select1");
               $Select1.empty();
                 for(var i=0;i<dataArray.length ;i++){
                     var data = dataArray[i] ;
 					$Select1.append("<option value='" + data.key + "'>" + data.value + "</option>" );  
                 }
               }catch(e){}
		},'json');
           
		 }

       function loadData(){
	       //未有选择
        jQuery.post("${pageContext.request.contextPath}/admin/product/getUsableProductItem/" +${prodId} , 
        function(dataArray) {
                try{ 
                $Select1 = $("#Select1");
                 for(var i=0;i<dataArray.length ;i++){
                     var data = dataArray[i] ;
 					 $Select1.append("<option value='" + data.key + "'>" + data.value + "</option>" );  
                 }
               }catch(e){}
		},'json');
		
           //已经选择的
 		jQuery.post("${pageContext.request.contextPath}/admin/product/getUsedProductItem/" +${prodId} , 
        function(dataArray) {
                try{
                $Select2 = $("#Select2");
                 for(var i=0;i<dataArray.length ;i++){
                     var data = dataArray[i] ;
 					 $Select2.append("<option value='" + data.key + "'>" + data.value + "</option>" );  
                 }
               }catch(e){}
		},'json'); 
           
       }
       
       </script>     
		
	</head>
	<body scroll="no" onLoad="loadData()">
		<table width="100%" height="100%" cellpadding="1" cellspacing="1">
			<tr>
				<td><br>
				&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/plugins/theme/skin/images/frame/menusearch.gif">商品										
				<input name="selectname" type="text" id="selectname" size="15" onKeyDown="javascript:onEnter(event)" >
                <input name="search" type="button" id="search" onClick="loadDataByName()" value="查询" class="s"></td>
			</tr>
				<tr>
<td>

								<fieldset>
									<legend>选择</legend>
									<table width="100%" cellpadding="5" cellspacing="0">
										<tr>
											<td width="45%" class="fim_l">
												<table width="*" cellpadding="0" cellspacing="0" class="">
													<tr>
														<td>
														</td>
														<td>
															&nbsp;可选择商品</td>
													</tr>
												</table>
											</td>
											<td width="2%">
											</td>
											<td width="45%">
												<table width="*" cellpadding="0" cellspacing="0">
													<tr>
														<td>
														</td>
														<td>
															&nbsp;已选择商品</td>
													</tr>
												</table>
											</td>
											
										</tr>
										<tr>
											<td valign="top" rowspan="2">
											   <table width="100%" height="100%">
											       <tr>
											        <td  width="100%" colspan="2" height="*">
											        <select id="Select1" multiple ondblclick="toRight(Select1,Select2)" name="Select1" 
													      style="width: 100%; height:220px">														
												      </select>
											        </td>
											      </tr>
											   </table>
											    
											</td>
											<td class="" rowspan="2">
												<input type="button" value=">>" title="全部添加"  onclick="toRightAll(Select1,Select2)" id="Button3" name="Button1"  class="s" /><br />
												<input type="button" value=">" title="添加" onclick="toRight(Select1,Select2)" id="Button4" name="Button2"  class="s" /><br />
												<input type="button" value="<" title="删除" onclick="toLeft(Select2,Select1)" id="Button11" name="Button3"  class="s" /><br />
												<input type="button" value="<<" title="全部删除" onclick="toLeftAll(Select2,Select1)"  id="Button12" name="Button4" class="s" /><br />
											</td>
											<td class="fim_m">
												<select id="Select2" multiple ondblclick="toLeft(Select2,Select1)" class="com_textarea"
													style="width: 100%; height: 220px">
												</select>
											</td>
											
										</tr>
										
									</table>
								</fieldset>
							</td>
			</tr>
			<tr>
										<td align="center">
											<input type="button" class="s" onclick="save(this)" value="确定" id="Button1"  name="Button1" />
											<br></br> 
										</td>
			</tr>
		</table>
	</body>
</html>
