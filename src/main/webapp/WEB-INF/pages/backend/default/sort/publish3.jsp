<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>

<div class="cc-cbox-filter">
	<label for="cc-cbox-filter3">输入三级商品分类</label><input role="textbox"
		autocomplete="off" id="cc-cbox-filter3" style="width: 175px;" class="lebelinput"  >
</div>
<ul role="listbox" class="cc-cbox-cont" id="subSortList" name="subSortList">
					<c:forEach items="${requestScope.nSort3List }" var="nsort3" >
											<li id="cc-cbox-item${nsort3.nsortId}"  sortId="${nsort3.nsortId}"  class="cc-cbox-item" >
											<!-- 
											<i>c</i> 
											 -->
											${nsort3.nsortName }</li>
						</c:forEach>
</ul>

<script type="text/javascript">
	$(document).ready(function(e) {
		$("li#category3 li.cc-cbox-item").die('click').live('click',function() {
			$this = $(this);
			var subsortId = $this.attr("sortId");
			$("#subsortId").val(subsortId);
			$("#brandId").val('');
			var targetUrl = "${pageContext.request.contextPath}/admin/brand/loadSortBrands/" +subsortId ;
			var childNode = "category4";
			retrieveNext(targetUrl, childNode);
			var preSelected = $("li#category3 li.cc-selected");
			preSelected.removeClass("cc-selected");
			preSelected.removeAttr("aria-selected");
			$this.addClass("cc-selected");
			$this.attr("aria-selected","true");
			$("#selected-category li:nth-child(4)").remove();
			$("#selected-category li:nth-child(3)").remove();
			$("#selected-category").append("<li>  > " + $this.text() + "</li>");
		});
	});
</script>