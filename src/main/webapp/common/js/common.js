function numberInputOnly(event, isInteger, allowNegative, isPercent) {
	var k = event.charCode||event.keyCode;
	if(k == 8) {//8 for BackSpace
		return true;
	}
	if(k == 45 && allowNegative) {//45 for -
		return true;
	}
	if(k == 46 && !isInteger) {//46 for .
		return true;
	}
	if(k == 37 && isPercent) {//37 for %
		return true;
	}
	
	if(k >= 48 && k <= 57) {
		return  true;
	} else {
		return  false
	}
}

function isEnter(event) {
	var k = event.charCode||event.keyCode;
	return k == 13;
}

function isInputtingSplitter(event, splitter) {
	var k = event.charCode||event.keyCode;
	return k == splitter.toString().charCodeAt();
}

function isNum(obj){
	 var val = obj.value ;
	 if(val==null||val==''){
	    return ;
	 }
	 
	 if( isNaN(val) ){
		 alert("请输入数字！") ;
		 obj.focus() ;
	 }
 }
 
 function checkExcelFileType( fileName ){
	 var pos = fileName.lastIndexOf(".");
	 var lastname = fileName.substring(pos,str.length) ;
	 if ( lastname.toLowerCase()==".xls" || lastname.toLowerCase()==".xlsx"){
	     return true;
	 }else {
	   	 alert("您上传的文件类型不对，必须为EXCEL文件类型");
	     return false;
	 }
}

 function checkTxtFileType( fileName ){
	 var pos = fileName.lastIndexOf(".");
	 var lastname = fileName.substring(pos,str.length) ;
	 if ( lastname.toLowerCase()==".txt"){
	     return true;
	 }else {
	   	 alert("您上传的文件类型不对，必须为txt类型");
	     return false;
	 }
}
 
function getValueFromRadios(radioName)
{
   var radios = document.getElementsByName(radioName);
   for(var i = 0;i < radios.length;i ++)
   {
      if(radios[i].checked)
      {
         return radios[i].value;
      }
   }
   return '';
}

function setValueForRadios(radioName, value) {
	var radioArray = document.getElementsByName(radioName);
   	for(var index = 0; index < radioArray.length; index++)
   	{
      	if(radioArray[index].value == value)
      	{
        	radioArray[index].checked = true;
      	} else {
      		radioArray[index].checked = false;
      	}
	}
}


function getValuesFromCheckBoxes(checkBoxName) {
   var strValue = '';
   var checkBoxs = document.getElementsByName(checkBoxName);

   for(var i = 0; i < checkBoxs.length;i ++)
   {
      if(checkBoxs[i].checked)
      {
      	 if(strValue != '') strValue += ',';
         strValue += checkBoxs[i].value;
      }
   }

   return strValue;
}

