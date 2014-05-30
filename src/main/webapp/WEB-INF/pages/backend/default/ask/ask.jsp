<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>创建Ask</title>
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
        <form action="<ls:url address='/admin/ask/save'/>" method="post" id="form1">
            <input id="askId" name="askId" value="${ask.askId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建Ask
                            </div>
                        </th>
                    </tr>
                </thead>
     			     <tr>
        <td>
          <div align="center">UserId: <font cr="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="userId" id="userId" value="${ask.userId}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">UserName:ont color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="userName" id="userName" value="${ask.userName}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center"><font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="prodId" id="prodId" value="${ask.prodId}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align=nter">ShopId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="shopId" id="shopId" value="${ask.shopId}" />
        </td>
      </tr>
     <tr>
        <td>
          < align="center">Type: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="type" id="type" value="${ask.type}" />
        </td>
      </tr>
     <tr>
        <td>
       <div align="center">Content: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="content" id="content" value="${ask.content}" />
        </td>
      </tr>
     <tr>
        <td>         <div align="center">Comment: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="comment" id="comment" value="${ask.comment}" />
        </td>
      </tr>
     <tr>
       d>
          <div align="center">CreateTime: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="createTime" id="createTime" value="${ask.createTime}" />
        </td>
      </tr>
     <tr>       <td>
          <div align="center">ReplyTime: <font color="ff0000">*</font></div>
       </td>
        <td>
           <p><input type="text" name="replyTime" id="replyTime" value="${ask.replyTime}" />
        </td>
      </tr>

                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="添加" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/ask/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>


