function scallWin(){
    if(!showad){return;}
    if (window.screen.width<=MinScreenW){
        showad = false;
        document.getElementById("coupletLeftDiv").style.display="none";
        document.getElementById("coupletRightDiv").style.display="none";
        return;
    }
    
    var Borderpx = ((window.screen.width-PageWidth)/2-AdDivW)/2;
    var x,y;
    if(document.body.scrollTop){
           x=document.body.scrollLeft;
           y=document.body.scrollTop;
    }else{
           x=document.documentElement.scrollLeft;
           y=document.documentElement.scrollTop;
    }

    document.getElementById("coupletLeftDiv").style.display="block";
    document.getElementById("coupletLeftDiv").style.top=y+Toppx+"px";
    document.getElementById("coupletLeftDiv").style.left=x+Borderpx+"px";

    document.getElementById("coupletRightDiv").style.display="block";
    document.getElementById("coupletRightDiv").style.top=y+Toppx+"px";
    document.getElementById("coupletRightDiv").style.left=x+document.body.clientWidth-document.getElementById("coupletRightDiv").offsetWidth-Borderpx+"px";

}

function hidead()
{
    showad = false;
    document.getElementById("coupletLeftDiv").style.display="none";
    document.getElementById("coupletRightDiv").style.display="none";
}
