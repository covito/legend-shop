<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<html>
    <head>
        <title>回复商品评论</title>
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
            replyContent: {
                required: true,
                minlength: 5,
                maxlength:300
            }
        },
        messages: {
            replyContent: {
                required: "请输入回复内容",
                minlength: "最少不能少于5个字",
                maxlength: "最大不能超过300字"
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
        <form action="${pageContext.request.contextPath}/admin/productcomment/save" method="post" id="form1">
            <input id="id" name="id" value="${bean.id}" type="hidden">
            <div align="center">
            <table  align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                回复商品评论
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td>
          <div align="center">商品</div>
       </td>
        <td>
           ${bean.prodName}
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">评价人</div>
       </td>
        <td>
           ${bean.userName}
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">评论内容</div>
       </td>
        <td>
           ${bean.content}
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">评论时间</div>
       </td>
        <td>
           <fmt:formatDate value="${bean.addtime}" pattern="yyyy-MM-dd HH:mm"/>
        </td>
      </tr>
         <tr>
        <td>
        <div align="center">评论IP</div>
       </td>
        <td>
           ${bean.postip}
        </td>
      </tr>
      <c:if test="${bean.replyName != null }">
       <tr>
        <td>
        <div align="center">回复</div>
       </td>
        <td>
           回复：${bean.replyName}
           回复时间： <fmt:formatDate value="${bean.replyTime}" pattern="yyyy-MM-dd HH:mm"/>
        </td>
      </tr>
      </c:if>
      <auth:auth ifAnyGranted="FA_PROD">
     <tr>
        <td>
          <div align="center">回复内容 <font color="ff0000">*</font></div>
       </td>
        <td>
           <textarea rows="5" cols="50" id="replyContent" name="replyContent">${bean.replyContent}</textarea>
        </td>
      </tr>
                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="提交" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='${pageContext.request.contextPath}/admin/productcomment/query'" />
                        </div>
                    </td>
                </tr>
                </auth:auth>
            </table>
           </div>
        </form>
    </body>
</html>

