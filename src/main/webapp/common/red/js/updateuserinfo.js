$("#petName").focus(function() {
	alert('into');
	$("#petName_msg").parent().removeClass("prompt-error");
	$("#petName_msg").parent().addClass("prompt-06");
	$("#petName_msg").html("4-20个字符，可由中英文、数字、“_”、“-”组成");
});

// 校验petName是否正确
$("#petName").blur(function() {
	var petName = $("#petName").val();
	if (petName == null || petName == "") {
		petNameFlag = 1;
		$("#petName_msg").html("请输入4个字符以上的昵称");
		$("#petName_msg").parent().addClass("prompt-error");
		$("#petName_msg").parent().removeClass("prompt-06");
		$("#petName_orderly").removeClass("icon-orderly");
		return;
	}
	if (originalPetName == petName) {
		petNameFlag = 0;
		$("#petName_msg").html("");
		return;
	}

	var reg = new RegExp("^([a-zA-Z0-9_-]|[\\u4E00-\\u9FFF])+$", "g");

	var reg_number = /^[0-9]+$/; // 判断是否为数字的正则表达式
	if (reg_number.test(petName)) {
		$("#petName_msg").parent().addClass("prompt-error");
		$("#petName_msg").parent().removeClass("prompt-06");
		$("#petName_msg").html("昵称不能全是数字");
		$("#petName_orderly").removeClass("icon-orderly");
		petNameFlag = 1;
	} else if (petName.replace(/[^\x00-\xff]/g, "**").length < 4) {
		petNameFlag = 1;
		$("#petName_msg").parent().addClass("prompt-error");
		$("#petName_msg").parent().removeClass("prompt-06");
		$("#petName_msg").html("请输入4个字符以上的昵称");
		$("#petName_orderly").removeClass("icon-orderly");
	} else if (petName.replace(/[^\x00-\xff]/g, "**").length > 20) {
		petNameFlag = 1;
		$("#petName_msg").parent().addClass("prompt-error");
		$("#petName_msg").parent().removeClass("prompt-06");
		$("#petName_msg").html("4-20个字符，可由中英文、数字、“_”、“-”组成");
		$("#petName_orderly").removeClass("icon-orderly");
	} else if (!reg.test(petName)) {
		$("#petName_msg").parent().addClass("prompt-error");
		$("#petName_msg").parent().removeClass("prompt-06");
		$("#petName_msg").html("昵称格式不正确");
		$("#petName_orderly").removeClass("icon-orderly");
		petNameFlag = 1;
	} else {
		checkPetName();
	}

});

function validPetName(petName){
	var reg = new RegExp("^([a-zA-Z0-9_-]|[\\u4E00-\\u9FFF])+$", "g");
	var reg_number = /^[0-9]+$/; // 判断是否为数字的正则表达式
	if (reg_number.test(petName)) {
		$("#petName_msg").parent().addClass("prompt-error");
		$("#petName_msg").parent().removeClass("prompt-06");
		$("#petName_msg").html("昵称不能全是数字");
		$("#petName_orderly").removeClass("icon-orderly");
		petNameFlag = 1;
	} else if (petName.replace(/[^\x00-\xff]/g, "**").length < 4) {
		petNameFlag = 1;
		$("#petName_msg").parent().addClass("prompt-error");
		$("#petName_msg").parent().removeClass("prompt-06");
		$("#petName_msg").html("请输入4个字符以上的昵称");
		$("#petName_orderly").removeClass("icon-orderly");
	} else if (petName.replace(/[^\x00-\xff]/g, "**").length > 20) {
		petNameFlag = 1;
		$("#petName_msg").parent().addClass("prompt-error");
		$("#petName_msg").parent().removeClass("prompt-06");
		$("#petName_msg").html("4-20个字符，可由中英文、数字、“_”、“-”组成");
		$("#petName_orderly").removeClass("icon-orderly");
	} else if (!reg.test(petName)) {
		$("#petName_msg").parent().addClass("prompt-error");
		$("#petName_msg").parent().removeClass("prompt-06");
		$("#petName_msg").html("昵称格式不正确");
		$("#petName_orderly").removeClass("icon-orderly");
		petNameFlag = 1;
	}
}

