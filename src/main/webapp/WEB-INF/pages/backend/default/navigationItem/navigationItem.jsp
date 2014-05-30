<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>二级网站导航</title>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.validate.js'/>" /></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
        <script language="javascript">
		    $.validator.setDefaults({
		    });

    $(document).ready(function() {
    jQuery("#form1").validate({
            rules: {
            name: "required",
			 seq: {
              number: true
            },
            link: "required"
        },
        messages: {
           name:"请输入名字",
			seq: {
                number: "请输入数字"
            },
            link:"请输入链接"		
        }
    });
 
		//斑马条纹
     	 $("#col1 tr:nth-child(even)").addClass("even");
});
</script>
</head>
    <body>
        <form action="<ls:url address='/admin/system/navigationItem/save'/>" method="post" id="form1">
            <input id="naviId" name="naviId" value="${navigation.naviId}" type="hidden">
            <input id="itemId" name="itemId" value="${navigationItem.itemId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1" style="width: 100%">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                	二级网站导航
                            </div>
                        </th>
                    </tr>
                </thead>
		<tr>
		        <td style="width: 30%">
		          	<div align="right">名称: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<input type="text" name="name" id="name" value="${navigationItem.name}" />
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="right">连接: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<input type="text" name="link" id="link" value="${navigationItem.link}" style="width:450px"/>
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="right">顺序: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<input type="text" name="seq" id="seq" value="${navigationItem.seq}" />
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="right">状态: <font color="ff0000">*</font></div>
		       	</td>
		         <td>
		           <select id="status" name="status">
			<ls:optionGroup type="select" required="true" cache="true"
	               beanName="ONOFF_STATUS" selectedValue="${navigationItem.status}"/>
					</select>		
		        </td>
		</tr>
                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="提交" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/system/navigation/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

