function save(type, prodId) {
		var temp = getTemplate();
		var data = {
                	"prodId": prodId,
                	"type" :type,
                	"model":temp
          };
          var contextPath =  jQuery("#contextPath").val(); 
          var param = JSON.stringify(data);
       	jQuery.ajax({
				url: contextPath + "/admin/dynamic/savetoprod",
				data: param,
				type:'post', 
				dataType : 'json', 
				contentType: "application/json",
				async : true, //默认为true 异步   
				error: function(jqXHR, textStatus, errorThrown) {
			 		// alert(textStatus, errorThrown);
				},
				success:function(retData){
		         	  if(retData){
		           		if(type != null && type ==1){
		           		 alert("保存商品动态属性成功");
		           		}else{
		           		 alert("保存商品动态参数成功");
		           		}
			           }else{
			        	    alert("保存失败");
				          }
				}
				});
	}

function addTemp(type){
    	var sortId = jQuery("#sortId").val();
    	if(sortId == null || sortId ==''){
    		alert("商品分类不能为空");
    		return;
    	}
    	var temp = getTemplate();
    	var tempName =  jQuery("#tempName").val(); 
    	if(tempName == null || tempName == ''){
    		alert("模版名称不能为空");
    		return;
    	}
	       
     var data = {
                	"tempName": tempName,
                	"type" :type,
                	"sortId":sortId,
                	"model":temp
          };
          var param = JSON.stringify(data);
          var contextPath =  jQuery("#contextPath").val(); 
       	jQuery.ajax({
				url: contextPath + "/admin/dynamic/save",
				data: param,
				type:'post', 
				dataType : 'json', 
				contentType: "application/json",
				async : true, //默认为true 异步   
				error: function(jqXHR, textStatus, errorThrown) {
			 		// alert(textStatus, errorThrown);
				},
				success:function(retData){
  					if(retData > -1){
	        		    //save and then update ui to make sure the new created is the current template
	        	      window.location.href =  contextPath +"/admin/prodspec/query/" + type;
		           }else{
		        	    alert("操作失败,检查名称是否重复");
			          }
				}
				});
	         
    }
    
      function modifyTemp(type){
    	var sortId = jQuery("#sortId").val();
    	if(sortId == null || sortId ==''){
    		alert("商品分类不能为空");
    		return;
    	}
    	var tempId =  jQuery("#tempId").val(); 
        var temp = getTemplate();
    	
    	if(tempId == null || tempId == '' || temp == null || temp == ''){
    		alert("请先加载属性模板");
    		return;
    	}
    	  var data = {
                	"tempId": tempId,
                	"type" :type,
                	"sortId":sortId,
                	"model":temp
          };
          var param = JSON.stringify(data);
           var contextPath =  jQuery("#contextPath").val(); 
       	jQuery.ajax({
				url: contextPath + "/admin/dynamic/update",
				data: param,
				type:'post', 
				dataType : 'json', 
				contentType: "application/json",
				async : true, //默认为true 异步   
				error: function(jqXHR, textStatus, errorThrown) {
			 		// alert(textStatus, errorThrown);
				},
				success:function(result){
				if(result){
				  alert("更新成功");
				}
				
				}
				});
 
    }
 
   
      function applyTemp(tempId){
   	    var table = document.getElementById("target");
   	    table.innerHTML = "";
   	    if(tempId != null || tempId != ''){
		var data = {
                	"tempId": tempId 
          };
          var contextPath =  jQuery("#contextPath").val(); 
       	jQuery.ajax({
				url: contextPath + "/admin/dynamic/loadspec",
				data: data,
				type:'post', 
				dataType : 'json', 
				async : false, //默认为true 异步   
				error: function(jqXHR, textStatus, errorThrown) {
			 		// alert(textStatus, errorThrown);
				},
				success:function(retData){
						if(retData != null){
							jQuery("#sortId").val(retData.sortId);
							jQuery("#tempId").val(retData.id);
							var models =  retData.modelList;
				            for(var i = 0; i < models.length; i++){
				            	addModel(table,models[i]);
				                }
						}
				}
				});
   	    }
   }
   