function checkPetName() {
	$("#petName").attr({
		disabled : "disabled"
	});
	$("#petName_msg").html("昵称唯一性验证中，请稍等...");
	jQuery.ajax({
		type : "post",
		url : "/user/userinfo/checkPetName.action",
		data : "userInfo.petName=" + encodeURI(encodeURI($("#petName").val())),
		success : function(html) {
			if ("0" == html) {
				$("#petName_msg").parent().removeClass("prompt-error");
				$("#petName_msg").parent().addClass("prompt-06");
//				$("#petName_msg").html("恭喜您，暂时还没有用户注册该昵称！");
				$("#petName_msg").html("");
				$("#petName_orderly").addClass("icon-orderly");
				$("#petName").attr({
					disabled : ""
				});
				petNameFlag = 0;

				//if (ifApply == 0) {
				//	$("#submitChangePetName").click();
				//}

			} else if ("1" == html) {
				$("#petName_msg").parent().addClass("prompt-error");
				$("#petName_msg").parent().removeClass("prompt-06");
				$("#petName_msg").html("此昵称已被其他用户抢注，请修改");
				$("#petName").attr({
					disabled : ""
				});
				petNameFlag = 1;
				$("#petName_orderly").removeClass("icon-orderly");
			} else if ("3" == html) {
				$("#petName_msg").parent().addClass("prompt-error");
				$("#petName_msg").parent().removeClass("prompt-06");
				$("#petName_msg").html("昵称的长度过短");
				$("#petName").attr({
					disabled : ""
				});
				petNameFlag = 1;
				$("#petName_orderly").removeClass("icon-orderly");
			} else if ("4" == html) {
				$("#petName_msg").parent().addClass("prompt-error");
				$("#petName_msg").parent().removeClass("prompt-06");
				$("#petName_msg").html("昵称的长度过长");
				$("#petName").attr({
					disabled : ""
				});
				petNameFlag = 1;
				$("#petName_orderly").removeClass("icon-orderly");
			} else if ("5" == html) {
				$("#petName_msg").parent().addClass("prompt-error");
				$("#petName_msg").parent().removeClass("prompt-06");
				$("#petName_msg").html("昵称不能全是数字");
				$("#petName").attr({
					disabled : ""
				});
				petNameFlag = 1;
				$("#petName_orderly").removeClass("icon-orderly");
			} else if ("6" == html) {
				$("#petName_msg").parent().addClass("prompt-error");
				$("#petName_msg").parent().removeClass("prompt-06");
				$("#petName_msg").html("昵称格式不正确");
				$("#petName").attr({
					disabled : ""
				});
				petNameFlag = 1;
				$("#petName_orderly").removeClass("icon-orderly");
			} else {
				$("#petName_msg").parent().addClass("prompt-error");
				$("#petName_msg").parent().removeClass("prompt-06");
				$("#petName_msg").html("昵称格式不正确");
				$("#petName").attr({
					disabled : ""
				});
				petNameFlag = 1;
				$("#petName_orderly").removeClass("icon-orderly");
			}
		},
		error : function(data) {
			$("#petName_msg").parent().addClass("prompt-error");
			$("#petName_msg").parent().removeClass("prompt-06");
//			$("#petName_msg").html("网络传输异常，请稍后再试...");
			$("#petName").attr({
				disabled : ""
			});
			petNameFlag = 1;
			$("#petName_orderly").removeClass("icon-orderly");
		}
	});
}
/*
$("#uname").focus(function() {
	$("#userName_msg").parent().removeClass("prompt-error");
	$("#userName_msg").parent().addClass("prompt-06");
	$("#userName_msg").html("请输入真实姓名，20个英文或10个汉字");
});
*/

