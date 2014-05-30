  function openbag(id) {
    window.open("/basket.htm?prodprodId="+id,"","height=420,width=530,left=190,top=10,resizable=yes,scrollbars=yes,status=no,toolbar=no,menubar=no,location=no");
  } 
  function openScript(url, width, height){
	var Win = window.open(url,"openScript",'width=' + width + ',height=' + height + ',resizable=1,scrollbars=yes,menubar=no,status=yes' );
	}
  function bookmark(title, url) {
  if (document.all){
    window.external.AddFavorite(url, title);
    }
  else if (window.sidebar){
    window.sidebar.addPanel(title, url, "")
    }else{
    window.external.AddFavorite(url, title);
    }

}
  
function searchPager(curPageNO){
	document.getElementById("curPageNOTop").value=curPageNO;
	document.getElementById("searchForm").submit();
}

function advanceSearch(path){
	if(form1_onsubmit()){
	if(path !=null && path != "undefined" && path != ""){
		window.location.href = path+ '/searchall/0/' + document.getElementById("headerkeywordp").value;
	}else{
	    window.location.href = '/searchall/0/' + document.getElementById("headerkeywordp").value;
	}
		
	}
}