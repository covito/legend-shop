<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>Page after deleted</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/artDialog/artDialog.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/artDialog/plugins/iframeTools.js"></script>
<link  href="<%=request.getContextPath() %>/plugins/artDialog/skins/idialog.css"  rel="stylesheet"   type="text/css"/>
<style type="text/css">
body {color:#333;font-size:12px;  font-family: "\5b8b\4f53", sans-serif;line-height: 1.5em;}
ul li{ list-style:none;}
</style>

<script type="text/javascript">
		  function deleteThisUser(){
		   art.dialog.confirm("您确定删除此用户吗？",
		    function(){
		       alert("对不起，删除失败");
		    },
		    function(){
		      alert('您取消了删除操作');
		    }
		    
		   );
		  }
		  
		</script>

</head>
<body>
	<ul>
		<li>姓名:Alice</li>
		<li>性别:女</li>
		<li>年龄:22</li>
		<li><input type="button" value="删除" onclick="deleteThisUser();" />
		</li>
	</ul>
</body>
</html>