// 校验Uname是否正确
$("#uname").blur(
		function() {
			// delspace("uname"); //首先去掉空格
			var uname = $("#uname").val();
			// 判断是否为空
			if (uname == null || uname == "") {
				// 姓名中不允许有连续多个·
//				$("#userName_msg").parent().addClass("prompt-error");
//				$("#userName_msg").parent().removeClass("prompt-06");
//				$("#userName_msg").html("请输入真实姓名，20个英文或10个汉字");
//				userNameFlag = 1;
//				$("#userName_orderly").removeClass("icon-orderly");
				userNameFlag = 0;
				$("#userName_msg").html("");
			} else if (uname.indexOf("··") != -1) {
				$("#uname").addClass("red");
				$("#userName_msg").parent().addClass("prompt-error");
				$("#userName_msg").parent().removeClass("prompt-06");
				$("#userName_msg").html("请输入真实姓名，20个英文或10个汉字");
				$("#userName_orderly").removeClass("icon-orderly");
				userNameFlag = 1;
				// 姓名前后不能加·
			} else if (uname.substring(0, 1) == "·"
					|| uname.substring(uname.length - 1) == "·") {
				userNameFlag = 1;
				$("#userName_msg").parent().addClass("prompt-error");
				$("#userName_msg").parent().removeClass("prompt-06");
				$("#userName_msg").html("请输入真实姓名，20个英文或10个汉字");
				$("#userName_orderly").removeClass("icon-orderly");
			} else {
				// · 占用两个字节
				var uname_ = replaceChar("uname", "·"); // 去掉姓名中的·

				// var reg = new
				// RegExp("^([a-zA-Z0-9_-]|[\\u4E00-\\u9FFF])+$","g");
				// var reg = new RegExp("^[\\u4E00-\\u9FFF]+$","g");
				var reg = new RegExp("^([a-zA-Z]|[\\u4E00-\\u9FFF])+$", "g");
				if (!reg.test(uname_)) {
					$("#userName_msg").parent().addClass("prompt-error");
					$("#userName_msg").parent().removeClass("prompt-06");
					$("#userName_msg").html("请输入真实姓名，20个英文或10个汉字");
					userNameFlag = 1;
					$("#userName_orderly").removeClass("icon-orderly");
				} else if (uname.replace(/[^\x00-\xff]/g, "**").length >= 4
						&& uname.replace(/[^\x00-\xff]/g, "**").length <= 20) {
					$("#uname").addClass("text");
					userNameFlag = 0;
					$("#userName_msg").html("");
					$("#userName_orderly").addClass("icon-orderly");
				} else {
					userNameFlag = 1;
					$("#uname").addClass("red");
					$("#userName_msg").parent().addClass("prompt-error");
					$("#userName_msg").parent().removeClass("prompt-06");
					$("#userName_msg").html("请输入真实姓名，20个英文或10个汉字");
					$("#userName_orderly").removeClass("icon-orderly");
				}
			}
		});


//$("#uname").blur();  

// 校验Uname是否正确
$("#uaddr").blur(function() {
	delspace("uaddr"); //首先去掉空格
	var addr = $("#uaddr").val();
	// 判断是否为空
	if (addr.replace(/[^\x00-\xff]/g, "**").length > 120) {
		addrFlag = 1;
		$("#uaddr_msg").parent().addClass("prompt-error");
		$("#uaddr_msg").parent().removeClass("prompt-06");
		$("#uaddr_msg").html("长度超长");
	} else {
		addrFlag = 0;
		$("#uaddr_msg").html("");
	}
});

// 邮箱格式判断
/*
$("#email").blur(function() {
	delspace("email");
	var email = $("#email").val();
	if (email == null || email == "") {
		emailFlag = 1;
		$("#email_msg").parent().addClass("prompt-error");
		$("#email_msg").parent().removeClass("prompt-06");
		$("#email_msg").html("请填写邮箱信息");
		$("#email_orderly").removeClass("icon-orderly");
		return;
	}
	emailFlag = checkEmailFont("email", "email_msg");
	//验证邮箱唯一性
	if(emailFlag==0){
	var mailData="userInfo.email="+email;
	jQuery.ajax({
		type : "post",
		url : "/user/userinfo/checkMail.action",
		data : mailData,
		success : function(html) {
			if (html == "0"){
                $("#email_orderly").addClass("icon-orderly");
			}else {
				emailFlag = 1;
				$("#email_msg").parent().addClass("prompt-error");
				$("#email_msg").parent().removeClass("prompt-06");
				$("#email_msg").html("该邮箱已被占用，请更换其他邮箱");
				$("#email_orderly").removeClass("icon-orderly");
				return;
			}
		}
	});
    }
});
*/
// 判断输入框内是否为邮箱格式 name为输入框ID msgName为提示信息的ID
function checkEmailFont(name, msgName) {
	var email = $("#" + name).val();

	if (email.replace(/[^\x00-\xff]/g, "**").length <= 4
			|| email.replace(/[^\x00-\xff]/g, "**").length >= 50) {

		$("#" + msgName).parent().addClass("prompt-error");
		$("#" + msgName).parent().removeClass("prompt-06");
		$("#" + msgName).html("邮箱长度不正确");
		$("#email_orderly").removeClass("icon-orderly");
		return 1;
	}

	var reg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
	if (reg.test(email)) {
		$("#" + msgName).html("");
		return 0;
	} else {
		$("#" + msgName).parent().addClass("prompt-error");
		$("#" + msgName).parent().removeClass("prompt-06");
		$("#" + msgName).html("邮箱格式不正确");
		$("#email_orderly").removeClass("icon-orderly");
		return 1;
	}
}

