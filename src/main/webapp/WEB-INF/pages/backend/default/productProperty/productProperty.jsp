<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>创建商品规格</title>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.validate.js'/>" /></script>
        <link rel="stylesheet" type="text/css" media="screen" href="<ls:templateResource item='/common/css/indexJpgForm.css'/>" />
        <style type="text/css" media="all">
          @import url(<ls:templateResource item='/css/screen.css'/>);
        </style>
        <script language="javascript">
		    $.validator.setDefaults({
		    });

    $(document).ready(function() {
    jQuery("#form1").validate({
            rules: {
            banner: {
                required: true,
                minlength: 5
            },
            url: "required"
        },
        messages: {
            banner: {
                required: "Please enter banner",
                minlength: "banner must consist of at least 5 characters"
            },
            url: {
                required: "Please provide a password"
            }
        }
    });
 
         $("#col1 tr:nth-child(even)").addClass("even");
         $("#col1 th").addClass('sortable'); 
         
                 //<tr/>居中
        $("#tab tr").attr("align","center");
        
        //增加<tr/>
        $("#addSpecBtn").click(function(){
            var _len = $("#tab tr").length;        
            var index = _len -1;
            var trcontent = "<tr id="+_len+" align='center'>"
                                +"<td>"+_len+"</td>"
                                +"<td><input type='text' name='productPropertyValueList[" + index +"].name' id='productPropertyValueList["+index +"].name' /></td>"
                                +"<td><input type='file' name='productPropertyValueList[" + index +"].file' id='productPropertyValueList["+index+"].file' /><img style='width: 20px; height: 20px; padding: 0px; vertical-align: middle; border: 1px solid #666666;' src=''></td>"
                                  +"<td><input type='text' style='width:40px' maxlength='5' name='productPropertyValueList[" + index  + "].sequence' id='productPropertyValueList["+ index +"].sequence' /></td>"
                                +"<td>下线&nbsp;  <a href=\'#\' onclick=\'deltr("+_len+")\'>删除</a></td>"
                            +"</tr>";
            $("#tab").append(trcontent);            
        })    
});

    //删除<tr/>
    var deltr =function(index)
    {
     //   var _len = $("#tab tr").length;
        $("tr[id='"+index+"']").remove();//删除当前行
    }
</script>
</head>
    <body>
        <form action="<ls:url address='/admin/productProperty/save'/>" method="post" id="form1">
            <input id="propId" name="propId" value="${productProperty.propId}" type="hidden">
            <div align="center">
            <table border="0" style="width:100%" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                	创建商品规格
                            </div>
                        </th>
                    </tr>
                </thead>
		<tr>
		        <td width="120px">
		          	<div align="center">属性名称: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<input type="text" name="propName" id="propName" value="${productProperty.propName}" />
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">商品分类 <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<input type="text" name="sortId" id="sortId" value="${productProperty.sortId}" />
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">别名:</div>
		       	</td>
		        <td>
		           <input type="text" name="memo" id="memo" value="${productProperty.memo}" />
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">排序: </div>
		       	</td>
		        <td>
		          <input type="text" name="Sequence" id="sequence" value="${productProperty.sequence}" />
		        </td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">是否: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		        <table style="border: 0px;padding: 0px;margin: 0px">
		        	<tr>
		        	<td>必须      
			        	<select id="isRequired" name="isRequired">
					 	 	<ls:optionGroup type="select" required="true" cache="true"  beanName="YES_NO" selectedValue="${productProperty.isRequired}"/>
		          	  </select>	
		            </td>
		            <td>多选
			        	<select id="isMulti" name="isMulti">
					 	 	<ls:optionGroup type="select" required="true" cache="true"  beanName="YES_NO" selectedValue="${productProperty.isMulti}"/>
		          	  </select>	
		            </td>
	            	<td>属性类型
	            			        	<select id="type" name="type">
					  <ls:optionGroup type="select" required="true" cache="true"
		                beanName="YES_NO" selectedValue="${productProperty.type}"/>
		            </select>	
	            	</td>
	            	<td>关键属性
	            			        	<select id="isKeyProp" name="isKeyProp">
					  <ls:optionGroup type="select" required="true" cache="true"
		                beanName="YES_NO" selectedValue="${productProperty.isKeyProp}"/>
		            </select>	
	            	</td>
	            	<td>
	            	参数属性        	
	            	<select id="isParamProp" name="isParamProp">
					  <ls:optionGroup type="select" required="true" cache="true"
		                beanName="YES_NO" selectedValue="${productProperty.isParamProp}"/>
		            </select>	
	            	</td>
	            	<td>销售属性  	
	            	<select id="isSaleProp" name="isSaleProp">
					  <ls:optionGroup type="select" required="true" cache="true"
		                beanName="YES_NO" selectedValue="${productProperty.isSaleProp}"/>
		            </select>	
	            	</td>
	            	<td>可以搜索 	
	            	<select id="isForSearch" name="isForSearch">
					  <ls:optionGroup type="select" required="true" cache="true"
		                beanName="YES_NO" selectedValue="${productProperty.isForSearch}"/>
		            </select>	</td>
	            	<td>输入属性    	
	            	<select id="isInputProp" name="isInputProp">
					  <ls:optionGroup type="select" required="true" cache="true"
		                beanName="YES_NO" selectedValue="${productProperty.isInputProp}"/>
		            </select>	</td>
	            	</tr>
		        </table>
		        

		        </td>
		</tr>
		<tr><td>&nbsp;</td>
		<td><input  type="button" id="addSpecBtn" value="增加规格值"/></td>
		</tr>
       <tr><td>&nbsp;</td>
		<td>
		    <table id="tab"  style="border: 1px; border-collapse: collapse;"  width="100%" align="center" >
		        <tr style="background-color: #cccccc">
		            <td width="60px">序号</td>
		            <td>名称</td>
		            <td>图片</td>
		            <td>顺序</td>
		            <td>操作</td>
		       </tr>
		    </table>
		</td>
		</tr>
         <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="添加" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/productProperty/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
</html>

