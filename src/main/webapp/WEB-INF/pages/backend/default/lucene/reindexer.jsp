<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>

<html>
	<head>
		<title>重新建立索引</title>
		<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
		 <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
		<script language="javascript">
	$.validator.setDefaults({
	});

	$(document).ready(function() {
			highlightTableRows("col1");
          //斑马条纹
     	   $("#col1 tr:nth-child(even)").addClass("even");	  
	});

function chooseByPostId(field)
{
	var value = field.value;
	
	if (value.length > 0) {
		if (isNaN(value * 1)) {
			alert("输入ID范围有误");
			field.value = "";
		}
		else {
			var f = field.form;

			f.fromDay.selectedIndex = 0;
			f.fromMonth.selectedIndex = 0;
			f.fromYear.selectedIndex = 0;
			f.fromHour.value = "";
			f.fromMinutes.value = "";

			f.toDay.selectedIndex = 0;
			f.toMonth.selectedIndex = 0;
			f.toYear.selectedIndex = 0;
			f.toHour.value = "";
			f.toMinutes.value = "";

			f.type[1].checked = true;
		}
	}
}

function writeOptions(fieldName, from, to) 
{
	document.write("<select name='" + fieldName + "' onChange='chooseByDate(this.form);'>");
	document.write("<option value=''>--</option>");

	for (var i = from; i <= to; i++) {
		document.write("<option value='" + i + "'>" + i + "</option>");
	}

	document.write("</select>&nbsp;&nbsp;");
}

function chooseByDate(f){
	f.firstPostId.value = "";
	f.lastPostId.value = "";
	f.type[0].checked = true;
}

function validateReindexForm(f){
	if (f.type[0].checked) {
		return validateDateRange(f);
	}else {
		return validatePostIdRange(f);
	}
}

function validatePostIdRange(f){
	if (f.firstPostId.value.length == 0
		|| isNaN(f.firstPostId.value * 1)
		|| f.lastPostId.value.length == 0
		|| isNaN(f.lastPostId.value * 1)) {
		alert("输入ID范围有误");
		return false;
	}

	return true;
}

function validateDateRange(f){
	var fromHour = f.fromHour.value * 1;
	var fromMinutes = f.fromMinutes.value * 1
	var toHour = f.toHour.value * 1;
	var toMinutes = f.toMinutes.value * 1

	if (f.fromDay.selectedIndex == 0 
		|| f.fromMonth.selectedIndex == 0
		|| f.fromYear.selectedIndex == 0
		|| f.fromHour.value.length == 0
		|| f.fromMinutes.value.length == 0
		|| isNaN(fromHour)
		|| isNaN(fromMinutes)
		|| fromHour < 0 || fromHour > 59
		|| fromMinutes < 0 || fromMinutes > 59
		|| f.toDay.selectedIndex == 0 
		|| f.toMonth.selectedIndex == 0
		|| f.toYear.selectedIndex == 0
		|| f.toHour.value.length == 0
		|| f.toMinutes.value.length == 0
		|| isNaN(toHour)
		|| isNaN(toMinutes)
		|| toHour < 0 || toHour > 59
		|| toMinutes < 0 || toMinutes > 59) {
		alert("输入时间范围有误");
		return false;
	}

	return true;
}

function writeTimeGroup(fieldNamePrefix){
	document.write("<input type='text' style='width: 35px;' maxlength='2' name='" + fieldNamePrefix + "Hour'> : ");
	document.write("<input type='text' style='width: 35px;' maxlength='2' name='" + fieldNamePrefix + "Minutes'>");
}
</script>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/admin/system/lucene/reindex" method="post" id="form1"
			 onSubmit="return validateReindexForm(this);">
			<input id="id" name="id" value="${bean.id}" type="hidden">
			<div align="center">
		 <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 系统管理  &raquo; <a href="${pageContext.request.contextPath}/admin/system/lucene/query">重建索引</a></th></tr>
	    </thead>
	    </table>
	     <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
			<table  align="center" class="${tableclass}" id="col1">
				<thead>
					<tr class="sortable">
						<th colspan="2">
							<div align="center">
								重建索引
							</div>
						</th>
					</tr>
				</thead>
				<tr>
					<td>
						<div align="right">
							<input type="radio" id="reindexByDate" value="1" name="type" checked="checked">依日期 
							<font color="ff0000">*</font>
						</div>
					</td>
					<td>
						
							开始
								<span class="gensmall">(dd/mm/yyyy hh:mm)</span>

								<script type="text/javascript">
									writeOptions("fromDay", 1, 31);
									writeOptions("fromMonth", 1, 12);
									writeOptions("fromYear", 1995, new Date().getFullYear());
									writeTimeGroup("from");
								</script>
				 <br>结束								<span class="gensmall">(dd/mm/yyyy hh:mm)</span>

								<script type="text/javascript">
									writeOptions("toDay", 1, 31);
									writeOptions("toMonth", 1, 12);
									writeOptions("toYear", 1995, new Date().getFullYear());
									writeTimeGroup("to");
								</script>
						
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							<input type="radio" id="reindexByMessage" value="2" name="type">依实体类型
							<font color="ff0000">*</font>
						</div>
					</td>
					<td>
						<input type="radio" id="prodType" name="entityType" value="0" checked="checked"/> 商品
						<input type="radio" id="shopType" name="entityType"/ value="1"> 商城
						<!-- 
						<input type="radio" id="newsType" name="entityType" value="2"/> 新闻
						 -->
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							依实体Id
							<font color="ff0000">*</font>
						</div>
					</td>
					<td>
						
							开始 <input type="text" name="firstPostId" onblur="chooseByPostId(this);" />
							结束 <input type="text" name="lastPostId" onBlur="chooseByPostId(this);" />
						
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							选项
						</div>
					</td>
					<td>
						<input type="checkbox" id="avoidDuplicated" name="avoidDuplicatedRecords" value="yes" checked="checked" /> 
						加入索引前检查文章是否存在,这样可以避免数据重复, 但是建立索引的过程将花较久的时间完成<br>
						<input type="radio" name="indexOperationType" id="operationTypeAppend"  value="append" checked="checked"/>
						加入文件到索引,将纪录加到现存的索引数据库而不重新建立.<br>
						<input type="radio" name="indexOperationType" id="operationTypeRecreate" value="recreate" />
						从无到有重建索引,建立一个全新的索引数据库. 这样将会删除所有存在的纪录.
					</td>
				</tr>
				
				<tr>

					<td colspan="2">
						<div align="center">
						<auth:auth ifAnyGranted="F_SYSTEM">
							<input type="submit" value="提交" />
					    </auth:auth>
							<input type="reset" value="重置" />
						</div>
					</td>
				</tr>
			</table>
			</div>
		</form>
	</body>
</html>