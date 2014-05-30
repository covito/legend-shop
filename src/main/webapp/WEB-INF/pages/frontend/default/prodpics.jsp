<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<script src="${pageContext.request.contextPath}/plugins/jqzoom/js/jquery.jqzoom-core.js" type="text/javascript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/jqzoom/css/jquery.jqzoom.css" type="text/css">
<link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/default/css/productpics.css" />
				<div class="jqzoom"  id="spec-n1">
					<a href="<ls:photo item='${prod.pic}'/>" class="jqzoom" rel='gal1'  title="${prod.name}" >
				      		<img width="350px" height="350px" src="<ls:images item='${prod.pic}' scale='0'/>" >
				      </a>
				 </div>
<c:if test="${requestScope.prodPics != null && fn:length(requestScope.prodPics) > 0}">
<div class="rollBox">
     <div class="LeftBotton" onmousedown="ISL_GoUp()" onmouseup="ISL_StopUp()" onmouseout="ISL_StopUp()"></div>
     <div class="Cont" id="ISL_Cont">
      <div class="ScrCont">
       <div id="List1">
       
        <!-- 图片列表 begin -->
        <c:forEach items="${requestScope.prodPics}" var="pics">
         <div class="pic">
          	<a href="${pageContext.request.contextPath}/productGallery/${prod.prodId}" target="_blank">
          	<img src="<ls:images item='${pics.filePath}' scale='3'/>" width="65" height="50" /></a>
         </div>
         </c:forEach>
        <!-- 图片列表 end -->
        
       </div>
       <div id="List2"></div>
      </div>
     </div>
     <div class="RightBotton" onmousedown="ISL_GoDown()" onmouseup="ISL_StopDown()" onmouseout="ISL_StopDown()"></div>
    </div>
     <script type="text/javascript" src="${pageContext.request.contextPath}/common/default/js/productpics.js"></script>
 </c:if>
 <script type="text/javascript">
jQuery(document).ready(function() {
	jQuery('.jqzoom').jqzoom({
            zoomType: 'standard',
            lens:true,
            preloadImages: false,
            alwaysOn:false,
            zoomWidth:400,
            zoomHeight:400
        });
        jQuery('.zoomPad').css('z-index','auto');
});
</script>