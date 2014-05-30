<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
<head>
<title>menu</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/theme/skin/css/base.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/theme/skin/css/menu.css" type="text/css" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script language='javascript'>var curopenItem = '1';</script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/plugins/theme/skin/js/frame/menu.js"></script>
	<script type="text/javascript">	
	function checkFrame(){
	   if(top==this)    
		{
    		 window.location.href = "${pageContext.request.contextPath}/admin/index";
		}
    }
    
    checkFrame();
	</script>
</head>
<body target="main">
<table width='99%' height="100%" border='0' cellspacing='0' cellpadding='0'>
  <tr>
    <td style='padding-left:3px;padding-top:8px' valign="top">
	<!--2. Second Menu Strat -->
				<c:forEach items="${requestScope.currentMenu.subMenu}"  var="subMenu">
						 <dl class='bitem'>
				        <dt id="menu_${subMenu.menuId }"  onClick='showHide("items1_${subMenu.seq}")'><b>${subMenu.name}</b></dt>
				        <dd style='display:block' class='sitem' id='items1_${subMenu.seq}'>
				          <ul class='sitemu'>
				           <c:forEach items="${subMenu.subMenu }" var="thirdMenu">
				           		    <li menuId="menu_${thirdMenu.menuId }">
						              <div class='items'>
						                <div class='fllct'><a href='${pageContext.request.contextPath}${thirdMenu.action}'   target='main'>${thirdMenu.name}</a></div>
						                <div class='flrct'> <a href='${pageContext.request.contextPath}${thirdMenu.action}'  target='main'><img src='${pageContext.request.contextPath}/plugins/theme/skin/images/frame/gtk-sadd.png' alt='${thirdMenu.title}' title='${thirdMenu.title}'/></a> </div>
						              </div>
						            </li>
				           </c:forEach>
				          </ul>
				        </dd>
				      </dl> 
				</c:forEach>
     </td>
  </tr>
</table>
</body>
</html>