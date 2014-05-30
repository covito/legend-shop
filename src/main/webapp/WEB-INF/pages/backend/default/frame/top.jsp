<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@page import="com.legendshop.core.UserManager"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<head>
<title>top</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath}/plugins/theme/skin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/common/default/css/back_top.css" rel="stylesheet" type="text/css" />
<script language='javascript'>
var preFrameW = '206,*';
var FrameHide = 0;
var curStyle = 1;
var totalItem = ${fn:length(sessionScope.MENU_LIST)}; //need to be changed when add tab
function ChangeMenu(way){
	
	var addwidth = 10;
	var fcol = window.top.document.getElementById("btFrame").cols;//top.document.all.btFrame.cols;
	if(way==1) addwidth = 10;
	else if(way==-1) addwidth = -10;
	else if(way==0){
		if(FrameHide == 0){
			preFrameW = top.document.all.btFrame.cols;
			top.document.all.btFrame.cols = '0,*';
			FrameHide = 1;
			return;
		}else{
			window.top.document.getElementById("btFrame").cols = preFrameW;
			FrameHide = 0;
			return;
		}
	}
	fcols = fcol.split(',');
	fcols[0] = parseInt(fcols[0]) + addwidth;
	window.top.document.getElementById("btFrame").cols = fcols[0]+',*';
}


function mv(selobj,moveout,itemnum)
{
   if(itemnum==curStyle) return false;
   if(moveout=='m') selobj.className = 'itemsel';
   if(moveout=='o') selobj.className = 'item';
   return true;
}

function changeSel(itemnum)
{
  curStyle = itemnum;
  for(i=1;i<=totalItem;i++)
  {
     if(document.getElementById('item'+i)) document.getElementById('item'+i).className='item';
  }
  document.getElementById('item'+itemnum).className='itemsel';
}

function  logout()
{
   if  (confirm("确认退出管理系统?"))
   {
     document.location.href="${pageContext.request.contextPath}/p/logout";
   }
}
</script>

</head>
<body bgColor='#ffffff'>
<table width="100%"  cellpadding="0" cellspacing="0" background="${pageContext.request.contextPath}/plugins/theme/skin/images/frame/topbg.gif">
  <tr>
    <td width='20%' height="60"><!-- <img src="skin/images/frame/logo.gif" />  --></td>
    <td width='80%' align="right" valign="bottom">
    	<table width="750"  cellspacing="0" cellpadding="0">
      <tr>
      <td align="right" height="26" style="padding-right:10px;line-height:26px;">
      	         当前用户：<b>${sessionScope.SPRING_SECURITY_LAST_USERNAME}</b> 
      	     <ls:plugin pluginId="advanceSearch"> &nbsp;
      	    		<span class="username"><a href="${pageContext.request.contextPath}/all"" target="_parent">搜索商城</a></span>
      	    </ls:plugin>
      	    <span class="username"><a href="${pageContext.request.contextPath}/index" target="_parent">返回前台</a></span>
      	    <ls:myshop>
            	<span class="username"><a href="<ls:domain shopName='${sessionScope.SPRING_SECURITY_LAST_USERNAME}'  />"  target="_parent">我的商城</a></span>
           </ls:myshop>
        	<span class="username"><a href="${pageContext.request.contextPath}/p/logout" target="_parent">退出</a></span>
      </td>
      </tr>
      <tr>
        <td align="right" height="34" class="rmain">
		<dl id="tpa">
		<dd class='img'><a href="javascript:ChangeMenu(-1);"><img vspace="5" src="${pageContext.request.contextPath}/plugins/theme/skin/images/frame/arrl.gif"  width="5" height="8" alt="Reduce left frame"  title="Reduce left frame" /></a></dd>
		<dd class='img'><a href="javascript:ChangeMenu(0);"><img vspace="3" src="${pageContext.request.contextPath}/plugins/theme/skin/images/frame/arrfc.gif"  width="12" height="12" alt="Show/Hide left frame" title="Show/Hide left frame" /></a></dd>
		<dd class='img' style="margin-right:10px;"><a href="javascript:ChangeMenu(1);"><img vspace="5" src="${pageContext.request.contextPath}/plugins/theme/skin/images/frame/arrr.gif"  width="5" height="8" alt="Add left frame" title="Add left frame" /></a></dd>

		<c:forEach items="${sessionScope.MENU_LIST}"  var="menu" varStatus="status">
				<c:choose>
						<c:when test="${status.index eq 0}">
						<dd menuId="${menu.menuId}" ><div class='itemsel'  id='item${menu.seq}'   onMouseMove="mv(this,'m',${menu.seq});" onMouseOut="mv(this,'o',${menu.seq});"><a href="${pageContext.request.contextPath}${menu.action}" onclick="changeSel(${menu.seq})" target="menu">${menu.name}</a></div></dd>
						</c:when>
						<c:otherwise>
						<dd menuId="${menu.menuId}" ><div class='item'  id='item${menu.seq}'   onMouseMove="mv(this,'m',${menu.seq});" onMouseOut="mv(this,'o',${menu.seq});"><a href="${pageContext.request.contextPath}${menu.action}" onclick="changeSel(${menu.seq})" target="menu">${menu.name}</a></div></dd>
						</c:otherwise>
				</c:choose>
		</c:forEach>
		</dl>
		</td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>