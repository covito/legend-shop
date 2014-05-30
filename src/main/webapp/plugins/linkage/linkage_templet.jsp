<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<!DOCTYPE script PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>

<script type="text/javascript">
  var templet = '${templet}';
    $(document).ready(function() {
		$("#templet").onchange(function{
			
		});
		initSelect();
	});
	
	//初始化方法
		  initSelect: function(){
				//1. init templet
				//console.debug("init");
				//2. init frontend language
				
				//3. init frontend style
				
				//4. init backend language
				
				//5. init backend style
		};
		
		addOptions: function(target, requiredTitle, entity){
			if("true" == requiredTitle){
				target.append("<option value=''>-- 请选择 --</option>" );  
			}
		 	target.append("<option value='" + entity.key + "'>" + entity.value + "</option>" );  
		}
</script>
</head>
<body>
 


<h2 class="contentTitle">模版联动，选择模版，然后自动选择后面的语言language和风格style</h2>
模版：
<select class="combox"  id="templet" name="templet"  requiredTitle="true"  childNode="cityid" selectedValue="440000"  retUrl="${pageContext.request.contextPath}/common/loadProvinces">
</select>
语言：
<select class="combox"   id="language" name="language"  requiredTitle="true"  selectedValue="440300"   showNone="false"  parentValue="440000" childNode="areaid" retUrl="${pageContext.request.contextPath}/common/loadCities/{value}">
</select>
风格：
<select class="combox"   id="style" name="style"   requiredTitle="true"  selectedValue="440303"    showNone="false"   parentValue="440300"  retUrl="${pageContext.request.contextPath}/common/loadAreas/{value}">
</select>

</body>
</html>