function getTemplate() {
		var temp = [];
        var target = document.getElementById("target");
        for(var i = 0 ; i<target.childNodes.length; i++){
            var jModel = {id:"",type:"",items:[]};
            var model = target.childNodes[i];
            var type = model.getAttribute("type");
            var jItems = [];
            if(type == "Text"){
            	var jItem = {key:"",value:""};
                for(var item = model.childNodes[0].firstChild; item; item = item.nextSibling){
                          if(item.name == "textInput"){
                            jModel.id = item.value;
                            jItem.key = item.value;
                            }
                         if(item.name == "textValue"){
                	    	jItem.value= item.value;
                    	    }
                }
         
                   jItems.push(jItem);
            }else{
                for(var items = model.childNodes[0].nextSibling; items; items = items.nextSibling){
                   var jItem = {key:"",value:""};
                   for(var item = items.firstChild; item; item = item.nextSibling){
                	    //alert(item.nodeType +" ,nodeValue = " +item.nodeValue +" ,value = " +item.value+" , name = " +item.name);
                	    if(item.name == "textInput"){
                	    	jItem.key= item.value;
                    	    }
                   }
                   jItems.push(jItem);
                  }
                 
                 for(var item2 = model.childNodes[0].firstChild; item2; item2 = item2.nextSibling){
                          if(item2.name == "textInput"){
                            jModel.id = item2.value;
                            }
                	}
                }
            jModel.items = jItems;
            jModel.type = type;
            
            temp.push(jModel);
        }
        return temp;
	}

	function addModel(table,model){
		switch(model.type){
	        case 'Select':addSelect(table,model);
	        break;
	        case 'Text':addText(table,model);
	        break;
	        case 'Radio':addRadio(table,model);
	        break;
	        case 'CheckBox':addCheckBox(table,model);
	        break;
	        default:alert('Other');
      }
	}

	function add(table,type){
        switch(type){
        case 'Select':addSelect(table);
        break;
        case 'Text':addText(table);
        break;
        case 'Radio':addRadio(table);
        break;
        case 'CheckBox':addCheckBox(table);
        break;
        default:alert('Other');
      }
	}

	function addText(table,model){
	   var text;
	   var value;
	   var contextPath = document.getElementById("contextPath").value;	
		if(model){
			text = "参数：<input id='textInput' name ='textInput' type='text' value='" + model.id + "'/>";
			 if(model!=null && model.items !=null){
			 var itemValue = model.items.length > 0 ?  model.items[0].value : "";
			 value = "&nbsp;&nbsp;值：<input id='textValue' name ='textValue' type='text' value='" + itemValue + "'/>";
			 }
		}else{
			text = "参数：<input id='textInput' name ='textInput' type='text'/>";
			value = "&nbsp;&nbsp;值：<input id='textValue' name ='textValue' type='text' />";
		}
        
        //var del = "<input id='textButton' name ='textButton' type='button' value='删除参数框' onclick='del(this.parentNode)'/>";
        var del = "<a href='#' onclick='del(this.parentNode)'><img src='" + contextPath +"/common/default/images/grid_delete.png' title='删除'/></a>";
        var up = "&nbsp;&nbsp;<a href='#' onclick='moveUp(this.parentNode)'><img src='"+ contextPath + "/common/default/images/up.gif' title='上移'/></a>";
        
        
        var down = "&nbsp;<a href='#' onclick='moveDown(this.parentNode)'><img src='" + contextPath + "/common/default/images/down.gif' title='下移'/></a>";
        var e = element("div", {"class": "textOptionType","className": "textOptionType"}, [text,value,del, up, down]);
        table.appendChild(element("div",{"class": "modelType","className": "modelType","type": "Text"},[e]));
	}

	function addSelect(table, model){
	 var text;
	 var contextPath = document.getElementById("contextPath").value;	
	if(model){
		text = "下拉列表：<input id='textInput' name ='textInput' type='text' value='" + model.id + "'/>";
	}else{
		text = "下拉列表：<input id='textInput' name ='textInput' type='text'/>";
	}
        var add = "<a href='#' onclick='addSelectOption(this.parentNode.parentNode)'><img src='" + contextPath + "/common/default/images/grid_add.png'/></a>";
       // var del = "<input id='textButton' name ='textButton' type='button' value='删除选择框' onclick='del(this.parentNode)'/>";
        var del = "<a href='#' onclick='del(this.parentNode)'><img src='" + contextPath + "/common/default/images/grid_delete.png' title='删除'/></a>";
        
        var up = "&nbsp;&nbsp;<a href='#' onclick='moveUp(this.parentNode)'><img src='" + contextPath + "/common/default/images/up.gif' title='上移'/></a>";
        
        var down = "&nbsp;<a href='#' onclick='moveDown(this.parentNode)'><img src='" + contextPath + "/common/default/images/down.gif' title='下移'/></a>";
        
        var e = element("div", {"class": "selectType","className": "selectType"}, [text,add,del, up, down]);
        
        var e1 = element("div",{"class": "modelType","className": "modelType","type": "Select"},[e]);
        
        if(model!=null && model.items !=null){
		for(var i = 0; i< model.items.length; i++){
			e1.appendChild(getSelectOption(model.items[i]));
			}	
		}
        
        table.appendChild(e1);
	   }

    function addSelectOption(obj, item){
    		var text;
    		 var contextPath = document.getElementById("contextPath").value;	
    		if(item){
    		  text = "选项&nbsp;<input id='textInput' name ='textInput' type='text' value='"+ item.value + "'/>";
    		}else{
    		  text = "选项&nbsp;<input id='textInput' name ='textInput' type='text'/>";
    		}
	        
	        var del = "<input id='textButton' name ='textButton' type='image' src='" + contextPath + "/common/default/images/grid_delete.png' title='删除' onclick='del(this)'/>";
	        var e = element("div", {"class": "selectOption","className": "selectOption"}, [text,del]);
	        obj.appendChild(e);
        }
        
        function getSelectOption(item){
    		var text;
    		var contextPath = document.getElementById("contextPath").value;	
    		if(item){
    		  text = "选项&nbsp;<input id='textInput' name ='textInput' type='text' value='"+ item.key + "'/>";
    		}else{
    		  text = "选项&nbsp;<input id='textInput' name ='textInput' type='text'/>";
    		}
	        
	        var del = "<input id='textButton' name ='textButton' type='image' src='" + contextPath + "/common/default/images/grid_delete.png' title='删除'  onclick='del(this)'/>";
	        var e = element("div", {"class": "selectOption","className": "selectOption"}, [text,del]);
	        return e;
        }

	function addCheckBox(table,model){
		 var text;
		 var contextPath = document.getElementById("contextPath").value;	
		if(model){
		  text = "多选框：<input id='textInput' name ='textInput' type='text' value='" + model.id + "'/>";
		}else{
		  text = "多选框：<input id='textInput' name ='textInput' type='text'/>";
		}
         var add = "<a href='#' onclick='addSelectOption(this.parentNode.parentNode)'><img src='" + contextPath + "/common/default/images/grid_add.png'/></a>";
         var del = "<a href='#' onclick='del(this.parentNode)'><img src='" + contextPath + "/common/default/images/grid_delete.png' title='删除'/></a>";
         var up = "&nbsp;&nbsp;<a href='#' onclick='moveUp(this.parentNode)'><img src='" + contextPath + "/common/default/images/up.gif' title='上移'/></a>";
         var down = "&nbsp;<a href='#' onclick='moveDown(this.parentNode)'><img src='" + contextPath + "/common/default/images/down.gif' title='下移'/></a>"; 
         var e = element("div", {"class": "CheckBoxType","className": "CheckBoxType"}, [text,add,del,up,down]);
         var e1 = element("div",{"class": "modelType","className": "modelType","type": "CheckBox"},[e]);
         
	        if(model!=null && model.items !=null){
			for(var i = 0; i< model.items.length; i++){
				e1.appendChild(getSelectOption(model.items[i]));
				}	
			}         
         
        table.appendChild(e1);
	    }

	function addRadio(table,model){
		var text;
		var contextPath = document.getElementById("contextPath").value;	
		if(model){
		  text = "单选框：<input id='textInput' name ='textInput' type='text' value='" + model.id + "'/>";
		}else{
		  text = "单选框：<input id='textInput' name ='textInput' type='text'/>";
		}
        var add = "<a href='#' onclick='addSelectOption(this.parentNode.parentNode)'><img src='" + contextPath + "/common/default/images/grid_add.png'/></a>";
        //var del = "<input id='textButton' name ='textButton' type='button' value='删除单选框' onclick='del(this.parentNode)'/>";
         var del = "<a href='#' onclick='del(this.parentNode)'><img src='" + contextPath +"/common/default/images/grid_delete.png' title='删除'/></a>";  
         var up = "&nbsp;&nbsp;<a href='#' onclick='moveUp(this.parentNode)'><img src='" + contextPath + "/common/default/images/up.gif' title='上移'/></a>";
         var down = "&nbsp;<a href='#' onclick='moveDown(this.parentNode)'><img src='" + contextPath + "/common/default/images/down.gif' title='下移'/></a>";
         
        var e = element("div", {"class": "radioType","className": "radioType"}, [text,add,del,up,down]);
        var e1 = element("div",{"class": "modelType","className": "modelType","type": "Radio"},[e]);
        
        if(model!=null && model.items !=null){
		for(var i = 0; i< model.items.length; i++){
			e1.appendChild(getSelectOption(model.items[i]));
			}	
		}
        
        table.appendChild(e1);
	 }

    function del(obj){
        var child = obj.parentNode;
        child.parentNode.removeChild(child);
      }
      
    function addRowRecord(attributeType){
        var table = document.getElementById("target");
        add(table,attributeType);
    }
      

    function addRow(){
        var table = document.getElementById("target");
        var modelType = document.getElementById("modelType");
        add(table,modelType.options[modelType.selectedIndex].value);
    }
    
  function moveUp(e) {
     var obj = e.parentNode;
     var toPos = obj.previousSibling;
     while (toPos && toPos.nodeType != 1){
       toPos = toPos.previousSibling;
      }
     // alert(toPos.tagName +" = "+ obj.tagName+ "  "+ toPos.id +" = " + obj.id);
     if (toPos && toPos.tagName == obj.tagName && toPos.id == obj.id) {
      obj.parentNode.removeChild(obj);
      toPos.parentNode.insertBefore(obj, toPos);
     }else{
       return;
     }
    }
    
     function redrawOrder(){
        order = 1;
        var rules = document.getElementsByName("rules");
        if(rules!=null){
            for(var i=0;i<rules.length;i++){
               rules[i].firstChild.nodeValue = order +". Routing Rule Type ";
               if(order % 2 != 0){
               rules[i].setAttribute("class","rules");
               rules[i].setAttribute("className","rules");
           }else{
               rules[i].setAttribute("class","ruleseven");
               rules[i].setAttribute("className","ruleseven");
           }
           order ++;
            }
        }
      }
    
    function moveDown(e) {
      var obj = e.parentNode; 
      var toPos = obj.nextSibling;
      while (toPos && toPos.nodeType != 1){
           toPos = toPos.nextSibling;
      }
      //alert(addroutingRule.innerHTML);
      //alert(toPos.innerHTML);
      if (toPos && toPos.tagName == obj.tagName && toPos.id == obj.id) {
           toPos.parentNode.removeChild(toPos);
           obj.parentNode.insertBefore(toPos, obj);
     }else{
           return;
     }
     //redrawOrder();
    }
    

	function element(tagName, attributes, children) {
		var e = document.createElement(tagName);
		for ( var a in attributes) {
			e.setAttribute(a, attributes[a]);
		}
		if (children)
			for ( var i = 0; i < children.length; i++)
				if (typeof (children[i]) == "string") {
					e.innerHTML += children[i];
				} else {
					e.appendChild(children[i]);
				}
		return e;
	}

    function isElement(obj){
        if(window.DOMParser){//Firefox
            if(obj instanceof Element){
                return true;
            }else{
                return false;
            }
        }else{
            return true;//IE
        }
     }