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

function isMobilephone(js_value)
{
		var	re = /^[0-9\s]*$/;

		if (js_value.match (re))
			return	true;
		return	false;
}