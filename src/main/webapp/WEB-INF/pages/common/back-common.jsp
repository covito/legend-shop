<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.legendshop.core.UserManager"%>
<%@page import="com.legendshop.core.helper.ThreadLocalContext"%>
<%@page import="com.legendshop.model.entity.ShopDetailView"%>
<%@page import="com.legendshop.model.entity.ShopDetail"%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
	<lb:shopDetail var="shopDetailView" />
<link href="${pageContext.request.contextPath}/common/css/css.css" rel="stylesheet" type="text/css" />
     <style type="text/css" media="all">
       @import url("${pageContext.request.contextPath}/common/default/css/screen.css");
     </style>
<jsp:scriptlet> 
	 String lClass = "";
	 ShopDetailView shopDetail =  ThreadLocalContext.getShopDetailView(UserManager.getUserName(request));
	 if(shopDetail != null){
	 	lClass = shopDetail.getBackEndStyle();
	 }
   if( request.getParameter( "class" ) != null ) {
      lClass = request.getParameter( "class" );
      if (!("its".equals(lClass) || "mars".equals(lClass) || "simple".equals(lClass) || "report".equals(lClass) || "mark".equals(lClass)))
      {
        lClass="simple";
      }
   }
   
   if(lClass == null || lClass == ""){
   		lClass = "simple";
   }
   pageContext.setAttribute("tableclass", lClass);
</jsp:scriptlet>
	<script type="text/javascript">	
	function checkFrame(){
	   if(top==this)    
		{
    		 window.location.href = "${pageContext.request.contextPath}/admin/index";
		}
    }
    
    checkFrame();
	</script>