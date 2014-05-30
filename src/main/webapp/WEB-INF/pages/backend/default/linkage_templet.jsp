<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<!DOCTYPE script PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>

<script type="text/javascript">
  var templetEntity = jQuery.parseJSON( '${templetEntity}');
    $(document).ready(function() {
		$("#frontEndTemplet").change(function(){
			initLanguageAndStyle(templetEntity.frontEndTempletMap[$(this).children('option:selected').val()], "frontEndLanguage", "frontEndStyle");
		});
		$("#backEndTemplet").change(function(){
		  initLanguageAndStyle(templetEntity.backEndTempletMap[$(this).children('option:selected').val()],  "backEndLanguage", "backEndStyle");
		});
		initSelect();
	});
	
	//初始化方法
	  initSelect = function(){
				//1. init templet
				var frontEndTempletNode = $("#frontEndTemplet");
				$.each( templetEntity.frontEndTempletList, function(i, content){
					addOptions(frontEndTempletNode, true,content.key,content.value);
				});
				
				var backEndTempletNode = $("#backEndTemplet");
				$.each( templetEntity.backEndTempletList, function(i, content){
					addOptions(backEndTempletNode, true,content.key,content.value);
				});
				
				//根据当前选中值来设置下级
				 initLanguageAndStyle( templetEntity.frontEndTempletMap[frontEndTempletNode.children('option:selected').val()], "frontEndLanguage", "frontEndStyle");
				  initLanguageAndStyle( templetEntity.backEndTempletMap[backEndTempletNode.children('option:selected').val()],  "backEndLanguage", "backEndStyle");
				
		};
		
		addOptions =  function(target, requiredTitle, key, value){
			if("true" == requiredTitle){
				target.append("<option value=''>-- 请选择 --</option>" );  
			}
		 	target.append("<option value='" + key + "'>" + value + "</option>" );  
		};
		
		initLanguageAndStyle = function(templet, languageId, styleId){
				//1. init frontend language
				var languageNode = $("#" + languageId);
				if(templet.languages != null && templet.languages.length > 0){
					$.each(templet.languages, function(i, content){
						addOptions(languageNode, true,content.key,content.value);
					});
					languageNode.show();
				}else{
					languageNode.empty();
					languageNode.hide();
				}
				//2. init frontend style
				var styleNode = $("#" + styleId);
				if(templet.styles != null && templet.styles.length > 0){
					$.each(templet.styles, function(i, content){
						addOptions(styleNode, true,content.key,content.value);
					});
					styleNode.show();
				}else{
					styleNode.empty();
					styleNode.hide();
				}
		}
</script>
</head>
<body>
 
<h2 class="contentTitle">模版联动，选择模版，然后自动选择后面的语言language和风格style</h2>
前台模版：
<select id="frontEndTemplet" name="frontEndTemplet" ></select>
语言：
<select  id="frontEndLanguage" name="frontEndLanguage" ></select>
风格：
<select  id="frontEndStyle" name="frontEndStyle"  ></select>
<br></br>
后台模版：
<select id="backEndTemplet" name="backEndTemplet" ></select>
语言：
<select  id="backEndLanguage" name="backEndLanguage" ></select>
风格：
<select  id="backEndStyle" name="backEndStyle" ></select>


</body>
</html>


