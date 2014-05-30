/**
 *  Branch Tag  : BMS 1.1
 *  Branch Time : 2002-04-19
 *  
 *  Change History :
 *  
 *     changed by    : 
 *     change time   : 
 *     change reason : 
 *     change action : 
**/
/**
 *	New a error message localizer in jsp file here.
 *	So the variable "errorLocalizer" is system reserved.
 */

//	client side validation code
function getErrormessage( errormsg )
{
	//	should define a suffix filter array
	var suffix = "<br>";
	var index = errormsg.lastIndexOf(suffix);
	
	if (index!=-1)
		return errormsg.substring(0,index);
	else
		return errormsg;
}	
	
function	isEmail ( js_value )
{
	var	pos ;
	var	re ;
	re = /^\s*$/ ;

	if ( js_value.match(re) )
	{
		return true ;
	}

	pos = js_value.indexOf( '@',0 ) ;
	if ( js_value.length <= 5 ) return false ;
	if ( pos==-1 || pos==0 || pos==(js_value.length-1) ) return false ;

	pos = js_value.indexOf( '.',0 ) ;
	if ( pos<=0 || pos==(js_value.length-1) ) return false ;

	return true ;
}

function	isPhone (js_value)
{
		var	re = /^[0-9\*\-( )]*$/;

		if (js_value.match (re))
				return	true;
		return	false;
	
}

function isMobilephone(js_value)
{
		var	re = /^[0-9\s]*$/;

		if (js_value.match (re))
			return	true;
		return	false;
}

function isPostcode(js_value)
{
		var	re ;
		re = /^\s*$/ ;
	
		if ( js_value.match(re) )
		{
			return true ;
		}
		if ( !is_natural(js_value) || js_value.length!=6 )
		{
			return false ;
		}
		return true ;
}

//	used by isPostCode
function	is_natural ( js_value )
{
		var	re ;
		re = /^\s*$/ ;
	
		if ( js_value.match(re) )
		{
			return true ;
		}
	
		re = /^\+{0,1}[0-9]*$/ ;
		if ( !js_value.match(re) ) return false ;
		return true ;
}
	
function isURL(js_value)
{
			
		var pos, posdot ;
		var	re ;
		re = /^\s*$/ ;
	
		if ( js_value.match(re) )
		{
			return true ;
		}
	
		pos = js_value.indexOf('://',0) ;
		if ( pos<0 ) return false ;
		posdot = js_value.lastIndexOf('.') ;
		if ( posdot<pos ) return false ;
		if ( posdot == js_value.length-1 ) return false ;
		return true ;
}

function isNumber(js_value)
{
	
	var	re ;
	re = /^\s*$/ ;

	if ( js_value.match(re) )
	{
		return true ;
	}
	if ( isNaN(js_value) || js_value.indexOf('.',0) >= 0 )	
	{
		return false ;
	}
	return true ;
		
}

function isDouble(js_value)
{
		var re;
		re = /^\s*$/;
		if( js_value.match(re))
		{
				return true;
		}
		
		if(isNaN(js_value))
				return false;
		return true;
}

function isPositive(js_value)
{
	if	(isDouble(js_value)&&js_value>0)
		return	true;
}

function isDate(date, format)
{
	if (format != 'yyyy-MM-dd')
		format = 'yyyy-MM-dd';
	if (date.length!=10||date==null)
		return false;
		
	var year = parseInt(date.substring(0,4));
	var month = parseInt(date.substring(5,7));
	var day = parseInt(date.substring(8,10));
	//by lixinqian, to deal with date format such as 200K-02-03 or 2000-01-32
	if(!(date.substring(0,4) + date.substring(5,7) + date.substring(8,10) > 19000000))
		return false;	
	if(month<1 || month>12 || day<1 || day >31)
		return false;		
		
	//	return when some date field not specify
	if (isNaN(year) || isNaN(month) || isNaN(day))
	{
		return false;
	}
	
	//	validate the month only has 30 days
	if (month==4||month==6||
		month==9||month==11)
	{
		if (day>30) return false;
		
		return true;
	}
	
	//	validate the February
	if (month==2)
	{
		//	if the year is leap year
		if ((year%400==0)||
			((year%100!=0)&&(year%4==0)))
		{
			if (day>29)	return false;
		}
		else
		{
			if (day>28)	return false;
		}
		
		return true;
	}
	
	if (day>31) return false;
	
	return true;
}

