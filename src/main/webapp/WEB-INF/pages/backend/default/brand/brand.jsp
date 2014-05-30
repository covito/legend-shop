<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html>
    <head>
        <title>创建品牌</title>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
         <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
        <script language="javascript">
    $.validator.setDefaults({
    });

    $(document).ready(function() {
    jQuery("#form1").validate({
            rules: {
            brandName: {
                required: true
            },
            seq: {
              number: true
            }
        },
        messages: {
            brandName: {
                required: "请输入品牌"
            },
            seq: {
                number: "请输入数字"
            }
        }
    });
 
  			highlightTableRows("col1");
          //斑马条纹
     	   $("#col1 tr:nth-child(even)").addClass("even");	
});
</script>
</head>
    <body>
        <form action="${pageContext.request.contextPath}/admin/brand/save" method="post" id="form1" enctype="multipart/form-data">
        <table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr><th>
	    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; 
	    	<a href="${pageContext.request.contextPath}/admin/brand/query">品牌管理</a> &raquo;
	    	<a href="<ls:url address='/admin/brand/load'/>"/>创建品牌</a>
	    	</th></tr>
	    </thead>
	    </table>
            <input id="brandId" name="brandId" value="${bean.brandId}" type="hidden">
            <div align="center">
            <table  align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建品牌
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td>
          <div align="center">品牌 <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="brandName" id="brandName" value="${bean.brandName}" maxlength="30"/>
        </td>
      </tr>
      <tr>
        <td>
          <div align="center">上传图片</div>
       </td>
        <td>
                <input type="file" name="file" id="file"/>
				<input type="hidden" name="brandPic" id="brandPic" value="${bean.brandPic}" />  
        </td>
      </tr>
        <tr>
        <td>
          <div align="center">次序</div>
       </td>
        <td>
           <input type="text" name="seq" id="seq" value="${bean.seq}"   maxlength="5" size="5"/>
        </td>
      </tr>
      <tr>
        <td>
          <div align="center">备注</div>
       </td>
        <td>
           <input type="text" name="memo" id="memo" value="${bean.memo}" size="31" maxlength="50"/>
        </td>
      </tr>
      	<c:if test="${bean.brandPic!=null}">
					<tr>
						<td>
							<div align="center">
								原有图片
							</div>
						</td>
						<td>
								<a href="<ls:photo item='${bean.brandPic}'/>" target="_blank"> <img src="<ls:photo item='${bean.brandPic}'/>" height="65"/></a>
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
                                onclick="window.location='${pageContext.request.contextPath}/admin/brand/query'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

