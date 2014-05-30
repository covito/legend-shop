function checkRandNum (){
	var inputVal = document.getElementById('randNum');
	//如果找不到对象则表示不用验证
	if(inputVal == null){
		return true;
	}
	
	if(inputVal.value==null||inputVal.value==''){
		 	alert(document.getElementById("cannonull").value) ; 
		 	inputVal.focus() ;
		    return false; //验证失败
		 }
	 if(inputVal.value.length!=4){
	 	alert(document.getElementById("charactors4").value) ;
	 	inputVal.focus() ;
		return false; //验证失败
	 }

	 return true;
}

function validateRandNum (contextPath){
	var checkResult = checkRandNum();
	if(!checkResult){
		 //验证失败，返回
		return checkResult;
	}
	var randNum = document.getElementById("randNum").value;
	var result = true;
		var data = {
            	"randNum": randNum
            };
	    jQuery.ajax({
			url: contextPath + "/validateRandImg", 
			type:'post', 
			data:data,
			async : false, //默认为true 异步   
		    dataType : 'json', 
			error: function(jqXHR, textStatus, errorThrown) {
			},
			success:function(retData){
				 if(!retData){
					 	result = retData;
					 	alert(document.getElementById("errorImage").value) ;
					 	document.getElementById("randNum").value="";
					 	document.getElementById('randNum').focus() ;
					 	changeRandImg(contextPath);
				 }
			}
			});	 
	 
	 return result;
}

    function changeRandImg(contextPath){
        var obj = document.getElementById("randImage") ;
        obj.src = contextPath + "/captcha.svl?d=" + new Date();
     }
     

    
    