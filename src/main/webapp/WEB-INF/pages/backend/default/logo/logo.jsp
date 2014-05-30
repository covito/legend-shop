<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
	<head>
		<title>创建 Logo</title>
		<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
		<script language="javascript">
	$.validator.setDefaults({
	});

	$(document).ready(function() {
	jQuery("#form1").validate({
	        rules: {
            url: "required"
        },
        messages: {
			url: {
        	required:"请输入链接地址"
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
		<form action="${pageContext.request.contextPath}/admin/logo/save" method="post" id="form1"
			enctype="multipart/form-data">
			<input id="userId" name="userId" value="${bean.userId}" type="hidden">
			<div align="center">
				    <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/logo/query">Logo管理</a> &raquo; 创建 Logo</th></tr>
	    </thead>
	    </table>
			<table  align="center" class="${tableclass}" id="col1">
				<thead>
					<tr class="sortable">
						<th colspan="2">
							<div align="center">
								创建 Logo
							</div>
						</th>
					</tr>
				</thead>
				<tr>
					<td>
						<div align="right">
							上传图片(170px*60px)
							<font color="ff0000">*</font>
						</div>
					</td>
					<td>
						
							<input type="file" name="file" id="file"/>
							<input type="hidden" name="bannerName" id="bannerName" size="80" 	value="${bean.logoPic}" />
						
					</td>
				</tr>
				<c:if test="${bean.logoPic!=null}">
					<tr>
						<td>
							<div align="center">
								原有图片
							</div>
						</td>
						<td>

								<a href="<ls:photo item='${bean.logoPic}'/>" target="_blank"> <img
									src="<ls:photo item='${bean.logoPic}'/>" width="170" height="44"/>
							</a>

						</td>
					</tr>
				</c:if>
				<tr>

					<td colspan="2">
						<div align="center">
						<auth:auth ifAnyGranted="FA_SHOP">
							<input type="submit" value="提交" />
					    </auth:auth>
							<input type="reset" value="重置" />
							<input type="button" value="返回"
								onclick="window.location='${pageContext.request.contextPath}/admin/logo/query'" />
						</div>
					</td>
				</tr>
			</table>
			</div>
		</form>
	</body>
</html>