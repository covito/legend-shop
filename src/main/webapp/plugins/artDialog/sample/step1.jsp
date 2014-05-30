<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>step1</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/artDialog/artDialog.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/artDialog/plugins/iframeTools.js"></script>
<style type="text/css">
body {color:#333;font-size:12px;  font-family: "\5b8b\4f53", sans-serif;line-height: 1.5em;}
ul li{ list-style:none;}
</style>
<script type="text/javascript">
		  function deleteUser(){
		     document.getElementById("step1Form").submit();
		  }
		</script>

</head>
<body>
	<form action="step2.jsp" method="get" id="step1Form">
		<ul>
			<li>姓名:Bob</li>
			<li>性别:男</li>
			<li>年龄:29</li>
			<li>详细信息:扒拉扒拉扒拉</li>
			<li><input type="button" value="删除该用户" onclick="deleteUser();" />
			</li>
		</ul>
	</form>
</body>
</html>