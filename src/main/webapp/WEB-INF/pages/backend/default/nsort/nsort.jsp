<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
 <%@ include file="/WEB-INF/pages/common/dialog.jsp"%>
<html>
    <head>
        <title>创建二级商品类型</title>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
        <script language="javascript">
    $.validator.setDefaults({
    });

    $(document).ready(function() {
    jQuery("#form1").validate({
            rules: {
            nsortName: "required",
            seq: {
              number:true
            }
        },
        messages: {
            nsortName: {
                required: "请输入分类名称"
            },
            seq: {
                number: "请输入数字"
            }
        }
    });
 
		//斑马条纹
     	 $("#col1 tr:nth-child(even)").addClass("even");
});
</script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
<script type="text/javascript">
	function appendBrand(id){
	    var win = openWindow("${pageContext.request.contextPath}/admin/nsort/appendBrand/" + id ,"appendBrand",'500','450') ;
	    win.focus() ;
	}
	
	  var nsort={
       appendBrand:function(id){
           var url="${pageContext.request.contextPath}/admin/nsort/appendBrand/"+id ;
		   var options={id:" appendBrand",title:"增加品牌",
		  // follow: document.getElementById('relProdSetp'),
		   width:500,height:450,lock:false,closeFn: function(){} };
		    art.dialog.open(url,options);
       }
  };	
</script>

</head>
    <body>
        <form action="${pageContext.request.contextPath}/admin/nsort/save" method="post" id="form1">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; 
    	<a href="${pageContext.request.contextPath}/admin/sort/query">类型管理</a> &raquo; 
    	<a href="${pageContext.request.contextPath}/admin/nsort/query?sortId=${nsort.sortId}">二级商品类型管理</a> &raquo; 创建二级商品类型
    	<c:if test="${nsort.parentNsortId != null}">  &raquo; 创建三级商品类型</c:if>
    	</th></tr>
    </thead>
    </table>
            <input id="nsortId" name="nsortId" value="${nsort.nsortId}" type="hidden">
            <input id="sortId" name="sortId" value="${nsort.sortId}" type="hidden">
            <input id="parentNsortId" name="parentNsortId" value="${nsort.parentNsortId}" type="hidden">
            <div align="center">
            <table  align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                            <c:choose>
                            	<c:when test="${nsort.parentNsortId == null}">创建二级商品类型</c:when>
                            	<c:otherwise>创建三级商品类型</c:otherwise>
                            </c:choose>
                          
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td>
          <div align="center">商品类型: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="nsortName" id="nsortName" value="${nsort.nsortName}" />
        </td>
      </tr>
      <tr>
        <td>
          <div align="center">排序(必须为数字)：</div>
       </td>
        <td>
           <input type="text" name="seq" id="seq" value="${nsort.seq}" />
        </td>
      </tr>
      <c:if test="${nsort.parentNsortId == null}">  
      <tr>
        <td>
          <div align="center">分类代表：</div>
       </td>
        <td>
                      
				<select id="sortDeputy" name="sortDeputy">
				  <ls:optionGroup type="select" required="true" cache="true"
	                beanName="YES_NO" selectedValue="${nsort.sortDeputy}"/>
	            </select>
           
        </td>
      </tr>
      </c:if>
      <c:if test="${nsort.parentNsortId != null && nsort.nsortId != null}">
      <tr>
        <td>
          <div align="center">品牌：</div>
       </td>
        <td>
           <a href="javascript:nsort.appendBrand(${nsort.nsortId})">增加品牌</a>&nbsp;还没有品牌？请先&nbsp;<a href="${pageContext.request.contextPath}/admin/brand/load">创建品牌</a>
        </td>
      </tr>
      </c:if>
      <tr>
                    <td colspan="2">
                        <div align="center">
                        <auth:auth ifAnyGranted="FA_SHOP">
                            <input type="submit" value="提交" />
                            </auth:auth>
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='${pageContext.request.contextPath}/admin/nsort/query?sortId=${nsort.sortId}'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

