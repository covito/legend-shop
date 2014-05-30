/*
@Author：lixiaohong
**攥取页面form或object中参数，返回可以有两种形式<Map>和<Xml>
**Map格式为[ [name1,value1],[name2,value2].....[namen,valuen] ]
**Xml格式为<request><param name='name1' value='value1'/>...<param name='namen' value='valuen'/></request>
**Example:
**var form = new ScracthForm() ;
**form.setIgnoreNull(false) ;//表示获取页面表单数据时，不忽略空对象，默认为忽略空对象
**var retMap = form.getMap(form_obj_id) ;
**var retXml = form.getXml(form_obj_id) ;
*/
var _debug = false ;
var _error = false ;

var scratch = new ScracthParam() ;
function debug(e){
   if(_debug){
      alert("[DEBUG] "+e) ;
   }
}

function error(e){
   if(_error){
      alert("[ERROR] "+e) ;
   }
}

function ScracthParam(){
    this.ignoreNull = false ;
    this.Map = [] ;
    
    this.setIgnoreNull = function(flag){
        this.ignoreNull = flag ;
    }
    
    this.putTextMap = function(obj){
        var _name = obj.name ;
        var _value = obj.value ;
        if(!this.ignoreNull){//do not ignore
            var temp = [obj.name,obj.value];
	    	this.Map.push(temp) ;
        }else{
           if(_value!=''&&_value!=null){
	            var temp = [obj.name,obj.value];
	    		this.Map.push(temp) ;
           }
        } 
    }
    this.putRadioMap = function(obj){
    	if(obj.checked==true){
           var temp = [obj.name,obj.value];
    	   this.Map.push(temp) ;
        }
    }
    this.putSelectMap = function(obj){
        var _value ;
        try{
            _value = obj.options[obj.selectedIndex].value;
        }catch(e){
           error("Method:putSelectMap  Object Name: "+obj.name+"  Message:"+e);
        }
    	var temp = [obj.name,_value];
    	this.Map.push(temp) ;
    }
    
    this.putMultiSelectMap = function(obj){
        var _value  ;
        try{
           for(var i=0 ;i<obj.options.length ;i=i+1){
              _value = obj.options[i].value;
              var temp = [obj.name,_value];
    	      this.Map.push(temp) ;
           }
        }catch(e){
           error("Method:putMultiSelectMap "+e) ;
        }
    	
    }
    
    this.putCheckBoxMap = function(obj){
        if(obj.checked==true){
           var temp = [obj.name,obj.value];
    	   this.Map.push(temp) ;
        }
    }
    this.putHiddenMap = function(obj){
    	var _name = obj.name ;
        var _value = obj.value ;
        if(!this.ignoreNull){//do not ignore
            var temp = [obj.name,obj.value];
	    	this.Map.push(temp) ;
        }else{
           if(_value!=''&&_value!=null){
	            var temp = [obj.name,obj.value];
	    		this.Map.push(temp) ;
           }
        } 
    }
    this.getMap = function(formObj,addtion,excludeObj){
        var inputTagArray = formObj.getElementsByTagName("input") ;
        if(inputTagArray.length){
             for(i=0 ;i<inputTagArray.length;i++){
                 var _obj = inputTagArray[i] ;
                 if(excludeObj){
                    var _name = _obj.name ;
                    if(_name == excludeObj){
                       continue ;
                    }
                 }
                 if((_obj.type).toLowerCase()=='text'){
                      this.putTextMap(_obj);
                 }else if((_obj.type).toLowerCase()=='radio'){
                      this.putRadioMap(_obj);
                 }else if((_obj.type).toLowerCase()=='hidden'){
                      this.putHiddenMap(_obj);
                 }else if((_obj.type).toLowerCase()=='checkbox'){
                      this.putCheckBoxMap(_obj);
                 }else if((_obj.type).toLowerCase()=='password'){
                      this.putTextMap(_obj);
                 }
             }
        }
        
        var selectTagArray = formObj.getElementsByTagName("select") ;
        if(selectTagArray.length){
             for(i=0 ;i<selectTagArray.length;i++){
                 var _obj = selectTagArray[i] ;
                 if(excludeObj){
                    var _name = _obj.name ;
                    if(_name == excludeObj){
                       continue ;
                    }
                 }
                 if(addtion){
                    if(_obj.id == addtion){
                        this.putMultiSelectMap(_obj);
                    }else{
                        this.putSelectMap(_obj); 
                    }
                 }else{
                        this.putSelectMap(_obj);
                 }
             }
        }
        
        var textareaTagArray = formObj.getElementsByTagName("textarea") ;
        if(textareaTagArray.length){
             for(i=0 ;i<textareaTagArray.length;i++){
                 var _obj = textareaTagArray[i] ;
                 if(excludeObj){
                    var _name = _obj.name ;
                    if(_name == excludeObj){
                       continue ;
                    }
                 }
                 this.putTextMap(_obj);
             }
        }
        
        return this.Map ;
    }
    
    this.getXml = function(formObj,addtion,excludeObj){
         var _map = this.getMap(formObj,addtion,excludeObj) ;
         var _xmlDoc = new ActiveXObject("MSXML2.DOMDocument.3.0") ;
         var requestRoot = _xmlDoc.createElement('request') ;
         _xmlDoc.appendChild( requestRoot ) ;
         try{
            for(i=0 ;i<_map.length;i++){
                var paramNode = _xmlDoc.createElement('param') ;
                requestRoot.appendChild(paramNode) ;
	            var _obj   = _map[i] ;
	            var _name  = _obj[0] ;
	            var _value = _obj[1] ;
	            paramNode.setAttribute('name',_name) ;
	            paramNode.setAttribute('value',_value) ;
	        }
         }catch(e){
            error("Method:getXml "+e) ;
         }
         return _xmlDoc.xml ;
    }
    
    this.mapToXml = function(_map){
        var _xmlDoc = new ActiveXObject("MSXML2.DOMDocument.3.0") ;
         var requestRoot = _xmlDoc.createElement('request') ;
         _xmlDoc.appendChild( requestRoot ) ;
         try{
            for(i=0 ;i<_map.length;i++){
                var paramNode = _xmlDoc.createElement('param') ;
                requestRoot.appendChild(paramNode) ;
	            var _obj   = _map[i] ;
	            var _name  = _obj[0] ;
	            var _value = _obj[1] ;
	            paramNode.setAttribute('name',_name) ;
	            paramNode.setAttribute('value',_value) ;
	        }
         }catch(e){
            error("Method:getXml "+e) ;
         }
         return _xmlDoc.xml ;
    }
    
    this.clear = function(){
       this.Map = [] ;
    }
}

String.prototype.replaceAll = stringReplaceAll;

//Object.prototype.setValue = setObjectValue ;
//function setObjectValue( value ){
   //alert(this.id + value) ;
//}

function  stringReplaceAll(AFindText,ARepText){
  raRegExp = new RegExp(AFindText,"g");
  return this.replace(raRegExp,ARepText)
}
