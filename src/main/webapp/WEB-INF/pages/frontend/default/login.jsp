<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@include file='/WEB-INF/pages/common/common.jsp'%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<script type="text/javascript" language="javascript" src="<ls:templateResource item='/common/js/randomimage.js'/>"></script>
		
	<link rel="stylesheet" type="text/css" media="screen" href="<ls:templateResource item='/common/default/css/errorform.css'/>" />
	 <script type="text/javascript" src="<ls:templateResource item='/common/js/jquery.superbox-min.js'/>"></script>
	 <link rel="stylesheet" href="<ls:templateResource item='/common/default/css/jquery.superbox.css'/>" type="text/css" media="all" />

	<script type="text/javascript">
		jQuery(function(){
			jQuery.superbox.settings = {
				closeTxt: "<fmt:message key="close"/>",
				loadTxt: "Loading...",
				nextTxt: "Next",
				prevTxt: "Previous"
			};
			jQuery.superbox();
		});
	</script>

    <table width="954px" cellpadding="0" cellspacing="0" class="tables">
                    <tr>
                  <td class="titlebg"><font style="font-size: 1.4em"><fmt:message key="login"/></font></td>
                </tr>
	<tr><td>
			<form name="loginform" action="${pageContext.request.contextPath}/p/j_spring_security_check" method="POST"  onsubmit="return checkRandNum();" >
			<input type="hidden" id="rand" name="rand"/>
			<input type="hidden" id="cannonull" name="cannonull" value='<fmt:message key="randomimage.errors.required"/>'/>
			<input type="hidden" id="charactors4" name="charactors4" value='<ls:i18n key="randomimage.charactors.required" length="4"/>'/>
			<input type="hidden" id="errorImage" name="errorImage" value='<fmt:message key="error.image.validation"/>'/>
			<input type="hidden" id="returnUrl" name="returnUrl" value="${param.returnUrl}"/>
            <table><tr><td>
            <table cellpadding="5px" > 
            		<tr>
						<td colspan="2" align="left">
						  <b><fmt:message key="username.password"/></b> 
						  <c:choose>
						  		<c:when test="${param.error == 1}"> <br><label class="error"><fmt:message key="error.password.noright"/></label></c:when>
						  		<c:when test="${param.error == 2}"> <br><label class="error"><fmt:message key="error.user.logined"/></label></c:when>
						  		<c:otherwise></c:otherwise>
						  </c:choose>
						</td>
					</tr>
					<tr>
						<td>
							<fmt:message key="user.name"/>
						</td>
						<td align="left"><input id="username" type='text' name="j_username" style="font-size:11pt; width: 180px"  class="inputbutton2" tabindex="1" value='<lb:lastLogingUser/>'/> </td>
					</tr>
					<tr>
						<td><fmt:message key="editForm.password"/></td>
						<td align="left"><input id="pwd" type='password' name='j_password' style="font-size:11pt; width: 180px"  autocomplete="off" class="inputbutton2" tabindex="2" onload="this.value=''"/></td>
					</tr>
					<lb:userValidationImage>
					<tr>
						<td><fmt:message key="validation.code"/></td>
						<td align="left"><input type="text" id="randNum" name="randNum" class="inputbutton2" maxlength="4"  style="font-size:11pt; width: 50px;" tabindex="3" >
						<!-- 
						<img id="randImage" name="randImage"/>
						 -->
						<img id="randImage" name="randImage" src="<ls:templateResource item='/captcha.svl'/>"  style="vertical-align: middle;"/>
 				&nbsp;<a href="javascript:void(0)" onclick="javascript:changeRandImg('${pageContext.request.contextPath}')" style="font-weight: bold;"><fmt:message key="change.random2"/></a>
						</td>
					</tr>	
					</lb:userValidationImage>	
					<tr>
						<td></td>
						<td><input name="submit" type="submit" value='<fmt:message key="login"/>' class="s" tabindex="4">
						<input name="reset" type="reset" value='<fmt:message key="reset.hint"/>' class="s" tabindex="5"></td>
					</tr>
				</table>
            </td>
            <td>
            	<table cellpadding="15px"><tr><td><fmt:message key="security.hint"/></td></tr></table>
            
            	<table cellpadding="2px" style="margin-left: 20px" align="left">
	            	<tr><td align="left"><img src="<ls:templateResource item='/common/default/images/004.gif'/>"/><fmt:message key="not.member.hint"/>,&nbsp;<a href="<ls:url address='/reg'/>"><fmt:message key="regFree"/></a></td></tr>
	            	<tr><td align="left"><img src="<ls:templateResource item='/common/default/images/004.gif'/>"/><fmt:message key="goto.home"/>&nbsp;<a href="<ls:url address='/index'/>"><fmt:message key="shop.index"/></a></td></tr>
	            	<tr><td align="left"><img src="<ls:templateResource item='/common/default/images/004.gif'/>"/><fmt:message key="forget.password.hint"/>
	            	<a href="<ls:url address='/resetpassword'/>" rel="superbox[iframe][330x230]">&nbsp;<fmt:message key="find.my.password"/></a>	   	
	            	</td>
	            	</tr>
            	</table>
            </td>
            </tr>
           </table>
			</form>
	</td>
	</tr>
	</table>
	<script type="text/javascript">	
	window.onload = function(){
	   if(window.top.location.href!=location.href)    
		{
    		window.top.location.href=location.href;    
		}

		var userName = document.getElementById("username").value;
		if(userName == null || userName =='' ){
			document.getElementById("username").focus();
		}else{
		    document.getElementById("pwd").focus();
		}
    }
	</script>
