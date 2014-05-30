<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<link rel="stylesheet" type="text/css" media='screen' href="<ls:templateResource item='/common/default/css/prodAttribute.css'/> " />
<table width="100%" id="prodAttributes">
<c:forEach items="${requestScope.list}" var="model" varStatus="pos">
  <tr>
    <td width="80px">${model.id}</td>
    <td>
    <ul id="modelId${pos.index}">
    <input type="hidden" autocomplete="off" id="prodAttr${pos.index}" name="prodAttr${pos.index}" dataValue="${model.id}" class="attrselect" value="">
    <c:forEach items="${model.items}" var="item" varStatus="status">
    	<li dataValue="${item.key}"><a href="#">${item.key}</a></li>
    </c:forEach>
    </ul>
    </td>
  </tr>
</c:forEach>
</table>
<script language="javascript" type="text/javascript">
function loadprodAttribute(){
	var dataArray = jQuery.parseJSON( '${attribute}');
    if(dataArray!="undefined" || dataArray != null){
        for(var i in dataArray){
                     var data = dataArray[i] ;
                     var modelId = "modelId"+i;
                     if("CheckBox" == data.type){
                      new Select(modelId,{
						Radio:false,
						Max:6,
						Current:i,
					OnClick:function(selected,current){
							document.getElementById("prodAttr" + current).value = selected.join(",");
						}
					});
				      }else{
				    	new Select(modelId,{
							Radio :true	,	//是否为单选,默认为true,如果设置为true,Default,Max选项将失效
							Current:i,
							OnClick:function(selected,current){
								document.getElementById("prodAttr" + current).value = selected.join(",")
							}
						});
				      
				     }
              }
        }
}


function Select(id,config){
	this.config = config||{};
	this.id = typeof(id)=='string'?document.getElementById(id):id;
	this.items = this.id.getElementsByTagName("li");
	this.selectClass = "select";
	this.selected = new Array();
	var _this = this;
	this.selectOpt = function(value,opt){
		var exist = false;
		for(var i=0;i<_this.selected.length;i++){
			if(_this.selected[i]==value){
				exist = true ;
				if(opt=="remove"){ _this.selected.splice(i,1);}
				break;
			}
		}
		if(!exist && opt=="add"){_this.selected.push(value);}
	};
	//初始化对象
	(function(_this){
		//是否有默认配置的选择项
		if(_this.config.Default){
			var arr = _this.config.Default.split(",");
			for(var i=0;i<arr.length;i++)_this.selectOpt(arr[i],"add");
		}
		
		for(var i=0;i<_this.items.length;i++){
			//将defalut中配置的项加上样式
			if( _this.selected.join(",").indexOf(_this.items[i].getAttribute('dataValue'))>-1 && _this.items[i].className==""){
				_this.items[i].className=_this.selectClass;
			}
			//如果有样式中定义了默认
			if(_this.items[i].className==_this.selectClass){
				_this.selectOpt(_this.items[i].getAttribute('dataValue'),"add");
			}
			//加点单击事件
			_this.items[i].onclick=function(){
				//是否为单选
				var radio = _this.config.Raido?_this.config.Raido:true;
				if(_this.config.Radio==null || _this.config.Radio){
					if(this.className!=_this.selectClass){
						var items = this.parentNode.getElementsByTagName("li");
						for(var i=0;i<items.length;i++){
							items[i].className="";
						}
						_this.selected.length = 0;
						_this.selected.push(this.getAttribute('dataValue'));
						this.className=_this.selectClass;
					}
				}else{
					var Max = _this.config.Max?_this.config.Max:1;
					if(this.className==_this.selectClass){
						this.className="";
						_this.selectOpt(this.getAttribute('dataValue'),"remove");
					}else{
						if(_this.selected.length>=Max){
							alert("最多只能选择"+Max+"项");
						}else{
							this.className=_this.selectClass;
							_this.selectOpt(this.getAttribute('dataValue'),"add");
						}
					}
				}
				//去掉那个虚线框
				this.firstChild.blur();
				//调用回调函数
				if(_this.config.OnClick)_this.config.OnClick.call(this,_this.selected,_this.config.Current);
				return false;
			}
		}
	})(_this);	
}

 jQuery(document).ready(function(){
			  loadprodAttribute();
 });

</script>

