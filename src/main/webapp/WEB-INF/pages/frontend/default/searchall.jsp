<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/common.jsp'%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
	<script type="text/javascript" src="<ls:templateResource item='/common/js/infinite-linkage.js'/>"></script>
  <script>
	function clearCritria(){
		  $("#provinceid").val("");
		  $("#cityid").val("");
		  $("#areaid").val("");
		  $("#priceStart").val("");
		  $("#priceEnd").val("");
	}
		
	$(document).ready(function() {
	   $("select.combox").initSelect();
	   	//call ajax
		$.post("${pageContext.request.contextPath}/visitedshop", 
				function(retData) {
						$("#visitedshop").html(retData);
		},'html');
	});
		
		
  </script>
  <!--[if lt IE 7]>
    <link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/default/css/overlay-ie6.css" />
<![endif]-->
<style type="text/css">
a:visited {
	text-decoration: underline
}

a:hover {
	text-decoration: underline
}

a:active {
	text-decoration: underline
}

a:link {
	text-decoration: underline
}

BODY {
	PADDING-RIGHT: 0px;
	PADDING-LEFT: 0px;
	BACKGROUND: #ffffff;
	PADDING-BOTTOM: 0px;
	MARGIN: 0px auto;
	PADDING-TOP: 0px;
}
</style>
		<center>
			<table width="98%">
				<tr>
					<td width="140px" style="BORDER-RIGHT: #999999 1px solid;vertical-align: top;" align="left">
					<c:if test="${searchArgs.entityType == 0}">
						<div style="font-weight: bold;"><fmt:message key="search.by.price"/></div><br>
						<div><fmt:message key="from.hint"/><input type="text" id="priceStart" name="priceStart" value="${searchArgs.priceStart}" maxlength="8" size="8"/> ￥</div><br>
						<div><fmt:message key="to.hint"/><input type="text" id="priceEnd" name="priceEnd" maxlength="8" value="${searchArgs.priceEnd}" size="8" onkeydown='if(event.keyCode==13)submitSearchAllTopform(0,document.getElementById("keyword").value);'/> ￥</div>
						<br>
					</c:if>
					
					<div style="font-weight: bold;"><fmt:message key="search.shop.by.address"/></div>
							<select class="combox"  id="provinceid" name="provinceid"  requiredTitle="true"  childNode="cityid" selectedValue="${searchArgs.provinceid}"  retUrl="${pageContext.request.contextPath}/common/loadProvinces">
							</select>&nbsp;<br/><br/>
							<select class="combox"   id="cityid" name="cityid"  requiredTitle="true"  selectedValue="${searchArgs.cityid}"   showNone="false"  parentValue="${searchArgs.provinceid}" childNode="areaid" retUrl="${pageContext.request.contextPath}/common/loadCities/{value}">
							</select>&nbsp;<br/><br/>
							<select class="combox"   id="areaid" name="areaid"   requiredTitle="true"  selectedValue="${searchArgs.areaid}"    showNone="false"   parentValue="${searchArgs.cityid}"  retUrl="${pageContext.request.contextPath}/common/loadAreas/{value}">
							</select>&nbsp;<br/><br/>
				        <div style="float: right">
				        <a href="javascript:clearCritria();">重置筛选条件&nbsp;</a>
				        </div>
					</td>
					<td align="left" valign="top">
						<c:choose>
							<c:when test="${requestScope.searchResult != null && searchArgs.entityType == 0}">
							<div style="margin-left: 20px;font-size: 12pt" align="left">
				
				<c:forEach items="${requestScope.searchResult}" var="record" varStatus="status">
					<table cellpadding="2">
						<tr>
							<td>
                              <img src="<ls:images item='${record.pic}' scale='2'/>" width="100px" height="100px"  style="margin-right: 3px"/>				
							</td>
							<td style="vertical-align: top"><div>
							<div style="margin-top: 8px;font-size: 13pt">
								 <a href="${pageContext.request.contextPath}/views/${record.prodId}" target="_blank">${record.name}</a>
								
							</div>
							<div>${record.content}</div>
							<div>
							<c:if test="${record.price != null}">
								<fmt:message key="prod_price"/>  <s><fmt:formatNumber type="currency" value="${record.price}" pattern="${CURRENCY_PATTERN}"/></s>
							</c:if>
							<c:if test="${record.cash != null}">
								<fmt:message key="prod_cash"/>
								<span style="font-weight: bold;"><fmt:formatNumber type="currency" value="${record.cash}" pattern="${CURRENCY_PATTERN}"/></span>
							</c:if>
							</div>
							<div><fmt:message key="product.sort"/> - <a href="${pageContext.request.contextPath}/sort/${record.sortId}" target="_blank">${record.sortName}</a> 
							<a href="${pageContext.request.contextPath}/nsort/${record.sortId}-${record.nsortId}" target="_blank">${record.nsortName}</a> 
							<a href="${pageContext.request.contextPath}/nsort/${record.sortId}-${record.nsortId}?subNsortId=${record.subNsortId}" target="_blank">${record.subNsortName}</a>
							</div>
							<c:if test="${'C2C' == applicationScope.BUSINESS_MODE}">
							<div style="color: green">
							<fmt:message key="shop.name"/> - <a href="<ls:domain shopName='${record.userName}' />"  style="color: green" target="_blank">${record.userName}</a>
							</div>
							</c:if>
							</div></td>
						</tr>
					</table>
					</c:forEach>
					<c:if test="${toolBar!=null}">
			<p align="right">
				<c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
			</p>
			</c:if>
			
					</div>	
							</c:when>
							<c:when test="${requestScope.searchResult != null && searchArgs.entityType == 1}">
<!-- shop -->
<div style="margin-left: 20px;font-size: 12pt" align="left">
				<c:forEach items="${requestScope.searchResult}" var="record" varStatus="status">
					<table cellpadding="2">
						<tr>
							<td style="vertical-align: top" align="left">
							<div>
							<div style="margin-top: 8px;font-size: 13pt">
							  <a href="<ls:domain shopName='${record.userName}' />"  target="_blank">${record.siteName}</a>
							</div>
							<div>${record.detailDesc}</div>
							<div><fmt:message key="shop.contact.method"/> - <fmt:message key="Phone"/>：
								<span style="color: green">${record.userTel}</span> &nbsp;  
								QQ:<span style="color: green">${record.qq}</span> &nbsp; 
								MSN:<span style="color: green"> ${record.msnNumber}</span>
							</div>
							<div>
								<c:if test="${record.province != null}">
								<fmt:message key="address"/> -  ${record.province}
								 <c:if test="${record.city != null}">&raquo; ${record.city}
								 	<c:if test="${record.area != null}">&raquo; ${record.area}</c:if>
								 </c:if>
								</c:if>
							</div>
							<c:if test="${record.postAddr != null}">
								<div><fmt:message key="detail.address"/> - ${record.postAddr}</div>
							</c:if>
							
							
							</div></td>
						</tr>

					</table>
					</c:forEach>
					<c:if test="${toolBar!=null}">
						<p align="right">
							<c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
						</p>
					</c:if>
		</div>
							</c:when>
							<c:otherwise>
								<div style="font-size: 13pt;margin-left: 20px">
									<fmt:message key="can.not.found"><fmt:param value="${searchArgs.keyword}"/></fmt:message>
								</div>
							</c:otherwise>
						</c:choose>

					</td>
					<td width="255px" valign="top" align="left"><div id="visitedshop" name="visitedshop"></div></td>
				</tr>
			</table>
		</center>