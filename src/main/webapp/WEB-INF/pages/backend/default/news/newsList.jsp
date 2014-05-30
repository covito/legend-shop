<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<html>
<head>
	<script src="<ls:templateResource item='/common/js/jquery.js'/>" type="text/javascript"></script>
    <script src="<ls:templateResource item='/common/default/js/alternative.js'/>" type="text/javascript"></script>
    <script src="<ls:templateResource item='/common/js/recordstatus.js'/>" type="text/javascript"></script>
 	<%@ include file="/WEB-INF/pages/common/dialog.jsp"%>
    <title>新闻列表</title>
</head>
<body>
    <%
        Integer offset = (Integer) request.getAttribute("offset");
    %>
    <form action="${pageContext.request.contextPath}/admin/news/query/${news.position}" id="form1" method="post">
    <table class="${tableclass}" style="width: 100%">
    <thead>
    	<tr>
	    	<th>
	    		<a href="<ls:url address='/admin/index'/>" target="_parent">首页</a> &raquo; 商城管理  &raquo; 资讯管理  &raquo;
		    	<a href="${pageContext.request.contextPath}/admin/news/query/${news.position}">
			    	 <ls:optionGroup type="label" required="true" cache="true"
			                beanName="NEWS_POSITION" selectedValue="${news.position}" defaultDisp=""/>
		         </a>
	    	</th>
    	</tr>
    </thead>
     <tbody><tr><td>
     <div align="left" style="padding: 3px">
       栏目
	           <select id="newsCategoryId" name="newsCategoryId">
	                 <ls:optionGroup type="select" required="false" cache="fasle"
	                beanName="NewsCategory" beanId="newsCategoryId" beanDisp="newsCategoryName" 
	                hql="select t.newsCategoryId, t.newsCategoryName from NewsCategory t where t.status = 1 and t.userName = ?" param="${sessionScope.SPRING_SECURITY_LAST_USERNAME}" selectedValue="${news.newsCategoryId}"/>
	            </select>
	       商品类型
	            <lb:sortOption id="sortId"   type="P" selectedValue="${news.sortId}"/>
	          状态
	           <select id="status" name="status">
				  <ls:optionGroup type="select" required="false" cache="true"
	                beanName="ONOFF_STATUS" selectedValue="${news.status}"/>
	            </select>
     </div>
	<div align="left" style="padding: 3px">
        <input type="hidden" id="curPageNO" name="curPageNO" value="${curPageNO}" />
	            标题
	            <input type="text" name="newsTitle" maxlength="50" size="30" value="${news.newsTitle}"/>
	     
         <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
                 商城 
               <input type="text" name="userName" maxlength="50" value="${news.userName}" />
        </auth:auth>
        	 <input type="submit" value="搜索"/>
       		 <auth:auth ifAnyGranted="FA_SHOP">
		            <input type="button" value="创建新闻" onclick='window.location="${pageContext.request.contextPath}/admin/news/load/${news.position}"'/>
		            <input type="button" value="创建新闻栏目" onclick='window.location="${pageContext.request.contextPath}/admin/newsCategory/load"'/>
            </auth:auth>
	</div>

 </td></tr></tbody>
    </table>
    
    </form>
    <div align="center">
        <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
    <display:table name="list" requestURI="/admin/news/query/${news.position}" id="item"
         export="true" class="${tableclass}" style="width:100%"  sort="external">
      <display:column title="顺序" class="orderwidth"><%=offset++%></display:column>
       <auth:auth ifAnyGranted="F_VIEW_ALL_DATA">
        <display:column title="商城" property="userName" sortable="true" sortName="n.userName"></display:column>
      </auth:auth>
      <display:column title="标题"><a href="${pageContext.request.contextPath}/news/${item.newsId}" target="_blank">${item.newsTitle}</a></display:column>
      <display:column title="栏目">
	                 ${item.newsCategoryName}
      </display:column>
      <display:column title="商品类型">
	                 ${item.sortName}
      </display:column>
      <display:column title="录入时间" property="newsDate" format="{0,date,yyyy-MM-dd HH:mm}" sortable="true" sortName="n.newsDate"></display:column>
      <display:column title="高亮" sortable="true" sortName="n.highLine"  style="width:40px">
      		 <c:choose>
                <c:when test="${item.highLine == 1}"><img src="<ls:templateResource item='/common/default/images/right.png'/> "></c:when>
                <c:otherwise><img src="<ls:templateResource item='/common/default/images/wrong.png'/> "></c:otherwise>
           </c:choose>
      </display:column>
           <auth:auth ifAnyGranted="FA_SHOP">
			      <display:column title="操作" media="html" style="width:80px">
			   		  	  <c:choose>
					  		<c:when test="${item.status == 1}">
					  			<img name="statusImg"  itemId="${item.newsId}"  itemName="${item.newsTitle}"  status="${item.status}"  class="cursor_pointer"   src="<ls:templateResource item='/common/default/images/blue_down.png'/> ">
					  		</c:when>
					  		<c:otherwise>
					  			<img  name="statusImg"   itemId="${item.newsId}"  itemName="${item.newsTitle}"  status="${item.status}"   class="cursor_pointer"  src="<ls:templateResource item='/common/default/images/yellow_up.png'/> ">
					  		</c:otherwise>
					  	</c:choose>   
			      <a href= "${pageContext.request.contextPath}/admin/news/load/${news.position}/${item.newsId}" title="修改"><img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> "></a>
			        <a href='javascript:deleteById("${item.newsId}")' title="删除"><img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/> "></a>
			      </display:column>
            </auth:auth>
    </display:table>
        <c:if test="${not empty toolBar}">
            <c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
        </c:if>
        </div>
        
         <table style="width: 100%; border: 0px"><tr><td align="left">说明：<br>
   1. 每个文章都是挂在栏目下面<br>
   2. 文章处于上线状态页面可见，处于下线状态页面不可见<br>
   3. 分类，指商品类型，用于标识该文章是描述那一个商品类型<br>
   4. 是否重点，如果是重点则在页面中以醒目颜色标注<br>
   </td><tr></table> 
        
         <script language="JavaScript" type="text/javascript">
<!--

  function deleteById(id) {
      if(confirm("  确定删除 ?")){
            window.location = "${pageContext.request.contextPath}/admin/news/delete/"+id;
        }
    }
         $(document).ready(function(){
          	$("img[name='statusImg']").click(function(event){
          		$this = $(this);
          		initStatus($this.attr("itemId"), $this.attr("itemName"),  $this.attr("status"), "${pageContext.request.contextPath}/admin/news/updatestatus/", event.target,"${pageContext.request.contextPath}");
       			 }
       		 );
              	
       		     highlightTableRows("item"); 
          });
         
//-->
</script>
        
</body>
</html>

