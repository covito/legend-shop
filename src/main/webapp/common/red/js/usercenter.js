/** JS for user center module */

$(document).ready(function() {
	userCenter.bindSubMenuClickEvent();
	// Initialize the right content to user center home page.
	$("#usercenterhome").click();
});

var userCenter = {
	bindSubMenuClickEvent : function() {
		//1. 个人中心首页
		this.bindUserCenterHomePage();
		//2. 交易信息 
		this.bindMyOrderInfoPage();
		
		//3.我的收藏 
		this.bindMyFavoritePage();
		this.bindProdcommentPage();
		this.bindProdConsultPage();
		
		//4. 我的消息 
		this.bindInboxPage();
		this.bindOutboxPage();
		
		//5. 个人资料 
		this.bindPersonalInfoPage();
		this.bindModifypasswordPage();
		this.bindReceiveaddressPage();
		
		//6.预存款 
		this.bindAdddepositPage();
		this.bindMydepositPage();
	},
	bindUserCenterHomePage : function() {
		var jqElement = $("#usercenterhome");
		jqElement.click(function() {
			userCenter.loadPageByAjax("/p/uchome");
			userCenter.changeSubNavClass(jqElement);
			userCenter.changeNavLocation("个人中心首页");
		});
	},

	bindMyOrderInfoPage : function() {
		var jqElement = $("#myordeer");
		jqElement.click(function() {
			userCenter.loadPageByAjax("/p/order?uc=uc");
			userCenter.changeSubNavClass(jqElement);
			userCenter.changeNavLocation("我的订单");
		});
	},
	bindProdcommentPage : function() {
		var jqElement = $("#prodcomment");
		jqElement.click(function() {
			userCenter.loadPageByAjax("/p/prodcomment");
			userCenter.changeSubNavClass(jqElement);
			userCenter.changeNavLocation("商品评论");
		});
	},
	bindProdConsultPage : function() {
		var jqElement = $("#prodcons");
		jqElement.click(function() {
			userCenter.loadPageByAjax("/p/prodcons");
			userCenter.changeSubNavClass(jqElement);
			userCenter.changeNavLocation("商品咨询");
		});
	},	
	
	
	bindMyFavoritePage : function() {
		var jqElement = $("#myfavourite");
		jqElement.click(function() {
			userCenter.loadPageByAjax("/p/favourite?curPageNO=1");
			userCenter.changeSubNavClass(jqElement);
			userCenter.changeNavLocation("我的收藏");
		});
	},
	
	bindInboxPage : function() {
		var jqElement = $("#inbox");
		jqElement.click(function() {
			userCenter.loadPageByAjax("/p/inbox");
			userCenter.changeSubNavClass(jqElement);
			userCenter.changeNavLocation("收件箱");
		});	
	},
	bindOutboxPage : function() {
		var jqElement = $("#outbox");
		jqElement.click(function() {
			userCenter.loadPageByAjax("/p/outbox");
			userCenter.changeSubNavClass(jqElement);
			userCenter.changeNavLocation("发件箱");
		});	
	},
	bindPersonalInfoPage : function() {
		var jqElement = $("#personinfo");
		jqElement.click(function() {
			userCenter.loadPageByAjax("/p/user");
			userCenter.changeSubNavClass(jqElement);
			userCenter.changeNavLocation("个人资料");
		});	
		
	},
	
	bindModifypasswordPage : function() {
		var jqElement = $("#modifypassword");
		jqElement.click(function() {
			userCenter.loadPageByAjax("/p/modifypassword");
			userCenter.changeSubNavClass(jqElement);
			userCenter.changeNavLocation("修改密码");
		});	
	},
	
	bindReceiveaddressPage : function() {
		var jqElement = $("#receiveaddress");
		jqElement.click(function() {
			userCenter.loadPageByAjax("/p/receiveaddress");
			userCenter.changeSubNavClass(jqElement);
			userCenter.changeNavLocation("收货地址");
		});	
	},
	
	bindAdddepositPage : function() {
		var jqElement = $("#adddeposit");
		jqElement.click(function() {
			userCenter.loadPageByAjax("/p/adddeposit#deposit");
			userCenter.changeSubNavClass(jqElement);
			userCenter.changeNavLocation("预存款充值");
		});	
	},
	
	bindMydepositPage : function() {
		var jqElement = $("#mydeposit");
		jqElement.click(function() {
			userCenter.loadPageByAjax("/p/mydeposit#deposit");
			userCenter.changeSubNavClass(jqElement);
			userCenter.changeNavLocation("我的预存款");
		});	
	},
	
	
	changeNavLocation : function(text) {
		$("#subNavLocation").html(text);
	},
	changeSubNavClass : function(jqElement) {
		$("#uc_left_nav h3").removeClass("focus");
		$("#uc_left_nav h3 li").removeClass("focus");
		jqElement.addClass("focus");
	},
	loadPageByAjax : function(url) {
		$.ajax({
			url : contextPath + url,
			async : false,
			success : function(data) {
				$('#rightContent').html(data);
			}
		});
	},
	refreshPageByAjax : function(url, JsonData) {
		$.post(contextPath + url, JsonData, function(data) {
			$('#rightContent').html(data);
		});
	}
}
var myOrder = {
	bindMyOrderContentPage : function() {
		$("#btn_keyword").click(function() {
			// alert($("#btn_keyword").val());
			if ($("#ip_keyword").val() == '商品名称、订单编号') {
				$("#ip_keyword").val("");
			}
			myOrder.queryOrders();
		});
		$("#orderType").change(function() {
			// alert($("#orderType").val());
			myOrder.queryOrders();
		});
		$("#orderActiveStatus").change(function() {
			// alert($("#orderActiveStatus").val());
			myOrder.queryOrders();
		});
		$("#ip_keyword").focus(function() {
			$(this).select();
			if ($(this).val() == '商品名称、订单编号') {
				$(this).val("");
			}
		});
	},

	queryOrders : function() {
		var url = "/p/order?uc=uc";
		url = url + "&orderType=" + $("#orderType").val()
				+ "&orderActiveStatus=" + $("#orderActiveStatus").val();
		var kwText = $("#ip_keyword").val();
		if(kwText == "商品名称、订单编号"){
			kwText="";
		}
	
		if (kwText != "" ) {
			var kwType = 0;
			var reg = /^(\d{22,})$/;
			if (reg.test(kwText)) {
				kwType = 1;
			}
			url = url + "&kwType=" + kwType;
		}
	//	alert(url+", kw: "+kwText);
		userCenter.refreshPageByAjax(url, {
			"kwText" : kwText
		});

	}
}