function setValuesForCheckboxes(checkboxName, values) {
	var checkboxArray = document.getElementsByName(checkboxName);
	var valueArray = values.split(",");	
	for(var i = 0; i < valueArray.length; i++) {
		for(var j = 0; j < checkboxArray.length; j++) {
			if(checkboxArray[j].value == valueArray[i]) {
				checkboxArray[j].checked = true;
				break;
			}
		}
	}
}
 
 function setReadonly(obj){
       obj.disabled = true ;
       var _inputArray = obj.getElementsByTagName("input") ;
       if(_inputArray){
           if(_inputArray.length){
                for(var i=0 ;i<_inputArray.length;i++){
                    _inputArray[i].disabled = 'disabled';
                }
           }
       }
       var _selectArray = obj.getElementsByTagName("select") ;
       if(_selectArray){
           if(_selectArray.length){
                for(var i=0 ;i<_selectArray.length;i++){
                    _selectArray[i].disabled = 'disabled';
                }
           }
       }
       var _iArray = obj.getElementsByTagName("img") ;
       if(_iArray){
           if(_iArray.length){
                for(var i=0 ;i<_iArray.length;i++){
                    _iArray[i].disabled = 'disabled';
                }
           }
       }
 }
 
 function setWritable(obj) {
       obj.disabled = false ;
       var _inputArray = obj.getElementsByTagName("input") ;
       if(_inputArray){
           if(_inputArray.length){
                for(var i=0 ;i<_inputArray.length;i++){
                    _inputArray[i].disabled = false;
                }
           }
       }
       var _selectArray = obj.getElementsByTagName("select") ;
       if(_selectArray){
           if(_selectArray.length){
                for(var i=0 ;i<_selectArray.length;i++){
                    _selectArray[i].disabled = false;
                }
           }
       }
       
       var _iArray = obj.getElementsByTagName("img") ;
       if(_iArray){
           if(_iArray.length){
                for(var i=0 ;i<_iArray.length;i++){
                    _iArray[i].disabled = false;
                }
           }
       }
 }
 
 function resizeWindow(tableName) {
	window.resizeTo($(tableName).offsetWidth + 10,$(tableName).offsetHeight + 110);
	if($(tableName).offsetHeight < 120) {
		window.close();
	}
}
 
 function computeLeft(width) {
 	return (window.screen.availWidth - width) / 2;
 }
 
 function computeTop(height) {
 	return (window.screen.availHeight - height) / 2;
 }
 
 function ShowWin( sURL , sFeatures ) {
	window.showModalDialog( sURL , null , sFeatures)
	//window.showModalDialog("Sample.htm",null,"dialogHeight:591px;dialogWidth:650px;")
	//window.showModalDialog([sURL] [, vArguments] [, sFeatures])
	//sFeatures{dialogHeight; dialogLeft; dialogTop; dialogWidth; center; dialogHide; edge; help; resizable; scroll; status; unadorned}
 }
 
 function showDialog(url, param, isModal, width, height) {
 	var features = "scroll:no; status:no; resizable:yes; center:yes; dialogHeight:" + height + "px; dialogWidth:" + width + "px;";
 	var returnValue;
 	if(isModal) {
 		returnValue = window.showModalDialog(url, param, features);
 	} else {
 		returnValue = window.showModelessDialog(url, param, features);
 	}
 	
 	return returnValue;
 }
 
function formatStringToData(data){
		  if(data==null) return 0 ;
		  var ret = '';
		  for(var i=0 ;i<data.length ;i++){
			  var temp = data.charAt(i)  ;
			  try{
				 if( !isNaN(temp) ){
				     ret = ret + temp ;
				 }
			  }catch(e){
			  }
		  }
		  return ret ;  
     }

 
 function openWindow(url, windowName, width, height) {
 	var left = computeLeft( formatStringToData(width) );
 	var top  = computeTop( formatStringToData(height) );
 	var features = "scrollbars=no, status=no, toolbar=no, resizable=yes, width=" + width + ", height=" + height + ", left=" + left + ", top=" + top;
 	return window.open(url, windowName, features);
 }
 
 function openWindowWithNoReturn(url, windowName, width, height) {
 	openWindow(url, windowName, width, height);
 }
 
 function showDialogWithNoReturn(url, param, isModal, width, height) {
 	showDialog(url, param, isModal, width, height);
 }
 
 
 /* other */
function autoResize(ifmobj)
{
	
	if(document.all(ifmobj.id).readyState=="complete")
	{
		try
		{
			var sHeight=window.frames[ifmobj.id].document.body.scrollHeight;
			
			if(sHeight<window.document.body.scrollHeight)
			{
				sHeight="100%";
			}
			document.all(ifmobj.id).style.height = sHeight;	
		}
		catch(e)
		{}
	}
	
}


function selectall(selector){
	var boxarray=document.all(selector).all.tags("input");
	if(document.all("checkboxid").checked){
		for( var i=0 ; i<boxarray.length ; i++){
			if(boxarray[i].type=="checkbox"){
				boxarray[i].checked= true;
			}
		}	
	}
	else{
		for( var i=0 ; i<boxarray.length ; i++){
			if(boxarray[i].type=="checkbox"){
				boxarray[i].checked= false;
			}
		}
	}
}

/* bn.htm */
var GetCacheNav;
function ChangeNav(obj_nav,FrmFdr,Ltb,Rtm,MainFrm)
{
	
	if(GetCacheNav!=null){
		GetCacheNav.className = "nav_f";
	}
	else{
		document.all("FirNav").className = "nav_f";
	}
	
	obj_nav.className = "nav_s";
	GetCacheNav = obj_nav;
	
	var frmFdr = "samplecode";
	var frmLtb = "ltb.htm";
	var frmRtm = "mls.htm";
	
	if(FrmFdr!=""){	
		frmFdr = FrmFdr;
	}
	if(Ltb!=""){	
		frmLtb = Ltb;
	}
	if(Rtm!=""){
		frmRtm = Rtm;
	}
	
	if(MainFrm==""){
		window.parent.document.all("mframe").src="../../modules/common/mainframe.htm?"+frmFdr+"&"+frmLtb+"&"+frmRtm;
	}
	else{
		window.parent.document.all("mframe").src="../../modules/"+frmFdr+"/"+MainFrm;
	}	
}
////删除字符串左右空格
String.prototype.trim = function () {
	return this.replace(/(^[\s]*)|([\s]*$)/g, "");
};

