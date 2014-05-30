
var caution = false;
function setCookie(name, value, expires, path, domain, secure) {
	var curCookie = name + "=" + escape(value) + ((expires) ? ";   expires=" + expires.toGMTString() : "") + ((path) ? ";   path=" + path : "") + ((domain) ? ";   domain=" + domain : "") + ((secure) ? ";   secure" : "");
	if (!caution || (name + "=" + escape(value)).length <= 4000) {
		document.cookie = curCookie;
	} else {
		if (confirm("Cookie   exceeds   4KB   and   will   be   cut!")) {
			document.cookie = curCookie;
		}
	}
}
function getCookie(name) {
	var prefix = name + "=";
	var cookieStartIndex = document.cookie.indexOf(prefix);
	if (cookieStartIndex == -1) {
		return null;
	}
	var cookieEndIndex = document.cookie.indexOf(";", cookieStartIndex + prefix.length);
	if (cookieEndIndex == -1) {
		cookieEndIndex = document.cookie.length;
	}
	return unescape(document.cookie.substring(cookieStartIndex + prefix.length, cookieEndIndex));
}
function deleteCookie(name, path, domain) {
	if (getCookie(name)) {
		document.cookie = name + "=" + ((path) ? ";   path=" + path : "") + ((domain) ? ";   domain=" + domain : "") + ";   expires=Thu,   01-Jan-70   00:00:01   GMT";
	}
}
function fixDate(date) {
	var base = new Date(0);
	var skew = base.getTime();
	if (skew > 0) {
		date.setTime(date.getTime() - skew);
	}
}
function show(form) {
	var getname = getCookie("name");
	if (getname != null) {
		form.NewText.value = getname;
	}
	deleteCookie("name");
}