// 手机号码格式判断
$("#usermob").blur(function() {
	delspace("usermob");
	// 去掉手机首位0
	var usermob = $("#usermob").val();
	while (true) {
		var start = usermob.substring(0, 1);
		if (start == "0") {
			usermob = usermob.substring(1);
		} else {
			break;
		}
	}

//	$("#usermob").val(usermob);

	if (usermob == null || usermob == "") {
		$("#usermob_msg").parent().addClass("prompt-error");
		$("#usermob_msg").parent().removeClass("prompt-06");
		$("#usermob_msg").html("请填写手机信息");
		$("#usermob_orderly").removeClass("icon-orderly");
		userMobFlag = 1;
		return;
	}

	// var re = /^[1-9][0-9]*]*$/; //判断是否为数字的正则表达式
	var re = /^1{1}[3,4,5,8]{1}\d{9}$/; // 判断是否为数字的正则表达式
	if (!re.test(usermob)) {
		userMobFlag = 1;
		$("#usermob_msg").parent().addClass("prompt-error");
		$("#usermob_msg").parent().removeClass("prompt-06");
		$("#usermob_msg").html("请输入正确的手机号码");
		$("#usermob_orderly").removeClass("icon-orderly");
		return;
	} else {
		userMobFlag = 0;
		$("#usermob_msg").html("");
		$("#usermob_orderly").addClass("icon-orderly");
	}
	
	//验证手机
	if(userMobFlag==0){
		var mobData="userInfo.usermob="+usermob;
		jQuery.ajax({
			type : "post",
			url : "/user/userinfo/checkMobile.action",
			data : mobData,
			success : function(html) {
				if (html == 0){
					$("#usermob_orderly").addClass("icon-orderly");
				}else {
					userMobFlag = 1;
					$("#usermob_msg").parent().addClass("prompt-error");
					$("#usermob_msg").parent().removeClass("prompt-06");
					$("#usermob_msg").html("该手机已被占用，请更换其他手机");
					$("#usermob_orderly").removeClass("icon-orderly");
					return;
				}
			}
		});
	}
});
//校验uCid是否正确
$("#ucid").blur( function (){
	delspace("ucid");
	var ucid = $("#ucid").val();
	
	var reg = null; //判断是否为数字的正则表达式
	//判断是否为空
	if(ucid==null || ucid==""){
		cidFlag=0;
		$("#ucid_msg").html("");
	}else if(ucid.length==15){
		reg=/^[0-9]+$/;
		if(reg.test(ucid)){
			cidFlag=0;
			$("#ucid_msg").html("");
//			$("#ucid_orderly").addClass("icon-orderly");
		}else{
			cidFlag=1;
			$("#ucid_msg").html("请输入正确的身份证号码！15位居民身份证号码应由纯数字构成");
		}
	}else if(ucid.length==18){
		reg=/^[0-9]+[xX]{0,1}$/;
		if(reg.test(ucid)){
			cidFlag=0;
			$("#ucid_msg").html("");
//			$("#ucid_orderly").addClass("icon-orderly");
			ucid=ucid.replace("x","X");
			$("#ucid").val(ucid);
		}else{
			cidFlag=1;
			$("#ucid_msg").html("请输入正确的身份证号码！18位居民身份证号码应由数字或X构成");
		}
	}else{
		cidFlag=1;
		$("#ucid_msg").html("请输入正确的身份证号码！居民身份证号码为15或18位");
	}
});
$("#remark").blur(function() {
	$("#remark").val(replaceChar("remark", "<!--"));
	if ($("#remark").val() == "") {
		document.getElementById("remark").style.color = "#8C8C8C";
		$("#remark").val("没有填写兴趣爱好");
	}
	replaceBrackets("#remark");
});
// 将其他信息(备注)输入框内 置空 方便用户输入
$("#remark").focus(function() {
	if ($("#remark").val() == "没有填写兴趣爱好") {
		document.getElementById("remark").style.color = "#000000";
		$("#remark").val("");
	}
	// 将光标置于输入框文字最后面
	// cursor("remark");
});

