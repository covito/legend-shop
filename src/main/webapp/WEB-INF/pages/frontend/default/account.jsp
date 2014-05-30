<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="com.legendshop.core.helper.PropertiesUtil"%>
<%@page import="com.legendshop.core.constant.SysParameterEnum"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@include file='/WEB-INF/pages/common/common.jsp'%>

<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
		<script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
<style>
<!--
	.myaccount{background-color: #F6F6F6;height: 28px;font-weight: bold;}
-->
</style>
<script type="text/javascript">
<!--
 function changePass(obj){
 	if(obj.checked){
 		document.getElementById("pass1").style.display="block";
 	}else{
 	 	document.getElementById("pass1").style.display="none";
 	}
 }
 
 		//设值
		function setSelectVal(target, val)
		{
		    if($.browser.msie && $.browser.version=="6.0") {
		        setTimeout(function(){
		        	target.val(val);
		        },1);
		    }else {
		    	target.val(val);
		    }
		};
 
 jQuery.validator.setDefaults({

	});
	
	jQuery(document).ready(function() {
	jQuery("#userUpdateForm").validate({
		rules: {
			name: {
				required: true,
				minlength: 5
			},
			nickName: "required",
			passwordOld: {
				required: "#modifyPass:checked"
			},
			password: {
				required: "#modifyPass:checked",
				minlength: 6
			},
			password2: {
				required: "#modifyPass:checked",
				minlength: 6,
				equalTo: "#password"
			},
			userMail: {
				required: true,
				email: true
			},
			userAdds:{
               required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },            
            userMobile:{
               required: true,
               minlength: 8              
            },
            userAdds: {
			  required: true
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
			passwordOld:{
			   required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
			},
			userMobile:{
               required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>',
               minlength: '<fmt:message key="errors.minlength"><fmt:param value=""/><fmt:param value="8"/></fmt:message>',
               number: '<fmt:message key="errors.phone"><fmt:param value=""/></fmt:message>'
            },
            userMail:{
               required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>',
               email: '<fmt:message key="errors.email"><fmt:param value=""/></fmt:message>'
            },
            fax:{
               required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },
            userPostcode:{
               required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },
            userAdds:{
              required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>',
              minlength: '<fmt:message key="errors.minlength"><fmt:param value=""/><fmt:param value="3"/></fmt:message>'
            }
		}
	});
	
	// propose username by combining first- and lastname
	jQuery("#name").blur(function() {
	if(jQuery("#name").val()!=null&&jQuery("#name").val()!=''){
	if(jQuery("#name").val().length>=6){
		       $.ajax({
				url:"${pageContext.request.contextPath}/isUserExist", 
				data: {"userName":jQuery("#name").val()},
				type:'post', 
				async : false, //默认为true 异步   
				error: function(jqXHR, textStatus, errorThrown) {
			 		 //console.log(textStatus, errorThrown);
				},
				success:function(retData){
				       if('true' == retData){
				          alert("alerm:user "+jQuery("#name").val()+" had exist!") ;
				          return false;
				       }else{
			
				       }
				}
				});
	    }
	    }
	});
	
	// check if confirm password is still valid after password changed
	jQuery("#password").blur(function() {
		jQuery("#password2").valid();
	})
});

//-->
</script>
<c:forEach items="${requestScope.USER_REG_ADV_740}" var="adv">
<table width="100%" cellpadding="0" cellspacing="0" style="margin-bottom: 5px" class="picstyle">
<tr><td><a href="${adv.linkUrl}"><img src="<ls:photo item='${adv.picUrl}'/>" title="${adv.title}"/></a></td></tr>
 </table>
</c:forEach>
<table width="100%" cellpadding="0" cellspacing="0" style="margin-bottom: 5px" class="tables">
<tr >
	<td class="myaccount"><fmt:message key="order.processing"/></td><td>${totalProcessingOrder}&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/p/order">[<fmt:message key="Query.Order.Status"/>]</a></td>
	<td class="myaccount"><fmt:message key="product.subscribed.list"/></a></td><td>${totalBasketByuserName}&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/p/buy">[<fmt:message key="shopingCar"/>]</a></td>
		<td class="myaccount">
		<%
			if(PropertiesUtil.getObject(SysParameterEnum.USE_SCORE, Boolean.class)){
		%>
			<fmt:message key="score.total"/>
		<%
			}
		%>
		</td><td>
		<%
			if(PropertiesUtil.getObject(SysParameterEnum.USE_SCORE, Boolean.class)){
		%>
			${user.score}
		<%} %>
		</td>
</tr>
<tr >
	<td class="myaccount"><fmt:message key="register.date"/></td><td><fmt:formatDate value="${user.userRegtime}" pattern="yyyy-MM-dd"/></td>
	<td class="myaccount"><fmt:message key="last.login.date"/></td><td><fmt:formatDate value="${user.userLasttime}" pattern="yyyy-MM-dd"/></td>
	<td class="myaccount"><fmt:message key="last.login.ip"/></td><td>${user.userLastip}</td>
</tr>
 </table>
   <table width="100%" cellspacing="0" cellpadding="0" class="tables">
                <tr> 
                  <td class="titlebg"><fmt:message key="myaccount"/></td>
                </tr>
                <tr> 
                  <td height="2"> <table width="100%" cellspacing="0" cellpadding="0" height="100%">
                      <tr>
                        <td align="left"> 
                        <form action="${pageContext.request.contextPath}/updateAccount" method="post" name="userUpdateForm" id="userUpdateForm">
                        <input type="hidden" id="userId" name="userId" value="${user.userId}">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <UL>
						<fmt:message key="register.hint"/></UL><br>
                            <table width="100%" align="center" cellpadding="0" cellspacing="0" style="margin-bottom: 6">
                              <tr> 
                                <td width="33%" height="29"><div align="right">
                               <font color="#ff0000">*</font><fmt:message key="user.name"/>：</div></td>
                                <td width="67%"><div align="left">
                                <c:choose>
                                	<c:when test="${param.userName == null}">
                                		<input style="WIDTH: 200px; color: #949492" type="text" name="userName" id="userName" size="20" class=input value="${sessionScope.SPRING_SECURITY_LAST_USERNAME}" readonly="readonly">
                                	</c:when>
                                	<c:otherwise>
                                <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
                                    <input style="WIDTH: 200px; color: #949492" type="text" name="userName" id="userName" size="20" class=input value="${param.userName}" readonly="readonly">
                                </auth:auth>
                                <auth:auth ifNotGranted="F_VIEW_ALL_DATA">
                               		 <input style="WIDTH: 200px; color: #949492" type="text" name="userName" id="userName" size="20" class=input value="${sessionScope.SPRING_SECURITY_LAST_USERNAME}" readonly="readonly">
                                </auth:auth>
                                	</c:otherwise>
                                </c:choose>
                                   <c:if test="${supportOpenShop == 'true' && 'C2C' == applicationScope.BUSINESS_MODE}">     
                                 &nbsp; 
                                  <c:choose>
                                  	<c:when test="${requestScope.myShopDetail == null}">
                                  		<fmt:message key="shop.not.open"/><font style="font-weight: bold; color: #CCFFCC"><a href="${pageContext.request.contextPath}/openShop"><fmt:message key="apply.hint"/></a></font>?
                                  	</c:when>
                                  	<c:otherwise>
                                  		<c:if test="${requestScope.myShopDetail.status == -1}">
                                  			<fmt:message key="shop.is.opening"/>
                                  		</c:if>
                                  		<c:if test="${requestScope.myShopDetail.status == -2}">
                                  			<fmt:message key="shop.audit.failed"/><a href="${pageContext.request.contextPath}/openShop"><b><fmt:message key="review.material"/></b></a>
                                  		</c:if>
                                  		<c:if test="${requestScope.myShopDetail.status == 1}">
                                  			<a href="<ls:url address='/admin/index'/>"><b><fmt:message key="fill.detail.shop"/></b></a>
                                  		</c:if>
                                  	</c:otherwise>
                                  </c:choose>
                                  </c:if>
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="29"><div align="right"><font color="#ff0000">*</font><fmt:message key="user.email"/>：</div></td>
                                <td height="29"><div align="left"> 
                                <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
                                    <input style="WIDTH: 200px;" type="text" name="userMail" id="userMail" size="20" class=input value="${user.userMail}" />
                                </auth:auth>
                                <auth:auth ifNotGranted="F_VIEW_ALL_DATA">
                                	<input style="WIDTH: 200px; color: #949492" type="text" name="userMail" id="userMail" size="20" class=input value="${user.userMail}" readonly="readonly" />
                                </auth:auth>
                                  &nbsp;<fmt:message key="mail.canot.changed"/></div></td>
                              </tr>
                              <tr> 
                                <td height="29"><div align="right"><font color="#ff0000">*</font><fmt:message key="real.name"/>：</div></td>
                                <td height="29"><div align="left"> 
                                    <input style="WIDTH: 200px" type="text" name="nickName" id="nickName" size="20" class=input value="${user.nickName}" maxlength="30">
                                  </div></td>
                              </tr>                      
                              <tr> 
                                <td height="29"><div align="right"><font color="#ff0000">*</font><fmt:message key="Phone"/>：</div></td>
                                <td height="29"><div align="left"> 
                                    <input style="WIDTH: 200px" type="text" name="userMobile" id="userMobile" size="20" class=input value="${user.userMobile}"  maxlength="50">
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="29"><div align="right"><fmt:message key="userTel"/>：</div></td>
                                <td height="29"><div align="left"> 
                                    <input style="WIDTH: 200px" type="text" name="userTel" id="userTel" size="20" class=input value="${user.userTel}" maxlength="50">
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="29"><div align="right">QQ：</div></td>
                                <td height="29"><div align="left"> 
                                    <input style="WIDTH: 200px" type="text" name="qq" id="qq" size="20" class=input value="${user.qq}" maxlength="50">&nbsp;<fmt:message key="more.qq.number"/>
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="29"><div align="right">MSN：</div></td>
                                <td height="29"><div align="left"> 
                                    <input style="WIDTH: 200px" type="text" name="msn" id="msn" size="20" class=input value="${user.msn}" maxlength="100">
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="29"><div align="right"><fmt:message key="Fax"/>：</div></td>
                                <td height="29"><div align="left"><input style="WIDTH: 200px" type="text" name="fax" id="fax" size="20" class=input value="${user.fax}" maxlength="15"></div></td>
                              </tr>
                              <tr> 
                                <td height="29"><div align="right"><fmt:message key="PostCode"/>：</div></td>
                                <td height="29"><div align="left"> 
                                    <input style="WIDTH: 200px" type="text" name="userPostcode" id="userPostcode" size="20" class=input value="${user.userPostcode}" maxlength="15"> 
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="29"><div align="right"><fmt:message key="gender.hint"/>：</div></td>
                                <td height="29"><div align="left"> 
                                        <select id="sex" name="sex">
										  <ls:optionGroup type="select" required="false" cache="true"
							                beanName="USER_SEX_TYPE" selectedValue="${user.sex}"/>
							            </select>
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="29"><div align="right"><fmt:message key="birth.day"/>： </div></td>
                                <td height="29"><div align="left">     
								<select name="userBirthYear" id="userBirthYear" size="1" onchange="showmonth();showday()">
									<script>
										var yearbegin=1930,yearend=new Date().getFullYear();
										document.write('<option value="" selected><fmt:message key="year.hint"/></option>')
										for(var i=yearbegin;i<=yearend;i++){
										document.write ("<option value="+i+">"+i+"</option>")
										}
									</script>
								</select>
					 			<select name="userBirthMonth" id="userBirthMonth" size="1" onchange="showday();">
									<option value="">
										<fmt:message key="month.hint"/>
									</option>
								</select>
								<select name="userBirthDay" id="userBirthDay" size="1">
									<option value="">
										<fmt:message key="day.hint"/>
									</option>
								</select>                                   
                                  </div>
                              </td>
                              </tr>
                              <tr> 
                                <td height="29"><div align="right"><font color="#ff0000">*</font><fmt:message key="Address"/>：</div></td>
                                <td height="29"><div align="left"> 
                                    <input style="WIDTH: 250px" type="text" name="userAdds" id="userAdds" size="20" class=input value="${user.userAdds}" maxlength="150">
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="29"><div align="right">
                                <fmt:message key="modify.password"/>：</div></td>
                                
                                <!--  修改密码 -->
                                <td height="29"><div align="left">
                                    <input type="checkbox" name="modifyPass" id="modifyPass" onclick="javascript:changePass(this)">
                                  </div></td>
                              </tr>
                              <tr><td colspan="2" width="100%">
                              <div id="pass1" style="display: none">
                                <table width="100%"  cellpadding="0" cellspacing="0">
                                   <tr> 
                                <td width="33%" height="29"><div align="right">
                                <font color="#ff0000">*</font><fmt:message key="origin.password"/>：</div></td>
                                <td width="67%" height="29"><div align="left">
                                    <input style="WIDTH: 200px" type="password" name="passwordOld" id="passwordOld" size="20" class="input" />
                                  </div></td>
                              </tr>
                              <tr> 
                                <td height="29"><div align="right">
                                <font color="#ff0000">*</font><fmt:message key="password"/>：</div></td>
                                <td height="29"><div align="left"> 
                                    <input style="WIDTH: 200px" type="password" name="password" id="password" size="20" class="input" />
                                  </div></td>
                              </tr>
                              <tr>
                                <td height="29"><div align="right"><font color="#ff0000">*</font><fmt:message key="Confirmation"/>：</div></td>
                                <td height="29"><div align="left"> 
                                    <input style="WIDTH: 200px" type="password" name="password2" id="password2" size="20" class="input" />
                                  </div></td>
                              </tr> 
                                </table>
                                </div>
                              </td></tr>
                              <tr> 
                                <td height="35" colspan="2"><div align="center"> 
                                    <table width="100%" align="center" cellpadding="0" cellspacing="0">
                                      <tr> 
                                        <td><div align="center">
                                              <!-- 管理员只能看，不能编辑 -->
                                            <input type="submit" value="<fmt:message key="submit"/>" class="s"/>
                                            <input type="reset" value="<fmt:message key="cancel"/>" class="s">
                                          </div></td>
                
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
				var userBirthYearValue = '${userBirthYear}';
				var userBirthMonthValue = '${userBirthMonth}';
				var userBirthDayValue = '${userBirthDay}';
				var elYear = $("#userBirthYear");
		      	var elMonth = $("#userBirthMonth");
		      	var elDay = $("#userBirthDay");
			function showyear(){
				if(userBirthYearValue!=""){
					setSelectVal(elYear,userBirthYearValue);
				}
			}
			function showmonth(){
			 elMonth.empty();
			 elMonth.append("<option value='01'>01</option>" );  
			 elMonth.append("<option value='02'>02</option>" );  
			 elMonth.append("<option value='03'>03</option>" ); 
			 elMonth.append("<option value='04'>04</option>" );
			 elMonth.append("<option value='05'>05</option>" ); 
			 elMonth.append("<option value='06'>06</option>" ); 
			 elMonth.append("<option value='07'>07</option>" ); 
			 elMonth.append("<option value='08'>08</option>" ); 
			 elMonth.append("<option value='09'>09</option>" ); 
			 elMonth.append("<option value='10'>10</option>" ); 
			 elMonth.append("<option value='11'>11</option>" );  
			elMonth.append("<option value='12'>12</option>" ); 
				if(userBirthMonthValue!=""){
					setSelectVal(elMonth, userBirthMonthValue);
				}
			}

		function showday(){
			if(elYear && elMonth){
			 	 elDay.empty();
				 elDay.append("<option value='01'>01</option>" );  
				 elDay.append("<option value='02'>02</option>" );  
				 elDay.append("<option value='03'>03</option>" ); 
				 elDay.append("<option value='04'>04</option>" );
				 elDay.append("<option value='05'>05</option>" ); 
				 elDay.append("<option value='06'>06</option>" ); 
				 elDay.append("<option value='07'>07</option>" ); 
				 elDay.append("<option value='08'>08</option>" ); 
				 elDay.append("<option value='09'>09</option>" ); 
				for(var i=10;i<28;i++){
					 elDay.append("<option value='" + (i+1) + "'>" + (i+1) + "</option>" );  
				}
				var month = elMonth.val();
				if(month!="02"){
					 elDay.append("<option value='29'>29</option>" ); 
					 elDay.append("<option value='30'>30</option>" ); 
				}
				switch(month){
					case "01":
					case "03":
					case "05":
					case "07":
					case "08":
					case "10":
					case "12":{
						elDay.append("<option value='31'>31</option>" ); 
						break;
					}
					case "02":{
						var nYear=elYear.val();
						if(nYear%400==0 || nYear%4==0 && nYear%100!=0){
						 	elDay.append("<option value='29'>29</option>" ); 
						}
						break;
					}
					
				}
			}
				if(userBirthDayValue!=""){
					setSelectVal(elDay, userBirthDayValue);
				}
		}
			//初始化
		      jQuery(document).ready(function(){
		      			showyear();
				 		showmonth();
				 		showday();
		             });
             
        </script>