<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<%@include file='/WEB-INF/pages/common/common.jsp'%>

<%
			int offset=((Integer)request.getAttribute("offset")).intValue();
	%>
	<script type="text/javascript">
<!--
		function pager(curPageNO){
			window.location.href="${pageContext.request.contextPath}/allnews?curPageNO="+curPageNO;
		}
		
		function changePager(curPageNO){
			document.getElementById("curPageNO").value=curPageNO;
			document.getElementById("form1").submit();
		}
//-->
</script>
      <c:forEach items="${requestScope.USER_REG_ADV_740}" var="adv">
		<table width="100%"  cellspacing="0" cellpadding="0" style="margin-bottom: 4px; margin-right: 5px;"  class="picstyle">
			<tr><td><a href="${adv.linkUrl}"><img src="<ls:photo item='${adv.picUrl}'/>" title="${adv.title}" width="100%"/></a></td></tr>
		 </table>
		</c:forEach>
          <table width="100%" class="tables" cellpadding="0" cellspacing="0">
            <tr><td class="titlebg"><fmt:message key="newsCenter"/></td></tr>
         <ls:cache key="ALL_NEWS_CATEGORY_${newsCategoryId}" cacheName="NewsList">
          <tr> 
            <td> 
               <div style="margin: 8px;" align="right">
                <form action="${pageContext.request.contextPath}/allnews" id="form1" method="post">
                  <fmt:message key="to.topic"/>：
	           <select id="newsCategoryId" name="newsCategoryId" onchange="this.form.submit();">
	          		 <option value=""><fmt:message key="please.select"/></option>
	                 <ls:optionGroup type="select" required="true" cache="fasle"
	                beanName="NewsCategory" beanId="newsCategoryId" beanDisp="newsCategoryName" 
	                hql="select t.newsCategoryId, t.newsCategoryName from NewsCategory t where t.status = 1 and t.userName = ?" param="${sessionScope.shopName}" selectedValue="${newsCategoryId}"/>
	            </select>
	            <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
	            
	            </form>
	            </div>
             	</td>
             </tr>
             </ls:cache>
           <c:forEach items="${requestScope.list}" var="news" varStatus="status">
			<tr> 
                <td align="left" style="border-bottom: 1px dotted #E9E9E9;padding: 3px">
                <div>
					&nbsp;${status.index+1}.
					<a href="${pageContext.request.contextPath}/news/${news.newsId}">
					<c:choose>
						<c:when test="${news.highLine == 1}">
							<span style="font-weight:bold; color:#FF6600">${news.newsTitle}</span>
						</c:when>
						<c:otherwise>
							<span style="font-weight:bold;">${news.newsTitle}</span>
						</c:otherwise>
				    </c:choose>
					</a>
				  &nbsp;[<fmt:formatDate value="${news.newsDate}" pattern="yyyy-MM-dd"/>]
				  </div>
				   <div style="margin-left: 50px;margin-bottom: 8px;margin-top: 2px;">${news.newsBrief}</div>
				</td>
			 </tr>
			</c:forEach>
			<tr><td>
			<div style="margin: 8px;" align="right">
				<c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
			</div>
			</td></tr>
          </table>

