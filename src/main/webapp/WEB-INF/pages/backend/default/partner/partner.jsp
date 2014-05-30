<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>

<html>
    <head>
        <title>创建供应商</title>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.validate.js'/>" /></script>
        <link rel="stylesheet" type="text/css" media="screen" href="<ls:templateResource item='/common/default/css/errorform.css'/>" />
        <style type="text/css" media="all">
          @import url(<ls:templateResource item='/common/default/css/screen.css'/>);
        </style>
        <script language="javascript">
		    $.validator.setDefaults({
		    });

		    $(document).ready(function() {
		        jQuery("#form1").validate({
		            rules: {
		            	partnerName: {
		                    required: true,
		                },

		                <c:if test='${empty partner}'>
		                password: {
		                    required: true,
		                },
		                passwordag: {
		                    required: true,
		    		        equalTo:"#password"
		                },
		                </c:if>
		                
		                title: {
		                    required: true,
		                },
		                location: {
		                    required: true,
		                },
		                showInfo: {
		                    required: true,
		                },
		                status: {
		                    required: true,
		                },
		                showInfo: {
		                    required: true,
		                }
		            },
		            messages: {
		            	partnerName: {
		                    required: '<fmt:message key="partner.username.required"/>',
		                },

		                password: {
		                    required: '<fmt:message key="password.required"/>',
		                },
		                passwordag: {
		                    required: '<fmt:message key="password.required"/>',
		                    equalTo: '<fmt:message key="password.equalTo"/>'
		                },
		                
		                title: {
		                    required: '<fmt:message key="partner.title.required"/>',
		                },
		                location: {
		                    required: '<fmt:message key="partner.location.required"/>',
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
	<table class="${tableclass}" style="width: 100%">
	    <thead>
	    	<tr>
		    	<th>
			    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
			    	<a href="<ls:url address='/admin/partner/query'/>">供应商管理</a>
		    	</th>
	    	</tr>
	    </thead>
    </table>
        <form action="<ls:url address='/admin/partner/save'/>" method="post" id="form1" enctype="multipart/form-data">
            <input id="partnerId" name="partnerId" value="${partner.partnerId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
							<c:choose>
			                	<c:when test="${not empty partner}">修改供应商</c:when>
			                	<c:otherwise>创建供应商</c:otherwise>
			                </c:choose>				
                            </div>
                        </th>
                    </tr>
                </thead>
     			     <tr>
        <td>
          <div align="center">供应商登录名: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="partnerName" id="partnerName" value="${partner.partnerName}" />
        </td>
      </tr>
     <c:if test="${empty partner}">
     <tr>
        <td>
          <div align="center">登录密码: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="password" name="password" id="password" value="${partner.password}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">确认密码: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="password" name="passwordag" id="passwordag" value="${partner.password}" />
        </td>
      </tr>
      </c:if>
     <tr>
        <td>
          <div align="center">供应商名称: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="title" id="title" value="${partner.title}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">供应商主页: </div>
       </td>
        <td>
           <input type="text" name="homepage" id="homepage" value="${partner.homepage}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">银行名称: </div>
       </td>
        <td>
           <input type="text" name="bankName" id="bankName" value="${partner.bankName}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">银行账号: </div>
       </td>
        <td>
           <input type="text" name="bankNo" id="bankNo" value="${partner.bankNo}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">银行用户名: </div>
       </td>
        <td>
           <input type="text" name="bankUser" id="bankUser" value="${partner.bankUser}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">商户所在位置: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="location" id="location" value="${partner.location}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">联系人: </div>
       </td>
        <td>
           <input type="text" name="contact" id="contact" value="${partner.contact}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">商户图片1: </div>
       </td>
        <td>
           
           
     		<c:if test="${not empty partner.image}">
     		<img width="300px" src="<ls:photo item='${partner.image}'/>"/><br/>
     		</c:if>
           <input type="file" name="imageFile" id="file" size="50"/>
           <input type="hidden" name="image" id="image" value="${partner.image}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">商户图片2: </div>
       </td>
        <td>
           
     		<c:if test="${not empty partner.image1}">
     		<img width="300px" src="<ls:photo item='${partner.image1}'/>"/><br/>
     		</c:if>
           <input type="file" name="imageFile1" id="file" size="50"/>
           <input type="hidden" name="image1" id="image1" value="${partner.image1}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">商户图片3: </div>
       </td>
        <td>
           
     		<c:if test="${not empty partner.image2}">
     		<img width="300px" src="<ls:photo item='${partner.image2}'/>"/><br/>
     		</c:if>
           <input type="file" name="imageFile2" id="file" size="50"/>
           <input type="hidden" name="image2" id="image2" value="${partner.image2}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">联系电话: </div>
       </td>
        <td>
           <input type="text" name="phone" id="phone" value="${partner.phone}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">商户地址: </div>
       </td>
        <td>
           <input type="text" name="address" id="address" value="${partner.address}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">商户其他信息: </div>
       </td>
        <td>
           <input type="text" name="other" id="other" value="${partner.other}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">商户手机号码: </div>
       </td>
        <td>
           <input type="text" name="mobile" id="mobile" value="${partner.mobile}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">是否展示相关信息: <font color="ff0000">*</font></div>
       </td>
        <td>
                      
				<select id="showInfo" name="showInfo">
				  <ls:optionGroup type="select" required="true" cache="true"  beanName="YES_NO" selectedValue="${partner.showInfo}"/>
	            </select>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">商户状态: <font color="ff0000">*</font></div>
       </td>
        <td>
           
				<select id="status" name="status">
				  <ls:optionGroup type="select" required="true" cache="true"
	                beanName="ENABLED" selectedValue="${partner.status}"/>
	            </select>
	        
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">是否显示首页: <font color="ff0000">*</font></div>
       </td>
        <td>
           
				<select id="display" name="display">
				  <ls:optionGroup type="select" required="true" cache="true"
	                beanName="YES_NO" selectedValue="${partner.display}"/>
	            </select>
	        
        </td>
      </tr>
      
     <c:if test="${not empty partner}">
     <tr>
        <td>
          <div align="center">评论满意数量: </div>
       </td>
        <td>
           <input disabled="disabled" type="text" name="commentGood" id="commentGood" value="${partner.commentGood}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">评论一般数量: </div>
       </td>
        <td>
           <input disabled="disabled" type="text" name="commentNone" id="commentNone" value="${partner.commentNone}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">评论失望数量: </div>
       </td>
        <td>
           <input disabled="disabled" type="text" name="commentBad" id="commentBad" value="${partner.commentBad}" />
        </td>
      </tr>
      </c:if>

                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="保存" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/partner/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>


