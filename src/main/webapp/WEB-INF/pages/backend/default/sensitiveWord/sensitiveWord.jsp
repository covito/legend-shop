<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>创建敏感字过滤表</title>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.validate.js'/>" /></script>
        <script type="text/javascript" src="<ls:templateResource item='/common/js/infinite-linkage.js'/>"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
</head>
    <body>
        <form action="<ls:url address='/admin/system/sensitiveWord/save'/>" method="post" id="form1">
        	<input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}"/>
            <input id="sensId" name="sensId" value="${sensitiveWord.sensId}" type="hidden">
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1"  style="width: 100%" >
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                	创建敏感字过滤表
                            </div>
                        </th>
                    </tr>
                </thead>
        <tr>
		        <td width="200px">
		          	<div align="center">是否全局敏感字: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		        		<select id="isGlobal" name="isGlobal" onchange="change(this.value)">
		        			<ls:optionGroup type="select" required="false" cache="true" beanName="YES_NO" selectedValue="${sensitiveWord.isGlobal}"/>          			
		        		</select>	  
		        </td>
		</tr>
		<tr id="one">
		<td>
		     <div align="center">分类:</div>
		</td>
			<td>
			<select class="combox"  id="sortId" name="sortId"  requiredTitle="-- 一级分类 -- "  childNode="nsortId" selectedValue="${sensitiveWord.sortId}"  retUrl="${pageContext.request.contextPath}/sort/loadDefaultSorts/${sensitiveWord.sortId}">	
			</select>
			<select class="combox"   id="nsortId" name="nsortId"  requiredTitle="-- 二级分类 --"  selectedValue="${sensitiveWord.nsortId}" showNone="false"  parentValue="${sensitiveWord.sortId}" childNode="subNsortId" retUrl="${pageContext.request.contextPath}/sort/loadNSorts/{value}">
			</select>
			<select class="combox"   id="subNsortId" name="subNsortId"  requiredTitle="-- 三级分类 --"  selectedValue="${sensitiveWord.subNsortId}"  showNone="false"   parentValue="${sensitiveWord.nsortId}" retUrl="${pageContext.request.contextPath}/sort/loadSubNSorts/{value}">
			</select>
			</td>
		</tr>
		<tr>
		        <td>
		          	<div align="center">关键字: <font color="ff0000">*</font></div>
		       	</td>
		        <td>
		           	<input type="text" name="words" id="words" value="${sensitiveWord.words}" />
		        </td>
		</tr>
		
                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="提交" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/system/sensitiveWord/query"/>'" />
                        </div>
                    </td>
                </tr>
            </table>
           </div>
        </form>
    </body>
     <script language="javascript">
		    $.validator.setDefaults({
		    });

       $(document).ready(function() {
    jQuery("#form1").validate({
			rules: {
			words: "required",
			isGlobal: "required" 
		},
    messages: {
			words:"请输入关键字",
			isGlobal:"请选择"	
		}
    });
    
		//斑马条纹
     	 $("#col1 tr:nth-child(even)").addClass("even");
});

		function change(v1){
		var b1=$("#one");
		if(v1==1){
		b1.hide();
		$("#sortId").val("null");
		$("#nsortId").val("null");
		$("#subNsortId").val("null");
		}
		if(v1==0){
		b1.show();
	}
}
	function pager(curPageNO){
			sendData(curPageNO);
		}

	 jQuery(document).ready(function(){
       		 //三级联动
              $("select.combox").initSelect();		
          });
</script>
</html>

