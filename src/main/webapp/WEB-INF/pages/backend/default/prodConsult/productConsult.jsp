<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>

<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<html>
    <head>
        <title>回复商品咨询</title>
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
            answer: {
                required: true,
                minlength: 5,
                maxlength:300
            }
        },
        messages: {
            answer: {
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
        <form action="${pageContext.request.contextPath}/admin/productConsult/save" method="post" id="form1">
            <input id="consId" name="consId" value="${productConsult.consId}" type="hidden">
            <div align="center">
            <table  align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                回复商品咨询
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td>
          <div align="center">商品</div>
       </td>
        <td>
           ${productConsult.prodName}
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">咨询人</div>
       </td>
        <td>
           ${productConsult.askUserName}
        </td>
      </tr>
      <tr>
        <td>
          <div align="center">咨询类型</div>
       </td>
        <td>
        <ls:optionGroup type="label" required="false" cache="true"
	                beanName="CONSULT_TYPE" selectedValue="${productConsult.pointType}" defaultDisp=""/>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">咨询内容</div>
       </td>
        <td>
           ${productConsult.content}
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">咨询时间</div>
       </td>
        <td>
           <fmt:formatDate value="${productConsult.recDate}" pattern="yyyy-MM-dd HH:mm"/>
        </td>
      </tr>
         <tr>
        <td>
        <div align="center">评论IP</div>
       </td>
        <td>
           ${productConsult.postip}
        </td>
      </tr>
      <c:if test="${productConsult.ansUserName != null }">
       <tr>
        <td>
        <div align="center">回复</div>
       </td>
        <td>
           回复人：${productConsult.ansUserName}
           回复时间： <fmt:formatDate value="${productConsult.answertime}" pattern="yyyy-MM-dd HH:mm"/>
        </td>
      </tr>
      </c:if>
      	<auth:auth ifAllGranted="FA_PROD">
     <tr>
        <td>
          <div align="center">回复内容 <font color="ff0000">*</font></div>
       </td>
        <td>
           <textarea rows="5" cols="50" id="answer" name="answer">${productConsult.answer}</textarea>
        </td>
      </tr>
                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="提交" />
                            <input type="reset" value="重置" />
                            <c:choose>
                            	<c:when test="${productConsult.answer !=null}">
                            	   <input type="button" value="返回"
                                       onclick="window.location='${pageContext.request.contextPath}/admin/productConsult/query?replyed=1'" />
                            	</c:when>
                            	<c:otherwise>
                            	        <input type="button" value="返回"
                                       onclick="window.location='${pageContext.request.contextPath}/admin/productConsult/query?replyed=0'" />
                            	</c:otherwise>
                            </c:choose>

                        </div>
                    </td>
                </tr>
                </auth:auth>
            </table>
           </div>
        </form>
    </body>
</html>

