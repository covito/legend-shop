<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<html>
    <head>
        <title>创建 ImgFile</title>
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
        <form action="${pageContext.request.contextPath}/admin/imgFile/save" method="post" id="form1">
            <input id="fileId" name="fileId" value="${bean.fileId}"  type="hidden"/>
             <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建 ImgFile
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td>
          <div align="center">UserName: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="userName" id="userName" value="${bean.userName}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">ProductId: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="productId" id="productId" value="${bean.productId}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">ProductType: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="productType" id="productType" value="${bean.productType}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">FilePath: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="filePath" id="filePath" value="${bean.filePath}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">FileType: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="fileType" id="fileType" value="${bean.fileType}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">FileSize: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="fileSize" id="fileSize" value="${bean.fileSize}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">UpoadTime: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="upoadTime" id="upoadTime" value="${bean.upoadTime}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">Status: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="status" id="status" value="${bean.status}" />
        </td>
      </tr>

                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="添加" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='${pageContext.request.contextPath}/admin/imgFile/query'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

