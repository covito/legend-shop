<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>step2</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/artDialog/artDialog.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/artDialog/plugins/iframeTools.js"></script>
<style type="text/css">
body {color:#333;font-size:12px;  font-family: "\5b8b\4f53", sans-serif;line-height: 1.5em;}
ul li{ list-style:none;}
</style>
</head>
<body>
	<ul>
		<li>成功删除了用户【Bob】的数据信息！</li>
		<li><input type="button" value="确定" onclick="refreshList();" />
		</li>
	</ul>
	<script type="text/javascript">
		   function refreshList(){
		     //alert(art.dialog.opener.location.href);
		    art.dialog.opener.location.href="list2.jsp";
		    // art.dialog.close();
		   }
		</script>
</body>
</html>
