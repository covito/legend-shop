<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/back-common.jsp"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
      <%@ include file="/WEB-INF/pages/common/messages.jsp"%>
		<display:table name="provinceList" requestURI="/admin/system/district/query" id="provinces" export="false" sort="external" class="${tableclass}" style="width:100%"> 
      <display:column style="width:55px;vertical-align: middle;text-align: center;" title="顺序<input type='checkbox'  id='checkbox' name='checkbox' onClick='javascript:selAll()' />">		
      <input type="checkbox" name="strArray" value="${provinces.id}"/></display:column>
     	<display:column title="名称"><input type="text" id="name" name="province" value="${provinces.province}" readonly/></display:column>
     	<display:column title="邮编"><input type="text" id="name" name="province" value="${provinces.provinceid}" readonly/></display:column>
	    <display:column title="操作" media="html"  style="width: 80px;">
		      <a href= "${pageContext.request.contextPath}/admin/system/district/load/${provinces.id}" title="修改">
		        	 <img alt="修改" src="<ls:templateResource item='/common/default/images/grid_edit.png'/> ">
		      </a>
		     <a href='javascript:confirmDeletePrivonce("${provinces.id}","${provinces.province}")' title="删除">
		      		<img alt="删除" src="<ls:templateResource item='/common/default/images/grid_delete.png'/>">
		      </a>
	      </display:column>
	    </display:table>	
	     <input type="button" value="刪除" style="float: left" onclick="return deleteAction('deleteprovince');"/>
        <script language="JavaScript" type="text/javascript">
          jQuery(document).ready(function(){
			    highlightTableRows("provinces");  
		 });
		</script>
