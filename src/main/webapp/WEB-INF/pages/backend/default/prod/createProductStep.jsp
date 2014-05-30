<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
 <script type="text/javascript"  src="${pageContext.request.contextPath}/common/js/common.js"></script>
 <%@ include file="/WEB-INF/pages/common/dialog.jsp"%>
      <c:choose>
      	<c:when test="${prod.prodId == null}">
      		商品详细信息
      	</c:when>
      	<c:otherwise>
      	  <a href="${pageContext.request.contextPath}/admin/product/update/${prod.prodId}">
      	  <c:choose>
      	  	<c:when test="${param.step == 1}"><b>1.商品详细信息</b></c:when>
      	  	<c:otherwise>1.商品详细信息</c:otherwise>
      	  </c:choose>
      	 </a> 
	      <a href="${pageContext.request.contextPath}/admin/imgFile/query?productId=${prod.prodId}">
	           	  <c:choose>
		      	  	<c:when test="${param.step == 2}"><b>2.商品图片</b></c:when>
		      	  	<c:otherwise>2.商品图片</c:otherwise>
		      	  </c:choose> 
	      
	      </a>  
	      <a href="${pageContext.request.contextPath}/admin/dynamic/loadAttribute/${prod.prodId}">
	     		  <c:choose>
		      	  	<c:when test="${param.step == 3}">
			      	  	<c:choose>
							<c:when test="${DYNAMIC_TYPE == 1}"><b>3.动态属性</b></c:when>
							<c:otherwise>3.动态属性</c:otherwise>
						</c:choose>
		      	  	</c:when>
		      	  	<c:otherwise>3.动态属性</c:otherwise>
		      	  </c:choose>  
	      </a> 
	      <a href="${pageContext.request.contextPath}/admin/dynamic/loadParameter/${prod.prodId}">
	     	    <c:choose>
		      	  	<c:when test="${param.step == 3}">
			      	  	<c:choose>
						<c:when test="${DYNAMIC_TYPE == 2}"><b>4.动态参数</b></c:when>
						<c:otherwise>4.动态参数</c:otherwise>
						</c:choose>
					</c:when>
		      	  	<c:otherwise>4.动态参数</c:otherwise>
		      	</c:choose>   
	      
	      </a> 
	      <a id="relProdSetp" href="javascript:createprodStep.relProduct(${prod.prodId})">
	      	    <c:choose>
		      	  	<c:when test="${param.step == 5}"><b>5.相关商品</b></c:when>
		      	  	<c:otherwise>5.相关商品</c:otherwise>
		      	  </c:choose>  
	      </a>

      	</c:otherwise>
      </c:choose>
         
 <script language="JavaScript" type="text/javascript">
	function appendProduct(id){
	    var win = openWindow("${pageContext.request.contextPath}/admin/product/append/"+id , "appendProduct",'500','450') ;
	    win.focus() ;
	}
	
  var createprodStep={
       relProduct:function(id){
           var url="${pageContext.request.contextPath}/admin/product/append/"+id ;
		   var options={id:"relProduct",title:"增加相关商品",
		   follow: document.getElementById('relProdSetp'),
		   width:500,height:450,lock:false,closeFn: function(){alert(1)} };
		    art.dialog.open(url,options);
       }
  };	

</script>