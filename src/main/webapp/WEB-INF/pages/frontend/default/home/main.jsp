<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/default/js/funcs.js"></script>
<div id="bodyer">
<c:forEach items="${requestScope.INDEX_ADV_1}" var="adv">
<table width="954px" cellpadding="0" cellspacing="0" style="margin-top: 5px" class="picstyle">
  <tr><td><a href="${adv.linkUrl}"><img src="<ls:photo item='${adv.picUrl}'/>" title="${adv.title}" width="100%"/></a></td></tr>
 </table>
</c:forEach>
<table width="950px" cellspacing="0" cellpadding="0" align="center" style="margin-top: 4px">
  <tr>
    <td>
      <%@ include file="../notice.jsp"%>
      <%@ include file="../topnews.jsp"%>
     </td>
  <td width="530px" valign="top">
   <div class="flashNews" style="width: 530px">
		<div id="Switch_1"><a id="imglink1" target="_blank" ><img class="indexJpg" id="img1"  src="${pageContext.request.contextPath}/common/images/ajax-loader.gif"  width="530px" height="290px" /></a></div>
        <c:forEach begin="2" end="${maxScreen}" var="i">
        	<div id="Switch_${i}" style="display:none;"><a id="imglink${i}" target="_blank"><img  class="indexJpg"  id="img${i}" width="530px" height="290px" /></a></div>
        </c:forEach>
 	<div id="SwitchTitle"></div>
		<ul id="SwitchNav" style="margin: 0px"></ul>
		<div class="bg" ></div>
    </div>
    </td>
    <td valign="top" width="205px"><%@ include file="../newproducts.jsp"%></td>
  </tr>
</table>
<c:forEach items="${requestScope.INDEX_ADV_2}" var="adv">
<table width="954px" cellpadding="0" cellspacing="0" style="margin-bottom: 4px" class="picstyle">
<tr><td><a href="${adv.linkUrl}"><img src="<ls:photo item='${adv.picUrl}'/>" title="${adv.title}" width="100%"/></a></td></tr>
 </table>
</c:forEach>
<table width="954px" cellspacing="0" cellpadding="0" style="margin-bottom: 5px" class="tables">
  <tr> 
    <td valign="top" align="left"> 
      <%@ include file="../recommend.jsp"%>
    </td>
  </tr>
</table>
<c:forEach items="${requestScope.INDEX_ADV_3}" var="adv">
<table width="954px" cellpadding="0" cellspacing="0" style="margin-top: 5px" class="picstyle">
<tr><td><a href="${adv.linkUrl}"><img src="<ls:photo item='${adv.picUrl}'/>" title="${adv.title}" width="100%"/></a></td></tr>
 </table>
</c:forEach>
</div>
<script type="text/javascript">
<!--
     //indexJpg
    var maxScreen = '${maxScreen}' ;
    var dataArray = jQuery.parseJSON( '${indexJSON}');
      jQuery(document).ready(function(){
          $(".indexJpg").mouseover(function(){
			  pauseSwitch();
			}).mouseout(function(){
			  goonSwitch();
			});
           startSwitcher();
       });
       //-->     
</script>