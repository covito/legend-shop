//初始化方法 
	  initSelect = function(){
					//1. init templet
					var frontEndTempletNode = $("#frontEndTemplet");
					$.each( templetEntity.frontEndTempletList, function(i, content){
						addOptions(frontEndTempletNode, true,content.key,content.value);
						if('selected' == content.status){
							setSelectVal(frontEndTempletNode, content.key);
						}
					});
					
					var backEndTempletNode = $("#backEndTemplet");
					$.each( templetEntity.backEndTempletList, function(i, content){
						addOptions(backEndTempletNode, true,content.key,content.value);
						if('selected' == content.status){
							setSelectVal(backEndTempletNode, content.key);
						}
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
		
		//设值
		 setSelectVal = function(target, val)
		{
		    if($.browser.msie && $.browser.version=="6.0") {
		        setTimeout(function(){
		        	target.val(val);
		        },1);
		    }else {
		    	target.val(val);
		    }
		};
		
		initLanguageAndStyle = function(templet, languageId, styleId){
			//1. init frontend language
			var languageNode = $("#" + languageId);
			if(templet.languages != null && templet.languages.length > 0){
				languageNode.empty();
				$.each(templet.languages, function(i, content){
					addOptions(languageNode, true,content.key,content.value);
					if('selected' == content.status){
						setSelectVal(languageNode, content.key);
					}
				});
				languageNode.parent().show();
			}else{
				languageNode.empty();
				languageNode.parent().hide();
			}
			//2. init frontend style
			var styleNode = $("#" + styleId);
			if(templet.styles != null && templet.styles.length > 0){
				styleNode.empty();
				$.each(templet.styles, function(i, content){
					addOptions(styleNode, true,content.key,content.value);
					if('selected' == content.status){
						setSelectVal(styleNode, content.key);
					}
				});
				styleNode.parent().show();
			}else{
				styleNode.empty();
				styleNode.parent().hide();
			}
	}