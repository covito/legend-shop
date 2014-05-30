function onEnter(e){
			var   ev   =   window.event||   e; 
			if(ev.keyCode == 13){
				loadDataByName();
			}
		}  
        
        function toRight(leftObj,rightObj){
             for(var i=0;i<leftObj.options.length;i++){
 				if(leftObj.options[i].selected){
 				   var e = leftObj.options[i];
 				   //是否存在右边框
 				   var len = 0 ;
 				   try{
 				       len = rightObj.options.length ;
 				   }catch(e){}
 				   
 				   var bool = false ;
 				   for(var j=0 ;j<len;j++){
 				       var temp = rightObj.options[j] ;
 				       if(e.text==temp.text&&e.value==temp.value){
 				          //存在相关数据则返回
 				          bool = true ;
 				       }
 				   }
 				   leftObj.remove(i);
 				   i=i-1;
 				   if(bool) continue ;
 				   
 				   rightObj.options.add(new Option(e.text, e.value));
 				   
 				}
 			}
        }
        
        function toLeft(leftObj,rightObj){
            for(var i=0;i<leftObj.options.length;i++){
                if(leftObj.options[i].selected){
 					var e = leftObj.options[i];
 					
 					//是否存在右边框
 				    var len = 0 ;
 				    try{
 				       len = rightObj.options.length ;
 				    }catch(e){}
 				   
 				    var bool = false ;
 				    for(var j=0 ;j<len;j++){
 				       var temp = rightObj.options[j] ;
 				       if(e.text==temp.text&&e.value==temp.value){
 				          //存在相关数据则返回
 				          bool = true ;
 				       }
 				    }
 				    leftObj.remove(i);
 					i=i-1;
 					if(bool){
 					    continue ;
 					}
 					
 					rightObj.options.add(new Option(e.text, e.value));
 			    }
 	       }
            //toRight(rightObj,leftObj) ;
        }
        function toRightAll(leftObj,rightObj){
             for(var i=0;i<leftObj.options.length;i++){
 				var e = leftObj.options[i];
 				
 				//是否存在右边框
 			    var len = 0 ;
 			    try{
 			       len = rightObj.options.length ;
 			    }catch(e){}
 			   
 			    var bool = false ;
 			    for(var j=0 ;j<len;j++){
 			       var temp = rightObj.options[j] ;
 			       if(e.text==temp.text&&e.value==temp.value){
 			          //存在相关数据则返回
 			          bool = true ;
 			       }
 			    }
 				if(bool) continue ;
 				
 				rightObj.options.add(new Option(e.text, e.value));
 				leftObj.remove(i);
 				i=i-1;
 			}
        }
        function toLeftAll(leftObj,rightObj){
              for(var i=0;i<leftObj.options.length;i++){
 				var e = leftObj.options[i];
 				
 				//是否存在右边框
 			    var len = 0 ;
 			    try{
 			       len = rightObj.options.length ;
 			    }catch(e){}
 			   
 			    var bool = false ;
 			    for(var j=0 ;j<len;j++){
 			       var temp = rightObj.options[j] ;
 			       if(e.text==temp.text&&e.value==temp.value){
 			          //存在相关数据则返回
 			          bool = true ;
 			       }
 			    }
 				leftObj.remove(i);
 				i=i-1;
 				if(bool){
 				    continue ;
 				}
 				
 				rightObj.options.add(new Option(e.text, e.value));
 			}
            //toRightAll(rightObj,leftObj) ;
        }