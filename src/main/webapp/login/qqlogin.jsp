<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
		<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="100342945" data-redirecturi="http://www.legendshop.cn/qc_callback.html" charset="utf-8"></script>
  </head>
  
  <body>
    This is my JSP page. <br>
    <span id="qq_login_btn"></span>
    
    <label><input type="button" value=" 获取 " onclick="getInfo();"/></label>
    
    		<div class="pad_2">
			<label></label><textarea rows="10" cols="20" readonly="1" class="callback" id="getInfo_back"></textarea>
		</div>
<script type="text/javascript">
	QC.Login({//按默认样式插入QQ登录按钮
		btnId:"qq_login_btn"	//插入按钮的节点id
	});
	
function getInfo() {
	if(QC.Login.check()){
		var getInfo_back = document.getElementById("getInfo_back");
		alert("getInfo_back = " + getInfo_back);
		getInfo_back.value = "";
	
		QC.api("get_user_info")
			.success(function(s){//成功回调
				alert("获取用户信息成功！当前用户昵称为："+s.data.nickname);
			})
			.error(function(f){//失败回调
				alert("获取用户信息失败！");
			})
			.complete(function(c){//完成请求回调
				alert("获取用户信息完成！");
				getInfo_back.value = c.stringifyData();
			});
	}else{
		alert("请登录后体验");
	}
}
</script>


  </body>
</html>
