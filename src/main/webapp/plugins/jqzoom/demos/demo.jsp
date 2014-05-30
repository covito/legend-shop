<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" http://www.w3.org/TR/html4/loose.dtd>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<title>JQzoom 2 Demo</title>

<script src="${pageContext.request.contextPath}/plugins/jqzoom/js/jquery-1.6.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/plugins/jqzoom/js/jquery.jqzoom-core.js" type="text/javascript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/jqzoom/css/jquery.jqzoom.css" type="text/css">


<script type="text/javascript">

$(document).ready(function() {
	$('.jqzoom').jqzoom({
            zoomType: 'standard',
            lens:true,
            preloadImages: false,
            alwaysOn:false
        });
	
});


</script>
</head>

<body>
<div class="clearfix" id="content" style="margin-top:100px;margin-left:350px; height:500px;width:500px;" >
        <a href="imgProd/triumph_big1.jpg" class="jqzoom" rel='gal1'  title="triumph" >
            <img src="imgProd/triumph_small1.jpg"  title="triumph"  style="border: 4px solid #666;">
        </a>
	<br/>
</div>


</body></html>