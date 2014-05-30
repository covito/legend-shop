<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<style>
#form1 p { padding:5px;  }
#form1 input { padding:4px; border:1px solid #CCC; }
#form1 input:focus { border-color:#426DC9; }
#lform1 .login-form-error { background:#FFFBFC; border-color:#F00 !important; }
</style>
<div id="form1">
	        <input type="hidden" id="rand" name="rand"/>
			<input type="hidden" id="cannonull" name="cannonull" value='<fmt:message key="randomimage.errors.required"/>'/>
			<input type="hidden" id="charactors4" name="charactors4" value='<ls:i18n key="randomimage.charactors.required" length="4"/>'/> 
			<input type="hidden" id="errorImage" name="errorImage" value='<fmt:message key="error.image.validation"/>'/> 
		 <p><label>激活码：<input id="liensekey" name="liensekey" type="text"></label></p>
		 <p><label>验证码：<input id="randNum" name="randNum" type="text"  maxlength="4" size="7" tabindex="3" >
		 <img id="randImage" name="randImage" src="<ls:templateResource item='/captcha.svl'/>"   style="vertical-align: top;"/>
			<a href="javascript:void(0)" onclick="javascript:changeRandImg('${pageContext.request.contextPath}')" style="font-weight: bold;">
						<fmt:message key="change.random2"/>
			</a>
		</label></p>
</div>
<script type="text/dialog">
var api = this,// 对话框扩展方法
	$ = function (id) {return document.getElementById(id)},
	form = $('form1'),
	liensekey = $('liensekey'),
	randImage = $('randImage');

// 操作对话框
api.title('系统升级')
	// 自定义按钮
	.button(
		{
			name: '升级',
			callback: function () {
				if (check(liensekey) && checkRandImage(randImage)){
					var upgradeResult = postUpgrade();
                    if(upgradeResult == 1){
							this.content('升级成功了').time(2);
							 var win = art.dialog.open.origin;//来源页面
   							 win.location.reload();
						}else if(upgradeResult == 2) {
							alert('激活码错误');
							changeRandImg('${pageContext.request.contextPath}');
						}else if(upgradeResult == 3) {
							alert('不能升级到对应版本');
							changeRandImg('${pageContext.request.contextPath}');
						}
                  }

				return false;
			},
			focus: true
		},
		{
			name: '取消'
		}
		/*, 更多按钮.. */
	)
	// 锁屏
	.lock();
	
liensekey.focus();

// 表单验证
var check = function (input) {
	if (input.value === '') {
		inputError(input);
		input.focus();
		return false;
	} else {
		return true;
	};
};

var checkRandImage = function (input) {
	if (!validateRandNum('${pageContext.request.contextPath}')) {
		inputError(input);
		input.focus();
		return false;
	} else {
		return true;
	};
};

// 输入错误提示
var inputError = function (input) {
	clearTimeout(inputError.timer);
	var num = 0;
	var fn = function () {
		inputError.timer = setTimeout(function () {
			input.className = input.className === '' ? 'login-form-error' : '';
			if (num === 5) {
				input.className === '';
			} else {
				fn(num ++);
			};
		}, 150);
	};
	fn();
};
</script>