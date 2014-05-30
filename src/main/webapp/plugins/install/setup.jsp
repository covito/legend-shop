<%@ page contentType="text/html;charset=utf-8"%>

<html>
<head>
<%

%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎使用LegendShop - 高性能的JAVA网购平台</title>

<link href="s.css" rel="stylesheet" type="text/css">


<style type="text/css">
<!--
.STYLE2 {color: #999999}
#Layer1 {
	position:absolute;
	left:452px;
	top:126px;
	width:310px;
	height:56px;
	z-index:1;
}
#Layer2 {
	position:absolute;
	width:1px;
	height:1px;
	z-index:2;
}
.STYLE3 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
}
-->
</style>
<script>
function contactDB() {
	var form1 = document.getElementById("form1");
	var jdbc_username = form1["jdbc_username"].value;
	var jdbc_password = form1["jdbc_password"].value;
	var jdbc_url = "jdbc:mysql://"+form1["dbhost"].value+":"+form1["dbport"].value+"/"+form1["dbname"].value+"?characterEncoding=utf-8";
	var jdbc_driver = "com.mysql.jdbc.Driver";
	//alert(jdbc_username +" , " + jdbc_password +" , " + jdbc_url);
	document.checkNickForm.jdbc_username.value = jdbc_username;
	document.checkNickForm.jdbc_password.value = jdbc_password;
	document.checkNickForm.jdbc_url.value = jdbc_url;
	document.checkNickForm.jdbc_driver.value = jdbc_driver;
	document.getElementById("check_username_info").className = "WarningMsg";
	document.getElementById("check_username_info").innerHTML = "检测中，请稍等...";
	document.getElementById("checkNickForm").submit();
	return true;

}

function install(b,install)
{
	document.getElementById("Layer1").style.display="";
	document.checkNickForm.install_db.value = install;
	document.form1.install_db.value = install;
	document.getElementById("form1").submit();
	b.disabled=true;
	b.value="正在处理最后的安装……";
} 




</script>

</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<table width="98%"  align="center" cellpadding="3" cellspacing="0">
  <tr>
    <td width="87%" height="59" class="line"><img src="${pageContext.request.contextPath}/common/images/legendshop.gif"></td>
    <td width="13%" align="right" valign="middle" class="line"><CENTER>
      <span class="STYLE3">LegendShop ${LEGENDSHOP_VERSION}</span>
    </CENTER></td>
  </tr>
</table>
<br>
<table width="98%"  align="center" cellpadding="3" cellspacing="0">
	  <iframe id="frameCheckForm" name="frameCheckForm" src="about:blank" width="0" height="0" scrolling="no"></iframe>
  <form id="checkNickForm" name=checkNickForm action="testdb.jsp" method="post" target="frameCheckForm">
    <input type="hidden" name="jdbc_username">
	<input type="hidden" name="jdbc_password">
	<input type="hidden" name="jdbc_url">
	<input type="hidden" name="jdbc_driver">
	<input type="hidden" name="install_db">
  </form>
  <tr>
    <td width="21%" align="left" valign="top"><table width="162"  cellpadding="3" cellspacing="1" bgcolor="#999999">
      <tr>
        <td width="215" bgcolor="#FFFFFF"><table width="163"  cellspacing="0" cellpadding="5">
          <tr>
            <td width="217" align="center" valign="middle" bgcolor="#eeeeee" class="line"><strong>安装步骤</strong></td>
          </tr>
          <tr>
            <td><img src="imgs/select.gif" width="14" height="13" align="absmiddle"> 设置系统域名</td>
          </tr>
          <tr>
            <td ><img src="imgs/20050129171621546.gif" align="absmiddle"> 配置数据库帐号</td>
          </tr>
          <tr>
            <td><span class="STYLE2">导入数据</span></td>
          </tr>
          <tr>
            <td><span class="STYLE2">安装完成</span></td>
          </tr>
        </table></td>
      </tr>
    </table>
	<div id="Layer2">
