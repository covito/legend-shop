<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.legendshop.spi.constants.SysParamGroupEnum"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<html>
    <head>
        <title>创建系统参数</title>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
        <script language="javascript">

    $(document).ready(function() {
  		//斑马条纹
     	 $("#col1 tr:nth-child(even)").addClass("even");
});
</script>
</head>
    <body>
        <form action="${pageContext.request.contextPath}/admin/system/systemParameter/save" method="post" id="form1">
            <input id="name" name="name" value="${bean.name}" type="hidden">
            <div align="center">
            <table  align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                ${bean.name} 参数
                            </div>
                        </th>
                    </tr>
                </thead>
        <tr>
        <td>
          <div align="center">功能说明</div>
       </td>
        <td>
           
			${bean.memo}
	       
        </td>
      </tr>
      <tr>
        <td>
          <div align="center">参数值</div>
       </td>
        <td>
           
         <c:choose>
           	<c:when test="${bean.type == 'Selection' || bean.type == 'Boolean'}">
		       <select id="value" name="value">
				  <ls:optionGroup type="select" required="true" cache="true"
	                beanName="${bean.optional}" selectedValue="${bean.value}"/>
	            </select>
           	</c:when>
           	<c:when test="${bean.type == 'Password'}">
           		 <input type="password" name="value" id="value"  size="50" maxlength="200"/>
           	 </c:when>
           	<c:otherwise>
           		<input type="text" name="value" id="value" value="${bean.value}" size="50" maxlength="200"/>
           	</c:otherwise>
           </c:choose>
           
        </td>
      </tr>
                <tr>
                    <td colspan="2">
                        <div align="center">
	                         <auth:auth ifAnyGranted="F_SYSTEM">
	                            <input type="submit" value="提交" />
	                         </auth:auth>
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='javascript:history.go(-1)'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

