<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>创建配送方式</title>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.validate.js'/>" /></script>
         <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="<ls:templateResource item='/common/default/css/errorform.css'/>" />

    
</head>
    <body>
        <table class="${tableclass}" style="width: 100%">
		    <thead>
		    	<tr>
			    	<th>
				    	<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
				    	<a href="<ls:url address='/admin/deliveryType/query'/>">配送方式</a>			    	
			    	</th>
		    	</tr>
		    </thead>
	    </table>
        <form action="<ls:url address='/admin/deliveryType/save'/>" method="post" id="form1">
            <input id="dvyTypeId" name="dvyTypeId" value="${deliveryType.dvyTypeId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
							<c:choose>
			                	<c:when test="${not empty deliveryType}">修改配送方式</c:when>
			                	<c:otherwise>创建配送方式</c:otherwise>
			                </c:choose>				
                            </div>
                        </th>
                    </tr>
                </thead>
     
     <tr>
        <td>
          <div align="center">配送名称: <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="name" id="name" value="${deliveryType.name}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">默认物流公司: <font color="ff0000">*</font></div>
       </td>
        <td>
           
           	<select id="dvyId" name="dvyId">
	            <ls:optionGroup type="select" required="true" cache="fasle"
	                beanId="dvyId" beanDisp="name" defaultDisp="-- 物流公司 --" 
	                hql="select t.dvyId, t.name from DeliveryCorp t where t.userName = ?" param="${sessionScope.SPRING_SECURITY_LAST_USERNAME}"
	                selectedValue="${deliveryType.dvyId}"
	                />
			</select>
           
           
           
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">描述: </div>
       </td>
        <td>
           <input type="text" name="notes" id="notes" value="${deliveryType.notes}" />
        </td>
      </tr>

                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="保存" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/deliveryType/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
            <script language="javascript">
		    $.validator.setDefaults({
		    });
			
			    $(document).ready(function() {
			    jQuery("#form1").validate({
			        rules: {
			        	name: {
			                required: true,
			            },
			            dvyId: {
			                required: true,
			            }
			        },
			        messages: {
			        	name: {
			                required: '<fmt:message key="name.required"/>',
			            },
			            dvyId: {
			                required: '<fmt:message key="deliverytype.dvyid.required"/>',
			            }
			        }
			    });
			 
					         //斑马条纹
			     	  		 $("#col1 tr:nth-child(even)").addClass("even");
					         highlightTableRows("col1");  
			});
			</script>
    </body>
</html>


