function selectTag(showContent,selfObj){
	// 操作标签
	var tag = document.getElementById("tags").getElementsByTagName("li");
	var taglength = tag.length;
	for(i=0; i<taglength; i++){
		tag[i].className = "";
	}
	selfObj.parentNode.className = "selectTag";
	// 操作内容
	for(i=0; j=document.getElementById("tagContent"+i); i++){
		j.style.display = "none";
	}
	if(showContent == "tagContent2"){
		//defined in productTab.jsp
		getProductComment(); 
	}
	
	if(showContent == "tagContent1"){
			 queryParameter();
	}
	
	document.getElementById(showContent).style.display = "block";
	
}