<div id="Layer1" style="display:none">
<div style="border:1px solid #000000;">
  <table width="300" height="56"  cellpadding="4" cellspacing="0">
    <tr>
      <td width="60" height="56" align="center" valign="middle" bgcolor="#FFFFFF"><img src="imgs/installing.gif" width="60" height="60"></td>
      <td width="204" bgcolor="#FFFFFF" style="font-size:12px;">&nbsp;&nbsp;请勿刷新页面，正在导入数据库......</td>
    </tr>
  </table>
  </div>
</div>
</div>
	</td>
    <td width="79%" align="left" valign="top"><table width="778"  cellpadding="3" cellspacing="0">

      <tr>
        <td width="760">
<form id="form1" name="form1" method="post" action="finish.jsp">
	<input id="domainName" name="domainName" type="hidden" value="<%=request.getParameter("domainName") %>"/>
	<input type="hidden" name="install_db">
<fieldset >
    <legend style="font-size:15px;padding-left:5px;padding-right:5px;font-weight:bold">数据库配置</legend>
	<table width="95%"  align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td align="left" style="line-height:20px;"><br>

		        <table width="100%"  cellpadding="7" cellspacing="1" bgcolor="#FF0000">
          <tr> 
            <td bgcolor="#FFFFCC"><img src="imgs/standard_msg_warning.gif" width="16" height="16" align="absmiddle"> 
              设置好数据参数后，请先执行“开始检测数据库”，只有通过了检测，才能继续进行最后一步安装</td>
          </tr>
        </table>		</td>
      </tr>
      <tr>
        <td align="left" style="line-height:20px;">
<br>
数据库名称 <br>
        <font color="#999999">- 请先手工创建一个空的数据库，并把名字写在下面</font> <br>
        <input name="dbname" type="text" class="inp" id="dbname" value="legendshop" size="30">
        <br>
        <br>
        数据库地址 <br>
        <font color="#999999">- 如果系统跟数据库在同一台机器上，一般填写localhost或127.0.0.1，如果数据库在远程，请把数据库的IP填上</font><br>
        <input name="dbhost" type="text" class="inp" id="dbhost" value="localhost" size="30">
        <br>
        <br>
        数据库连接端口<br>
        <font color="#999999">- mysql安装默认为3306，但有些主机提供商出于安全考虑，会修改为其它值</font><br>
        <input name="dbport" type="text" class="inp" id="dbport" value="3306" size="30">
        <br>
        <br>
        连接数据库帐号 <br>
        <input name="jdbc_username" type="text" class="inp" id="jdbc_username" value="root" size="30">
         <br>
        <br>
        连接数据库密码 <br>
        <input name="jdbc_password" type="text" class="inp" id="jdbc_password" value="root" size="30">
        <br>
        <br>
        <br>
        <table width="100%"  cellspacing="0" cellpadding="1">
          <tr>
            <td bgcolor="#666666">
<table width="100%" height="40"  cellpadding="5" cellspacing="0" bgcolor="#FFFFCC">
                <tr> 
                  <td width="14%">检测结果： </td>
                  <td width="86%" height="40"><span id="check_username_info">&nbsp;</span></td>
                </tr>
                <tr> 
                  <td>返回信息：</td>
                  <td  style='word-break:break-all;'><span id="desc">&nbsp;</span></td>
                </tr>
                <tr> 
                  <td colspan="2"><input type="button" name="Submit2" value="开始检测数据库" onClick="contactDB();">
                  </td>
                </tr>
              </table> </td>
          </tr>
        </table>
        <br>		</td>
      </tr>
      <tr>
        <td align="left" style="line-height:20px;">&nbsp;		</td>
      </tr>
    </table>
</fieldset> 

        <br>
        <br>
        <input name="Submit3" type="button" class="btn" onClick="history.back();" value=" 上一步 ">
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
		            <input type="button" name="Submit" value="导入数据库，完成安装" disabled id="nextb"  onClick="install(this,1)" style="height:30px;">
		            <input type="button" name="Submit" value="跳过数据库，直接安装" disabled id="nextc" onClick="install(this,0)" style="height:30px;">
		            </form>		  </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="center" valign="middle">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="2" align="center" valign="middle"><hr width="100%" size="1" noshade></td>
  </tr>
  <tr>
    <td colspan="2" align="center" valign="middle">LegendShop ${LEGENDSHOP_VERSION} - All Rights Reserved.</td>
  </tr>
</table>

</body>
</html>
