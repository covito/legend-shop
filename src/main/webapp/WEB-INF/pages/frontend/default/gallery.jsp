<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
  <%@include file='/WEB-INF/pages/common/common.jsp'%>
  <%@include file='/WEB-INF/pages/common/taglib.jsp'%>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/theme/gallery/jquery.ad-gallery.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/theme/gallery/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/theme/gallery/jquery.ad-gallery.js"></script>
  <script type="text/javascript">
  var start_message = '<fmt:message key="start.message"/>';
  var stop_message = '<fmt:message key="stop.message"/>';
  $(function() {
    var galleries = $('.ad-gallery').adGallery();

  });
  </script>
  <title>${prod.name}</title>
</head>
<body style="text-align:center">
  <div id="container">
    <h2>${prod.name} <a href="${pageContext.request.contextPath}/views/${prod.prodId}">[<fmt:message key="order"/>]</a></h2>
    <div id="gallery" class="ad-gallery">
      <div class="ad-image-wrapper">
      </div>
      <div class="ad-controls">
      </div>
      <div class="ad-nav">
        <div class="ad-thumbs">
          <ul class="ad-thumb-list">
          <c:forEach items="${requestScope.prodPics}" var="pic"  varStatus="status">
            <li><a href="<ls:photo item='${pic.filePath}'/>">
                <img src="<ls:images item='${pic.filePath}' scale='3'/>" class="image${status.index+1}" width="65px" height="65px">
              </a></li>
          </c:forEach>
          </ul>
        </div>
      </div>
    </div>

  </div>
</body>
</html>