//删除字符串左边空格
String.prototype.lTrim = function () {
	return this.replace(/(^[\s]*)/g, "");
};

//删除字符串右边空格
String.prototype.rTrim = function () {
	return this.replace(/([\s]*$)/g, "");
};
//定义一个可静态调用方法的js类
function CheckUtil() {
}
//校验是否为空(先删除二边空格再验证)
CheckUtil.isNull = function (str) {
	if (null == str || "" == str.trim()) {
		return true;
	} else {
		return false;
	}
};
//校验是否全是数字
CheckUtil.isDigit = function (str) {
	var patrn = /^\d+$/;
	return patrn.test(str);
};
//校验是否是整数
CheckUtil.isInteger = function (str) {
	var patrn = /^([+-]?)(\d+)$/;
	return patrn.test(str);
};
//校验是否为正整数
CheckUtil.isPlusInteger = function (str) {
	var patrn = /^([+]?)(\d+)$/;
	return patrn.test(str);
};
//校验是否为负整数
CheckUtil.isMinusInteger = function (str) {
	var patrn = /^-(\d+)$/;
	return patrn.test(str);
};
//校验是否为浮点数
CheckUtil.isFloat = function (str) {
	var patrn = /^([+-]?)\d*\.\d+$/;
	return patrn.test(str);
};
//校验是否为正浮点数
CheckUtil.isPlusFloat = function (str) {
	var patrn = /^([+]?)\d*\.\d+$/;
	return patrn.test(str);
};
//校验是否为负浮点数
CheckUtil.isMinusFloat = function (str) {
	var patrn = /^-\d*\.\d+$/;
	return patrn.test(str);
};
//校验是否仅中文
CheckUtil.isChinese = function (str) {
	var patrn = /[\u4E00-\u9FA5\uF900-\uFA2D]+$/;
	return patrn.test(str);
};
//校验是否仅ACSII字符
CheckUtil.isAcsii = function (str) {
	var patrn = /^[\x00-\xFF]+$/;
	return patrn.test(str);
};
//校验手机号码
CheckUtil.isMobile = function (str) {
	var patrn = /^0?1((3[0-9]{1})|(59)|(58)){1}[0-9]{8}$/;
	return patrn.test(str);
};
//校验电话号码
CheckUtil.isPhone = function (str) {
	var patrn = /^(0[\d]{2,3}-)?\d{6,8}(-\d{3,4})?$/;
	return patrn.test(str);
};
//校验URL地址
CheckUtil.isUrl = function (str) {
	var patrn = /^http[s]?:\/\/[\w-]+(\.[\w-]+)+([\w-\.\/?%&=]*)?$/;
	return patrn.test(str);
};
//校验电邮地址
CheckUtil.isEmail = function (str) {
	var patrn = /^[\w-]+@[\w-]+(\.[\w-]+)+$/;
	return patrn.test(str);
};
//校验邮编
CheckUtil.isZipCode = function (str) {
	var patrn = /^\d{6}$/;
	return patrn.test(str);
};
//校验合法时间
CheckUtil.isDate = function (str) {
	if (!/\d{4}(\.|\/|\-)\d{1,2}(\.|\/|\-)\d{1,2}/.test(str)) {
		return false;
	}
	var r = str.match(/\d{1,4}/g);
	if (r == null) {
		return false;
	}
	var d = new Date(r[0], r[1] - 1, r[2]);
	return (d.getFullYear() == r[0] && (d.getMonth() + 1) == r[1] && d.getDate() == r[2]);
};
//校验字符串：只能输入6-20个字母、数字、下划线(常用手校验用户名和密码)
CheckUtil.isString6_20 = function (str) {
	var patrn = /^(\w){6,20}$/;
	return patrn.test(str);
};

