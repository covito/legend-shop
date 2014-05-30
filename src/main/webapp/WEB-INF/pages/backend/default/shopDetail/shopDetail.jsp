<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html>
    <head>
        <title>商城信息</title>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
        <script type='text/javascript' src="<ls:templateResource item='/common/js/shopdetail.js'/>"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
		<script type="text/javascript" src="<ls:templateResource item='/common/js/infinite-linkage.js'/>"></script>
		<script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
        <script language="javascript">
          var templetEntity = jQuery.parseJSON( '${templetEntity}');
    $.validator.setDefaults({
    });

    $(document).ready(function() {
    jQuery("#form1").validate({
            rules: {
             siteName: {
				required: true,
				minlength: 2
			},
            shopAddr: "required",
            briefDesc: "required"
        },
        messages: {
             shopAddr: {
                required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },
             bankCard: {
                required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },
            payee: {
                required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },
           code: {
                required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },
            recipient: {
                required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },
           postAddr: {
                required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>',
                minlength: '<fmt:message key="errors.minlength"><fmt:param value=""/><fmt:param value="2"/></fmt:message>'
            }, 
		   briefDesc: {
                required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>'
            },
            siteName: {
                required: '<fmt:message key="errors.required"><fmt:param value=""/></fmt:message>',
                minlength: '<fmt:message key="errors.minlength"><fmt:param value=""/><fmt:param value="2"/></fmt:message>'
            }
        }
    });
		 highlightTableRows("col1");
		//斑马条纹
     	 $("#col1 tr:nth-child(even)").addClass("even");
         $("select.combox").initSelect();
         
         //templet selection
		$("#frontEndTemplet").change(function(){
			initLanguageAndStyle(templetEntity.frontEndTempletMap[$(this).children('option:selected').val()], "frontEndLanguage", "frontEndStyle");
		});
		$("#backEndTemplet").change(function(){
		  initLanguageAndStyle(templetEntity.backEndTempletMap[$(this).children('option:selected').val()],  "backEndLanguage", "backEndStyle");
		});
		initSelect();
});

	//更改商城状态
	function auditShop(userId,shopId,status) {
	       var str ="上线";
	       if(status == 0){
	       	str = "下线";
	       }else if(status == -1){
	       	str = "审核中";
	       }else if(status == -2){
	       	str = "拒绝";
	       }else if(status == -3){
	       	str = "关闭";
	       }
		  if(confirm('确认状态变更为 ' + str +  " ?")){
		  	    var data = {
                	"userId": userId,
                	"shopId": shopId,
                	"status": status
                };
		    jQuery.ajax({
				url:"${pageContext.request.contextPath}/admin/shopDetail/audit" , 
				type:'post', 
				data:data,
				async : true, //默认为true 异步   
			    dataType : 'html', 
				error: function(jqXHR, textStatus, errorThrown) {
			 		// alert(textStatus, errorThrown);
				},
				success:function(retData){
						 if(retData == null ||  retData == ''){
					 		 alert('成功') ;
	        				window.location.reload();
				       }else{
					 		  alert('失败') ;	
				       }
				}
				});
	    }
	}

</script>

