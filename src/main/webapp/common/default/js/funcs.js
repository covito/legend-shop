var CurScreen ;
var refreshSwitchTimer;
var Switcher = new Array();

function startSwitcher(){
	        for(var i in dataArray){
	                     maxScreen = dataArray.length;
	                     var ii = parseInt(i);
	                     var data = dataArray[i] ;
	                     Switcher[ii+1] = Array();
	                     Switcher[ii+1]['title'] = data.title ;
	                     Switcher[ii+1]['stitle'] = data.stitle ;
	                     Switcher[ii+1]['link'] = data.titleLink ;
	                     var imgorder = "img"+(ii+1);
	                     var imgs = document.getElementById(imgorder);
	                     imgs.src = '/photoserver/photo/' + data.img;
	                     imgs.alt = data.alt;
	                     var linkorder = "imglink"+(ii+1); 
	                     var links = document.getElementById(linkorder);
	                     links.href = data.link;
	            }
	            switchPic(1);
	            refreshSwitchTimer = setTimeout('reSwitchPic()', 6000);
}


function switchPic(screen) {
	if (screen > maxScreen) {
		screen = 1 ;
	}
	
	for (i=1;i<=maxScreen;i++) {
		document.getElementById("Switch_"+i).style.display = "none" ;
	}
	document.getElementById("Switch_"+screen).style.display = "block" ;
	showSwitchNav(screen);
	showSwitchTitle(screen);
	//Effect.Appear("Switch_"+screen);
			
	//switchLittlePic(screen);
	//showSwitchTitles(screen);
	CurScreen = screen  ;
}
function showSwitchNav(screen) {
	var NavStr = "" ;
	for (i=1;i<=maxScreen;i++) {
		if (i == screen) {
			NavStr += '<li onmouseover="pauseSwitch();" onmouseout="goonSwitch();"><a href="javascript://" target="_self" class="sel">'+i+'</a></li>' ;
		}
		else {
			NavStr += '<li onmouseover="pauseSwitch();" onmouseout="goonSwitch();" onclick="goManSwitch('+i+');"><a href="javascript://" target="_self">'+i+'</a></li>' ;
		}
		
	}
	document.getElementById("SwitchNav").innerHTML = NavStr ;
}
function showSwitchTitle(screen) {
	var titlestr = "" ;
	titlestr = '<h3><a href="'+Switcher[screen]['link']+'" target="_blank">'+Switcher[screen]['title']+'</a></h3><p><a href="'+Switcher[screen]['link']+'" target="_blank">'+Switcher[screen]['stitle']+'</a></p>' ;
	document.getElementById("SwitchTitle").innerHTML = titlestr ;
}
function reSwitchPic() {
	refreshSwitchTimer = null;
	switchPic(CurScreen+1);
	refreshSwitchTimer = setTimeout('reSwitchPic()', 6000);
}

function pauseSwitch() {
    if(refreshSwitchTimer != null){
       clearTimeout(refreshSwitchTimer);
    }
}

function goonSwitch() {
    if(refreshSwitchTimer != null){
       clearTimeout(refreshSwitchTimer);
    }
    refreshSwitchTimer = setTimeout('reSwitchPic();', 6000);
}

function goManSwitch(index) {
    if(refreshSwitchTimer != null){
       clearTimeout(refreshSwitchTimer);
    }
	CurScreen = index - 1 ;
	reSwitchPic();
}


