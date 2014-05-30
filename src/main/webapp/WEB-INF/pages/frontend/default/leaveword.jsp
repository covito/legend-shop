<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:directive.page import="com.legendshop.core.UserManager" />
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@include file='/WEB-INF/pages/common/common.jsp'%>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/common/js/randomimage.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
      <c:forEach items="${requestScope.USER_REG_ADV_740}" var="adv">
		<table width="100%"  cellspacing="0" cellpadding="0" style="margin-bottom: 4px; margin-right: 5px;"  class="picstyle">
			<tr><td><a href="${adv.linkUrl}"><img src="<ls:photo item='${adv.picUrl}'/>" title="${adv.title}" width="100%"/></a></td></tr>
		 </table>
		</c:forEach>
<table width="100%" class="tables" cellpadding="0" cellspacing="0">
							<tr>
								<td class="titlebg">
									<div align="center">
										<b><fmt:message
													key="leave.word" />
										</b>
									</div>
								</td>
							</tr>
							<tr>
								<td>
							<form method="POST" action="${pageContext.request.contextPath}/userComment/save" id="form1" name="form1"  onsubmit="return validateRandNum('${pageContext.request.contextPath}');">
														<input id="userId" name="userId"
															value="<%=UserManager.getUserId(request)%>" type="hidden" />
														<input id="userName" name="userName"
															value="${sessionScope.SPRING_SECURITY_LAST_USERNAME}" type="hidden" />
										<table>
																		<tr>
																			<td width="150px">
																				<div align="right">
																					<font color="#ff0000">*</font>
																					<fmt:message key="user.name" />：
																				
																				</div>
																			</td>
																			<td>
																				<div align="left">
																					<p>
																						<input type="text" id="yourName"
																							name="yourName" size="30" maxlength="30">
																					</p>
																				</div>
																			</td>
																		</tr>
																		<tr>
																			<td>
																				<div align="right">
																					<font color="#ff0000">*</font>
																					<fmt:message key="content" />：
																				
																				</div>
																			</td>
																			<td>
																			
																						<textarea rows="10" cols="50" id="content"
																							name="content" ></textarea>
																					
																				
																			</td>
																		</tr>
																		<tr>
																		<td align="right"><fmt:message key="validation.code"/>：</td>
																			<input type="hidden" id="rand" name="rand"/>
																			<input type="hidden" id="cannonull" name="cannonull" value="<fmt:message key="randomimage.errors.required"/>"/>
																			<input type="hidden" id="charactors4" name="charactors4" value="<ls:i18n key="randomimage.charactors.required" length="4"/>"/>
																			<input type="hidden" id="errorImage" name="errorImage" value="<fmt:message key="error.image.validation"/>"/>
																			<td align="left"><input type="text" id="randNum" name="randNum" class="inputbutton2" maxlength="7" size="7" tabindex="3" >
																			<img id="randImage" name="randImage" src="<ls:templateResource item='/captcha.svl'/>"  style="vertical-align: middle;"/></td>
																		</tr>					
																		<tr><td></td><td align="left"><fmt:message key="change.random1"/> &nbsp;<a href="javascript:void(0)" onclick="javascript:changeRandImg('${pageContext.request.contextPath}')" style="font-weight: bold;"><fmt:message key="change.random2"/></a></td></tr>
																		
																		<tr>													
																			<td colspan="2">
																				<div align="center">
																					<input type="submit"
																						value="<fmt:message key="submit"/>" class="s" />
																				</div>
																			</td>
																		</tr>
																	</table>
													</form>
								</td>
							</tr>
						</table>
<script type="text/javascript">
jQuery(document).ready(function() {
    jQuery("#form1").validate({
        rules: {
            yourName: {
                required: true
            },
           content: {
                required: true,
                minlength: 3
            }
        },
        messages: {
            yourName: {
                required: '<fmt:message key="username.required"/>'
            },
            content: {
                required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>',
                minlength: '<fmt:message key="errors.minlength"><fmt:param value=""/><fmt:param value="3"/></fmt:message>'
            }
        }
    })
});  
 
</script>