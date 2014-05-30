<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="/WEB-INF/pages/common/include.dwz.jsp"></jsp:include>
<body scroll="no">
	<div id="layout">
		<div class="tabsPage" id="navTab">
			<div class="navTab-panel tabsPageContent layoutBox">
				<div class="page unitBox" style="display: block;">
					<h2 class="contentTitle">填写宝贝基本信息</h2>

					<div class="pageContent" layoutH="56">
						<form method="post" action="/buildpack/demo/common/ajaxDone.html"
							class="pageForm required-validate"
							onsubmit="return validateCallback(this, navTabAjaxDone);">
							<div class="formBar">
								<ul>
									<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
									<li><div class="buttonActive">
											<div class="buttonContent">
												<button type="submit">保存</button>
											</div>
										</div>
									</li>
									<li>
										<div class="button">
											<div class="buttonContent">
												<button type="button" class="close">取消</button>
											</div>
										</div></li>
								</ul>
							</div>

							<div class="tabs" currentIndex="0" eventType="click">
								<div class="tabsHeader">
									<div class="tabsHeaderContent">
										<ul>
											<li class="selected"><a
												href='<ls:url address='/admin/product/productProperty'/>'
												class="j-ajax"><span>宝贝基本信息</span> </a></li>
											<li><a
												href='<ls:url address='/admin/product/productDetails'/>'
												class="j-ajax"><span>宝贝描述</span> </a></li>
											<li><a
												href='<ls:url address='/admin/product/saleProperty'/>'
												class="j-ajax"><span>宝贝销售属性</span> </a></li>
										</ul>
									</div>
								</div>
								<div class="tabsContent">
									<div class="pageFormContent nowrap">
										<dl>
											<dt>宝贝类型111：</dt>
											<dd>
												<label><input type="radio" name="r1" />全新</label> <label><input
													type="radio" name="r1" />二手</label>
											</dd>
										</dl>

										<dl>
											<dt>品牌：</dt>
											<dd>
												<input type="hidden" name="orgLookup.id"
													value="${orgLookup.id}" /> <input type="text"
													class="required" name="orgLookup.orgName" value=""
													suggestFields="orgNum,orgName"
													suggestUrl="demo/database/db_lookupSuggest.html"
													lookupGroup="orgLookup" /> <a class="btnLook"
													href="demo/database/dwzOrgLookup.html"
													lookupGroup="orgLookup">查找带回</a>
											</dd>
										</dl>

										<dl>
											<dt>袖长：</dt>
											<dd>
												<label><input type="checkbox" name="c1" value="1" />29444</label>
												<label><input type="checkbox" name="c1" value="2" />吊带裙</label>
												<label><input type="checkbox" name="c1" value="3" />无袖/背心裙</label>
												<label><input type="checkbox" name="c1" value="5" />其他</label>
											</dd>
										</dl>

										<dl>
											<dt>领子A：</dt>
											<dd>
												<label><input type="checkbox" name="cp_20673"
													value="1" />立领</label> <label><input type="checkbox"
													name="cp_20673" value="2" />翻领</label> <label><input
													type="checkbox" name="cp_20673" value="3" />圆领</label> <label><input
													type="checkbox" name="cp_20673" value="5" />其他</label>
											</dd>
										</dl>

										<dl>
											<dt>款式细节SB：</dt>
											<dd>
												<input type="hidden" name="orgLookup.id"
													value="${orgLookup.id}" /> <input type="text"
													class="required" name="orgLookup.orgName" value=""
													suggestFields="orgNum,orgName"
													suggestUrl="demo/database/db_lookupSuggest.html"
													lookupGroup="orgLookup" /> <a class="btnLook"
													href="demo/database/dwzOrgLookup.html"
													lookupGroup="orgLookup">查找带回</a>
											</dd>
										</dl>

										<dl>
											<dt>图案：</dt>
											<dd>
												<input type="hidden" name="orgLookup.id"
													value="${orgLookup.id}" /> <input type="text"
													class="required" name="orgLookup.orgName" value=""
													suggestFields="orgNum,orgName"
													suggestUrl="demo/database/db_lookupSuggest.html"
													lookupGroup="orgLookup" /> <a class="btnLook"
													href="demo/database/dwzOrgLookup.html"
													lookupGroup="orgLookup">查找带回</a>
											</dd>
										</dl>

										<div>
											<table class="list nowrap itemDetail" addButton="添加自定义s属性"
												width="50%">
												<thead>
													<tr>
														<th type="text" name="items[#index#].itemString" size="12"
															fieldClass="required"
															fieldAttrs="{remote:'validate_remote.html', maxlength:10}">属性名</th>
														<th type="text" name="items[#index#].itemString" size="12"
															fieldClass="required"
															fieldAttrs="{remote:'validate_remote.html', maxlength:10}">属性值</th>
														<th type="del" width="60">操作</th>
													</tr>
												</thead>
												<tbody></tbody>
											</table>
										</div>
									</div>


									<div></div>
									<div></div>
								</div>
								<div class="tabsFooter">
									<div class="tabsFooterContent"></div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>