function isNumber(oNum)
   {
	  if(!oNum) return false;
	  var strP=/^\d+(\.\d+)?$/;
	  if(!strP.test(oNum)) return false;
	  try{
	  if(parseFloat(oNum)!=oNum) return false;
	  }
	  catch(ex)
	  {
	   return false;
	  }
	  return true;
   }
   
   function contains(entry, value){
      for(var i = 0; i <entry.length; i++){
      if(entry[i] == value){
      	return false;
       }
      }
   	return true;
   }
   
	function RemoveArray(array,attachId)
	{
	    for(var i=0,n=0;i<array.length;i++)
	    {
	        if(array[i]!=attachId)
	        {
	            array[n++]=array[i];
	        }
	    }
	    array.length -= 1;
	}
	
/* * productdetail jsp */
	function getProdAttr(){
		var prodattr = "";
		var errMsg = "";
		var attrselect = $(".attrselect");
		for(var i = 0; i< attrselect.size(); i++){
			if(attrselect.get(i).value == ''){
				errMsg = errMsg + " " + attrselect.get(i).getAttribute("dataValue") ;
			}else{
				prodattr = prodattr + attrselect.get(i).getAttribute("dataValue") +":"+attrselect.get(i).value + ";";
			}
			
		}
		if(errMsg != ""){
			prodattr = "error"+errMsg;
		}
		return prodattr;
	}

	String.prototype.startWith=function(str){
	var reg=new RegExp("^"+str);    
	return reg.test(this);       
	} 
	