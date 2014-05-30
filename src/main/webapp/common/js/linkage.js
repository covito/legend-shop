var contextPath = document.getElementById("contextPath").value;	

function ajaxAddOptions(url, child,required){
		    child.empty();  
		    if(required){
		      child.append("<option>-- 请选择 --</option>" );  
		    }
		     $.ajax({
					url:url, 
					type:'post', 
					async : false, //默认为true 异步   
					dataType : 'json', 
					error:function(data){
						alert(data);   
					},   
					success:function(json){
				        $(json).each(function(i){
				            var x = json[i];  
				            child.append("<option value='" + x.key + "'>" + x.value + "</option>" );  
				        })  
					}   
					});  
		}  
		
		function ajaxClearOptions(child,hidden){
		  child.empty();
		   child.append("<option>-- 请选择 --</option>" );  
		}
	
		 function initSelect(provinceid,cityid,areaid, provinceidValue,cityidValue,areasidValue){
			 	ajaxAddOptions(contextPath + "/common/loadProvinces", provinceid,true);
			 	
			 	if(provinceidValue){
			 		//provinceid.val(provinceidValue);
			 		//provinceid.attr("value", provinceidValue);
			 		setSelectVal(provinceid, provinceidValue);
			 	}
			 	if(cityidValue){
			 	 	ajaxAddOptions(contextPath + "/common/loadCities/"+provinceidValue,cityid,true);
			 	 	//cityid.attr("value", cityidValue);
			 	 	setSelectVal(cityid, cityidValue);
			 	 	cityid.show();
			 	}else{
			 		cityid.hide();
			 	}
				if(areasidValue){
					 ajaxAddOptions(contextPath + "/common/loadAreas/"+cityidValue, areaid);
					setSelectVal(areaid, areasidValue);
					areaid.show();
				}else{
					areaid.hide();
				}
		}
		 
	function	 changeProvince(cityid,areaid,value){
			 ajaxAddOptions(contextPath + "/common/loadCities/"+value, cityid,true);
			 if(cityid.find("option").length > 1){
				 cityid.show();
			 }else{
				 cityid.hide();
			 }
			 ajaxClearOptions(areaid);
			  areaid.hide();
		 }
	
	function changeCities(areaid,value){
		 ajaxAddOptions(contextPath + "/common/loadAreas/"+value, areaid);
	 	 if(areaid.find("option").length > 0){
			 areaid.show();
		 }else{
			 areaid.hide();
		 }
	}
	
	function setSelectVal(sel, val)
	{
	    if($.browser.msie && $.browser.version=="6.0") {
	        setTimeout(function(){
	            sel.val(val);
	        },1);
	    }else {
	            sel.val(val);
	    }
	}