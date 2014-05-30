<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
	<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
<div class="cc-cbox-filter">
	<label for="cc-cbox-filter282">输入名称/拼音首字母</label><input role="textbox"
		autocomplete="off" id="cc-cbox-filter282" style="width: 175px;">
</div>
<ul role="listbox" class="cc-cbox-cont">
	<li role="option" id="cc-cbox-item1"
		class="cc-cbox-item cc-hasChild-item"><i>c</i> <a
		target="<ls:url address='/admin/product/category3'/>"  rel="category3">衬衫</a></li>
	<li role="option" id="cc-cbox-item2"
		class="cc-cbox-item cc-hasChild-item"><i>f</i>
		<a target="<ls:url address='/admin/product/category3'/>"  rel="category3">风衣</a>
	</li>
	<li role="option" id="cc-cbox-item3"
		class="cc-cbox-item cc-hasChild-item"><i>g</i> <a
		target="<ls:url address='/admin/product/category3'/>"  rel="category3">工装制服</a></li>
	<li role="option" id="cc-cbox-item4"
		class="cc-cbox-item cc-hasChild-item"><i>j</i>夹克</li>
	<li role="option" id="cc-cbox-item5"
		class="cc-cbox-item cc-hasChild-item"><i>m</i>马甲</li>
	<li role="option" id="cc-cbox-item6"
		class="cc-cbox-item cc-hasChild-item">棉衣</li>
	<li role="option" id="cc-cbox-item7"
		class="cc-cbox-item cc-hasChild-item">毛衣</li>
	<li role="option" id="cc-cbox-item8"
		class="cc-cbox-item cc-hasChild-item">民族服装</li>
	<li role="option" id="cc-cbox-item9"
		class="cc-cbox-item cc-hasChild-item"><i>n</i>牛仔裤</li>
	<li role="option" id="cc-cbox-item10"
		class="cc-cbox-item cc-hasChild-item"><i>p</i>Polo衫</li>
	<li role="option" id="cc-cbox-item11"
		class="cc-cbox-item cc-hasChild-item">皮裤</li>
	<li role="option" id="cc-cbox-item12"
		class="cc-cbox-item cc-hasChild-item">皮衣</li>
	<li role="option" id="cc-cbox-item13"
		class="cc-cbox-item cc-hasChild-item"><i>t</i>T恤</li>
	<li role="option" id="cc-cbox-item14"
		class="cc-cbox-item cc-hasChild-item"><i>w</i>卫衣</li>
	<li role="option" id="cc-cbox-item15"
		class="cc-cbox-item cc-hasChild-item"><i>x</i>休闲裤</li>
	<li role="option" id="cc-cbox-item16"
		class="cc-cbox-item cc-hasChild-item">西裤</li>
	<li role="option" id="cc-cbox-item17"
		class="cc-cbox-item cc-hasChild-item">西服</li>
	<li role="option" id="cc-cbox-item18"
		class="cc-cbox-item cc-hasChild-item">西服套装</li>
	<li role="option" id="cc-cbox-item19"
		class="cc-cbox-item cc-hasChild-item">休闲套装</li>
	<li role="option" id="cc-cbox-item20"
		class="cc-cbox-item cc-hasChild-item"><i>y</i>羽绒服</li>
	<li role="option" id="cc-cbox-item21" class="cc-cbox-item"><i>z</i>针织衫</li>
</ul>


<script type="text/javascript">
	$(document).ready(function(e) {
		$("li#category2 li.cc-cbox-item").click(function() {
			$this2 = $(this).find(">a");
			var targetUrl = $this2.attr("target");
			var childNode = $this2.attr("rel");
			retrieveNext(targetUrl, childNode);
		
			var preSelected = $("li#category2 li.cc-selected");
			preSelected.removeClass("cc-selected");
			preSelected.removeAttr("aria-selected");
			$(this).addClass("cc-selected");
			$(this).attr("aria-selected","true");
			
			
			$("#selected-category li:nth-child(3)").remove();
			$("#selected-category li:nth-child(2)").remove();
			
			
			$("#selected-category").append("<li>  > " + $(this).text() + "</li>");
		});
	});
	
</script>