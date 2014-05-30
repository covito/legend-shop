<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>

<html>
    <head>
        <title>修改供应商密码</title>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.validate.js'/>" /></script>
        <link rel="stylesheet" type="text/css" media="screen" href="<ls:templateResource item='/common/default/css/errorform.css'/>" />
         <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
</head>
    <body>    
	<table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr>
		    	<th>
			    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
			    	<a href="<ls:url address='/admin/partner/query'/>">供应商管理</a>
		    	</th>
	    	</tr>
	    </thead>
    </table>
        <form action="<ls:url address='/admin/partner/savePassword'/>" method="post" id="form1">
            <input id="partnerId" name="partnerId" value="${partner.partnerId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">修改供应商密码</div>
                        </th>
                    </tr>
                </thead>
     			     <tr>
        <td>
          <div align="center">供应商登 <font color="ff0000">*</font></div>
       </td>
        <td>
           ${partner.partnerName}
        </td>
      </tr>
     <tr>
        <td>
          <div align="cer">登录密码: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="password" name="password" id="password" />
        </td>
      </tr>
     <tr>
        <td>
          <div an="center">确认密码: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="password" name="passwordag" id="passwordag" />
        </td>
      </tr>
                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="保存" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/partner/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
        <script language="javascript">
		    $.validator.setDefaults({
		    });

		    $(document).ready(function() {
		        jQuery("#form1").validate({
		            rules: {
		                password: {
		                    required: true,
		                },
		                passwordag: {
		                    required: true,
		    		        equalTo:"#password"
		                }
		            },
		            messages: {
		                password: {
		                    required: '<fmt:message key="password.required"/>',
		                },
		                passwordag: {
		                    required: '<fmt:message key="password.required"/>',
		                    equalTo: '<fmt:message key="password.equalTo"/>'
		                }
		            }
		        });
		         //斑马条纹
     	  		 $("#col1 tr:nth-child(even)").addClass("even");
		         highlightTableRows("col1");  
		    });
</script>
    </body>
</html>