$("#uaddr").blur(function() {
	$("#uaddr").val(replaceChar("uaddr", "<!--"));
	if ($("#uaddr").val() == "") {
		document.getElementById("uaddr").style.color = "#8C8C8C";
		$("#uaddr").val("请输入详细地址");
	}
	replaceBrackets("#uaddr");
});
$("#uaddr").focus(function() {
	if ($("#uaddr").val() == "请输入详细地址") {
		document.getElementById("uaddr").style.color = "#000000";
		$("#uaddr").val("");
	}
});

// 提交用户基本信息修改
function updateUserInfo() {
	updateUserInfoValid(1);
}

function updateUserInfoValid(status,url) {
//	if (petNameFlag != 0 || userNameFlag != 0 || emailFlag != 0
//			|| userMobFlag != 0 || addrFlag != 0 || cidFlag!=0) {
//		return;
//	}
	if ($("#petName").val() == "") {
		$("#petName_msg").parent().addClass("prompt-error");
		$("#petName_msg").parent().removeClass("prompt-06");
		$("#petName_msg").html("请输入昵称");
		scroller("petName", 500);
		return;
	}
	validPetName($("#petName").val());
	if (petNameFlag != 0) {
    	scroller("petName", 500);
    	return;
	}
	/*
	if ($("#uname").val() == "") {
		$("#userName_msg").parent().addClass("prompt-error");
		$("#userName_msg").parent().removeClass("prompt-06");
		$("#userName_msg").html("请输入真实姓名");
		scroller("uname", 500);
		return;
	}
	*/
    if (userNameFlag != 0) {
    	scroller("uname", 500);
    	return;
	}
    /*
    if (emailFlag != 0) {
    	scroller("email", 500);
    	return;
	}
	*/
    if (userMobFlag != 0) {
    	scroller("usermob", 500);
    	return;
	}
    if (addrFlag != 0) {
    	scroller("uaddr", 500);
    	return;
	}
    if (cidFlag != 0) {
    	scroller("ucid", 500);
    	return;
	}
	//if ($("#uaddr").val() == "") {
	//	$("#uaddr_msg").parent().addClass("prompt-error");
	//	$("#uaddr_msg").parent().removeClass("prompt-06");
	//	$("#uaddr_msg").html("请输入详细地址信息！");
	//	return;
	//}

	if ($("#province").val() == -1 || $("#city").val() == -1
			|| $("#county").val() == -1) {
		$("#province_msg").parent().addClass("prompt-error");
		$("#province_msg").parent().removeClass("prompt-06");
		$("#province_msg").html("请正确选择省市县信息！");
		scroller("province", 500);
		return;
	}
	if ($("#province").val() == null|| $("#city").val() == null
			|| $("#county").val() == null) {
		$("#province_msg").parent().addClass("prompt-error");
		$("#province_msg").parent().removeClass("prompt-06");
		$("#province_msg").html("请正确选择省市县信息！");
		scroller("province", 500);
		return;
	}
	if ($("#province").val() == ""|| $("#city").val() == ""
			|| $("#county").val() == "") {
		$("#province_msg").parent().addClass("prompt-error");
		$("#province_msg").parent().removeClass("prompt-06");
		$("#province_msg").html("请正确选择省市县信息！");
		scroller("province", 500);
		return;
	}
	if($("input[name=userInfo.sex]:checked").val()==null||$("input[name=userInfo.sex]:checked").val()==""){
		alert("请选择性别");
		return; 
	}
	
	var datas = "";

	datas += "petName=" + encodeURI(encodeURI($("#petName").val())) + "&";
	datas += "userName=" + encodeURI(encodeURI($("#uname").val())) + "&";
	datas += "addr="+ encodeURI(encodeURI($("#uaddr").val())) + "&";
	datas += "sex=" + $("input[name=userInfo.sex]:checked").val() + "&";
	datas += "province=" + $("#province").val() + "&";
	datas += "city=" + $("#city").val() + "&";
	datas += "county=" + $("#county").val() + "&";
	datas += "ucid=" + $("#ucid").val() + "&";

	datas += "birthday="
			+ encodeURI(encodeURI($("#birthdayYear").val() + "-"
					+ $("#birthdayMonth").val() + "-" + $("#birthdayDay").val()))
			+ "&";
	if($("input[name=userInfo.maritalStatus]:checked").val()!=null&&$("input[name=userInfo.maritalStatus]:checked").val()!=""){
		datas += "maritalStatus="
			+ $("input[name=userInfo.maritalStatus]:checked").val() + "&";
	}
	if($("#monthlyIncome").val()!=null&&$("#monthlyIncome").val()!=""){
		datas += "monthlyIncome=" + $("#monthlyIncome").val() + "&";
	}

	datas += "remark=" + encodeURI(encodeURI($("#remark").val())) + "&";

	if ($("#usermob").length != 0) {
		if ($("#usermob").val() == "") {
			alert("请输入手机信息！");
			return;
		}
		datas += "usermob=" + $("#usermob").val() + "&";
	}
	datas += "code=" + $("#code").val()+"&";
	datas += "returnpagecode=" + $("#returnpagecode").val()+"&";
	jQuery.ajax({
		type : "post",
		url : "/user/userinfo/updateUserInfo.action",
		data : datas,
		success : function(html) {
			if ((status == 2 || status == 3) && html == "0"){
				window.location.href = url;
				alert(window.location.href);
				return; 
			}
			if ("MO-1" == html) {
				alert("请输入正确的手机号码");
			} else if ("MO-2" == html) {
				alert("您的手机号已被占用，请更换手机号码");
			} else if ("0" == html) {
				if ("校园用户" == originalUserType
						&& originalProvince != $("#province").val()) {
					originalProvince = $("#province").val();
					document.documentElement.scrollTop = 0;
					setSchoolName("");
					$("#reminded").click();
				}
				 newtype();
				 setTimeout("jdThickBoxclose()", 3000);
			} else {
				alert("保存失败，请稍后再试...");
			}
		}
	});

}

