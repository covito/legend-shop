<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.legendshop.central.install.DbInfo"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<%
	DbInfo result = (new com.legendshop.central.install.TestDB()).testDB(request);
%>
<script>

var result = <%=result.getResult()%>;
var desc = "<%=result.getDesc()%>";
var theP;
if(document.all)
{
	theP = parent.document;
}
else
{
	theP = parent.document;
}
//alert(result + ", " +theP);
//alert(theP.getElementById("check_username_info"));
if ( result==0 )
{
	theP.getElementById("check_username_info").innerHTML = "检测数据库成功，可以继续安装";
	theP.getElementById("desc").innerHTML = desc;
	theP.getElementById("check_username_info").className = "OkMsg";
	theP.getElementById("nextb").disabled = false;
	theP.getElementById("nextc").disabled = false;
}
else if( result==1 )
{
	theP.getElementById("check_username_info").innerHTML = "数据库版本低于4.1版本，不能安装本系统";
	theP.getElementById("desc").innerHTML = desc;
	theP.getElementById("check_username_info").className = "ErrorMsg";
	theP.getElementById("nextb").disabled = true;
	theP.getElementById("nextc").disabled = true;
}
else if( result==2 )
{
	theP.getElementById("check_username_info").innerHTML = "连接数据库失败，请检查数据库连接参数";
	theP.getElementById("desc").innerHTML = desc;
	theP.getElementById("check_username_info").className = "ErrorMsg";
	theP.getElementById("nextb").disabled = true;
	theP.getElementById("nextc").disabled = true;
}
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

</body>
</html>