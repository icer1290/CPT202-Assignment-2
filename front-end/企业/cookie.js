	function setCookie(cookieName, cookieValue){
		document.cookie = cookieName + "=" + escape(cookieValue) + ";path=/"
	}
	
	/* 读取cookie 输入变量名，返回变量值 */
	function ReadCookie(cookieName) {
		var theCookie = "" + document.cookie;
		var ind = theCookie.indexOf(cookieName);
		if(ind==-1 || cookieName=="") return "";
		var ind1 = theCookie.indexOf(';',ind);
		if(ind1==-1) ind1 = theCookie.length;
		/*读取Cookie值*/
		return unescape(theCookie.substring(ind+cookieName.length+1,ind1));
	}