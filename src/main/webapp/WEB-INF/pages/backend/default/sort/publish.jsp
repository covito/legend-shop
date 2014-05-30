<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
<link href="<ls:templateResource item="/common/default/css/category-min.css" />" rel="stylesheet" type="text/css" media="screen" />
<link href="<ls:templateResource item="/common/default/css/core.css" />" rel="stylesheet" type="text/css" media="screen" />
</head>
<body scroll="no">
	<div id="layout">
		<div class="tabsPage" id="navTab">
			<div class="tabsPageContent" style="border: 0px;">
				<div style="display: block;" class="page">
					<h2 class="contentTitle">选择商品总分类</h2>
					<div class="pageContent" style="padding:5px">
						<div class="cate-main">
							<div id="cate-cascading">
								<div class="cc-listwrap">
									<ol class="cc-list" id="cc-list" style="left: 0px;">
										<li class="cc-list-item" tabindex="-1" style="">
											<div class="cc-cbox-filter">
												<label for="cc-cbox-filter1">输入一级商品分类</label>
												<input id="cc-cbox-filter1" class="lebelinput" name="cc-cbox-filter1" style="width: 176px;">
											</div>
											<div class="cc-tree">
												<ul  class="cc-tree-cont">
													<li class="cc-tree-group  cc-tree-expanded" >
													   <ul class="cc-tree-gcont" id="sortList" name="sortList">
															<c:forEach items="${requestScope.sortList }" var="sort" >
																<li  id="${sort.sortId}" class='cc-tree-item <c:if test="${fn:length(sort.nsort)>0}">cc-hasChild-item</c:if>'>${sort.sortName }</li>
															</c:forEach>
														</ul>
													</li>
												</ul>
											</div>
										</li>

										<li id="category2" class="cc-list-item" tabindex="-1"></li>
										<li id="category3" class="cc-list-item" tabindex="-1"></li>
										<li id="category4" class="cc-list-item" tabindex="-1"></li>
									</ol>
								</div>
							</div>
							<div class="cate-path">
								<dl>
									<div class="clearfix">
										<dt>您当前选择的是：</dt>
										<dd>
											<ol id="selected-category" class="category-path">
											</ol>
										</dd>
									</div>
								</dl>
							</div>
							<form name="mainform" action="<ls:url address='/admin/product/load'/>" method="post" id="mainform">
							<input type="hidden" id="sortId" name="sortId" />
							<input type="hidden" id="nsortId" name="nsortId" />
							<input type="hidden" id="subsortId" name="subsortId" />
							<input type="hidden" id="brandId" name="brandId"/>
							<input type="hidden" id="prodId" name="prodId"  value="${prodId}"/>
								<fieldset>
									<div class="cateBottom">
										<span class="cateBtn catePublish"><button id="catePubBtn"  type="button">我已阅读以下规则，现在发布</button>
										</span> 
									</div>
								</fieldset>
							</form>

							<div class="agreement">
								<div class="notice">
									<h5>发布须知：</h5>
									禁止发布侵犯他人知识产权的商品，请确认商品符合知识产权保护的规定
								</div>
								<h5 align="center">规则</h5>
										用户应遵守国家法律、行政法规、部门规章等规范性文件。对任何涉嫌违反国家法律、行政法规、部门规章等规范性文件的行为，
										本平台有权酌情处理。但平台对用户的处理不免除其应尽的法律责任。
										用户在平台的任何行为，应同时遵守与平台及其关联公司所签订的各项协议。 平台有权随时变更本规则并在网站上予以公告。
										若用户不同意相关变更，应立即停止使用平台的相关服务或产品。平台有权对用户行为及应适用的规则进行单方认定，并据此处理。
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
		<script type="text/javascript">
					$(document).ready(function(e) {
								$("li.cc-tree-item").die('click').live('click',function() {
										$this = $(this);
										var sortId = $this.attr("id");
										$("#sortId").val(sortId);
										$("#nsortId").val('');
										$("#subsortId").val('');
										$("#brandId").val('');
										var targetUrl = "${pageContext.request.contextPath}/admin/sort/publish2/" +sortId ;
									 	var childNode = "category2";
										retrieveNext(targetUrl, childNode);
										$("li.cc-selected").removeClass("cc-selected");
										$this.addClass("cc-selected");
										$("#category3").empty();
										$("#category4").empty();
										$("#selected-category").empty();
										$("#selected-category").append("<li>" + $this.text() + "</li>");
							    });

										    $('.lebelinput').live('focus', function(){
												$(this).prev("label").hide();
											});
											//restoring input value on blrr if the input is left blank
											$('.lebelinput').live('blur', function(){
											   $this = $(this);
												if( $this.val()=='')
												{
													 $this.prev("label").show();
												}
											});
											
											$('#cc-cbox-filter1').die('keypress').live('keypress', function(e){
											   var valueText = $(this).val();
												 if(e.which == 13) {
											        		//call ajax
											               $.post("${pageContext.request.contextPath}/admin/sort/loadsort", 
														        {
														            "sortName": valueText
														          },
														        function(retData) {
														              	$("#sortList").html(retData);
																},'html');
											    }
											});
										
                                      $('#cc-cbox-filter2').die('keypress').live('keypress', function(e){
											   var valueText = $(this).val();
												 if(e.which == 13) {
											        		//call ajax
											               $.post("${pageContext.request.contextPath}/admin/sort/loadnsort", 
														        {
														           "sortId": $("#sortId").val(),
														            "nsortName": valueText
														          },
														        function(retData) {
														              	$("#nsortList").html(retData);
																},'html');
											    }
											});
											
									 $('#cc-cbox-filter3').die('keypress').live('keypress', function(e){
											   var valueText = $(this).val();
												 if(e.which == 13) {
											        		//call ajax
											               $.post("${pageContext.request.contextPath}/admin/sort/loadsubsort", 
														        {
														           "nsortId": $("#nsortId").val(),
														            "nsortName": valueText
														          },
														        function(retData) {
														              	$("#subSortList").html(retData);
																},'html');
											    }
											});
											
										$('#cc-cbox-filter4').die('keypress').live('keypress', function(e){
											   var valueText = $(this).val();
												 if(e.which == 13) {
											        		//call ajax
											               $.post("${pageContext.request.contextPath}/admin/brand/loadBrandByName/" + $("#subsortId").val(), 
														        {
														            "brandName": valueText
														          },
														        function(retData) {
														              	$("#brandList").html(retData);
																},'html');
											    }
											});
											
											$("#catePubBtn").click(function() {
												if($("#sortId").val() == '' || $("#nsortId").val() == '' ||  $("#subsortId").val() == '' ){
													$("#selected-category").append("<li><font color='red'>  &nbsp;  -   请选择商品分类</font></li>");
												}else{
												var prodId = $("#prodId").val();
												var $mainform = $("#mainform"); 
												if(prodId != ''){
												   $mainform.attr('action', "${pageContext.request.contextPath}/admin/product/update/" + prodId);
												   $mainform.submit();
												}else{
													$mainform.attr('action', "${pageContext.request.contextPath}/admin/product/load");
												   $mainform.submit();
												}
													
												}
											});

							});
		//获取下一页
		function retrieveNext(targetUrl, childNode){
			if(childNode != null && childNode != ''){
						 jQuery.ajax({
							url: targetUrl, 
							type:'post', 
							async : false, //默认为true 异步   
							dataType : 'html', 
							error:function(data){
							 //alert(data);   
							},   
							success:function(data){
								   $("#" + childNode).html(data);
							}   
							});  
				}
			}				
				</script>
</body>
</html>