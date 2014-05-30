<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>创建TagMap</title>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.validate.js'/>" /></script>
        <link rel="stylesheet" type="text/css" media="screen" href="<ls:templateResource item='/common/css/errorform.css'/>" />
        <style type="text/css" media="all">
          @import url(<ls:templateResource item='/css/screen.css'/>);
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
        <form action="<ls:url address='/admin/tagMap/save'/>" method="post" id="form1">
            <input id="tagMapId" name="tagMapId" value="${tagMap.tagMapId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建TagMap
                            </div>
                        </th>
                    </tr>
                </thead>
     			     <tr>
        <td>
          <div align="center">TagId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="tagId" id="tagId" value="${tagMap.tagId}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">ReferId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="referId" id="referId" value="${tagMap.referId}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Type: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="type" id="type" value="${tagMap.type}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">StartTime: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="startTime" id="startTime" value="${tagMap.startTime}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">EndTime: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="endTime" id="endTime" value="${tagMap.endTime}" />
        </td>
      </tr>

                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="添加" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/tagMap/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>


