function highlightTableRows(tableId) {
        var previousClass = null;  
        var table = document.getElementById(tableId);
        if(table == null) return;
        var tbody;
        if(table.getElementsByTagName("tbody") != null){
         tbody = table.getElementsByTagName("tbody")[0]; 
        }
        if (tbody == null) {  
            var rows = table.getElementsByTagName("tr");  
        } else {  
            var rows = tbody.getElementsByTagName("tr");  
        }  
        for (i=0; i < rows.length; i++) {
            rows[i].onmouseover = function() { previousClass=this.className;this.className='pointercolor' };  
            rows[i].onmouseout = function() { this.className=previousClass };  
			/*
            rows[i].onclick = function() {
                var cell = this.getElementsByTagName("td")[0];  
                var link = cell.getElementsByTagName("a")[0];  
                var theUrl=link.getAttribute("href");  
                if(theUrl != null && theUrl.indexOf("javascript")!=-1){
                    eval(theUrl); }else{
                    location.href=theUrl;  
                }
       		 this.style.backgroundColor="blue";  
            }  
            
            */
        }  
    } 
    
 function selAll(){
    chk = document.getElementById("checkbox");
	allSel = document.getElementsByName("strArray");
	
	if(!chk.checked)
		for(i=0;i<allSel.length;i++)
		{
			   allSel[i].checked=false;
		}
	else
		for(i=0;i<allSel.length;i++)
		{
			   allSel[i].checked=true;
		}
	}
	
 	function checkSelect(selAry){
		for(i=0;i<selAry.length;i++)
		{
			if(selAry[i].checked)
			{
				return true;
			}
		}
		return false;
	 }

    function pager(curPageNO){
        document.getElementById("curPageNO").value=curPageNO;
        document.getElementById("form1").submit();
    }
	 