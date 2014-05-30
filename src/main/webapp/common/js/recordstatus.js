function initStatus(itemId, itemName, status, url, target,path){
  	var desc;
  	var toStatus;
  	   if(status == 1){
  	   		toStatus  = 0;
  	   		desc = itemName +' 下线?';
  	   }else{
  	       toStatus = 1;
  	       desc = itemName + ' 上线?';
  	   }

   	 art.dialog({
	    content: desc,
	    lock:false,
	    ok: function () {
		       jQuery.ajax({
					url: url + itemId + "/" + toStatus, 
					type:'get', 
					async : false, //默认为true 异步   
					dataType : 'json', 
					error:function(data){
					alert(data);   
					},   
					success:function(data){
						$target = $(target);
						if(data == 1){
							$target.attr("src",path + "/common/default/images/blue_down.png");
						}else if(data == 0){
							$target.attr("src",path + "/common/default/images/yellow_up.png");
						}
						$target.attr("status",data);
					}   
					});  
		    
	        return true;
	    },
	    cancelVal: '关闭',
	    cancel: true //为true等价于function(){}
	});
	
}