function isMonth(moon, format)
{
	if (format != 'yyyy-MM')
		format = 'yyyy-MM';
	
	if (moon.length!=7||moon==null)
		return false;
		
	var year = parseInt(moon.substring(0,4));
	var month = parseInt(moon.substring(5,7));
	

	//by lixinqian, to deal with date format such as 200K-02-03 or 2000-01-32
	if(!(moon.substring(0,4) + moon.substring(5,7) > 190000))
		return false;	
	if(month<1 || month>12)
		return false;		
		
	//	return when some date field not specify
	if (isNaN(year) || isNaN(month))
	{
		return false;
	}
	
	return true;
}

function isYear(annual, format)
{
	if (format != 'yyyy')
		format = 'yyyy';
	
	if (annual.length!=4||annual==null)
		return false;
		
	var year = parseInt(annual.substring(0,4));

	//by lixinqian, to deal with date format such as 200K-02-03 or 2000-01-32
	if(!(annual.substring(0,4) > 1900))
		return false;	
	//	return when some date field not specify
	if (isNaN(year))
	{
		return false;
	}
	
	return true;
}

function	isLoginname (js_value)
{
	if (isEmpty (js_value))
		return	true;
	
	var re = '[A-Za-z]+[A-Za-z0-9_]*';
	return	regmatch (js_value, re)
}

function	isCode (js_value)
{
	if (isEmpty (js_value))
		return	true;
	
	var re = '[A-Za-z0-9_]*';
	return	regmatch (js_value, re)
}

function	isPassword (js_value)
{
	if (isEmpty (js_value))
		return	true;
	
	var re = '[A-Za-z0-9_]*';
	return	regmatch (js_value, re)
}

function	isPrice (js_value)
{
	return	isDouble (js_value);
}

function	isEmpty (js_value)
{
	var re;
	re = /^\s*$/;
	if(js_value.match (re))
	{
		return true;
	}
	return	false;
}

function contains(s1,s2)
{
	var s = s1.lastIndexOf(s2);
	if (s!=-1)
		return true;
	return false;
}

function regmatch (s1, s2)
{
//	eval ("re = /^" + s2 + "$/");
	re = new RegExp ('^' + s2 + '$')
	return s1.match (re);
//	return s1.match (s2);
}

function strlength(s1,minsize,maxsize)
{
	if (minsize == Number.MAX_VALUE)
		minsize = 0;
	
	var len = s1.length;
	if (len>=minsize && len <= maxsize )
		return true;
	else
		return false;
}

function arraysize(aArray,minsize,maxsize)
{
	if (minsize == Number.MAX_VALUE)
		minsize = 0;
	
	var size = aArray.length;
	if(size >=minsize && size <= maxsize )
		return true;
	else
		return false;
}

function date_compare(date1, date2)
{
	var  date1_time = date1.getTime();
	var  date2_time = date2.getTime();
	
	if (date1_time > date2_time)
			return 1;
	if (date1_time == date2_time)
			return 0;
	return -1;
}

function string_compare(str1, str2)
{
	var len1 = str1.length;
	var len2 = str2.length;
	
	var n;
	if (len1 > len2) n = len2;
		else n = len1;
	
	var i = 0;
	var j = 0;

	while (n-- != 0) 
	{
	    var c1 = str1.charCodeAt(i++);
	    var c2 = str2.charCodeAt(j++);
	    if (c1 != c2) 
	    {
			return c1 - c2;
	    }
	}
	
	return len1 - len2;
    
}

