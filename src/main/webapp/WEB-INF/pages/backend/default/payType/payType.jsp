<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>

<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<html>
    <head>
        <title>创建支付方式</title>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
        <script language="javascript">
    $.validator.setDefaults({
    });

    $(document).ready(function() {
    jQuery("#form1").validate({
            rules: {
            banner: {
                required: true,
                minlength: 5
            },
            url: "required"
        },
        messages: {
            banner: {
                required: "Please enter banner",
                minlength: "banner must consist of at least 5 characters"
            },
            url: {
                required: "Please provide a password"
            }
        }
    });
 
      $("#col1 tr").each(function(i){
      if(i>0){
         if(i%2 == 0){
             $(this).addClass('even');
         }else{    
              $(this).addClass('odd'); 
         }   
    }
     });   
         $("#col1 th").addClass('sortable'); 
});
</script>
</head>
    <body>
        <form action="${pageContext.request.contextPath}/admin/paytype/save" method="post" id="form1">
            <input id="payId" name="payId" value="${bean.payId}" type="hidden">
            <div align="center">
          <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/paytype/query">支付管理</a> &raquo; 创建支付方式</th></tr>
		    </thead>
		    </table>
            <table  align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建支付方式
                            </div>
                        </th>
                    </tr>
                </thead>
     <tr>
        <td>
          <div align="center">支付方式: <font color="ff0000">*</font></div>
       </td>
        <td>
                 <select id="payTypeId" name="payTypeId">
				  <ls:optionGroup type="select" required="true" cache="true" beanId="paymentProcessors"
	                 selectedValue="${bean.payTypeId}"/>
	            </select>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">合作身份者ID(Partner):</div>
       </td>
        <td>
           <input type="text" name="partner" id="partner" value="${bean.partner}" size="50" maxlength="100"/>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">安全检验码:</div>
       </td>
        <td>
           <input type="text" name="validateKey" id="validateKey" value="${bean.validateKey}" size="50" maxlength="50"/>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">签约账号:</div>
       </td>
        <td>
           <input type="text" name="sellerEmail" id="sellerEmail" value="${bean.sellerEmail}" size="50" maxlength="100"/>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">备注:</div>
       </td>
        <td>
           <input type="text" name="memo" id="memo" value="${bean.memo}" size="50" maxlength="100"/>
        </td>
      </tr>

                <tr>
                    <td colspan="2">
                        <div align="center">
                            <auth:auth ifAnyGranted="FA_PAY"><input type="submit" value="提交"/></auth:auth>
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='${pageContext.request.contextPath}/admin/paytype/query'" />
                        </div>
                    </td>
                </tr>
            </table>
    <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
     如果选择货到付款方式，则无需填写合作身份者ID，安全检验码和签约账号<br>
   </td><tr></table> 
           </div>
        </form>

    </body>
</html>

