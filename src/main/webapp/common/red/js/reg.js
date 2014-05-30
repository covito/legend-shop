  function showbusiness(){
    	var openshop = '${param.openshop}';
    	if(1 == openshop){
    	 	$("#openShop").attr("value", 'business1');
    		$('#businessTable').show();
    	}else{
    	   $("#openShop").attr("value", 'persional');
    		$('#businessTable').hide();
    	}
    }
  
  function changeregTab(){
	   $(".pagetablg ul li").click(function(){
	    	 $(".pagetablg ul li").each(function(i){
	    	 	 $(this).removeClass("onlg");
	    	 });
	    	 if($(this).attr("id") == 'business'){
	    			 $("#openShop").attr("value", 'business');
	    	 		$('#businessTable').show();
	    	 }else{
	    	 		$("#openShop").attr("value", 'personal');
	    	 		$('#businessTable').hide();
	    	 		
	    	 }
	    	 $(this).addClass("onlg");
	  	  });
  }
    

	/*** 检查是否由数字字母和下划线组成 ***/
	String.prototype.isAlpha = function() {
		return (this.replace(/\w/g, "").length == 0);
	}
	
	// propose username by combining first- and lastname
	function checkName() {
		var result = true;
	var nameValue = jQuery("#name").val();
	if(nameValue!=null && nameValue!=''){
	if(nameValue.length >= 4 && nameValue.isAlpha() ){
       //call ajax action
	       $.ajax({
				url: contextPath + "/isUserExist", 
				data: {"userName":nameValue},
				type:'post', 
				async : false, //默认为true 异步   
				error: function(jqXHR, textStatus, errorThrown) {
			 		 //console.log(textStatus, errorThrown);
				},
				success:function(retData){
				 		if('true' == retData){
							result = false;
						  }
				}
				});
	      }
	    }
    return result;
	}
	
	function checkEmail() {
		var result = true;
		var userMailValue = jQuery("#userMail").val();
		if(userMailValue!=null && userMailValue!=''){
			if(isEmail(userMailValue)){
			       //call ajax action
			       $.ajax({
						url: contextPath + "/isEmailExist", 
						data: {"email":userMailValue},
						type:'post', 
						async : false, //默认为true 异步   
						error: function(jqXHR, textStatus, errorThrown) {
					 		 //console.log(textStatus, errorThrown);
						},
						success:function(retData){
						 		if('true' == retData){
									result = false;
								  }
						}
						});
			     }
			    }
		return result;
	}


function isEmail(str){
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	return reg.test(str);
}

 function isOpenShop(obj){
 	if(obj.checked){
 		document.getElementById("shopDetail").style.display="";
 	}else{
 	 	document.getElementById("shopDetail").style.display="none";
 	}
 }
		
   function changeType(obj){
 	if(obj == 1){
 		document.getElementById("trafficPicDiv").style.display="";
 	}else{
 	 	document.getElementById("trafficPicDiv").style.display="none";
 	}
 }	
 