</head>
    <body>
        <form action="${pageContext.request.contextPath}/admin/shopDetail/save" method="post" id="form1" enctype="multipart/form-data">
            <input id="userId" name="userId" value="${shopDetail.userId == null ? param.userId:shopDetail.userId}" type="hidden">
            <c:choose>
            	<c:when test="${shopDetail.userId == null}">
            			<input id="userId" name="userId" value="${param.userId}" type="hidden">
            	</c:when>
            	<c:otherwise>
            			<input id="userId" name="userId" value="${shopDetail.userId}" type="hidden">
            	</c:otherwise>
            </c:choose>
             <input id="id" name="id" value="${id}" type="hidden">
             <input id="shopId" name="shopId" value="${shopDetail.shopId}" type="hidden">

                <table class="${tableclass}" style="width: 100%">
			    <thead>
			    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; <a href="${pageContext.request.contextPath}/admin/shopDetail/query">商城管理</a> &raquo; 商城信息</th></tr>
			    </thead>
			    </table>
            <table style="width: 100%" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                商城信息
                            </div>
                        </th>
                    </tr>
                </thead>
      <tr>
        <td width="150px">
          <div align="right">用户名:</div>
       </td>
        <td>
           <input type="text" name="userName" id="userName" value="${shopDetail.userName == null ? param.userName : shopDetail.userName}" size="50" readonly="readonly"/>
           <a href="${pageContext.request.contextPath}/p/myaccount" target="_blank">我的账号</a>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">网店名称: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="siteName" id="siteName" value="${shopDetail.siteName}" size="50"/>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">网店地址: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="shopAddr" id="shopAddr" value="${shopDetail.shopAddr}" size="50"/>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">银行汇款帐号:</div>
       </td>
        <td>
           <input type="text" name="bankCard" id="bankCard" value="${shopDetail.bankCard}" size="50"/>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">收款人姓名:</div>
       </td>
        <td>
           <input type="text" name="payee" id="payee" value="${shopDetail.payee}" size="50" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">邮政编码:</div>
       </td>
        <td>
           <input type="text" name="code" id="code" value="${shopDetail.code}" size="50"/>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">汇款地址:</div>
       </td>
        <td>
           <input type="text" name="postAddr" id="postAddr" value="${shopDetail.postAddr}" size="50"/>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">邮递接收人: </div>
       </td>
        <td>
           <input type="text" name="recipient" id="recipient" value="${shopDetail.recipient}" size="50"/>
        </td>
      </tr>
       <tr>
        <td>
          <div align="right">选择前台模版: </div>
       </td>
        <td><!-- see BusinessService getColorTyle -->
        <div style="float: left;"><select id="frontEndTemplet" name="frontEndTemplet"></select></div>
			<div style="float: left;">&nbsp;&nbsp;语言<select  id="frontEndLanguage" name="frontEndLanguage" ></select></div>
			<div style="float: left;">&nbsp;&nbsp;风格<select  id="frontEndStyle" name="frontEndStyle" ></select></div>
        </td>
      </tr>  
       <tr>
        <td>
          <div align="right">选择后台模版: </div>
       </td>
        <td><!-- see BusinessService getColorTyle -->
        <div style="float: left;"><select id="backEndTemplet" name="backEndTemplet"></select></div>
			<div style="float: left;">&nbsp;&nbsp;语言<select  id="backEndLanguage" name="backEndLanguage" ></select></div>
			<div style="float: left;">&nbsp;&nbsp;风格<select  id="backEndStyle" name="backEndStyle" ></select></div>
        </td>
      </tr>     
     
      <tr>
        <td>
          <div align="right">地区选项: <font color="ff0000">*</font></div>
        </td>
        <td>
							<select class="combox"  id="provinceid" name="provinceid"  requiredTitle="true"  childNode="cityid" selectedValue="${shopDetail.provinceid}"  retUrl="${pageContext.request.contextPath}/common/loadProvinces">
							</select>
							<select class="combox"   id="cityid" name="cityid"  requiredTitle="true"  selectedValue="${shopDetail.cityid}"   showNone="false"  parentValue="${shopDetail.provinceid}" childNode="areaid" retUrl="${pageContext.request.contextPath}/common/loadCities/{value}">
							</select>
							<select class="combox"   id="areaid" name="areaid"   requiredTitle="true"  selectedValue="${shopDetail.areaid}"    showNone="false"   parentValue="${shopDetail.cityid}"  retUrl="${pageContext.request.contextPath}/common/loadAreas/{value}">
							</select>
        </td>
      </tr>
     <tr>
        <td>
          <div align="right">简要描述: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="briefDesc" id="briefDesc" value="${shopDetail.briefDesc}" size="50"/>
        </td>
      </tr>
      <tr>
        <td>
          <div align="right">独立域名配置 </div>
       </td>
        <td>
        <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
                  接入域名（需要管理员审批并开通）
		          <input type="text" name="domainName"  id="domainName" value="${shopDetail.domainName}" />&nbsp;
		          ICP信息 <input type="text" name="icpInfo"  id="icpInfo" value="${shopDetail.icpInfo}" />
        </auth:auth>
        <auth:auth ifNotGranted="F_VIEW_ALL_DATA">
                  接入域名（需要管理员审批并开通）
		          <input type="text" name="domainName"  id="domainName" value="${shopDetail.domainName}"   disabled="disabled"/>&nbsp;
		          ICP信息 <input type="text" name="icpInfo"  id="icpInfo" value="${shopDetail.icpInfo}"  disabled="disabled" />
        </auth:auth>

        </td>
      </tr>
     <tr>
        <td>
          <div align="right">商城服务(出现在商品说明中): </div>
       </td>
        <td>
           
              <textarea  name="detailDesc" id="detailDesc">${shopDetail.detailDesc}</textarea>
           
        </td>
      </tr>
      <tr>
        <td>
          <div align="right">状态设置: <font color="ff0000">*</font></div>
       </td>
        <td>
        <!-- for管理员 -->
        <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
                <font color="red">当前状态：
        		<ls:optionGroup  type="label" required="true" cache="true"
	                beanName="SHOP_STATUS" selectedValue="${shopDetail.status}"/></font>&nbsp;&nbsp;
        <c:choose>
        	<c:when test="${shopDetail.status == -1 }">
        	         <a href='javascript:auditShop("${shopDetail.userId}","${shopDetail.shopId}",1)'>同意申请</a>
        	         <a href='javascript:auditShop("${shopDetail.userId}","${shopDetail.shopId}",-2)'>拒绝申请</a>
        	</c:when>
        	<c:otherwise>
        	         <a href='javascript:auditShop("${shopDetail.userId}","${shopDetail.shopId}",1)'>上线</a>
        	         <a href='javascript:auditShop("${shopDetail.userId}","${shopDetail.shopId}",0)'>下线</a>
        	         <a href='javascript:auditShop("${shopDetail.userId}","${shopDetail.shopId}",-3)'>违规关闭（用户将不能登录后台）</a>
        	</c:otherwise>
        </c:choose>
        </auth:auth>
        
        <!-- for user 已经审核成功的 -->
        <c:if test="${shopDetail.status != -1 && shopDetail.status != -3 }">
        <auth:auth ifNotGranted="F_VIEW_ALL_DATA">
                <select id="status" name="status">
				  <ls:optionGroup type="select" required="true" cache="true"
	                beanName="SHOP_STATUS" selectedValue="${shopDetail.status}" exclude="-1,-2,-3"/>
	            </select>
        </auth:auth>
        </c:if>
        <!-- 还没有审核 -->
        <c:if test="${shopDetail.status == -1 || shopDetail.status == -3}">
         <auth:auth ifNotGranted="F_VIEW_ALL_DATA">
        <font color="red">
        		<ls:optionGroup type="label" required="true" cache="true"
	                beanName="SHOP_STATUS" selectedValue="${shopDetail.status}"/></font>
	     </auth:auth>
        </c:if>
        
        </td>
      </tr>  
      
      <c:if test="${shopDetail.recDate!=null}">
     <tr>
        <td>
          <div align="right">修改时间: <font color="ff0000">*</font></div>
       </td>
        <td><fmt:formatDate value="${shopDetail.modifyDate}" pattern="yyyy-MM-dd HH:mm"/></td>
      </tr>
      </c:if>
      <c:if test="${shopDetail.recDate!=null}">
     <tr>
        <td>
          <div align="right">创建时间: <font color="ff0000">*</font></div>
       </td>
        <td><fmt:formatDate value="${shopDetail.recDate}" pattern="yyyy-MM-dd HH:mm"/></td>
      </tr>
      </c:if>
      <tr>
      	<td><div align="right">商城图片</div></td>
      	<td><input type="file" name="file" id="file"/><br>
			<c:if test="${shopDetail.shopPic != null}">
              <img src="<ls:photo item='${shopDetail.shopPic}'/>" height="150" width="200"/> &nbsp;&nbsp;&nbsp;
             </c:if>
						</td>
      </tr>
      <tr>
        <td>
          <div align="right">验证信息: </div>
       </td>
        <td>
           
           <c:if test="${shopDetail.type != null}">
              网店类型：
             <ls:optionGroup type="label" required="false" cache="true" beanName="SHOP_TYPE" selectedValue="${shopDetail.type}"/><br><br>
            </c:if>
            <c:if test="${shopDetail.idCardNum != null}">
              身份证： ${shopDetail.idCardNum}<br><br>
            </c:if>
            <c:if test="${shopDetail.idCardPic != null}">
              身份证照片： <img src="<ls:photo item='${shopDetail.idCardPic}'/>" height="150" width="200"/> &nbsp;&nbsp;&nbsp;
              </c:if>
              <c:if test="${shopDetail.trafficPic != null}">
             营业执照照片： <img src="<ls:photo item='${shopDetail.trafficPic}'/>" height="150" width="200"/><br>
             </c:if>
           
        </td>
      </tr>
                <tr>
                    <td colspan="2">
                        <div align="center">
                        <auth:auth ifAnyGranted="FA_SHOP,F_VIEW_ALL_DATA">
                            <input type="submit" value="提交" />
                        </auth:auth>
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='${pageContext.request.contextPath}/admin/shopDetail/query'" />
                        </div>
                    </td>
                </tr>
            </table>

        </form>
    </body>
</html>

