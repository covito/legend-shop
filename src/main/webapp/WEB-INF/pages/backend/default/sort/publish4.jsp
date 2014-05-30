<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>

<div class="cc-cbox-filter">
	<label for="cc-cbox-filter4">输入品牌</label><input role="textbox"
		autocomplete="off" id="cc-cbox-filter4" style="width: 175px;" class="lebelinput"  >
</div>
<ul role="listbox" class="cc-cbox-cont" id="brandList" name="brandList">
					<c:forEach items="${requestScope.brandList }" var="brand" >
											<li  id="${brand.brandId}" class="cc-cbox-item">${brand.brandName }</li>
						</c:forEach>
</ul>

<script type="text/javascript">
	$(document).ready(function(e) {
		$("li#category4 li.cc-cbox-item").die('click').live('click',function() {
			$this = $(this);
			var preSelected = $("li#category4 li.cc-selected");
			preSelected.removeClass("cc-selected");
			preSelected.removeAttr("aria-selected");
			$this.addClass("cc-selected");
			$this.attr("aria-selected","true");
			$("#selected-category li:nth-child(4)").remove();
			$("#brandId").val($this.attr("id"));
			$("#selected-category").append("<li>  > " + $this.text() + "</li>");
		});
	});
</script>