function newtype() {
    jQuery.jdThickBox({
        type: "text", 
        title: "提示",
        width: 120,
        height:70,
        source: "<div class=\"m warn\"><div class=\"mc\"><s><\/s>资料保存成功<div class=\"clr\"><\/div><\/div><div class=\"btns\"><a href=\"javascript:void(0)\" class=\"btn btn-12 ftx-03\" onclick=\"jdThickBoxclose()\"><s><\/s>关闭<\/a><\/div><\/div>",
        _autoReposi: true
        });
} 
/*
function verifyMail(url) {
	updateUserInfoMailOrMobileValid(2,url);
	
}
function verifyMobile(url) {
	updateUserInfoMailOrMobileValid(3,url);
}

function updateUserInfoMailOrMobileValid(status,url) {
	var datas = "";
	if (status==2) {
		if (emailFlag != 0) {
//	    	scroller("email", 500);
	    	return;
		}
		if ($("#email").length != 0) {
			if ($("#email").val() == "") {
				alert("请输入邮箱信息！");
				return;
			}
			datas += "email=" + $("#email").val();
		}
		if(emailFlag==1){
			alert("该邮箱已被占用，请更换其他邮箱！");
			return;
		}
	}else{
		 if (userMobFlag != 0) {
//		    	scroller("usermob", 500);
		    	return;
		}
		 if ($("#usermob").length != 0) {
				if ($("#usermob").val() == "") {
					alert("请输入手机信息！");
					return;
				}
			datas += "usermob=" + $("#usermob").val();
		}
	}
	jQuery.ajax({
		type : "post",
		url : "/user/userinfo/updateUserInfoMailOrMobile.action",
		data : datas,
		success : function(html) {
			if ((status == 2 || status == 3) && html == "0"){
				window.location.href = url;
				return; 
			}
			if ("MA-3" == html) {
				alert("请输入正确的邮箱");
			}else if ("MA-4" == html) {
				alert("该邮箱已被占用，请更换其他邮箱");
			}else if ("MA-5" == html) {
				alert("该邮箱已被占用，请更换其他邮箱");
			}else if ("MO-1" == html) {
				alert("请输入正确的手机号码");
			}else if ("MO-2" == html) {
				alert("您的手机号已被占用，请更换手机号码");
			} else {
				alert("保存失败，请稍后再试...");
			}
		}
	});
}
*/
