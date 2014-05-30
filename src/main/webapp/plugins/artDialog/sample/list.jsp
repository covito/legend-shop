<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>A data list page</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/artDialog/artDialog.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/artDialog/plugins/iframeTools.js"></script>
<link  href="<%=request.getContextPath() %>/plugins/artDialog/skins/idialog.css"  rel="stylesheet"   type="text/css"/>
<style type="text/css">
body {color:#333;font-size:12px;  font-family: "\5b8b\4f53", sans-serif;line-height: 1.5em;}
ul li{ list-style:none;}
</style>
<script type="text/javascript">
		  
		  function testPop(){
		  var testDialog = art.dialog({
    id: 'testDialog01', lock:false,
    content: '我初始化后会返回控制接口，请注意接收'});
		//  testDialog.close();
		  }
		  
		  function showPopDiv(){
		 
		   var url="step1.jsp";
		    alert(url);
		   var options={id:"test01",title:"查看用户详情",width:400,height:240,lock:false,closeFn: function(){} };
		    art.dialog.open(url,options);
		  }
		  
		  function hidePopDiv(){
		       //art.dialog.get('test01').close();
		       art.dialog.get('test01').time(3);
		    }
		</script>

</head>
<body>
	<ul>
		<li>姓名:Bob</li>
		
		<li>性别:男</li>
		<li>年龄:29</li>
		<li><input type="button" value="查看详细信息" onclick="showPopDiv();" />
		</li>
	</ul>

	<ul>
		<li>姓名:Alice</li>
		<li>性别:女</li>
		<li>年龄:22</li>
		<li><input type="button" value="测试弹出层"  onclick="testPop();"/>
		</li>
		
	</ul>
</body>
</html>