function getUTCDate(utcString){
   var date = new Date(utcString) ;
   var y = date.getYear() ;
   var m = format(date.getMonth()+1) ;
   var d = format(date.getDate()) ;
   var h = format(date.getHours()) ;
   var mi = format(date.getMinutes()) ;
   var s = format(date.getSeconds()) ;
   return y+"-"+m+"-"+d+" "+h+":"+mi+":"+s;
   
   function format(data){
      if(data==null)
         return "00" ;
      var _d = data+"" ;
      if(_d.length==1){
        return "0"+_d ;
      }
      return _d ;
   }
}
/**
*
*
*/
Date.prototype.dateDiff = function(interval,objDate){
    //若參數不足或 objDate 不是日期物件則回傳 undefined
    if(arguments.length<2||objDate.constructor!=Date) return undefined;
    switch (interval) {
      //計算秒差
      case "s":return parseInt((objDate-this)/1000);
      //計算分差
      case "n":return parseInt((objDate-this)/60000);
      //計算時差
      case "h":return parseInt((objDate-this)/3600000);
      //計算日差
      case "d":return parseInt((objDate-this)/86400000);
      //計算週差
      case "w":return parseInt((objDate-this)/(86400000*7));
      //計算月差
      case "m":return (objDate.getMonth()+1)+((objDate.getFullYear()-this.getFullYear())*12)-(this.getMonth()+1);
      //計算年差
      case "y":return objDate.getFullYear()-this.getFullYear();
      //輸入有誤
      default:return undefined;
    }
}


/* logonow.htm */
function checkValue()
{
	if (document.all("sltName").value == "0")
	{
		alert("请选择相应用户登录！");
	}
	else
	{
		window.returnValue = document.all("sltName").value;
		SetCookie("SPRING_SECURITY_LAST_USERNAME",window.returnValue);
		//window.close();
		window.location.replace ("themes/dfstyle/default.htm");
	}
}


		
function removeAllChilds(containerId, shouldResize) {
	if($(containerId).childNodes.length > 0) {
		$(containerId).removeChild($(containerId).childNodes[0]);
		removeAllChilds(containerId, shouldResize);
	} else {
		if(shouldResize) {
			resizeWindow("mainTable");
		}
	}
}

	/**合并单元格*/
  function spanGrid(tabObj,colIndex){
	 if(tabObj != null){
		  var i,j;
		  var intSpan;
		  var strTemp;
		  for(i = 0; i < tabObj.rows.length; i++){
			   intSpan = 1;
			   strTemp = tabObj.rows[i].cells[colIndex].innerText;
			   for(j = i + 1; j < tabObj.rows.length; j++){
				    if(strTemp == tabObj.rows[j].cells[colIndex].innerText){
					     intSpan++;
					     tabObj.rows[i].cells[colIndex].rowSpan  = intSpan;
					     tabObj.rows[j].cells[colIndex].style.display = "none";
				    }else{
				     	 break;
				    }
			   }
			   i = j - 1;
		 }
	 }
 }
 
 String.prototype.replaceAll =  function(AFindText,ARepText){
  raRegExp = new RegExp(AFindText,"g");
  return this.replace(raRegExp,ARepText);
}

function formatDate(day) {
	if(day) {
		var year = day.getYear();
		var month = day.getMonth() + 1;
		var date = day.getDate();
		if(month.toString().length == 1) {
			month = "0" + month;
		}
		if(date.toString().length == 1) {
			date = "0" + date;
		}
		return year + "-" + month + "-" + date;
	} else {
		return "";
	}
}

function insertText(obj,str) {
    if (document.selection) {
        var sel = document.selection.createRange();
        sel.text = str;
    } else if (typeof obj.selectionStart === 'number' && typeof obj.selectionEnd === 'number') {
        var startPos = obj.selectionStart,
            endPos = obj.selectionEnd,
            cursorPos = startPos,
            tmpStr = obj.value;
        obj.value = tmpStr.substring(0, startPos) + str + tmpStr.substring(endPos, tmpStr.length);
        cursorPos += str.length;
        obj.selectionStart = obj.selectionEnd = cursorPos;
    } else {
        obj.value += str;
    }
}

//将光标移动到最后
function moveEnd(obj){
    obj.focus();
    var len = obj.value.length;
    if (document.selection) {
        var sel = obj.createTextRange();
        sel.moveStart('character',len);
        sel.collapse();
        sel.select();
    } else if (typeof obj.selectionStart == 'number' && typeof obj.selectionEnd == 'number') {
        obj.selectionStart = obj.selectionEnd = len;
    }
} 