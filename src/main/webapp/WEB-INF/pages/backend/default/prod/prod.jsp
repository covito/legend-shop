<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<html>
<head>
<title>创建商品类型</title>
		<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
		<script type="text/javascript" src="<ls:templateResource item='/common/js/infinite-linkage.js'/>"></script>
		<script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
		<script src="<ls:templateResource item='/plugins/My97DatePicker/WdatePicker.js'/>" type="text/javascript"></script>
</head>
<body>
<form action="<ls:url address='/admin/product/save'/>"  id="saveProdForm" name="saveProdForm" method="post" enctype="multipart/form-data" onsubmit="return checkform()">
    <input type="hidden" value="next" id="nextAction" name="nextAction"/>
    <input type="hidden" id="prodId" name="prodId" value="${prod.prodId}">
    <input type="hidden" id="globalSort" name="globalSort" value="${sort1.sortId}">
    <input type="hidden" id="globalNsort" name="globalNsort" value="${nsort2.nsortId}">
    <input type="hidden" id="globalSubSort" name="globalSubSort" value="${nsort3.nsortId}">
    <input type="hidden" id="globalBrand" name="globalBrand" value="${brand.brandId}">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr><th><a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商品管理  &raquo; <a href="<ls:url address='/admin/product/query'/>">商品管理</a> &raquo; <a href="${pageContext.request.contextPath}/admin/product/load">创建商品</a>
    		<c:if test="${prod.name != null}">&raquo; <a href= "${pageContext.request.contextPath}/views/${prod.prodId}" target="_blank">${prod.name}</a></c:if>
    	</th></tr>
    </thead>
    <tr class="odd"><td>
    	<jsp:include page="/admin/product/createsetp">
    		<jsp:param name="step" value="1"/>
    	</jsp:include>
    	</td></tr>
    </table>
  <table class="${tableclass}" style="width: 100%" id="col1">
   <thead>
    <tr>
      <td colspan="3">
	      <c:if test="${sort1 != null}"> 类目：   ${sort1.sortName}</c:if>
	      <c:if test="${nsort2 != null}">  &raquo;   ${nsort2.nsortName}</c:if>
	      <c:if test="${nsort3 != null}">  &raquo;   ${nsort3.nsortName} </c:if>
	      <c:if test="${brand != null}">&raquo;   ${brand.brandName}</c:if>
	      &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/sort/publish/${prod.prodId}"><b>更改类目</b></a>
      </td>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td width="120px"><div align="center">选择店铺内商品类型</div></td>
      <td width="48%">
      <div>
		          	      <select class="combox"  id="sortId" name="sortId"  requiredTitle="-- 一级分类 -- "  childNode="nsortId" selectedValue="${prod.sortId}"  retUrl="${pageContext.request.contextPath}/sort/loadSorts/${prod.sortId}">
							</select>
							<select class="combox"   id="nsortId" name="nsortId"  requiredTitle="-- 二级分类 --"  selectedValue="${prod.nsortId}"   showNone="false"  parentValue="${prod.sortId}" childNode="subNsortId" retUrl="${pageContext.request.contextPath}/sort/loadNSorts/{value}">
							</select>
							<select class="combox"   id="subNsortId" name="subNsortId"   requiredTitle="-- 三级分类 --"  selectedValue="${prod.subNsortId}"    showNone="false"   parentValue="${prod.nsortId}"   childNode="brandId"  retUrl="${pageContext.request.contextPath}/sort/loadSubNSorts/{value}">
							</select>
 							<select class="combox"   id="brandId" name="brandId"   requiredTitle="-- 品牌 --"  selectedValue="${prod.brandId}"    showNone="false"   parentValue="${prod.subNsortId}"  retUrl="${pageContext.request.contextPath}/brand/loadBrands/{value}">
					</select>   
        </div>
        <div style="margin-top: 3px">
        还没有商品类型？请先&nbsp;<a href="${pageContext.request.contextPath}/admin/sort/load">创建类型</a>&nbsp;<a href="${pageContext.request.contextPath}/admin/brand/load">创建品牌</a>
		</div></td>
      <td width="25%" rowspan="9" align="center" valign="middle" style="margin: 0px">
      	<c:choose>
      		<c:when test="${prod.pic!=null}"><a href="<ls:photo item='${prod.pic}'/>" target="_blank">
      		<img src="<ls:images item='${prod.pic}' scale='0'/>" height="350px" width="350px"/></a>
      		<br/>
      		<c:if test="${prod.smallPic != null}">
      				<a href="<ls:photo item='${prod.smallPic}'/>" target="_blank">[查看缩略图]</a>
      		</c:if>
      		</c:when>
      		<c:otherwise>请上传商品图片</c:otherwise>
      	</c:choose>
      	
      	</td>
    </tr>
    
    <tr>
      <td width="120px"><div align="center">商品<font color="#ff0000">*</font></div></td>
      <td>
			<input type="text" name="name" id="name" size="50" value="${prod.name}" maxlength="120"/></td>
    </tr>
    <tr>
      <td width="120px"><div align="center">型号</div></td>
      <td>
			<input type="text" name="modelId" id="modelId" size="50" value="${prod.modelId}" maxlength="50"/></td>
    </tr>
    <tr>
      <td width="120px"><div align="center">关键字</div></td>
      <td>
			<input type="text" name="keyWord" id="keyWord" size="50" value="${prod.keyWord}" maxlength="100"/></td>
    </tr> 
     <tr>
      <td width="120px"><div align="center">价格</div></td>
      <td>
			原价&nbsp;<input type="text" name="price" id="price" size="10" value="${prod.price}" maxlength="15" />&nbsp;
            现价&nbsp;<input type="text" name="cash" id="cash" size="10" value="${prod.cash}" maxlength="15"/>&nbsp;
           运费&nbsp;<input type="text" name="carriage" id="carriage" size="10" value="${prod.carriage}" maxlength="15"/>
		</td>
    </tr>    
   <tr>
      <td width="120px"><div align="center">库存量</div></td>
      <td>
			<input type="text" name="stocks" id="stocks" size="15" value="${prod.stocks}" maxlength="15"/>      </td>
    </tr>
    <tr>
      <td width="120px"><div align="center">类型</div></td>
      <td>
		       <select id="prodType" name="prodType" style="display: none">
				  <ls:optionGroup type="select" required="true" cache="true"
	                beanName="PRODUCT_TYPE" selectedValue="${prod.prodType}"/>
	            </select>
	        是否精品产品
	       <select id="commend" name="commend">
	       			<ls:optionGroup type="select" required="false" cache="true"  beanName="YES_NO_STR" selectedValue="${prod.commend}"/>
      		</select>       
      		&nbsp;&nbsp;是否热销产品
      		<select id="hot" name="hot">
      			    <ls:optionGroup type="select" required="false" cache="true" beanName="YES_NO_STR" selectedValue="${prod.hot}"/>
      		</select>    
       </td>
    </tr> 
    <tr>
      <td width="120px"><div align="center">有效时间</div></td>
      <td>
			开始时间
			<input readonly="readonly" name="startDate" id="startDate" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${prod.startDate}" pattern="yyyy-MM-dd"/>' />
			&nbsp;
			结束时间
			<input readonly="readonly"  name="endDate" id="endDate" class="Wdate" type="text" onClick="WdatePicker()" value='<fmt:formatDate value="${prod.endDate}" pattern="yyyy-MM-dd"/>' />
	  </td>
    </tr> 
    <tr>
      <td width="120px"><div align="center">商品图片<font color="#ff0000">*</font></div></td>
      <td>
      <div style="float: left;margin: 0px"><input type="file" name="file" id="file" />
      	<input type="hidden" name="pic" id="pic"  value="${prod.pic}"/>
      	上传缩略图：<input id="useSmallPic" name="useSmallPic"  type="checkbox"  onclick="javascript:showSmallPicDiv(this)" />
      	</div>
      	<div style="display: none;float: left;margin: 0px" id="smallPicDiv" name="smallPicDiv">
      	<input type="file" name="smallPicFile" id="smallPicFile" />
      	<input type="hidden" name="smallPic" id="smallPic"  value="${prod.smallPic}"/>
      	</div>
      <div class="clear" style="clear:both;">图片分辨率640×470,大小不能超过1M</div>
      	
      	
      	</td>
    </tr>
     <tr>
      <td width="120px"><div align="center">简要描述</div></td>
      <td colspan="2">
                     	<input type="text" name="brief" id="brief" size="81"  value="${prod.brief}" maxlength="120"/> 
      </td>
    </tr>    
    <auth:auth ifAnyGranted="FA_PROD">
      <tr>
      <td width="14%"><div align="center">详细描述</div></td>
      <td colspan="2">
			<FCK:editor instanceName="content" height="600px" basePath="/plugins/fckeditor">
                <jsp:attribute name="value">${prod.content}</jsp:attribute>
            </FCK:editor>  
	 </td>
    </tr>
    </auth:auth>
   <tr>
      <td width="120px"><div align="center">是否立即上线</div></td>
      <td colspan="2">
                <select id="status" name="status">
				  <ls:optionGroup type="select" required="true" cache="true"
	                beanName="ONOFF_STATUS" selectedValue="${prod.status}"/>
	            </select> 
	            &nbsp;下线放入仓库中
      </td>
    </tr>    
    <tr>
      <td colspan="3"><div align="center">
        <auth:auth ifAnyGranted="FA_PROD"> 
            <input type="button" value="下一步" onclick="javascript:submitNext()"/>
            <input type="button" value="完成" onclick="javascript:submitFinish()"/>
        </auth:auth>  
        <input type="reset" value="重置"/>
		<input type="button" value="返回" onclick='window.location="<ls:url address='/admin/product/query'/>"'/>
      </div></td>
    </tr>
    </tbody>
  </table>
   <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 带星号<font color="#ff0000">*</font>的属性务必要填写，如果不填写则不在页面展示。如价格和库存量等。<br>
   2. 商品类型：首先选择了一级类型，自动关联对应的二级类型，选择了二级类型，自动关联对应的三级类型<br>
   3. 是否推荐到首页如果选择是，则会在首页的精品推荐中出现<br>
   4. 商品在开始时间和时间结束之内有效，如果不填写则长期有效<br>
   5. 详细描述可以上传图片，编辑html元素
   </td><tr></table> 
