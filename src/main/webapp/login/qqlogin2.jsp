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
	 //调用QC.Login方法，指定btnId参数将按钮绑定在容器节点中
	   QC.Login({
	       //btnId：插入按钮的节点id，必选
	       btnId:"qq_login_btn",	
	       //用户需要确认的scope授权项，可选，默认all
	       scope:"all",
	       //按钮尺寸，可用值[A_XL| A_L| A_M| A_S|  B_M| B_S| C_S]，可选，默认B_S
	       size: "B_M"
	   }, function(reqData, opts){//登录成功
	       //根据返回数据，更换按钮显示状态方法
	       //系统登录，成功后跳转到用户中心
	       //window.location.href = "${pageContext.request.contextPath}/p/usercenter";
	       
	       var dom = document.getElementById(opts['btnId']),
	       _logoutTemplate=[
	            //头像
	            '<span><img src="{figureurl}" class="{size_key}"/></span>',
	            //昵称
	            '<span>{nickname}</span>',
	            //退出
	            '<span><a href="javascript:QC.Login.signOut();">退出</a></span>'	
	                     ].join("");

	       dom && (dom.innerHTML = QC.String.format(_logoutTemplate, {
	           nickname : QC.String.escHTML(reqData.nickname),
	           figureurl : reqData.figureurl
	              }));
	       
	   }, function(opts){//注销成功

	alert('QQ登录 注销成功');

	                     }
	);
	
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
