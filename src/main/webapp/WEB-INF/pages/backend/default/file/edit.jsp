<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
<head>
<title>编辑文件</title>
<script src="<ls:templateResource item='/common/js/jquery.js'/>"
	type="text/javascript"></script>
<script language="javascript">
	function saveFileContent(){
		    var data = {
	   				 "content": $("#content").val(),
                	"parentFilePath": $("#parentFilePath").val(),
                	"fileName": $("#fileName").val()
                };
    			$.ajax({
				url:"${pageContext.request.contextPath}/admin/system/file/save" , 
				data: data,
				type:'post', 
				dataType : 'json', 
				async : true, //默认为true 异步   
				error: function(jqXHR, textStatus, errorThrown) {
			 		// alert(textStatus, errorThrown);
				},
				success:function(result){
				   if(result != 0){
				   	alert("保存文件失败，失败代码是 " + result);
				   }else{
				   	alert("保存文件成功");
				   }
				}
				});
	}
</script>
</head>
<body>
		<input id="parentFilePath" name="parentFilePath" value="${parentFilePath}" type="hidden">
		<input id="fileName" name="fileName" value="${fileName}" type="hidden">
		<div align="center">
			<table class="${tableclass}" style="width: 100%">
				<thead>
					<tr>
						<th><a href="<ls:url address='/admin/index'/>"
							target="_parent">首页</a> &raquo; 系统管理 &raquo; ${title }</th>
					</tr>
				</thead>
			</table>
			<table align="center" class="${tableclass}" id="col1"
				style="width:100%">
				<tr>
					<td>
						<textarea style="width:100%; height: 400px" id="content" name="content">${content }</textarea>
						</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<auth:auth ifAnyGranted="F_SYSTEM">
								<input type="submit" value="提交"  onclick="javascript:saveFileContent()"/>
							</auth:auth>
						</div></td>
				</tr>
			</table>
		</div>
</body>
</html>