<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>

    <c:forEach items="${requestScope.USER_REG_ADV_740}" var="adv">
	<table width="100%" cellpadding="0" cellspacing="0" style="margin-bottom: 5px;" class="picstyle">
		<tr><td><a href="${adv.linkUrl}"><img src="<ls:photo item='${adv.picUrl}'/>" title="${adv.title}" width="100%"/></a></td></tr>
	 </table>
	</c:forEach>
<table width="746px" cellpadding="0" cellspacing="0"
	style="TABLE-LAYOUT: fixed; word-break: break-all" class="tables">
			<tr>
				<td class="titlebg"><b>${news.newsTitle}</td>
			</tr>
          <tr> 
            <td> 
               <div style="margin: 8px;" align="right">
                <form action="${pageContext.request.contextPath}/allnews" id="form1" method="post">
                <span style="margin-right: 20px"><fmt:formatDate value="${news.newsDate}" pattern="yyyy-MM-dd"/></span>
                <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
                  <fmt:message key="to.topic"/>：
	           <select id="newsCategoryId" name="newsCategoryId" onchange="this.form.submit();">
	           		 <option value=""><fmt:message key="please.select"/></option>
	                 <ls:optionGroup type="select" required="true" cache="fasle"
	                beanName="NewsCategory" beanId="newsCategoryId" beanDisp="newsCategoryName" 
	                hql="select t.newsCategoryId, t.newsCategoryName from NewsCategory t where t.status = 1 and t.userName = ?" param="${news.userName}" selectedValue="${newsCategoryId}" />
	            </select>
	            <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
	            
	            </form>
	            </div>
             	</td>
             </tr>
	<tr>
		<td height="200px" valign="top">
			<div align="center">
				<div align="left" width="100%" style="margin-left: 8px;margin-right: 5px">
					${news.newsContent}
				</div>
			</div>
		</td>
	</tr>
</table>