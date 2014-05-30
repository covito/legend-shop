<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    	<title><fmt:message key="find.my.password"/></title>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
		<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/common/js/randomimage.js"></script>
	<script type="text/javascript">
		function reset(){
			if(validateRandNum('${pageContext.request.contextPath}')){
			var resetpass = document.getElementById("resetpass");
			var sendMail = document.getElementById("sendMail");
			var userName = document.getElementById("userName").value;
			var userMail = document.getElementById("userMail").value;
		$.post("${pageContext.request.contextPath}/resetPassword" , 
	        {
            "userName": userName,
            "userMail": userMail
              },
        function(retData) {
	        if(retData !=null){
	        	 alert('<fmt:message key="check.parameter"/>');	
	        }else{
	        	resetpass.style.display = "none";
				sendMail.style.display = "block";
	        }    
		},'json');
		    
			}
		}
	</script>
  </head>
  <body>
  	        <input type="hidden" id="rand" name="rand"/>
			<input type="hidden" id="cannonull" name="cannonull" value='<fmt:message key="randomimage.errors.required"/>'/>
			<input type="hidden" id="charactors4" name="charactors4" value='<ls:i18n key="randomimage.charactors.required" length="4"/>'/>
			<input type="hidden" id="errorImage" name="errorImage" value='<fmt:message key="error.image.validation"/>'/>
    <table class="tables" id="resetpass" cellpadding="0" cellspacing="0">
        	<tr height="25px">
    		<td colspan="2" class="titlebg"><div style="font-size: 16px;font-weight: bold;"><fmt:message key="find.my.password"/></div><br></td>
    		</td>
    	</tr>
    	<tr height="25px">
    		<td><fmt:message key="user.name"/>：</td>
    		<td><input type="text" maxlength="50" id="userName" name="userName"/>
    		</td>
    	</tr>
    	<tr height="25px">
    	   <td><fmt:message key="user.email"/>：</td>
    		<td><input type="text" maxlength="50" id="userMail" name="userMail"/>
    		</td>
    	</tr>
    	<tr height="25px">
    	   <td><fmt:message key="validation.code"/>：</td>
    		<td>
    			
		<input type="text" id="randNum" name="randNum" class="inputbutton2" maxlength="50" tabindex="3" >
    		</td>
    	</tr>    	
		<tr height="25px">
			<td></td>
			<td>
			<img id="randImage" name="randImage" src="<ls:templateResource item='/captcha.svl'/>"/>
			&nbsp;<a href="javascript:void(0)" onclick="javascript:changeRandImg('${pageContext.request.contextPath}')" style="font-weight: bold;"><fmt:message key="change.random2"/></a>
			</td>
		</tr>

    	<tr height="25px"><td colspan="2" align="center"><input type="submit" value='<fmt:message key="submit"/>' onclick="reset()"/></td></tr>
    </table>
    <table id="sendMail" style="display: none">
    	<tr><td><br><br><fmt:message key="reset.mail.sended"/></td></tr>
    </table>
  </body>
</html>
