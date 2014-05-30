<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>LegendShop商品属性选择器</title>
<style type="text/css">
<!--
body{padding:20px;}
body,td,th {
	font-size: 12px;
}
a:link {
	color: #000000;
}
a:visited {
	color: #000000;
}
a:hover {
	color: #000000;
}

ul{list-style-type:none;}
ul li{float:left;display:inline;margin-right:5px;width:auto;overflow:hidden;}
ul li a{display:block;border:1px solid #CCCCCC;padding:5px 6px 5px 6px;margin:1px;}
ul li a:hover{border:2px solid #FF6701;margin:0px;}
.select{}
.select a{
border:2px solid #FF6701;
margin:0px;
background:url(/common/default/images/productselected.gif) no-repeat right bottom;
}
-->
</style>

<script language="javascript">
window.onload=function(){
	var color = new Select("color",{
		Radio :true	,	//是否为单选,默认为true,如果设置为true,Default,Max选项将失效
		OnClick:function(selected){
			document.form.color.value = selected.join(",")
		}
	});
	
	var size = new Select("size",{
		OnClick:function(selected){
			document.form.size.value = selected.join(",")
		}
	});
	
	var other = new Select("other",{
		Radio:false,
		Max:5,
		//Default:"袜子,扣子",
		OnClick:function(selected){
			document.form.other.value = selected.join(",")
		}
	});

}
</script>
</head>
<body>
<form name="form">
<table border="1" cellspacing="0" cellpadding="0">
  <tr>
    <td>颜色(单选)：</td>
    <td><ul id="color">
    	<li dataValue="red"><a href="#">红色</a></li>
        <li dataValue="blue"><a href="#">蓝色</a></li>
        <li dataValue="amethyst"><a href="#">紫色</a></li>
        <li dataValue="green"><a href="#">绿色</a></li>
    </ul></td>
  </tr>
  <tr>
    <td>尺码(单选)：</td>
    <td>
    <ul id="size">
    	<li dataValue="S"><a href="#">S</a></li>
        <li dataValue="M" class="select"><a href="#">M</a></li>
        <li dataValue="L"><a href="#">L</a></li>
        <li dataValue="XL"><a href="#">XL</a></li>
    </ul></td>
  </tr>
  <tr>
    <td>配件(多选)：</td>
    <td>
    <ul id="other">
    	<li dataValue="袜子"><a href="#">袜子</a></li>
        <li dataValue="扣子"><a href="#">扣子</a></li>
        <li dataValue="盒子" class="select"><a href="#">盒子</a></li>
        <li dataValue="袋子"><a href="#">袋子</a></li>
        <li dataValue="鞋子"><a href="#">鞋子</a></li>
        <li dataValue="帽子"><a href="#">帽子</a></li>
        <li dataValue="围巾"><a href="#">围巾</a></li>
    </ul>
    </td>
  </tr>
</table>
<div id="result">
   <input name="color" type="text" id="color" size="15" />
    <input name="size" type="text" id="size" size="15" />
<input name="other" type="text" id="other" size="15" />
</div>
</form>

<script language="javascript" type="text/javascript">
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
				if(_this.config.OnClick)_this.config.OnClick.call(this,_this.selected);
				return false;
			}
		}
	})(_this);	
}
</script>
</body>
</html>
