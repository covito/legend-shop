<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html>
    <head>
        <title>创建标签</title>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.js'/>"></script>
         <script type='text/javascript' src="<ls:templateResource item='/common/js/jquery.validate.js'/>" /></script>
        <link rel="stylesheet" type="text/css" media="screen" href="<ls:templateResource item='/common/default/css/errorform.css'/>" />
         <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
</head>
    <body>
        <form action="<ls:url address='/admin/tag/save'/>" method="post" id="form1">
            <input id="tagId" name="tagId" value="${tag.tagId}" type="hidden">
   <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th>
    			<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
				<a href="<ls:url address='/admin/tag/query'/>">标签管理</a>
    	</th></tr>
    </thead>
    </table>
            <div align="center">
            <table border="0" align="center" class="${tableclass}" id="col1">
                <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
                                创建标签
                            </div>
                        </th>
                    </tr>
                </thead>
     			     <tr>
        <td>
          <div align="center">名称 <font color="ff0000">*</font></div>
       </td>
        <td>
           <input type="text" name="name" id="name" value="${tag.name}" />
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">商品类型<font color="ff0000">*</font></div>
       </td>
        <td>
           <select id="sortId" name="sortId" >
	            <ls:optionGroup type="select" required="false" cache="fasle"
	                defaultDisp="-- 一级类型 --" 
	                hql="select t.sortId, t.sortName from Sort t where  t.status = 1 and t.sortType = 'P' and t.userName = ?" param="${tag.userName}" selectedValue="${tag.sortId}"/>
		</select>
        </td>
      </tr>
     <tr>
        <td>
          <div align="center">新闻栏目<font color="ff0000">*</font></div>
       </td>
        <td>
	           <select id="newsCategoryId" name="newsCategoryId">
	                 <ls:optionGroup type="select" required="false" cache="fasle"
	                beanName="NewsCategory" beanId="newsCategoryId" beanDisp="newsCategoryName" 
	                hql="select t.newsCategoryId, t.newsCategoryName from NewsCategory t where t.status = 1 and t.userName = ?" param="${tag.userName}" selectedValue="${tag.newsCategoryId}"/>
	            </select>
        </td>
      </tr>
      <tr>
      <td>
          <div align="center">状态<font color="ff0000">*</font></div>
       </td>
        <td>
           <select id="status" name="status">
	        <ls:optionGroup type="select" required="true" cache="true"
	                beanName="YES_NO" selectedValue="${tag.status}"/>
		</select>
           
        </td>
      </tr>
      <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="submit" value="提交" />
                            <input type="reset" value="重置" />
                            <input type="button" value="返回"
                                onclick="window.location='<ls:url address="/admin/tag/query"/>'" />
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
                required: true
            },
            sortId: "required",
            newsCategoryId: "required"
            
        },
        messages: {
            name: {
                required: "请输入名称"
            },
            sortId: {
                required: "请选择商品分类"
            },
            newsCategoryId:{
            	required: "请选择商品分类"
            }
        }
    });
     highlightTableRows("col1");
 		//斑马条纹
     	 $("#col1 tr:nth-child(even)").addClass("even");
		});
</script>
    </body>
</html>