var mayFav = {
		bindPageAction:function(){
		this.bindCheckAllBox();
		this.bindDeleteBtn();
		this.bindClearAllBtn();	
	},	
	bindCheckAllBox : function() {
		$("#selectAllCheck").click(function() {
			var nextCheck = this.checked;
			$("input[name='favCheck']").each(function() {
				this.checked = nextCheck;
			});
		});

	},
	bindDeleteBtn : function() {
		$("#delBtn")
				.click(
						function() {
							var selectedFavId = "";
							$("input[name='favCheck']")
									.each(
											function() {
												if (this.checked == true) {
													selectedFavId = (selectedFavId == "" ? this.id
															: selectedFavId
																	+ ";"
																	+ this.id);
												}
											});
							// alert(selectedFavId);
							if (selectedFavId == "") {
								alert("请先选择您要删除的信息，再点击删除按钮!");
								return false;
							}
							if (!confirm("您确定要删除选中的信息吗？")) {
								return false;
							}

							var jsonData = {
								"selectedFavs" : selectedFavId
							};
							var url = contextPath + "/p/delfavourite";
							$.post(url, jsonData, function(data) {
								// alert(data.result+", "+data.message);
								$("#uc_left_nav h3:eq(3)").click();
								//Ray add 20130518
								$("input:checked").parent().parent().remove(); 
							});
						});
	},
	bindClearAllBtn : function() {
		$("#clrBtn").click(function() {
			if (!confirm("您确定要清空所有的收藏信息吗？")) {
				return false;
			}
			
			var url = contextPath + "/p/clrfavourite";
			$.get(url, null, function(data) {
				// alert(data.result+", "+data.message);
				$("#uc_left_nav h3:eq(3)").click();
				//Ray add 20130518
				$("input:checked").parent().parent().remove(); 
			});
		});
		
	}
	
	
	/** ============================account===================================*/
	
	
	

}
