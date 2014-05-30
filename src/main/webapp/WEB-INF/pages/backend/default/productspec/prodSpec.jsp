<%@page pageEncoding="UTF-8" language="java"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>

<html>
<head>
<title>商品动态属性</title>
<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
<script type='text/javascript' src="<ls:templateResource item='/common/default/js/dynamicAttribute.js'/>"></script>
<style type="text/css">
.modelType {
	border: 1px solid;
	margin: 3px;
	padding: 1px;
	background-color: #FFFFF6;
}

.modelType:hover {
	background: #CCCCBB;
}

.textOptionType {
	padding: 3px;
	margin-left: 68px;
}

.selectType {
	padding: 3px;
	margin-left: 68px;
}

.selectOption {
	padding: 3px;
	margin-left: 68px;
}

.CheckBoxType {
	padding: 3px;
	margin-left: 68px;
}

.radioType {
	padding: 3px;
	margin-left: 68px;
}

.selectOption {
	padding: 3px;
	margin-left: 100px;
}
</style>
</head>
<body>

	<table class="${tableclass}" style="width: 100%">
		<thead>
			<tr>
				<th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品规格管理 &raquo; <c:choose>
						<c:when test="${dynamicTemp.type  == 1}">
							<a href="<ls:url address='/admin/prodspec/query/1'/>">动态属性</a>
						</c:when>
						<c:otherwise>
							<a href="<ls:url address='/admin/prodspec/query/2'/>">动态参数</a>
						</c:otherwise>
					</c:choose>
				</th>
			</tr>
		</thead>
	</table>
	<table style="width: 100%; ">
		<tr>
			<td width="300px">商品分类<font color="#ff0000">*</font> <lb:sortOption id="sortId" type="P"
					selectedValue="${dynamicTemp.sortId}" /> <br /> <br /> <!-- 
					<select id="modelType" name="modelType">
					   <option value="Text">参数</option>
					   <option value="Select">下拉列表</option>
					   <option value="Radio">单选框</option>
					   <option value="CheckBox">多选框</option>
					</select>
					 --> 可选模板 <lb:dynTempOption id="tempId" type="${dynamicTemp.type }" selectedValue="${dynamicTemp.id }"
					onChange="applyTemp(this.value);" /> <input type='button' value='更新'
				onclick="javascript:modifyTemp('${DYNAMIC_TYPE}');"><br><br> 新建模板<font color="#ff0000">*</font> <input
						type="text" value="" id="tempName" name="tempName" size="14" maxlength="50"> <input type='button'
							value='保存' onclick="javascript:addTemp('${DYNAMIC_TYPE}');"> <br><br><br><br> <c:choose>
													<c:when test="${DYNAMIC_TYPE == 1}">创建动态属性： <br> <!-- 
					<input type="button" value="下拉列表" onclick="addRowRecord('Select')">	
					 --> <input type="button" value="单选框" onclick="addRowRecord('Radio')"> <input type="button" value="多选框"
																onclick="addRowRecord('CheckBox')">
													</c:when>
													<c:otherwise>创建动态参数： <br><input type="button" value="参数" onclick="addRowRecord('Text')">
													</c:otherwise>
												</c:choose> <br><br> <input type="hidden" id="contextPath" name="contextPath"
														value="<%=request.getContextPath()%>" />
			</td>
			<td width="650px"><div class="box" id="target" style="border: 1px"></div></td>
		</tr>
	</table>
	<table style="width: 100%; border: 0px">
		<tr>
			<td align="left">说明：<br> 1.<img alt="创建商品属性模板"
					src="${pageContext.request.contextPath}/common/default/images/grid_add.png">&nbsp在属性内容编辑器中则用于创建一个动态属性<br>
							2.<img alt="删除商品属性模板" src="<ls:templateResource item='/common/default/images/grid_delete.png'/>">&nbsp;在属性内容编辑器中则用于删除属性或者属性下的选项<br>
			</td>
			<tr>
	</table>
	<script language="javascript">   <!--
	
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