</form>
   <script language="JavaScript" type="text/javascript">
    jQuery(document).ready(function() {
            //三级联动
           $("select.combox").initSelect();
          //斑马条纹
     	   $("#col1 tr:nth-child(even)").addClass("even");
     	    highlightTableRows("col1");  
		  var useSmallPic = '${prod.useSmallPic}';
		  if('Y' == useSmallPic){
		  		var useSmallPic = document.getElementById("useSmallPic");
		  		useSmallPic.checked = "checked";
		  		showSmallPicDiv(useSmallPic);
		  }
		 
	});
	
	function checkform() {
		var content = document.getElementById("content");
		var name = document.getElementById("name");
		if(name.value==null||name.value=='')
		{
			alert("请输入商品!");
			name.focus();
			return false;
		}
		
	    if(name.length <=4)
		{
			alert("商品名称不能少于4位!");
			name.focus();
			return false;
		}

		var price = document.getElementById("price");
		if((price.value != null || price.value!='') && isNaN(price.value)){
			alert("商品原价必须为数字!");
			price.focus();
			return false;
		}
		var cash = document.getElementById("cash");
        if((cash.value != null || cash.value!='') && isNaN(cash.value)){
			alert("商品价格必须为数字!");
			cash.focus();
			return false;
		}
		var carriage = document.getElementById("carriage");
	    if((carriage.value != null || carriage.value!='') && isNaN(carriage.value)){
			alert("商品运费必须为数字!");
			carriage.focus();
			return false;
		}
		var stocks = document.getElementById("stocks");
	        if((stocks.value != null || stocks.value!='') && isNaN(stocks.value)){
			alert("库存量必须为数字!");
			cash.focus();
			return false;
		}	
	  var file = document.getElementById("file");
	  if(file.value==null||file.value=='')
		{
		var pic = document.getElementById("pic");
		if(pic.value==null||pic.value==''){
			alert("请上传商品图片!");
			file.focus();
			return false;
		}
		}			
		return true ;
  }
  
  function submitNext(){
  	var saveProdForm = document.getElementById("saveProdForm");
  	if(checkform()){
  		saveProdForm.submit();
  	}
  }
  
  function submitFinish(){
    	var saveProdForm = document.getElementById("saveProdForm");
    	document.getElementById("nextAction").value = "success";
  		  	if(checkform()){
  		saveProdForm.submit();
  	}
  }
  
  function showSmallPicDiv(obj){
  	var smallPicDiv = document.getElementById("smallPicDiv");
	  if(obj.checked){
	  			smallPicDiv.style.display="block";
	  }else{
	 		 smallPicDiv.style.display="none";
	  }
  		
  }
  
</script>
</body>
</html>