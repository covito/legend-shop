<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>

<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<html>
<head>
        <script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
         <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
<script type="text/javascript">
<!--
 function submitForm(){
 	var reslut = true;
 	var newsTitle = $("#newsTitle").val();
 	if(newsTitle == null|| newsTitle ==''){
 		reslut = false;
 		alert("新闻标题不能为空");
 		return;
 	}
 	if(reslut){
			document.getElementById('form1').submit();
		}
 }


$(document).ready(function() {
jQuery("#form1").validate({
        rules: {
       newsCategoryId:"required",
	   newsTitle: {
           required: true,
           minlength: 3
       }
    },
    messages: {
        newsCategoryId:{
        required: "请填写新闻栏目"
        },
    	newsTitle: {
        required: "请输入新闻标题",
         minlength: "请认真填写，新闻标题不能少于3个字符"
      }
    }
});
highlightTableRows("col1");
//斑马条纹
$("#col1 tr:nth-child(even)").addClass("even");	  
});
//-->
</script>
</head>
<body>
<center>
<form method="post" action="${pageContext.request.contextPath}/admin/news/save" id="form1">
<input type="hidden" id="position" name="position" value="${position }"/>
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 
    	资讯管理  &raquo;
	    	 <a href="${pageContext.request.contextPath}/admin/news/query/${position}">
			    	 <ls:optionGroup type="label" required="true" cache="true"
			                beanName="NEWS_POSITION" selectedValue="${position}" defaultDisp=""/>
		         </a>
    	 </th></tr>
    </thead>
        <table style="width:100%;" class="${tableclass}" id="col1">
          <thead>
                    <tr>
                        <th colspan="2">
                            <div align="center">创建新闻</div>
                        </th>
                    </tr>
                </thead>
         <tbody>
         <tr><td>新闻栏目<font color="FF0000">*</font></td>
         <td>
	            <select id="newsCategoryId" name="newsCategoryId">
	                 <ls:optionGroup type="select" required="false" cache="fasle"
	                beanName="NewsCategory" beanId="newsCategoryId" beanDisp="newsCategoryName" defaultDisp="-- 请选择 --"
	                hql="select t.newsCategoryId, t.newsCategoryName from NewsCategory t where t.status = 1 and  t.userName = ?" param="${sessionScope.SPRING_SECURITY_LAST_USERNAME}" selectedValue="${news.newsCategoryId}"/>
	            </select>
	            </td> 
		</tr>
		  <tr><td>状态</td>
		  <td>
	           <select id="status" name="status">
				  <ls:optionGroup type="select" required="true" cache="true"
	                beanName="ONOFF_STATUS" selectedValue="${bean.status}"/>
	            </select>

	            &nbsp;高亮
	            <select id="highLine" name="highLine">
	            <ls:optionGroup  type="select" required="true" cache="true"
	                beanName="YES_NO" selectedValue="${news.highLine == null ? 0 : news.highLine}"   />
	            </select>
		  </td>
		  </tr>
		  <tr><td>商品类型 </td>
		  <td>
	            <lb:sortOption id="sortId"  type="P"  selectedValue="${news.sortId}"/>
	            还没有商品类型？请先&nbsp;<a href="${pageContext.request.contextPath}/admin/sort/load">商品类型</a>&nbsp;<a href="${pageContext.request.contextPath}/WEB-INF/pages/newsCategory/newsCategory.jsp">创建栏目</a>
		  </td>
		  </tr>
		    <tr>
            <td>
                 新闻标题<font color="FF0000">*</font>
            </td>
            <td>
            <input type="text" name="newsTitle" id="newsTitle" size="40" class=input value="${news.newsTitle}" />
            <input type="hidden" id="newsId" name="newsId" value="${news.newsId}"/>
            </td>
          </tr>
		   <tr>
            <td>
                 <span title="如果不填写则以新闻内容前面部分代替" style="cursor: pointer;">新闻提要</span>
                 
            </td>
            <td><input type="text" name="newsBrief" id="newsBrief" size="130" maxlength="130" class=input value="${news.newsBrief}" /></td>
          </tr>
          <tr>
            <td colspan="2">
            <auth:auth ifAnyGranted="FA_SHOP">
			<FCK:editor instanceName="newsContent" height="400px" width="100%" basePath="/plugins/fckeditor">
                <jsp:attribute name="value">${news.newsContent}</jsp:attribute>
            </FCK:editor>
            </auth:auth>
            <auth:auth ifNotGranted="FA_SHOP">
                你无权编辑该内容，请与管理员联系
            </auth:auth>
            </td>
          </tr>

					</tbody>
        </table>
        <auth:auth ifAnyGranted="FA_SHOP">
        <input type="submit" value="提交" />
        <input type="button" value="返回" onclick="window.location='${pageContext.request.contextPath}/admin/news/query/${position}'" />
        </auth:auth>
      </form>
      </center>
</body>
</html>