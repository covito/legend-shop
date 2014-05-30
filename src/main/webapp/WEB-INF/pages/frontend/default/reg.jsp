<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
		<script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
<c:forEach items="${requestScope.USER_REG_ADV_950}" var="adv">
<table class="picstyle" cellpadding="0" cellspacing="0" style="margin-bottom: 5px">
  <tr><td><a href="${adv.linkUrl}"><img src="<ls:photo item='${adv.picUrl}'/>" title="${adv.title}" width="950px" /></a></td>
  </tr>
 </table>
</c:forEach>
   <table width="100%" cellspacing="0" cellpadding="0" class="tables"> 
                <tr>
                  <td class="titlebg" ><fmt:message key="register.title"/></td>
                </tr>
                <tr> 
                  <td height="2" bgcolor="#FFFFF6"><table width="100%" cellspacing="0" cellpadding="0" height="100%">
                      <tr> 
                        <td align="left">
                        <form action="${pageContext.request.contextPath}/userReg" method="post" name="userRegForm" id="userRegForm" enctype="multipart/form-data">
                        <div style="margin-left: 100px">
                        <UL>
	                        <fmt:message key="register.hint"/></UL>
	                       <c:forEach items="${User_Messages.callBackList}" var="callback">	
								<li>
									<font color="red">${callback.callBackTitle} ${callback.callBackDesc}</font>
								</li>
						</c:forEach>
                        </div>
                        <br>
                            <table width="100%" align="center" cellpadding="0" cellspacing="0" style="margin-bottom: 6" border="0">
                              <tr>
                                <td width="33%" height="29"><div align="right">
                               <font color="#ff0000">*</font><fmt:message key="user.name"/>： </div></td>
                                <td width="67%"><div align="left">
                                <input class="input" type="text" name="name" id="name" maxlength="15" value="${userForm.name}"/>
                                  <span id="userAreardyExists"></span>
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="29"><div align="right"><font color="#ff0000">*</font><fmt:message key="real.name"/>：</div></td>
                                <td height="29">
                                  <div align="left">
                                     <input class="input" type="text" name="nickName" id="nickName" size="20" value="${userForm.nickName}" maxlength="30">
                                  </div>
                                  </td>
                              </tr>
                              <tr> 
                                <td height="29"><div align="right">
                                <font color="#ff0000">*</font><fmt:message key="password"/>： </div></td>
                                <td height="29"><div align="left"> 
                                    <input class="input" type="password" name="password" id="password" size="20" maxlength="50">
                                  </div></td>
                              </tr>
                              <tr>
                                <td height="29"><div align="right"><font color="#ff0000">*</font><fmt:message key="Confirmation"/>： </div></td>
                                <td height="29"><div align="left">
                                    <input class="input" type="password" name="password2" id="password2" size="20" maxlength="50">
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="29"><div align="right"><font color="#ff0000">*</font><fmt:message key="user.email"/>：</div></td>
                                <td height="29"><div align="left"> 
                                    <input class="input" type="text" name="userMail" id="userMail" value="${userForm.userMail}" maxlength="50">
                                    <span id="mailAreardyExists"></span>
                                  </div></td>
                              </tr>
                              <c:if test="${supportOpenShop == 'true' && 'C2C' == applicationScope.BUSINESS_MODE}">
                               <tr>
	                              <td><br><div align="right"><fmt:message key="openShop"/>：</div>
	                              </td>
	                              <td><br><input type="checkbox" id="openShop" name="openShop" onclick="javascript:isOpenShop(this)" style="margin-left: 10px"/></td>
                              </tr>   
                              </c:if> 
                              <tr><td colspan="2" width="100%">
                              <div id="shopDetail" style="display: none">
                                <table width="100%"  cellpadding="0" cellspacing="0" >
                               <tr>
	                              <td colspan="2">
	                           <div style="margin-left: 150px">
			                        <fmt:message key="mall.register.required"/>
			                        <c:if test="${validationOnOpenShop == 'true'}">   
			                        <li><fmt:message key="mall.register.shop.required"/>：</li>
			                        </c:if>
                               </div>
                               <br>
	                              </td>
                              </tr>
                                <tr>
                                <td width="33%" height="29"><div align="right">
                                <font color="#ff0000">*</font><fmt:message key="mall.name"/>：</div></td>
                                <td width="67%" height="29"><div align="left">
                                    <input name="shopDetail.siteName" id="siteName" class="input"  maxlength="50"/>
                                  </div></td>
                              </tr>
                              <tr>
                                <td height="29"><div align="right"><font color="#ff0000">*</font><fmt:message key="idcard.number"/>：</div></td>
                                <td height="29"><div align="left"> 
                                    <input type="text" name="shopDetail.idCardNum" id="idCardNum" class="input" maxlength="20"  maxlength="50"/>
                                  </div></td>
                              </tr> 
                              <tr>
                                <td height="29"><div align="right">
                                <font color="#ff0000">*</font><fmt:message key="address"/>：</div></td>
                                <td height="29"><div align="left"> 
                                    <input style="WIDTH: 250px;" name="shopDetail.postAddr" id="postAddr" size="20" class="input"  maxlength="300"/>
                                  </div></td>
                              </tr>
                              <tr>
                                <td height="29"><div align="right"><font color="#ff0000">*</font><fmt:message key="mall.type"/>：</div></td>
                                <td height="29"><div align="left"> 
                                    &nbsp;&nbsp;<fmt:message key="type.business"/><input type="radio" checked="checked"  name="shopDetail.type" id="type" value="1" onclick="javascript:changeType(1)"/>&nbsp;
                                    <fmt:message key="type.individual"/> <input type="radio" name="shopDetail.type" value="0" onclick="javascript:changeType(0)"/>
                                  </div></td>
                              </tr>

                              <tr>
                                <td height="29"><div align="right"><font color="#ff0000">*</font><fmt:message key="upload.IDcard.pic"/>：</div></td>
                                <td height="29"><div align="left"> 
                                    <input type="file" style="WIDTH: 250px;margin-left: 10px" name="shopDetail.idCardPicFile" id="trafficPicFile" class="input" />
                                  </div></td>
                              </tr>
                              <tr>                            
                                <td height="29" colspan="2">
                                  <div id="trafficPicDiv" >
                                <table width="100%" cellpadding="0" cellspacing="0"><tr><td width="33%">
	                                 <div align="right"><font color="#ff0000">*</font><fmt:message key="upload.business.pic"/>：<br>
	                                </div>  
	                                </td>
	                                <td> <input type="file" style="WIDTH: 250px;margin-left: 10px;" name="shopDetail.trafficPicFile" id="trafficPicFile" class="input" /></td>
	                                </tr>
                                </table>
                    			</div>
                                </td>
                              </tr> 
                                </table>
                                </div>
                              </td></tr>
                              <tr> 
                                <td height="29"><div align="right"><fmt:message key="UserAgreement"/></div></td>
                                <td height="29"><br/>
                                  <textarea id="regItem" name="regItem" class="lgtext" style="width: 600px;height: 100px;font-size: 9pt">${regItem }</textarea>
                              </tr>                              
                              <tr>
                                <td height="35" colspan="2"><div align="center"> 
                                    <table width="100%" align="center" cellpadding="0" cellspacing="0">
                                      <tr>
                                        <td><div align="left"> 
                                            <input type="submit" value='<fmt:message key="submit"/>' class="s" style="margin-left: 250px"/>
                                            <input type="reset" value='<fmt:message key="reset.hint"/>' class="s">
                                          </div>
                                          </td>
                                </tr>
                                    </table>
                                  </div></td>
                              </tr>
                            </table>
                          </form>
                         </td>
                      </tr>
                    </table></td>
                </tr>
              </table>
              
 <script type="text/javascript">
	jQuery.validator.setDefaults({

	});

	/*** 检查是否由数字字母和下划线组成 ***/
	String.prototype.isAlpha = function() {
		return (this.replace(/\w/g, "").length == 0);
	}
	
	jQuery.validator.addMethod("stringCheck", function(value, element) {
     return value.isAlpha();}, '<fmt:message key="user.reg.username"/>'); 
	
   jQuery.validator.addMethod("checkName1", function(value, element) {
     return checkName(value);}, '<fmt:message key="errors.user.exists"><fmt:param value=""/></fmt:message>'); 
     
   jQuery.validator.addMethod("checkEmail1", function(value, element) {
     return checkEmail(value);}, '<fmt:message key="errors.email.exists"><fmt:param value=""/></fmt:message>'); 
	
	jQuery(document).ready(function() {
	jQuery("#userRegForm").validate({
		rules: {
			name: {
				required: true,
				minlength: 4,
				stringCheck: true,
				checkName1: true
			},
			nickName: "required",
			password: {
				required: true,
				minlength: 6
			},
			password2: {
				required: true,
				minlength: 6,
				equalTo: "#password"
			},
			userMail: {
				required: true,
				email: true,
				checkEmail1: true
			},
            "shopDetail.postAddr": {
				required: "#openShop:checked",
				minlength: 2
			},
		    "shopDetail.idCardNum": {
				required: "#openShop:checked",
				minlength: 15
			},
		    "shopDetail.type": {
				required: "#openShop:checked"
			},
		    "shopDetail.idCardPicFile": {
				required: "#openShop:checked"
			},			
		    "shopDetail.trafficPicFile": {
				required: "#openShop:checked",
				required: "#type:checked"
			}		
			
		},
		messages: {
            name: {
                required: '<fmt:message key="username.required"/>',
                minlength: '<fmt:message key="username.minlength"/>'
            },
            password: {
                required: '<fmt:message key="password.required"/>',
                minlength: '<fmt:message key="password.minlength"/>'
            },
            password2: {
                required: '<fmt:message key="password.required"/>',
                minlength: '<fmt:message key="password.minlength"/>',
                equalTo: '<fmt:message key="password.equalTo"/>'
            },
            nickName:{
               required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },
            userMail:{
               required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>',
               email: '<fmt:message key="errors.email"><fmt:param value=""/></fmt:message>'
            },
            userAdds:{
               required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },            
            "shopDetail.siteName": {
               required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>',
               minlength: '<fmt:message key="errors.minlength"><fmt:param value=""/><fmt:param value="2"/></fmt:message>'
            },   
            "shopDetail.postAddr":{
               required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>',
               minlength: '<fmt:message key="errors.minlength"><fmt:param value=""/><fmt:param value="2"/></fmt:message>'
            }, 
            "shopDetail.idCardNum": {
               required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>',
               minlength: '<fmt:message key="errors.minlength"><fmt:param value=""/><fmt:param value="15"/></fmt:message>'
            }, 
            "shopDetail.type": {
               required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },             
 		    "shopDetail.idCardPicFile":  {
				required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
			},		           
 		    "shopDetail.trafficPicFile": {
				required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
			}
        }
	});
	
	// check if confirm password is still valid after password changed
	jQuery("#password").blur(function() {
		jQuery("#password2").valid();
	});
	
	jQuery("#name").focus();
});

	// propose username by combining first- and lastname
	function checkName() {
		var result = true;
	var nameValue = jQuery("#name").val();
	if(nameValue!=null && nameValue!=''){
	if(nameValue.length >= 4 && nameValue.isAlpha() ){
			   $.ajax({
				url:"${pageContext.request.contextPath}/isUserExist", 
				data: {"userName":nameValue},
				type:'post', 
				async : false, //默认为true 异步   
				error: function(jqXHR, textStatus, errorThrown) {
			 		 //console.log(textStatus, errorThrown);
				},
				success:function(retData){
				    		if('true' == retData){
								result = false;
							  }
				}
				});
	      }
	    }
    return result;
	}
	
	function checkEmail() {
		var result = true;
	var userMailValue = jQuery("#userMail").val();
	if(userMailValue!=null && userMailValue!=''){
	if(isEmail(userMailValue)){
			       //call ajax action
			       $.ajax({
						url:  "${pageContext.request.contextPath}/isEmailExist", 
						data: {"email":userMailValue},
						type:'post', 
						async : false, //默认为true 异步   
						error: function(jqXHR, textStatus, errorThrown) {
					 		 //console.log(textStatus, errorThrown);
						},
						success:function(retData){
						 		if('true' == retData){
									result = false;
								  }
						}
						});
	     }
	    }
    return result;
	}


function isEmail(str){
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	return reg.test(str);
}

 function isOpenShop(obj){
 	if(obj.checked){
 		document.getElementById("shopDetail").style.display="block";
 	}else{
 	 	document.getElementById("shopDetail").style.display="none";
 	}
 }
		
   function changeType(obj){
 	if(obj == 1){
 		document.getElementById("trafficPicDiv").style.display="block";
 	}else{
 	 	document.getElementById("trafficPicDiv").style.display="none";
 	}
 }	
</script>
              