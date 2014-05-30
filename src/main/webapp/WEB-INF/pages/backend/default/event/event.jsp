<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>系统日志</title>
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
        <form action="<ls:url address='/admin/event/save'/>" method="post" id="form1">
            <input id="eventId" name="eventId" value="${event.eventId}" type="hidden">
            <table  align="center" class="${tableclass}" id="col1" style="width:100%">
                <thead>
                    <tr class="sortable">
                        <th>
                            <div align="center">
                               数据
                            </div>
                        </th>
                    </tr>
                </thead>

     	<tr>
        <td>
        <div> 操作人：${event.modifyUser}，用户姓名：${event.userName}，对象：${event.type}，类型 ：${event.operation }， 时间：${event.createTime} </div>
          <div>
          <textarea style="width:100%; height:380px">${event.relateData}</textarea>
          </div>
       </td>
      </tr>    
       <tr>
                    <td>
                        <div align="center">
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/event/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>


