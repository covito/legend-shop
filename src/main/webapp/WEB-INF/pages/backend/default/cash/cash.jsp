<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>创建Cash</title>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.validate.js'/>" /></script>
        <link rel="stylesheet" type="text/css" media="screen" href="<ls:templateResource item='/common/default/css/errorform.css'/>" />
        <style type="text/css" media="all">
          @import url(<ls:templateResource item='/common/default/css/screen.css'/>);
        </style>
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
        <form action="<ls:url address='/admin/cash/save'/>" method="post" id="form1">
            <input id="cashId" name="cashId" value="${cash.cashId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建Cash
                            </div>
                        </th>
                    </tr>
                </thead>
     			     <tr>
        <td>
          <div align="center">UserId: <font cr="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="userId" id="userId" value="${cash.userId}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">UserName:ont color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="userName" id="userName" value="${cash.userName}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="centeCode: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="code" id="code" value="${cash.code}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align=nter">ShopId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="shopId" id="shopId" value="${cash.shopId}" />
        </td>
      </tr>
     <tr>
        <td>
          <dilign="center">ProdId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="prodId" id="prodId" value="${cash.prodId}" />
        </td>
      </tr>
     <tr>
        <td>
        div align="center">PartnerId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="partnerId" id="partnerId" value="${cash.partnerId}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">SubId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="subId" id="subId" value="${cash.subId}" />
        </td>
      </tr>
     <tr>
     <td>
          <div align="center">Detail: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="detail" id="detail" value="${cash.detail}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Money: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="money" id="money" value="${cash.money}" />
        </td>
      </tr>    <tr>
        <td>
          <div align="center">Status: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="status" id="status" value="${cash.status}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">BeginTime: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="beginTime" id="beginTime" value="${cash.beginTime}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">EndTime: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="endTime" id="endTime" value="${cash.endTime}" p>       </td>
      </tr>
     <tr>
        <td>
          <div align="center">Ip: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="ip" id="ip" value="${cash.ip}" > </td>
      </tr>
     <tr>
        <td>
          <div align="center">CreateTime: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="createTime" id="createTime" value="${cash.createTime}" p>
        </td>
      </tr>

                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="添加" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/cash/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>


