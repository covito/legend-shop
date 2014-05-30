/**
 * LegendShop linkage plugin
 */

(function($){
	//查找下一个对象
	var findNext = function( selector ){
	    var set = $(selector);
	    return set.eq( set.index( this) + 1 )
	};
	
	//清理内容
	var  ajaxClearOptions = function(target, requiredTitle){
		   target.empty();
		  // target.show();
		   if(requiredTitle != null || requiredTitle != ''){
				if("true" == requiredTitle){
					target.append("<option value=''>-- 请选择 --</option>" );  
				}else{
					target.append("<option value=''>" + requiredTitle + "</option>" );  
				}
		   }

			while(target.attr("childNode")){
				target = $("#"+target.attr("childNode"));
				ajaxClearOptions(target, target.attr("requiredTitle"));
			}
		};
		
		//隐藏
		var  hidenSelect = function(target){
			var showNone = target.attr("showNone")  || "";
			if("true" != showNone){
				target.hide();
			}
			while(target.attr("childNode")){
				target = $("#"+target.attr("childNode"));
				hidenSelect(target);
			}
		};
		
		//设值
		var setSelectVal = function(target, val)
		{
		    if($.browser.msie && $.browser.version=="6.0") {
		        setTimeout(function(){
		        	target.val(val);
		        },1);
		    }else {
		    	target.val(val);
		    }
		};
	
	$.extend($.fn, {
		ajaxAddOptions: function (url, requiredTitle, selectedValue){
		    var _this = this;
		    ajaxClearOptions(_this, requiredTitle);
		    if(url.indexOf("{value}") > 0 && !selectedValue){ //第一级菜单不需要传值，对于第二第三级菜单如果没有选择值则不需要从数据库load值
		    	return;
		    }
		    var retUrl = url.replace("{value}", selectedValue);
		     $.ajax({
					url: retUrl, 
					type:'post', 
					async : false, //默认为true 异步   
					dataType : 'json', 
					error:function(data){
						//alert(JSON.stringify(data));   
					},   
					success:function(json){
				        $(json).each(function(i){
				            var x = json[i];  
				            _this.append("<option value='" + x.key + "'>" + x.value + "</option>" );  
				        })  
					}   
					});  
		},
		
		//初始化方法
		  initSelect: function(){
				return this.each(function(i){
					var $this = $(this).removeClass("combox");
					var name = $this.attr("name");
					var value= $this.val();
					var childNode = $this.attr("childNode");
					var retUrl = $this.attr("retUrl") || "";
					var cid = $this.attr("id") || Math.round(Math.random()*10000000);
					var selectedValue = $this.attr("selectedValue")  || "";
					var parentValue = $this.attr("parentValue")  || "";
					var requiredTitle = $this.attr("requiredTitle");
					$this.ajaxAddOptions(retUrl, requiredTitle, parentValue);
				 	if(selectedValue !=null && selectedValue != ''){
				 		//set selected value
				 		setSelectVal($this, selectedValue);
				 	}
					if (childNode && retUrl) {
						function _onchange(event){
							var $childNode = $("#"+childNode);
							if ($childNode.size() == 0) return false;
							$childNode.ajaxAddOptions($childNode.attr("retUrl"), $childNode.attr("requiredTitle"), $this.children('option:selected').val());
							 if($childNode.find("option").length > 1){
								 $childNode.show();
							 }else{
								 hidenSelect($childNode);
							 }
						}
						$this.unbind("change", _onchange).bind("change", _onchange);
					}
				});
			  
		}
	});
})(jQuery);
