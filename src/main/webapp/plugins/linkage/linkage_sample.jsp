<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<!DOCTYPE script PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/infinite-linkage.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
		$("select.combox").initSelect();
	});
</script>
</head>
<body>
 


<h2 class="contentTitle">三级下拉菜单-默认选中值,如果没有值则隐藏</h2>
<select class="combox"  id="provinceid" name="provinceid"  requiredTitle="true"  childNode="cityid" selectedValue="440000"  retUrl="${pageContext.request.contextPath}/common/loadProvinces">
</select>
<select class="combox"   id="cityid" name="cityid"  requiredTitle="true"  selectedValue="440300"   showNone="false"  parentValue="440000" childNode="areaid" retUrl="${pageContext.request.contextPath}/common/loadCities/{value}">
</select>
<select class="combox"   id="areaid" name="areaid"   requiredTitle="true"  selectedValue="440303"    showNone="false"   parentValue="440300"  retUrl="${pageContext.request.contextPath}/common/loadAreas/{value}">
</select>

<h2 class="contentTitle">三级下拉菜单</h2>
<select class="combox"  id="provinceid1" name="provinceid1"  requiredTitle="true"  childNode="cityid1"   retUrl="${pageContext.request.contextPath}/common/loadProvinces">
</select>
<select class="combox"   id="cityid1" name="cityid1"  requiredTitle="true"   showNone="true" childNode="areaid1" retUrl="${pageContext.request.contextPath}/common/loadCities/{value}">
</select>
<select class="combox"   id="areaid1" name="areaid1"   requiredTitle="true"   showNone="true"   retUrl="${pageContext.request.contextPath}/common/loadAreas/{value}">
</select>

<h2 class="contentTitle">二级下拉菜单</h2>
<select class="combox"  id="provinceid2" name="provinceid2"  requiredTitle="true"  childNode="cityid2"   retUrl="${pageContext.request.contextPath}/common/loadProvinces">
</select>
<select class="combox"   id="cityid2" name="cityid2"  requiredTitle="true"   showNone="true"  retUrl="${pageContext.request.contextPath}/common/loadCities/{value}">
</select>

<h2 class="contentTitle">一级下拉菜单</h2>
<select class="combox"  id="provinceid3" name="provinceid3"  requiredTitle="true"  retUrl="${pageContext.request.contextPath}/common/loadProvinces">
</select>
</body>
</html>


