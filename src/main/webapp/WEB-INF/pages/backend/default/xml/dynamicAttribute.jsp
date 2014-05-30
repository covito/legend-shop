<%@page pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
<html>
	<head>
		<title>商品动态属性</title>
       <script type='text/javascript' src="<ls:templateResource item='/common/default/js/dynamicAttribute.js'/>"></script>
        <style type="text/css">
        	.modelType{border: 1px solid ; margin: 3px;padding: 1px;background-color: #FFFFF6;}
        	.modelType:hover{background:#CCCCBB;} 
        	.textOptionType { padding: 3px ;margin-left:68px ; }
        	.selectType { padding: 3px ;margin-left:68px ; }
        	.selectOption { padding: 3px ;margin-left:68px ; }
        	.CheckBoxType { padding: 3px ;margin-left:68px ; }
        	.radioType { padding: 3px ;margin-left:68px ; }
        	.selectOption {padding: 3px ;margin-left:100px ;}
        </style>
	</head>
	<body>

 <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理 &raquo; <a href="<ls:url address='/admin/product/query'/>">商品管理</a> &raquo; <a href="${pageContext.request.contextPath}/admin/product/load">创建商品</a>
    	 <c:if test="${prod.name != null}"> &raquo; <a href= "${pageContext.request.contextPath}/views/${prod.prodId}" target="_blank">${prod.name}</a></c:if>
    	</th></tr>
   	</thead>
    <tr class="odd">
      <td><div align="left">
        <jsp:include page="/admin/product/createsetp">
    		<jsp:param name="step" value="3"/>
    	</jsp:include>
      </div></td>
    </tr>
   
    </table>
    <table style="width: 100%; ">
			<tr>
				<td width="300px">
					可选模板
					<lb:dynTempOption id="tempId"  type="${DYNAMIC_TYPE}" sortId="${prod.sortId }"  onChange="applyTemp(this.value);"/>
				<br><br>
				<c:choose>
					<c:when test="${DYNAMIC_TYPE == 1}">创建动态属性： <br>
						<script language="javascript"><!--
						function submitPrevious(){
					 		 window.location = "${pageContext.request.contextPath}/admin/imgFile/query?productId=${prod.prodId}";
					  }  
					  
					    function submitNext(){
					 		 window.location = "${pageContext.request.contextPath}/admin/dynamic/loadParameter/${prod.prodId}";
					  }  
						--></script>
					<!-- 
					<input type="button" value="下拉列表" onclick="addRowRecord('Select')">	
					 -->
					<input type="button" value="单选框" onclick="addRowRecord('Radio')">	
					<input type="button" value="多选框" onclick="addRowRecord('CheckBox')">	
					</c:when>
					<c:otherwise>创建动态参数： <br><input type="button" value="参数" onclick="addRowRecord('Text')">	
						<script language="javascript"><!--
								function submitPrevious(){
							 		 window.location = "${pageContext.request.contextPath}/admin/dynamic/loadAttribute/${prod.prodId}";
							  }  
							  
							    function submitNext(){
							   		 createprodStep.relProduct(${prod.prodId});
							  }  
								--></script>
					</c:otherwise>
				</c:choose>
				<br><br>
				<input type="hidden"  id="contextPath"  name="contextPath" value="<%= request.getContextPath()%>" /> 
				<input  type="button" value="保存设置" onclick="javascript:save('${DYNAMIC_TYPE}', '${prod.prodId}')"/>
				</td>
				<td width="650px"><div class="box" id="target" style="border: 1px"></div></td>
			</tr>
		</table>
	<table style="width: 100%; border: 0px">
           <tr>
		      <td><div align="center">
		        <auth:auth ifAnyGranted="FA_PROD"> 
		            <input type="button" value="上一步" onclick="javascript:submitPrevious()"/>
		            <input type="button" value="下一步" onclick="javascript:submitNext()"/>
		        </auth:auth> 
		      </div></td>
		    </tr>
       </table>
   <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1.<img alt="创建商品属性模板" src="${pageContext.request.contextPath}/common/default/images/grid_add.png">&nbsp;以“模板名称”为名称，以当前的属性配置作为内容，创建商品属性模板，在属性内容编辑器中则用于创建一个动态属性<br>
   2.<img alt="删除商品属性模板" src="<ls:templateResource item='/common/default/images/grid_delete.png'/>">&nbsp;删除选择模板中选定的商品属性模板,在属性内容编辑器中则用于删除属性或者属性下的选项<br>
   </td><tr></table> 
	<script language="javascript"><!--
	function load() {
		var table = document.getElementById("target");
		var dataArray = jQuery.parseJSON( '${dynTempJson}');
		if(dataArray!=null && dataArray.length >0){
		 for(var i = 0; i < dataArray.length; i++){
            	addModel(table,dataArray[i]);
                }
            }
	}
	

 load();
--></script>
	</body>
</html>
