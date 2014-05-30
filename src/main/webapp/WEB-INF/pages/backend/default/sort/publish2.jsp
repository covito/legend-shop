<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<div class="cc-cbox-filter">
	<label for="cc-cbox-filter2">输入二级商品分类</label><input  id="cc-cbox-filter2"  class="lebelinput"  style="width: 175px;">
</div>
<ul role="listbox" class="cc-cbox-cont"  id="nsortList" name="nsortList">
						<c:forEach items="${requestScope.nSort2List }" var="nsort" >
											<li id="cc-cbox-item${nsort.nsortId}"  sortId="${nsort.nsortId}" class='cc-cbox-item  <c:if test="${fn:length(nsort.subSort)>0}">cc-hasChild-item</c:if>'>
											    <!-- 
											    <i>A</i>
											     -->
													 ${nsort.nsortName }
											</li>
						</c:forEach>
</ul>
<script type="text/javascript">
	$(document).ready(function(e) {
		$("li#category2 li.cc-cbox-item").die('click').live('click',function() {
			$this = $(this);
			var nsortId = $this.attr("sortId");
			$("#nsortId").val(nsortId);
			$("#subsortId").val('');
			$("#brandId").val('');
			var targetUrl = "${pageContext.request.contextPath}/admin/sort/publish3/" +nsortId ;
			var childNode = "category3";
			retrieveNext(targetUrl, childNode);
			var preSelected = $("li#category2 li.cc-selected");
			preSelected.removeClass("cc-selected");
			preSelected.removeAttr("aria-selected");
			$this.addClass("cc-selected");
			$this.attr("aria-selected","true");
			$("#selected-category li:nth-child(4)").remove();
			$("#selected-category li:nth-child(3)").remove();
			$("#selected-category li:nth-child(2)").remove();
			$("#selected-category").append("<li>  > " + $this.text() + "</li>");
		});
	});
	
</script>