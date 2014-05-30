<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>

<html>
<head>
<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.js" type="text/javascript"></script>
 <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/common/default/css/errorform.css" />
<title>创建商品类型</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin/sort/save" id="sortForm" method="post" enctype="multipart/form-data">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; <a href="${pageContext.request.contextPath}/admin/sort/query">类型管理</a> &raquo; 创建商品类型</th></tr>
    </thead>
    </table>
  <table class="${tableclass}" style="width: 100%" id="col1" >
     <thead>
                    <tr class="sortable">
                        <th colspan="2">
                            <div align="center">
							<c:choose>
			                	<c:when test="${not empty sort}">修改类型</c:when>
			                	<c:otherwise>创建类型</c:otherwise>
			                </c:choose>				
                            </div>
                        </th>
                    </tr>
    </thead>
    <tbody>
    <tr>
      <td width="200px"><div align="right">类型：<font color="#ff0000">*</font></div></td>
      <td  ><input type="text" name="sortName" id="sortName" size="30" value="${sort.sortName}">
      <input id="sortId" name="sortId" value="${sort.sortId}" type="hidden">
      </td>
    </tr>
    <tr>
      <td><div align="right">次序(必须为数字)：</div></td>
      <td  >
      <input type="text" name="seq" id="seq" size="30" value="${sort.seq}">
      </td>
    </tr>
    <tr>
      <td><div align="right">Header菜单：</div></td>
      <td  >
				<select id="headerMenu" name="headerMenu">
				  <ls:optionGroup type="select" required="true" cache="true"
	                beanName="YES_NO" selectedValue="${sort.headerMenu}"/>
	            </select>	
      </td>
    </tr>
    <tr>
      <td><div align="right">导航菜单：</div></td>
      <td  >
				<select id="navigationMenu" name="navigationMenu">
				  <ls:optionGroup type="select" required="true" cache="true"
	                beanName="YES_NO" selectedValue="${sort.navigationMenu}"/>
	            </select>	
      </td>
    </tr>
    <c:if test="${sort.brandList != null}">
	      <tr>
	      <td><div align="right">品牌：</div></td>
	      <td  >
					<c:forEach var="brand"  items="${sort.brandList}">
						   ${brand.brandName } &nbsp;
					</c:forEach>
	      </td>
	    </tr>
    </c:if>
	<tr>
      <td ><div align="right">类型图片(765*240)</div></td>
      <td >
      	<input type="file" name="file" id="file" size="30"/>
      	<input type="hidden" name="pictureName" id="pictureName" size="30" value="${sort.picture}"/>
      </td>
    </tr>
          <c:if test="${sort.picture!=null && sort.picture!='' }">
    <tr>
    <td  ><div align="right">原有图片</div></td>
      <td >
      	<a href="<ls:photo item='${sort.picture}'/>" target="_blank">
      	<img src="<ls:photo item='${sort.picture}'/>" height="60" width="300"/></a>
      </td>
    </tr>
     	</c:if>
    <tr>
      <td colspan="2" ><div align="center">  
      <auth:auth ifAnyGranted="FA_PROD">
        <input type="submit" value="提交"/>
        </auth:auth>
        <input type="reset" value="重置"/>
        <input type="button" value="返回"  onclick='window.location="${pageContext.request.contextPath}/admin/sort/query"' />  
      </div></td>
    </tr>
    </tbody>
  </table>
</form>
<script language="javascript">
  $.validator.setDefaults({
	});
 $(document).ready(function() {
	$("#sortForm").validate({
		rules: {
			sortName:  "required",
			 seq: {
              number: true
            }
		},
		messages: {
			sortName: "请输入类型",
			seq: {
                number: "请输入数字"
            },
			file:"请上传商品类型图片"
		}
		});
			highlightTableRows("col1");
          //斑马条纹
     	   $("#col1 tr:nth-child(even)").addClass("even");	  
	});
</script>
</body>
</html>