function validate_date(year, month, day)
{
	if (null==year||
		null==month||
		null==day) 
		return;
	
	var year_sel	= year.selectedIndex;
	var month_sel	= month.selectedIndex;
	var day_sel		= day.selectedIndex;
	
	var year_num	= parseInt(year.options[year_sel].value);
	if (isNaN(year_num))
		year_num	= parseInt(year.options[year_sel].text);
		
	var month_num	= parseInt(month.options[month_sel].value);
	if (isNaN(month_num))
		month_num	= parseInt(month.options[month_sel].text);
		
	var day_num		= parseInt(day.options[day_sel].value);
	if (isNaN(day_num))
		day_num		= parseInt(day.options[day_sel].text);
	
	//	return when some date field not specify
	if (isNaN(year_num) || isNaN(month_num) || isNaN(day_num))
	{
		return;
	}
	
	var alert_str = year_num + "年" + month_num + "月没有" + day_num + "天！";
	
	//	validate the month only has 30 days
	if (month_num==4||month_num==6||
		month_num==9||month_num==11)
	{
		if (day_num>30)	alert(alert_str);
		return;
	}
	
	//	validate the February
	if (month_num==2)
	{
		//	if the year is leap year
		if ((year_num%400==0)||
			((year_num%100!=0)&&(year_num%4==0)))
		{
			if (day_num>29)	alert(alert_str);
		}
		else
		{
			if (day_num>28)	alert(alert_str);
		}
		
		return;
	}
}

function	getValue (js_obj)
{
	if (null == js_obj)
		return	null;

	var	re, i;
	re = /^\s*$/ ;

  	if (js_obj.length)
  	{
		if (js_obj[0].type == "checkbox")
		{
			for (i=0; i<js_obj.length; i++)
				if (js_obj[i].checked == true)
					return	js_obj[i].value;
			return	null;
		}
		else if (js_obj[0].type == "radio")
		{
			for (i=0; i<js_obj.length; i++)
				if (js_obj[i].checked == true)
					return	js_obj[i].value;
			return	null;
		}
  	}
 	else
  	{
		if (js_obj.type == "select-multiple")
		{
			if (js_obj.selectedIndex < 0)
				return	null;
			else
				return	js_obj[js_obj.selectedIndex].value;
		}
		else if (js_obj.type == "select-one")
		{
			if (js_obj.selectedIndex < 0)
				return	null;
			else
				return	js_obj[js_obj.selectedIndex].value;
		}
		else if (js_obj.type == "text")
		{
		}
		else if (js_obj.type == "checkbox") 
		{
			if (js_obj.checked == false)
				return	null;
		}
		else if (js_obj.type == "radio")
		{
			if (js_obj.checked == false)
				return	null;
		}

		return	js_obj.value;
  	}

	return	js_obj.value;
}

	function checkNull(element,cname){
		
		if(element.value==""){
			alert("请输入"+cname+"!");
			element.focus();
			return false;
		}else{
		   var str = element.value ;
		   var len = str.length ;
		   j= 0 ;
		   for(i=0;i<len;i++){
		      substr =  str.substring(i,i+1);
			  if(substr==" "){
			     j = j+1 ; 
			  }
		   }
		   if(j==len){
		       alert("请输入"+cname+"!");
			   element.focus();
			    return false;
		   }
		}
		return true;
	}
	
	function checkNum(element,cname){
		if(isNaN(element.value)){
			alert(cname+"必须为数字！");
			element.focus();
			return false;
		}
		return true;
	}

	function checkMax(element,max,cname){
		if(element.value.getByteSize()>=max){
			alert(cname+" 字数过长 ！");
			element.focus();
			return false;
		}
		return true;
	}
	
	function checkLen(element,len,cname){
	   if(element.value.length!=len){
	     alert(cname+"长度必须为"+len+"位!");
	     element.focus() ;
	     return false;
	   }
	   return true ;
	}
	
